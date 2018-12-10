'use strict';
var basepath = "http://192.168.200.18:8800/";
if(!Array.prototype.map) {
	Array.prototype.map = function(callback, args) {
		var arg, arr, index;

		if(this == null) {
			throw new TypeError('this is null or not defined');
		}

		var obj = new Object(this);
		var len = obj >>> 0;

		if(Object.prototype.toString.call(callback) != '[object Function]') {
			throw new TypeError(callback + 'is not a function');
		}

		if(args) {
			arg = args;
		}
		arr = new Array(len);
		index = 0;

		while(index < len) {
			var kValue, mappedValue;
			if(index in obj) {
				kValue = obj[index];
				mappedValue = callback.call(arg, kValue, index, obj);
				arr[index] = mappedValue;
			}
			index++;
		}
		return arr;
	}
}

if(!Array.indexOf) {  
	Array.prototype.indexOf = function(obj) {    
		for(var i = 0; i < this.length; i++) {      
			if(this[i] == obj) {        
				return i;      
			}
		}    
		return -1;  
	}
}
layui.config({
    base: '/static/js/expansion/' //假设这是你存放拓展模块的根目录
});

$('.search-form .search-condition-box li').on('click', function() {
	$(this).addClass('active').siblings().removeClass('active');
});

var urlList = ['home'];
$('#left-menu a[url]').on('click', function() {
	if($(this).siblings().length != 0) return;
	var $a = $(this);
	var url = $a.attr('url');
	var menuId = $a.attr('menuId');
	tabMenu.addPage(menuId, url, $a.text());

});

$('#tabs-list').on('click', 'li[url]', function() {
	tabMenu.showIframe($(this).attr('url'));
});
$('#tabs-list').on('click', '.layui-tab-close', function() {
	tabMenu.removePage($(this).parent('li').attr('url'));
});

$('#prev-tab').on('click', function() {
	tabMenu.tabLocated('first');
});
$('#next-tab').on('click', function() {
	tabMenu.tabLocated('last');
});

var tabs = sessionStorage.getItem("tabs") && sessionStorage.getItem("tabs").split(",") || [],
    activeTab = sessionStorage.getItem("activeTab") || "",
    topMenuId = $('#left-menu a[menuId="' + activeTab + '"]').parents(".layui-nav").attr("menuId");
var menuIdList = [],
    menuDataList = [];

var tabMenu = {
    getPageUrl: function(url) {
        return  url;
    },
    saveActiveApps: function(appId) {
        sessionStorage.setItem('activeApp', appId)
    },
    saveTabs: function(tabArr) {
        sessionStorage.setItem("tabs", tabArr.toString())
    },
    saveActiveTab: function(tab) {
        sessionStorage.setItem("activeTab", tab)
    },
    tabLocated: function(index) {
        if("first" !== index) {
            var contWidth = $("#tabs-list").width();
            if("last" === index) {
                var tabsWidth = $("#tabs-list")[0].scrollWidth;
            } else {
                tabsWidth = 0;
                for(var i = 0; i < index + 1; i++) {
                    tabsWidth += $("#tabs-list li:eq(" + i + ")").outerWidth();
                }
            }
            $("#tabs-list").css("left", contWidth < tabsWidth ? contWidth - tabsWidth : 0);
        } else {
            $("#tabs-list").css("left", 0);
        }
    },
    showPage: function(menuId) {
        $('#tabs-list li[menuId="' + menuId + '"]').addClass("layui-this").siblings("li").removeClass("layui-this");
        tabMenu.showIframe(menuId);
    },
    showIframe: function(menuId) {
        tabMenu.tabLocated(menuIdList.indexOf(menuId));
        $('.main-container[menuId="' + menuId + '"]').addClass("show").siblings().removeClass("show");
        tabMenu.saveActiveTab(menuId);
    },
    addPage: function(menuId, url, name, activeTab) {
        if(menuIdList.indexOf(menuId) != -1) { //已打开页面
            tabMenu.showPage(menuId);
            return;
        }
        if(menuIdList.length >= 12) {
            layui.use('layer', function() {
                var layer = layui.layer;
                layer.alert('打开页面数量过多，请关闭不需要的标签页之后再打开新页面！');
            });
            return;
        }
        menuIdList.push(menuId);
        menuDataList.push(menuId + '&' + url + '&' + name);

        $('#tabs-list').append('<li menuId="' + menuId + '">' + name + '<i class="layui-icon layui-unselect layui-tab-close">ဆ</i>');
        $('#main').append('<iframe class="main-container" menuId="' + menuId + '" src="' + tabMenu.getPageUrl(url) + '" frameborder="no" border="0"></iframe>');
        if(arguments.length != 4) {
            $('#tabs-list li.layui-this', parent.document).removeClass('layui-this');
            $('.main-container.show').removeClass('show');

            $('#tabs-list li:last-child').addClass('layui-this');
            $('#main .main-container:last-child').addClass('show');
            tabMenu.saveActiveTab(menuId);
            tabMenu.saveTabs(menuDataList);
        } else {
            if(activeTab == menuId) {
                $('#tabs-list li:last-child').addClass('layui-this');
                $('#main .main-container:last-child').addClass('show');
            }
            return;
        }
    },
    removePage: function(menuId) {
        var index = menuIdList.indexOf(menuId);
        menuIdList.splice(index, 1), menuDataList.splice(index, 1);
        var $closeTab = $('#tabs-list li[menuId="' + menuId + '"]');
        if($closeTab.hasClass("layui-this")) {
            var showMenuId = $closeTab.prev("li").attr("menuId");
            $closeTab.prev("li").addClass("layui-this");
            tabMenu.showIframe(showMenuId);
        }
        $('#tabs-list li[menuId="' + menuId + '"],.main-container[menuId="' + menuId + '"]').remove();
        tabMenu.saveTabs(menuDataList);
    }
};

