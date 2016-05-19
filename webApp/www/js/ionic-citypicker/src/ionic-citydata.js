var app = angular.module('ionic-citydata', ['ionic']);
app.service('CityPickerService', function () {
  this.cityList = [
    {
      "name":'二手车',
      "sub":[
        {"name":"普通自行车"},
        {"name":"山地自行车"},
        {"name":"折叠自行车"},
        {"name":"迷你自行车"},
        {"name":"公路自行车"},
        {"name":"电动车"},
        {"name":"其他车"}],
      "type":1
    },
    {
      "name":'二手手机',
      "sub":[
        {"name":"苹果"},
        {"name":"三星"},
        {"name":"小米"},
        {"name":"华为"},
        {"name":"HTC"},
        {"name":"诺基亚"},
        {"name":"魅族"},
        {"name":"索尼"},
        {"name":"中兴"},
        {"name":"LG"},
        {"name":"联想"},
        {"name":"酷派"},
        {"name":"其他手机"}],
      "type":1
    },
    {
      "name":'PC/PAD',
      "sub":[
        {"name":"台式机"},
        {"name":"ThinkPad/IBM"},
        {"name":"苹果(Mac)"},
        {"name":"联想(Lenovo)"},
        {"name":"戴尔(DELL)"},
        {"name":"华硕(ASUS)"},
        {"name":"惠普(HP)"},
        {"name":"索尼(Sony)"},
        {"name":"三星(Samsung)"},
        {"name":"其他电脑"},
        {"name":"iPad"},
        {"name":"Surface"},
        {"name":"三星平板"},
        {"name":"小米平板"},
        {"name":"联想平板"},
        {"name":"其他平板"}],
      "type":1
    },
    {
      "name":'二手家电',
      "sub":[
        {"name":"洗衣机"},
        {"name":"厨房电器"},
        {"name":"冰箱/冰柜"},
        {"name":"数码相机"},
        {"name":"其他家电"}],
      "type":1
    },
    {
      "name":'服装包箱',
      "sub":[
        {"name":"T恤"},
        {"name":"衬衫"},
        {"name":"外套"},
        {"name":"裤子"},
        {"name":"西装"},
        {"name":"裙子"},
        {"name":"休闲鞋"},
        {"name":"运动鞋"},
        {"name":"帆布鞋"},
        {"name":"高跟鞋"},
        {"name":"皮鞋"},
        {"name":"单肩包"},
        {"name":"双肩包"},
        {"name":"书包"},
        {"name":"钱包"},
        {"name":"电脑包"},
        {"name":"箱子"},
        {"name":"其他服饰"}],
      "type":1
    },
    {
      "name":'图书音像',
      "sub":[
        {"name":"专业书籍"},
        {"name":"考试(GRE/托福/雅思)"},
        {"name":"工具书/辅导书"},
        {"name":"生活类书籍"},
        {"name":"学生文具"},
        {"name":"学习机"},
        {"name":"乐器"},
        {"name":"音响"},
        {"name":"耳机"},
        {"name":"U盘/硬盘"},
        {"name":"MP3/iPod/iWatch"},
        {"name":"其他图书"}],
      "type":1
    },
    {
      "name":'生活用品',
      "sub":[
        {"name":"电灯"},
        {"name":"雨伞"},
        {"name":"梳子/镜子"},
        {"name":"家具"},
        {"name":"桌椅板凳"},
        {"name":"垫褥床铺"},
        {"name":"茶杯/水杯"},
        {"name":"布偶/娃娃"},
        {"name":"其他生活用品"}],
      "type":1
    },
    {
      "name":'体育器材',
      "sub":[
        {"name":"健身器材"},
        {"name":"网球"},
        {"name":"足球"},
        {"name":"篮球"},
        {"name":"乒乓球"},
        {"name":"羽毛球"},
        {"name":"户外用品"},
        {"name":"游泳用品"},
        {"name":"其他体育用品"}],
      "type":1
    },
    {
      "name":'未分类',
      "sub":[{"name":""}],
      "type":0
    },
  ]
});
