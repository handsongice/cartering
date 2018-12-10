'use strict';

layui.use(['element', 'layer'], function () {
    var element = layui.element
    ,layer = layui.layer;
    // var loadindex;
    // $.ajax({
    //     url: "/left_menu",
    //     type: 'post',
    //     async : true,
    //     dataType: 'json',
    //     data: {
    //     },
    //     beforeSend:function(rs){
    //         loadindex = layer.load(2, {
    //             time: 1500,
    //             shade: [0.3,'#000'],
    //         });
    //     },
    //     complete:function(rs){
    //         layer.close(loadindex);
    //     },
    //     success: function(result) {
    //         if(result.status == 200) {
    //             $('#left-menu').html(template('menu-tpl', {datas:result.data}));
    //         }
    //     }
    // });
});

$('#quit-system').on('click', function () {
    layer.confirm('确认退出本系统？', function () {
        var loadindex;
        $.ajax({
            url: "/dologout",
            async : true,
            dataType: 'json',
            data: {
            },
            beforeSend:function(rs){
                loadindex = layer.load(2, {
                    time: 1500,
                    shade: [0.3,'#000'],
                });
            },
            complete:function(rs){
                layer.close(loadindex);
            },
            success: function(result) {
                if(result.status == 200) {
                    window.location.href = '/main'
                } else {
                    layer.msg('登场异常')
                }
            },
            error: function (rs) {
                layer.msg('登场异常')
            }
        });
    });
});

$('#shrink-menu,#spread-menu').on('click', function () {
    $('.layui-layout').toggleClass('shrink-layout');
    $('#shrink-menu,#spread-menu').toggleClass('hide');
});

$('#left-menu .layui-nav-item').on('click', function () {
    $('.layui-layout').removeClass('shrink-layout');
});

$('#refresh-page').on('click', function () {
    $('.main-container.show').attr('src', $('.main-container.show').attr('src'));
});


// $(document).on('click', function() {
//     $('#tabs-operation').addClass('hide');
// })

