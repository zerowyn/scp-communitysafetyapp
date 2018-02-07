/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

/**
 * @author xinghai
 * @since 2017/12/18
 */
public class ResponsePageVo {
    private Integer total;
    private Integer pageNo;
    private Object rows;

    public ResponsePageVo(){}
    public ResponsePageVo(Integer total, Integer pageNo, Object rows) {
        this.total = total;
        this.pageNo = pageNo;
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
