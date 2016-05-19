/**
 * Created by Mattxue on 2015/12/13.
 */
$(function () {
  $('.secondary-types li a').click(function (e) {
    $('#field_types').css('opacity', '0').css('display', 'none');
    $('#field_detail').css('opacity', '1').css('display', 'block');
  });
});

function reselectType(){
  $('#field_types').css('opacity', '1').css('display', 'block');
  $('#field_detail').css('opacity', '0').css('display', 'none');
  $('#pro2').removeClass('active');
  $('#first_cate').val('');
  $('#second_cate').val('');
}

function getCate(a) {
  var firstCate = document.getElementById('first_cate');
  var secondCate = document.getElementById('second_cate');
  var aFirstCate = document.getElementById('a_first_cate');
  var aSecondCate = document.getElementById('a_second_cate');

  $('#pro1').addClass('active');
  $('#pro2').addClass('active');

  switch (a) {
    case ('100'):
    {
      firstCate.value = 1;
      secondCate.value = 100;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '普通自行车';
    }
      ;
      break;
    case ('101'):
    {
      firstCate.value = 1;
      secondCate.value = 101;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '山地自行车';
    }
      ;
      break;
    case ('102'):
    {
      firstCate.value = 1;
      secondCate.value = 102;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '折叠自行车';
    }
      ;
      break;
    case ('103'):
    {
      firstCate.value = 1;
      secondCate.value = 103;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '迷你自行车';
    }
      ;
      break;
    case ('104'):
    {
      firstCate.value = 1;
      secondCate.value = 104;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '公路自行车';
    }
      ;
      break;
    case ('105'):
    {
      firstCate.value = 1;
      secondCate.value = 105;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '电动车';
    }
      ;
      break;
    case ('106'):
    {
      firstCate.value = 1;
      secondCate.value = 106;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '其他车';
    }
      ;
      break;
    case ('200'):
    {
      firstCate.value = 2;
      secondCate.value = 200;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '苹果';
    }
      ;
      break;
    case ('201'):
    {
      firstCate.value = 2;
      secondCate.value = 201;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '三星';
    }
      ;
      break;
    case ('202'):
    {
      firstCate.value = 2;
      secondCate.value = 202;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '小米';
    }
      ;
      break;
    case ('203'):
    {
      firstCate.value = 2;
      secondCate.value = 203;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '华为';
    }
      ;
      break;
    case ('204'):
    {
      firstCate.value = 2;
      secondCate.value = 204;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = 'HTC';
    }
      ;
      break;
    case ('205'):
    {
      firstCate.value = 2;
      secondCate.value = 205;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '诺基亚';
    }
      ;
      break;
    case ('206'):
    {
      firstCate.value = 2;
      secondCate.value = 206;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '魅族';
    }
      ;
      break;
    case ('207'):
    {
      firstCate.value = 2;
      secondCate.value = 207;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '索尼';
    }
      ;
      break;
    case ('208'):
    {
      firstCate.value = 2;
      secondCate.value = 208;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '中兴';
    }
      ;
      break;
    case ('209'):
    {
      firstCate.value = 2;
      secondCate.value = 209;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = 'LG';
    }
      ;
      break;
    case ('210'):
    {
      firstCate.value = 2;
      secondCate.value = 210;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '联想';
    }
      ;
      break;
    case ('211'):
    {
      firstCate.value = 2;
      secondCate.value = 211;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '酷派';
    }
      ;
      break;
    case ('212'):
    {
      firstCate.value = 2;
      secondCate.value = 212;
      aFirstCate.innerText = '二手手机/ ';
      aSecondCate.innerText = '其他手机';
    }
      ;
      break;
    case ('300'):
    {
      firstCate.value = 3;
      secondCate.value = 300;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '台式机';
    }
      ;
      break;
    case ('301'):
    {
      firstCate.value = 3;
      secondCate.value = 301;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = 'ThinkPad/IBM';
    }
      ;
      break;
    case ('302'):
    {
      firstCate.value = 3;
      secondCate.value = 302;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '苹果(Mac)';
    }
      ;
      break;
    case ('303'):
    {
      firstCate.value = 3;
      secondCate.value = 303;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '联想(Lenovo)';
    }
      ;
      break;
    case ('304'):
    {
      firstCate.value = 3;
      secondCate.value = 304;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '戴尔(DELL)';
    }
      ;
      break;
    case ('305'):
    {
      firstCate.value = 3;
      secondCate.value = 305;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '华硕(ASUS)';
    }
      ;
      break;
    case ('306'):
    {
      firstCate.value = 3;
      secondCate.value = 306;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '惠普(HP)';
    }
      ;
      break;
    case ('307'):
    {
      firstCate.value = 3;
      secondCate.value = 307;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '索尼(Sony)';
    }
      ;
      break;
    case ('308'):
    {
      firstCate.value = 3;
      secondCate.value = 308;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '三星(Samsung)';
    }
      ;
      break;
    case ('309'):
    {
      firstCate.value = 3;
      secondCate.value = 309;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '其他电脑';
    }
      ;
      break;
    case ('310'):
    {
      firstCate.value = 3;
      secondCate.value = 310;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = 'iPad';
    }
      ;
      break;
    case ('311'):
    {
      firstCate.value = 3;
      secondCate.value = 311;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = 'Surface';
    }
      ;
      break;
    case ('312'):
    {
      firstCate.value = 3;
      secondCate.value = 312;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '三星平板';
    }
      ;
      break;
    case ('313'):
    {
      firstCate.value = 3;
      secondCate.value = 313;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '小米平板';
    }
      ;
      break
    case ('314'):
    {
      firstCate.value = 3;
      secondCate.value = 314;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '联想平板';
    }
      ;
      break
    case ('315'):
    {
      firstCate.value = 3;
      secondCate.value = 315;
      aFirstCate.innerText = 'PC/PAD/ ';
      aSecondCate.innerText = '其他平板';
    }
      ;
      break;
    case ('400'):
    {
      firstCate.value = 4;
      secondCate.value = 400;
      aFirstCate.innerText = '二手家电/ ';
      aSecondCate.innerText = '洗衣机';
    }
      ;
      break;
    case ('401'):
    {
      firstCate.value = 4;
      secondCate.value = 401;
      aFirstCate.innerText = '二手家电/ ';
      aSecondCate.innerText = '厨房电器';
    }
      ;
      break;
    case ('402'):
    {
      firstCate.value = 4;
      secondCate.value = 402;
      aFirstCate.innerText = '二手家电/ ';
      aSecondCate.innerText = '冰箱/冰柜';
    }
      ;
      break;
    case ('403'):
    {
      firstCate.value = 4;
      secondCate.value = 403;
      aFirstCate.innerText = '二手家电/ ';
      aSecondCate.innerText = '数码相机';
    }
      ;
      break;
    case ('404'):
    {
      firstCate.value = 4;
      secondCate.value = 404;
      aFirstCate.innerText = '二手家电/ ';
      aSecondCate.innerText = '其他家电';
    }
      ;
      break;
    case ('500'):
    {
      firstCate.value = 5;
      secondCate.value = 500;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = 'T恤';
    }
      ;
      break;
    case ('501'):
    {
      firstCate.value = 5;
      secondCate.value = 501;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '衬衫';
    }
      ;
      break;
    case ('502'):
    {
      firstCate.value = 5;
      secondCate.value = 502;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '外套';
    }
      ;
      break;
    case ('503'):
    {
      firstCate.value = 5;
      secondCate.value = 503;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '裤子';
    }
      ;
      break;
    case ('504'):
    {
      firstCate.value = 5;
      secondCate.value = 504;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '西装';
    }
      ;
      break;
    case ('505'):
    {
      firstCate.value = 5;
      secondCate.value = 505;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '裙子';
    }
      ;
      break;
    case ('506'):
    {
      firstCate.value = 5;
      secondCate.value = 506;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '休闲鞋';
    }
      ;
      break;
    case ('507'):
    {
      firstCate.value = 5;
      secondCate.value = 507;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '运动鞋';
    }
      ;
      break;
    case ('508'):
    {
      firstCate.value = 5;
      secondCate.value = 508;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '帆布鞋';
    }
      ;
      break;
    case ('509'):
    {
      firstCate.value = 5;
      secondCate.value = 509;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '高跟鞋';
    }
      ;
      break;
    case ('510'):
    {
      firstCate.value = 5;
      secondCate.value = 510;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '皮鞋';
    }
      ;
      break;
    case ('511'):
    {
      firstCate.value = 5;
      secondCate.value = 511;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '单肩包';
    }
      ;
      break;
    case ('512'):
    {
      firstCate.value = 5;
      secondCate.value = 512;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '双肩包';
    }
      ;
      break;
    case ('513'):
    {
      firstCate.value = 5;
      secondCate.value = 513;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '书包';
    }
      ;
      break
    case ('514'):
    {
      firstCate.value = 5;
      secondCate.value = 514;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '钱包';
    }
      ;
      break
    case ('515'):
    {
      firstCate.value = 5;
      secondCate.value = 515;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '电脑包';
    }
      ;
      break;
    case ('516'):
    {
      firstCate.value = 5;
      secondCate.value = 516;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '箱子';
    }
      ;
      break
    case ('517'):
    {
      firstCate.value = 5;
      secondCate.value = 517;
      aFirstCate.innerText = '服装包箱/ ';
      aSecondCate.innerText = '其他服饰';
    }
      ;
      break;
    case ('600'):
    {
      firstCate.value = 6;
      secondCate.value = 600;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '专业书籍';
    }
      ;
      break;
    case ('601'):
    {
      firstCate.value = 6;
      secondCate.value = 601;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '考试(GRE/托福/雅思)';
    }
      ;
      break;
    case ('602'):
    {
      firstCate.value = 6;
      secondCate.value = 602;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '小说/文学';
    }
      ;
      break;
    case ('603'):
    {
      firstCate.value = 6;
      secondCate.value = 603;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '工具书/辅导书';
    }
      ;
      break;
    case ('604'):
    {
      firstCate.value = 6;
      secondCate.value = 604;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '生活类书籍';
    }
      ;
      break;
    case ('605'):
    {
      firstCate.value = 6;
      secondCate.value = 605;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '学生文具';
    }
      ;
      break;
    case ('606'):
    {
      firstCate.value = 6;
      secondCate.value = 606;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '学习机';
    }
      ;
      break;
    case ('607'):
    {
      firstCate.value = 6;
      secondCate.value = 607;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '乐器';
    }
      ;
      break;
    case ('608'):
    {
      firstCate.value = 6;
      secondCate.value = 608;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '音响';
    }
      ;
      break;
    case ('609'):
    {
      firstCate.value = 6;
      secondCate.value = 609;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '耳机';
    }
      ;
      break;
    case ('610'):
    {
      firstCate.value = 6;
      secondCate.value = 610;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = 'U盘/硬盘';
    }
      ;
      break;
    case ('611'):
    {
      firstCate.value = 6;
      secondCate.value = 611;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = 'MP3/iPod/iWatch';
    }
      ;
      break;
    case ('612'):
    {
      firstCate.value = 6;
      secondCate.value = 612;
      aFirstCate.innerText = '图书音像/ ';
      aSecondCate.innerText = '其他图书';
    }
      ;
      break;
    case ('700'):
    {
      firstCate.value = 7;
      secondCate.value = 700;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '电灯';
    }
      ;
      break;
    case ('701'):
    {
      firstCate.value = 7;
      secondCate.value = 701;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '雨伞';
    }
      ;
      break;
    case ('702'):
    {
      firstCate.value = 7;
      secondCate.value = 702;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '梳子/镜子';
    }
      ;
      break;
    case ('703'):
    {
      firstCate.value = 7;
      secondCate.value = 703;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '家具';
    }
      ;
      break;
    case ('704'):
    {
      firstCate.value = 7;
      secondCate.value = 704;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '桌椅板凳';
    }
      ;
      break;
    case ('705'):
    {
      firstCate.value = 7;
      secondCate.value = 705;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '垫褥床铺';
    }
      ;
      break;
    case ('706'):
    {
      firstCate.value = 7;
      secondCate.value = 706;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '茶杯/水杯';
    }
      ;
      break;
    case ('707'):
    {
      firstCate.value = 7;
      secondCate.value = 707;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '布偶/娃娃';
    }
      ;
      break;
    case ('708'):
    {
      firstCate.value = 7;
      secondCate.value = 708;
      aFirstCate.innerText = '生活用品/ ';
      aSecondCate.innerText = '其他生活用品';
    }
      ;
      break;
    case ('800'):
    {
      firstCate.value = 8;
      secondCate.value = 800;
      aFirstCate.innerText = '二手车/ ';
      aSecondCate.innerText = '健身器材';
    }
      ;
      break;
    case ('801'):
    {
      firstCate.value = 8;
      secondCate.value = 801;
      aFirstCate.innerText = '体育器材/ ';
      aSecondCate.innerText = '网球';
    }
      ;
      break;
    case ('802'):
    {
      firstCate.value = 8;
      secondCate.value = 802;
      aFirstCate.innerText = '体育器材/ ';
      aSecondCate.innerText = '足球';
    }
      ;
      break;
    case ('803'):
    {
      firstCate.value = 8;
      secondCate.value = 803;
      aFirstCate.innerText = '体育器材/ ';
      aSecondCate.innerText = '篮球';
    }
      ;
      break;
    case ('804'):
    {
      firstCate.value = 8;
      secondCate.value = 804;
      aFirstCate.innerText = '体育器材/ ';
      aSecondCate.innerText = '乒乓球';
    }
      ;
      break;
    case ('805'):
    {
      firstCate.value = 8;
      secondCate.value = 805;
      aFirstCate.innerText = '体育器材/ ';
      aSecondCate.innerText = '羽毛球';
    }
      ;
      break;
    case ('806'):
    {
      firstCate.value = 8;
      secondCate.value = 806;
      aFirstCate.innerText = '体育器材/ ';
      aSecondCate.innerText = '户外用品';
    }
      ;
      break;
    case ('807'):
    {
      firstCate.value = 8;
      secondCate.value = 807;
      aFirstCate.innerText = '体育器材/ ';
      aSecondCate.innerText = '游泳用品';
    }
      ;
      break;
    case ('808'):
    {
      firstCate.value = 8;
      secondCate.value = 808;
      aFirstCate.innerText = '体育器材/ ';
      aSecondCate.innerText = '其他体育用品';
    }
      ;
      break;
    case ('9'):
    {
      firstCate.value = 9;
      secondCate.value = 0;
      aFirstCate.innerText = '未分类/ ';
    }
      ;
      break;


    default:
      ;
      break;
  }

}

