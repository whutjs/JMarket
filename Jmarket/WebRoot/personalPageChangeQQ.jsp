<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="description"
        content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 <link rel="shortcut icon" href="<%=path %>/images/favicon.ico" />
  <link rel="stylesheet" href="<%=path %>/myjs/Player/jPlayer/jplayer.flat.css" type="text/css">
  <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" type="text/css">
  <link rel="stylesheet" href="<%=path %>/mycss/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="<%=path %>/mycss/Player/animate.css" type="text/css">
  <!--<link rel="stylesheet" href="<%=path %>/mycss/Player/simple-line-icons.css" type="text/css">-->
  <!--<link rel="stylesheet" href="<%=path %>/mycss/Player/font.css" type="text/css">-->
  <link rel="stylesheet" href="<%=path %>/mycss/Player/app.css" type="text/css">
  <link href="<%=path %>/mycss/footer.css" rel="stylesheet" type="text/css">
  <link href="<%=path %>/mycss/personal.css" type="text/css" rel="stylesheet">
  <title>个人中心-更换QQ</title>

</head>
<body>
<section class="vbox">
   <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
    <div class="navbar-header aside bg-info nav-xs">
      <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
        <i class="fa fa-list"></i>
      </a>
      <a href="<%=path %>/search/main" class="navbar-brand text-lt">
        <i class="fa fa-home"></i>
        <img src="<%=path %>/images/sjtu_logo.png" alt="." class="hide">
        <span class="hidden-nav-xs m-l-sm">主页</span>
      </a>
      <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">
        <i class="fa fa-gear"></i>
      </a>
    </div>
    <ul class="nav navbar-nav hidden-xs">
      <li>
        <a href="#nav,.navbar-header" data-toggle="class:nav-xs,nav-xs" class="text-muted">
          <i class="fa fa-indent text"></i>
          <i class="fa fa-dedent text-active"></i>
        </a>
      </li>
    </ul>
    <div class="navbar-right ">
      <ul class="nav navbar-nav m-n hidden-xs nav-user user">
        <li class="hidden-xs">
          <a href="#" class="dropdown-toggle lt" data-toggle="dropdown">
            <i class="fa fa-bell"></i>
            <span class="badge badge-sm up bg-danger count" style="display: inline-block;">${reply4usersize}</span>
          </a>
          <section class="dropdown-menu aside-xl animated fadeInUp">
            <section class="panel bg-white">
              <div class="panel-heading b-light bg-light">
                <strong>您有 <span class="count" style="display: inline;">${reply4usersize}</span> 条回复</strong>
              </div>
              <div class="list-group list-group-alt">
              	<c:forEach var="result" items="${reply4user}">
              	<c:set var="messageuser" value='${result["TUser"]}' scope="page"></c:set>
                <a href="#" class="media list-group-item">
                  <span class="pull-left thumb-sm">
                             <c:if test="${empty user.UImage }">
                                <img src="<%=path %>/images/user.png" class="img-circle" style="width:40px;height:40px;">
                             </c:if>
                             <c:if test="${not empty user.UImage }">
                             	<img src="<%=path %>/${user.UImage}" class="img-circle" style="width:40px;height:40px;">
                             </c:if>
                  </span>
                  <span class="media-body block m-b-none">
                      	${messageuser["UName"]}评论了您<br>
                    <small class="text-muted">${result["MDate"]}</small>
                  </span>
                </a>
                </c:forEach>
              </div>
              <div class="panel-footer text-sm">
                <a href="#" class="pull-right"><i class="fa fa-cog"></i></a>
                <a href="#" data-toggle="class:show animated fadeInRight">查看所有消息</a>
              </div>
            </section>
          </section>
        </li>
