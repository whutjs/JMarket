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
    <title>联系我们</title>
    <link rel="shortcut icon" href="<%=path %>/images/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="<%=path %>/mycss/aboutus.css">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/navbar.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/footer.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=path %>/mycss/Player/app.css" type="text/css">
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="wrapper wrapper_navbar_navbar at-top">
    <nav class="navbar navbar-inverse no-border navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=path %>/search/main">JMarket</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
                                    	我的消息
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

        <form class="navbar-form navbar-right" role="search" id="search_form" action="<%=path%>/search/main">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="感兴趣的东西..." id="search_item" name="search_item">
          </div>
          <button type="submit" class="btn btn-search">
            <span class="fa fa-search" aria-hidden="true"></span>
          </button>
        </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>
<div class="container" style="margin-top: 80px;">
    <div id="main-column">
        <div id="post-36" class="fullwidth-page post-36 page type-page status-publish hentry">
            <div class="fullwidth-container sample" style="border: none;">
                <p style="background-color: #FFD300;padding:10px;"><span style="font-size: 24px;color: #333; font-weight: 600;"><span>联系我们</span></span>
                </p>
                <section class="about-box f-cb" style="border: 1px solid #ddd;padding: 10px;">
                    <section class="about-ctr f-cb">
                        <p style="white-space: normal;"><span
                        >电子邮箱(E-mail)：</span><span
                        >2456085825@qq.com</span>
                        </p>
                        <p style="white-space: normal;"><span><span
                        >电话(Tel)：</span><span
                        >(+86)13167116991</span></span>
                        </p>
                        <p><span><span>联系地址(Add)：<span>上海市闵行区闵行区上海交通大学电院3号楼</span></span></span>
                        </p>
                        <p><span><span style="">邮编(Post Code)：</span><span>200240</span></span></p>
                        <p>&nbsp;</p>
                    </section>
                </section>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
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


<script type="text/javascript" src="<%=path %>/myjs/jumpToMainpage.js"></script>
<script type="text/javascript">
    $(function () {
        $('.nav li').hover(function (e) {
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
            content: '<div id="popOverBox"><img src="<%=path %>/images/srcode.png" width="80" height="80" /></div>',
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
            content: '<div id="popOverBox"><img src="<%=path %>/images/srcode.png" width="80" height="80" /></div>',
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

</script>
</body>
</html>