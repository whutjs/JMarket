/**
 * Created by Mattxue on 2015/12/13.
 */
/*cookie operations*/
function setCookie(name,value,time)
{
    var strSec = getSec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strSec*1);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function getSec(str)
{
    var str1=str.substring(1,str.length)*1;
    var str2=str.substring(0,1);
    if (str2=="s")
    {
        return str1*1000;
    }
    else if (str2=="h")
    {
        return str1*60*60*1000;
    }
    else if (str2=="d")
    {
        return str1*24*60*60*1000;
    }
}
//这是有设定过期时间的使用示例：
//s20是代表20秒
//h是指小时，如12小时则是：h12
//d是天数，30天则：d30

function Dictionary(){
    this.data = new Array();

    this.put = function(key,value){
        this.data[key] = value;
    };

    this.get = function(key){
        return this.data[key];
    };

    this.remove = function(key){
        this.data[key] = null;
    };

    this.isEmpty = function(){
        return this.data.length == 0;
    };

    this.size = function(){
        return this.data.length;
    };
}

dic = new Dictionary();

function Filter(a,b){
    //var $ = function(e){return document.getElementById(e);}
    var ipts = document.getElementById('formCondition').getElementsByTagName('input'), result=[];

    for(var i=0,l=ipts.length;i<l;i++){
      if(ipts[i].getAttribute('name')=='price'){
        result.push(ipts[0]);
      } else if(ipts[i].getAttribute('name')=='quality'){
        result.push(ipts[1]);
      } else if(ipts[i].getAttribute('name')=='campus'){
        result.push(ipts[2]);
      } else if(ipts[i].getAttribute('name')=='first_cate'){
        result.push(ipts[3]);
      } else if(ipts[i].getAttribute('name')=='second_cate'){
        result.push(ipts[4]);
      } else if(ipts[i].getAttribute('name')=='pagination'){
        result.push(ipts[5]);
      } else if(ipts[i].getAttribute('name')=='sort_by'){
        result.push(ipts[6]);
      } else if(ipts[i].getAttribute('name')=='search_item'){
        result.push(ipts[7]);
      }
    }
    var ele = document.getElementById(a)
    if(ele){
        ele.value = b;
        setCookie(a,b,"d1");

        var firstCate = getCookie("first_cate");
        var secondCate = getCookie("second_cate");
        var sortBy = getCookie("sort_by");
        var price = getCookie("price");
        var quality = getCookie("quality");
        var campus = getCookie("campus");

        if(firstCate == null){
          result[3].value = '0';
        } else{
          result[3].value = firstCate;
        }

        if(secondCate == null){
          result[4].value = '0';
        } else{
          result[4].value = secondCate;
        }
        if(sortBy == null){
          //result[2].parentNode.removeChild(result[2]);
          result[6].value = '0';
        } else {
          result[6].value = sortBy;
        }

        if(price == null || price==0){
            result[0].parentNode.removeChild(result[0]);
            //result[0].value = '0';
        } else{
            result[0].value = price;
        }

        if(quality == null || quality == 0){
            result[1].parentNode.removeChild(result[1]);
            //result[1].value = '0';
        } else{
            result[1].value = quality;
        }
        if(campus == null || campus == 0){
            result[2].parentNode.removeChild(result[2]);
            //result[2].value = '0';
        } else {
            result[2].value = campus;
        }
        result[5].parentNode.removeChild(result[5]);
        result[7].parentNode.removeChild(result[7]);

        document.forms['formCondition'].submit();
    }
    return false;
}

function searchFilterInput(){
  var ipts = document.getElementById('formCondition').getElementsByTagName('input'), result=[];

  for(var i=0,l=ipts.length;i<l;i++){
    if(ipts[i].getAttribute('name')=='price'){
      result.push(ipts[0]);
    } else if(ipts[i].getAttribute('name')=='quality'){
      result.push(ipts[1]);
    } else if(ipts[i].getAttribute('name')=='campus'){
      result.push(ipts[2]);
    } else if(ipts[i].getAttribute('name')=='first_cate'){
      result.push(ipts[3]);
    } else if(ipts[i].getAttribute('name')=='second_cate'){
      result.push(ipts[4]);
    } else if(ipts[i].getAttribute('name')=='pagination'){
      result.push(ipts[5]);
    } else if(ipts[i].getAttribute('name')=='sort_by'){
      result.push(ipts[6]);
    } else if(ipts[i].getAttribute('name')=='search_item'){
      result.push(ipts[7]);
    }
  }

  delCookie('price');
  delCookie('quality');
  delCookie('campus');
  delCookie('first_cate');
  delCookie('second_cate');
  delCookie('pagination');
  delCookie('sort_by');

  result[0].parentNode.removeChild(result[0]);
  result[1].parentNode.removeChild(result[1]);
  result[2].parentNode.removeChild(result[2]);
  result[3].parentNode.removeChild(result[3]);
  result[4].parentNode.removeChild(result[4]);
  result[5].parentNode.removeChild(result[5]);
  result[6].parentNode.removeChild(result[6]);
}