/*  Setting building to change with campus */
function addOption(val, txt, child) {
  var opt = document.createElement("option");
  opt.text = txt;
  opt.value = val;
  child.options.add(opt);
}

function clickParentOpt() {

  var grand_parent = document.getElementById("campus");
  var g_value = grand_parent.value;
  var parent = document.getElementById("area");
  var p_len = parent.options.length;
  var child = document.getElementById("building");
  var c_len = child.options.length;

  if (c_len > 0) {
    for (var i = 0; i < c_len; i++) {
      child.options.remove(0);
    }
  }

  if (p_len > 0) {
    for (var i = 0; i < p_len; i++) {
      parent.options.remove(0);
    }
  }
  switch (g_value) {
    case "0":
    {
      $('#area').css('display', 'none');
      $('#building').css('display', 'none');
    };break;
    case "1":
    {
      $('#area').css('display', 'none');
      $('#building').css('display', 'none');
    };break;
    case "2":
    {
      $('#area').css('display', 'inline-block');
      addOption('0', '-请选择-', parent);
      addOption('1', '西1区', parent);
      addOption('2', '西2区', parent);
      addOption('3', '西3区', parent);
      addOption('4', '西4区', parent);
      addOption('5', '西5区', parent);
      addOption('6', '东1区', parent);
      addOption('7', '东2区', parent);
      addOption('8', '东3区', parent);
      addOption('9', '东4区', parent);
    }
      ;
      break;
    default:
      break;
  }
}

