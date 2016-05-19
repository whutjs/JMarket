/**
 * Created by Mattxue on 2016/1/6.
 */
$(document).ready(function () {
	$("#imageify0").uploadify({
        
        'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
         method : 'get',            //以get方式上传                
        'buttonText':'选择图一',        //上传按钮的文字
        'fileTypeDesc':'Image Files',    //可上传文件格式的描述
        'fileTypeExts':'*.gif; *.jpg; *.png',    //可上传的文件格式 
        'auto':true,    //选择一个文件是否自动上传
        'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
         swf : 'uploadify/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
         uploader : 'upload.action',                //服务器响应地址
        'formData' : {'name':'lmy'},        //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
        'onUploadStart' : function(file) {    //上传前触发的事件
      
        } ,
        'onUploadSuccess' : function(file, data, response) {    //上传成功后触发的事件          
        	selectPic(0);
         var img = document.getElementById('img0');  
         console.log(data);
         //var obj = data.parseJSON();
         testJson = eval("(" + data + ")");         
         img.src = testJson.path;
         img.setAttribute("value", testJson.name);
        }
    });
    
    $("#imageify1").uploadify({
        'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
         method : 'get',            //以get方式上传                
        'buttonText':'选择图二',        //上传按钮的文字
        'fileTypeDesc':'Image Files',    //可上传文件格式的描述
        'fileTypeExts':'*.gif; *.jpg; *.png',    //可上传的文件格式 
        'auto':true,    //选择一个文件是否自动上传
        'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
         swf : 'uploadify/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
         uploader : 'upload.action',                //服务器响应地址
        'formData' : {'name':'lmy'},        //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
        'onUploadStart' : function(file) {    //上传前触发的事件
        
           //在这里添加  $('#imageify').uploadify('cancel'); 可以取消上传
            //$("#imageify").uploadify("settings","formData",{'name':'lmy1'}); //动态指定参数
        } ,
        'onUploadSuccess' : function(file, data, response) {    //上传成功后触发的事件          
        	selectPic(1);
         var img = document.getElementById('img1');  
         console.log(data);
         //var obj = data.parseJSON();
         testJson = eval("(" + data + ")");         
         img.src = testJson.path;
         img.setAttribute("value", testJson.name);
        }
    });
    
    $("#imageify2").uploadify({
        'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
         method : 'get',            //以get方式上传                
        'buttonText':'选择图三',        //上传按钮的文字
        'fileTypeDesc':'Image Files',    //可上传文件格式的描述
        'fileTypeExts':'*.gif; *.jpg; *.png',    //可上传的文件格式 
        'auto':true,    //选择一个文件是否自动上传
        'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
         swf : 'uploadify/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
         uploader : 'upload.action',                //服务器响应地址
        'formData' : {'name':'lmy'},        //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
        'onUploadStart' : function(file) {    //上传前触发的事件
        
           //在这里添加  $('#imageify').uploadify('cancel'); 可以取消上传
            //$("#imageify").uploadify("settings","formData",{'name':'lmy1'}); //动态指定参数
        } ,
        'onUploadSuccess' : function(file, data, response) {    //上传成功后触发的事件          
        	selectPic(2);
         var img = document.getElementById('img2');  
         console.log(data);
         //var obj = data.parseJSON();
         testJson = eval("(" + data + ")");         
         img.src = testJson.path;
         img.setAttribute("value", testJson.name);
        }
    });
    
    $("#imageify3").uploadify({
        'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
         method : 'get',            //以get方式上传                
        'buttonText':'选择图四',        //上传按钮的文字
        'fileTypeDesc':'Image Files',    //可上传文件格式的描述
        'fileTypeExts':'*.gif; *.jpg; *.png',    //可上传的文件格式 
        'auto':true,    //选择一个文件是否自动上传
        'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
         swf : 'uploadify/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
         uploader : 'upload.action',                //服务器响应地址
        'formData' : {'name':'lmy'},        //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
        'onUploadStart' : function(file) {    //上传前触发的事件
        
           //在这里添加  $('#imageify').uploadify('cancel'); 可以取消上传
            //$("#imageify").uploadify("settings","formData",{'name':'lmy1'}); //动态指定参数
        } ,
        'onUploadSuccess' : function(file, data, response) {    //上传成功后触发的事件      
        	selectPic(3);
         var img = document.getElementById('img3');  
         console.log(data);
         //var obj = data.parseJSON();
         testJson = eval("(" + data + ")");         
         img.src = testJson.path; 
         img.setAttribute("value", testJson.name);
        }
    });
    
    $("#imageify4").uploadify({
        'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
         method : 'get',            //以get方式上传                
        'buttonText':'选择图五',        //上传按钮的文字
        'fileTypeDesc':'Image Files',    //可上传文件格式的描述
        'fileTypeExts':'*.gif; *.jpg; *.png',    //可上传的文件格式 
        'auto':true,    //选择一个文件是否自动上传
        'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
         swf : 'uploadify/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
         uploader : 'upload.action',                //服务器响应地址
        'formData' : {'name':'lmy'},        //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
        'onUploadStart' : function(file) {    //上传前触发的事件
        
           //在这里添加  $('#imageify').uploadify('cancel'); 可以取消上传
            //$("#imageify").uploadify("settings","formData",{'name':'lmy1'}); //动态指定参数
        } ,
        'onUploadSuccess' : function(file, data, response) {    //上传成功后触发的事件   
        	selectPic(4);
         var img = document.getElementById('img4');  
         console.log(data);
         //var obj = data.parseJSON();
         testJson = eval("(" + data + ")");         
         img.src = testJson.path;  
         img.setAttribute("value", testJson.name);
        }
    });
    
    $("#imageify5").uploadify({
        'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
         method : 'get',            //以get方式上传                
        'buttonText':'选择图六',        //上传按钮的文字
        'fileTypeDesc':'Image Files',    //可上传文件格式的描述
        'fileTypeExts':'*.gif; *.jpg; *.png',    //可上传的文件格式 
        'auto':true,    //选择一个文件是否自动上传
        'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
         swf : 'uploadify/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
         uploader : 'upload.action',                //服务器响应地址
        'formData' : {'name':'lmy'},        //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
        'onUploadStart' : function(file) {    //上传前触发的事件
        
        // $("#imageify5").uploadify('cancel'); //可以取消上传
           // $("#imageify5").uploadify("settings","formData",{'name':'lmy1'}); //动态指定参数
        } ,
        'onUploadSuccess' : function(file, data, response) {    //上传成功后触发的事件          
        	selectPic(5);
         var img = document.getElementById('img5');  
         console.log(data);
         //var obj = data.parseJSON();
         testJson = eval("(" + data + ")");         
         img.src = testJson.path;  
         img.setAttribute("value", testJson.name);
        }
    });
});

