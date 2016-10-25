/**
 * Created by jiahao.zhang on 2016/10/24.
 */

$(function () {

    var isWaiting = false;
    var isLogining = false;
    var timeOut;
    var count = 60;
    var sendBt = $("#valicate");
    var loginBt = $("#login");
    var phoneReg = /^1[0-9]{10}$/;
    var phonecodeReg = /^\d{4}$/;
    var source = "shop_web";

    var ButtonCount = function () {
        if (count == 0) {
            count = 60;
            sendBt.removeClass("unableBtn").html("获取验证码");
            clearTimeout(timeOut);
            isWaiting = false;
        } else {
            sendBt.addClass("unableBtn").html("(" + count-- + ")s");
            setTimeout(ButtonCount, 1000);
        }
    };

    $(".loginBox .close").click(function () {
        $(".loginWrap").hide();
        $(".loginBox").hide();
        $("body").css('overflow', 'auto');
    })

    $(".phoneInput").click(function () {
        hideErrorInfo();
    });

    $("#valicate").click(function () {
        var phone = $("#phone").val();

        if (isWaiting) {
            return;
        }
        if ($.trim(phone) == "" || !isMoblePhoneNumber(phone)) {
            showErrorInfo("请输入11位手机号码！", "phone");
            return;
        }
        $.ajax({
            url: acountServiceApiUrl + "/api/smsVerify",
            type: "POST",
            data: JSON.stringify(new sendCodeParams($("#phone").val())),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            beforeSend: function () {
                isWaiting = true;
                timeOut = setTimeout(ButtonCount, 0);
            },
            success: function (data) {
                if (data.code == 1) {
                    hideErrorInfo();
                } else {
                    if (data.code == -1 || data.code == -2) {
                        showErrorInfo("验证码发送频繁！请稍后再试！");
                    } else {
                        showErrorInfo("验证码发送失败！");
                    }
                }
            }
        });
    });

    $("#login").click(function () {
        var phone = $("#phone").val();
        var phonecode = $("#phonecode").val();
        var loginSuccess = false;

        if (isLogining) {
            return;
        }
        if ($.trim(phone) == "" || !isMoblePhoneNumber(phone)) {
            showErrorInfo("请输入11位手机号码！", "phone");
            return;
        }
        if ($.trim(phonecode) == "" || !isValiCode(phonecode)) {
            showErrorInfo("请输入4位数字验证码！", "phonecode");
            return;
        }
        $.ajax({
            url: acountServiceApiUrl + "/api/login",
            type: "POST",
            data: JSON.stringify(new loginParams($("#phone").val(), $("#phonecode").val())),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            //async:false, //取消同步  firefox有问题
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            beforeSend: function () {
                isLogining = true;
                loginBt.addClass("unableBtn")
            },
            success: function (data) {
                if (data.status == 1) {
                    loginUser.phone = phone;
                    loginSuccess = true;
                    isLogin = "true";
                    closeDiv("loginWin");
                    $("#loginUrl").attr("href", acountUrl);
                    $("#customerInfo").html("我的账户");
                    $("#logout").css("display", "");
                    if (data.isNew) {
                        change("BSUV", null, null);
                        $("#register_Moble").val(phone);
                        showDiv("regWin");
                    } else {
                        redirect();
                        // 是否有弹窗显示
                        alertWindow.show();
                    }
                    if (loginSuccess) {
                        merge();
                        showHeaderCartQuantity();
                    }
                } else {
                    isLogining = false;
                    loginBt.removeClass("unableBtn")
                    if (data.status == 2) {
                        showErrorInfo("验证码错误！", "phonecode");
                    } else if (data.status == 3) {
                        showErrorInfo("登陆失败次数过多！已被锁定！");
                    } else {
                        showErrorInfo("发送失败！请稍后再试！");
                    }
                }
            }
        });

    });

    $("#logout").click(function () {
        jeepConfirm("您真的要退出登录吗？", function () {
            $.ajax({
                url: acountServiceApiUrl + "/api/logout",
                type: "GET",
                dataType: "text",
                crossDomain: true,
                xhrFields: {
                    withCredentials: true
                },
                success: function (data) {
                    if (data == "success") {
                        removeCookie("key");
                        location.replace("/");
                    }
                }
            });
        });
    });

    function sendCodeParams(phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.source = source;
        return this;
    }

    function loginParams(phone, phoneCode) {
        this.phone = phone;
        this.phoneCode = phoneCode;
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