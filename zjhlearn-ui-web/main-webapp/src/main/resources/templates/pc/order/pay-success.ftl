<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>支付成功 - Coin Store</title>
    <#include "../include/header.ftl"/>
    <link rel="stylesheet" type="text/css" href="${css("pay_success.css")}">
</head>
<body class="shop">
<div class="shopContainer">
<#include "../include/prev.ftl">
<div class="shopCarBox">
    <h3 class="shopCarTit">付款成功啦！硬币君马上吐硬币 >.<</h3>
    <div class="paySucBox">
        <div class="paySuc">
            <img src="${image("qrcode_for_bilifans.png")}" alt="" height="212" width="212">
            <p>马上关注CoinStore官方微信，<br>随时查看最新动态！</p>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function checkShipment() {
        $.ajax({
            url: "/order/${order.sn}?now=" + new Date().getTime(),
            type: "GET",
            success: function (order) {
                if (order && order.status == "completed") {
                    location.href = "/"
                }
            }
        });
    }
    setInterval(checkShipment, 3000);
</script>
</body>
</html>