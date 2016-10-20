/**
 * Created by jiahao.zhang on 2016/10/20.
 */

$(".payWay li").click(function () {
    $(this).parent().children().removeClass("current");
    $(this).addClass("current");
    if ($("#selectBank").hasClass("current")) {
        $(".payBank").show();
    }
    else {
        $(".payBank").hide();
    }
    $("#paymentPluginId").val($(this).attr("data-plugin"));
});

$(".payBankList li").click(function () {
    $(this).parent().children().removeClass("current");
    $(this).addClass("current");
    $("#bank").val($(this).attr("data-bank"));
});

function create() {
    var coinNum = $(".coinNum").val();
    var paymentMethod = $("#paymentPluginId").val();
    if (!paymentMethod) {
        alert("请选择支付方式");
        return false;
    }

    if (/^\d*[1-9]\d*$/.test(coinNum)) {
        $.ajax({
            url: "/order/create",
            type: "POST",
            data: JSON.stringify({coinNum: coinNum, paymentMethod: paymentMethod}),
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: function (data) {
                payment(data.sn, data.paymentMethod);
            },
            error: function() {
                alert("服务器君开小差了>.< 请重试！");
            }
        });
    } else {
        alert("添加失败,数量错误！");
    }
}

function payment(sn, paymentMethod) {
    if ("wechat" == paymentMethod) {
        location.href = "/order/payment/wechatQr?orderSn=" + sn;
        return false;
    }
}