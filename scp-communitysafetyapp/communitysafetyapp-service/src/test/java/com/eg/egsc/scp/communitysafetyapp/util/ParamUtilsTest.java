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
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ParamUtilsTest {
    private static final String START="单元测试开始";
    private static final String END = "单元测试结束";

    /**
     * 测试ParamUtils构造方法
     */
    @Test
    public void testParamUtilsTest() {
        LoggerUtil.info(START);
        try {
            Class singletonClass = Class.forName("com.eg.egsc.scp.communitysafetyapp.util.ParamUtils");
            Constructor c = singletonClass.getDeclaredConstructor();
            c.setAccessible(true);
            ParamUtils po=(ParamUtils)c.newInstance();
            LoggerUtil.info(po.toString());
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info("e{}",e);
        }
    }

    /**
     * 测试 toStringList 为空
     */
    @Test
    public void toStringListTest() {
        LoggerUtil.info(START);
        List<String> longList = ParamUtils.toStringList("abc,efg,hig", ",");
        LoggerUtil.info(longList.toString());
        LoggerUtil.info(END);
    }

}