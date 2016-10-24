package coin.store.entity.baseEntity;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 排序基类
 *
 * Created by 11501 on 2016/10/21.
 */
public abstract class OrderEntity<ID extends Serializable> extends BaseEntity<ID> implements Comparable<OrderEntity<ID>> {

    private static final long serialVersionUID = 818020302097358413L;

    /** "排序"属性名称 */
    public static final String ORDER_PROPERTY_NAME = "order";

    /** 排序 */
    private Integer order;

    /**
     * 获取排序
     *
     * @return 排序
     */
    @Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
    @NumericField
    @Min(0)
    @Column(name = "orders")
    public Integer getOrder() {
        return order;
    }

    /**
     * 设置排序
     *
     * @param order
     *            排序
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 实现compareTo方法
     *
     * @param orderEntity
     *            排序对象
     * @return 比较结果
     */
    public int compareTo(OrderEntity<ID> orderEntity) {
        if (orderEntity == null) {
            return 1;
        }
        return new CompareToBuilder().append(getOrder(), orderEntity.getOrder()).append(getId(), orderEntity.getId()).toComparison();
    }
}