function cateFilterInput(){
  var ipts = document.getElementById('formCondition').getElementsByTagName('input'), result=[];

  for(var i=0,l=ipts.length;i<l;i++){
    if(ipts[i].getAttribute('name')=='price'){
      result.push(ipts[0]);
    } else if(ipts[i].getAttribute('name')=='quality'){
      result.push(ipts[1]);
    } else if(ipts[i].getAttribute('name')=='campus'){
      result.push(ipts[2]);
    } else if(ipts[i].getAttribute('name')=='first_cate'){
      result.push(ipts[3]);
    } else if(ipts[i].getAttribute('name')=='second_cate'){
      result.push(ipts[4]);
    } else if(ipts[i].getAttribute('name')=='pagination'){
      result.push(ipts[5]);
    } else if(ipts[i].getAttribute('name')=='sort_by'){
      result.push(ipts[6]);
    } else if(ipts[i].getAttribute('name')=='search_item'){
      result.push(ipts[7]);
    }
  }

  var firstCate = getCookie("first_cate");
  var secondCate = getCookie("second_cate");

  if(firstCate == null){
    result[3].parentNode.removeChild(result[3]);
  } else{
    result[3].value = firstCate;
  }

  if(secondCate == null || secondCate == 0){
    result[4].parentNode.removeChild(result[4]);
  } else{
    result[4].value = secondCate;
  }

  result[0].parentNode.removeChild(result[0]);
  result[1].parentNode.removeChild(result[1]);
  result[2].parentNode.removeChild(result[2]);
  result[5].parentNode.removeChild(result[5]);
  result[6].parentNode.removeChild(result[6]);
  result[7].parentNode.removeChild(result[7]);

}

function paginationFilterInput(){
  var ipts = document.getElementById('formCondition').getElementsByTagName('input'), result=[];

  for(var i=0,l=ipts.length;i<l;i++){
    if(ipts[i].getAttribute('name')=='price'){
      result.push(ipts[0]);
    } else if(ipts[i].getAttribute('name')=='quality'){
      result.push(ipts[1]);
    } else if(ipts[i].getAttribute('name')=='campus'){
      result.push(ipts[2]);
    } else if(ipts[i].getAttribute('name')=='first_cate'){
      result.push(ipts[3]);
    } else if(ipts[i].getAttribute('name')=='second_cate'){
      result.push(ipts[4]);
    } else if(ipts[i].getAttribute('name')=='pagination'){
      result.push(ipts[5]);
    } else if(ipts[i].getAttribute('name')=='sort_by'){
      result.push(ipts[6]);
    } else if(ipts[i].getAttribute('name')=='search_item'){
      result.push(ipts[7]);
    }
  }

  var price = getCookie("price");
  var quality = getCookie("quality");
  var campus = getCookie("campus");
  var firstCate = getCookie("first_cate");
  var secondCate = getCookie("second_cate");
  var sortBy = getCookie("sort_by");

  if(price == null || price == 0){
    result[0].parentNode.removeChild(result[0]);
  } else{
    result[0].value = price;
  }

  if(quality == null || quality == 0){
    result[1].parentNode.removeChild(result[1]);
  } else{
    result[1].value = quality;
  }
  if(campus == null || campus == 0){
    result[2].parentNode.removeChild(result[2]);
  } else {
    result[2].value = campus;
  }
  if(firstCate == null || firstCate == 0){
    result[3].parentNode.removeChild(result[3]);
  } else{
    result[3].value = firstCate;
  }
  if(secondCate == null || secondCate == 0){
    result[4].parentNode.removeChild(result[4]);
  } else{
    result[4].value = secondCate;
  }
  if(sortBy == null || sortBy == 0){
    result[6].parentNode.removeChild(result[6]);
  } else{
    result[6].value = sortBy;
  }

  result[7].parentNode.removeChild(result[7]);
}

