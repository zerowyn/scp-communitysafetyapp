/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service;

import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.web.vo.EventTypeVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.SubSystemVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerTypeVo;

import java.util.List;
import java.util.Map;

/**
 * @author xinghai
 * @since 2017/12/26
 */
public interface ResourceService {
    List<SubSystemVo> getSubSystemList() throws SafetyException;

    List<EventTypeVo> getEventTypeList(String subSystemCode) throws SafetyException;

    List<EventTypeVo> getTriggerEventTypeList(String subSystemCode) throws SafetyException;

    List<TriggerTypeVo> getTriggerTypeVoList() throws SafetyException;

    Map getEventLogEventTypeList() throws SafetyException;
}
