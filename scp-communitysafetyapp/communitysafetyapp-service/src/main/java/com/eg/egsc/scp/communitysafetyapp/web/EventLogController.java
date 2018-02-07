/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eg.egsc.framework.service.base.web.BaseWebController;
import com.eg.egsc.scp.communitysafetyapp.service.EventLogService;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.dto.EventLogReq;
import com.eg.egsc.scp.communitysafetyapp.web.result.ApiResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 事件日志
 *
 * @author xinghai
 * @since 2017/12/18
 */
@RestController
@RequestMapping("/event/log")
@Api("事件日志")
public class EventLogController extends BaseWebController {
    @Autowired
    private EventLogService eventLogServiceImpl;

    @ApiOperation("事件日志查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ApiResult getLogList(@RequestBody EventLogReq req) {
        LoggerUtil.info("查询事件日志开始！");
        return ApiResult.successFul(eventLogServiceImpl.getEventLogList(req));
    }

    @ApiOperation("事件日志结果查询")
    @RequestMapping(value = "/triggerId/{id}", method = RequestMethod.GET)
    public ApiResult getTriggerResultsById(@PathVariable String id) {
        LoggerUtil.info("事件日志结果开始");
        return ApiResult.successFul(eventLogServiceImpl.getTriggerResultsById(id));
    }

}
