package zjh.learn.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

/**
 * TokenDto
 *
 * Created by jiahao on 16-8-10.
 */
@Document(collection = "tokens")
public class TokenDto {

    public static final TokenDto EMPTY = new TokenDto(true);

    @Id
    private String id;

    private String authCode;

    private String userId;

    private String ip;

    private long createTime;

    private long expireTime;

    public TokenDto() {
        this(false);
    }

    public TokenDto(String userId, String ip) {
        this();
        this.userId = userId;
        this.ip = ip;
    }

    private TokenDto(boolean empty) {
        if (!empty) {
            this.createTime = new Date().getTime();
            this.authCode = UUID.randomUUID().toString().replace("-", "");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
