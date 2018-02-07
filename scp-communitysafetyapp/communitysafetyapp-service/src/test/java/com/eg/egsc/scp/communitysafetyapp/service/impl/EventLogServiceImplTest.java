/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.util.SafetyUtils;
import com.eg.egsc.scp.communitysafetyapp.web.dto.EventLogReq;
import com.eg.egsc.scp.communitysafetyapp.web.vo.ResponsePageVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.reflect.Method;


/**
 * @Author zhangbingfa
 * Created on 2017/12/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class EventLogServiceImplTest {
    @Autowired
    private EventLogServiceImpl eventLogServiceImpl;
    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";

    /**
     * 事件日志测试
     */
    public EventLogReq newDto() {
        LoggerUtil.info(START);
        EventLogReq eventLogReq = new EventLogReq();
        eventLogReq.setPageSize(10);
        eventLogReq.setPageNo(1);
        eventLogReq.setEventType("20113");
        String startTime = "2017-12-27 12:50:55";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date star = sdf.parse(startTime);
            eventLogReq.setStartTime(star);
            eventLogReq.setEndTime(star);
            eventLogReq.setStatus("2");
        } catch (ParseException e) {
            LoggerUtil.info("时间格式错误");
        }
        return eventLogReq;
    }

    /**
     *
     * 查询事件日志
     */
    @Transactional
    @Test
    @Rollback
    public void getEventLogList() {
        LoggerUtil.info(START);
        try {
            EventLogReq req = this.newDto();
            ResponsePageVo eventLogList = eventLogServiceImpl.getEventLogList(req);
            ObjectMapper objectMapper = new ObjectMapper();
            String sto = objectMapper.writeValueAsString(eventLogList);
            LoggerUtil.info(sto);
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    /**
     * 查询事件日志异常
     */
    @Transactional
    @Test
    @Rollback
    public void getEventLogList2() {
        LoggerUtil.info(START);
        try {
            EventLogReq req = this.newDto();
            req.setPageNo(0);
            req.setPageSize(1);
            req.setStatus(null);
            ResponsePageVo list = eventLogServiceImpl.getEventLogList(req);
            String result = new ObjectMapper().writeValueAsString(list);
            LoggerUtil.info(result);
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    /**
     * getExtendTest
     */
    @Transactional
    @Test
    @Rollback
    public void getExtendTest() {
        LoggerUtil.info(START);
        Class clazz = eventLogServiceImpl.getClass();
        try {
            Method method = clazz.getDeclaredMethod("getExtend", List.class, JSONObject.class);
            method.setAccessible(true);
            List list = new ArrayList();
            Map dataMap = new LinkedHashMap();
            Map rowsMap = new LinkedHashMap();
            Map rowsMapImg = new LinkedHashMap();
            Map extendMap = new HashMap();
            Map extendMapImg = new HashMap();
            List rowsList = new ArrayList();
            rowsMap.put("eventType",20113);
            rowsMapImg.put("eventType",25010);
            extendMap.put("FacePicUrl","group1/M00/01/CD/wKgA9lphngKAXM_AAADJJmPcf4U97.jpeg");
            extendMap.put("FaceImageURI","group1/M00/01/CD/wKgA9lphngKAXM_AAADJJmPcf4U97.jpeg");
            List imgList = new ArrayList();
            imgList.add(new JSONObject(extendMap));
            extendMapImg.put("Images",imgList);
            rowsMap.put("extend",extendMap);
            rowsMapImg.put("extend",extendMapImg);
            rowsList.add(rowsMap);
            rowsList.add(rowsMapImg);
            dataMap.put("rows",rowsList);
            JSONObject json = new JSONObject(dataMap);
            List resultList = (List) method.invoke(eventLogServiceImpl, new Object[]{list, json});
            LoggerUtil.info(resultList.toString());
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    /**
     * 事件日志结果查询测试
     */
    @Rollback
    @Transactional
    @Test
    public void getTriggerResultsByIdTest() {
        LoggerUtil.info(START);
        try {
            String triggerResults = eventLogServiceImpl.getTriggerResultsById("12520");
            if (SafetyUtils.isEmpty(triggerResults)) {
                throw new SafetyException("联动参数有误");
            }
            LoggerUtil.info(triggerResults);
        } catch (SafetyException e) {
            LoggerUtil.info(e.getMessage());
        }
    }


}
