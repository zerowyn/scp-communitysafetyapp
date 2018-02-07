/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Constructor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class SafetyConstantTest {


    private static final String START = "单元测试成功";
    private static final String END = "单元测试失败";

    /**
     * 测试 SafetyConstant 构造方法
     */
    @Test
    public void testSafetyConstantTest() {
        LoggerUtil.info(START);
        try {
            Class singletonClass = Class.forName("com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant");
            Constructor c = singletonClass.getDeclaredConstructor();
            c.setAccessible(true);
            Object o = c.newInstance();
            LoggerUtil.info(o.toString());
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info("e{}",e);
        }

    }
}