/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ResourceControllerTest {

    private MockMvc mockMvc;
    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws SafetyException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    //事件类型列表测试
    @Test
    @Transactional
    @Rollback()
    public void getEventTypeList() {
        LoggerUtil.info("获取事件类型列表单元测试开始");
        try {
            MvcResult result = mockMvc
                    .perform(MockMvcRequestBuilders.get("/resource/eventType/2009/list")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            LoggerUtil.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            throw new SafetyException(e.getMessage());
        }
        LoggerUtil.info("获取事件类型列表单元测试结束");
    }

    //设备分类列表测试
    @Test
    @Transactional
    @Rollback
    public void getSubSystemListTest() {
        LoggerUtil.info("获取设备分类列表单元测试开始");
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                    .get("/resource/subSystem/list")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            LoggerUtil.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
        LoggerUtil.info("获取设备分类列表单元测试结束");
    }

    //获取联动方式测试
    @Test
    @Transactional
    @Rollback
    public void getTriggerTypeListTest() {
        LoggerUtil.info(START);
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/resource/triggerType/list")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            LoggerUtil.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
        LoggerUtil.info(END);
    }

    @Test
    @Transactional
    @Rollback
    public void getEventTypeList1() {
        LoggerUtil.info("获取事件类型单元测试开始");
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/resource/log/eventType/list")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print()).andReturn();
            LoggerUtil.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
        LoggerUtil.info("获取事件类型单元测试结束");
    }

}
