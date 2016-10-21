package coin.store.base;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页
 *
 * Created by 11501 on 2016/10/21.
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -2053800594583879853L;

    //内容
    private final List<T> content = new ArrayList<T>();

    //总记录数目
    private final long total;

    //分页信息
    private final Pageable pageable;

    //默认构造方法
    public Page() {
        this.total = 0L;
        this.pageable = new Pageable();
    }

    //带参构造方法
    public Page(List<T> content, long total, Pageable pageable) {
        this.content.addAll(content);
        this.total = total;
        this.pageable = pageable;
    }

    /**
     * Returns a new {@link Page} with the content of the current one mapped by the given {@link Converter}.
     *
     * @param converter must not be {@literal null}.
     * @return a new {@link Page} with the content of the current one mapped by the given {@link Converter}.
     * @since 1.10
     */
    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return new Page<S>(getConvertedContent(converter), total, pageable);
    }

    /**
     * Applies the given {@link Converter} to the content..
     *
     * @param converter must not be {@literal null}.
     * @return a new {@link List}
     */
    protected <S> List<S> getConvertedContent(Converter<? super T, ? extends S> converter) {

        Assert.notNull(converter, "Converter must not be null!");

        List<S> result = new ArrayList<S>(content.size());

        result.addAll(content.stream().map((Function<T, S>) converter::convert).collect(Collectors.toList()));

        return result;
    }

    /**
     * 获取页码
     *
     * @return 页码
     */
    public int getPageNumber() {
        return pageable.getPageNumber();
    }

    /**
     * 获取每页记录数
     *
     * @return 每页记录数
     */
    public int getPageSize() {
        return pageable.getPageSize();
    }

    /**
     * 获取搜索属性
     *
     * @return 搜索属性
     */
    public String getSearchProperty() {
        return pageable.getSearchProperty();
    }

    /**
     * 获取搜索值
     *
     * @return 搜索值
     */
    public String getSearchValue() {
        return pageable.getSearchValue();
    }

    /**
     * 获取排序属性
     *
     * @return 排序属性
     */
    public String getOrderProperty() {
        return pageable.getOrderProperty();
    }

    /**
     * 获取排序方向
     *
     * @return 排序方向
     */
    public Order.Direction getOrderDirection() {
        return pageable.getOrderDirection();
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    public List<Order> getOrders() {
        return pageable.getOrders();
    }

    /**
     * 获取筛选
     *
     * @return 筛选
     */
    public List<Filter> getFilters() {
        return pageable.getFilters();
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public int getTotalPages() {
        return (int) Math.ceil((double) getTotal() / (double) getPageSize());
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * 获取总记录数
     *
     * @return 总记录数
     */
    public long getTotal() {
        return total;
    }

    /**
     * 获取分页信息
     *
     * @return 分页信息
     */
    public Pageable getPageable() {
        return pageable;
    }

    /**
     * 获取空分页
     *
     * @param pageable
     *            分页信息
     * @return 空分页
     */
    public static final <T> Page<T> emptyPage(Pageable pageable) {
        return new Page<T>(Collections.<T> emptyList(), 0L, pageable);
    }
}
