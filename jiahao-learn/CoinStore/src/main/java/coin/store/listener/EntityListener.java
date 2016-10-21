package coin.store.listener;

import coin.store.entity.baseEntity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Listener - 创建日期、修改日期、版本处理
 *
 * Created by 11501 on 2016/10/21.
 */
public class EntityListener {

    /**
     * 保存前处理
     *
     * @param entity
     *            实体对象
     */
    @PrePersist
    public void prePersist(BaseEntity<?> entity) {
        entity.setCreateDate(new Date());
        entity.setModifyDate(new Date());
        entity.setVersion(null);
    }

    /**
     * 更新前处理
     *
     * @param entity
     *            实体对象
     */
    @PreUpdate
    public void preUpdate(BaseEntity<?> entity) {
        entity.setModifyDate(new Date());
    }

}
