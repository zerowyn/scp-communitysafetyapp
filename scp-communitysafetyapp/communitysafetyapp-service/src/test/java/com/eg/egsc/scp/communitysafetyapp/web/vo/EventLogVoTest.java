/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author gucunyang
 * @since 2018-01-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class EventLogVoTest {

    /**
     * EventLogVoTest
     */
    @Test
    public void eventLogVoTest() {
        EventLogVo eventLogVo = new EventLogVo();
        eventLogVo.setId(100009L);
        eventLogVo.setEventLogId("999777");
        eventLogVo.setEventType(1001);
        eventLogVo.setEventTypeName("门禁");
        String startTimeStr = "2018-01-15 14:17:27";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date star = sdf.parse(startTimeStr);
            eventLogVo.setStartTime(String.valueOf(star.getTime()));
            eventLogVo.setEndTime(String.valueOf(star.getTime()));
        } catch (ParseException e) {
            LoggerUtil.info("时间格式错误");
        }
        eventLogVo.setContent("测试");
        eventLogVo.setCreator("xl");
        eventLogVo.setDeviceCode("88899");
        eventLogVo.setResourceCode("11231");
        eventLogVo.setDeviceName("资源名012");
        eventLogVo.setResourceName("888设备资源001");
        eventLogVo.setStatus(2);
        eventLogVo.setStatusMessage("测试");
        String extendString = "{\"orderBy\":\"event_log_id\",\"sort\":\"asc\"}";
        eventLogVo.setExtend(extendString);

        Long id = eventLogVo.getId();
        String eventLogId = eventLogVo.getEventLogId();
        Integer eventType = eventLogVo.getEventType();
        String eventTypeName = eventLogVo.getEventTypeName();
        String startTime = eventLogVo.getStartTime();
        String endTime = eventLogVo.getEndTime();
        String content = eventLogVo.getContent();
        String creator = eventLogVo.getCreator();
        String deviceCode = eventLogVo.getDeviceCode();
        String resourceCode = eventLogVo.getResourceCode();
        String deviceName = eventLogVo.getDeviceName();
        String resourceName = eventLogVo.getResourceName();
        Integer status = eventLogVo.getStatus();
        String statusMessage = eventLogVo.getStatusMessage();
        Object extend = eventLogVo.getExtend();
        String eventLogVoStr = "{\"id\":" + id + ",\"eventLogId\":" + eventLogId + ",\"eventType\":" + eventType
                + ",\"eventTypeName\":" + eventTypeName + ",\"startTime\":" + startTime + ",\"endTime\":" + endTime
                + ",\"content\":" + content + ",\"creator\":" + creator + ",\"deviceCode\":" + deviceCode
                + ",\"resourceCode\":" + resourceCode + ",\"deviceName\":" + deviceName + ",\"resourceName\":"
                + resourceName + ",\"status\":" + status + ",\"statusMessage\":" + statusMessage
                + ",\"extend\":" + extend + "}";
        LoggerUtil.info(eventLogVoStr);
        LoggerUtil.info("单元测试成功");
    }

}