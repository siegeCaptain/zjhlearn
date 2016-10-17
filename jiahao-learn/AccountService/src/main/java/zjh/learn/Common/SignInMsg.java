package zjh.learn.Common;

/**
 * 登录消息返回规范化
 * Created by jiahao on 16-8-10.
 */
public enum SignInMsg {

    UserOrPasswordError(1, "用户名或密码错误"),
    TokenCreateError(4, "Token创建错误");

    int code;
    String value;

    SignInMsg(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
