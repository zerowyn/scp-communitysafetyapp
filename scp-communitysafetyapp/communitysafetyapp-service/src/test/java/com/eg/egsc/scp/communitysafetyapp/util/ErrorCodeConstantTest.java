package com.eg.egsc.scp.communitysafetyapp.util;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Constructor;

/**
 * ErrorCodeConstant 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ErrorCodeConstantTest {

    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";

    /**
     * ErrorCodeConstant 构造方法测试
     */
    @Test
    public void testErrorCodeConstantTest() {
        LoggerUtil.info(START);
        try {
            Class singletonClass = Class.forName("com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant");
            Constructor c = singletonClass.getDeclaredConstructor();
            c.setAccessible(true);
            ErrorCodeConstant ecc = (ErrorCodeConstant) c.newInstance();
            LoggerUtil.info(ecc.toString());
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info("e{}", e);
        }
    }
}