/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.enums;

/**
 * @author xinghai
 * @since 2017/12/27
 */
public enum ExceptionStatusEnum {
    REQUEST_TIMEOUT("33501", "Request TimeOut", "请求事件组件超时"),
    PARAM_INVALID("33502", "Param Invalid", "传入的参数不合法"),
    REQUEST_TREE_TIMEOUT("33503", "Request Tree TimeOut", "请求组织树组件超时"),
    REQUEST_DEVICE_TIMEOUT("33504", "Request Device TimeOut", "请求设备组件超时"),
    TRIGGER_GET_FAIL("33511", "Trigger Get Fail", "获取联动规则失败"),
    TRIGGER_ADD_FAIL("33512", "Trigger Add Fail", "新增联动规则失败"),
    TRIGGER_UPDATE_FAIL("33513", "Trigger Update Fail", "更新联动规则失败"),
    TRIGGER_DEL_FAIL("33514", "Trigger Del Fail", "删除联动规则失败"),
    EVENT_LOG_GET_FAIL("33515", "EventLog Get Fail", "获取事件日志失败"),
    TRIGGER_RESULT_GET_FAIL("33516", "TriggerResult Get Fail", "获取联动结果失败"),
    QUERY_FAIL("33517", "Query Fail", "查询错误"),
    ADD_FAIL("33518", "Add Fail", "新增错误"),
    UPDATE_FAIL("33519", "Update Fail", "修改错误"),
    DELETE_FAIL("33520", "Delete Fail", "删除错误");
    private final String value;
    private final String message;
    private final String reasonPhrase;

    ExceptionStatusEnum(String value, String message, String reasonPhrase) {
        this.value = value;
        this.message = message;
        this.reasonPhrase = reasonPhrase;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
