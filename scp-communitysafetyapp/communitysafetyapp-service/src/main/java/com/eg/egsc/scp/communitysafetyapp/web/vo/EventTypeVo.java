/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

/**
 * @author xinghai
 * @since 2017/12/27
 */
public class EventTypeVo {
    private Integer code;
    private String name;

    public EventTypeVo(){}

    public EventTypeVo(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
