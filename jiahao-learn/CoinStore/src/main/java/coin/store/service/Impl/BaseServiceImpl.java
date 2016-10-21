package coin.store.service.Impl;

import coin.store.entity.baseEntity.BaseEntity;
import coin.store.service.BaseService;

import java.io.Serializable;

/**
 * Created by 11501 on 2016/10/21.
 */
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T, ID>{
}
