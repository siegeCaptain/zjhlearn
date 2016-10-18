package coin.store.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity 基类
 * Created by 11501 on 2016/10/18.
 */
public class BaseEntity<ID extends Serializable> implements Serializable {

    private ID id;

    private Date createDate;

    private Date modifyDate;

    private Long version;

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

    /**
     * 判断是否为新建对象
     *
     * @return 是否为新建对象
     */
    public boolean isNew() {
        return getId() == null;
    }

    /**
     * 重写toString方法
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", version=" + version +
                '}';
    }

    /**
     * 重写equals方法
     *
     * @param obj
     *            对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        BaseEntity<?> other = (BaseEntity<?>) obj;
        return getId() != null ? getId().equals(other.getId()) : false;
    }
}