function clickChildOpt() {
  var parent = document.getElementById("area");
  var p_value = parent.value;
  var child = document.getElementById("building");
  var c_len = child.options.length;

  if (c_len > 0) {
    for (var i = 0; i < c_len; i++) {
      child.options.remove(0);
    }
  }

  switch (p_value) {
    case "0":
    {
      $('#building').css('display', 'none');
    };break;
    case "1":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('X08', 'X08', child);
      addOption('X10', 'X10', child);
      addOption('X11', 'X11', child);
      addOption('X12', 'X12', child);
      addOption('X13', 'X13', child);
      addOption('X14', 'X14', child);
      addOption('X15', 'X15', child);
      addOption('X16', 'X16', child);
      addOption('X17', 'X17', child);
      addOption('X18', 'X18', child);
      addOption('X19', 'X19', child);
      addOption('X20', 'X20', child);
      addOption('X21', 'X21', child);
      addOption('X22', 'X22', child);
      addOption('X23', 'X23', child);
      addOption('X24', 'X24', child);

    }
      ;
      break;
    case "2":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('X25', 'X25', child);
      addOption('X26', 'X26', child);
      addOption('X27', 'X27', child);
      addOption('X28', 'X28', child);
      addOption('X29', 'X29', child);
      addOption('X30', 'X30', child);
      addOption('X31', 'X31', child);
      addOption('X32', 'X32', child);
      addOption('X33', 'X33', child);
      addOption('X34', 'X34', child);
    }
      ;
      break;
    case "3":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('X35', 'X35', child);
      addOption('X36', 'X36', child);
      addOption('X37', 'X37', child);
      addOption('X38', 'X38', child);
      addOption('X39', 'X39', child);
      addOption('X40', 'X40', child);
      addOption('X41', 'X41', child);
      addOption('X42', 'X42', child);
      addOption('X43', 'X43', child);
      addOption('X44', 'X44', child);
      addOption('X45', 'X45', child);
      addOption('X46', 'X46', child);
      addOption('X47', 'X47', child);
      addOption('X48', 'X48', child);
      addOption('X49', 'X49', child);
    }
      ;
      break;
    case "4":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('X50', 'X50', child);
      addOption('X51', 'X51', child);
      addOption('X52', 'X52', child);
      addOption('X53', 'X53', child);
      addOption('X54', 'X54', child);
      addOption('X55', 'X55', child);
      addOption('X56', 'X56', child);
      addOption('X57', 'X57', child);
      addOption('X58', 'X58', child);
      addOption('X59', 'X59', child);
      addOption('X60', 'X60', child);
      addOption('X61', 'X61', child);
      addOption('X62', 'X62', child);
    }
      ;
      break;
    case "5":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('X63', 'X63', child);
      addOption('X64', 'X64', child);
      addOption('X65', 'X65', child);
      addOption('X66', 'X66', child);
      addOption('X67', 'X67', child);
      addOption('X68', 'X68', child);
      addOption('X69', 'X69', child);
      addOption('X70', 'X70', child);
    }
      ;
      break;
    case "6":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('D01', 'D01', child);
      addOption('D02', 'D02', child);
      addOption('D03', 'D03', child);
      addOption('D04', 'D04', child);
      addOption('D05', 'D05', child);
      addOption('D06', 'D06', child);
      addOption('D07', 'D07', child);
      addOption('D08', 'D08', child);
      addOption('D09', 'D09', child);
      addOption('D10', 'D10', child);
      addOption('D11', 'D11', child);
      addOption('D12', 'D12', child);
      addOption('D13', 'D13', child);
      addOption('D14', 'D14', child);
      addOption('zhenzhen', '蓁蓁楼', child);
    }
      ;
      break;
    case "7":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('D15', 'D15', child);
      addOption('D16', 'D16', child);
      addOption('D17', 'D17', child);
      addOption('D18', 'D18', child);
      addOption('D19', 'D19', child);
    }
      ;
      break;
    case "8":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('D20', 'D20', child);
      addOption('D21', 'D21', child);
      addOption('D22', 'D22', child);
      addOption('D23', 'D23', child);
      addOption('D24', 'D24', child);
      addOption('D25', 'D25', child);
      addOption('D26', 'D26', child);
      addOption('D27', 'D27', child);
    }
      ;
      break;
    case "9":
    {
      $('#building').css('display', 'inline-block');
      addOption('0','-请选择-',child);
      addOption('D28', 'D28', child);
      addOption('D29', 'D29', child);
      addOption('D30', 'D30', child);
      addOption('D31', 'D31', child);
      addOption('D32', 'D32', child);
      addOption('D33', 'D33', child);
    }
      ;
      break;
    default:
      break;
  }
  //if(p_value == "1"){
  //    addOption('D1', 'D1', child);
  //}else if(p_value == "2"){
  //    addOption('D1', 'D1', child);
  //}
}


