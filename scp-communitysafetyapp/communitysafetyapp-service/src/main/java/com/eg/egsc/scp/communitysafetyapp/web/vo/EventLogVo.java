/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import com.eg.egsc.scp.communitysafetyapp.enums.EventStatusEnum;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author xinghai
 * @since 2017/12/27
 */
public class EventLogVo extends BaseBusinessDto{
    private Long id;
    private String eventLogId;//事件日志的唯一编号
    private Integer eventType;//事件类型
    private String eventTypeName;//事件类型名
    private String startTime;//事件开始时间
    private String endTime;//事件结束时间
    private String content;//内容
    private String creator;//创建者
    private String deviceCode;//设备编号
    private String resourceCode;//资源点编号
    private String deviceName;//设备名称
    private String resourceName;//资源点名称
    private Integer status;//事件状态  1开始  2脉动  3结束
    private String statusMessage;//事件状态对应信息
    private transient Object extend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventLogId() {
        return eventLogId;
    }

    public void setEventLogId(String eventLogId) {
        this.eventLogId = eventLogId;
    }

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        if(startTime != null){
            this.startTime = DateFormatUtils.format(new Date(Long.parseLong(startTime)), "yyyy-MM-dd HH:mm:ss",TimeZone.getTimeZone("GMT+8"));
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        if(endTime != null){
            this.endTime = DateFormatUtils.format(new Date(Long.parseLong(endTime)), "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("GMT+8"));
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    public String getStatusMessage() {
        return null != this.status ? EventStatusEnum.getName(this.status) : statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