function getCategory(a){
  $('ul.mcd-menu > li > a').removeClass('active');

  //删除所有不必要的cookie
  delCookie('price');
  delCookie('quality');
  delCookie('campus');
  delCookie('pagination');
  delCookie('sort_by');


  var typeVal = a.getAttribute('type');
  var intTypeVal = parseInt(typeVal);
  var firstCate = document.getElementById("first_cate");
  var secondCate = document.getElementById("second_cate");

  if(intTypeVal<100){
    firstCate.value = intTypeVal;
    secondCate.value = 0;
    setCookie('first_cate',intTypeVal,"d1");
    setCookie('second_cate',0,"d1");
  }else if(intTypeVal>=100 && intTypeVal<200){
    firstCate.value = 1;
    secondCate.value = intTypeVal;
    setCookie('first_cate',1,"d1");
    setCookie('second_cate',intTypeVal,"d1");
  }else if(intTypeVal>=200 && intTypeVal<300){
    firstCate.value = 2;
    secondCate.value = intTypeVal;
    setCookie('first_cate',2,"d1");
    setCookie('second_cate',intTypeVal,"d1");
  }else if(intTypeVal>=300 && intTypeVal<400){
    firstCate.value = 3;
    secondCate.value = intTypeVal;
    setCookie('first_cate',3,"d1");
    setCookie('second_cate',intTypeVal,"d1");
  }else if(intTypeVal>=400 && intTypeVal<500){
    firstCate.value = 4;
    secondCate.value = intTypeVal;
    setCookie('first_cate',4,"d1");
    setCookie('second_cate',intTypeVal,"d1");
  }else if(intTypeVal>=500 && intTypeVal<600){
    firstCate.value = 5;
    secondCate.value = intTypeVal;
    setCookie('first_cate',5,"d1");
    setCookie('second_cate',intTypeVal,"d1");
  }else if(intTypeVal>=600 && intTypeVal<700){
    firstCate.value = 6;
    secondCate.value = intTypeVal;
    setCookie('first_cate',6,"d1");
    setCookie('second_cate',intTypeVal,"d1");
  }else if(intTypeVal>=700 && intTypeVal<800){
    firstCate.value = 7;
    secondCate.value = intTypeVal;
    setCookie('first_cate',7,"d1");
    setCookie('second_cate',intTypeVal,"d1");
  }else if(intTypeVal>=800 && intTypeVal<900){
    firstCate.value = 8;
    secondCate.value = intTypeVal;
    setCookie('first_cate',8,"d1");
    setCookie('second_cate',intTypeVal,"d1");
  }else if(intTypeVal>=900){
    firstCate.value = 0;
    secondCate.value = 0;
    setCookie('first_cate',0,"d1");
    setCookie('second_cate',0,"d1");
  }
  //过滤掉其他input，只保留first_cate和second_cate
  cateFilterInput();

  document.forms['formCondition'].submit();
}


