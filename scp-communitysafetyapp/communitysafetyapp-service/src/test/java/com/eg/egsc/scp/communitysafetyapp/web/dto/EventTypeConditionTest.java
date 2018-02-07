package com.eg.egsc.scp.communitysafetyapp.web.dto;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * EventTypeCondition 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class EventTypeConditionTest {

    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";

    /**
     * EventTypeCondition set get 方法测试
     */
    @Test
    public void setEventTypeCondition() {
        LoggerUtil.info(START);
        EventTypeCondition etc = new EventTypeCondition(100, 1);
        etc.setEventTypeMax(100);
        etc.setEventTypeMin(1);
        LoggerUtil.info(etc.getEventTypeMax() + "--" + etc.getEventTypeMin());
        LoggerUtil.info(END);
    }
}