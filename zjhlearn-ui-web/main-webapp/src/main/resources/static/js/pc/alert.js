/**
 * Created by 11501 on 2016/10/24.
 */
// zjhConfirm([title,] content, callbackSuccess [, callbackCancel] [, buttonWordYes, buttonWordNo])
function zjhConfirm(title, content, callbackSuccess, callbackCancel, buttonWordYes, buttonWordNo) {
    if (typeof content !== "string") {
        buttonWordNo = buttonWordYes;
        buttonWordYes = callbackCancel;
        callbackCancel = callbackSuccess;
        callbackSuccess = content;
        content = title;
        title = undefined;
    }
    if (typeof callbackCancel !== "function") {
        buttonWordNo = buttonWordYes;
        buttonWordYes = callbackCancel;
    }

    var $confirm = $("#zjhpAlertConfirm");

    //button文字
    $confirm.find(".loginBtn").text(buttonWordYes || "确定");
    $confirm.find(".cancelBtn").text(buttonWordNo || "取消");

    //窗体内容
    if (title) {
        $confirm.find(".layerNoice").show();
        $confirm.find(".loginSuccess").hide();
        $confirm.find(".layerNoice .title").html(title);
        $confirm.find(".layerNoice .smallNotice").html(content);
    } else {
        $confirm.find(".layerNoice").hide();
        $confirm.find(".loginSuccess").show();
        $confirm.find(".loginSuccess").html(content);
    }

    //show
    $(".loginWrap").show();
    $confirm.show();
    $("body").css('overflow', 'hidden');

    //click
    _alertCloseBind($confirm.find(".loginBtn"), $confirm, callbackSuccess);
    _alertCloseBind($confirm.find(".cancelBtn"), $confirm, callbackCancel);
    _alertCloseBind($confirm.find(".close"), $confirm, callbackCancel)
}

// zjhAlert(content [, callbackSuccess [, callbackCancel]] [, buttonWordYes])
function zjhAlert(content, callbackSuccess, callbackCancel, buttonWordYes) {
    if (typeof callbackCancel !== "function") {
        buttonWordYes = callbackCancel;
    }
    if (typeof callbackSuccess != "function") {
        buttonWordYes = callbackSuccess;
    }

    var $alert = $("#zjhAlertNotice");

    //button文字
    $alert.find(".loginBtn").text(buttonWordYes || "确定");

    //窗体内容
    $alert.find(".loginSuccess").html(content);

    //show
    $(".loginWrap").show();
    $alert.show();
    $("body").css('overflow', 'hidden');

    //click
    _alertCloseBind($alert.find(".loginBtn"), $alert, callbackSuccess);
    _alertCloseBind($alert.find(".close"), $alert, callbackCancel)
}

function zjhMessage() {
    var $message = $("#zjhAlertMessage");

    $message.show();
    $("body").css('overflow', 'hidden');

    setTimeout("_alertClose({ data: $('#zjhAlertMessage')})", 1000);
}

function _alertCloseBind(button, alertBody, callback) {
    button.unbind();
    button.click(alertBody, _alertClose);
    if (typeof callback === "function")
        button.click(callback);
}

function _alertClose(alertBody) {
    $(".loginWrap").hide();
    alertBody.data.hide();
    $("body").css("overflow", "auto");
}