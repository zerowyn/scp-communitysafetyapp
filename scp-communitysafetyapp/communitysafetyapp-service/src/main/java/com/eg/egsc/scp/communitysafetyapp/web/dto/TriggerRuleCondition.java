/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.dto;

/**
 * @author xinghai
 * @since 2017/12/20
 */
public class TriggerRuleCondition extends BasePage {
    /**
     * 事件类型
     **/
    private Integer eventType;
    /**
     * 事件源
     **/
    private String eventSourceCode;

    /**
     * 设备分类
     **/
    private String deviceType;

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getEventSourceCode() {
        return eventSourceCode;
    }

    public void setEventSourceCode(String eventSourceCode) {
        this.eventSourceCode = eventSourceCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