<c:choose>

						<c:when test="${login}">
							<ul class="nav navbar-nav navbar-right nav-inline">
							
								<li class="dropdown">
                        <a  class="dropdown-toggle bg clear" data-toggle="dropdown">
                            <span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm">
                            <c:if test="${empty user.UImage }">
                                <img src="<%=path %>/images/user.png" style="width:40px;height:40px;">
                             </c:if>
                             <c:if test="${not empty user.UImage }">
                             	<img src="<%=path %>/${user.UImage}" style="width:40px;height:40px;">
                             </c:if>
                            </span>${user.UEmail }
                            <b class="caret"></b>
                        </a>
                          <ul class="dropdown-menu animated fadeInRight">
                            <li>
                                <span class="arrow top"></span>
                            </li>
                            <li>
                                <a href="<%=path %>/post.jsp">我要发布</a>
                            </li>
                            <li>
                                <a href="<%=path %>/usercenter/message?type=1">基本资料</a>
                            </li>
                            <li>
                                <a href="<%=path %>/usercenter/message?type=2">
                                     <span class="badge bg-danger pull-right">${totalsize}</span>
                                    所有消息
                                </a>
                            </li>
                            <li>
                                <a href="<%=path %>/usercenter/item">所有发布</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="<%=path %>/usercenter/logout">登出</a>
                            </li>
                        </ul>
                    </li>
								
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="nav navbar-nav navbar-right nav-inline">
								<li><a href="<%=path%>/login.jsp" id="nav_login">登录 <span
										class="sr-only">(current)</span>
								</a>
								</li>
								<li><a href="<%=path%>/register.jsp" id="nav_register">注册</a>
								</li>
							</ul>
						</c:otherwise>

					</c:choose>

        </li>
      </ul>
    </div>
  </header>
  <section>
    <section class="hbox stretch">
      <!-- .aside -->
      <aside class="bg-black dk nav-xs aside hidden-print" id="nav">
        <section class="vbox">
          <section class="w-f-md scrollable">
            <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 317px;">
              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0"
                   data-size="10px" data-railopacity="0.2" style="overflow: hidden; width: auto; height: 317px;">

                <!-- nav -->
                <nav class="nav-primary hidden-xs">
                  <ul class="nav bg clearfix">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                      我的资料
                    </li>
                    <li>
                      <a href="<%=path %>/personalPage.jsp">
                        <i class="fa fa-user text-warning"></i>
                        <span class="font-thin">基本资料</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=path %>/personalPageChangePassword.jsp">
                        <i class="fa fa-lock text-dark"></i>
                        <span class="font-thin">更改密码</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=path %>/personalPageChangeAvatar.jsp">
                        <i class="fa fa-camera text-danger-lt"></i>
                        <span class="font-thin">更换头像</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=path %>/personalPageChangeWechat.jsp">
                        <i class="fa fa-weixin  text-success"></i>
                        <span class="font-thin">更换微信</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=path %>/personalPageChangeQQ.jsp">
                        <i class="fa fa-qq  text-success-dk" style="background:#eee;"></i>
                        <span class="font-thin">更换QQ</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=path %>/personalPageChangePhone.jsp">
                        <i class="fa fa-phone  text-success-dker"></i>
                        <span class="font-thin">更换手机号</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=path %>/usercenter/item" class="auto">
                        <i class="fa fa-shopping-cart text-danger-lter"></i>
                        <span class="font-thin">我的发布</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=path %>/usercenter/message?type=2">
                        <i class="fa fa-comment text-dark"></i>
                        <b class="badge bg-danger dker pull-right">${totalsize}</b>
                        <span>我的消息</span>
                      </a>
                    </li>
                    <!--  <li class="m-b hidden-nav-xs"></li>
                  </ul>
                  <ul class="nav" data-ride="collapse">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                      我的物品
                    </li>
                    <li>
                      <a href="<%=path %>/usercenter/item" class="auto">
                        <span class="pull-right text-muted">
                          <i class="fa fa-angle-left text"></i>
                          <i class="fa fa-angle-down text-active"></i>
                        </span>
                        <i class="fa fa-shopping-cart text-danger-lter">
                        </i>
                        <span>我的发布</span>
                      </a>
                    </li>
                  </ul>
                  <ul class="nav text-sm">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                      消息
                    </li>
                    <li>
                      <a href="<%=path %>/usercenter/message?type=2">
                        <i class="fa fa-comment text-dark"></i>
                        <b class="badge bg-danger dker pull-right">${reply4usersize}</b>
                        <span>我的消息</span>
                      </a>
                    </li>
                  </ul>-->
                </nav>
                <!-- / nav -->
              </div>
              <div class="slimScrollBar"
                   style="width: 10px; position: absolute; top: -429px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 0px; height: 200.978px; background: rgb(0, 0, 0);"></div>
              <div class="slimScrollRail"
                   style="width: 10px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 0px; background: rgb(51, 51, 51);"></div>
            </div>
          </section>
        </section>
      </aside>
      <!-- /.aside -->
      <section id="content">
        <div class="container">
          <div class="hbox hbox-auto-xs hbox-auto-sm m-t-lg">
            <div class="col">
              <div style="background:url(<%=path %>/images/personal-bg.jpg) center center; background-size:cover">
                <div class="wrapper-lg bg-white-opacity">
                  <div class="row m-t">
                    <div class="col-sm-7">
                      <a href="" class="thumb-lg pull-left">
                              <c:if test="${empty user.UImage }">
                                <img src="<%=path %>/images/user.png" class="img-circle" style="width:80px;height:80px;">
                             </c:if>
                             <c:if test="${not empty user.UImage }">
                             	<img src="<%=path %>/${user.UImage}" class="img-circle" style="width:80px;height:80px;">
                             </c:if>
                      </a>
                      <div class="clear m-b">
                        <div class="m-b m-t-sm">
                          <span class="h4 text-dark-lter">${user.UEmail }</span>
                        </div>
                        <p class="m-b">
                          <a class="btn btn-sm btn-success btn-rounded btn-default btn-icon" title="微信号：${user.UWechat}"><i class="fa fa-wechat"></i></a>
                          <a class="btn btn-sm btn-success btn-rounded btn-default btn-icon" title="QQ号：${user.UQq}"><i class="fa fa-qq"></i></a>
                          <a class="btn btn-sm btn-success btn-rounded btn-default btn-icon" title="手机号：${user.UPhone}"><i class="fa fa-mobile-phone"></i></a>
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="wrapper bg-white b-b">
                <ul class="nav nav-pills nav-sm">
                  <li class="active"><a>更换QQ</a></li>
                </ul>
              </div>
              <div class="div-box">
                <form class="chg-qq-form" id="chg_qq_form" action="<%=path %>/usercenter/qq">
                  <div class="control-group">
                    <label class="control-label"><i style="color: red"></i> 新QQ号</label>
                    <div class="controls">
                      <input type="text" class="input-xlarge"  id="qq" value="" name="newqq">
                      <p class="help-block" id="tip_qq">*新的QQ号</p>
                    </div>
                  </div>

                  <div class="control-group">
                    <div class="controls control-submit">
                      <input type="submit" class="input-xlarge"  id="btn_submit" value="提交">
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>
    </section>
  </section>
