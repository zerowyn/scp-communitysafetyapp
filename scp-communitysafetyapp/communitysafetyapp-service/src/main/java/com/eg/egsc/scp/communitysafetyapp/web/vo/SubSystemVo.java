/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

/**
 * @author xinghai
 * @since 2017/12/26
 */
public class SubSystemVo {
    private String code;
    private String name;

    public SubSystemVo(){}

    public SubSystemVo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