var dom = function(domStr, pageType) {
	return(pageType === 'children') ? $(domStr, parent.document) : $(domStr);
}
/** 隐藏页面 批量关闭标签 */
$(document).on('click', function() {
    $('#tabs-operation', parent.document).addClass('hide');
});

$('#other-tab').on('click', function(e) {
    e.stopPropagation();
    $('#tabs-operation').removeClass('hide');
});
//关闭全部标签页
$('#close-all-tab').on('click', function() {
    var menuIdList = getSavedMenuIdList();
    for (var i = 0, len = menuIdList.length; i < len; i ++) {
        tabMenu.removePage(menuIdList[i]);
    }
});
//关闭其他标签页
$('#close-other-tab').on('click', function() {
    var menuIdList = getSavedMenuIdList();
    var activeId = sessionStorage.getItem('activeTab');
    menuIdList.remove(activeId);

    for (var i = 0, len = menuIdList.length; i < len; i ++) {
        tabMenu.removePage(menuIdList[i]);
    }
});
//关闭当前标签页
$('#close-active-tab').on('click', function() {
    var menuId = $('#tabs-list li.layui-this').attr('menuId');
    if (menuId != "home")
        tabMenu.removePage(menuId);
});
Array.prototype.indexOf = function(val) {
    for(var i = 0; i < this.length; i++) {
        if(this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if(index > -1) {
        this.splice(index, 1);
    }
};

function getSavedMenuIdList() {
    var tabs = sessionStorage.getItem("tabs") || "";
    var tabArr = tabs.split(",");
    var menuIdArr = [];
    for (var i = 0, len = tabArr.length; i < len; i ++) {
        menuIdArr.push(tabArr[i].split('&')[0]);
    }
    menuIdArr.remove('home');
    return menuIdArr;
}

var nemo = {
};

function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
};
// Generate a pseudo-GUID by concatenating random hexadecimal.
function uuid() {
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}
/** 数字金额大写转换(可以处理整数,小数,负数)  有bug  66.66 显示为66.65*/
function smalltoBIG1(n)
{
    var fraction = ['角', '分'];
    var digit = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'];
    var unit = [ ['元', '万', '亿'], ['', '拾', '佰', '仟']  ];
    var head = n < 0? '欠': '';
    n = Math.abs(n);

    var s = '';

    for (var i = 0; i < fraction.length; i++)
    {
        s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
    }
    s = s || '整';
    n = Math.floor(n);

    for (var i = 0; i < unit[0].length && n > 0; i++)
    {
        var p = '';
        for (var j = 0; j < unit[1].length && n > 0; j++)
        {
            p = digit[n % 10] + unit[1][j] + p;
            n = Math.floor(n / 10);
        }
        s = p.replace(/(零.)*零$/, '').replace(/^$/, '零')  + unit[0][i] + s;
    }
    return head + s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/, '零元整');
}
/** 数字金额大写转换(可以处理整数,小数,负数) */
function smalltoBIG(num) {
    var chnNumChar = ["零", "壹", "贰", "仨", "肆", "伍", "陆", "柒", "捌", "玖"];
    var chnUnitSection = ["", "万", "亿", "万", "亿亿"];
    var chnUnitChar = ["", "拾", "佰", "仟"];

    var numToChn = function(num) {
        var index = num.toString().indexOf(".");
        if(index === -1) {
            return '元';
        }
        var str = num.toString().slice(index);
        var a = "元";
        for(var i = 1; i < str.length; i++) {
            a += chnNumChar[parseInt(str[i])] + ((i == 1) ? "角" : "分");
        }
        return a;
    }

    //定义在每个小节的内部进行转化的方法，其他部分则与小节内部转化方法相同
    function sectionToChinese(section) {
        var str = '',
            chnstr = '',
            zero = false,
            count = 0; //zero为是否进行补零， 第一次进行取余由于为个位数，默认不补零
        while(section > 0) {
            var v = section % 10; //对数字取余10，得到的数即为个位数
            if(v == 0) { //如果数字为零，则对字符串进行补零
                if(zero) {
                    zero = false; //如果遇到连续多次取余都是0，那么只需补一个零即可
                    chnstr = chnNumChar[v] + chnstr;
                }
            } else {
                zero = true; //第一次取余之后，如果再次取余为零，则需要补零
                str = chnNumChar[v];
                str += chnUnitChar[count];
                chnstr = str + chnstr;
            }
            count++;
            section = Math.floor(section / 10);
        }
        return chnstr;
    }
    var chnSymbol = "";
    //定义整个数字全部转换的方法，需要依次对数字进行10000为单位的取余，然后分成小节，按小节计算，当每个小节的数不足1000时，则需要进行补零
    var a = numToChn(num);
    if(num >= 0) {
        num = Math.floor(num);
    } else {
        chnSymbol = '（负数）';
        num = -Math.ceil(num);
    }

    var unitPos = 0;
    var strIns = '',
        chnStr = '';
    var needZero = false;

    if(num === 0) {
        return chnNumChar[0];
    }
    while(num > 0) {
        var section = num % 10000;
        if(needZero) {
            chnStr = chnNumChar[0] + chnStr;
        }
        strIns = sectionToChinese(section);
        strIns += (section !== 0) ? chnUnitSection[unitPos] : chnUnitSection[0];
        chnStr = strIns + chnStr;
        needZero = (section < 1000) && (section > 0);
        num = Math.floor(num / 10000);
        unitPos++;
    }
    return chnSymbol + chnStr + a;
}

