/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.enums;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ResultStatusTest {

    @Test
    public void testResultStatusTest(){
        String message = ResultStatus.OK.message();
        String message1 = ResultStatus.AUTHENTICATION_ERROR.message();
        String message2 = ResultStatus.BUSINESS_ERROR.message();
        String message3 = ResultStatus.REQUEST_ERROR.message();
        String message4 = ResultStatus.REQUEST_PARAM_ERROR.message();
        String message5 = ResultStatus.SYSTEM_ERROR.message();
        String message6 = ResultStatus.REQUEST_PARAM_TRANSFER_ERROR.message();
        String message7 = ResultStatus.VALIDATION_ERROR.message();
        LoggerUtil.info(message+message1+message2+message3+message4+message5+message6+message7);

    }

}