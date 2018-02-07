/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

import com.eg.egsc.scp.communitysafetyapp.web.vo.EventTypeVo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author xinghai
 * @since 2017/12/19
 */
public class SafetyUtils {
    private SafetyUtils() {
    }

    /**
     * 事件类型Map
     */
    public static Map<Integer, String> getEventTypeMap(List<EventTypeVo> eventTypeVoList) {
        Map<Integer, String> map = new HashMap<>();
        for (EventTypeVo eventType : eventTypeVoList) {
            map.put(eventType.getCode(), eventType.getName());
        }
        return map;
    }

    /**
     * 判断一个字符串是否为空
     *
     * @author xinghai
     * @date 2017/12/19 14:40
     */
    public static boolean isEmpty(CharSequence cs) {
        return StringUtils.isEmpty(cs);
    }

    /**
     * 判断一个列表是否为空
     *
     * @author xinghai
     * @date 2017/12/19 14:40
     */
    public static <T> boolean isEmpty(Collection<T> c) {
        return CollectionUtils.isEmpty(c);
    }

}
