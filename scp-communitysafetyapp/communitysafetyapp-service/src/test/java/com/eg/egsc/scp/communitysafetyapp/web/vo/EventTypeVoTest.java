/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author gucunyang
 * @since 2018-01-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class EventTypeVoTest {

    /**
     * EventTypeVoTest
     */
    @Test
    public void eventTypeVoTest() {
        EventTypeVo eventTypeVo1 = new EventTypeVo();
        EventTypeVo eventTypeVo2 = new EventTypeVo(1001, "门禁");
        eventTypeVo1.setCode(1002);
        eventTypeVo2.setName("测试");
        LoggerUtil.info("单元测试成功");
    }
}