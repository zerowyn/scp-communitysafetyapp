/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service;

import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleCondition;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleDto;
import com.eg.egsc.scp.communitysafetyapp.web.vo.ResponsePageVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerRuleVo;

import java.util.List;

/**
 * @author xinghai
 * @since 2017/12/14
 */
public interface TriggerRuleService {
    void addTriggerRule(TriggerRuleDto triggerRuleDto) throws SafetyException;

    void updateTriggerRule(TriggerRuleDto triggerRuleDto) throws SafetyException;

    void delTriggerRule(List<String> triggerRuleIds) throws SafetyException;

    TriggerRuleVo getTriggerRuleById(String triggerRuleId) throws SafetyException;

    ResponsePageVo getTriggerRulePage(TriggerRuleCondition condition) throws SafetyException;

}