function validateQuality(){
  var quality = $("#quality").find("option:selected").val();
  if(quality=='0'){
    $('#tip_quality').html('<i class="fa fa-exclamation-circle"> 必填项</i>');
    $('#tip_quality').css('color','red');
    return false;
  }else{
    $('#tip_quality').html('<i class="fa fa-check-circle"> 合格</i>');
    $('#tip_quality').css('color','green');
    return true;
  }
}

function validatePrice(){
  var price = $("#price").val();

  if(price.length>0){
    $('#tip_price').html('<i class="fa fa-check-circle"> 合格</i>');
    $('#tip_price').css('color','green');
    return true;
  }else{
    $('#tip_price').html('<i class="fa fa-exclamation-circle"> 必填项</i>');
    $('#tip_price').css('color','red');
    return false;
  }
}

function validateLocation(){
  var campus = $("#campus").find("option:selected").val();
  var area = $("#area").find("option:selected").val();
  var building = $("#building").find("option:selected").val();

  if(campus=='0'){
    $('#tip_location').html('<i class="fa fa-exclamation-circle"> 必填项</i>');
    $('#tip_location').css('color','red');
    return false;
  }else if(campus=='1'){
    $('#tip_location').html('<i class="fa fa-check-circle"> 合格</i>');
    $('#tip_location').css('color','green');
    return true;
  }else if(campus=='2'){
    if(area!='0'){
      if(building=='0'){
        $('#tip_location').html('<i class="fa fa-exclamation-circle"> 必填项</i>');
        $('#tip_location').css('color','red');
        return false;
      }else {
        $('#tip_location').html('<i class="fa fa-check-circle"> 合格</i>');
        $('#tip_location').css('color','green');
        return true;
      }
    }else{
      $('#tip_location').html('<i class="fa fa-exclamation-circle"> 必填项</i>');
      $('#tip_location').css('color','red');
      return false;
    }
  }
}

