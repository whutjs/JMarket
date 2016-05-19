/**
 * Created by Mattxue on 2016/1/11.
 */
$(document).ready(function(){
        $("#btn_slide1").click(function(){
                $("#panel1").slideToggle("slow");
                $(this).toggleClass("active"); return false;
        });

        $("#btn_slide2").click(function(){
                $("#panel2").slideToggle("slow");
                $(this).toggleClass("active"); return false;
        });

        $("#btn_slide3").click(function(){
                $("#panel3").slideToggle("slow");
                $(this).toggleClass("active"); return false;
        });
});