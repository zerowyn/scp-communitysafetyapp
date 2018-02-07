/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

import com.eg.egsc.scp.eventcomponent.dto.TriggerDetailDto;

import java.util.List;

/**
 * @author xinghai
 * @since 2018/1/3
 */
public class TriggerRuleVo {
    /**事件类型**/
    private Integer eventType;
    /**事件类型名称**/
    private String eventTypeName;
    /**联动规则Id**/
    private String uuid;
    /**事件源**/
    private String eventSourceCode;
    /**设备名称**/
    private String deviceName;
    /**联动规则列表**/
    private List<TriggerDetailDto> triggers;
    /**创建人**/
    private String creator;
    /**是否具备时序性**/
    private Boolean isSequential;
    /**设备类型**/
    private String deviceType;

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEventSourceCode() {
        return eventSourceCode;
    }

    public void setEventSourceCode(String eventSourceCode) {
        this.eventSourceCode = eventSourceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<TriggerDetailDto> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<TriggerDetailDto> triggers) {
        this.triggers = triggers;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getIsSequential() {
        return this.isSequential;
    }

    public void setIsSequential(Boolean isSequential) {
        this.isSequential = isSequential;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
