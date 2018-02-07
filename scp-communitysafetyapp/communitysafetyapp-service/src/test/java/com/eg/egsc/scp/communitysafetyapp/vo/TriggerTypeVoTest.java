package com.eg.egsc.scp.communitysafetyapp.vo;

import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerTypeVo;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gongxin
 * @since 2018-01-16
 */
public class TriggerTypeVoTest {

    @Test
    @Transactional
    @Rollback
    public void getTriggerRuleVoTest(){
        TriggerTypeVo triggerTypeVo = new TriggerTypeVo("zhangsan","11","22","33");
        triggerTypeVo.setName("zhangsan");
        triggerTypeVo.setValue("11");
        triggerTypeVo.setCode("22");
        triggerTypeVo.setTo("33");


        LoggerUtil.info(triggerTypeVo.getName()+"--"+triggerTypeVo.getValue()
                +"--"+triggerTypeVo.getCode()+"--"+triggerTypeVo.getTo());
    }

}
