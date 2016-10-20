<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>订单支付 - Coin Store</title>
</head>

<body>
    <#include "../include/header.ftl">
    <#include "create.ftl">
    <div class="payCon">
        <h3 class="payTit">选择您的付款方式：</h3>
        <ul class="payWay">
            <li data-plugin="wechatScanPaymentPlugin"><label></label><a href="javascript:;"><img
                    src="${image("pay_wx.png")}" alt="">微信支付</a></li>
            <li data-plugin="alipayDirectPaymentPlugin"><label></label><a href="javascript:;"><img src="${image("pay_zfb.png")}"
                                                                                                   alt="">支付宝</a></li>
            <li id="selectBank" data-plugin="alipayBankPaymentPlugin"><label></label><a href="javascript:;"><img
                    src="${image("pay_wy.png")}"
                    alt="">网银支付</a></li>
        </ul>
        <!--银行选择-->
        <div class="payBank" style="display:none">
            <div class="payBankTit"><span>选择支付银行：</span></div>
            <ul class="payBankList">
                <li class="cur" data-bank="ABC">
                    <img src="${image("pc/bank1.jpg")}" alt="">
                </li>
                <li data-bank="BOCB2C">
                    <img src="${image("pc/bank2.jpg")}" alt="">
                </li>
                <li data-bank="CEBBANK">
                    <img src="${image("pc/bank3.jpg")}" alt="">
                </li>
                <li data-bank="COMM">
                    <img src="${image("pc/bank4.jpg")}" alt="">
                </li>
                <li data-bank="CCB">
                    <img src="${image("pc/bank5.jpg")}" alt="">
                </li>
                <li data-bank="ICBCB2C">
                    <img src="${image("pc/bank6.jpg")}" alt="">
                </li>
                <li data-bank="CITIC">
                    <img src="${image("pc/bank7.jpg")}" alt="">
                </li>
                <li data-bank="CMB">
                    <img src="${image("pc/bank8.jpg")}" alt="">
                </li>
            </ul>
        </div>
    </div>

    <div class="shopCarOper">
        <form id="payForm" method="post" action="${payUrl}">
            <a href="javascript:" onclick="cancelPayment()" class="shopCarDel">取消支付</a>
            <div class="scOperRight">
                <input type="submit" class="scOperBtn" value="立即支付"/>
                <div class="scOperTotal">
                    <h4>应付金额:<span>${order.amount}</span></h4>
                </div>
            </div>
            <input type="hidden" name="sn" value="${order.sn}"/>
            <input type="hidden" name="paymentPluginId" id="paymentPluginId">
            <input type="hidden" name="type" value="payment">
            <input type="hidden" name="bank" id="bank" value="ABC">
            <input type="hidden" name="openId" value="wechat_openid">
        </form>
    </div>

    <script>
        $(".payWay li").click(function () {
            $(this).parent().children().removeClass("cur");
            $(this).addClass("cur");
            if ($("#selectBank").hasClass("cur")) {
                $(".payBank").show();
            }
            else {
                $(".payBank").hide();
            }
            $("#paymentPluginId").val($(this).attr("data-plugin"));
        });

        $(".payBankList li").click(function () {
            $(this).parent().children().removeClass("cur");
            $(this).addClass("cur");
            $("#bank").val($(this).attr("data-bank"));
        });

        $("#payForm").submit(function () {
            if (!$("#paymentPluginId").val()) {
                alert("请选择支付方式");
                return false;
            }
            if ("wechatScanPaymentPlugin" == $("#paymentPluginId").val()) {
                location.href = "/order/payment/wechatQr?orderSn=${order.sn}";
                return false;
            }
        });

        //取消支付
        function cancelPayment() {
            confirm("您确定要取消支付？", "下单后半小时内未支付成功，订单将被取消<br>请尽快完成支付！", function() {
                location.href = "/order/account/list/unpaid";
            }, "确认取消", "继续支付");
        }
    </script>
</body>
</html>