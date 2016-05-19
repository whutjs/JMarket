<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>JMarket注册</title>
     <link rel="shortcut icon" href="<%=path %>/images/favicon.ico" />
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/font-awesome.min.css"  rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/register.css" rel="stylesheet" type="text/css">
    <!--<link href="<%=path %>/mycss/animation-bg.css" rel="stylesheet" type="text/css">-->
    <link href="<%=path %>/mycss/navbar.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/sidebar.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/footer.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/background.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/flexslider.css" rel="stylesheet" type="text/css">
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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
                    <li><a href="<%=path %>/login.jsp" id="nav_login">登录 <span class="sr-only">(current)</span></a></li>
                    <li><a href="<%=path %>/register.jsp" id="nav_register">注册</a></li>
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

      <div class="father-container">
        <div class="sidebar-container">
            <div class="sidebar-menu">
                <nav>
                    <ul class="mcd-menu">
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main'" class="nav-header menu-first active" data-toggle="collapse">
                                <i class="fa fa-circle-o"></i>
                                <strong>全部</strong>
                            </a>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=1'" class="nav-header menu-first collapsed" data-toggle="collapse">
                                <i class="fa fa-bicycle"></i>
                                <strong>二手车</strong>
                            </a>
                            <ul id="sechand_bycles" class="nav nav-list collapse menu-second">
                                <li><a href="<%=path%>/search/main?first_cate=1&secend_cate=100"><i class="fa fa-bicycle"></i> 普通自行车</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=1&secend_cate=101"><i class="fa fa-bicycle"></i> 山地自行车</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=1&secend_cate=102"><i class="fa fa-bicycle"></i> 折叠自行车</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=1&secend_cate=103"><i class="fa fa-bicycle"></i> 迷你自行车</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=1&secend_cate=104"><i class="fa fa-bicycle"></i> 公路自行车</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=1&secend_cate=105"><i class="fa fa-bicycle"></i> 电动车</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=1&secend_cate=106"><i class="fa fa-car"></i> 其他车</a></li>
                            </ul>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=2'" class="nav-header menu-first collapsed" data-toggle="collapse" aria-expanded="false">
                                <i class="fa fa-mobile-phone" style="width: 30px;height: 30px;"></i>
                                <strong>二手手机</strong>
                            </a>
                            <ul id="sechand_phone" class="nav nav-list collapse menu-second">
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=200"><i class="fa fa-apple"></i> 苹果</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=201"><i class="fa fa-mobile"></i> 三星</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=202"><i class="fa fa-android"></i> 小米</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=203"><i class="fa fa-mobile"></i> 华为</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=204"><i class="fa fa-mobile"></i> HTC</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=205"><i class="fa fa-windows"></i> 诺基亚</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=206"><i class="fa fa-mobile"></i> 魅族</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=207"><i class="fa fa-mobile"></i> 索尼</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=208"><i class="fa fa-mobile"></i> 中兴</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=209"><i class="fa fa-mobile"></i> LG</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=210"><i class="fa fa-windows"></i> 联想</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=211"><i class="fa fa-mobile"></i> 酷派</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=2&secend_cate=212"><i class="fa fa-mobile-phone"></i> 其他手机</a></li>

                            </ul>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=3'" class="nav-header menu-first collapsed" data-toggle="collapse">
                                <i class="fa fa-laptop"></i>
                                <strong>PC/PAD</strong>
                            </a>
                            <ul id="sechand_pc" class="nav nav-list collapse menu-second">
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=300"><i class="fa fa-desktop"></i> 台式机</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=301"><i class="fa fa-laptop"></i> ThinkPad/IBM</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=302"><i class="fa fa-apple"></i> 苹果(Mac)</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=303"><i class="fa fa-laptop"></i> 联想(Lenovo)</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=304"><i class="fa fa-laptop"></i> 戴尔(DELL)</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=305"><i class="fa fa-laptop"></i> 华硕(ASUS)</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=306"><i class="fa fa-laptop"></i> 惠普(HP)</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=307"><i class="fa fa-laptop"></i> 索尼(Sony)</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=308"><i class="fa fa-laptop"></i> 三星(Samsung)</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=309"><i class="fa fa-laptop"></i> 其他电脑</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=310"><i class="fa fa-tablet"></i> iPad</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=311"><i class="fa fa-tablet"></i> Surface</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=312"><i class="fa fa-tablet"></i> 三星平板</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=313"><i class="fa fa-tablet"></i> 小米平板</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=314"><i class="fa fa-tablet"></i> 联想平板</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=3&secend_cate=315"><i class="fa fa-tablet"></i> 其他平板</a></li>
                            </ul>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=4'" class="nav-header menu-first collapsed" data-toggle="collapse">
                                <i class="fa fa-plug"></i>
                                <strong>二手家电</strong>
                            </a>
                            <ul id="sechand_home_applications" class="nav nav-list collapse menu-second">
                                <li><a href="<%=path%>/search/main?first_cate=4&secend_cate=400"><i class="fa fa-plug"></i> 洗衣机</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=4&secend_cate=401"><i class="fa fa-plug"></i> 厨房电器</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=4&secend_cate=402"><i class="fa fa-plug"></i> 冰箱/冰柜</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=4&secend_cate=403"><i class="fa fa-camera"></i> 数码相机</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=4&secend_cate=404"><i class="fa fa-plug"></i> 其他家电</a></li>
                            </ul>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=5'" class="nav-header menu-first collapsed" data-toggle="collapse">
                                <i class="fa fa-suitcase"></i>
                                <strong>服装包箱</strong>
                            </a>
                            <ul id="sechand_clothes" class="nav nav-list collapse menu-second">
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=500"><i class="fa fa-user-md"></i> T恤</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=501"><i class="fa fa-user"></i> 衬衫</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=502"><i class="fa fa-user-md"></i> 外套</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=503"><i class="fa fa-user-md"></i> 裤子</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=504"><i class="fa fa-user-md"></i> 西装</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=505"><i class="fa fa-user-md"></i> 裙子</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=506"><i class="fa fa-list"></i> 休闲鞋</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=507"><i class="fa fa-list"></i> 运动鞋</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=508"><i class="fa fa-list"></i> 帆布鞋</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=509"><i class="fa fa-list"></i> 高跟鞋</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=510"><i class="fa fa-list"></i> 皮鞋</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=511"><i class="fa fa-briefcase"></i> 单肩包</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=512"><i class="fa fa-briefcase"></i> 双肩包</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=513"><i class="fa fa-briefcase"></i> 书包</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=514"><i class="fa fa-briefcase"></i> 钱包</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=515"><i class="fa fa-suitcase"></i> 电脑包</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=516"><i class="fa fa-briefcase"></i> 箱子</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=5&secend_cate=517"><i class="fa fa-list"></i> 其他服饰</a></li>
                            </ul>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=6'" class="nav-header menu-first collapsed" data-toggle="collapse">
                                <i class="fa fa-book"></i>
                                <strong>图书音像</strong>
                            </a>
                            <ul id="sechand_book_and_cd" class="nav nav-list collapse menu-second">
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=600"><i class="fa fa-book"></i> 专业书籍</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=601"><i class="fa fa-book"></i> 考试(GRE/托福/雅思)</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=602"><i class="fa fa-book"></i> 小说/文学</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=603"><i class="fa fa-book"></i> 工具书/辅导书</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=604"><i class="fa fa-book"></i> 生活类书籍</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=605"><i class="fa fa-calculator"></i> 学生文具</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=606"><i class="fa fa-tablet"></i> 学习机</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=607"><i class="fa fa-music"></i> 乐器</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=608"><i class="fa fa-bell"></i> 音响</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=609"><i class="fa fa-headphones"></i> 耳机</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=610"><i class="fa fa-square"></i> U盘/硬盘</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=611"><i class="fa fa-music"></i> MP3/iPod/iWatch</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=6&secend_cate=612"><i class="fa fa-book"></i> 其他图书</a></li>
                            </ul>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=7'" class="nav-header menu-first collapsed" data-toggle="collapse">
                                <i class="fa fa-umbrella"></i>
                                <strong>生活用品</strong>
                            </a>
                            <ul id="sechand_gadgets" class="nav nav-list collapse menu-second">
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=700"><i class="fa fa-lightbulb-o"></i> 电灯</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=701"><i class="fa fa-umbrella"></i> 雨伞</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=702"><i class="fa fa-circle-thin"></i> 梳子/镜子</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=703"><i class="fa fa-money"></i> 家具</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=704"><i class="fa fa-wheelchair"></i> 桌椅板凳</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=705"><i class="fa fa-stop"></i> 垫褥床铺</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=706"><i class="fa fa-coffee"></i> 茶杯/水杯</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=707"><i class="fa fa-child"></i> 布偶/娃娃</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=7&secend_cate=708"><i class="fa fa-plus-square"></i> 其他生活用品</a></li>
                            </ul>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=8'" class="nav-header menu-first collapsed" data-toggle="collapse">
                                <i class="fa fa-soccer-ball-o"></i>
                                <strong>体育器材</strong>
                            </a>
                            <ul id="sechand_sports" class="nav nav-list collapse menu-second">
                                <li><a href="<%=path%>/search/main?first_cate=8&second_cate=800"><i class="fa fa-male"></i> 健身器材</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=8&secend_cate=801"><i class="fa fa-circle"></i> 网球</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=8&secend_cate=802"><i class="fa fa-soccer-ball-o"></i> 足球</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=8&secend_cate=803"><i class="fa fa-circle"></i> 篮球</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=8&secend_cate=804"><i class="fa fa-circle"></i> 乒乓球</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=8&secend_cate=805"><i class="fa fa-circle"></i> 羽毛球</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=8&secend_cate=806"><i class="fa fa-tree"></i> 户外用品</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=8&secend_cate=807"><i class="fa fa-tint"></i> 游泳用品</a></li>
                                <li><a href="<%=path%>/search/main?first_cate=8&second_cate=808"><i class="fa fa-cogs"></i> 其他体育用品</a></li>
                            </ul>
                        </li>
                        <li>
                            <a onclick="window.location.href='<%=path%>/search/main?first_cate=9'" class="nav-header menu-first collapsed" data-toggle="collapse">
                                <i class="fa fa-tags"></i>
                                <strong>未分类</strong>
                            </a>
                        </li>
                    </ul>
                </nav>

            </div>
        </div>
        <div class="content-container">
            <ul class="breadcrumb">
                <li>
                    <a href="#" class="breadcrumb-first-level">主页</a>
                </li>
                <li class="breadcrumb-second-level">注册账户</li>
            </ul>
            <div class="col-md-12">
                <h1 class="heading1">
                    <span class="maintext">邮箱注册</span>
                    <span class="subtext">例：xxx@edu.sjtu.cn</span>
                </h1>
                <form class="register-form" id="register_form" action="<%=path %>/check/index" method="post">
                    <!-- progressbar -->
                    <ul id="progressbar">
                        <li class="active">填写信息</li>
                        <li class="active">填写验证码</li>
                        <li>完成注册</li>
                    </ul>
                    <!-- fieldsets -->
                    <!--<fieldset style="opacity: 1; display: block;">-->
                        <!--<div class="input-group" style="text-align: center;margin: auto;">-->
                            <!--<img class="sjtu-logo" src="<%=path %>/images/sjtu.png">-->
                        <!--</div>-->
                        <!--<div class="input-group input-group-line">-->
                            <!--<span class="input-group-addon" id="basic-addon1"><i class="fa fa-user"></i></span>-->
                            <!--<input type="email" class="form-control register-input" id="input_email" placeholder="交大邮箱" aria-describedby="basic-addon1">-->
                        <!--</div>-->
                        <!--<div class="input-group input-group-line">-->
                            <!--<span class="input-group-addon" id="basic-addon2"><i class="fa fa-lock"></i></span>-->
                            <!--<input type="password" class="form-control register-input" id="input_password" placeholder="您的密码" aria-describedby="basic-addon2">-->
                        <!--</div>-->
                        <!--<div class="input-group input-group-line">-->
                            <!--<span class="input-group-addon" id="basic-addon3"><i class="fa fa-lock"></i></span>-->
                            <!--<input type="password" class="form-control register-input" id="input_password_again" placeholder="确认密码" aria-describedby="basic-addon3">-->
                        <!--</div>-->
                        <!--<input type="button" name="next" class="next action-button" id="next_button" value="下一步">-->
                        <!--<div class="form-group" style="float: right;">-->
                            <!--已有账号，请<a href="#" id="login">登录</a>-->
                        <!--</div>-->
                        <!--<input type="button" name="next" class="next action-button" value="Next" style="display: none">-->
                    <!--</fieldset>-->
                    <fieldset style="opacity: 1; display: block;">
                        <h3 class="fs-subtitle">验证码</h3>
                        <input type="text" class="code" name="code" placeholder="邮箱验证码" id="code">
                        <p id="err_code_tip" style="display: none; text-align: left;margin-top: 10px;"></p>
                        <s:actionerror/>
                        <!-- style="text-align: left;margin-top: 10px;color:red; margin-left:0px; margin-right:0px; font-size:14px" -->
                        <style>
                        .errorMessage{
                          padding:0 !important;
                        }}
                        </style>
                        <input type="submit" name="next" class="next action-button btn-disabled" value="提交" id="submit_code" disabled="disabled">
                        <input type="button" name="previous" class="previous action-button" value="上一步" onclick="location.href='register.jsp'">
                    </fieldset>
                    <!--<fieldset style="opacity: 0; display: none;" class="success">-->
                        <!--<h2 class="fs-title congratulate">恭喜您！已成功注册</h2>-->
                    <!--</fieldset>-->
                </form>
            </div>
        </div>
    </div>

    <ul class="bg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>

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

