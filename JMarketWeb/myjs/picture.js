/**
 * Created by Mattxue on 2016/1/6.
 */
$(document).ready(function () {

});

function selectPic(i){
        var obj = document.getElementById("img_box"+i);
        if (obj){

        }else if(i<6){
                $('#row_img_box').append('<div class="col-md-3" id="img_box'+(i)+'"><img src="" title="点击删除" id="img'+(i)+'"'+'class="post-img" onclick="deletePic('+(i)+')"></div>');
                if(i<5){
                        $('#row_btn_box').append('<div class="col-md-2" id="div_box'+(i+1)+'"><a class="file" onclick="selectPic'+'('+(i+1)+');"'+'id="a_img_'+(i+1)+'">'+'<i class="fa fa-plus" style="font-size: 48px;"></i><input id="imageify'+(i+1)+'"'+'name="imageify" type="file" class="no-padding-left"/></a></div>');
                }
        }
}
function deletePic(i){
        $('#img_box'+(i)).remove();
        $('#div_box'+(i+1)).remove();
        var deleted_imgs = $('#deleted_imgs').attr('value');
        $('#deleted_imgs').attr('value',deleted_imgs+'@'+'file_path');

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