function selectPic(i){
	var obj = document.getElementById("img_box"+i);
    if (obj){

    }else if(i<6){
            $('#row_img_box').append('<div class="col-md-3" id="img_box'+(i)+'"><img src="" title="点击删除" id="img'+(i)+'"'+'class="post-img" onclick="deletePic('+(i)+')"></div>');
//            if(i<5){
//                    $('#row_btn_box').append('<div class="col-md-2" id="div_box'+(i+1)+'"><a class="file"'+'id="a_img_'+(i+1)+'">'+'<input id="imageify'+(i+1)+'"'+'name="imageify" type="file" class="no-padding-left"/><input type="hidden" id="is_del_'+(i+1)+'"'+' value=""/></a></div>');
            	$('#div_box'+(i+1)).css('display','block');
            	var count = parseInt($('#count').attr("value"));
            	$('#count').attr('value',count+1);
//            }
           
    }
}
function deletePic(i){
	var deleted_imgs = $('#deleted_imgs').attr('value');
    var img_name = document.getElementById('img'+i).getAttribute("value");
    
    $('#deleted_imgs').attr('value',deleted_imgs+'@'+img_name);
    
    $('#img_box'+(i)).remove();
    var count = parseInt($('#count').attr("value"));
    $('#div_box'+(count-1)).css('display', 'none');
    $('#count').attr("value",count-1);
    
    while(i<6){
        $('#div_box'+(i+1)).attr('id','div_box'+i);
        $('#a_img_'+(i+1)).attr('onclick', 'selectPic('+i+');');
        $('#a_img_'+(i+1)).attr('id','a_img_'+i);
        $('#imageify'+(i+1)).attr('id','imageify'+i);

        if(i>0){
                $('#img_box'+i).attr('id','img_box'+(i-1));
                $('#img'+i).attr('onclick', 'deletePic('+(i-1)+');');
                $('#img'+i).attr('id','img'+(i-1));
        }
        i=i+1;
    }
}