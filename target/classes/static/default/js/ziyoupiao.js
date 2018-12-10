/* 初始化购方信息autoComplete数组  */
// function initAutoCompleteGouxiao(objs) {
//     var mGfmc = new Array();
//     var mGfsh = new Array();
//     var mGfdzdh = new Array();
//     var mGfyhzh = new Array();
//     for (var i = 0; i < objs.length; i++) {
//         mGfmc.push(objs[i].gfmc);
//         mGfsh.push(objs[i].gfsh);
//         mGfdzdh.push(objs[i].dzdh);
//         mGfyhzh.push(objs[i].yhzh);
//     }
// //        console.info("init ---");
// //        console.info(mGfmc);
//
// }
/* 初始化购方信息autoComplete数组  */
function initAutoCompleteGouxiao(objs) {
    var mGfmc = new Array();
    var mGfsh = new Array();
    var mGfdzdh = new Array();
    var mGfyhzh = new Array();
    for (var i = 0; i < objs.length; i++) {
        mGfmc.push(objs[i].gfmc);
        mGfsh.push(objs[i].gfsh);
        mGfdzdh.push(objs[i].dzdh);
        mGfyhzh.push(objs[i].yhzh);

    }
    /** 购方名称模糊搜索 */
    $('#xxfp_gfmc').autocomplete({
        source: mGfmc,
        width: 200,
        //height: 30,
        minChars: 1,
        autoFill: true,
        scrollHeight: 100,
        select: function (event, ui) {
            console.log(1);
            var mc = ui.item.value;
            for (var j = 0; j < objs.length; j++) {
                console.log(objs);
                if (mc == objs[j].gfmc) {
                    var gfmc = objs[j].gfmc;
                    var gfsh = objs[j].gfsh;
                    var gfdzdh = objs[j].dzdh;
                    var gfyhzh = objs[j].yhzh;
                    $("#xxfp_gfmc").val(gfmc);
                    $("#xxfp_gfsh").val(gfsh);
                    $("#xxfp_gfdzdh").val(gfdzdh);
                    $("#xxfp_gfyhzh").val(gfyhzh);
                    break;
                }
            }
        }
    });
    /* 根据 税 号   模糊搜索 */
    $('#xxfp_gfsh').autocomplete({
        source: mGfsh,
        width: 200,
        minChars: 1,
        autoFill: true,
        scrollHeight: 100,
        select: function (event, ui) {
            var gfsh = ui.item.value;
            for (var j = 0; j < objs.length; j++) {
                if (gfsh == objs[j].gfsh) {
                    var gfmc = objs[j].gfmc;
                    var gfsh = objs[j].gfsh;
                    var gfdzdh = objs[j].dzdh;
                    var gfyhzh = objs[j].yhzh;
                    $("#xxfp_gfmc").val(gfmc);
                    $("#xxfp_gfsh").val(gfsh);
                    $("#xxfp_gfdzdh").val(gfdzdh);
                    $("#xxfp_gfyhzh").val(gfyhzh);
                    break;
                }
            }
        }
    });
    /* 根据 地址、电话 模糊搜索 */
    $('#xxfp_gfdzdh').autocomplete({
        source: mGfdzdh,
        width: 200,
        minChars: 1,
        autoFill: true,
        scrollHeight: 100,
        select: function (event, ui) {
            var dzdh = ui.item.value;
            for (var j = 0; j < objs.length; j++) {
                if (dzdh == objs[j].dzdh) {
                    var gfmc = objs[j].gfmc;
                    var gfsh = objs[j].gfsh;
                    var gfdzdh = objs[j].dzdh;
                    var gfyhzh = objs[j].yhzh;
                    $("#xxfp_gfmc").val(gfmc);
                    $("#xxfp_gfsh").val(gfsh);
                    $("#xxfp_gfdzdh").val(gfdzdh);
                    $("#xxfp_gfyhzh").val(gfyhzh);
                    break;
                }
            }
        }
    });
    /* 根据开户行、银行卡号模糊搜索 */
    $('#xxfp_gfyhzh').autocomplete({
        source: mGfyhzh,
        width: 200,
        minChars: 1,
        autoFill: true,
        scrollHeight: 100,
        select: function (event, ui) {
            var kaihuhang = ui.item.value;
            for (var j = 0; j < objs.length; j++) {
                if (kaihuhang == null) {
                    continue;
                }
                ////console.info(kaihuhang);
                if (kaihuhang == objs[j].yhzh) {
                    var gfmc = objs[j].gfmc;
                    var gfsh = objs[j].gfsh;
                    var gfdzdh = objs[j].dzdh;
                    var gfyhzh = objs[j].yhzh;
                    $("#xxfp_gfmc").val(gfmc);
                    $("#xxfp_gfsh").val(gfsh);
                    $("#xxfp_gfdzdh").val(gfdzdh);
                    $("#xxfp_gfyhzh").val(gfyhzh);
                    break;
                }
            }
        }
    });
}


