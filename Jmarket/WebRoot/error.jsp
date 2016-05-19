<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>404 not found</title>
     <link rel="shortcut icon" href="<%=path %>/images/favicon.ico" />
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet" type="text/css">
    
    <link href="<%=path %>/mycss/error.css" rel="stylesheet" type="text/css">
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<%=path %>/myjs/rotate.js"></script>
    <script>
        $(document).ready(function () {
            $("#btn_circle").rotate();
        });
    </script>
</head>
<body>
<div class="fluid">
    <div class="wrapper wrapper_navbar_navbar at-top">
        <div class="col-md-12 error-div">
            <div class="btn-circle" id="btn_circle">
                <p><span class="icon"><i class="fa fa-meh-o"></i></span></p>
                <p><span class="error-404">404</span></p>
            </div>
            <hr>
            <div class="oops">
                <p class="sorry">很抱歉哦！您所请求的页面不存在呢！</p>
                <p class="reasons">可能得的原因:</p>
                <ul>
                    <li>
                        网络连接已断开，请检查一下您的网络连接状态
                    </li>
                    <li>
                        错误的请求链接，请键入正确的链接地址
                    </li>
                    <li>
                        页面不存在，我们的错喽！
                    </li>
                </ul>
            </div>
            <br>
            <div class="back">
                <a href="<%=path %>/search/main" class="back-to-mainpage">返回主页</a>
            </div>
            <hr>
        </div>
    </div>
</div>

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
</body>
</html>