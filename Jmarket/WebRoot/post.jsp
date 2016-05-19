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
    <title>发布</title>
     <link rel="shortcut icon" href="<%=path %>/images/favicon.ico" />
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/font-awesome.min.css"  rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/login.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/navbar.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/footer.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/post.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/mycss/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <link href="<%=path %>/mycss/fileinput/default.css" media="all" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%= path %>/uploadify/uploadify.css">
    <link rel="stylesheet" href="<%=path %>/mycss/Player/app.css" type="text/css">
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="<%= path %>/uploadify/jquery.uploadify.min.js"></script> 
    <script src="<%=path %>/myjs/post.js"></script>
    <script src="<%=path %>/myjs/fileinput/fileinput.js"></script>
    <script src="<%=path %>/myjs/fileinput/fileinput_locale_zh.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<%=path %>/myjs/picture.js"></script>
    
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
        <form class="post-form" id="post_form" name="post_form" action="<%=path%>/post/postItem">
            <!-- progressbar -->
            <ul id="progressbar">
                <li id="pro1" class="active">选择分类</li>
                <li id="pro2">填写明细</li>
                <li id="pro3">完成发布</li>
            </ul>
            <fieldset id="field_types" style="opacity: 1; display: block;">
                <ul class="all-types">
                    <div class="row">
                        <div class="col-md-4">
                            <li>
                                <h5>二手车</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="100" onclick="getCate('100')" >普通自行车</a></li>
                                    <li><a href="javascript:void(0);" type="101" onclick="getCate('101')">山地自行车</a></li>
                                    <li><a href="javascript:void(0);" type="102" onclick="getCate('102')">折叠自行车</a></li>
                                    <li><a href="javascript:void(0);" type="103" onclick="getCate('103')">迷你自行车</a></li>
                                    <li><a href="javascript:void(0);" type="104" onclick="getCate('104')">公路自行车</a></li>
                                    <li><a href="javascript:void(0);" type="105" onclick="getCate('105')">电动车</a></li>
                                    <li><a href="javascript:void(0);" type="106" onclick="getCate('106')">其他车</a></li>
                                </ul>
                            </li>
                            <li>
                                <h5>二手手机</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="200" onclick="getCate('200')">苹果</a></li>
                                    <li><a href="javascript:void(0);" type="201" onclick="getCate('201')">三星</a></li>
                                    <li><a href="javascript:void(0);" type="202" onclick="getCate('202')">小米</a></li>
                                    <li><a href="javascript:void(0);" type="203" onclick="getCate('203')">华为</a></li>
                                    <li><a href="javascript:void(0);" type="204" onclick="getCate('204')">HTC</a></li>
                                    <li><a href="javascript:void(0);" type="205" onclick="getCate('205')">诺基亚</a></li>
                                    <li><a href="javascript:void(0);" type="206" onclick="getCate('206')">魅族</a></li>
                                    <li><a href="javascript:void(0);" type="207" onclick="getCate('207')">索尼</a></li>
                                    <li><a href="javascript:void(0);" type="208" onclick="getCate('208')">中兴</a></li>
                                    <li><a href="javascript:void(0);" type="209" onclick="getCate('209')">LG</a></li>
                                    <li><a href="javascript:void(0);" type="210" onclick="getCate('210')">联想</a></li>
                                    <li><a href="javascript:void(0);" type="211" onclick="getCate('211')">酷派</a></li>
                                    <li><a href="javascript:void(0);" type="212" onclick="getCate('212')">其他手机</a></li>
                                </ul>
                            </li>
                            <li>
                                <h5>PC/PAD</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="300" onclick="getCate('300')">台式机</a></li>
                                    <li><a href="javascript:void(0);" type="301" onclick="getCate('301')">ThinkPad/IBM</a></li>
                                    <li><a href="javascript:void(0);" type="302" onclick="getCate('302')">苹果(Mac)</a></li>
                                    <li><a href="javascript:void(0);" type="303" onclick="getCate('303')">联想(Lenovo)</a></li>
                                    <li><a href="javascript:void(0);" type="304" onclick="getCate('304')">戴尔(DELL)</a></li>
                                    <li><a href="javascript:void(0);" type="305" onclick="getCate('305')">华硕(ASUS)</a></li>
                                    <li><a href="javascript:void(0);" type="306" onclick="getCate('306')">惠普(HP)</a></li>
                                    <li><a href="javascript:void(0);" type="307" onclick="getCate('307')">索尼(Sony)</a></li>
                                    <li><a href="javascript:void(0);" type="308" onclick="getCate('308')">三星(Samsung)</a></li>
                                    <li><a href="javascript:void(0);" type="309" onclick="getCate('309')">其他电脑</a></li>
                                    <li><a href="javascript:void(0);" type="310" onclick="getCate('310')">iPad</a></li>
                                    <li><a href="javascript:void(0);" type="311" onclick="getCate('311')">Surface</a></li>
                                    <li><a href="javascript:void(0);" type="312" onclick="getCate('312')">三星平板</a></li>
                                    <li><a href="javascript:void(0);" type="313" onclick="getCate('313')">小米平板</a></li>
                                    <li><a href="javascript:void(0);" type="314" onclick="getCate('314')">联想平板</a></li>
                                    <li><a href="javascript:void(0);" type="315" onclick="getCate('315')">其他平板</a></li>
                                </ul>
                            </li>
                        </div>
                        <div class="col-md-4">
                            <li>
                                <h5>二手家电</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="400" onclick="getCate('400')">洗衣机</a></li>
                                    <li><a href="javascript:void(0);" type="401" onclick="getCate('401')">厨房电器</a></li>
                                    <li><a href="javascript:void(0);" type="402" onclick="getCate('402')">冰箱/冰柜</a></li>
                                    <li><a href="javascript:void(0);" type="403" onclick="getCate('403')">数码相机</a></li>
                                    <li><a href="javascript:void(0);" type="404" onclick="getCate('404')">其他家电</a></li>
                                </ul>
                            </li>
                            <li>
                                <h5>服装包箱</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="500" onclick="getCate('500')">T恤</a></li>
                                    <li><a href="javascript:void(0);" type="501" onclick="getCate('501')">衬衫</a></li>
                                    <li><a href="javascript:void(0);" type="502" onclick="getCate('502')">外套</a></li>
                                    <li><a href="javascript:void(0);" type="503" onclick="getCate('503')">裤子</a></li>
                                    <li><a href="javascript:void(0);" type="504" onclick="getCate('504')">西装</a></li>
                                    <li><a href="javascript:void(0);" type="505" onclick="getCate('505')">裙子</a></li>
                                    <li><a href="javascript:void(0);" type="506" onclick="getCate('506')">休闲鞋</a></li>
                                    <li><a href="javascript:void(0);" type="507" onclick="getCate('507')">运动鞋</a></li>
                                    <li><a href="javascript:void(0);" type="508" onclick="getCate('508')">帆布鞋</a></li>
                                    <li><a href="javascript:void(0);" type="509" onclick="getCate('509')">高跟鞋</a></li>
                                    <li><a href="javascript:void(0);" type="510" onclick="getCate('510')">皮鞋</a></li>
                                    <li><a href="javascript:void(0);" type="511" onclick="getCate('511')">单肩包</a></li>
                                    <li><a href="javascript:void(0);" type="512" onclick="getCate('512')">双肩包</a></li>
                                    <li><a href="javascript:void(0);" type="513" onclick="getCate('513')">书包</a></li>
                                    <li><a href="javascript:void(0);" type="514" onclick="getCate('514')">钱包</a></li>
                                    <li><a href="javascript:void(0);" type="515" onclick="getCate('515')">电脑包</a></li>
                                    <li><a href="javascript:void(0);" type="516" onclick="getCate('516')">箱子</a></li>
                                    <li><a href="javascript:void(0);" type="517" onclick="getCate('517')">其他服饰</a></li>
                                </ul>
                            </li>
                            <li>
                                <h5>图书音像</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="600" onclick="getCate('600')">专业书籍</a></li>
                                    <li><a href="javascript:void(0);" type="601" onclick="getCate('601')">考试(GRE/托福/雅思)</a></li>
                                    <li><a href="javascript:void(0);" type="602" onclick="getCate('602')">小说/文学</a></li>
                                    <li><a href="javascript:void(0);" type="603" onclick="getCate('603')">工具书/辅导书</a></li>
                                    <li><a href="javascript:void(0);" type="604" onclick="getCate('604')">生活类书籍</a></li>
                                    <li><a href="javascript:void(0);" type="605" onclick="getCate('605')">学生文具</a></li>
                                    <li><a href="javascript:void(0);" type="606" onclick="getCate('606')">学习机</a></li>
                                    <li><a href="javascript:void(0);" type="607" onclick="getCate('607')">乐器</a></li>
                                    <li><a href="javascript:void(0);" type="608" onclick="getCate('608')">音响</a></li>
                                    <li><a href="javascript:void(0);" type="609" onclick="getCate('609')">耳机</a></li>
                                    <li><a href="javascript:void(0);" type="610" onclick="getCate('610')">U盘/硬盘</a></li>
                                    <li><a href="javascript:void(0);" type="611" onclick="getCate('611')">MP3/iPod/iWatch</a></li>
                                    <li><a href="javascript:void(0);" type="612" onclick="getCate('612')">其他图书</a></li>
                                </ul>
                            </li>
                        </div>
                        <div class="col-md-4">
                            <li>
                                <h5>生活用品</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="700" onclick="getCate('700')">电灯</a></li>
                                    <li><a href="javascript:void(0);" type="701" onclick="getCate('701')">雨伞</a></li>
                                    <li><a href="javascript:void(0);" type="702" onclick="getCate('702')">梳子/镜子</a></li>
                                    <li><a href="javascript:void(0);" type="703" onclick="getCate('703')">家具</a></li>
                                    <li><a href="javascript:void(0);" type="704" onclick="getCate('704')">桌椅板凳</a></li>
                                    <li><a href="javascript:void(0);" type="705" onclick="getCate('705')">垫褥床铺</a></li>
                                    <li><a href="javascript:void(0);" type="706" onclick="getCate('706')">茶杯/水杯</a></li>
                                    <li><a href="javascript:void(0);" type="707" onclick="getCate('707')">布偶/娃娃</a></li>
                                    <li><a href="javascript:void(0);" type="708" onclick="getCate('708')">其他生活用品</a></li>
                                </ul>
                            </li>
                            <li>
                                <h5>体育器材</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="800" onclick="getCate('800')">健身器材</a></li>
                                    <li><a href="javascript:void(0);" type="802" onclick="getCate('801')">网球</a></li>
                                    <li><a href="javascript:void(0);" type="802" onclick="getCate('802')">足球</a></li>
                                    <li><a href="javascript:void(0);" type="803" onclick="getCate('803')">篮球</a></li>
                                    <li><a href="javascript:void(0);" type="804" onclick="getCate('804')">乒乓球</a></li>
                                    <li><a href="javascript:void(0);" type="805" onclick="getCate('805')">羽毛球</a></li>
                                    <li><a href="javascript:void(0);" type="806" onclick="getCate('806')">户外用品</a></li>
                                    <li><a href="javascript:void(0);" type="807" onclick="getCate('807')">游泳用品</a></li>
                                    <li><a href="javascript:void(0);" type="808" onclick="getCate('808')">其他体育用品</a></li>
                                </ul>
                            </li>
                            <li>
                                <h5>未分类</h5>
                                <ul class="secondary-types">
                                    <li><a href="javascript:void(0);" type="9" onclick="getCate('9')">未分类</a></li>
                                </ul>
                            </li>
                        </div>
                    </div>
                </ul>
            </fieldset>
            <fieldset id="field_detail" style="opacity: 0; display: none;">
                <div class="control-group">
                    <label class="control-label"><i style="color: red">*</i> 类别</label>
                    <div class="controls">
                        <a style="color: #333; font-size: 13px; text-decoration: none;" id="a_first_cate">
                         <a style="color: #e4393c; cursor: pointer;"id="a_second_cate" onclick="reselectType()" title='点击重新选择类别'></a>
                        </a>
                        <input type="hidden" value="" class="input-xlarge" id="first_cate" name="type1">
                        <input type="hidden" value="" class="input-xlarge" id="second_cate" name="type2">
                        <p class="help-block">*点击红字重新选择类别</p>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"><i style="color: red">*</i> 新旧</label>
                    <div class="controls ">
                        <select class="input-xlarge" id="quality" name="state">
                            <option value="0">-请选择-</option>
                            <option value="1">全新</option>
                            <option value="2">9成新</option>
                            <option value="3">8成新</option>
                            <option value="4">7成新</option>
                            <option value="5">6成新</option>
                            <option value="6">5成新</option>
                            <option value="7">5成新以下</option>
                        </select>
                        <p class="help-block" id="tip_quality">*必填</p>
                    </div>
                </div>

                <div class="control-group">
                   <label class="control-label"><i style="color: red">*</i> 价格</label>
                   <div class="controls">
                      <input type="number" class="input-xlarge price" min="0" placeholder="￥" id="price" value="" name="IPrice">
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
                        <input type="text" placeholder="6-50字" class="input-xlarge" maxlength="50" id="title" value="" name="IName">
                        <p class="help-block" id="tip_title">*6-50字，简要描述</p>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"><i style="color: red">*</i> 详情</label>
                    <div class="controls">
                        <div class="textarea">
                            <textarea placeholder="说明我们未提供的内容，如物品的细节之处，何时购入，是否在保修期等等，10-2000字" id="detail" maxlength="2000" name="IDescription"></textarea>
                        </div>
                        <p class="help-block" id="tip_detail">*说明我们未提供的内容，如物品的细节之处，何时购入，是否在保修期等等,10-2000字</p>
                    </div>
                </div>

                <div class="control-group">
                  <label class="control-label">图片</label>
                  <div class="controls">
                      <div class="row" style="margin-bottom: 15px;" id="row_btn_box">
                          <div class="col-md-2" id="div_box0">
                              <a class="file" id="a_img_0">
                                  <input id="imageify0" name="imageify" type="file" class="no-padding-left"/>
                                  <input type="hidden" id="deleted_imgs" value="" name="delete"/>
                                  <input type="hidden" id="count" value="1"/>
                              </a>
                          </div>
                          <div class="col-md-2" id="div_box1" style="display:none;">
                              <a class="file" id="a_img_1">
                                  <input id="imageify1" name="imageify" type="file" class="no-padding-left"/>
                              </a>
                          </div>
                          <div class="col-md-2" id="div_box2" style="display:none;">
                              <a class="file" id="a_img_2">
                                  <input id="imageify2" name="imageify" type="file" class="no-padding-left"/>
                              </a>
                          </div>
                          <div class="col-md-2" id="div_box3" style="display:none;">
                              <a class="file" id="a_img_3">
                                  <input id="imageify3" name="imageify" type="file" class="no-padding-left"/>
                              </a>
                          </div>
                          <div class="col-md-2" id="div_box4" style="display:none;">
                              <a class="file" id="a_img_4">
                                  <input id="imageify4" name="imageify" type="file" class="no-padding-left"/>
                              </a>
                          </div>
                          <div class="col-md-2" id="div_box5" style="display:none;">
                              <a class="file" id="a_img_5">
                                  <input id="imageify5" name="imageify" type="file" class="no-padding-left"/>
                              </a>
                          </div>
                      </div>
                      <div class="row" id="row_img_box">
                      </div>

                  </div>
                </div>

                <div class="control-group">
                <div class="controls">
                  <input type="submit"  class="btn btn-post" value="发布" id="btn_post">
                </div>
              </div>

            </fieldset>
            <fieldset id="field_success" style="opacity: 0; display: none;">
              <!--  <h2 class="fs-title congratulate">已成功发布物品</h2>
              <span id="jumpTo">5</span>秒后自动跳转到<a href="#">主页</a>-->
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


<script type="text/javascript" src="<%=path %>/myjs/jumpToMainpage.js"></script>
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
