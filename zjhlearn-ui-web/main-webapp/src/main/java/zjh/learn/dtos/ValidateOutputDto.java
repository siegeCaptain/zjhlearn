package zjh.learn.dtos;

/**
 * DTO-权限验证返回output
 *
 * Created by jiahoa.zhang on 2016/10/24.
 */
public class ValidateOutputDto {

    /**
     * 返回是否验证成功
     */
    private int status;

    private Integer userId;

    private String phone;

    private String openId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "ValidateOutputDto{" +
                "status=" + status +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
