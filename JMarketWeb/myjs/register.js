/**
 * Created by Mattxue on 2015/12/3.
 */
/**
 * Created by MattX on 2015/8/25.
 */
var success_flag=true;
//用户名长度4-20位。
function checkUser(str){
    if(str.length>=2 && str.length<=20){
        return true;
    }
    else{
        return false;
    }
}

//邮箱验证规则：姑且把邮箱地址分成“第一部分@第二部分”这样
//第一部分：由字母、数字、下划线、短线“-”、点号“.”组成，
//第二部分：为一个域名，域名由字母、数字、短线“-”、域名后缀组成，
//而域名后缀一般为.xxx或.xxx.xx，一区的域名后缀一般为2-4位，如cn,com,net，现在域名有的也会大于4位
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

//以字母开头，长度在6-20之间，只能包含字符、数字
function checkPassword(str){
    var re=/^[0-9a-zA-Z]{6,20}$/
    if(re.test(str)){
        return true;
    }
    else{
        return false;
    }
}

$('#input_email').blur(function () {
    var curMailbox = $(this).val();
    if (checkMailbox(curMailbox)) {
        $('#err_mail_tip').css('display', 'none');
        success_flag=true;
    }
    else {
        $('#err_mail_tip').css('color', '#ff2222').css('display', 'block').text('邮箱格式错误');
        success_flag=false;
        $("#next_button").attr("disabled",false).addClass("btn-disabled");
    }
});

$('#input_password').blur(function () {
    var curPassword = $(this).val();
    if (checkPassword(curPassword)) {
        $('#err_mail_psw_tip').css('display', 'none');
        success_flag=true;
    }
    else {
        $('#err_mail_psw_tip').css('color', '#ff2222').css('display', 'block').text('密码格式错误');
        success_flag=false;
        $("#next_button").attr("disabled",false).addClass("btn-disabled");
    }
});
$('#input_password_again').blur(function () {
    var curPassword = $(this).val();
    var psw = $("#input_password").val();
    if (curPassword != psw) {
        $('#err_mail_psw_again_tip').css('color', '#ff2222').css('display', 'block').text('两次密码不一致');
        success_flag=false;
        $("#next_button").attr("disabled",false).addClass("btn-disabled");
    }
    else {
        var curMailbox = $('#input_email').val();
        var curPassword = $('#input_password').val();
        if(curMailbox.length>0){
            if(curPassword>0){
                $('#err_mail_psw_again_tip').css('display', 'none');
                success_flag = true;
                $("#next_button").attr("disabled",false).removeClass("btn-disabled");
            }
            else{
                $('#err_mail_tip').css('color', '#ff2222').css('display', 'block').text('邮箱为空');
                success_flag=false;
                $("#next_button").attr("disabled",false).addClass("btn-disabled");
            }
        }
        else{
            $('#err_mail_psw_tip').css('color', '#ff2222').css('display', 'block').text('密码为空');
            success_flag=false;
            $("#next_button").attr("disabled",false).addClass("btn-disabled");
        }
    }
});

/*
 * Author: Mattxue
 * Function:检查邮箱注册表单
 * Date:2015-08-29
 */

function checkMailForm() {
    //验证邮箱提示
    var curMailbox = $("#input_email").val();

    if(curMailbox.length>0){
        //var err_mailbox_tip = $("#err_mail_tip").html();
        var flag_error_mailbox_tip = $("#err_mail_tip").css("display")=='none'?true:false;
        if(!flag_error_mailbox_tip){
            success_flag=false;
        }
        else{
            //alert(success_flag);
        }
    }
    else{
        $('#err_mail_tip').css('color', '#ff2222').css('display', 'block').text('邮箱格式错误');
        success_flag=false;
    }

    //验证邮箱注册密码
    var curPassword = $("#input_password").val();
    if(curPassword.length>0){
        //var err_password_tip = $('#err_mail_psw_tip').html();
        var flag_error_password_tip = $("#err_mail_psw_tip").css("display")=='none'?true:false;
        if(!flag_error_password_tip){
            success_flag=false;
        }
        else{
            //alert(success_flag);
        }
    }
    else{
        $('#err_mail_psw_tip').css('color', '#ff2222').css('display', 'block').text('密码格式错误');
        success_flag=false;
    }

    //验证密码一致提示
    var psw = $("#input_password_again").val();
    if(psw.length>0){
        //var err_password_again_tip = $('#err_mail_psw_again_tip').html();
        var flag_error_password_again_tip = $("#err_mail_psw_again_tip").css("display")=='none'?true:false;
        if(!flag_error_password_again_tip){
            success_flag=false;
        }
        else{
            //alert(success_flag);
        }
    }
    else{
        $('#err_mail_psw_again_tip').css('color', '#ff2222').css('display', 'block').text('两次密码不一致');
        success_flag=false;
    }

    return success_flag;
}


document.getElementById("next_button").addEventListener("click", function(){
    var flag = checkMailForm();
    if(flag == true){
        //location.href='registerTwo.html';
    }
    else{

    }
});







