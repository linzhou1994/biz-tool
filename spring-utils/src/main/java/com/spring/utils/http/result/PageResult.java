package com.spring.utils.http.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spring.utils.bean.BeanCopy;
import com.spring.utils.http.request.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private Long pageNum;
    /**
     * 每页大小
     */
    private Long pageSize;

    private List<T> data;

    public PageResult() {
    }

    public PageResult(Long total, Long pageNum, Long pageSize, List<T> data) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
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
        return new PageResult<E>(0L, pageRequest.getPageNum(), pageRequest.getPageSize(), Collections.emptyList());
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
        return new PageResult<E>(total, pageRequest.getPageNum(), pageRequest.getPageSize(), data);
    }

    /**
     * 构建返回对象
     *
     * @param pageResult
     * @param eClass
     * @param <E>
     * @return
     */
    public static <E> PageResult<E> build(PageResult pageResult, Class<E> eClass) {
        List<E> resultData = BeanCopy.copyList(pageResult.getData(), eClass);
        return new PageResult<E>(pageResult.getTotal(), pageResult.getPageNum(), pageResult.getPageSize(), resultData);
    }

    /**
     * 构建返回对象
     */
    public static <S, T> PageResult<T> build(IPage<S> iPage, Class<T> clazz) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNum(iPage.getCurrent());
        pageResult.setPageSize(iPage.getSize());
        pageResult.setTotal(iPage.getTotal());
        // 构建记录
        List<S> records = iPage.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            // 复制对象
            pageResult.setData(records.stream()
                    .map(record -> BeanCopy.copy(record, clazz))
                    .collect(Collectors.toList())
            );
        } else {
            pageResult.setData(new ArrayList<>());
        }
        return pageResult;
    }


}
