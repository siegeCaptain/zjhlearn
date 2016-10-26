package zjh.learn.Dtos;

/**
 * DTO-验证权限input
 *
 * Created by jiahao.zhang on 2016/10/24.
 */
public class ValidateInputDto {
    private String key;
    private String ip;
    private String source;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ValidateInputDto{" +
                "key='" + key + '\'' +
                ", ip='" + ip + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
