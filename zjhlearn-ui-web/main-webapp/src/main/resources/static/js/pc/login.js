/**
 * Created by jiahao.zhang on 2016/10/24.
 */

function showUserInfo() {

}

function showLogin() {
    $(".loginWrap").show();
    $("#loginWin").show();
    $("body").css('overflow', 'hidden');
}

function showDiv(divId) {
    $(".loginWrap").show();
    $("#" + divId).show();
    $("body").css('overflow', 'hidden');
}

function closeDiv(divId) {
    $(".loginWrap").hide();
    $("#" + divId).hide();
    $("body").css('overflow', 'auto');
    if (divId == "regWin") {
        redirect();
    }
}

$(function () {

    var isWaiting = false;
    var isLogining = false;
    var timeOut;
    var count = 60;
    var loginBtn = $("#login");
    var phoneReg = /^1[0-9]{10}$/;
    var phonecodeReg = /^\d{4}$/;
    var source = "shop_web";

    /**
     * todo 获取验证码倒计时
     */
    // var ButtonCount = function () {
    //     if (count == 0) {
    //         count = 60;
    //         sendBt.removeClass("unableBtn").html("获取验证码");
    //         clearTimeout(timeOut);
    //         isWaiting = false;
    //     } else {
    //         sendBt.addClass("unableBtn").html("(" + count-- + ")s");
    //         setTimeout(ButtonCount, 1000);
    //     }
    // };

    /**
     *  关闭登陆弹窗按钮
     */
    $(".loginBox .close").click(function () {
        $(".loginWrap").hide();
        $(".loginBox").hide();
        $(".phoneInput").val("");
        $("body").css('overflow', 'auto');
    });

    $(".phoneInput").click(function () {
        hideErrorInfo();
    });

    //todo 验证码登陆
    // $("#valicate").click(function () {
    //     var phone = $("#phone").val();
    //
    //     if (isWaiting) {
    //         return;
    //     }
    //     if ($.trim(phone) == "" || !isMoblePhoneNumber(phone)) {
    //         showErrorInfo("请输入11位手机号码！", "phone");
    //         return;
    //     }
    //     $.ajax({
    //         url: acountServiceApiUrl + "/api/smsVerify",
    //         type: "POST",
    //         data: JSON.stringify(new sendCodeParams($("#phone").val())),
    //         contentType: "application/json; charset=utf-8",
    //         dataType: "json",
    //         crossDomain: true,
    //         xhrFields: {
    //             withCredentials: true
    //         },
    //         beforeSend: function () {
    //             isWaiting = true;
    //             timeOut = setTimeout(ButtonCount, 0);
    //         },
    //         success: function (data) {
    //             if (data.code == 1) {
    //                 hideErrorInfo();
    //             } else {
    //                 if (data.code == -1 || data.code == -2) {
    //                     showErrorInfo("验证码发送频繁！请稍后再试！");
    //                 } else {
    //                     showErrorInfo("验证码发送失败！");
    //                 }
    //             }
    //         }
    //     });
    // });

    $("#login").click(function () {
        var phone = $("#phone").val();
        var password = $("#password").val();
        var loginSuccess = false;

        if (isLogining) {
            return;
        }
        if ($.trim(phone) == "" || !isMoblePhoneNumber(phone)) {
            showErrorInfo("请输入11位手机号码！", "phone");
            return;
        }
        // if ($.trim(phonecode) == "" || !isValiCode(phonecode)) {
        //     showErrorInfo("请输入4位数字验证码！", "phonecode");
        //     return;
        // }
        $.ajax({
            url: "/login",
            type: "POST",
            data: JSON.stringify(new loginParams($("#phone").val(), $("#password").val())),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            beforeSend: function () {
                isLogining = true;
                loginBtn.addClass("unableBtn")
            },
            success: function (data) {
                if (data.code == 1) {
                    loginSuccess = true;
                    isLogin = "true";
                    closeDiv("loginWin");
                    $("#userInfo").html("我的账户");
                    $("#logout").css("display", "");
                    if (loginSuccess) {
                        //成功后的一些必要操作
                        window.location.reload(true);
                    }
                } else {
                    isLogining = false;
                    loginBtn.removeClass("unableBtn")
                    showErrorInfo(data.message);
                }
            }
        });

    });

    $("#logout").click(function () {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.confirm('您真的要退出登录吗？', {icon: 3, title:'提示'}, function(index){
                //do something
                $.ajax({
                    url: "/logout",
                    type: "GET",
                    dataType: "text",
                    crossDomain: true,
                    xhrFields: {
                        withCredentials: true
                    },
                    success: function (data) {
                        if (data) {
                            location.replace("/");
                        }
                    }
                });
                layer.close(index);
            });
        });
    });

    function sendCodeParams(phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.source = source;
        return this;
    }

    function loginParams(phone, password, phoneCode) {
        this.phone = phone;
        this.password = password;
        // this.phoneCode = phoneCode;
        this.source = source;
        return this;
    }

    function isMoblePhoneNumber(phoneNumber) {
        if (phoneReg.test(phoneNumber)) {
            return true;
        }
        return false;
    }

    function isValiCode(valicode) {
        if (phonecodeReg.test(valicode)) {
            return true;
        }
        return false;
    }

    function showErrorInfo(errorInfo, id) {
        if (id != null) {
            $("#" + id).addClass("errorInput")
        }
        $("#errorInfo").html(errorInfo);
        $("#errorInfo").show();
    }

    function hideErrorInfo() {
        $("#phone").removeClass("errorInput");
        $("#phonecode").removeClass("errorInput");
        $("#errorInfo").hide();
    }

    function redirect() {
        if (redirectUrl != "") {
            window.location.href = redirectUrl;
        }
        if (reload) {
            window.location.reload(true);
        }
    }

});