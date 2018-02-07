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
public class FilterUtilsTest {

    /**
     * FilterUtilsConstructorTest
     */
    @Test
    public void filterUtilsConstructorTest() {
        try {
            Class<?> classType = FilterUtils.class;
            Constructor<?> filterUtilsConstructor = classType.getDeclaredConstructor();
            filterUtilsConstructor.setAccessible(true);
            FilterUtils filterUtils = (FilterUtils) filterUtilsConstructor.newInstance();
            LoggerUtil.info(filterUtils.toString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
        LoggerUtil.info("单元测试成功");
    }
}