function sort(a){
  var sort_by = a.getAttribute('type');
  var intVal = parseInt(sort_by);
  var sort = document.getElementById("sort_by");

  if(intVal == 0){
    sort.value = 0;
    setCookie('sort_by',0,"d1");
  }else if(intVal == 1){
    sort.value = 1;
    setCookie('sort_by',1,"d1");
  }else if(intVal == 2){
    sort.value = 2;
    setCookie('sort_by',2,"d1");
  }else{
    sort.value = 0;
    setCookie('sort_by',0,"d1");
  }

  var ipts = document.getElementById('formCondition').getElementsByTagName('input'), result=[];

  for(var i=0,l=ipts.length;i<l;i++){
    if(ipts[i].getAttribute('name')=='price'){
      result.push(ipts[0]);
    } else if(ipts[i].getAttribute('name')=='quality'){
      result.push(ipts[1]);
    } else if(ipts[i].getAttribute('name')=='campus'){
      result.push(ipts[2]);
    } else if(ipts[i].getAttribute('name')=='first_cate'){
      result.push(ipts[3]);
    } else if(ipts[i].getAttribute('name')=='second_cate'){
      result.push(ipts[4]);
    } else if(ipts[i].getAttribute('name')=='pagination'){
      result.push(ipts[5]);
    } else if(ipts[i].getAttribute('name')=='sort_by'){
      result.push(ipts[6]);
    } else if(ipts[i].getAttribute('name')=='search_item'){
      result.push(ipts[7]);
    }
  }

  var price = getCookie("price");
  var quality = getCookie("quality");
  var campus = getCookie("campus");
  var firstCate = getCookie("first_cate");
  var secondCate = getCookie("second_cate");

  if(price == null){
    result[0].parentNode.removeChild(result[0]);
  } else{
    result[0].value = price;
  }

  if(quality == null){
    result[1].parentNode.removeChild(result[1]);
  } else{
    result[1].value = quality;
  }
  if(campus == null){
    result[2].parentNode.removeChild(result[2]);
    //result[2].value = '0';
  } else {
    result[2].value = campus;
  }
  if(firstCate == null){
    result[3].parentNode.removeChild(result[3]);
  } else{
    result[3].value = firstCate;
  }
  if(secondCate == null || secondCate == 0){
    result[4].parentNode.removeChild(result[4]);
  } else{
    result[4].value = secondCate;
  }

  delCookie('pagination');
  result[5].parentNode.removeChild(result[5]);
  result[7].parentNode.removeChild(result[7]);

  document.forms['formCondition'].submit();
}

function pagination(a){
  var curPage = a.innerHTML;
  var intVal = parseInt(curPage);
  var pagination = document.getElementById("pagination");
  pagination.value = intVal;
  setCookie('pagination',intVal,"d1");

  paginationFilterInput();

  document.forms['formCondition'].submit();
}

function prevPagination(){
  var paginationVal = getCookie('pagination');
  var intPageVal = parseInt(paginationVal);

  if(paginationVal==null){
    intPageVal=1;
  }
  if(intPageVal==1){
    $('#prevPage').css('display', 'none');
  }else{
    var prevPage = intPageVal-1;
    var pagination = document.getElementById("pagination");
    pagination.value = prevPage;
    setCookie('pagination',prevPage,"d1");

    paginationFilterInput();

    document.forms['formCondition'].submit();
  }
}

function nextPagination(pageNum){
  var paginationVal = getCookie('pagination');
  var intPageVal = parseInt(paginationVal);

  if(paginationVal==null){
    intPageVal=1;
  }

  if(intPageVal==pageNum){
    $('#nextPage').css('display', 'none');
  }else{
    var nextPage = intPageVal+1;
    var pagination = document.getElementById("pagination");
    pagination.value = nextPage;
    setCookie('pagination',nextPage,"d1");

    paginationFilterInput();

    document.forms['formCondition'].submit();
  }
}

