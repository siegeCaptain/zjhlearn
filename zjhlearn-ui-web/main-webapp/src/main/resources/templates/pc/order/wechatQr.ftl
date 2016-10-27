<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>订单支付 - Coin Store</title>
    <#include "../include/header.ftl"/>
    <link rel="stylesheet" type="text/css" href="${css("pay_wx.css")}">
    <style>
        .payWx .payWxCon {
            width: 514px;
            height: 303px;
            margin: 30px auto;
            background: url(${image("wx/wx_bg.jpg")}) no-repeat;
        }
    </style>
</head>
<body class="shop">
<#include "../include/prev.ftl">
<div class="shopContainer">
    <div class="shopCarBox">
        <input id="orderSn" type="hidden" value="${order.sn}">
        <h3 class="shopCarTit"><span class="scTitLeft">订单号：<em>${order.sn}</em></span>微信支付</h3>
        <div class="payWx">
            <div class="payWxCon">
                <a href="javascript:void(0);" class="payCode"><img src="/order/payment/qrimage?ordersn=${order.sn}" style="width: 212px;height: 212px;"></a>
                <p class="payCodeBot"><img src="${image("wx/wx_icon.png")}" alt=""><span>请使用微信扫一扫<br>扫描二维码支付</span></p>
            </div>
        </div>
        <div class="shopCarOper">
            <a href="/order/payment?orderSn=${order.sn}" class="layui-btn">选择其他支付方式</a>
            <div class="scOperRight">
                <div class="scOperTotal">
                    <h4>应付金额:<span>￥：${amount}</span></h4>
                </div>
            </div>
        </div>
    </div>
    <div class="sFooterHeight"></div>
</div>
<script type="text/javascript">
    function checkPayment() {
        $.ajax({
            url: "/order/" + $("#orderSn").val() + "?now=" + new Date().getTime(),
            type: "GET",
            success: function (order) {
                if (order && order.status != "pendingPayment") {
                    location.href = "/order/payment/success?orderSn=${order.sn}"
                }
            }
        });
    }
    setInterval(checkPayment, 3000);
</script>
</body>
</html>