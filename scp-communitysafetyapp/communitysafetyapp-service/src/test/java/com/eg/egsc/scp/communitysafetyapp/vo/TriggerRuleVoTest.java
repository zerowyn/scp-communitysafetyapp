package com.eg.egsc.scp.communitysafetyapp.vo;


import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerRuleVo;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author gongxin
 * @since 2018-01-16
 */

public class TriggerRuleVoTest {

    @Test
    @Transactional
    @Rollback
    public void getTriggerRuleVoTest() {

        TriggerRuleVo triggerRuleVo = new TriggerRuleVo();
        String uuid= UUID.randomUUID().toString();
        uuid=uuid.replace("-", "");
        List  list = new ArrayList();
        list.add("A");
        triggerRuleVo.setEventType(1);
        triggerRuleVo.setEventTypeName("test");
        triggerRuleVo.setUuid(uuid);
        triggerRuleVo.setEventSourceCode("1666");
        triggerRuleVo.setDeviceName("test1");
        triggerRuleVo.setTriggers(list);
        triggerRuleVo.setCreator("TOM");
        triggerRuleVo.setIsSequential(true);

        LoggerUtil.info(triggerRuleVo.getEventType()+"--"+triggerRuleVo.getEventTypeName()
        +"--"+triggerRuleVo.getUuid()+"--"+triggerRuleVo.getEventSourceCode()
                +"--"+triggerRuleVo.getDeviceName()+"--"+triggerRuleVo.getTriggers()
                +"--"+triggerRuleVo.getCreator());

    }
}
