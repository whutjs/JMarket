/**
 * Created by Mattxue on 2016/1/7.
 */
'use strict';
var directives = angular.module('Jmarket.directives', [])

  .directive('pwCheck', [function () {
    return {
      require: 'ngModel',
      link: function (scope, elem, attrs, ctrl) {
        var firstPassword = '#' + attrs.pwCheck;
        elem.bind('keyup', function () {
          scope.$apply(function () {
            var valOfPwd = document.getElementById(attrs.pwCheck).value;
            ctrl.$setValidity('pwmatch', elem.val() == valOfPwd);
          });
        });
      }
    };
  }])
  .directive('mail', [function () {
    return {
      require: 'ngModel',
      link: function (scope, elem, attrs, ctrl) {
        elem.bind('keyup', function () {
          scope.$apply(function () {
            var mail = document.getElementById('mailbox').value;
            var suffix = mail.split("@")[1];
            if(suffix=='sjtu.edu.cn'){
              ctrl.$setValidity('validatemail', true);
            }else{
              ctrl.$setValidity('validatemail', false);
            }
          });
        });
      }
    };
  }]);


