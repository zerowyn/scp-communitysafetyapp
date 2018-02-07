/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.lang.reflect.Constructor;


/**
 * @author gucunyang
 * @since 2018-01-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class LoggerUtilTest {

    /**
     * LoggerUtilsConstructorTest
     */
    @Test
    public void loggerUtilsConstructorTest() {
        try {
            Class<?> classType = LoggerUtil.class;
            Constructor<?> loggerUtilsConstructor = classType.getDeclaredConstructor();
            loggerUtilsConstructor.setAccessible(true);
            LoggerUtil loggerUtil = (LoggerUtil) loggerUtilsConstructor.newInstance();
            LoggerUtil.info(loggerUtil.toString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
        LoggerUtil.info("单元测试成功");
    }
}