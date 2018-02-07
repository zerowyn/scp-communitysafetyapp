/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service;

import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.web.dto.EventLogReq;
import com.eg.egsc.scp.communitysafetyapp.web.vo.ResponsePageVo;


/**
 * 事件日志接口
 *
 * @author xinghai
 * @since 2017/12/8
 */
public interface EventLogService {
    ResponsePageVo getEventLogList(EventLogReq eventLogReq) throws SafetyException;

    String getTriggerResultsById(String id) throws SafetyException;
}
