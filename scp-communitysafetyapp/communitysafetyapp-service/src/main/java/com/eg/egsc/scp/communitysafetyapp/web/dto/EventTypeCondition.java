/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.dto;

/**
 * @author zhangbingfa
 * @since 2018/1/21
 */
public class EventTypeCondition {
    /**
     * 事件类型最大值
     */
    private int eventTypeMax;
    /**
     * 事件类型最小值
     */
    private int eventTypeMin;

    public EventTypeCondition(int min, int max) {
        this.eventTypeMin = min;
        this.eventTypeMax = max;
    }

    public int getEventTypeMax() {
        return eventTypeMax;
    }

    public void setEventTypeMax(int eventTypeMax) {
        this.eventTypeMax = eventTypeMax;
    }

    public int getEventTypeMin() {
        return eventTypeMin;
    }

    public void setEventTypeMin(int eventTypeMin) {
        this.eventTypeMin = eventTypeMin;
    }
}
