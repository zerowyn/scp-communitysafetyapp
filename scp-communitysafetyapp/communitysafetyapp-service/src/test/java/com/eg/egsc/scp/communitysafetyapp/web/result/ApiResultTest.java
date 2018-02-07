package com.eg.egsc.scp.communitysafetyapp.web.result;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ApiResult 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ApiResultTest {

    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";

    /**
     * 错误的返回 set get  测试
     */
    @Test
    public void errorTest() {
        LoggerUtil.info(START);
        ApiResult error = ApiResult.error(404, "错误测试");
        LoggerUtil.info(error.toString());
        error.setStatus(500);
        error.setData(new Object());
        error.setMessage("错误测试");
        error.setSuccess(false);
        LoggerUtil.info(error.getStatus() + "-" + error.getMessage() + "-" + error.getData() + "-" + error.isSuccess());
        LoggerUtil.info(END);
    }
}