/**
 * Created by Mattxue on 2015/12/4.
 */
var code_flag = true;
function verify() {
    var curCode = $("#code").val();
    if (curCode.length > 0) {
        $('#err_code_tip').css('display', 'none');
        code_flag = true;
    }
    else {
        $('#err_code_tip').css('color', '#ff2222').css('display', 'block').text('验证码为空');
        code_flag = false;
    }
    return code_flag;
}
$('#code').blur(function () {
    var curCode= $(this).val();
    if (curCode.length>0) {
        $('#err_code_tip').css('display', 'none');
        code_flag = true;
        $("#submit_code").attr("disabled",false).removeClass("btn-disabled");
    }
    else {
        $('#err_code_tip').css('color', '#ff2222').css('display', 'block').text('验证码为空');
        code_flag=false;
        $("#submit_code").attr("disabled",true).addClass("btn-disabled");
    }
});
document.getElementById("submit_code").addEventListener("click", function(){
    var flag = verify();
    if(flag){
        //location.href='registerThree.html';
    }
    else{

    }
});