<a>coinNum:</a><input class="coinNum" type="text" name="coinNum" value=""></input>
<a href="javascript:create()">提 交</a>
<script type="application/javascript">
    function create() {
        var coinNum = $(".coinNum").val();
        if (/^\d*[1-9]\d*$/.test(coinNum)) {
            $.ajax({
                url: "/order/create",
                type: "POST",
                data: JSON.stringify({"coinNum": coinNum}),
                dataType: "json",
                contentType: "application/json",
                success: function (order) {
                    window.location.href = "/order/payment?orderSn=" + order.sn.toString();
                }
            });
        } else {
            alert("添加失败,数量错误！");
        }
    }
</script>