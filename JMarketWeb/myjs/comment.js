/**
 * Created by Mattxue on 2016/1/1.
 */
function relpy($name, $id){
        $('#father_comment_id').val($id);
        $('#comment_content').val('回复'+$name+'：');
}
function checkCommentForm(){
        var text = $('#comment_content').val();
        if(text.length>0){
                return true;
        }else{
                alert('请输入评论内容');
                return false;
        }
}