;
layui.define(function (e) {
    var i = (layui.$, layui.layer, layui.laytpl, layui.setter, layui.view, layui.admin);
    console.log(i)
    i.events.logout = function () {
        i.req({
            url: "dologout",
            type: "get",
            data: {},
            done: function (e) {
                console.log(e)
                i.exit(function () {
                    location.href = "/main"
                })
            }
        })
    },
        e("common", {})
});