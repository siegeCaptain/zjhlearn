<script type="application/javascript">
    var redirectUrl = "";
    var isLogin = "${isLoginStr}";
    var alertWindowId = "";
    var reload = false;
    var acountServiceApiUrl = "${acountServiceApiUrl}";
    var loginUrl = "${loginUrl}";
</script>
<link rel="stylesheet" type="text/css", href="${css("login.css")}">
<script src="${js("pc/login.js")}"></script>

<!--顶部栏-->
<div class="sHeight"></div>
<div class="sTop">
    <div class="sTopBox">
        <ul class="sNav">
            <li class="sNavUnable">
                <a href="javascript:userInfo();">
                    <script>
                        function userInfo() {
                            <#if isLogin == false >
                                showLogin();
                            <#else>
                                showUserInfo();
                            </#if>
                        }
                    </script>
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
                <div id="ervImg" style="background-color: #FF5722;width: 110px;height: 140px;margin: 0 0 0 -38px;display: none;position: absolute;line-height: 12px;">
                    <img src="${image("qrcode_for_bilifans.png")}" width="100" height="100" style="margin: 5px 0 0 5px;">
                    <span style='display: inline-block;text-align: center;width: 100%;font-size: 12px;'>扫码关注CoinStore</span>
                    <span style='display: inline-block;text-align: center;width: 100%;font-size: 12px;'>发现更多精彩</span>
                </div>
                <script>
                    $("#wxA").hover(function () {
                        $("#ervImg").show();
                    }, function () {
                        $("#ervImg").hide();
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

    <h3 class="loginTit"><span>
        <a href="javascript:void(0);" class="close"><i class="layui-icon" style="font-size: 30px">&#x1006;</i></a>
    </span></h3>
    <ul class="loginInput">
        <li>
            <span class="loginLeft">手机号：</span>
            <input id="phone" type="text" class="loginTel phoneInput" maxlength="11">
        </li>
        <li>
            <span class="loginLeft">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
            <input id="password" type="password" class="loginTel phoneInput">
        </li>
        <!-- 点击灰色按钮加class 'greyBtn'  -->
        <li>
        <p class="error" id="errorInfo" style="display:none;">密码错误，请重新输入。</p>
        </li>
    </ul>
    <div class="layerBot">
        <span>
            <a href="javascript:void(0);" class="loginBtn" id="login">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
        </span>
    </div>
</div>