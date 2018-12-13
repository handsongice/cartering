/** layuiAdmin.std-v1.1.0 LPPL License By http://www.layui.com/admin/ */
;
layui.define(["form", "upload"], function (t) {
    var i = layui.$,
        e = layui.layer,
        a = (layui.laytpl, layui.setter, layui.view, layui.admin),
        n = layui.form,
        s = layui.upload;
    i("body");
    n.verify({
        nickname: function (t, i) {
            return new RegExp("^[a-zA-Z0-9_一-龥\\s·]+$").test(t) ? /(^\_)|(\__)|(\_+$)/.test(t) ? "用户名首尾不能出现下划线'_'" : /^\d+\d+\d$/.test(t) ? "用户名不能全为数字" : void 0 : "用户名不能有特殊字符"
        },
        pass: [/^[\S]{6,12}$/, "密码必须6到12位，且不能出现空格"],
        repass: function (t) {
            if (t !== i("#LAY_password").val()) return "两次密码输入不一致"
        }
    });
    t("set", {})
});