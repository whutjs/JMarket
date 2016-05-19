/**
 * Created by Mattxue on 2015/12/22.
 */
$(function() {
  $('.nav li').hover(function(e) {
    $('.nav li').removeClass('active');
    //$(e.target).addClass('active');
    $(this).addClass('active');
  });
});

$(function () {
  $("[rel=android-drevil]").popover({
    trigger: 'manual',
    placement: 'left',
    title: '<div style="text-align:center; font-size:12px; color: #808080;"> Android用户扫这里',
    html: 'true',
    content: '<div id="popOverBox"><img src="../images/srcode.png" width="80" height="80" /></div>',
    animation: false
  }).on("mouseenter", function () {
    var _this = this;
    $(this).popover("show");
    $(this).siblings(".popover").on("mouseleave", function () {
      $(_this).popover('hide');
    });
  }).on("mouseleave", function () {
    var _this = this;
    setTimeout(function () {
      if (!$(".popover:hover").length) {
        $(_this).popover("hide")
      }
    }, 100);
  });
  $("[rel=ios-drevil]").popover({
    trigger: 'manual',
    placement: 'left',
    title: '<div style="text-align:center; font-size:12px; color: #808080;"> iOS用户扫那里',
    html: 'true', //needed to show html of course
    content: '<div id="popOverBox"><img src="../images/srcode.png" width="80" height="80" /></div>',
    animation: false
  }).on("mouseenter", function () {
    var _this = this;
    $(this).popover("show");
    $(this).siblings(".popover").on("mouseleave", function () {
      $(_this).popover('hide');
    });
  }).on("mouseleave", function () {
    var _this = this;
    setTimeout(function () {
      if (!$(".popover:hover").length) {
        $(_this).popover("hide")
      }
    }, 100);
  });
});