<script type="text/javascript" src="<%=path %>/myjs/filter.js"></script>
<script type="text/javascript" src="<%=path %>/myjs/tips.js"></script>
<script type="text/javascript" src="<%=path %>/myjs/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="<%=path %>/myjs/jquery.easing.min.js"></script>
<script type="text/javascript" src="<%=path %>/myjs/registerSkip.js"></script>
<script type="text/javascript" src="<%=path %>/myjs/verifyCode.js"></script>
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
            content: '<div id="popOverBox"><img src="<%=path %>/images/sample/sample1.jpg" width="100" height="80" /></div>',
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
            content: '<div id="popOverBox"><img src="<%=path %>/images/sample/sample2.png" width="100" height="80" /></div>',
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
<script type="text/javascript">
    $(window).load(function() {
        // The slider being synced must be initialized first
        $('#carousel').flexslider({
            animation: "slide",
            controlNav: false,
            animationLoop: false,
            slideshow: false,
            itemWidth: 120,
            itemMargin: 5,
            asNavFor: '#slider'
        });

        $('#slider').flexslider({
            animation: "slide",
            controlNav: false,
            animationLoop: false,
            slideshow: false,
            sync: "#carousel"
        });
    });
</script>
<script type="text/javascript">
    $('#login-button').click(function(event){
        event.preventDefault();
        $('form').fadeOut(1000);
        $('.wrapper').addClass('form-success');
    });
</script>
</body>
</html>