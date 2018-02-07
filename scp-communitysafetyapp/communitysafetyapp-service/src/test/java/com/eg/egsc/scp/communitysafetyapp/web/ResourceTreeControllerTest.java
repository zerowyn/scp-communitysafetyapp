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

/**
 * @author zpc
 * @since 2018/1/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ResourceTreeControllerTest {
    private MockMvc mockMvc;
    private static final String TEST_START = "单元测试开始";
    private static final String TEST_END = "单元测试结束";

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws SafetyException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getRootBaseOrg() {
        LoggerUtil.info(TEST_START);
        try {
            MvcResult result = mockMvc
                    .perform(MockMvcRequestBuilders.get("/resource/root/org")
                            .contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers
                            .print()).andReturn();
            LoggerUtil.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

        LoggerUtil.info(TEST_END);
    }

    @Test
    @Transactional
    @Rollback()
    public void getDeviceList() {
        LoggerUtil.info(TEST_START);
        try {
            MvcResult result = mockMvc
                    .perform(MockMvcRequestBuilders.get("/resource/device/tree?code=2009&uuid='135544'")
                            .contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers
                            .print()).andReturn();
            LoggerUtil.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

        LoggerUtil.info(TEST_END);

    }

}