// /**
//  * 保存自由票信息到数据库
//  */
// $('#saveInvTemp').on('click', function () {
//     console.log(1);
//     var gfmc = $("#xxfp_gfmc").val();
//     var gfsh = $("#xxfp_gfsh").val();
//     var gfdzdhg = $("#xxfp_gfdzdhg").val();
//     var gfyhzh = $("#xxfp_gfyhzh").val();
//
//
//     var xfmc = $("#xxfp_xfmc").val();
//     var xfsh = $("#xxfp_xfsh").val();
//     var xfdzdh = $("#xxfp_xfdzdh").val();
//     var xfyhzh = $("#xxfp_xfyhzh").val();
//
//
//     var skr = $("#xxfp_skr").val();
//     var fhr = $("#xxfp_fhr").val();
//     var kpr = $("#xxfp_kpr").val();
//
//
//     var hjje = $("#xxfp_jehj").html();
//     var hjse = $("#xxfp_sehj").html();
//     var jshj = $("#xxfp_jshj").html();
//
//     var bz = $("#xxfp_bz").val();
//
//     //获取各行商品信息
//     var array = new Array();
//     fpmxArrayPush(array);
//
//     // 对象赋值开始
//     var xxfp = {};
//     var defaultType = '[[${defaultType}]]';//默认发票票种
//     xxfp.fpzl = defaultType;
//     var date = new Date();
//     xxfp.czsj = date;
//     xxfp.nsrsbh = xfsh + "";
//     xxfp.fjh = "";
//     xxfp.gfmc = gfmc;
//     xxfp.gfsh = gfsh;
//     xxfp.gfdzdh = gfdzdhg;
//     xxfp.gfyhzh = gfyhzh;
//
//     xxfp.xfmc = xfmc;
//     xxfp.xfsh = xfsh;
//     xxfp.xfdzdh = xfdzdh;
//     xxfp.xfyhzh = xfyhzh;
//
//
//     xxfp.skr = skr;
//     xxfp.fhr = fhr;
//     xxfp.kpr = kpr;
//     xxfp.hjje = Number(hjje);
//     xxfp.hjse = Number(hjse);
//     xxfp.jshj = Number(jshj);
//     xxfp.bz = bz;
//     if (array.length > 8) {
//         xxfp.qdbz = 1;
//     }
//     else {
//         xxfp.qdbz = 0;
//     }
//     xxfp.xxfpMxs = array;
//     console.log(array);
//     var json = JSON.stringify(xxfp);
//
//     console.info(json);
//
//     $.ajax({
//         type: 'POST',
//         url: '/noc/zyp/saveInvTemp',
//         cache: false,
//         contentType: 'application/json;charset=utf-8',
//         data: json,
//         dataType: "json",
//         success: function (data) {
//             console.info(data);
//             if (data.flag) {
//                 //window.open(data.obj);
//                 alert(data.message);
//                 window.location.href = '<%=basePath%>pages/business/xxfp/show.jsp?msg=' + data.obj;
//
//             } else {
//                 alert(data.obj);
//
//             }
//         },
//         error: function (error) {
//             console.info(error);
//             alert("未找到该页面");
//             return;
//         }
//
//     });
//
// });

