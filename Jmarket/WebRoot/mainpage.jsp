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
    <meta name="viewport" content="width=device-width">
    <title>JMarket主页</title>
     <link rel="shortcut icon" href="<%=path %>/images/favicon.ico" />
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet" type="text/css">
    
    <link href="<%=path %>/mycss/mainpage.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/navbar.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/sidebar.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/footer.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/background.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=path %>/mycss/Player/app.css" type="text/css">
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<%=path %>/myjs/conditionFilter.js"></script>
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
                <a class="navbar-brand">JMarket</a>
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

					<form class="navbar-form navbar-right" id="formCondition" name="formCondition" method="get" action="<%=path %>/search/main" onkeydown="if(event.keyCode==13){return false;}">
                    <input id="price" type="hidden" value="0" name="price" to="filter">
                    <input id="quality" type="hidden" value="0" name="quality" to="filter">
                    <input id="campus" type="hidden" value="0" name="campus" to="filter">
                    <input id="first_cate" type="hidden" value="0" name="first_cate" to="filter">
                    <input id="second_cate" type="hidden" value="0" name="second_cate" to="filter">
                    <input id="pagination" type="hidden" value="0" name="pagination" to="filter">
                    <input id="sort_by" type="hidden" value="0" name="sort_by" to="filter">
                    <!--<input id="search" type="hidden" value="" name="search" to="filter">-->
                <!--<form class="navbar-form navbar-right" role="search" id="search_form">-->
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="感兴趣的东西..." id="search_item" value="" name="search_item" to="filter">
                    </div>
                    <button type="button" class="btn btn-search" id="btn_search">
                        <span class="fa fa-search" aria-hidden="true"></span>
                    </button>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div class="father-container">
        <div class="sidebar-container">
            <div class="sidebar-menu">
                <nav>
                    <ul class="mcd-menu">
                        <li>
                            <a href="javascript:void(0);" class="nav-header menu-first" id="cate0" data-toggle="collapse" onclick="getCategory(this)" type="0">
                                <i class="fa fa-circle-o"></i>
                                <strong>全部</strong>
                            </a>
                        </li>
                        <li>
                            <a href="#sechand_bycles" class="nav-header menu-first collapsed" id="cate1" data-toggle="collapse" type="1" onclick="getCategory(this)">
                                <i class="fa fa-bicycle"></i>
                                <strong>二手车</strong>
                            </a>
                            <ul id="sechand_bycles" class="nav nav-list collapse menu-second">
                                <li><a href="javascript:void(0);" id="second_cate100" type="100" onclick="getCategory(this)"><i class="fa fa-bicycle"></i> 普通自行车</a></li>
                                <li><a href="javascript:void(0);" id="second_cate101" type="101" onclick="getCategory(this)"><i class="fa fa-bicycle"></i> 山地自行车</a></li>
                                <li><a href="javascript:void(0);" id="second_cate102" type="102" onclick="getCategory(this)"><i class="fa fa-bicycle"></i> 折叠自行车</a></li>
                                <li><a href="javascript:void(0);" id="second_cate103" type="103" onclick="getCategory(this)"><i class="fa fa-bicycle"></i> 迷你自行车</a></li>
                                <li><a href="javascript:void(0);" id="second_cate104" type="104" onclick="getCategory(this)"><i class="fa fa-bicycle"></i> 公路自行车</a></li>
                                <li><a href="javascript:void(0);" id="second_cate105" type="105" onclick="getCategory(this)"><i class="fa fa-bicycle"></i> 电动车</a></li>
                                <li><a href="javascript:void(0);" id="second_cate106" type="106" onclick="getCategory(this)"><i class="fa fa-car"></i> 其他车</a></li>

                            </ul>
                        </li>
                        <li>
                            <a href="#sechand_phone" class="nav-header menu-first collapsed" id="cate2" data-toggle="collapse" aria-expanded="false" type="2" onclick="getCategory(this)">
                                <i class="fa fa-mobile-phone" style="width: 30px;height: 30px;"></i>
                                <strong>二手手机</strong>
                            </a>
                            <ul id="sechand_phone" class="nav nav-list collapse menu-second">
                                <li><a href="javascript:void(0);" id="second_cate200" type="200" onclick="getCategory(this)"><i class="fa fa-apple"></i> 苹果</a></li>
                                <li><a href="javascript:void(0);" id="second_cate201" type="201" onclick="getCategory(this)"><i class="fa fa-mobile"></i> 三星</a></li>
                                <li><a href="javascript:void(0);" id="second_cate202" type="202" onclick="getCategory(this)"><i class="fa fa-android"></i> 小米</a></li>
                                <li><a href="javascript:void(0);" id="second_cate203" type="203" onclick="getCategory(this)"><i class="fa fa-mobile"></i> 华为</a></li>
                                <li><a href="javascript:void(0);" id="second_cate204" type="204" onclick="getCategory(this)"><i class="fa fa-mobile"></i> HTC</a></li>
                                <li><a href="javascript:void(0);" id="second_cate205" type="205" onclick="getCategory(this)"><i class="fa fa-windows"></i> 诺基亚</a></li>
                                <li><a href="javascript:void(0);" id="second_cate206" type="206" onclick="getCategory(this)"><i class="fa fa-mobile"></i> 魅族</a></li>
                                <li><a href="javascript:void(0);" id="second_cate207" type="207" onclick="getCategory(this)"><i class="fa fa-mobile"></i> 索尼</a></li>
                                <li><a href="javascript:void(0);" id="second_cate208" type="208" onclick="getCategory(this)"><i class="fa fa-mobile"></i> 中兴</a></li>
                                <li><a href="javascript:void(0);" id="second_cate209" type="209" onclick="getCategory(this)"><i class="fa fa-mobile"></i> LG</a></li>
                                <li><a href="javascript:void(0);" id="second_cate210" type="210" onclick="getCategory(this)"><i class="fa fa-windows"></i> 联想</a></li>
                                <li><a href="javascript:void(0);" id="second_cate211" type="211" onclick="getCategory(this)"><i class="fa fa-mobile"></i> 酷派</a></li>
                                <li><a href="javascript:void(0);" id="second_cate212" type="212" onclick="getCategory(this)"><i class="fa fa-mobile-phone"></i> 其他手机</a></li>

                            </ul>
                        </li>
                        <li>
                            <a href="#sechand_pc" class="nav-header menu-first collapsed" id="cate3" data-toggle="collapse" type="3" onclick="getCategory(this)">
                                <i class="fa fa-laptop"></i>
                                <strong>PC/PAD</strong>
                            </a>
                            <ul id="sechand_pc" class="nav nav-list collapse menu-second">
                                <li><a href="javascript:void(0);" id="second_cate300" type="300" onclick="getCategory(this)"><i class="fa fa-desktop"></i> 台式机</a></li>
                                <li><a href="javascript:void(0);" id="second_cate301" type="301" onclick="getCategory(this)"><i class="fa fa-laptop"></i> ThinkPad/IBM</a></li>
                                <li><a href="javascript:void(0);" id="second_cate302" type="302" onclick="getCategory(this)"><i class="fa fa-apple"></i> 苹果(Mac)</a></li>
                                <li><a href="javascript:void(0);" id="second_cate303" type="303" onclick="getCategory(this)"><i class="fa fa-laptop"></i> 联想(Lenovo)</a></li>
                                <li><a href="javascript:void(0);" id="second_cate304" type="304" onclick="getCategory(this)"><i class="fa fa-laptop"></i> 戴尔(DELL)</a></li>
                                <li><a href="javascript:void(0);" id="second_cate305" type="305" onclick="getCategory(this)"><i class="fa fa-laptop"></i> 华硕(ASUS)</a></li>
                                <li><a href="javascript:void(0);" id="second_cate306" type="306" onclick="getCategory(this)"><i class="fa fa-laptop"></i> 惠普(HP)</a></li>
                                <li><a href="javascript:void(0);" id="second_cate307" type="307" onclick="getCategory(this)"><i class="fa fa-laptop"></i> 索尼(Sony)</a></li>
                                <li><a href="javascript:void(0);" id="second_cate308" type="308" onclick="getCategory(this)"><i class="fa fa-laptop"></i> 三星(Samsung)</a></li>
                                <li><a href="javascript:void(0);" id="second_cate309" type="309" onclick="getCategory(this)"><i class="fa fa-laptop"></i> 其他电脑</a></li>
                                <li><a href="javascript:void(0);" id="second_cate310" type="310" onclick="getCategory(this)"><i class="fa fa-tablet"></i> iPad</a></li>
                                <li><a href="javascript:void(0);" id="second_cate311" type="311" onclick="getCategory(this)"><i class="fa fa-tablet"></i> Surface</a></li>
                                <li><a href="javascript:void(0);" id="second_cate312" type="312" onclick="getCategory(this)"><i class="fa fa-tablet"></i> 三星平板</a></li>
                                <li><a href="javascript:void(0);" id="second_cate313" type="313" onclick="getCategory(this)"><i class="fa fa-tablet"></i> 小米平板</a></li>
                                <li><a href="javascript:void(0);" id="second_cate314" type="314" onclick="getCategory(this)"><i class="fa fa-tablet"></i> 联想平板</a></li>
                                <li><a href="javascript:void(0);" id="second_cate315" type="315" onclick="getCategory(this)"><i class="fa fa-tablet"></i> 其他平板</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sechand_home_applications" class="nav-header menu-first collapsed" id="cate4" data-toggle="collapse" type="4" onclick="getCategory(this)">
                                <i class="fa fa-plug"></i>
                                <strong>二手家电</strong>
                            </a>
                            <ul id="sechand_home_applications" class="nav nav-list collapse menu-second">
                                <li><a href="javascript:void(0);" id="second_cate400" type="400" onclick="getCategory(this)"><i class="fa fa-plug"></i> 洗衣机</a></li>
                                <li><a href="javascript:void(0);" id="second_cate401" type="401" onclick="getCategory(this)"><i class="fa fa-plug"></i> 厨房电器</a></li>
                                <li><a href="javascript:void(0);" id="second_cate402" type="402" onclick="getCategory(this)"><i class="fa fa-plug"></i> 冰箱/冰柜</a></li>
                                <li><a href="javascript:void(0);" id="second_cate403" type="403" onclick="getCategory(this)"><i class="fa fa-camera"></i> 数码相机</a></li>
                                <li><a href="javascript:void(0);" id="second_cate404" type="404" onclick="getCategory(this)"><i class="fa fa-plug"></i> 其他家电</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sechand_clothes" class="nav-header menu-first collapsed" id="cate5" data-toggle="collapse" type="5" onclick="getCategory(this)">
                                <i class="fa fa-suitcase"></i>
                                <strong>服装包箱</strong>
                            </a>
                            <ul id="sechand_clothes" class="nav nav-list collapse menu-second">
                                <li><a href="javascript:void(0);" id="second_cate500" type="500" onclick="getCategory(this)"><i class="fa fa-user-md"></i> T恤</a></li>
                                <li><a href="javascript:void(0);" id="second_cate501" type="501" onclick="getCategory(this)"><i class="fa fa-user"></i> 衬衫</a></li>
                                <li><a href="javascript:void(0);" id="second_cate502" type="502" onclick="getCategory(this)"><i class="fa fa-user-md"></i> 外套</a></li>
                                <li><a href="javascript:void(0);" id="second_cate503" type="503" onclick="getCategory(this)"><i class="fa fa-user-md"></i> 裤子</a></li>
                                <li><a href="javascript:void(0);" id="second_cate504" type="504" onclick="getCategory(this)"><i class="fa fa-user-md"></i> 西装</a></li>
                                <li><a href="javascript:void(0);" id="second_cate505" type="505" onclick="getCategory(this)"><i class="fa fa-user-md"></i> 裙子</a></li>
                                <li><a href="javascript:void(0);" id="second_cate506" type="506" onclick="getCategory(this)"><i class="fa fa-list"></i> 休闲鞋</a></li>
                                <li><a href="javascript:void(0);" id="second_cate507" type="507" onclick="getCategory(this)"><i class="fa fa-list"></i> 运动鞋</a></li>
                                <li><a href="javascript:void(0);" id="second_cate508" type="508" onclick="getCategory(this)"><i class="fa fa-list"></i> 帆布鞋</a></li>
                                <li><a href="javascript:void(0);" id="second_cate509" type="509" onclick="getCategory(this)"><i class="fa fa-list"></i> 高跟鞋</a></li>
                                <li><a href="javascript:void(0);" id="second_cate510" type="510" onclick="getCategory(this)"><i class="fa fa-list"></i> 皮鞋</a></li>
                                <li><a href="javascript:void(0);" id="second_cate511" type="511" onclick="getCategory(this)"><i class="fa fa-briefcase"></i> 单肩包</a></li>
                                <li><a href="javascript:void(0);" id="second_cate512" type="512" onclick="getCategory(this)"><i class="fa fa-briefcase"></i> 双肩包</a></li>
                                <li><a href="javascript:void(0);" id="second_cate513" type="513" onclick="getCategory(this)"><i class="fa fa-briefcase"></i> 书包</a></li>
                                <li><a href="javascript:void(0);" id="second_cate514" type="514" onclick="getCategory(this)"><i class="fa fa-briefcase"></i> 钱包</a></li>
                                <li><a href="javascript:void(0);" id="second_cate515" type="515" onclick="getCategory(this)"><i class="fa fa-suitcase"></i> 电脑包</a></li>
                                <li><a href="javascript:void(0);" id="second_cate516" type="516" onclick="getCategory(this)"><i class="fa fa-briefcase"></i> 箱子</a></li>
                                <li><a href="javascript:void(0);" id="second_cate517" type="517" onclick="getCategory(this)"><i class="fa fa-list"></i> 其他服饰</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sechand_book_and_cd" class="nav-header menu-first collapsed" id="cate6" data-toggle="collapse" type="6" onclick="getCategory(this)">
                                <i class="fa fa-book"></i>
                                <strong>图书音像</strong>
                            </a>
                            <ul id="sechand_book_and_cd" class="nav nav-list collapse menu-second">
                                <li><a href="javascript:void(0);" id="second_cate600" type="600" onclick="getCategory(this)"><i class="fa fa-book"></i> 专业书籍</a></li>
                                <li><a href="javascript:void(0);" id="second_cate601" type="601" onclick="getCategory(this)"><i class="fa fa-book"></i> 考试(GRE/托福/雅思)</a></li>
                                <li><a href="javascript:void(0);" id="second_cate602" type="602" onclick="getCategory(this)"><i class="fa fa-book"></i> 小说/文学</a></li>
                                <li><a href="javascript:void(0);" id="second_cate603" type="603" onclick="getCategory(this)"><i class="fa fa-book"></i> 工具书/辅导书</a></li>
                                <li><a href="javascript:void(0);" id="second_cate604" type="604" onclick="getCategory(this)"><i class="fa fa-book"></i> 生活类书籍</a></li>
                                <li><a href="javascript:void(0);" id="second_cate605" type="605" onclick="getCategory(this)"><i class="fa fa-calculator"></i> 学生文具</a></li>
                                <li><a href="javascript:void(0);" id="second_cate606" type="606" onclick="getCategory(this)"><i class="fa fa-tablet"></i> 学习机</a></li>
                                <li><a href="javascript:void(0);" id="second_cate607" type="607" onclick="getCategory(this)"><i class="fa fa-music"></i> 乐器</a></li>
                                <li><a href="javascript:void(0);" id="second_cate608" type="608" onclick="getCategory(this)"><i class="fa fa-bell"></i> 音响</a></li>
                                <li><a href="javascript:void(0);" id="second_cate609" type="609" onclick="getCategory(this)"><i class="fa fa-headphones"></i> 耳机</a></li>
                                <li><a href="javascript:void(0);" id="second_cate610" type="610" onclick="getCategory(this)"><i class="fa fa-square"></i> U盘/硬盘</a></li>
                                <li><a href="javascript:void(0);" id="second_cate611" type="611" onclick="getCategory(this)"><i class="fa fa-music"></i> MP3/iPod/iWatch</a></li>
                                <li><a href="javascript:void(0);" id="second_cate612" type="612" onclick="getCategory(this)"><i class="fa fa-book"></i> 其他图书</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sechand_gadgets" class="nav-header menu-first collapsed" id="cate7" data-toggle="collapse" type="7" onclick="getCategory(this)">
                                <i class="fa fa-umbrella"></i>
                                <strong>生活用品</strong>
                            </a>
                            <ul id="sechand_gadgets" class="nav nav-list collapse menu-second">
                                <li><a href="javascript:void(0);" id="second_cate700" type="700" onclick="getCategory(this)"><i class="fa fa-lightbulb-o"></i> 电灯</a></li>
                                <li><a href="javascript:void(0);" id="second_cate701" type="701" onclick="getCategory(this)"><i class="fa fa-umbrella"></i> 雨伞</a></li>
                                <li><a href="javascript:void(0);" id="second_cate702" type="702" onclick="getCategory(this)"><i class="fa fa-circle-thin"></i> 梳子/镜子</a></li>
                                <li><a href="javascript:void(0);" id="second_cate703" type="703" onclick="getCategory(this)"><i class="fa fa-money"></i> 家具</a></li>
                                <li><a href="javascript:void(0);" id="second_cate704" type="704" onclick="getCategory(this)"><i class="fa fa-wheelchair"></i> 桌椅板凳</a></li>
                                <li><a href="javascript:void(0);" id="second_cate705" type="705" onclick="getCategory(this)"><i class="fa fa-stop"></i> 垫褥床铺</a></li>
                                <li><a href="javascript:void(0);" id="second_cate706" type="706" onclick="getCategory(this)"><i class="fa fa-coffee"></i> 茶杯/水杯</a></li>
                                <li><a href="javascript:void(0);" id="second_cate707" type="707" onclick="getCategory(this)"><i class="fa fa-child"></i> 布偶/娃娃</a></li>
                                <li><a href="javascript:void(0);" id="second_cate708" type="708" onclick="getCategory(this)"><i class="fa fa-plus-square"></i> 其他生活用品</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sechand_sports" class="nav-header menu-first collapsed" id="cate8" data-toggle="collapse" type="8" onclick="getCategory(this)">
                                <i class="fa fa-soccer-ball-o"></i>
                                <strong>体育器材</strong>
                            </a>
                            <ul id="sechand_sports" class="nav nav-list collapse menu-second">
                                <li><a href="javascript:void(0);" id="second_cate800" type="800" onclick="getCategory(this)"><i class="fa fa-male"></i> 健身器材</a></li>
                                <li><a href="javascript:void(0);" id="second_cate801" type="801" onclick="getCategory(this)"><i class="fa fa-circle"></i> 网球</a></li>
                                <li><a href="javascript:void(0);" id="second_cate802" type="802" onclick="getCategory(this)"><i class="fa fa-soccer-ball-o"></i> 足球</a></li>
                                <li><a href="javascript:void(0);" id="second_cate803" type="803" onclick="getCategory(this)"><i class="fa fa-circle"></i> 篮球</a></li>
                                <li><a href="javascript:void(0);" id="second_cate804" type="804" onclick="getCategory(this)"><i class="fa fa-circle"></i> 乒乓球</a></li>
                                <li><a href="javascript:void(0);" id="second_cate805" type="805" onclick="getCategory(this)"><i class="fa fa-circle"></i> 羽毛球</a></li>
                                <li><a href="javascript:void(0);" id="second_cate806" type="806" onclick="getCategory(this)"><i class="fa fa-tree"></i> 户外用品</a></li>
                                <li><a href="javascript:void(0);" id="second_cate807" type="807" onclick="getCategory(this)"><i class="fa fa-tint"></i> 游泳用品</a></li>
                                <li><a href="javascript:void(0);" id="second_cate808" type="808" onclick="getCategory(this)"><i class="fa fa-cogs"></i> 其他体育用品</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sechand_others" class="nav-header menu-first collapsed" id="cate9" data-toggle="collapse" type="9" onclick="getCategory(this)">
                                <i class="fa fa-tags"></i>
                                <strong>未分类</strong>
                            </a>
                        </li>
                    </ul>
                </nav>

            </div>
        </div>

        <div class="content-container">

            <div id="conditions_field">
                <ol class="breadcrumb">
                    <li><a style="color: #808080; text-decoration: none;" id="bread_first_cate"></a></li>
                    <li id="bread_second_cate" style="color: #e4393c;"></li>
                    <!--<div class="crumbs-nav-item" style="display: none;" id="crumbs_nav_item">-->
                    <li class="selector-set" id="bread_box">
                        <a href="#" class="ss-item" style="display: none;" id="ss_item_price">
                            <b>价格：</b>
                            <em id="em_price"></em>
                            <i class="fa fa-remove" id="btn_del_price"></i>
                        </a>

                        <a href="#" class="ss-item" style="display: none;" id="ss_item_quality">
                            <b>新旧：</b>
                            <em id="em_quality"></em>
                            <i class="fa fa-remove" id="btn_del_quality"></i>
                        </a>

                        <a href="#" class="ss-item" style="display: none;" id="ss_item_campus">
                            <b>校区：</b>
                            <em id="em_campus"></em>
                            <i class="fa fa-remove" id="btn_del_campus"></i>
                        </a>
                    </li>
              </ol>
                <!--</div>-->

                <div class="container">
                    <div id="conditions_field_top" class="row">
                        <div id="bt-lists" class="col-xs-2 col-sm-3">
                            <a href="#" class="ico open-list">
                                <span class="hidden-xs open-list" id="sort_text"></span>
                            </a>
                            <a id="bt_sort" href="javascript:void(0);" class="open-list pull-right hidden-xs"><span class="fa fa-angle-down fleche-sort"></span></a>
                        </div>

                        <div class="col-xs-10 col-sm-7">
                            <a class="pull-left condition-head" href="#">条件检索</a>
                            <a id="bt-trier" href="javascript:void(0);" class="fleche-menu pull-right"><span class="fa fa-angle-down fleche-filtre"></span></a>
                        </div>

                        <div id="nb-products" class="col-xs-0 col-sm-2 text-right hidden-xs">
                            <span>共${size}</span>
                        </div>
                    </div>

                    <div id="sort_field_filters" class="row">
                        <div id="sort" style="display: none; width: 25%; margin-top: 0px; padding-bottom: 10px; margin-bottom: 0px; background-color: #fff">
                            <div class="clearfix sort-divider">
                                <a href="javascript:void(0)" class="btn-sort btn-active" id="sort_by_time" onclick="sort(this)" type="0"><span><i class="fa fa-clock-o"></i> 时间</span></a>
                            </div>
                            <div class="clearfix sort-divider">
                                <a href="javascript:void(0)" class="btn-sort" id="sort_by_price" onclick="sort(this)" type="1"><span><i class="fa fa-money"></i> 价格</span></a>
                            </div>
                            <div class="clearfix sort-divider">
                                <a href="javascript:void(0)" class="btn-sort" id="sort_by_hot" onclick="sort(this)" type="2"><span><i class="fa fa-fire"></i> 热度</span></a>
                            </div>
                        </div>
                    </div>

                    <div id="conditions_field_filters" class="row">

                            <div id="filters" style="display: none; margin-top: 0px; margin-bottom: 0px;">
                                <div class="clearfix condition-item-field" id="sort_ways">
                                    <label class="text-right field">排序</label>
                                    <a href="javascript:void(0);" class="btn btn-neeed btn-active" id="small_sort_by_time" onclick="sort(this)" type="0"><span><i class="fa fa-clock-o"></i> 时间</span></a>
                                    <a href="javascript:void(0);" class="btn btn-neeed " id="small_sort_by_price" onclick="sort(this)" type="1"><span><i class="fa fa-money"></i> 价格</span></a>
                                    <a href="javascript:void(0);" class="btn btn-neeed " id="small_sort_by_hot" onclick="sort(this)" type="2"><span><i class="fa fa-fire"></i> 热度</span></a>
                                </div>
                                <div class="clearfix condition-item-field">
                                    <label class="text-right field">价格</label>
                                    <a class="btn btn-neeed " id="price0" price="0" href="javascript:Filter('price','0');">不限</a>
                                    <a class="btn btn-neeed " id="price1" price="1" href="javascript:Filter('price','1');">10元以下</a>
                                    <a class="btn btn-neeed " id="price2" price="2" href="javascript:Filter('price','2');">10-50元</a>
                                    <a class="btn btn-neeed " id="price3" price="3" href="javascript:Filter('price','3');">50-100元</a>
                                    <a class="btn btn-neeed " id="price4" price="4" href="javascript:Filter('price','4');">100-200元</a>
                                    <a class="btn btn-neeed " id="price5" price="5" href="javascript:Filter('price','5');">200-400元</a>
                                    <a class="btn btn-neeed " id="price6" price="6" href="javascript:Filter('price','6');">400-600元</a>
                                    <a class="btn btn-neeed " id="price7" price="7" href="javascript:Filter('price','7');">600-800元</a>
                                    <a class="btn btn-neeed " id="price8" price="8" href="javascript:Filter('price','8');">800-1000元</a>
                                    <a class="btn btn-neeed " id="price9" price="9" href="javascript:Filter('price','9');">1000-2000元</a>
                                    <a class="btn btn-neeed " id="price10" price="10" href="javascript:Filter('price','10');">2000元以上</a>
                                </div>
                                <div class="clearfix condition-item-field">
                                    <label class="text-right field">新旧</label>
                                    <a class="btn btn-neeed " id="quality0" quality="0" href="javascript:Filter('quality', '0')">不限</a>
                                    <a class="btn btn-neeed " id="quality1" quality="1" href="javascript:Filter('quality', '1')">全新</a>
                                    <a class="btn btn-neeed " id="quality2" quality="2" href="javascript:Filter('quality', '2')">九成新</a>
                                    <a class="btn btn-neeed " id="quality3" quality="3" href="javascript:Filter('quality', '3')">八成新</a>
                                    <a class="btn btn-neeed " id="quality4" quality="4" href="javascript:Filter('quality', '4')">七成新</a>
                                    <a class="btn btn-neeed " id="quality5" quality="5" href="javascript:Filter('quality', '5')">六成新</a>
                                    <a class="btn btn-neeed " id="quality6" quality="6" href="javascript:Filter('quality', '6')">五成新</a>
                                    <a class="btn btn-neeed " id="quality7" quality="7" href="javascript:Filter('quality', '7')">五成新以下</a>
                                </div>
                                <div class="clearfix condition-item-field">
                                    <label class="text-right field">校区</label>
                                    <a class="btn btn-neeed " id="campus0" campus="0" href="javascript:Filter('campus', '0')">不限</a>
                                    <a class="btn btn-neeed " id="campus1" campus="1" href="javascript:Filter('campus', '1')">闵行校区</a>
                                    <a class="btn btn-neeed " id="campus2" campus="2" href="javascript:Filter('campus', '2')">徐汇校区</a>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>

            <div class="row p-row">

					<c:forEach var="result" items="${items}">
					<c:if test='${not empty result["IId"]}'>  
						<div class="col-md-4 col-sm-6 col-xs-12 site">
							<a href='<%=path %>/detail/getdetail?itemID=${result["IId"]}'
								target="_blank" class="site-thumb">
								<div class="site-metrics">
									<div class="inner">
										<div class="site-stats">
											<span><i class="fa fa-link"></i>
											</span>
										</div>
									</div>
								</div>
								<div class="img-box">
								<c:if test="${not empty itemphoto[result['IId']] }">
									<img src='<%=path %>/${itemphoto[result["IId"]]}' class="img-responsive"
										alt="">
								</c:if>
								<c:if test="${ empty itemphoto[result['IId']] }">
									<img src='<%=path %>/images/noPic.png' class="img-responsive"
										alt="">
								</c:if>
								</div> </a>
							<div class="site-details">
								<span class="site-price"><p>${result["IPrice"]}</p>
								</span> <span class="site-title"><a
									href='<%=path %>/detail/getdetail?itemID=${result["IId"]}'>${result["IName"]
										}</a>
								</span>
								<p class="p-inline">
									<span class="site-credit left">来自于<strong>${result["IPlace"]}
									</strong>
									</span> <span class="site-date right">${result["IDate"] }</span>
								</p>
							</div>
						</div>
						</c:if>
					</c:forEach>					
            </div>
            
            <nav class="nav-pagination">
                <ul class="pagination myPagination" id="myPagination">
                    <li>
                        <a href="javascript:void(0);" aria-label="Previous" id="prevPage" onclick="prevPagination()">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
						<li><a href="javascript:void(0);" class="page-active"
							onclick="pagination(this)">1</a>
						</li>
						<c:set var="count" value="1"/>
						<c:if test="${page >= 2}">
							<c:if test="${page>currentpage+3 and currentpage-3> 2}">
							<li><span>...</span></li>
							<c:forEach begin="${currentpage-3 }" end="${currentpage+3}" var="i">
								<li><a href="javascript:void(0);"
									onclick="pagination(this)">${i }</a>
								</li>
							</c:forEach>
							<li><span>...</span></li>
							<li><a href="javascript:void(0);"
									onclick="pagination(this)">${page}</a>
								</li>
							</c:if>
							<c:if test="${page>currentpage+3 and currentpage-3<= 2}">
							<c:forEach begin="2" end="${currentpage+3}" var="i">
								<li><a href="javascript:void(0);"
									onclick="pagination(this)">${i }</a>
								</li>
							</c:forEach>
							<li><span>...</span></li>
							<li><a href="javascript:void(0);"
									onclick="pagination(this)">${page}</a>
								</li>
							</c:if>
							<c:if test="${page<=currentpage+3 and currentpage-3> 2}">
							<li><span>...</span></li>
							<c:forEach begin="${currentpage-3 }" end="${page}" var="i">
								<li><a href="javascript:void(0);"
									onclick="pagination(this)">${i }</a>
								</li>
							</c:forEach>
							</c:if>
							<c:if test="${page<=currentpage+3 and currentpage-3<= 2}">
							<c:forEach begin="2" end="${page}" var="i">
								<li><a href="javascript:void(0);"
									onclick="pagination(this)">${i }</a>
								</li>
							</c:forEach>
							</c:if>
					</c:if>
						<li>
						<a href="javascript:void(0);" aria-label="Next" id="nextPage"
							onclick="nextPagination(5)"> <span aria-hidden="true">&raquo;</span>
						</a>
						</li>
                    </li>
                </ul>
          </nav>

        </div>
    </div>
</div>

<hr class="divider">

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

<script type="text/javascript" src="<%=path %>/myjs/filter.js"></script>
<script type="text/javascript" src="<%=path %>/myjs/tips.js"></script>
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
