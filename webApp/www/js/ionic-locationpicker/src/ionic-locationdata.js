var app = angular.module('ionic-locationdata', ['ionic']);
app.service('LocationPickerService', function () {
  this.locationList = [
    {
      "name":'徐汇校区',
      "sub":[{"name":""}],
      "type":0
    },
    {
      "name":'闵行校区',
      "sub":[
        {"name":"西1区",
          "sub":[
            {
              "name": "X08",
            },
            {
              "name": "X10",
            },
            {
              "name": "X11",
            },
            {
              "name": "X12",
            },
            {
              "name": "X13",
            },
            {
              "name": "X14",
            },
            {
              "name": "X15",
            },
            {
              "name": "X16",
            },
            {
              "name": "X17",
            },
            {
              "name": "X18",
            },
            {
              "name": "X19",
            },
            {
              "name": "X20",
            },
            {
              "name": "X21",
            },
            {
              "name": "X22",
            },
            {
              "name": "X23",
            },
            {
              "name":"X24",
            }],
          "type":0
        },
        {"name":"西2区",
          "sub":[
            {
              "name": "X25",
            },
            {
              "name": "X26",
            },
            {
              "name": "X27",
            },
            {
              "name": "X28",
            },
            {
              "name": "X29",
            },
            {
              "name": "X30",
            },
            {
              "name": "X31",
            },
            {
              "name": "X32",
            },
            {
              "name": "X33",
            },
            {
              "name": "X34",
          }],
          "type":0
        },
        {"name":"西3区",
          "sub":[
            {
              "name": "X35",
            },
            {
              "name": "X36",
            },
            {
              "name": "X37",
            },
            {
              "name": "X38",
            },
            {
              "name": "X39",
            },
            {
              "name": "X40",
            },
            {
              "name":"X41",
            },
            {
              "name": "X42",
            },
            {
              "name": "X43",
            },
            {
              "name": "X44",
            },
            {
              "name": "X45",
            },
            {
              "name": "X46",
            },
            {
              "name": "X47",
            },
            {
              "name": "X48",
            },
            {
              "name":"X49",
            }],
          "type":0
        },
        {"name":"西4区",
          "sub":[
            {
              "name": "X50",
            },
            {
              "name": "X51",
            },
            {
              "name": "X52",
            },
            {
              "name": "X53",
            },
            {
              "name": "X54",
            },
            {
              "name": "X55",
            },
            {
              "name": "X56",
            },
            {
              "name": "X57",
            },
            {
              "name": "X58",
            },
            {
              "name": "X59",
            },
            {
              "name": "X60",
            },
            {
              "name": "X61",
            },
            {
              "name":"X62",
            }],
          "type":0
        },
        {"name":"西5区",
          "sub":[
            {
              "name": "X63",
            },
            {
              "name": "X64",
            },
            {
              "name": "X65",
            },
            {
              "name": "X66",
            },
            {
              "name": "X67",
            },
            {
              "name": "X68",
            },
            {
              "name": "X69",
            },
            {
              "name":"X70",
            }],
          "type":0
        },
        {"name":"东1区",
          "sub":[
            {
              "name": "D01",
            },
            {
              "name": "D02",
            },
            {
              "name": "D03",
            },
            {
              "name": "D04",
            },
            {
              "name": "D05",
            },
            {
              "name": "D06",
            },
            {
              "name": "D07",
            },
            {
              "name": "D08",
            },
            {
              "name": "D09",
            },
            {
              "name": "D10",
            },
            {
              "name": "D11",
            },
            {
              "name": "D12",
            },
            {
              "name": "D13",
            },
            {
            "name":"D14",
            }],
          "type":0
        },
        {"name":"东2区",
          "sub":[
            {
              "name": "D15",
            },
            {
              "name": "D16",
            },
            {
              "name": "D17",
            },
            {
              "name": "D18",
            },
            {
              "name":"D19",
            }],
          "type":0
        },
        {"name":"东3区",
          "sub":[
            {
              "name": "D20",
            },
            {
              "name": "D21",
            },
            {
              "name": "D22",
            },
            {
              "name": "D23",
            },
            {
              "name": "D24",
            },
            {
              "name": "D25",
            },
            {
              "name": "D26",
            },
            {
              "name":"D27",
            }],
          "type":0
        },
        {
          "name":"东4区",
          "sub":[
            {
              "name": "D28",
            },
            {
              "name": "D29",
            },
            {
              "name": "D30",
            },
            {
              "name": "D31",
            },
            {
              "name": "D32",
            },
            {
              "name":"D33",
            }],
          "type":0
        }],
      "type":1
    },
  ]
});