/** 获取当前时间  xxxx年xx月xx日*/
function getNow() {
    var da = new Date();
    var year = da.getFullYear()+'年';
    var month = da.getMonth()+1+'月';
    var date = da.getDate()+'日';
    //以上就完成了当前时间的格式设置，接下来就是给span标签赋值：
    return [year,month,date].join(' ');
}
/** 获取当前年月  xxxx-xx */
function getYearMonth() {
    var da = new Date();
    var year = da.getFullYear()+"";
    var month = da.getMonth()+1+"";
    //以上就完成了当前时间的格式设置，接下来就是给span标签赋值：
    return [year,month].join('-');
}
/** 小数转百分数*/
function decimal2percent(decimal) {
    var a = parseFloat(decimal);
    if(0 != a){
        return a*100+"%";
    }else{
        return "0";
    }
}

/** 日期验证 yyyy-mm*/
function checkDateYYYYMM(fname){
    var sc = $("#"+fname);
    var s = sc.val();
    if (sc==null){
        // alert("Element is null");
        return false;
    }
    var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02]))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02)$/;
    if(!s.match(reg)){
        // alert("false");
        return false;
    }else{
        // alert("true");
        return true;
    }
}
/** 日期验证 yyyy-mm-dd*/
function checkDate(fname){
    var sc = $("#"+fname);
    var s = sc.val();
    if (sc==null){
        alert("Element is null");
        return true;
    }
    var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    if(!s.match(reg)){
        alert("false");
    }else{
        alert("true");
    }
}
/** 键盘输入数字过滤验证 能输入正数 负数*/
function numberKeyPress_plusAndMinus(ob, round) {
    var str;
    if(round) {
        var str = new RegExp("^[\\+\\-]?\\d*?\\.?\\d{0," + round + "}$", "g");
    }  else {
        str = /^[\+\-]?\d*?\.?\d*?$/;
    }
    if(!ob.value.match(str)) {
        if(undefined == ob.t_value) {
            ob.t_value = "";
        }
        ob.value = ob.t_value;
    } else {
        ob.t_value = ob.value;
    }
    if(ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) {
        ob.o_value = ob.value;
    }
}
/** 键盘输入数字过滤验证 只能输入正数 */
function numberKeyPress(ob, round) {
    var str;
    if(round) {
        var str = new RegExp("^\\d*?\\.?\\d{0," + round + "}$", "g");
    }  else {
        str = /^\d*?\.?\d*?$/;
    }
    if(!ob.value.match(str)) {
        if(undefined == ob.t_value) {
            ob.t_value = "";
        }
        ob.value = ob.t_value;
    } else {
        ob.t_value = ob.value;
    }
    if(ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) {
        ob.o_value = ob.value;
    }
}

function lpad(desstr, padchar, lenint) {
    var result = "";
    for (var i = 1; i <= lenint - desstr.length; i++) {
        result += padchar
        //document.write("result=" + result + "<br/>")
    }
    result += desstr
    return result;
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}