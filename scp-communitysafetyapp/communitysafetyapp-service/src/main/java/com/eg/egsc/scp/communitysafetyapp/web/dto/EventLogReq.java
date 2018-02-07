/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author zhangbingfa Created on 2017/12/14. 接受页面参数 bean
 */
public class EventLogReq extends BasePage {
    /**
     * 事件类型
     **/
    private String eventType;

    /**
     * 事件状态
     **/
    private String status;

    /**
     * 开始时间
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束时间
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        if (startTime == null) {
            return null;
        }
        return (Date) startTime.clone();
    }

    public void setStartTime(Date startTime) {
        if (startTime == null) {
            this.startTime = null;
        } else {
            this.startTime = (Date) startTime.clone();
        }
    }

    public Date getEndTime() {
        if (endTime == null) {
            return null;
        }
        return (Date) endTime.clone();
    }

    public void setEndTime(Date endTime) {
        if (endTime == null) {
            this.endTime = null;
        } else {
            this.endTime = (Date) endTime.clone();
        }
    }
}
