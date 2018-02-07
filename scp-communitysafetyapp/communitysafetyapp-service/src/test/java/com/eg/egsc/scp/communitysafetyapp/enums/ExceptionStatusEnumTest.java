package com.eg.egsc.scp.communitysafetyapp.enums;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ExceptionStatusEnumTest {

    @Test
    public void exceptionStatus() {
        LoggerUtil.info(ExceptionStatusEnum.TRIGGER_ADD_FAIL.getReasonPhrase());
        LoggerUtil.info(ExceptionStatusEnum.DELETE_FAIL.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.EVENT_LOG_GET_FAIL.getValue());
        LoggerUtil.info(ExceptionStatusEnum.ADD_FAIL.getValue());
        LoggerUtil.info(ExceptionStatusEnum.QUERY_FAIL.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.PARAM_INVALID.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.REQUEST_DEVICE_TIMEOUT.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.REQUEST_TIMEOUT.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.REQUEST_TREE_TIMEOUT.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.TRIGGER_DEL_FAIL.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.TRIGGER_RESULT_GET_FAIL.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.UPDATE_FAIL.getMessage());
        LoggerUtil.info(ExceptionStatusEnum.TRIGGER_UPDATE_FAIL.getMessage());
    }


}