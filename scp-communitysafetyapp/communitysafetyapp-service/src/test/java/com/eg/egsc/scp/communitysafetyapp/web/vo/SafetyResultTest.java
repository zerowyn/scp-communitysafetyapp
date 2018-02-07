package com.eg.egsc.scp.communitysafetyapp.web.vo;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class SafetyResultTest {


    /**
     * 测试 SafetyResult set get 方法
     */
    @Test
    public void setSafetyResultTest() {
        SafetyResult s = new SafetyResult();
        s.setCode("1");
        s.setErrMsg("2");
        s.setSuccess(true);
        s.setData("3");
        LoggerUtil.info(s.getCode() + s.getErrMsg() + s.getData() + s.isSuccess());
    }
}