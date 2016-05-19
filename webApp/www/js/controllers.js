var app = angular.module('Jmarket.controllers', []);

//用户侧边栏
app.controller('AppCtrl', function($scope,$state,$http,$timeout,$ionicHistory,ApiEndpoint, $ionicLoading,UserInfoService,MainPageDataService,$ionicSideMenuDelegate) {


  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:

  //每次进入view触发事件
  $scope.$on('$ionicView.enter', function(e) {

    $scope.userData = UserInfoService.getInfo();
  });

  $scope.$on('updateAvatar', function(){
    $scope.userData = UserInfoService.getInfo();
  })

  //向userMainPage发送主页数据刷新消息
  $scope.$on("uploadSuccess",function(){
    console.log("uploadSuccess");
    $scope.$broadcast("user.uploadSuccess");
  });

  //搜索相关
  $scope.searchInput={};
  $scope.mySearch = function(){
    //{"page", “1”,
    //“page_size”:”24”,
    //“keyword”:”自行车”
    //}
  $ionicSideMenuDelegate.toggleLeft(false);
  $ionicHistory.nextViewOptions({
    disableBack: true
  });
  $state.go('app.main');
    $timeout(function(){
      $scope.$broadcast("usersearchDataCome",$scope.searchInput);
    },1000);

  }

  /*
   * if given group is the selected group, deselect it
   * else, select the given group
   */
  $scope.toggleGroup = function(group) {
    group.show = !group.show;
  };
  $scope.isGroupShown = function(group) {
    return group.show;
  };

  $scope.exit = function(){
    console.log("seeyou");
    UserInfoService.logout();
    UserInfoService.setInfo({});
    UserInfoService.setPostData({});
    $state.go('guestView.main');

  }

});

//游客侧边栏
app.controller('GuestViewCtrl', function($scope,$http,$state,$ionicHistory,$timeout,$ionicLoading,ApiEndpoint,MainPageDataService,$ionicSideMenuDelegate){

  /*
   * if given group is the selected group, deselect it
   * else, select the given group
   */


  $scope.toggleGroup = function(group) {
    group.show = !group.show;
  };
  $scope.isGroupShown = function(group) {
    return group.show;
  };



//搜索相关
  $scope.searchInput={};
  $scope.mySearch = function(){
    //{"page", “1”,
    //“page_size”:”24”,
    //“keyword”:”自行车”
    //}
    $ionicSideMenuDelegate.toggleLeft(false);
    $ionicHistory.nextViewOptions({
      disableBack: true
    });
    $state.go('guestView.main');
    $timeout(function(){
      $scope.$broadcast("searchDataCome",$scope.searchInput);
    },1000);


  }

});

