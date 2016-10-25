<script type="application/javascript">
    var redirectUrl = "";
    var isLogin = "${isLoginStr}";
    var alertWindowId = "";
    var reload = false;
    var acountServiceApiUrl = "${acountServiceApiUrl}";
</script>
<script src="${js("pc/login.js")}"></script>

<!--顶部栏-->
<div class="sHeight"></div>
<div class="sTop">
    <div class="sTopBox">
        <ul class="sNav">
            <li class="sNavUnable">
                <a href="javascript:void(0);">
                <i class="layui-icon">&#xe612;</i>
                <span id="userInfo">
                    <#if isLogin == true >
                        我的账户
                    <#else>
                        注册/登录
                    </#if>
                    </span>
                </a>
                <a id="logout" style="padding: 0 0px;margin: 0 0px;display: <#if isLogin == true ><#else>none</#if>" href="javascript:void(0);" >
                    退出登录
                </a>
            </li>
            <li class="sNavUnable">
                <a href="javascript:void(0);" id="wxA"><img src="${image("wx/wx.png")}" alt=""></a>
                <script>
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        var index;
                        $("#wxA").hover(function () {
                            index = layer.tips('<div id="ervImg" style="background-color: #FFF;width: 110px;height: 140px;margin: 0 0 0 -38px;display: none;position: absolute;line-height: 12px;">'+
                            '<img src="${image("pc/unpayed_erv.png")}" width="100" height="100" style="margin: 5px 0 0 5px;">'+
                            '<span style=\'display: inline-block;text-align: center;width: 100%;font-size: 12px;\'>扫码关注CoinStore</span>'+
                            '<span style=\'display: inline-block;text-align: center;width: 100%;font-size: 12px;\'>发现更多精彩</span>'+
                            '</div>',
                            '#wxA', {tips: 3});
                        }, function () {
                            layer.close(index);
                        });
                    });
                </script>
            </li>
        </ul>
    </div>
</div>

<!-- 弹窗遮罩 -->
<div class="loginWrap" style="z-index:999;height: 1000%;"></div>

<!-- 登陆弹窗 -->
<div id="loginWin" class="loginBox jeepAlertWin" style="z-index:1000;position:fixed;">
    <h3 class="loginTit"><a href="javascript:void(0);" class="close"></a></h3>
    <ul class="loginInput">
        <li>
            <span class="loginLeft">手机号：</span>
            <input id="phone" type="text" class="loginTel phoneInput" maxlength="11"></li>
        <li>
            <p>
                <span class="loginLeft">验证码：</span>
                <input id="phonecode" type="text" class="loginMsd phoneInput" maxlength="4">
                <a href="javascript:;" class="codeBtn" id="valicate">点击获取验证码</a>
            </p>
            <!-- 点击灰色按钮加class 'greyBtn'  -->
            <p class="error" id="errorInfo" style="display:none;">验证码错误，请重新输入。</p>
        </li>
    </ul>
    <div class="layerBot">
        <span>
            <a href="javascript:;" class="loginBtn" id="login">提&nbsp;&nbsp;&nbsp;交</a>
        </span>
    </div>
</div>

<!-- 登陆弹窗 -->
<#--<script>-->
    <#--$("#userInfo").click(function () {-->
        <#--layui.use('layer', function(){-->
            <#--var layer = layui.layer;-->
            <#--layer.open({-->
                        <#--type: 1,-->
                        <#--content:'<div>'+-->
                            <#--'    <ul class="loginInput">'+-->
                            <#--'        <li>'+-->
                            <#--'            <span class="loginLeft">手机号：</span>'+-->
                            <#--'            <input id="phone" type="text" class="loginTel phoneInput" maxlength="11"></li>'+-->
                            <#--'            <input id="phone" type="text" class="loginPsw password"></li>'+-->
                            <#--'        <li>'+-->
                            <#--'            <p>'+-->
                            <#--'                <span class="loginLeft">验证码：</span>'+-->
                            <#--'                <input id="phonecode" type="text" class="loginMsd phoneInput" maxlength="4">'+-->
                            <#--'                <a href="javascript:void(0);" class="codeBtn" id="valicate">点击获取验证码</a>'+-->
                            <#--'            </p>'+-->
                            <#--'            <p class="error" id="errorInfo" style="display:none;">验证码错误，请重新输入。</p>'+-->
                            <#--'        </li>'+-->
                            <#--'    </ul>'+-->
                            <#--'    <div class="layerBot">'+-->
                            <#--'            <span>'+-->
                            <#--'                <a href="javascript:;" class="loginBtn" id="login">提&nbsp;&nbsp;&nbsp;交</a>'+-->
                            <#--'            </span>'+-->
                            <#--'    </div>'+-->
                            <#--'</div>'-->
                        <#--});-->
        <#--});-->
    <#--});-->
<#--</script>-->