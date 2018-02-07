/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.dto;

import java.io.Serializable;

/**
 * @author xinghai
 * @since 2017/12/14
 */
public class TriggersDto implements Serializable {

    private static final long serialVersionUID = 2625266913214743114L;
    /**
     * 联动详情ID
     **/
    private Long triggerDetailId;
    /**
     * 规则方法
     **/
    private String method;
    /**
     * 联动参数
     **/
    private transient Object params;
    /**
     * 下一个联动触发规则
     **/
    private String to;
    /**
     * 联动顺序
     **/
    private Integer order;

    public Long getTriggerDetailId() {
        return triggerDetailId;
    }

    public void setTriggerDetailId(Long triggerDetailId) {
        this.triggerDetailId = triggerDetailId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