//主页(包括用户和游客)
app.controller('MainCtrl', function ($scope,$state,$ionicPopover,$ionicLoading, $http,ApiEndpoint,MainPageDataService,UserInfoService){

  $scope.firstType=0;
  $scope.secondType=0;
  $scope.price=0; //最高价格
  $scope.quality=0; //最高品质
  $scope.campus=0; //0 不限,1 闵行 2 徐汇
  $scope.sort=0; //0 热度 , 1 时间
  $scope.page = 1;
  $scope.searchPage = 1;
  $scope.mainData = [];
  $scope.rawData=[];
  $scope.EOD = true;
  $scope.state = 0;// 0 浏览商品模式 1 搜索数据模式
  $scope.backFromDetail = false;
  $scope.once2 = true;
  $scope.once = true;

  //展示主页数据
  $scope.showData = function(){

    var j = $scope.mainData.length;

    for (var i= 0; i <$scope.rawData.length; i++) {


      $scope.rawData = MainPageDataService.getData();
      var avatar_path = ($scope.rawData[i].avatar_path) ? ApiEndpoint.imgUrl+$scope.rawData[i].avatar_path:'img/user.png';
      var postImgPath_show =($scope.rawData[i].postImgPath[0]) ? ApiEndpoint.imgUrl+$scope.rawData[i].postImgPath[0]:'img/sample/sample1.png';
      var date = $scope.rawData[i].postDate.substr(0,$scope.rawData[i].postDate.lastIndexOf('.'));
      $scope.mainData[i+j] = {
        item_id: $scope.rawData[i].item_id,
        avatar_path: avatar_path,
        username: $scope.rawData[i].username,
        postDate: date,
        postImgPath: $scope.rawData[i].postImgPath,
        postImgPath_show: postImgPath_show,
        price: $scope.rawData[i].price,
        location: $scope.rawData[i].location,
        title: $scope.rawData[i].title,
        quality: $scope.rawData[i].quality,
        phone: $scope.rawData[i].phone,
        wechat:$scope.rawData[i].wechat,
        qq:$scope.rawData[i].qq,
        details:$scope.rawData[i].description,
      };
    }
    MainPageDataService.setMain($scope.mainData);

  };

  //获取主页数据
  $scope.getData = function(){
    console.log($scope.page);
    var success = function(response){
      console.log(response);
      var status =  response.status;
      var res = eval(response.data);
      console.log(res);
      if(status == 200) {
        $scope.rawData = eval(res.items);
      }else{
        $ionicLoading.show({
          template:"服务器出了一点小故障哦..",
          duration:1000,
        });
        console.log(response.status);
      }
      if($scope.rawData.length < 10 && $scope.once){
        $scope.EOD = false;
        $scope.once = false;
        $ionicLoading.show({
          template:"没有更多商品了哟~~",
          duration:1000,
        });
      }else{
        $scope.page+=1;
      }
      MainPageDataService.setData($scope.rawData,{page:$scope.page});
      $ionicLoading.hide();
      $scope.showData();
    };
    var error= function(e){
      $ionicLoading.hide();
      $ionicLoading.show({
        template:"网络没连接哦,亲~~",
        duration:1000,
      });
    }

      $ionicLoading.show({
        template: '<p>努力加载中...</p><ion-spinner icon="ripple"></ion-spinner>'
      });
    $http({
      url: ApiEndpoint.url + '/getItemAction',
      method: "GET",
      params:{first_type:$scope.firstType, second_type:$scope.firstType ,page:$scope.page,page_size:10,place:$scope.campus,order:$scope.sort,max_price:$scope.price,max_state:$scope.quality}
    }).then(success,error);
  };

  $scope.getSearchData = function(msg){
    if(msg != null){
      $scope.searchInput = eval(msg);
    }
    $scope.searchInput.page = $scope.searchPage;//当前的搜索页
    $scope.searchInput.page_size = 10;//设置页面大小

    var success = function(response){

      var res = eval(response.data);
      console.log(res);
      var flag = res.flag;
      if(flag == "success"){
        $scope.rawData = eval(res.items);
        console.log($scope.rawData);
        MainPageDataService.setData($scope.rawData);
        $scope.showData();
        $ionicLoading.hide();

        if($scope.rawData.length < 10 && $scope.once2 ){
          $scope.once2 = false;
          $scope.EOD = false;
          $ionicLoading.show({
            template:"没有更多商品了哟~~",
            duration:1000,
          });
        }else{
          $scope.searchPage += 1;
        }
      }else{
        $ionicLoading.hide();
        //alert("服务器除了点问题哟")
      }
    };
    var fail = function(e){
      $ionicLoading.hide();
      $ionicLoading.show({
        template:"网络未连接哦~~",
        duration:1000,
      });
    };

    $ionicLoading.show({
      template: '<p>努力加载中...</p><ion-spinner icon="ripple"></ion-spinner>'
    });

    $http({
      url:ApiEndpoint.url+'/searchAction',
      method:'POST',
      headers: {

        'Content-Type': 'application/json'
      },
      data:$scope.searchInput,
    }).then(success,fail);

  };

  //进入浏览商品页
  $scope.$on('$ionicView.enter',function(e){
    //从详情页跳转回主页处理函数
    if($scope.backFromDetail){
      //从详情页跳转回来,do nothing
      $scope.backFromDetail = false;

    }else {

      if ($scope.state == 1) {
        console.log("Search enter");
        // $scope.getData(-1, 0);
      }
      if ($scope.state == 0) {
        $scope.page = 1;
        $scope.getData();
      }
    }


  });

  //由侧边栏传入,第一次进入主页,搜索数据
  $scope.$on('searchDataCome', function(e,msg) {
    $scope.state = 1;//改变mode
    $scope.mainData = [];//清除页面数据
    $scope.rawData = [];//清除缓存
    $scope.EOD = true;//重置数据是否加载完的标志位
    $scope.searchPage = 1;//重置当前页面数
    $scope.getSearchData(msg);
  });

  $scope.$on('usersearchDataCome', function(e,msg) {
    $scope.state = 1;//改变mode
    $scope.mainData = [];//清除页面数据
    $scope.rawData = [];//清除缓存
    $scope.EOD = true;//重置数据是否加载完的标志位
    $scope.searchPage = 1;//重置当前页面数
    $scope.getSearchData(msg);
  });
  //由详情返回
  $scope.$on('backFromDetail',function(e){
    $scope.backFromDetail = true;
  });
  //加载更多搜索数据
  $scope.$on('moreSearchData',function(e){
    $scope.getSearchData(null);
  });
  //加载更多主页数据
  $scope.$on('$stateChangeSuccess', function() {
    $scope.loadMore();
  });

  $scope.$on('firstTypeSearch', function(){
    $scope.rawData = [];
    $scope.mainData = [];
    $scope.page = 1;
    $scope.getData();
  });

  $scope.$on('specificTypeSearch', function(){
    $scope.rawData = [];
    $scope.mainData = [];
    $scope.page = 1;
    $scope.getData();
  });

  $scope.$on('conditionSearch',function(){
    $scope.rawData = [];
    $scope.mainData = [];
    $scope.page = 1;
    $scope.getData();
  });

  $scope.$on('changeSort',function(){
    $scope.rawData = [];
    $scope.mainData = [];
    $scope.page = 1;
    $scope.getData();
  });

  $scope.doRefresh = function(){

    $scope.page = 1;
    $scope.searchPage = 1;
    if($scope.state == 1){
      $scope.mainData = [];
      $scope.rawData = [];
      $scope.getSearchData(null);
    }else{
      $scope.mainData = [];
      $scope.rawData = [];
      $scope.getData();
    }
    $scope.$broadcast('scroll.refreshComplete');



  }

  $scope.canScroll= false;
  $scope.canScroll2 = false;
  //加载更多响应
  $scope.loadMore = function() {
    if($scope.state == 0){
      //浏览商品

      if(!$scope.canScroll){
        $scope.canScroll = true;
        $scope.$broadcast("scroll.infiniteScrollComplete");
      }else{
        $scope.$broadcast("scroll.infiniteScrollComplete");
        if(!$scope.backFromDetail){//从详情回到主页会自动触发一个loadmore,用这个方法来屏蔽掉
          $scope.getData();
        }

      }

    }
    if($scope.state == 1 ){
      //搜索商品

      if(!$scope.canScroll2){
        $scope.$broadcast("scroll.infiniteScrollComplete");
        $scope.canScroll2 = true;
      }else{
        $scope.$broadcast("scroll.infiniteScrollComplete");
        $scope.$emit("moreSearchData");
      }

    }
    //console.log("refresh in load");
  };
  $scope.moreDataCanBeLoaded = function(){
    console.log("judge"+$scope.EOD);
    return $scope.EOD;
  };
  $scope.details = function(item_id) {
    console.log(item_id);
    $scope.backFromDetail = true;
    if(UserInfoService.getState()){
    $state.go('app.details', {item_id:item_id,origin:'main'} );
    }else{
      $state.go('guestView.details', {item_id:item_id,origin:'main'} );
    }

  };

  //Popup
  // .fromTemplate() method
  var template = '<ion-popover-view><ion-header-bar> <h1 class="title">My Popover Title</h1> </ion-header-bar> <ion-content> Hello! </ion-content></ion-popover-view>';

  $scope.popover = $ionicPopover.fromTemplate(template, {
    scope: $scope
  });
  // .fromTemplateUrl() method
  $ionicPopover.fromTemplateUrl('popover.html', {
    scope: $scope
  }).then(function(popover) {
    $scope.popover = popover;
  });


  $scope.openPopover = function($event) {
    $scope.popover.show($event);
  };
  $scope.closePopover = function() {
    $scope.popover.hide();
  };
  //Cleanup the popover when we're done with it!
  $scope.$on('$destroy', function() {
    $scope.popover.remove();
  });
  // Execute action on hide popover
  $scope.$on('popover.hidden', function() {
    // Execute action
  });
  // Execute action on remove popover
  $scope.$on('popover.removed', function() {
    // Execute action
  });

  // 排序浮动窗口
  $ionicPopover.fromTemplateUrl('order-popover.html', {
    scope: $scope
  }).then(function(popover) {
    $scope.orderPopover = popover;
  });


  $scope.openOrderPopover = function($event) {
    $scope.orderPopover.show($event);
  };
  $scope.closeOrderPopover = function() {
    $scope.orderPopover.hide();
  };


  // 条件选择浮动窗口
  $ionicPopover.fromTemplateUrl('condition-popover.html', {
    scope: $scope
  }).then(function(popover) {
    $scope.ConditionPopover = popover;
  });

  $scope.openConditionPopover = function($event) {
    $scope.ConditionPopover.show($event);
  };
  $scope.closeConditionPopover = function() {
    $scope.ConditionPopover.hide();
  };


  //这里仅仅是获取第一分类内容
  $scope.FastGetFirstType = function($index){
    document.getElementById('tab_type0').style.color='#FFD300';
    document.getElementById('tab_type1').style.color='#333';
    document.getElementById('tab_type2').style.color='#333';
    document.getElementById('tab_type3').style.color='#333';
    document.getElementById('tab_type4').style.color='#333';
    document.getElementById('tab_type5').style.color='#333';
    document.getElementById('tab_type6').style.color='#333';
    document.getElementById('tab_type7').style.color='#333';
    document.getElementById('tab_type8').style.color='#333';
    document.getElementById('tab_type9').style.color='#333';

    switch ($index){
      case(0):{
        $scope.firstType=0;
        document.getElementById('tab_type0').style.color='#FFD300';
      };break;
      case(1):{
        $scope.firstType=1;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type1').style.color='#FFD300';
      };break;
      case(2):{
        $scope.firstType=2;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type2').style.color='#FFD300';
      };break;
      case(3):{
        $scope.firstType=3;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type3').style.color='#FFD300';
      };break;
      case(4):{
        $scope.firstType=4;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type4').style.color='#FFD300';
      };break;
      case(5):{
        $scope.firstType=5;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type5').style.color='#FFD300';
      };break;
      case(6):{
        $scope.firstType=6;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type6').style.color='#FFD300';
      };break;
      case(7):{
        $scope.firstType=7;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type7').style.color='#FFD300';
      };break;
      case(8):{
        $scope.firstType=8;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type8').style.color='#FFD300';
      };break;
      case(9):{
        $scope.firstType=9;
        document.getElementById('tab_type0').style.color='#333';
        document.getElementById('tab_type9').style.color='#FFD300';
      };break;
      default:break;
    }
    //添加搜索代码，仅仅是第一分类的内容

    console.log("firstType:"+$scope.firstType);
    //getData()
    $scope.$broadcast('firstTypeSearch');
    //搜索
  }
  //这里是分类筛选
  $scope.getCategory = function($index){
    switch ($index){
      case (100):{
        $scope.firstType=1;
        $scope.secondType=100;
      };break;
      case (101):{
        $scope.firstType=1;
        $scope.secondType=101;
      };break;
      case (102):{
        $scope.firstType=1;
        $scope.secondType=102;
      };break;
      case (103):{
        $scope.firstType=1;
        $scope.secondType=103;
      };break;
      case (104):{
        $scope.firstType=1;
        $scope.secondType=104;
      };break;
      case (105):{
        $scope.firstType=1;
        $scope.secondType=105;
      };break;
      case (106):{
        $scope.firstType=1;
        $scope.secondType=106;
      };break;
      case (200):{
        $scope.firstType=2;
        $scope.secondType=200;
      };break;
      case (201):{
        $scope.firstType=2;
        $scope.secondType=201;
      };break;
      case (202):{
        $scope.firstType=2;
        $scope.secondType=202;
      };break;
      case (203):{
        $scope.firstType=2;
        $scope.secondType=203;
      };break;
      case (204):{
        $scope.firstType=2;
        $scope.secondType=204;
      };break;
      case (205):{
        $scope.firstType=2;
        $scope.secondType=205;
      };break;
      case (206):{
        $scope.firstType=2;
        $scope.secondType=206;
      };break;
      case (207):{
        $scope.firstType=2;
        $scope.secondType=207;
      };break;
      case (208):{
        $scope.firstType=2;
        $scope.secondType=208;
      };break;
      case (209):{
        $scope.firstType=2;
        $scope.secondType=209;
      };break;
      case (210):{
        $scope.firstType=2;
        $scope.secondType=210;
      };break;
      case (211):{
        $scope.firstType=2;
        $scope.secondType=211;
      };break;
      case (212):{
        $scope.firstType=2;
        $scope.secondType=212;
      };break;
      case (300):{
        $scope.firstType=3;
        $scope.secondType=300;
      };break;
      case (301):{
        $scope.firstType=3;
        $scope.secondType=301;
      };break;
      case (302):{
        $scope.firstType=3;
        $scope.secondType=302;
      };break;
      case (303):{
        $scope.firstType=3;
        $scope.secondType=303;
      };break;
      case (304):{
        $scope.firstType=3;
        $scope.secondType=304;
      };break;
      case (305):{
        $scope.firstType=3;
        $scope.secondType=305;
      };break;
      case (306):{
        $scope.firstType=3;
        $scope.secondType=306;
      };break;
      case (307):{
        $scope.firstType=3;
        $scope.secondType=307;
      };break;
      case (308):{
        $scope.firstType=3;
        $scope.secondType=308;
      };break;
      case (309):{
        $scope.firstType=3;
        $scope.secondType=309;
      };break;
      case (310):{
        $scope.firstType=3;
        $scope.secondType=310;
      };break;
      case (311):{
        $scope.firstType=3;
        $scope.secondType=311;
      };break;
      case (312):{
        $scope.firstType=3;
        $scope.secondType=312;
      };break;
      case (313):{
        $scope.firstType=3;
        $scope.secondType=313;
      };break;
      case (314):{
        $scope.firstType=3;
        $scope.secondType=314;
      };break;
      case (315):{
        $scope.firstType=3;
        $scope.secondType=315;
      };break;
      case (400):{
        $scope.firstType=4;
        $scope.secondType=400;
      };break;
      case (401):{
        $scope.firstType=4;
        $scope.secondType=401;
      };break;
      case (402):{
        $scope.firstType=4;
        $scope.secondType=402;
      };break;
      case (403):{
        $scope.firstType=4;
        $scope.secondType=403;
      };break;
      case (404):{
        $scope.firstType=4;
        $scope.secondType=404;
      };break;
      case (500):{
        $scope.firstType=5;
        $scope.secondType=500;
      };break;
      case (501):{
        $scope.firstType=5;
        $scope.secondType=501;
      };break;
      case (502):{
        $scope.firstType=5;
        $scope.secondType=502;
      };break;
      case (503):{
        $scope.firstType=5;
        $scope.secondType=503;
      };break;
      case (504):{
        $scope.firstType=5;
        $scope.secondType=504;
      };break;
      case (505):{
        $scope.firstType=5;
        $scope.secondType=505;
      };break;
      case (506):{
        $scope.firstType=5;
        $scope.secondType=506;
      };break;
      case (507):{
        $scope.firstType=5;
        $scope.secondType=507;
      };break;
      case (508):{
        $scope.firstType=5;
        $scope.secondType=508;
      };break;
      case (509):{
        $scope.firstType=5;
        $scope.secondType=509;
      };break;
      case (510):{
        $scope.firstType=5;
        $scope.secondType=510;
      };break;
      case (511):{
        $scope.firstType=5;
        $scope.secondType=511;
      };break;
      case (512):{
        $scope.firstType=5;
        $scope.secondType=512;
      };break;
      case (513):{
        $scope.firstType=5;
        $scope.secondType=513;
      };break;
      case (514):{
        $scope.firstType=5;
        $scope.secondType=514;
      };break;
      case (515):{
        $scope.firstType=5;
        $scope.secondType=515;
      };break;
      case (516):{
        $scope.firstType=5;
        $scope.secondType=516;
      };break;
      case (517):{
        $scope.firstType=5;
        $scope.secondType=517;
      };break;
      case (600):{
        $scope.firstType=6;
        $scope.secondType=600;
      };break;
      case (601):{
        $scope.firstType=6;
        $scope.secondType=601;
      };break;
      case (602):{
        $scope.firstType=6;
        $scope.secondType=602;
      };break;
      case (603):{
        $scope.firstType=6;
        $scope.secondType=603;
      };break;
      case (604):{
        $scope.firstType=6;
        $scope.secondType=604;
      };break;
      case (605):{
        $scope.firstType=6;
        $scope.secondType=605;
      };break;
      case (606):{
        $scope.firstType=6;
        $scope.secondType=606;
      };break;
      case (607):{
        $scope.firstType=6;
        $scope.secondType=607;
      };break;
      case (608):{
        $scope.firstType=6;
        $scope.secondType=608;
      };break;
      case (609):{
        $scope.firstType=6;
        $scope.secondType=609;
      };break;
      case (610):{
        $scope.firstType=6;
        $scope.secondType=610;
      };break;
      case (611):{
        $scope.firstType=6;
        $scope.secondType=611;
      };break;
      case (612):{
        $scope.firstType=6;
        $scope.secondType=612;
      };break;
      case (700):{
        $scope.firstType=7;
        $scope.secondType=700;
      };break;
      case (701):{
        $scope.firstType=7;
        $scope.secondType=701;
      };break;
      case (702):{
        $scope.firstType=7;
        $scope.secondType=702;
      };break;
      case (703):{
        $scope.firstType=7;
        $scope.secondType=703;
      };break;
      case (704):{
        $scope.firstType=7;
        $scope.secondType=704;
      };break;
      case (705):{
        $scope.firstType=7;
        $scope.secondType=705;
      };break;
      case (706):{
        $scope.firstType=7;
        $scope.secondType=706;
      };break;
      case (707):{
        $scope.firstType=7;
        $scope.secondType=707;
      };break;
      case (708):{
        $scope.firstType=7;
        $scope.secondType=708;
      };break;
      case (800):{
        $scope.firstType=8;
        $scope.secondType=800;
      };break;
      case (801):{
        $scope.firstType=8;
        $scope.secondType=801;
      };break;
      case (802):{
        $scope.firstType=8;
        $scope.secondType=802;
      };break;
      case (803):{
        $scope.firstType=8;
        $scope.secondType=803;
      };break;
      case (804):{
        $scope.firstType=8;
        $scope.secondType=804;
      };break;
      case (805):{
        $scope.firstType=8;
        $scope.secondType=805;
      };break;
      case (806):{
        $scope.firstType=8;
        $scope.secondType=806;
      };break;
      case (807):{
        $scope.firstType=8;
        $scope.secondType=807;
      };break;
      case (808):{
        $scope.firstType=8;
        $scope.secondType=808;
      };break;
      case (9):{
        $scope.firstType=9;
        $scope.secondType=0;
      };break;
      default:break;
    }

    //添加类别搜索代码
    console.log($scope.firstType);
    console.log($scope.secondType);
    $scope.$emit('specificTypeSearch');
    $scope.closePopover();
  }
  //修改价格域
  $scope.getPrice = function($index){
    document.getElementById('price0').style.color = '#FFD300';
    document.getElementById('price1').style.color = '#333';
    document.getElementById('price2').style.color = '#333';
    document.getElementById('price3').style.color = '#333';
    document.getElementById('price4').style.color = '#333';
    document.getElementById('price5').style.color = '#333';
    document.getElementById('price6').style.color = '#333';
    document.getElementById('price7').style.color = '#333';
    document.getElementById('price8').style.color = '#333';
    document.getElementById('price9').style.color = '#333';
    document.getElementById('price10').style.color = '#333';
    switch ($index){
      case 0:{
        $scope.price=0;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price0').style.color = '#FFD300';

      };break;
      case 1:{
        $scope.price=1;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price1').style.color = '#FFD300';
      };break;
      case 2:{
        $scope.price=2;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price2').style.color = '#FFD300';
      };break;
      case 3:{
        $scope.price=3;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price3').style.color = '#FFD300';
      };break;
      case 4:{
        $scope.price=4;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price4').style.color = '#FFD300';
      };break;
      case 5:{
        $scope.price=5;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price5').style.color = '#FFD300';
      };break;
      case 6:{
        $scope.price=6;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price6').style.color = '#FFD300';
      };break;
      case 7:{
        $scope.price=7;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price7').style.color = '#FFD300';
      };break;
      case 8:{
        $scope.price=8;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price8').style.color = '#FFD300';
      };break;
      case 9:{
        $scope.price=9;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price9').style.color = '#FFD300';
      };break;
      case 10:{
        $scope.price=10;
        document.getElementById('price0').style.color = '#333';
        document.getElementById('price10').style.color = '#FFD300';
      };break;
      default:break;
    }
    $scope.$emit("changePrice");
  }
  //修改新旧程度
  $scope.getQuality = function($index){
    document.getElementById('quality0').style.color = '#FFD300';
    document.getElementById('quality1').style.color = '#333';
    document.getElementById('quality2').style.color = '#333';
    document.getElementById('quality3').style.color = '#333';
    document.getElementById('quality4').style.color = '#333';
    document.getElementById('quality5').style.color = '#333';
    document.getElementById('quality6').style.color = '#333';
    document.getElementById('quality7').style.color = '#333';
    switch ($index){
      case 0:{
        $scope.quality=0;
        document.getElementById('quality0').style.color = '#FFD300';
      };break;
      case 1:{
        $scope.quality=1;
        document.getElementById('quality0').style.color = '#333';
        document.getElementById('quality1').style.color = '#FFD300';
      };break;
      case 2:{
        $scope.quality=2;
        document.getElementById('quality0').style.color = '#333';
        document.getElementById('quality2').style.color = '#FFD300';
      };break;
      case 3:{
        $scope.quality=3;
        document.getElementById('quality0').style.color = '#333';
        document.getElementById('quality3').style.color = '#FFD300';
      };break;
      case 4:{
        $scope.quality=4;
        document.getElementById('quality0').style.color = '#333';
        document.getElementById('quality4').style.color = '#FFD300';
      };break;
      case 5:{
        $scope.quality=5;
        document.getElementById('quality0').style.color = '#333';
        document.getElementById('quality5').style.color = '#FFD300';
      };break;
      case 6:{
        $scope.quality=6;
        document.getElementById('quality0').style.color = '#333';
        document.getElementById('quality6').style.color = '#FFD300';
      };break;
      case 7:{
        $scope.quality=7;
        document.getElementById('quality0').style.color = '#333';
        document.getElementById('quality7').style.color = '#FFD300';
      };break;
      default:break;
    }
    $scope.$emit("changeQuality");
  }
  //修改校区
  $scope.getCampus = function($index){
    document.getElementById('campus0').style.color = '#FFD300';
    document.getElementById('campus1').style.color = '#333';
    document.getElementById('campus2').style.color = '#333';
    switch ($index){
      case 0:
      {
        $scope.campus = 0;
        document.getElementById('campus0').style.color = '#333';
        document.getElementById('campus0').style.color = '#FFD300';
      };break;
      case 1:{
        $scope.campus=1;
        document.getElementById('campus0').style.color = '#333';
        document.getElementById('campus1').style.color = '#FFD300';
      };break;
      case 2:{
        $scope.campus=2;
        document.getElementById('campus0').style.color = '#333';
        document.getElementById('campus2').style.color = '#FFD300';
      };break;
      default:break;
    }

  }

  $scope.conditionSearch = function(){
    console.log($scope.price);
    console.log($scope.quality);
    console.log($scope.campus);

    $scope.closeConditionPopover();
    $scope.mainData = [];
    $scope.rawData = [];
    $scope.page = 1;
    $scope.$emit("conditionSearch");
    //添加具体代码
  }

  $scope.sortBy = function ($index) {
    switch ($index) {
      case 0:{
        $scope.sort=0;
      };break;
      case 1:{
        $scope.sort=1;
      };break;
      default:break;
    }
    //添加排序代码
    console.log($scope.price);
    console.log($scope.quality);
    console.log($scope.campus);
    console.log($scope.sort);
    $scope.$emit("changeSort");
    $scope.closeOrderPopover();
  }

  //showSecondoryType
  $scope.showSecondoryType = function($index){
    document.getElementById('sechand_bycles').style.display='none';
    document.getElementById('sechand_phone').style.display='none';
    document.getElementById('sechand_pc').style.display='none';
    document.getElementById('sechand_home_applications').style.display='none';
    document.getElementById('sechand_clothes').style.display='none';
    document.getElementById('sechand_book_and_cd').style.display='none';
    document.getElementById('sechand_gadgets').style.display='none';
    document.getElementById('sechand_sports').style.display='none';
    document.getElementById('ion_item_1').style.backgroundColor='#fff';
    document.getElementById('ion_item_2').style.backgroundColor='#fff';
    document.getElementById('ion_item_3').style.backgroundColor='#fff';
    document.getElementById('ion_item_4').style.backgroundColor='#fff';
    document.getElementById('ion_item_5').style.backgroundColor='#fff';
    document.getElementById('ion_item_6').style.backgroundColor='#fff';
    document.getElementById('ion_item_7').style.backgroundColor='#fff';
    document.getElementById('ion_item_8').style.backgroundColor='#fff';
    document.getElementById('ion_item_9').style.backgroundColor='#fff';
    switch ($index){
      case 1:{
        document.getElementById('sechand_bycles').style.display='block';
        document.getElementById('ion_item_1').style.backgroundColor='#eee';
      };break;
      case 2:{
        document.getElementById('sechand_phone').style.display='block';
        document.getElementById('ion_item_2').style.backgroundColor='#eee';
      };break;
      case 3:{
        document.getElementById('sechand_pc').style.display='block';
        document.getElementById('ion_item_3').style.backgroundColor='#eee';
      };break;
      case 4:{
        document.getElementById('sechand_home_applications').style.display='block';
        document.getElementById('ion_item_4').style.backgroundColor='#eee';
      };break;
      case 5:{
        document.getElementById('sechand_clothes').style.display='block';
        document.getElementById('ion_item_5').style.backgroundColor='#eee';
      };break;
      case 6:{
        document.getElementById('sechand_book_and_cd').style.display='block';
        document.getElementById('ion_item_6').style.backgroundColor='#eee';
      };break;
      case 7:{
        document.getElementById('sechand_gadgets').style.display='block';
        document.getElementById('ion_item_7').style.backgroundColor='#eee';
      };break;
      case 8:{
        document.getElementById('sechand_sports').style.display='block';
        document.getElementById('ion_item_8').style.backgroundColor='#eee';
      };break;
      case 9:{
        document.getElementById('ion_item_9').style.backgroundColor='#eee';
      };break;
      default:break;
    }
  };
});