$(function() {

  var price = getCookie("price");
  var quality = getCookie("quality");
  var campus = getCookie("campus");
  var strPrice,  strQuality, strCampus;

  if(price == null || price == '0'){
    $('#ss_item_price').css('display', 'none');
    $('#em_price').text('不限');
    $('#price0').addClass('btn-active');
  } else{
    $('#crumbs_nav_item').css('display', 'inline-block');
    $('#ss_item_price').css('display', 'inline-block');

    switch (price){
      case "1":{strPrice="10元以下"; $('#price1').addClass('btn-active');};break;
      case "2":{strPrice="10-50元"; $('#price2').addClass('btn-active');};break;
      case "3":{strPrice="50-100元"; $('#price3').addClass('btn-active');};break;
      case "4":{strPrice="100-200元"; $('#price4').addClass('btn-active');};break;
      case "5":{strPrice="200-400元"; $('#price5').addClass('btn-active');};break;
      case "6":{strPrice="400-600元"; $('#price6').addClass('btn-active');};break;
      case "7":{strPrice="600-800元"; $('#price7').addClass('btn-active');};break;
      case "8":{strPrice="800-1000元"; $('#price8').addClass('btn-active');};break;
      case "9":{strPrice="1000-2000元"; $('#price9').addClass('btn-active');};break;
      case "10":{strPrice="2000元以上"; $('#price10').addClass('btn-active');};break;
      default:break;
    }
    $('#em_price').text(strPrice);
  }
  if(quality == null || quality == '0'){
    $('#ss_item_quality').css('display', 'none');
    $('#em_quality').text('不限');
    $('#quality0').addClass('btn-active');
  } else{
    $('#crumbs_nav_item').css('display', 'inline-block');
    $('#ss_item_quality').css('display', 'inline-block');

    switch (quality){
      case "1":{strQuality="全新";$('#quality1').addClass('btn-active');};break;
      case "2":{strQuality="九成新";$('#quality2').addClass('btn-active');};break;
      case "3":{strQuality="八成新";$('#quality3').addClass('btn-active');};break;
      case "4":{strQuality="七成新";$('#quality4').addClass('btn-active');};break;
      case "5":{strQuality="六成新";$('#quality5').addClass('btn-active');};break;
      case "6":{strQuality="五成新";$('#quality6').addClass('btn-active');};break;
      case "7":{strQuality="五成新以下";$('#quality7').addClass('btn-active');};break;
      default:break;

    }
    $('#em_quality').text(strQuality);
  }
  if(campus == null || campus == '0'){
    $('#ss_item_campus').css('display', 'none');
    $('#em_campus').text('不限');
    $('#campus0').addClass('btn-active');
  }else {
    $('#crumbs_nav_item').css('display', 'inline-block');
    $('#ss_item_campus').css('display', 'inline-block');

    switch (campus) {
      case "1":{strCampus = "闵行校区";$('#campus1').addClass('btn-active');};break;
      case "2":{strCampus = "徐汇校区";$('#campus2').addClass('btn-active');};break;
      default:break;
    }
    $('#em_campus').text(strCampus);
  }

  $('#btn_del_price').hover(function(){
    $(this).removeClass('fa-remove').addClass('fa-times-circle');
  });
  $('#btn_del_quality').hover(function(){
    $(this).removeClass('fa-remove').addClass('fa-times-circle');
  });
  $('#btn_del_campus').hover(function(){
    $(this).removeClass('fa-remove').addClass('fa-times-circle');
  });

  $('#btn_del_price').click(function(){
    $('#ss_item_price').css('display', 'none');
    $('#em_price').text('');
    delCookie('price');
    //dic.remove('price');
    Filter('price','0')
  });

  $('#btn_del_quality').click(function(){
    $('#ss_item_quality').css('display', 'none');
    $('#em_quality').text('');
    delCookie('quality');
    //dic.remove('quality');
    Filter('quality','0')
  });

  $('#btn_del_campus').click(function(){
    $('#ss_item_campus').css('display', 'none');
    $('#em_campus').text('');
    delCookie('campus');
    //dic.remove('campus');
    Filter('campus','0')
  });

});