//添加销项发票明细
function fpmxArrayPush(array) {
    var dataRight = 1;
    // qdbz = 清单标识
    console.log("=========~" + $(".fill-tr").length);
    if ($(".fill-tr").length > 8) {
        $(".fill-tr").each(function (index, element) {
            console.log(index);
            $tr = $(this);
            var spmc = $tr.find(".hwmc").val();
            var hsje = Number($tr.find(".hsje").val()).toFixed(2);
            var ggxh = $tr.find(".ggxh").val();
            var ssflbm = $tr.find(".ssflbm").val();
            var jldw = $tr.find(".dw").val();
            var number = $tr.find(".xmsl").val();
            var hsdj = $tr.find(".hsdj").val();
            var slv = $tr.find(".sl").val();
            var se = $tr.find(".se").text();
            var yhzclx = $tr.find(".yhzclx").val();
            //优惠政策内容
            var yhzcnr = $tr.find(".yhzcnr").val();

            //当一行数据都为空时
            if (spmc == ""|| jldw == ""|| hsdj == "" || hsje == "" || slv == "" || se == "") {
                dataRight = 0;
                return false;
            }
            //判断金额部分字段，全部为数字
            if (verifyNum(hsje) || verifyNum(number) || verifyNum(hsdj) || verifyNum(slv) || verifyNum(se)) {
                dataRight = 0;
                return false;
            }
            var xxfpJson = {
                "pfhh": index,
                "taxRate": slv,
                "se": Number(se),
                "goodsName": spmc,
                "goodsSsflbm": ssflbm,
                "goodsMode": ggxh,
                "goodsUnit": jldw,
                "goodsAmount": Number(number),
                "hsdj": hsdj,
                "moneyIncludeTax": hsje,
                "yhzclx": yhzclx,
                "yhzcnr": yhzcnr,
                "fphxz": "0"
                // "pricecard":pricecard
                /*,
                "djmxxh":(index+1)+"",//单据明细序号
                "fpmxxh":(index+1)+"",//单据明细序号
*/
            };
            array.push(xxfpJson);
            console.log("=========xxfpJson:" + xxfpJson);
        });
        return dataRight;
    } else {
        $(".fill-tr").each(function (index, element) {
            //console.log(index);
            $tr = $(this);
            var spmc = $tr.find(".hwmc").val();
            var hsje = $tr.find(".hsje").val();
            var ggxh = $tr.find(".ggxh").val();
            var ssflbm = $tr.find(".ssflbm").val();
            var jldw = $tr.find(".dw").val();
            var number = $tr.find(".xmsl").val();
            var hsdj = $tr.find(".hsdj").val();
            var slv = $tr.find(".sl").val();
            var se = $tr.find(".se").text();
            var yhzclx = $tr.find(".yhzclx").val();
            //优惠政策内容
            var yhzcnr = $tr.find(".yhzcnr").val();
            //当一行数据都为空时
            if (spmc == ""|| jldw == ""|| hsdj == "" || hsje == "" || slv == "" || se == "") {
                dataRight = 0;
                return false;
            }
            //判断金额部分字段，全部为数字
            if (verifyNum(hsje) || verifyNum(number) || verifyNum(hsdj) || verifyNum(slv) || verifyNum(se)) {
                dataRight = 0;
                return false;
            }
            var xxfpJson = {
                "pfhh": index,
                "taxRate": slv,
                "se": Number(se),
                "goodsName": spmc,
                "goodsSsflbm": ssflbm,
                "goodsMode": ggxh,
                "goodsUnit": jldw,
                "goodsAmount": Number(number),
                "hsdj": hsdj,
                "moneyIncludeTax": hsje,
                "yhzclx": yhzclx,
                "yhzcnr": yhzcnr,
                "fphxz": "0"
            };
            array.push(xxfpJson);
            console.log("=========xxfpJson:" + xxfpJson);
        });
        return dataRight;
    }
}

function verifyNum(num) {
    console.log(num);
    console.log(isNaN(num));
    if (num == '' || num == null) {
        return true;
    }
    return isNaN(num);
}
