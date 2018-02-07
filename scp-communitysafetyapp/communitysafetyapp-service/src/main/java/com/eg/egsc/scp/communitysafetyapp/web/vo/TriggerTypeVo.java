/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

import java.io.Serializable;

/**
 * 联动方式
 * @author xinghai
 * @since 2018/1/3
 */
public class TriggerTypeVo implements Serializable {
    private static final long serialVersionUID = 5789085318929118864L;
    private String name;
    private String value;
    private String code;
    private String to;

    public TriggerTypeVo(String name, String value, String code, String to) {
        this.name = name;
        this.value = value;
        this.code = code;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
