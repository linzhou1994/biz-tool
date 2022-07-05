package com.spring.utils.http.result;

import com.spring.utils.bean.BeanCopy;
import com.spring.utils.http.request.PageRequest;

import java.util.Collections;
import java.util.List;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-07-03 16:26
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
public class PageResult<T> {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 当前页码
     */
    private Long curPage;
    /**
     * 每页大小
     */
    private Long pageSize;

    private List<T> data;

    public PageResult(Long total, Long curPage, Long pageSize, List<T> data) {
        this.total = total;
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 创建为空的分页返回
     *
     * @param pageRequest
     * @param <E>
     * @return
     */
    public static <E> PageResult<E> buildEmpty(PageRequest pageRequest) {
        return new PageResult<E>(0L, pageRequest.getCurPage(), pageRequest.getPageSize(), Collections.emptyList());
    }

    /**
     * 构建返回对象
     *
     * @param pageRequest
     * @param total
     * @param data
     * @param <E>
     * @return
     */
    public static <E> PageResult<E> build(PageRequest pageRequest, Long total, List<E> data) {
        return new PageResult<E>(total, pageRequest.getCurPage(), pageRequest.getPageSize(), data);
    }

    /**
     * 构建返回对象
     *
     * @param pageResult
     * @param eClass
     * @param <E>
     * @return
     */
    public static  <E> PageResult<E> build(PageResult pageResult,Class<E> eClass) {
        List<E> resultData = BeanCopy.copyList(pageResult.getData(), eClass);
        return new PageResult<E>(pageResult.getTotal(), pageResult.getCurPage(), pageResult.getPageSize(), resultData);
    }


}
