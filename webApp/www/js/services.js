/**
 * Created by leonard on 15/12/18.
 */
var service = angular.module('Jmarket.service',[])

//令牌服务
.factory('TokenService',function(){
  var _Token = "4byW4liCRO"
  return {
    set:function(token){
      _Token = token
    },
    get:function(){
      return _Token.toString()
    }

  }
})

//用户信息服务
.factory('UserInfoService',function(){
  var _isLogin = false; //default
  var _userinfo = {};
  var _userPostData = [];
  return {
    setInfo:function(userinfo){
      _userinfo = userinfo
    },
    getInfo:function(){
      return _userinfo;
    },
    login:function(){
      _isLogin = true;
    },
    logout:function(){
      _isLogin = false;
    },
    getState:function(){
      return _isLogin;
    },
    setPostData:function(postData){
      _userPostData = postData;
    },
    getPostData:function(){
      return _userPostData;
    },
    getPostDataItem:function(item_id){
      for(var i in _userPostData){
        if(item_id == _userPostData[i].item_id) {
          return _userPostData[i];
          break;
        }
      }
    }

  }

})

//主页数据
.factory('MainPageDataService', function(){
  var _data = {};
  var _mainPageData = {};
  return {
    setData:function(data){
      _data = data;
    },
    getData:function(){
      return _data;
    },
    getItem:function(item_id){
        for(var i in _mainPageData){
          if(item_id == _mainPageData[i].item_id) {
            return _mainPageData[i];
            break;
          }
        }
    },
    setMain:function(main){
      _mainPageData = main;
    },
    getMain:function(){
      return _mainPageData;
    }

  }


})

//设置数据
.factory("Settings", function(){

})

//上传数据服务
.factory('UploadData', function(ApiEndpoint, TokenService){
  var uploadData = {
        "first_type": "",
        "second_type": "",
        "quality": "",
        "price": "",
        "imgSrc": [
          "",
          "",
          "",
          "",
          "",
          ""
        ],
        "title": "",
        "detail": "",
        "location": "",
        "token": "",
        "imgSize": ""};

  return{
    upload:function(imgUrl,success,error){
      try{
        var options = new FileUploadOptions();
        options.filekey = "file";
        options.fileName = imgUrl[0].substr(imgUrl[0].lastIndexOf('/')+1);
        var params = {}
        params['Token'] = TokenService.get()
        options.params = params;
        var fileTransfer = new FileTransfer();
        fileTransfer.upload(
          imgUrl,
          encodeURI(ApiEndpoint.url+"/uploadFileAction"),
          success,
          error,
          options
        );
      }catch(e){
        alert('网络有点故障哦', e);
      }
    }

  }
})

.factory('UploadAvatar',function(ApiEndpoint,TokenService){
  return{
    upload:function(imgUrl,success,error){
      try{
        var options = new FileUploadOptions();
        var imgPath = imgUrl[0];
        options.fileKey = "file";
        //console.log(imgPath.substr(imgPath.lastIndexOf('/')+1));
        options.fileName = imgPath.substr(imgPath.lastIndexOf('/')+1);
        var params = {}
        params['Token'] = TokenService.get()
        options.params = params;
        var fileTransfer = new FileTransfer();
        fileTransfer.upload(
          imgPath,
          encodeURI(ApiEndpoint.url+"/uploadAvatarAction"),
          success,
          error,
          options
        );
      }catch(e){
        alert('网络有点故障哦', e);
      }
    }

  }
});