</section>

<div id="footer">
  <div class="container">
    <div class="footer-menu">
      <ul>
                <li><a href="<%=path%>/aboutus.jsp">关于我们</a></li>
                <li><a href="<%=path%>/qa.jsp">FAQ</a></li>
                <li><a href="<%=path%>/contactus.jsp">联系我们</a></li>
      </ul>
    </div>
  </div>
</div>
<ul id="fixed_links" class="hide-mobile">
  <li>
    <a href="#" class="link-icon" id="android" rel="android-drevil">
      <i class="fa fa-android"></i>
    </a>
  </li>
  <li>
    <a href="#" class="link-icon" id="ios" rel="ios-drevil">
      <i class="fa fa-apple"></i>
    </a>
  </li>
  <li>
    <a href="#" id="back-to-top" style="display: block;font-size: 1em;color: #FFF">
      <i class="fa fa-chevron-up" title="回到顶部"></i>
    </a>
  </li>
</ul>

<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- App -->
<script src="<%=path %>/myjs/Player/app.js"></script>
<script src="<%=path %>/myjs/Player/slimscroll/jquery.slimscroll.min.js"></script>
<!--<script src="<%=path %>/myjs/Player/app.plugin.js"></script>-->
<!--<script type="text/javascript" src="<%=path %>/myjs/appDownloadTip.js"></script>-->

<!--<script type="text/javascript" src="<%=path %>/myjs/Player/jPlayer/jquery.jplayer.min.js"></script>-->
<!--<script type="text/javascript" src="<%=path %>/myjs/Player/jPlayer/add-on/jplayer.playlist.min.js"></script>-->
<!--<script type="text/javascript" src="<%=path %>/myjs/Player/jPlayer/demo.js"></script>-->

</body>
</html>

