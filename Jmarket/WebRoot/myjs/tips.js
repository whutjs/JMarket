/**
 * Created by MattX on 2015/8/29.
 */

/*
 * Author: Mattxue
 * Function:自动关闭的弹出提示框
 * Date:2015-08-29
 */
function popUpTip(strTitle, strTip, filePath) {
    var msgw,msgh,bordercolor;
    msgw=510;//提示窗口的宽度
    msgh=305;//提示窗口的高度
    titleheight=50 //提示窗口标题高度
//        bordercolor="#336699";//提示窗口的边框颜色
//        titlecolor="#99CCFF";//提示窗口的标题颜色
    var sWidth,sHeight;
    //获取当前窗口尺寸
    sWidth = document.body.offsetWidth;
    sHeight = document.body.offsetHeight;
//    //背景div
    var bgObj=document.createElement("div");
    bgObj.setAttribute('id','alertDiv');
    bgObj.style.position="absolute";
    bgObj.style.top="0";
    bgObj.style.background="#808080";
    bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
    bgObj.style.opacity="0.6";
    bgObj.style.left="0";
    bgObj.style.width = sWidth + "px";
    bgObj.style.height = sHeight + "px";
    bgObj.style.zIndex = "10000";
    document.body.appendChild(bgObj);
    //创建提示窗口的div
    var msgObj = document.createElement("div");
    msgObj.setAttribute("id","msgDiv");
    msgObj.setAttribute("align","center");
    msgObj.style.background="#fff";
    msgObj.style.borderRadius="6px";
    msgObj.style.position = "absolute";
    msgObj.style.left = "50%";
    msgObj.style.font="18px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
    //窗口距离左侧和顶端的距离
    msgObj.style.marginLeft = "-225px";
    //窗口被卷去的高+（屏幕可用工作区高/2）-150
    msgObj.style.top = document.body.scrollTop+(window.screen.availHeight/2)-150 +"px";
    msgObj.style.width = msgw + "px";
    msgObj.style.height = msgh + "px";
    msgObj.style.textAlign = "center";
    msgObj.style.lineHeight ="25px";
    msgObj.style.zIndex = "10001";
    document.body.appendChild(msgObj);

    //提示信息图标框
    var imgBox=document.createElement("div");
    imgBox.setAttribute("id","imgDiv");
    imgBox.setAttribute("align","center");
    document.getElementById("msgDiv").appendChild(imgBox);

    //提示信息图标
    var img=document.createElement("img");
    img.setAttribute("id","imgIcon");
    img.style.margin="0";
    img.style.padding="0";
    img.style.height='90px';
    img.style.width='90px';
    img.style.textAlign = "center";
    img.style.marginTop="40px";
    img.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
    img.style.opacity="0.75";
//        img.style.src="/resources/images/tips/ok.png";
    document.getElementById("imgDiv").appendChild(img);
    var pic = document.getElementById("imgIcon");
    pic.src = filePath;

    //操作提示
    var tipTxt = document.createElement("p");
    tipTxt.setAttribute("id","msgTxt");
    tipTxt.style.margin="24px 0";
    tipTxt.style.textAlign="center";
    tipTxt.style.color='#353535';
    tipTxt.style.fontSize="28px";
    tipTxt.style.fontWeight="700px";
    tipTxt.style.fontFamily="'Microsoft YaHei' ! important;";
    tipTxt.innerHTML = strTip;
    document.getElementById("msgDiv").appendChild(tipTxt);
    //操作提示信息
    var txt = document.createElement("p");
    txt.setAttribute("id","txt");
    txt.style.margin="20px 0";
    txt.style.textAlign="center";
    txt.style.color='#828282';
    txt.style.fontSize="18px";
    tipTxt.style.fontFamily="'Microsoft YaHei' ! important;";
    txt.innerHTML = strTip;
    document.getElementById("msgDiv").appendChild(txt);
    //操作提示信息
    var btnClose = document.createElement("button");
    btnClose.setAttribute("id","btnClose");
    btnClose.style.textAlign="center";
    btnClose.style.marginTop="0px";
    btnClose.style.padding="10px 26.6px";
    btnClose.borderRadius="6px";
    btnClose.style.color="#fff";
    btnClose.style.backgroundColor="#32cd32";
    btnClose.style.fontSize="18px";
    btnClose.innerHTML = "确定";
    document.getElementById("msgDiv").appendChild(btnClose);
    document.getElementById("btnClose").onclick = function () {
        closeWin();
    }

    //设置关闭时间
    window.setTimeout("closeWin()",5000);
}

function closeWin() {
    document.body.removeChild(document.getElementById("alertDiv"));
    document.getElementById("imgDiv").removeChild(document.getElementById("imgIcon"));
    document.getElementById("msgDiv").removeChild(document.getElementById("imgDiv"));
    document.getElementById("msgDiv").removeChild(document.getElementById("msgTxt"));
    document.getElementById("msgDiv").removeChild(document.getElementById("txt"));
    document.getElementById("msgDiv").removeChild(document.getElementById("btnClose"));
    document.body.removeChild(document.getElementById("msgDiv"));
}