$(document).ready(function() {
  var firstCate = getCookie('first_cate');
  var secondCate = getCookie('second_cate');
  var intFirstCate = parseInt(firstCate);
  var intSecondCate = parseInt(secondCate);

  var breadFirstCate = document.getElementById('bread_first_cate');
  var breadSecondCate = document.getElementById('bread_second_cate');

  if(firstCate==null){
    breadFirstCate.innerHTML = '全部';
    $('#cate0').addClass('active');
  }
  if(intSecondCate==0||secondCate==null){
    $('#bread_second_cate').css('display', 'none');
  }else{
    $('#bread_second_cate').css('display', 'inline-block');
  }

  switch (intFirstCate){
    case 0: {breadFirstCate.innerHTML = '全部'; $('#cate0').addClass('active');}; break;
    case 1: {breadFirstCate.innerHTML = '二手车'; $('#cate1').addClass('active');}; break;
    case 2: {breadFirstCate.innerHTML = '二手手机'; $('#cate2').addClass('active');}; break;
    case 3: {breadFirstCate.innerHTML = 'PC/PAD'; $('#cate3').addClass('active');}; break;
    case 4: {breadFirstCate.innerHTML = '二手家电'; $('#cate4').addClass('active');}; break;
    case 5: {breadFirstCate.innerHTML = '服装包箱'; $('#cate5').addClass('active');}; break;
    case 6: {breadFirstCate.innerHTML = '图书音像'; $('#cate6').addClass('active');}; break;
    case 7: {breadFirstCate.innerHTML = '生活用品'; $('#cate7').addClass('active');}; break;
    case 8: {breadFirstCate.innerHTML = '体育器材'; $('#cate8').addClass('active');}; break;
    case 9: {breadFirstCate.innerHTML = '未分类'; $('#cate9').addClass('active');}; break;
    default:;break;
  }

  switch (intSecondCate){
    case 100: {breadSecondCate.innerHTML = '普通自行车';$('#second_cate100').addClass('active');}; break;
    case 101: {breadSecondCate.innerHTML = '山地自行车';$('#second_cate101').addClass('active');};break;
    case 102: {breadSecondCate.innerHTML = '折叠自行车';$('#second_cate102').addClass('active');};break;
    case 103: {breadSecondCate.innerHTML = '迷你自行车';$('#second_cate103').addClass('active');};break;
    case 104: {breadSecondCate.innerHTML = '公路自行车';$('#second_cate104').addClass('active');};break;
    case 105: {breadSecondCate.innerHTML = '电动车';$('#second_cate105').addClass('active');};break;
    case 106: {breadSecondCate.innerHTML = '其他车';$('#second_cate106').addClass('active');};break;
    case 200: {breadSecondCate.innerHTML = '苹果';$('#second_cate200').addClass('active');};break;
    case 201: {breadSecondCate.innerHTML = '三星';$('#second_cate201').addClass('active');};break;
    case 202: {breadSecondCate.innerHTML = '小米';$('#second_cate202').addClass('active');};break;
    case 203: {breadSecondCate.innerHTML = '华为';$('#second_cate203').addClass('active');};break;
    case 204: {breadSecondCate.innerHTML = 'HTC';$('#second_cate204').addClass('active');};break;
    case 205: {breadSecondCate.innerHTML = '诺基亚';$('#second_cate205').addClass('active');};break;
    case 206: {breadSecondCate.innerHTML = '魅族';$('#second_cate206').addClass('active');};break;
    case 207: {breadSecondCate.innerHTML = '索尼';$('#second_cate207').addClass('active');};break;
    case 208: {breadSecondCate.innerHTML = '中兴';$('#second_cate208').addClass('active');};break;
    case 209: {breadSecondCate.innerHTML = 'LG';$('#second_cate209').addClass('active');};break;
    case 210: {breadSecondCate.innerHTML = '联想';$('#second_cate210').addClass('active');};break;
    case 211: {breadSecondCate.innerHTML = '酷派';$('#second_cate211').addClass('active');};break;
    case 212: {breadSecondCate.innerHTML = '其他手机';$('#second_cate212').addClass('active');};break;
    case 300: {breadSecondCate.innerHTML = '台式机';$('#second_cate300').addClass('active');};break;
    case 301: {breadSecondCate.innerHTML = 'ThinkPad/IBM';$('#second_cate301').addClass('active');};break;
    case 302: {breadSecondCate.innerHTML = '苹果(Mac)';$('#second_cate302').addClass('active');};break;
    case 303: {breadSecondCate.innerHTML = '联想(Lenovo)';$('#second_cate303').addClass('active');};break;
    case 304: {breadSecondCate.innerHTML = '戴尔(DELL)';$('#second_cate304').addClass('active');};break;
    case 305: {breadSecondCate.innerHTML = '华硕(ASUS)';$('#second_cate305').addClass('active');};break;
    case 306: {breadSecondCate.innerHTML = '惠普(HP)';$('#second_cate306').addClass('active');};break;
    case 307: {breadSecondCate.innerHTML = '索尼(Sony)';$('#second_cate307').addClass('active');};break;
    case 308: {breadSecondCate.innerHTML = '三星(Samsung)';$('#second_cate308').addClass('active');};break;
    case 309: {breadSecondCate.innerHTML = '其他电脑';$('#second_cate309').addClass('active');};break;
    case 310: {breadSecondCate.innerHTML = 'iPad';$('#second_cate310').addClass('active');};break;
    case 311: {breadSecondCate.innerHTML = 'Surface';$('#second_cate311').addClass('active');};break;
    case 312: {breadSecondCate.innerHTML = '三星平板';$('#second_cate312').addClass('active');};break;
    case 313: {breadSecondCate.innerHTML = '小米平板';$('#second_cate313').addClass('active');};break;
    case 314: {breadSecondCate.innerHTML = '联想平板';$('#second_cate314').addClass('active');};break;
    case 315: {breadSecondCate.innerHTML = '其他平板';$('#second_cate315').addClass('active');};break;
    case 400: {breadSecondCate.innerHTML = '洗衣机';$('#second_cate400').addClass('active');};break;
    case 401: {breadSecondCate.innerHTML = '厨房电器';$('#second_cate401').addClass('active');};break;
    case 402: {breadSecondCate.innerHTML = '冰箱/冰柜';$('#second_cate402').addClass('active');};break;
    case 403: {breadSecondCate.innerHTML = '数码相机';$('#second_cate403').addClass('active');};break;
    case 404: {breadSecondCate.innerHTML = '其他家电';$('#second_cate404').addClass('active');};break;
    case 500: {breadSecondCate.innerHTML = 'T恤';$('#second_cate500').addClass('active');};break;
    case 501: {breadSecondCate.innerHTML = '衬衫';$('#second_cate501').addClass('active');};break;
    case 502: {breadSecondCate.innerHTML = '外套';$('#second_cate502').addClass('active');};break;
    case 503: {breadSecondCate.innerHTML = '裤子';$('#second_cate503').addClass('active');};break;
    case 504: {breadSecondCate.innerHTML = '西装';$('#second_cate504').addClass('active');};break;
    case 505: {breadSecondCate.innerHTML = '裙子';$('#second_cate505').addClass('active');};break;
    case 506: {breadSecondCate.innerHTML = '休闲鞋';$('#second_cate506').addClass('active');};break;
    case 507: {breadSecondCate.innerHTML = '运动鞋';$('#second_cate507').addClass('active');};break;
    case 508: {breadSecondCate.innerHTML = '帆布鞋';$('#second_cate508').addClass('active');};break;
    case 509: {breadSecondCate.innerHTML = '高跟鞋';$('#second_cate509').addClass('active');};break;
    case 510: {breadSecondCate.innerHTML = '皮鞋';$('#second_cate510').addClass('active');};break;
    case 511: {breadSecondCate.innerHTML = '单肩包';$('#second_cate511').addClass('active');};break;
    case 512: {breadSecondCate.innerHTML = '双肩包';$('#second_cate512').addClass('active');};break;
    case 513: {breadSecondCate.innerHTML = '书包';$('#second_cate513').addClass('active');};break;
    case 514: {breadSecondCate.innerHTML = '钱包';$('#second_cate514').addClass('active');};break;
    case 515: {breadSecondCate.innerHTML = '电脑包';$('#second_cate515').addClass('active');};break;
    case 516: {breadSecondCate.innerHTML = '箱子';$('#second_cate516').addClass('active');};break;
    case 517: {breadSecondCate.innerHTML = '其他服饰';$('#second_cate517').addClass('active');};break;
    case 600: {breadSecondCate.innerHTML = '专业书籍';$('#second_cate600').addClass('active');};break;
    case 601: {breadSecondCate.innerHTML = '考试(GRE/托福/雅思)';$('#second_cate601').addClass('active');};break;
    case 602: {breadSecondCate.innerHTML = '小说/文学';$('#second_cate602').addClass('active');};break;
    case 603: {breadSecondCate.innerHTML = '工具书/辅导书';$('#second_cate603').addClass('active');};break;
    case 604: {breadSecondCate.innerHTML = '生活类书籍';$('#second_cate604').addClass('active');};break;
    case 605: {breadSecondCate.innerHTML = '学生文具';$('#second_cate605').addClass('active');};break;
    case 606: {breadSecondCate.innerHTML = '学习机';$('#second_cate606').addClass('active');};break;
    case 607: {breadSecondCate.innerHTML = '乐器';$('#second_cate607').addClass('active');};break;
    case 608: {breadSecondCate.innerHTML = '音响';$('#second_cate608').addClass('active');};break;
    case 609: {breadSecondCate.innerHTML = '耳机';$('#second_cate609').addClass('active');};break;
    case 610: {breadSecondCate.innerHTML = 'U盘/硬盘';$('#second_cate610').addClass('active');};break;
    case 611: {breadSecondCate.innerHTML = 'MP3/iPod/iWatch';$('#second_cate611').addClass('active');};break;
    case 612: {breadSecondCate.innerHTML = '其他图书';$('#second_cate612').addClass('active');};break;
    case 700: {breadSecondCate.innerHTML = '电灯';$('#second_cate700').addClass('active');};break;
    case 701: {breadSecondCate.innerHTML = '雨伞';$('#second_cate701').addClass('active');};break;
    case 702: {breadSecondCate.innerHTML = '梳子/镜子';$('#second_cate702').addClass('active');};break;
    case 703: {breadSecondCate.innerHTML = '家具';$('#second_cate703').addClass('active');};break;
    case 704: {breadSecondCate.innerHTML = '桌椅板凳';$('#second_cate704').addClass('active');};break;
    case 705: {breadSecondCate.innerHTML = '垫褥床铺';$('#second_cate705').addClass('active');};break;
    case 706: {breadSecondCate.innerHTML = '茶杯/水杯';$('#second_cate706').addClass('active');};break;
    case 707: {breadSecondCate.innerHTML = '布偶/娃娃';$('#second_cate707').addClass('active');};break;
    case 708: {breadSecondCate.innerHTML = '其他生活用品';$('#second_cate708').addClass('active');};break;
    case 800: {breadSecondCate.innerHTML = '健身器材';$('#second_cate800').addClass('active');};break;
    case 801: {breadSecondCate.innerHTML = '网球';$('#second_cate801').addClass('active');};break;
    case 802: {breadSecondCate.innerHTML = '足球';$('#second_cate802').addClass('active');};break;
    case 803: {breadSecondCate.innerHTML = '篮球';$('#second_cate803').addClass('active');};break;
    case 804: {breadSecondCate.innerHTML = '乒乓球';$('#second_cate804').addClass('active');};break;
    case 805: {breadSecondCate.innerHTML = '羽毛球';$('#second_cate805').addClass('active');};break;
    case 806: {breadSecondCate.innerHTML = '户外用品';$('#second_cate806').addClass('active');};break;
    case 807: {breadSecondCate.innerHTML = '游泳用品';$('#second_cate807').addClass('active');};break;
    case 808: {breadSecondCate.innerHTML = '其他体育用品';$('#second_cate808').addClass('active');};break;
  }


  //search
  $('#btn_search').click(function(){
    var content = $('#search_item').val();
    if(content.length>0){
      searchFilterInput();
      document.forms['formCondition'].submit();
    }else{
      alert('内容为空');
    }
  });


  //sort
  var sortBy = getCookie('sort_by');
  var sortText = document.getElementById('sort_text');
  if(sortBy==null || sortBy == 0){
    sortText.innerHTML = '默认排序';
    $("#sort_by_time").addClass('btn-active');
    $("#sort_by_price").removeClass('btn-active');
    $("#sort_by_hot").removeClass('btn-active');
    $("#small_sort_by_time").addClass('btn-active');
    $("#small_sort_by_price").removeClass('btn-active');
    $("#small_sort_by_hot").removeClass('btn-active');
  }else if(sortBy == 1){
    sortText.innerHTML = '价格排序';
    sortText.style.color = '#e4393c';
    $("#sort_by_price").addClass('btn-active');
    $("#sort_by_time").removeClass('btn-active');
    $("#sort_by_hot").removeClass('btn-active');
    $("#small_sort_by_time").addClass('btn-active');
    $("#small_sort_by_price").removeClass('btn-active');
    $("#small_sort_by_hot").removeClass('btn-active');
  }else if(sortBy == 2){
    sortText.innerHTML = '热度排序';
    sortText.style.color = '#e4393c';
    $("#sort_by_hot").addClass('btn-active');
    $("#sort_by_price").removeClass('btn-active');
    $("#sort_by_time").removeClass('btn-active');
    $("#small_sort_by_time").addClass('btn-active');
    $("#small_sort_by_price").removeClass('btn-active');
    $("#small_sort_by_hot").removeClass('btn-active');
  }

  //pagination
  var pagination = getCookie('pagination');
  var ul = document.getElementById('myPagination');
  var li = ul.getElementsByTagName('li');

  if(pagination == null){
    $('#prevPage').css('display', 'none');
  }else{
    $('#myPagination > li > a').removeClass('page-active');

    if(pagination == '1'){
      $('#prevPage').css('display', 'none');
    }else if(pagination == '5'){
      $('#nextPage').css('display', 'none');
    }
  }

  for (var i = 0; i < li.length; i++) {
    if(li[i].innerText == pagination){
      $(li[i]).children("a").addClass('page-active');
    }
  }


});