//引导页
app.controller('TutorialCtrl', function($scope, $state, $ionicViewService, UserInfoService) {
               //window.localStorage['didTutorial'] = null;// For Test

   var startApp = function() {
   //$ionicViewService.clearHistory();
   // 默认进入首页
   if(UserInfoService.getState()){
   $state.go('app.main');
   }else{
   $state.go('guestView.main');
   }
   };

   if(window.localStorage['didTutorial'] == "true") {
   console.log('Skip intro');
   startApp();
   }else {
    window.localStorage['didTutorial'] = true;
    }
  // "立即体验"按钮Event
  $scope.gotoMain = function() {
    startApp();
  }

  $scope.slideHasChanged = function(index) {

  };
});

//注册
app.controller('RegisterCtrl', function($scope, $ionicModal, $state,$ionicPopup,$ionicLoading, $timeout, $http, ApiEndpoint, TokenService, UserInfoService) {

  $scope.registerData = {};
  $scope.auth_code = {};
  $scope.auth_code_input={};
  $scope.once = true;

  $ionicModal.fromTemplateUrl('views/register.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  $scope.closeRegister = function() {
    $ionicLoading.hide();
    $scope.modal.hide();
    $state.go('app.main');
    //弹出登录注册结果
  };

  $scope.registerUser = function() {
    $scope.modal.show();
  };


  $scope.emailAction = function() {
    var success= function(response){
      console.log(response);
      res = eval(response.data);
      var flag= res.flag;
      if(flag == "success"){
        $scope.auth_code = res.auth_code;
        $ionicLoading.show({
          template:"验证码已发送"
        });
        $timeout(function(){
          $ionicLoading.hide()
        },1000);
        console.log($scope.auth_code);
      }else{
        //var ErrorMessage = res.msg;
        //$cordovaToast.showLongBottom("对不起，"+ErrorMessage).then(function(success) {
        //  // success
        //}, function (error) {
        //  // error
        //});
      }
    };
    var error = function(){

    };
    console.log($scope.registerData.email);
    $http({

      url: ApiEndpoint.url+'/emailAction',

      method:"POST",

      headers: {

        'Content-Type': 'application/json'
      },
      data: {"email":$scope.registerData.email}
    }).then(success,error);
  };

  $scope.doRegister = function() {
    if($scope.auth_code != $scope.auth_code_input.text){

      $ionicLoading.show({
        template:'验证码错误哟',
        duration:1000,
      });
      //$cordovaToast.showLongBottom("验证码错误").then(function(success) {
      //  // success
      //}, function (error) {
      //  // error
      //});
      return
    }
    console.log($scope.registerData);
    var res = {};
    $ionicLoading.show({
      template:'注册中,请稍候...'
    });
    var success = function(response){
      $ionicLoading.hide();
      console.log(response);
      res = eval(response.data);
      var flag= res.flag;
      if(flag == "success"){
        TokenService.set(res.token);
        UserInfoService.setInfo(res.user_info);
        $timeout(function() {
          $scope.closeRegister();
          UserInfoService.login();
        }, 1000);
      }
      else if(res.msg == "bad request"){
        //var ErrorMessage = res.msg;
        //$cordovaToast.showLongBottom("Sorry"+ErrorMessage).then(function(success) {
        //  // success
        //}, function (error) {
        //  // error
        //});
        $ionicLoading.hide();


      }else {
        $ionicLoading.show({
          template:res.msg,
          duration:1000,
        });
      }

    };
    var error = function(e){
      ionicLoading.hide();
      $ionicLoading.show({
        template:'网络未连接哟~',
        duration:1000,
      });
      //$cordovaToast.showLongBottom("网络未连接").then(function(success) {
      //  // success
      //}, function (error) {
      //  // error
      //});
    };
    $http({
      url: ApiEndpoint.url+'/registerAction',
      method:"POST",
      headers: {
        'Content-Type': 'application/json'
      },
      data: $scope.registerData
    }).then(success,error);
  };

  $scope.ajaxGetCode = function() {

      console.log($scope.registerData.email);

      if ($scope.registerData.email != null) {
        $scope.emailAction();
        $scope.countdown = 60;
        document.getElementById('get_code').innerHTML = $scope.countdown;
        document.getElementById('get_code').disabled = true;
        var myTime = setInterval(function () {
          $scope.countdown--;
          $scope.$digest(); // 通知视图模型的变化
        }, 1000);

        // 倒计时60-0秒，但算上0的话就是11s
        $timeout(function () {
          // Do SomeThing
          document.getElementById('get_code').innerHTML = '获取验证码';
          document.getElementById('get_code').disabled = false;
          clearInterval(myTime);
          //$scope.countdown.$destroy();
        }, 61000);
      } else {
        alert('请输入有效邮箱');
      }
  $scope.ajaxGetCode = function(){

    //$scope.emailAction();
    if($scope.registerData.email!=null){
      $scope.countdown = 60;
      document.getElementById('get_code').innerHTML = '获取验证码'+$scope.countdown;
      document.getElementById('get_code').disabled=true;
      $scope.lock = false;

      var myTime = setInterval(function() {
        $scope.countdown--;
        $scope.$digest(); // 通知视图模型的变化

      }, 1000);

      // 倒计时60-0秒，但算上0的话就是11s
      $timeout(function() {
        // Do SomeThing
        document.getElementById('get_code').innerHTML = '获取验证码';
        document.getElementById('get_code').disabled=false;
        clearInterval(myTime);
      }, 61000);
    }else{
      alert('请输入有效邮箱');
    }
  }

  }

});

