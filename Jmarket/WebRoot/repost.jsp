<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>修改发布</title>
     <link rel="shortcut icon" href="<%=path %>/images/favicon.ico" />
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/font-awesome.min.css"  rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/login.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/navbar.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/footer.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/post.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=path %>/mycss/Player/app.css" type="text/css">
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<%=path %>/myjs/post.js"></script>
</head>
<body>
<div class="wrapper wrapper_navbar_navbar at-top">
    <nav class="navbar navbar-inverse no-border navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=path %>/search/main">JMarket</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right nav-inline">
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

                </ul>
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

<div class="container post-container">
    <div class="col-md-12">
        <form class="post-form" id="post_form" name="post_form" action="<%=path %>/usercenter/repost" >
            <fieldset id="field_detail">
                <div class="control-group">
                    <label class="control-label"><i style="color: red">*</i> 新旧</label>
                    <div class="controls ">
                        <select class="input-xlarge" id="quality" name="state">
								<option value="0">-请选择-</option>
								<c:choose>
									<c:when test="${ item4repost.IState == 10}">
										<option value="1" selected="selected">全新</option>
									</c:when>
									<c:otherwise>
										<option value="1">全新</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ item4repost.IState == 9}">
										<option value="2" selected="selected">9成新</option>
									</c:when>
									<c:otherwise>
										<option value="2">9成新</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ item4repost.IState == 8}">
										<option value="3" selected="selected">8成新</option>
									</c:when>
									<c:otherwise>
										<option value="3">8成新</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ item4repost.IState == 7}">
										<option value="4" selected="selected">7成新</option>
									</c:when>
									<c:otherwise>
										<option value="4">7成新</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ item4repost.IState == 6}">
										<option value="5" selected="selected">6成新</option>
									</c:when>
									<c:otherwise>
										<option value="5">6成新</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ item4repost.IState == 5}">
										<option value="6" selected="selected">5成新</option>
									</c:when>
									<c:otherwise>
										<option value="6">5成新</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ item4repost.IState == 4}">
										<option value="7" selected="selected">5成新以下</option>
									</c:when>
									<c:otherwise>
										<option value="7">5成新以下</option>
									</c:otherwise>
								</c:choose>
                        </select>
                        <p class="help-block" id="tip_quality">*必填</p>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"><i style="color: red">*</i> 价格</label>
                    <div class="controls">
                        <input type="number" class="input-xlarge price" min="0" placeholder="￥" id="price" value="${item4repost.IPrice }" name="price">
                        <p class="help-block" id="tip_price">单位:RMB</p>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"><i style="color: red">*</i> 位置</label>
                    <div class="controls">
                        <select class="input-xlarge" id="campus" onchange="clickParentOpt()" name="campus">
                            <option value="0">-请选择-</option>
                            <option value="1">徐汇校区</option>
                            <option value="2">闵行校区</option>
                        </select>
                        <select class="input-xlarge" id="area" onchange="clickChildOpt()" name="area">
                        </select>
                        <select class="input-xlarge" id="building" name="building">
                        </select>
                        <p class="help-block" id="tip_location">*先选择校区</p>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"><i style="color: red">*</i> 标题</label>
                    <div class="controls">
                        <input type="text" placeholder="6-50字" class="input-xlarge" maxlength="50" id="title" value="${item4repost.IName }" name="name">
                        <p class="help-block" id="tip_title">*6-50字，简要描述</p>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"><i style="color: red">*</i> 详情</label>
                    <div class="controls">
                        <div class="textarea">
                            <textarea name="description" id="detail" maxlength="2000">${item4repost.IDescription}</textarea>
                        </div>
                        <p class="help-block" id="tip_detail">*说明我们未提供的内容，如物品的细节之处，何时购入，是否在保修期等等,10-2000字</p>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <input type="submit"  class="btn btn-post" value="重新发布" id="btn_post">
                    </div>
                </div>

            </fieldset>
        </form>
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


<script type="text/javascript">
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
