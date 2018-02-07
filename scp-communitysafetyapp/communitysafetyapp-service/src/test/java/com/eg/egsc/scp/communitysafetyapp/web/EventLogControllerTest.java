/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.dto.EventLogReq;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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


/**
 * @author xinghai
 * @since 2017/12/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class EventLogControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext web;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.web).build();
    }

    public EventLogReq newDto() {
        EventLogReq eventLogReq = new EventLogReq();
        eventLogReq.setPageSize(10);
        eventLogReq.setPageNo(1);
        return eventLogReq;
    }

    /**
     * 事件日志测试
     */
    @Test
    @Transactional
    @Rollback
    public void getLogList() {
        LoggerUtil.info(START);
        EventLogReq eventLogReq = this.newDto();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String dto = objectMapper.writeValueAsString(eventLogReq);
            RequestBuilder request = MockMvcRequestBuilders.post("/event/log/list")
                    .contentType(MediaType.APPLICATION_JSON).content(dto);
            mockMvc.perform(request)
                    .andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

        LoggerUtil.info(END);
    }

    /**
     * 事件日志结果查询测试
     */
    @Rollback
    @Transactional
    @Test
    public void getLogListTest() {
        LoggerUtil.info(START);
        EventLogReq eventLogReq = this.newDto();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String dto = objectMapper.writeValueAsString(eventLogReq);
            RequestBuilder request = MockMvcRequestBuilders
                    .get("/event/log/triggerId/12520")
                    .contentType(MediaType.APPLICATION_JSON).content(dto);
            MvcResult result = mockMvc.perform(request)
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            LoggerUtil.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
        LoggerUtil.info(END);
    }
}