//登录
app.controller('LoginCtrl', function ($scope,$ionicModal, $timeout, $http, $state,$ionicLoading, ApiEndpoint, TokenService, UserInfoService) {

  //Form data for the login modal
  $scope.loginData = {};
  $scope.isRemember = false;
  var res = {}
  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('views/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
    $scope.modal.remove();
    $state.go('app.main');
  };

  $scope.$watch('isRemember', function(newValue, oldValue){
    if(newValue == oldValue) {return;}
    console.log("11");
  })
  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };

//y.d.li@sjtu.edu.cn
  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);

    var httppost = function(loginData) {
      $ionicLoading.show({
        template: '登录中,请稍后...'
      });

      var success = function(response){
        var res = eval(response.data);
        if(res.flag == "success"){
        console.log(res);
        TokenService.set(res.token.toString());
        console.log(TokenService.get());
        res.user_info.image = (res.user_info.image)?ApiEndpoint.imgUrl + res.user_info.image:'img/user.png';
        console.log(res.user_info);
        UserInfoService.setInfo(res.user_info);
        UserInfoService.login();
        $scope.closeLogin();

        $ionicLoading.hide();
        }else{
        $ionicLoading.hide();
          var ErrorMsg = res.msg;
          $ionicLoading.show({
            template:ErrorMsg,
            duration:1000,
          });
          console.log(ErrorMsg);
        }
      }
      var error = function(response){
        $ionicLoading.hide();
        $ionicLoading.show({
          template:'您没联网哦,亲~',
          duration:1000,
        });
      }

      $http({
        url: ApiEndpoint.url + '/loginAction',

        method: 'POST',

        headers: {
          'Content-Type': 'application/json'
        },
        data: loginData
      }).then(success,error);

      //老版本的网络连接
      //$http({
      //  url: ApiEndpoint.url + '/loginAction',
      //
      //  method: 'POST',
      //
      //  headers: {
      //    'Content-Type': 'application/json'
      //  },
      //  data: loginData
      //}).
      //success(function (response) {
      //  var res = eval(response);
      //  console.log(res);
      //  TokenService.set(res.token.toString());
      //  console.log(TokenService.get());
      //  localDataInit(res);
      //
      //}).
      //error(function () {
      //  $ionicLoading.hide();
      //  $cordovaToast.showLongBottom("网络连接出错").then(function(success) {
      //    // success
      //  }, function (error) {
      //    // error
      //  });
      //});

    };


    httppost($scope.loginData);

  };

  $scope.forgetPassword = function(){
    var success = function(response){
          var  res = eval(response.data);
      if(res.flag == "success"){
        $ionicLoading.show({
          template:'密码已发到注册邮箱,请查收~',
          duration:1000
        });
      }
    };
    var error = function(e){
      $ionicLoading.show({
        template:'网络未连接哦!',
        duration:1000
      });
    }
    if($scope.loginData.email != null){
      $http({
        url: ApiEndpoint.url+"/findPswAction",
        method: 'GET',
        params: {email:$scope.loginData.email},
      }).then(success,error);
    }else{
      $ionicLoading.show({
        template:'请输入邮箱',
        duration:1000,
      });
    }


  };
});

