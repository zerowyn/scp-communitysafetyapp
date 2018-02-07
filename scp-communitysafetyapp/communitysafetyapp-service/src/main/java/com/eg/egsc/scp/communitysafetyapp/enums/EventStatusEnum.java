/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.enums;

/**
 * @Author zhangbingfa Created on 2017/12/27.
 */
public enum EventStatusEnum {
    EVENTSTART("事件开始", 0),
    EVENTPULSE("事件进行中", 1),
    EVENTEND("事件结束", 2);

    private String name;
    private int value;

    EventStatusEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getName(int value) {
        for (EventStatusEnum eventType : EventStatusEnum.values()) {
            if (eventType.value == value) {
                return eventType.name;
            }
        }
        return null;
    }
}


