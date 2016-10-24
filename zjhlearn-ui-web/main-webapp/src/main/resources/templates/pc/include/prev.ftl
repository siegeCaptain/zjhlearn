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
        </ul>
    </div>
</div>