function validateTitle(){
  var title = $("#title").val();

  if(title.length>=6 && title.length<=50){
    $('#tip_title').html('<i class="fa fa-check-circle"> 合格</i>');
    $('#tip_title').css('color','green');
    return true;
  }else{
    $('#tip_title').html('<i class="fa fa-exclamation-circle"> 字数必须在6-50字之间</i>');
    $('#tip_title').css('color','red');
    return false;
  }
}

function validateDetail(){
  var detail = $("#detail").val();

  if(detail.length>=10 && detail.length<=10000){
    $('#tip_detail').html('<i class="fa fa-check-circle"> 合格</i>');
    $('#tip_detail').css('color','green');
    return true;
  }else{
    $('#tip_detail').html('<i class="fa fa-exclamation-circle"> 字数必须在10-2000字之间</i>');
    $('#tip_detail').css('color','red');
    return false;
  }
}

$(document).ready(function () {
  //$("#file_img").fileinput({
  //  uploadUrl: '#', // you must set a valid URL here else you will get an error
  //  allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg', 'bmp'],
  //  overwriteInitial: false,
  //  maxFileSize: 4000,//KB
  //  maxFilesNum: 6,//file number
  //  //allowedFileTypes: ['image', 'video', 'flash'],
  //  slugCallback: function (filename) {
  //    return filename.replace('(', '_').replace(']', '_');
  //  }
  //});

  $('#area').css('display', 'none');
  $('#building').css('display', 'none');

  //blur
  $('#quality').blur(function () {
    validateQuality();
  });

  $('#price').blur(function () {
    validatePrice();
  });

  $('#campus').blur(function () {
    validateLocation();
  });

  $('#area').blur(function () {
    validateLocation();
  });

  $('#building').blur(function () {
    validateLocation();
  });

  $('#title').blur(function () {
    validateTitle();
  });

  $('#detail').blur(function () {
    validateDetail();
  });


  $('#btn_post').click(function(){
    var is_success_q=true;
    var is_success_p=true;
    var is_success_l=true;
    var is_success_t=true;
    var is_success_d=true;
    is_success_q = validateQuality();
    is_success_p = validatePrice();
    is_success_l = validateLocation();
    is_success_t = validateTitle();
    is_success_d = validateDetail();

    if(is_success_q==true && is_success_p==true && is_success_l==true && is_success_t==true && is_success_d==true){

      $('#field_types').css('opacity', '0').css('display', 'none');
      $('#field_detail').css('opacity', '0').css('display', 'none');
      $('#field_success').css('opacity', '1').css('display', 'block');

      countDown(5,'mainpage.html');

      setTimeout(function () {
        document.forms['post_form'].submit();
      }, 5000);


    }else{
      return false;
    }
  });

});