//我的收藏
app.controller('StarsCtrl', function($scope){
  //我的收藏
  $scope.starsData = {}

});

//上传商品
app.controller('UploadCtrl', function ($scope, $ionicHistory,$cordovaImagePicker, $ionicLoading,$ionicModal, $timeout,$state ,$http, UploadData, TokenService, ApiEndpoint) {

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('views/uploadGoods.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  $scope.imgSrc = new Array(6);
  var imgRemoteURL = new Array()

  $ionicModal.fromTemplateUrl('views/uploadGoods.html', {
    scope: $scope
  }).then(function (modal) {
    $scope.modal = modal;
  });
  // Triggered in t modal to close it
  $scope.close = function () {
    $scope.modal.hide();
    $scope.modal.remove();
    $ionicHistory.nextViewOptions({
      disableBack: true
    });
    $state.go('app.main');
  };
  // Open the login modal
  $scope.show = function () {
    $scope.modal.show();
  };


  //send image
  var sendImage = function(imgSrc){
    var errorCount = 0;
    function win(r) {
      console.log("Code = " + r.responseCode);
      console.log("Response = " + r.response);
      console.log("Sent = " + r.bytesSent);
      //response.push(r)

      var res = {};
      res = r.response;
      var obj = JSON.parse(res);
      console.log(obj.image_path);
      imgRemoteURL[imgRemoteURL.length] = obj.image_path;
      console.log();
    }
    function fail(error) {
      $ionicLoading.show({
        template:'很遗憾,发生了一点错误~',
        duration:1000,
      });
      //$cordovaToast.showLongBottom("发生一个错误,错误码:"+error.code).then(function(success) {
      //  // success
      //}, function (error) {
      //  // error
      //});
      console.log("upload error source " + error.source);
      console.log("upload error target " + error.target);
      console.log(error.content);
    }


    for(var i = 0 ; i < imgSrc.length;i++){
      if(imgSrc[i] != ""){
        var imgUrl = imgSrc[i];
        UploadData.upload(imgUrl,win,fail);
      }
    }

  }

//image picker
  $scope.pickImage = function () {
    console.log("haha");
    var options = {
      maximumImagesCount: 6,
      width: 800,
      height: 800,
      quality: 80
    };
    $cordovaImagePicker.getPictures(options)
      .then(function (results) {
        console.log(results);
        $scope.imgSrc = results;
        //上传图片
        sendImage($scope.imgSrc)

      }, function (error) {
        // error getting photos
      });


  }

  // post
  $scope.postFormData = {};

  //发送数据
  var sendData = function(uploadData){
    var success = function(response){
      var res = eval(response.data);
      if (res.flag == "success") {
        $scope.$emit("uploadSuccess");
        $timeout(function(){
          $ionicLoading.hide();
          $scope.close();
        },1000)
      } else {
        $ionicLoading.hide();
       $ionicLoading.show({
         template:"不好意思,发送失败咯,重新登录试试~",
         duration:1000

       })
        console.log("options:"+response.status);
      }
    };
    var error = function(e){
      $ionicLoading.hide();
      $ionicLoading.show({
        template:'网络出故障啦',
        duration:1000,
      });
      //alert("网络出故障啦");
    };
    $http({
      url: ApiEndpoint.url + '/postItemAction',

      method: 'POST',

      headers: {
        'Content-Type': 'application/json;charset=utf-8'
      },
      data: uploadData
      //method  : 'POST',
      //url     : ApiEndpoint+'/postItemAction',
      //data    : $.param(uploadData),  // pass in data as strings
      //headers : { 'Content-Type': 'application/json;charset=utf-8' }  // set the headers so angular passing info as form data (not request payload)
    }).then(success,error);
  };

  $scope.getType = function(strTwoType) {
    $scope.first_type = 0;
    $scope.second_type = 0;
    var strArr = strTwoType.split('-');

    switch (strArr[0]){
      case('二手车'):{$scope.first_type=1;$scope.postFormData.first_type=$scope.first_type};break;
      case('二手手机'):{$scope.first_type=2;$scope.postFormData.first_type=$scope.first_type};break;
      case('PC/PAD'):{$scope.first_type=3;$scope.postFormData.first_type=$scope.first_type};break;
      case('二手家电'):{$scope.first_type=4;$scope.postFormData.first_type=$scope.first_type};break;
      case('服装包箱'):{$scope.first_type=5;$scope.postFormData.first_type=$scope.first_type};break;
      case('图书音像'):{$scope.first_type=6;$scope.postFormData.first_type=$scope.first_type};break;
      case('生活用品'):{$scope.first_type=7;$scope.postFormData.first_type=$scope.first_type};break;
      case('体育器材'):{$scope.first_type=8;$scope.postFormData.first_type=$scope.first_type};break;
      case('未分类'):{$scope.first_type=9;$scope.postFormData.first_type=$scope.first_type};break;
      default:break;
    }

    switch (strArr[1]){
      case ('普通自行车'):{$scope.second_type=100;$scope.postFormData.second_type=$scope.second_type};break;
      case ('山地自行车'):{$scope.second_type=101;$scope.postFormData.second_type=$scope.second_type};break;
      case ('折叠自行车'):{$scope.second_type=102;$scope.postFormData.second_type=$scope.second_type};break;
      case ('迷你自行车'):{$scope.second_type=103;$scope.postFormData.second_type=$scope.second_type};break;
      case ('公路自行车'):{$scope.second_type=104;$scope.postFormData.second_type=$scope.second_type};break;
      case ('电动车'):{$scope.second_type=105;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他车'):{$scope.second_type=106;$scope.postFormData.second_type=$scope.second_type};break;
      case ('苹果'):{$scope.second_type=200;$scope.postFormData.second_type=$scope.second_type};break;
      case ('三星'):{$scope.second_type=201;$scope.postFormData.second_type=$scope.second_type};break;
      case ('小米'):{$scope.second_type=202;$scope.postFormData.second_type=$scope.second_type};break;
      case ('华为'):{$scope.second_type=203;$scope.postFormData.second_type=$scope.second_type};break;
      case ('HTC'):{$scope.second_type=204;$scope.postFormData.second_type=$scope.second_type};break;
      case ('诺基亚'):{$scope.second_type=205;$scope.postFormData.second_type=$scope.second_type};break;
      case ('魅族'):{$scope.second_type=206;$scope.postFormData.second_type=$scope.second_type};break;
      case ('索尼'):{$scope.second_type=207;$scope.postFormData.second_type=$scope.second_type};break;
      case ('中兴'):{$scope.second_type=208;$scope.postFormData.second_type=$scope.second_type};break;
      case ('LG'):{$scope.second_type=209;$scope.postFormData.second_type=$scope.second_type};break;
      case ('联想'):{$scope.second_type=210;$scope.postFormData.second_type=$scope.second_type};break;
      case ('酷派'):{$scope.second_type=211;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他手机'):{$scope.second_type=212;$scope.postFormData.second_type=$scope.second_type};break;
      case ('台式机'):{$scope.second_type=300;$scope.postFormData.second_type=$scope.second_type};break;
      case ('ThinkPad/IBM'):{$scope.second_type=301;$scope.postFormData.second_type=$scope.second_type};break;
      case ('苹果(Mac)'):{$scope.second_type=302;$scope.postFormData.second_type=$scope.second_type};break;
      case ('联想(Lenovo)'):{$scope.second_type=303;$scope.postFormData.second_type=$scope.second_type};break;
      case ('戴尔(DELL)'):{$scope.second_type=304;$scope.postFormData.second_type=$scope.second_type};break;
      case ('华硕(ASUS)'):{$scope.second_type=305;$scope.postFormData.second_type=$scope.second_type};break;
      case ('惠普(HP)'):{$scope.second_type=306;$scope.postFormData.second_type=$scope.second_type};break;
      case ('索尼(Sony)'):{$scope.second_type=307;$scope.postFormData.second_type=$scope.second_type};break;
      case ('三星(Samsung)'):{$scope.second_type=308;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他电脑'):{$scope.second_type=309;$scope.postFormData.second_type=$scope.second_type};break;
      case ('iPad'):{$scope.second_type=310;$scope.postFormData.second_type=$scope.second_type};break;
      case ('Surface'):{$scope.second_type=311;$scope.postFormData.second_type=$scope.second_type};break;
      case ('三星平板'):{$scope.second_type=312;$scope.postFormData.second_type=$scope.second_type};break;
      case ('小米平板'):{$scope.second_type=313;$scope.postFormData.second_type=$scope.second_type};break;
      case ('联想平板'):{$scope.second_type=314;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他平板'):{$scope.second_type=315;$scope.postFormData.second_type=$scope.second_type};break;
      case ('洗衣机'):{$scope.second_type=400;$scope.postFormData.second_type=$scope.second_type};break;
      case ('厨房电器'):{$scope.second_type=401;$scope.postFormData.second_type=$scope.second_type};break;
      case ('冰箱/冰柜'):{$scope.second_type=402;$scope.postFormData.second_type=$scope.second_type};break;
      case ('数码相机'):{$scope.second_type=403;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他家电'):{$scope.second_type=404;$scope.postFormData.second_type=$scope.second_type};break;
      case ('T恤'):{$scope.second_type=500;$scope.postFormData.second_type=$scope.second_type};break;
      case ('衬衫'):{$scope.second_type=501;$scope.postFormData.second_type=$scope.second_type};break;
      case ('外套'):{$scope.second_type=502;$scope.postFormData.second_type=$scope.second_type};break;
      case ('裤子'):{$scope.second_type=503;$scope.postFormData.second_type=$scope.second_type};break;
      case ('西装'):{$scope.second_type=504;$scope.postFormData.second_type=$scope.second_type};break;
      case ('裙子'):{$scope.second_type=505;$scope.postFormData.second_type=$scope.second_type};break;
      case ('休闲鞋'):{$scope.second_type=506;$scope.postFormData.second_type=$scope.second_type};break;
      case ('运动鞋'):{$scope.second_type=507;$scope.postFormData.second_type=$scope.second_type};break;
      case ('帆布鞋'):{$scope.second_type=508;$scope.postFormData.second_type=$scope.second_type};break;
      case ('高跟鞋'):{$scope.second_type=509;$scope.postFormData.second_type=$scope.second_type};break;
      case ('皮鞋'):{$scope.second_type=510;$scope.postFormData.second_type=$scope.second_type};break;
      case ('单肩包'):{$scope.second_type=511;$scope.postFormData.second_type=$scope.second_type};break;
      case ('双肩包'):{$scope.second_type=512;$scope.postFormData.second_type=$scope.second_type};break;
      case ('书包'):{$scope.second_type=513;$scope.postFormData.second_type=$scope.second_type};break;
      case ('钱包'):{$scope.second_type=514;$scope.postFormData.second_type=$scope.second_type};break;
      case ('电脑包'):{$scope.second_type=515;$scope.postFormData.second_type=$scope.second_type};break;
      case ('箱子'):{$scope.second_type=516;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他服饰'):{$scope.second_type=517;$scope.postFormData.second_type=$scope.second_type};break;
      case ('专业书籍'):{$scope.second_type=600;$scope.postFormData.second_type=$scope.second_type};break;
      case ('考试(GRE/托福/雅思)'):{$scope.second_type=601;$scope.postFormData.second_type=$scope.second_type};break;
      case ('小说/文学'):{$scope.second_type=602;$scope.postFormData.second_type=$scope.second_type};break;
      case ('工具书/辅导书'):{$scope.second_type=603;$scope.postFormData.second_type=$scope.second_type};break;
      case ('生活类书籍'):{$scope.second_type=604;$scope.postFormData.second_type=$scope.second_type};break;
      case ('学生文具'):{$scope.second_type=605;$scope.postFormData.second_type=$scope.second_type};break;
      case ('学习机'):{$scope.second_type=606;$scope.postFormData.second_type=$scope.second_type};break;
      case ('乐器'):{$scope.second_type=607;$scope.postFormData.second_type=$scope.second_type};break;
      case ('音响'):{$scope.second_type=608;$scope.postFormData.second_type=$scope.second_type};break;
      case ('耳机'):{$scope.second_type=609;$scope.postFormData.second_type=$scope.second_type};break;
      case ('U盘/硬盘'):{$scope.second_type=610;$scope.postFormData.second_type=$scope.second_type};break;
      case ('MP3/iPod/iWatch'):{$scope.second_type=611;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他图书'):{$scope.second_type=612;$scope.postFormData.second_type=$scope.second_type};break;
      case ('电灯'):{$scope.second_type=700;$scope.postFormData.second_type=$scope.second_type};break;
      case ('雨伞'):{$scope.second_type=701;$scope.postFormData.second_type=$scope.second_type};break;
      case ('梳子/镜子'):{$scope.second_type=702;$scope.postFormData.second_type=$scope.second_type};break;
      case ('家具'):{$scope.second_type=703;$scope.postFormData.second_type=$scope.second_type};break;
      case ('桌椅板凳'):{$scope.second_type=704;$scope.postFormData.second_type=$scope.second_type};break;
      case ('垫褥床铺'):{$scope.second_type=705;$scope.postFormData.second_type=$scope.second_type};break;
      case ('茶杯/水杯'):{$scope.second_type=706;$scope.postFormData.second_type=$scope.second_type};break;
      case ('布偶/娃娃'):{$scope.second_type=707;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他生活用品'):{$scope.second_type=708;$scope.postFormData.second_type=$scope.second_type};break;
      case ('健身器材'):{$scope.second_type=800;$scope.postFormData.second_type=$scope.second_type};break;
      case ('网球'):{$scope.second_type=801;$scope.postFormData.second_type=$scope.second_type};break;
      case ('足球'):{$scope.second_type=802;$scope.postFormData.second_type=$scope.second_type};break;
      case ('篮球'):{$scope.second_type=803;$scope.postFormData.second_type=$scope.second_type};break;
      case ('乒乓球'):{$scope.second_type=804;$scope.postFormData.second_type=$scope.second_type};break;
      case ('羽毛球'):{$scope.second_type=805;$scope.postFormData.second_type=$scope.second_type};break;
      case ('户外用品'):{$scope.second_type=806;$scope.postFormData.second_type=$scope.second_type};break;
      case ('游泳用品'):{$scope.second_type=807;$scope.postFormData.second_type=$scope.second_type};break;
      case ('其他体育用品'):{$scope.second_type=808;$scope.postFormData.second_type=$scope.second_type};break;

      default:{$scope.second_type=0;$scope.postFormData.second_type=$scope.second_type};break;
    }
  };

  $scope.processPostForm = function () {

    $ionicLoading.show({
      template: '上传数据中,请稍等'
    });
    $scope.postFormData["imgSrc"] = imgRemoteURL;
    $scope.postFormData["token"] = TokenService.get();
    $scope.postFormData["imgSize"]=imgRemoteURL.length;
    console.log($scope.postFormData);

    sendData($scope.postFormData);


  };

});

//平台相关
app.controller('PlatformCtrl', function($scope){
  ionic.Platform.ready(function(){
    // will execute when device is ready, or immediately if the device is already ready.
  });

  var deviceInformation = ionic.Platform.device();

  var isWebView = ionic.Platform.isWebView();
  var isIPad = ionic.Platform.isIPad();
  var isIOS = ionic.Platform.isIOS();
  var isAndroid = ionic.Platform.isAndroid();
  var isWindowsPhone = ionic.Platform.isWindowsPhone();

  var currentPlatform = ionic.Platform.platform();
  var currentPlatformVersion = ionic.Platform.version();

  //var buttonCodeHeight = 5%;

  ionic.Platform.exitApp(); // stops the app
});

//修改密码
app.controller('ModPwdCtrl', function($scope,$http,$state,$ionicLoading,$timeout,TokenService,ApiEndpoint){

  $scope.data = {};

  $scope.doModify = function(){
    //console.log("新密码:"+$scope.data.newPwd);
    //console.log("新密码:"+$scope.data.oldPwd);
    //console.log("新密码:"+$scope.data.againPwd);
      var success = function(response){
        var result = eval(response.data);
        console.log(result);
        if (result.flag == "success") {
          $timeout(function(){
            $ionicLoading.show({
              template:"修改成功",
              duration:1000,
            });
            $state.go("app.settings")
          },1000)
        } else if(result.msg == "bad request") {

          console.log(response.status);
        }else{
          $ionicLoading.show({
            template:"修改失败",
            duration:1000,
          });
        }
      };
      var error = function(){
        $ionicLoading.show({
          template:'网络故障哟',
          duration:1000,
        });
        //$cordovaToast.showLongBottom("网络故障").then(function(success) {
        //  // success
        //}, function (error) {
        //  // error
        //});
      };
      $http({
          url: ApiEndpoint.url + '/changePswAction',

          method: "POST",

          headers: {
            'Content-Type': 'application/json;charset=utf-8'
          },
          data: {token:TokenService.get(),password:$scope.data.oldPwd,new_password:$scope.data.newPwd}
          //method  : 'POST',
          //url     : ApiEndpoint+'/postItemAction',
          //data    : $.param(uploadData),  // pass in data as strings
          //headers : { 'Content-Type': 'application/json;charset=utf-8' }  // set the headers so angular passing info as form data (not request payload)
        }).then(success,error);


  };



});

//设置信息
app.controller('SettingsCtrl', function($scope, $state,$ionicActionSheet, $ionicPopup, $ionicLoading,$timeout,$cordovaImagePicker,UploadAvatar,UserInfoService,$http,TokenService,ApiEndpoint){
  $scope.settingsData = {}
  $scope.settingsImgSrc = 'http://img4.imgtn.bdimg.com/it/u=2293915270,526489152&fm=11&gp=0.jpg';

  // Triggered on a button click, or some other target
  $scope.actionSheetShow = function() {

    // Show the action sheet
    var hideSheet = $ionicActionSheet.show({
      buttons: [
        { text: '从相册选择' }
      ],
      //destructiveText: 'Delete',
      titleText: '更换头像',
      cancelText: '取消',
      cancel: function() {
        // add cancel code..
      },
      buttonClicked: function(index) {
        console.log(index);
        if(index == 0){
          //从相册选择
          var options = {
            maximumImagesCount: 1,
            width: 800,
            height: 800,
            quality: 80
          };
          function win(r) {
            console.log("Code = " + r.responseCode);
            console.log("Response = " + r.response);
            console.log("Sent = " + r.bytesSent);
            //response.push(r)

            var res = {};
            res = r.response;
            var obj = JSON.parse(res);
            console.log(obj);
            var old = UserInfoService.getInfo();
            old["image"] = ApiEndpoint.imgUrl+obj.image_path;
            UserInfoService.setInfo(old);
            $ionicLoading.hide();
            $scope.$emit("updateAvatar");
            $ionicLoading.show({
              template:"上传成功",
              duration:1000
            });


          }
          function fail(error) {
            $ionicLoading.hide();
            $ionicLoading.show({
              template:'网络错误',
              duration:1000,
            });

            //$cordovaToast.showLongBottom("网络错误" + error.code).then(function(success) {
            //  // success
            //}, function (error) {
            //  // error
            //});
            console.log("upload error source " + error.source);
            console.log("upload error target " + error.target);
            console.log(error.content);
          }
          $cordovaImagePicker.getPictures(options)
            .then(function (results) {
              console.log(results);
              var imgSrc = results;
              $ionicLoading.show({
                template:"头像上传中..."
              });
              UploadAvatar.upload(imgSrc,win,fail)

            }, function (error) {
              // error getting photos
            });

        }

        return true;
      }
    });

    // For example's sake, hide the sheet after two seconds
    $timeout(function() {
      hideSheet();
    }, 3000);

  };

  // Triggered on a button click, or some other target
  $scope.showPopupWechat = function() {
    $scope.data = {};

    // An elaborate, custom popup
    var myPopupWechat = $ionicPopup.show({
      template: '<input type="text" ng-model="data.wechat">',
      title: '更换微信号',
      scope: $scope,
      buttons: [
        { text: '取消' },
        {
          text: '<b>保存</b>',
          type: 'button-self-design',
          onTap: function(e) {
            if (!$scope.data.wechat) {

              e.preventDefault();
            } else {
              return $scope.data.wechat;
            }
          }
        }
      ]
    });

    myPopupWechat.then(function(wechat) {
      console.log('点击', wechat);

        var success = function(response){
          var result = eval(response.data);
          console.log(result);
          if (result.flag == "success") {
            $ionicLoading.hide();
            var old = UserInfoService.getInfo();
            old["wechat"] = wechat;
            UserInfoService.setInfo(old);
            $timeout(function () {
              $state.go("app.settings")
            }, 1000)
          } else {
            //$cordovaToast.showLongBottom("上传失败").then(function(success) {
            //  // success
            //}, function (error) {
            //  // error
            //});
          }
        };
        var error = function(e){
          $ionicLoading.hide();
          $ionicLoading.show({
            template:'网络错误',
            duration:1000,
          });
        };
        $ionicLoading.show({
          template:"修改中,请稍候"
        });
        $http({
          url: ApiEndpoint.url + '/modifyUserInfoAction',

          method: "POST",

          headers: {
            'Content-Type': 'application/json;charset=utf-8'
          },
          data: {"token": TokenService.get(), "wechat": wechat}
          //method  : 'POST',
          //url     : ApiEndpoint+'/postItemAction',
          //data    : $.param(uploadData),  // pass in data as strings
          //headers : { 'Content-Type': 'application/json;charset=utf-8' }  // set the headers so angular passing info as form data (not request payload)
        }).then(success,error);

    });

  };


  // Triggered on a button click, or some other target
  $scope.showPopupQQ = function() {
    $scope.data = {};

    // An elaborate, custom popup
    var myPopupQQ = $ionicPopup.show({
      template: '<input type="number" ng-model="data.qq">',
      title: '更换QQ',
      scope: $scope,
      buttons: [
        { text: '取消' },
        {
          text: '<b>保存</b>',
          type: 'button-self-design',
          onTap: function(e) {
            if (!$scope.data.qq) {
              //don't allow the user to close unless he enters wifi password
      e.preventDefault();
  } else {
      return $scope.data.qq;
    }
  }
}
]
    });

    myPopupQQ.then(function(res) {
      if(res.length == 0){
        return
      }
      var success = function(response){
        var ressult = eval(response);
        console.log(ressult);
        if (ressult.flag == "success") {

          var old = UserInfoService.getInfo();
          old["qq"] = res;
          UserInfoService.setInfo(old);
          $timeout(function(){
            $ionicLoading.hide();
            $state.go("app.settings")
          },1000)
        } else {
          $ionicLoading.hide();
          if(result.msg == 'invalid token'){
            $ionicLoading.show({
              template:"请重新登录~",
              duration:1000,
            });
          }
        }
      };
      var error = function(e){
        $ionicLoading.show({
          template:"网络未连接哦~",
          duration:1000,
        });
      }
      $ionicLoading.show({
        template:"修改中,请稍候..."
      })
      $http({
        url: ApiEndpoint.url + '/modifyUserInfoAction',

        method: "POST",

        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        data: {"token":TokenService.get(),"qq":res}
        //method  : 'POST',
        //url     : ApiEndpoint+'/postItemAction',
        //data    : $.param(uploadData),  // pass in data as strings
        //headers : { 'Content-Type': 'application/json;charset=utf-8' }  // set the headers so angular passing info as form data (not request payload)
      }).then(success,error);
    });
  };

  // Triggered on a button click, or some other target
  $scope.showPopupPhone = function() {
    $scope.data = {};

    // An elaborate, custom popup
    var myPopupPhone = $ionicPopup.show({
      template: '<input type="number" ng-model="data.phone">',
      title: '更换手机号',
      subTitle: '11位有效手机号码',
      scope: $scope,
      buttons: [
        { text: '取消' },
        {
          text: '<b>保存</b>',
          type: 'button-self-design',
          onTap: function(e) {
            if (!$scope.data.phone) {
              //don't allow the user to close unless he enters wifi password

              e.preventDefault();
            } else {
              return $scope.data.phone;
            }
          }
        }
      ]
    });

    myPopupPhone.then(function(res) {
      if(res.length == 0){
        return
      }
      $ionicLoading.show({
        template:"信息修改中,请稍候..."
      });
      $http({
        url: ApiEndpoint.url + '/modifyUserInfoAction',

        method: "POST",

        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        data: {"token":TokenService.get(),"phone":res}
        //method  : 'POST',
        //url     : ApiEndpoint+'/postItemAction',
        //data    : $.param(uploadData),  // pass in data as strings
        //headers : { 'Content-Type': 'application/json;charset=utf-8' }  // set the headers so angular passing info as form data (not request payload)
      })
        .success(function(response) {
          var ressult = eval(response);
          console.log(ressult);
          if (ressult.flag == "success") {

            var old = UserInfoService.getInfo();
            old["phone"] = res;
            UserInfoService.setInfo(old);

            $timeout(function(){
              $ionicLoading.hide();
              $state.go("app.settings")
            },1000)
          } else {
            //$cordovaToast.showLongBottom("上传失败").then(function(success) {
            //  // success
            //}, function (error) {
            //  // error
            //});
          }
        }).error(function(e){
        $ionicLoading.show({
          template:'网络错误',
          duration:1000,
        });
        //$cordovaToast.showLongBottom("网络故障").then(function(success) {
        //  // success
        //}, function (error) {
        //  // error
        //});
      });
      console.log('点击', res);
    });

  };

  $scope.exit= function(){
    console.log("seeyou");
    UserInfoService.logout();
    UserInfoService.setInfo({});
    $state.go('guestView.login');
  }

});

//个人信息
app.controller('MeCtrl', function($scope,$state, $http,$ionicLoading,UserInfoService,ApiEndpoint){
  $scope.userData = {};
  $scope.raw_commentsData  = [];
  $scope.raw_myPostData = [];
  $scope.$on('$ionicView.enter', function(e) {
    $scope.userData = UserInfoService.getInfo();
    $scope.getData();
  });
  //user_info: Object
  //email: "y.d.li@sjtu.edu.cn"
  //image: null
  //register_time: "2015-12-04 10:48:27.0"
  //user_id: "Uy.d.li"
  //username: "JMUy.d.li"
  //$scope.meData = {
  //  user_id: '1',
  //  avatar_path: 'http://cdn.duitang.com/uploads/item/201408/13/20140813122725_8h8Yu.jpeg',
  //  username: '一只学霸',
  //  mailbox: 'abc_sjtu@sjtu.edu.cn',
  //  wechat: '39232931',
  //  qq:'12121234',
  //  phone:'1893729839',
  //  registerDate:'2015年12月27日'
  //};
  //$scope.meData = UserInfoService.getInfo()
  $scope.shouldShowDelete = true;
  $scope.shouldShowReorder = true;
  $scope.listCanSwipe = true;

  $scope.commentsData = [];
  $scope.myPostData = [];

  $scope.getData = function(){
    var success = function(response){
      var res = eval(response);
      if(res.data.flag = "success"){
        console.log(res.data.message);
        console.log(res.data.items);
        UserInfoService.setPostData( res.data.items);
        $scope.raw_commentsData = res.data.message;
        $scope.raw_myPostData = res.data.items;
        $scope.showData();

      }
    };
    var fail = function(e){

    }

    $http({
      url:ApiEndpoint.url+"/getUserItemCommentAction",
      method:'GET',
      params:{user_id:$scope.userData.user_id}
    }).then(success,fail)
  }

  $scope.showData = function(){
    $scope.myPostData = [];
    for(var i=0;i<$scope.raw_commentsData.length;i++){
      var avatar = ($scope.raw_commentsData[i].avatar)?ApiEndpoint.imgUrl+$scope.raw_commentsData[i].avatar:'img/user.png'
      var date = $scope.raw_commentsData[i].date.substr(0,$scope.raw_commentsData[i].date.lastIndexOf('.'));
      $scope.commentsData[i] = {
        userAvatar: avatar,
        userName: $scope.raw_commentsData[i].user_name,
        commentDate: date,
        commentContent: $scope.raw_commentsData[i].content ,
      };
    }
    for (var i=0; i<$scope.raw_myPostData.length; i++) {
      var avatar = ($scope.raw_myPostData[i].avatar_path)?ApiEndpoint.imgUrl+$scope.raw_myPostData[i].avatar_path:'img/user.png';
      var postpic = ($scope.raw_myPostData[i].postImgPath[0])?ApiEndpoint.imgUrl+$scope.raw_myPostData[i].postImgPath[0]:'img/sample/sample1.png'
      $scope.myPostData[i] = {
        item_id: $scope.raw_myPostData[i].item_id,
        avatar_path: avatar,
        username: $scope.raw_myPostData[i].username,
        postDate: $scope.raw_myPostData[i].postDate,
        postImgPath: postpic,
        price: $scope.raw_myPostData[i].price,
        location: $scope.raw_myPostData[i].location,
        title: $scope.raw_myPostData[i].title,
      };
    }
  }

$scope.details = function(item_id){
  $state.go('app.details',{item_id:item_id,origin:'me'});
}

});

//物品详情
app.controller('DetailsCtrl', function($scope, $state,$http,$timeout,$ionicLoading,$stateParams,MainPageDataService,ApiEndpoint,UserInfoService,TokenService){

  $scope.detailsImages =[];
  $scope.commentsData = [];
  $scope.countComments = 0;
  $scope.postcomment ={};
  //$scope.countCollections = 0
  //商品id
  $scope.item_id = $stateParams.item_id;
  $scope.origin = $stateParams.origin;
  var item_data = {}
  if($scope.origin == 'main'){
     item_data = MainPageDataService.getItem($scope.item_id);
  }else{
     item_data = UserInfoService.getPostDataItem($scope.item_id);
  }

  var avatar = (item_data.avatar_path)?item_data.avatar_path:'img/user.png';
  $scope.detailsData = {
    item_id: item_data.item_id,
    avatarPath: avatar,
    username: item_data.username,
    postDate: item_data.postDate,
    price: item_data.price,
    title:item_data.title,
    location: item_data.location,
    quality: item_data.quality,
    phone: item_data.phone,
    wechat: item_data.wechat,
    qq: item_data.qq,
    details: item_data.details,
  };
  //展示图片
  if(item_data.postImgPath.length == 0){
    for(var i = 0; i < 6; i++){
      $scope.detailsImages[i] = {
        postImgPath: "img/sample/sample1.png",
      }
    }
  }
  for(var i = 0;i < item_data.postImgPath.length;i++){
    console.log(item_data.postImgPath[i]);
    $scope.detailsImages[i] = {
      postImgPath: ApiEndpoint.imgUrl+item_data.postImgPath[i],
    }
  }

  //进入时触发函数
  $scope.$on('$ionicView.enter',function(e){
    $scope.getCommentData();
    //$scope.$emit("backFromDetail");
  });

  //展示评论
  $scope.$on('getCommentDataDone', function(e){
    console.log("getCommentDataDone");
    $scope.showComment();
  });

  $scope.showComment = function(){
    if($scope.raw == null){
      //无评论
    }else {
      var length=$scope.raw.length;
        $scope.countComments = UserInfoService.getPostData().length;
      for(var i = 0; i< $scope.raw.length;i++){
        var avatar = ($scope.raw.avatar)? ApiEndpoint.imgUrl+$scope.raw.avatar:'img/user.png'
        $scope.commentsData[i]={
          avatarPath: avatar,
          username: $scope.raw[i].user_name,
          commentsDate: $scope.raw[i].date,
          commentsContent: $scope.raw[i].content,
        }
      }
    }

  };

  $scope.getCommentData = function(){
    var success = function(response){
        var res = eval(response);
      console.log(res.data);
      var flag = res.data.flag;
      if(flag == "success"){
        $scope.raw = eval(res.data.message);
        UserInfoService.setPostData($scope.raw);

        $scope.$emit('getCommentDataDone');
        //$scope.showComment();
      }
    };

    var fail = function(e){
      $ionicLoading.show({
        template:'网络出故障啦',
        duration:1000,
      });
      //alert("网络未连接");
    };
    $http({
      url:ApiEndpoint.url+"/getCommentAction",
      method:'GET',
      params:{item_id:$scope.item_id}
    }).then(success,fail);
  };



  $scope.doComment = function(){
    if(UserInfoService.getState()){
      //alert($scope.postcomment.input);
      if($scope.postcomment.input != null){
        $scope.postCommentData();
      }else{
        $ionicLoading.show({
          template:'别淘气,空评论是发不了的哟~~',
          duration:1000,
        });
        //alert("别淘气,空评论是不了的哟~~");
      }

    }else{
      $ionicLoading.show({
        template:'您还未登录不能评论哟',
        duration:1000,
      });
      //alert("您还未登录不能评论哟");
    }
  }

  $scope.postCommentData = function(){
    var success = function(response){
      var res = eval(response.data);
      var flag = res.flag;
      if(flag == "success"){
        $ionicLoading.hide();
        $ionicLoading.show({
          template:'评论成功',
          duration:1000,
        });
       //alert("评论成功");

        $scope.postcomment.input=null;

        $timeout(function(){
          $scope.refreshCommment();
        },500);
        $timeout(function(){
          $scope.refreshCommment();
        },500);
      }
    };

    var fail = function(e){
      $ionicLoading.show({
        template:'网络未连接',
        duration:1000,
      });
     // alert("网络未连接");
    };
    $ionicLoading.show({
      template:"发布中..."
    })
    $http({
      url:ApiEndpoint.url+"/commitMsgAction",
      method:'POST',
      data:{"token":TokenService.get(),"item_id":$scope.item_id,"parent_msg_id":null,content:$scope.postcomment.input},
    }).then(success,fail);
  }

  $scope.refreshCommment = function(){
    $scope.commentsData = [];
    $scope.getCommentData();
  }
  //for(var j=0; j<countComments;j++){
  //  $scope.commentsData[j] = {
  //    avatarPath: 'http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=38ecb37c54fbb2fb347e50167a7a0c92/d01373f082025aafc50dc5eafaedab64034f1ad7.jpg',
  //    username: '一只学霸',
  //    commentsDate: '2015年12月24日 21:19:59秒',
  //    commentsContent: '喜欢的不得了！爱不释手，手不释爱，哈哈哈，我要疯了',
  //  };
  //}
});



