/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.enums;

/**
 * @author xinghai
 * @since 2018/1/5
 */
public enum ResultStatus {
    OK("200", "OK", "成功"),
    BUSINESS_ERROR("1000", "Business Error", "业务类异常"),
    VALIDATION_ERROR("1100", "Validation Error", "参数数据验证异常"),
    AUTHENTICATION_ERROR("1200", "Authentication Error", "身份认证异常"),
    REQUEST_ERROR("1300", "Request Error", "客户端发送的请求异常"),
    REQUEST_PARAM_ERROR("1400", "Request Param Error", "客户端发送的请求参数异常"),
    REQUEST_PARAM_TRANSFER_ERROR("1500", "Request Param Transfer Error", "客户端发送的请求数据在服务器转换异常"),
    SYSTEM_ERROR("2000", "System Error", "系统抛出的其他未知异常");

    private final String value;
    private final String message;
    private final String reasonPhrase;

    ResultStatus(String value, String reasonPhrase, String message) {
        this.value = value;
        this.message = message;
        this.reasonPhrase = reasonPhrase;
    }

    public String value() {
        return this.value;
    }

    public String message() {
        return this.message;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
