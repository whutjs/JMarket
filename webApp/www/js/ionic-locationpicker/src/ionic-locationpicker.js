//"use strict";
var app = angular.module('ionic-locationpicker', ['ionic']);
app.directive('ionicLocationPicker', ['$ionicPopup', '$timeout','LocationPickerService','$ionicScrollDelegate','$ionicModal', function ($ionicPopup, $timeout,LocationPickerService, $ionicScrollDelegate,$ionicModal) {
  return {
    restrict: 'AE',
    template:  '<input type="text"  id="location" placeholder={{vm.placeholder}} ng-model="locationdata"  name="location" class={{vm.cssClass}} readonly required  style="width:100%; background-color: #fff; border: 1px solid #ddd; border-radius: 2px;padding: 18px 15px;height: 34px;">',
    scope: {
      locationdata: '=',
      backdrop:'@',
      backdropClickToClose:'@',
      buttonClicked: '&'
    },
    link: function (scope, element, attrs) {
        var vm=scope.vm={},locationpickerModel=null;
        //根据城市数据来 设置Handle。
        vm.provinceHandle="provinceHandle"+attrs.locationdata;
        vm.locationHandle="locationHandle"+attrs.locationdata;
        vm.countryHandle="countryHandle"+attrs.locationdata;
        vm.placeholder=attrs.placeholder || "请选择城市";
        vm.okText=attrs.okText || "确定";
        vm.cssClass=attrs.cssClass;
        vm.barCssClass=attrs.barCssClass || "bar-self-design";
        vm.backdrop=scope.$eval(scope.backdrop) || false;
        vm.backdropClickToClose=scope.$eval(scope.backdropClickToClose) || false;
        vm.locationData=LocationPickerService.locationList;
        vm.tag=attrs.tag || "-";
        vm.returnOk=function(){
          locationpickerModel && locationpickerModel.hide();
          scope.buttonClicked && scope.buttonClicked();
        }
        vm.clickToClose=function(){
          vm.backdropClickToClose && locationpickerModel && locationpickerModel.hide();
        }
        vm.getData=function(name){
          $timeout.cancel(vm.scrolling);//取消之前的scrollTo.让位置一次性过渡到最新
          $timeout.cancel(vm.dataing);//取消之前的数据绑定.让数据一次性过渡到最新
          switch(name)
          {
            case 'province':
              if (!vm.locationData) return false;
              var province=true,length=vm.locationData.length,Handle=vm.provinceHandle,HandleChild=vm.locationHandle;
            break;
            case 'location':
              if (!vm.province.sub) return false;
              var location=true,length=vm.province.sub.length,Handle=vm.locationHandle,HandleChild=vm.countryHandle;
            break;
            case 'country':
              if (!vm.location.sub) return false;
              var country=true,Handle=vm.countryHandle,length=vm.location.sub.length;
            break;
          }
          var top= $ionicScrollDelegate.$getByHandle(Handle).getScrollPosition().top;//当前滚动位置
          var index = Math.round(top / 36);
          if (index < 0 ) index =0;//iOS bouncing超出头
          if (index >length-1 ) index =length-1;//iOS bouncing超出尾
          if (top===index*36 ) {
            vm.dataing=$timeout(function () {
                province && (vm.province=vm.locationData[index],vm.location=vm.province.sub[0],vm.country={},(vm.location && vm.location.sub && (vm.country=vm.location.sub[0])));//处理省市乡联动数据
                location &&  (vm.location=vm.province.sub[index],vm.country={},(vm.location && vm.location.sub && (vm.country=vm.location.sub[0])));//处理市乡联动数据
                country &&  (vm.country=vm.location.sub[index]);//处理乡数据
                HandleChild && $ionicScrollDelegate.$getByHandle(HandleChild).scrollTop();//初始化子scroll top位
                //数据同步
                (vm.location.sub && vm.location.sub.length>0) ? (scope.locationdata=vm.province.name +vm.tag+  vm.location.name+vm.tag+vm.country.name ) :(scope.locationdata=vm.province.name +vm.tag+  vm.location.name)
            },150)
          }else{
            vm.scrolling=$timeout(function () {
             $ionicScrollDelegate.$getByHandle(Handle).scrollTo(0,index*36,true);
            },150)
          }

        }

        element.on("click", function () {
            //零时处理 点击过之后直接显示不再创建
            if (!attrs.checked) {
              locationpickerModel && locationpickerModel.remove();
            }else{
              locationpickerModel && locationpickerModel.show();
              return
            }
            attrs.checked=true;
            $ionicModal.fromTemplateUrl('js/ionic-locationpicker/src/location-picker-modal.html', {
              scope: scope,
              animation: 'slide-in-up',
              backdropClickToClose:vm.backdropClickToClose
            }).then(function(modal) {
              locationpickerModel = modal;
              //初始化 先获取数据后展示
              $timeout(function () {
                vm.getData('province');
                locationpickerModel.show();
              },100)
            })
        })
        //销毁模型
        scope.$on('$destroy', function() {
          locationpickerModel && locationpickerModel.remove();
        });
    }
  }
}]);
