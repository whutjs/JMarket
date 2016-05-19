<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
        　<script type="text/javascript" src="<%= path %>/uploadify/jquery-1.8.0.min.js"></script>  
    <script type="text/javascript" src="<%= path %>/uploadify/jquery.uploadify.min.js"></script>  
    <link rel="stylesheet" type="text/css" href="<%= path %>/uploadify/uploadify.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
        $(document).ready(function(){
            $("#imageify0").uploadify({
                'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
                 method : 'get',            //以get方式上传                
                'buttonText':'上传',        //上传按钮的文字
                'fileTypeDesc':'Image Files',    //可上传文件格式的描述
                'fileTypeExts':'*.gif; *.jpg; *.png',    //可上传的文件格式 
                'auto':true,    //选择一个文件是否自动上传
                'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
                 swf : 'uploadify/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
                 uploader : 'avatar.action',                //服务器响应地址
                'formData' : {'name':'lmy'},        //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
                'onUploadStart' : function(file) {    //上传前触发的事件
              
                } ,
                'onUploadSuccess' : function(file, data, response) {    //上传成功后触发的事件          
                 var img = document.getElementById('img0');  
                 console.log(data);
                 //var obj = data.parseJSON();
                 testJson = eval("(" + data + ")");         
                 img.src = testJson.path;       
                }
            });
            
        });
        
    </script>
  </head>
  
  <body>
    <div id="queue"></div> 
	<input id="imageify0" name="imageify" type="file"/>
	<div>
	  <img src='' id='img0' style='width:500px;'>
	</div> 
  </body>
</html>
