/**
 * Created by Mattxue on 2015/12/3.
 */
/*
 * Author: Mattxue
 * Function:检查邮箱注册表单
 * Date:2015-08-29
 */
var success_flag=true;
function checkLoginForm() {
    //验证邮箱是否为空
    var curMailbox = $("#input_email").val();

    if(curMailbox.length>0){
        var flag_error_mailbox_tip = $("#err_mail_tip").css("display")=='none'?true:false;
        if(!flag_error_mailbox_tip){
            success_flag=false;
        }
        else {
            success_flag = true;
        }
    }
    else{
        $('#err_mail_tip').css('color', '#ff2222').css('display', 'block').text('邮箱为空');
        success_flag=false;
    }

    var curPassword = $("#input_password").val();

    if(curMailbox.length>0){
        var flag_error_password_tip = $("#err_mail_psw_tip").css("display")=='none'?true:false;
        if(!flag_error_password_tip){
            success_flag=false;
        }
        else {
            success_flag = true;
        }
    }
    else{
        $('#err_mail_psw_tip').css('color', '#ff2222').css('display', 'block').text('密码为空');
        success_flag=false;
    }
}

$('#input_email').blur(function () {
    var curMailbox = $(this).val();
    if (curMailbox.length>0) {
        $('#err_mail_tip').css('display', 'none');
        success_flag = true;
    }
    else {
        $('#err_mail_tip').css('color', '#ff2222').css('display', 'block').text('邮箱为空');
        success_flag=false;
        $("#login_button").attr("disabled",false).addClass("btn-disabled");
    }
});
$('#input_password').blur(function () {
    var curPassword = $(this).val();
    if (curPassword.length>0) {
        var curMailbox = $('#input_email').val();
        if (curMailbox.length>0) {
            $('#err_mail_psw_tip').css('display', 'none');
            success_flag = true;
            $("#login_button").attr("disabled",false).removeClass("btn-disabled");
        }
    }
    else {
        $('#err_mail_psw_tip').css('color', '#ff2222').css('display', 'block').text('密码为空');
        success_flag=false;
        $("#login_button").attr("disabled",false).addClass("btn-disabled");
    }
});



document.getElementById("login_button").addEventListener("click", function(){
    var flag = checkLoginForm();
    if(flag == true){
        location.href='mainpage.jsp';
    }
    else{

    }
})

function checkMailbox(str){
    var a=str.indexOf("@");
    if(a==0){
        return false;
    }
    else{
        var subStr = str.substr(a+1);
        if(subStr == 'sjtu.edu.cn'){
            return true;
        }
        else{
            return false;
        }
    }
}

function forgetPassword(){
    var mail = document.getElementById('input_email').value;
    var http = '/jmarket/login/find?';
    if(checkMailbox(mail)){
        $('#forget_password').attr('href',http+'email='+mail);
        setTimeout(function () {
            alert('密码已经发送至您的邮箱');
          }, 1000);
    }else{
        alert('请输入有效邮箱');
    }
}
