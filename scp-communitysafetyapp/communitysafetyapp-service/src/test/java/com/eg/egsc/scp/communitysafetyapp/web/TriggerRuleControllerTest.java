/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleCondition;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleDto;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggersDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author xinghai
 * @since 2017/12/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class TriggerRuleControllerTest {

    private MockMvc mockMvc;
    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";
    private static final String ENCODE_UTF_8 = "UTF-8";
    private static final String TRIGGER_RULE_LIST_URL = "/trigger/rule/list";


    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws SafetyException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional
    @Rollback()
    public void addTriggerRule() throws SafetyException {
        LoggerUtil.info(START);
        try {
            TriggerRuleDto triggerRuleDto = this.mockTriggerRuleUpdateDto();
            ObjectMapper objectMapper = new ObjectMapper();
            String dtoOne = objectMapper.writeValueAsString(triggerRuleDto);
            RequestBuilder requestOne = MockMvcRequestBuilders.post("/trigger/rule/save")
                    .contentType(MediaType.APPLICATION_JSON).content(dtoOne.getBytes(ENCODE_UTF_8));
            mockMvc.perform(requestOne).andDo(MockMvcResultHandlers.print()).andReturn();
            triggerRuleDto.setTriggers(null);
            String dtoTwo = objectMapper.writeValueAsString(triggerRuleDto);
            RequestBuilder requestTwo = MockMvcRequestBuilders.post("/trigger/rule/save")
                    .contentType(MediaType.APPLICATION_JSON).content(dtoTwo.getBytes(ENCODE_UTF_8));
            mockMvc.perform(requestTwo).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

        LoggerUtil.info(END);
    }

    private TriggerRuleDto mockTriggerRuleUpdateDto() {
        TriggerRuleDto triggerRuleDto = new TriggerRuleDto();
        List<TriggersDto> triggerDtoList = new ArrayList<>();
        TriggersDto triggersDto = new TriggersDto();
        triggersDto.setOrder(1);
        triggersDto.setMethod("FAC_CALLING");
        triggersDto.setTo("MSG/EVENT/LIFTCONTROL");
        //联动参数设置
        Map<String, String> map = new HashMap<>();
        map.put("name", "xinghai");
        map.put("age", "20");
        triggersDto.setParams(map);
        triggerRuleDto.setTriggerRuleId("1");
        triggerDtoList.add(triggersDto);
        triggerRuleDto.setEventType(30000);
        triggerRuleDto.setEventSourceCode(UUID.randomUUID().toString().replace("-", ""));
        triggerRuleDto.setIsSequential(true);
        triggerRuleDto.setCreator("admin");
        triggerRuleDto.setTriggers(triggerDtoList);
        return triggerRuleDto;
    }

    @Test
    @Transactional
    @Rollback()
    public void updateTriggerRule() {
        LoggerUtil.info(START);
        try {
            TriggerRuleDto triggerRuleDto = this.mockTriggerRuleUpdateDto();
            ObjectMapper objectMapper = new ObjectMapper();
            String dto = objectMapper.writeValueAsString(triggerRuleDto);
            RequestBuilder request = MockMvcRequestBuilders.post("/trigger/rule/update")
                    .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes(ENCODE_UTF_8));
            mockMvc.perform(request).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

        LoggerUtil.info(END);
    }

    @Test
    @Transactional
    @Rollback()
    public void delTriggerRule() {
        LoggerUtil.info(START);
        try {
            MvcResult resultOne = mockMvc
                    .perform(MockMvcRequestBuilders.post("/trigger/rule/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"triggerRuleId\":\"6a443c96fc644130920a9954746c5a19\"}"))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            MvcResult resultTwo = mockMvc
                    .perform(MockMvcRequestBuilders.post("/trigger/rule/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"test\":\"test\"}")).andDo(MockMvcResultHandlers.print()).andReturn();

            LoggerUtil.info(resultOne.getResponse().getContentAsString());
            LoggerUtil.info(resultTwo.getResponse().getContentAsString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

        LoggerUtil.info(END);
    }

    @Test
    @Transactional
    @Rollback()
    public void getTriggerRule() {
        LoggerUtil.info(START);
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/trigger/rule/get/176")
                    .contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers
                    .print()).andReturn();
            LoggerUtil.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

        LoggerUtil.info(END);
    }

    private TriggerRuleCondition mockTriggerRuleCondition() {
        TriggerRuleCondition condition = new TriggerRuleCondition();
        condition.setEventSourceCode("98C653AA928236D89991DFD007620123");
        condition.setEventType(5000);
        condition.setPageNo(1);
        condition.setPageSize(10);
        return condition;
    }

    @Test
    @Transactional
    @Rollback()
    public void getTriggerRuleList() {
        LoggerUtil.info(START);
        try {
            TriggerRuleCondition condition = this.mockTriggerRuleCondition();
            ObjectMapper objectMapper = new ObjectMapper();
            String dto = objectMapper.writeValueAsString(condition);
            RequestBuilder request = MockMvcRequestBuilders.post(TRIGGER_RULE_LIST_URL)
                    .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes(ENCODE_UTF_8));
            mockMvc.perform(request).andDo(MockMvcResultHandlers.print()).andReturn();

            condition.setPageNo(0);
            condition.setPageSize(10);
            dto = objectMapper.writeValueAsString(condition);
            RequestBuilder request1 = MockMvcRequestBuilders.post(TRIGGER_RULE_LIST_URL)
                    .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes(ENCODE_UTF_8));
            mockMvc.perform(request1).andDo(MockMvcResultHandlers.print()).andReturn();

            condition.setPageSize(0);
            condition.setPageNo(1);
            dto = objectMapper.writeValueAsString(condition);
            RequestBuilder request2 = MockMvcRequestBuilders.post(TRIGGER_RULE_LIST_URL)
                    .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes(ENCODE_UTF_8));
            mockMvc.perform(request2).andDo(MockMvcResultHandlers.print()).andReturn();

            condition.setPageSize(10);
            condition.setPageNo(1);
            condition.setEventType(-1);
            dto = objectMapper.writeValueAsString(condition);
            RequestBuilder request3 = MockMvcRequestBuilders.post(TRIGGER_RULE_LIST_URL)
                    .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes(ENCODE_UTF_8));
            mockMvc.perform(request3).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

        LoggerUtil.info(END);
    }

}
