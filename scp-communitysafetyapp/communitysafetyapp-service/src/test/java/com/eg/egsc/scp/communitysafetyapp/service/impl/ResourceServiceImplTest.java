/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service.impl;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.vo.EventTypeVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.SubSystemVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerTypeVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ResourceServiceImplTest {
    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";
    @Autowired
    private ResourceServiceImpl resourceServiceImpl;

    @Test
    @Transactional
    @Rollback
    public void getSubSystemListTest() {
        LoggerUtil.info(START);
        try {
            List<SubSystemVo> subSystemVoList = resourceServiceImpl.getSubSystemList();
            ObjectMapper objectMapper = new ObjectMapper();
            for (int i=0;i<=subSystemVoList.size(); i++) {
                String subSvo = objectMapper.writeValueAsString(subSystemVoList.get(i));
                LoggerUtil.info(subSvo);
            }
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info("e{}", e);
        }
    }

    //获取事件列表测试
    @Test
    @Transactional
    @Rollback
    public void getEventTypeListTest() {
        LoggerUtil.info(START);
        try {
            List<EventTypeVo> eventTypeVoListOne = resourceServiceImpl.getEventTypeList("2000");
            List<EventTypeVo> eventTypeVoListTwo = resourceServiceImpl.getEventTypeList("2001");
            ObjectMapper objectMapper = new ObjectMapper();
            for (EventTypeVo evo : eventTypeVoListOne) {
                String eventTypeOne = objectMapper.writeValueAsString(evo);
                LoggerUtil.info(eventTypeOne);
            }
            for (EventTypeVo evt : eventTypeVoListTwo) {
                String eventTypeTwo = objectMapper.writeValueAsString(evt);
                LoggerUtil.info(eventTypeTwo);
            }
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info("e{}", e);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void getTriggerEventTypeListTest() {
        LoggerUtil.info(START);
        try {
            List<EventTypeVo> eventTypeVoListOne = resourceServiceImpl.getTriggerEventTypeList("2009");
            List<EventTypeVo> eventTypeVoListTwo = resourceServiceImpl.getTriggerEventTypeList("2001");
            List<EventTypeVo> eventTypeVoListThree = resourceServiceImpl.getTriggerEventTypeList("2011");
            ObjectMapper objectMapper = new ObjectMapper();
            for (EventTypeVo evo : eventTypeVoListOne) {
                String eventTypeVoOne = objectMapper.writeValueAsString(evo);
                LoggerUtil.info(eventTypeVoOne);
            }
            for (EventTypeVo evt : eventTypeVoListTwo) {
                String eventTypeVoTwo = objectMapper.writeValueAsString(evt);
                LoggerUtil.info(eventTypeVoTwo);
            }
            for (EventTypeVo evth : eventTypeVoListThree) {
                String eventTypeVoThree = objectMapper.writeValueAsString(evth);
                LoggerUtil.info(eventTypeVoThree);
            }
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info("e{}", e);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void getTriggerTypeVoListTest() {
        LoggerUtil.info(START);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<TriggerTypeVo> listTriggerTypeVos = resourceServiceImpl.getTriggerTypeVoList();
            String triggerTypeVos = objectMapper.writeValueAsString(listTriggerTypeVos);
            LoggerUtil.info(triggerTypeVos);
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info("e{}", e);
        }
    }

}
