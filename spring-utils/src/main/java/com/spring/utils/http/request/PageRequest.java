package com.spring.utils.http.request;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-07-03 16:30
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
public class PageRequest {

    /**
     * 当前页码
     */
    private Long pageNum;
    /**
     * 每页大小
     */
    private Long pageSize;


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

    /**
     * 数据偏移量
     *
     * @return
     */
    public long getOffset() {
        return (pageNum - 1) * pageSize;
    }
}
