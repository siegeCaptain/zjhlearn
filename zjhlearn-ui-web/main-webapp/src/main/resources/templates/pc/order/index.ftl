<style type="text/css">

    .payCon .payWay li label {
        display: inline-block;
        vertical-align: middle;
        width: 14px;
        height: 14px;
        background: url(${image("select2.png")}) no-repeat;
        background-size: 100% auto;
        margin-right: 6px;
        list-style-type: none;
    }

    .payCon .payWay li.current label {
        background: url(${image("select2_on.png")}) no-repeat;
        background-size: 100% auto;
        list-style-type: none;
    }

</style>

<div>
    <a>coinNum:</a><input class="coinNum" type="text" name="coinNum" value=""></input>

    <div class="payCon">
        <h3 class="payTit">选择您的付款方式：</h3>
        <ul class="payWay">
            <li data-plugin="wechat"><label></label><a href="javascript:;"><img
                    src="${image("pay_wx.png")}" alt="">微信支付</a></li>
            <li data-plugin="alipay"><label></label><a href="javascript:;"><img
                    src="${image("pay_zfb.png")}" alt="">支付宝</a></li>
            <li id="selectBank" data-plugin="alipayBankPaymentPlugin"><label></label><a href="javascript:;"><img
                    src="${image("pay_wy.png")}" alt="">网银支付</a></li>
        </ul>
        <!--银行选择
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
        </div> -->
    </div>

    <div class="shopCarOper">
        <a href="javascript:createOrder()" class="layui-btn">确 定</a>
        <input type="hidden" name="paymentPluginId" id="paymentPluginId">
        <input type="hidden" name="bank" id="bank" value="ABC">
        <input type="hidden" name="openId" value="wechat_openid">
    </div>
</div>

<script src="${js("order.js")}"></script>