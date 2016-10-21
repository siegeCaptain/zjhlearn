package coin.store.dtos;

import java.util.Date;

/**
 * DTO 基类
 * Created by 11501 on 2016/10/18.
 */
public class BaseEntityDto<ID> {

    private ID id;

    private Date createDate;

    private Date modifyDate;

    private Long version;

    // region getter and setter
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    //endregion

    /**
     * 判断是否是新创建的对象
     */
    public boolean isNew() {
        return getId() == null;
    }
}
