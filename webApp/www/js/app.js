var myAPP= angular.module('Jmarket', ['ionic', 'Jmarket.controllers', 'ngCordova','Jmarket.service','Jmarket.directives', 'angular-carousel','ionic-citydata','ionic-citypicker','ionic-locationdata','ionic-locationpicker','tabSlideBox']);

document.addEventListener("deviceready",onDeviceReady, false);
function onDeviceReady(){
  console.log(FileTransfer);
}
//全局常量
myAPP.constant('ApiEndpoint', {
  url: 'http://121.42.163.161/m',
  //url:'http://202.120.38.140:18080/jmarket/m',
  imgUrl:'http://121.42.163.161/'
  //test:"http://192.168.1.141/jmarket/m"

  //url:'192.168.1.128:7777'
});

myAPP.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }


  });
});

myAPP.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

  .state('tutorial', {
    url: '/',
    templateUrl: 'views/tutorial.html',
    controller: 'TutorialCtrl'
  })


  .state('guestView',{
    url:'/guest',
    abstract:true,
    templateUrl:'views/guestsidemenu.html',
    controller: 'GuestViewCtrl'
  })

  .state('guestView.main', {
    url: '/main',
    views: {
      'menuContent': {
        templateUrl: 'views/main.html',
        controller:'MainCtrl'
      }
    }
  })
  .state('guestView.details', {
    url: '/details/:origin/:item_id',
    views: {
      'menuContent': {
        templateUrl: 'views/details.html',
        controller: 'DetailsCtrl'
      }
    },
  })

  .state('guestView.login', {
    url: '/login',
    views: {
      'menuContent': {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      }
    },
  })
  .state('guestView.register', {
    url: '/register',
    views: {
      'menuContent': {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl'
      }
    },
  })

  .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'views/sidemenu.html',
    controller: 'AppCtrl'
  })
  .state('app.search', {
    url: '/search',
    views: {
      'menuContent': {
        templateUrl: 'views/search.html',
        controller: 'SearchCtrl'
      }
    }
  })

  .state('app.register', {
      url: '/register',
      views: {
        'menuContent': {
          templateUrl: 'views/register.html',
          controller: 'RegisterCtrl'
        }
      },
    })

  .state('app.details', {
    url: '/details/:origin/:item_id',
    views: {
      'menuContent': {
        templateUrl: 'views/details.html',
        controller: 'DetailsCtrl'
      }
    },
  })

  .state('app.upload', {
    url: '/upload',
    views: {
      'menuContent': {
        templateUrl: 'views/uploadGoods.html',
        controller: 'UploadCtrl'
      }
    },
  })

  .state('app.stars', {
    url: '/stars',
    views: {
      'menuContent': {
        templateUrl: 'views/stars.html',
        controller: 'StarsCtrl'
      }
    },
  })
  .state('app.settings', {
    url: '/settings',
    views: {
      'menuContent': {
        templateUrl: 'views/settings.html',
        controller: 'SettingsCtrl'
      }
    },
  })
    .state('app.modpwd', {
      url: '/modpwd',
      views: {
        'menuContent': {
          templateUrl: 'views/meModPwd.html',
          controller: 'ModPwdCtrl'
        }
      },
    })
  .state('app.me', {
    url: '/me',
    views: {
      'menuContent': {
        templateUrl: 'views/me.html',
        controller: 'MeCtrl'
      }
    }
  })
  .state('app.main', {
    url: '/main',
    views: {
      'menuContent': {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      }
    },
  })
  ;
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/');
});
