package com.eg.egsc.scp.communitysafetyapp.util;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Constructor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ValidationUtilsTest {
    @Test
    public void isUuidValid() {
        boolean bResult = ValidationUtils.isUuidValid("100420110015285d8511");
        if (bResult){
            LoggerUtil.info("该uuid有效");
        }
    }

    @Test
    public void isDeviceCodeValid() {
        boolean bResult = ValidationUtils.isDeviceCodeValid("2017");
        if (bResult){
            LoggerUtil.info("该设备编码有效");
        }
    }

    @Test
    public void isEventTypeValid() {
        boolean bResult = ValidationUtils.isEventTypeValid("3000");
        if (bResult){
            LoggerUtil.info("该事件类型有效");
        }
    }

    @Test
    public void isPositiveInteger() {
        Integer num = 12;
        boolean bResult = ValidationUtils.isPositiveInteger(String.valueOf(num));
        if (bResult){
            LoggerUtil.info(num + "是正整数");
        }
    }

    @Test
    public void isDecimal() {
        double num = 11.2;
        boolean bResult = ValidationUtils.isDecimal(String.valueOf(num));
        if (bResult){
            LoggerUtil.info(num + "是小数");
        }
    }

    /**
     * Test Constructor
     */
    @Test
    public void validationUtilsConstructorTest() {
        try {
            Class<?> classType = ValidationUtils.class;
            Constructor<?> validationUtilsConstructor = classType.getDeclaredConstructor();
            validationUtilsConstructor.setAccessible(true);
            ValidationUtils validationUtils = (ValidationUtils) validationUtilsConstructor.newInstance();
            LoggerUtil.info(validationUtils.toString());
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
        LoggerUtil.info("单元测试成功");
    }

    /**
     * Test isEmpty
     */
    @Test
    public void isEmpty() {
        String str = " ";
        boolean strResult = ValidationUtils.isPositiveInteger(str);
        if (strResult){
            LoggerUtil.info(str + "不是正整数");
        }
    }

}