package com.eg.egsc.scp.communitysafetyapp.web;

import com.eg.egsc.framework.service.base.web.BaseWebController;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceService;
import com.eg.egsc.scp.communitysafetyapp.web.result.ApiResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


/**
 * 数据字典 Created by xinghai on 2017/12/26.
 */
@RestController
@RequestMapping(value = "/resource")
public class ResourceController extends BaseWebController {

    @Autowired
    ResourceService resourceServiceImpl;

    /**
     * 事件类型
     */
    @ApiOperation("联动规则事件类型列表")
    @RequestMapping(value = "/eventType/{code}/list", method = RequestMethod.GET)
    public ApiResult getEventTypeList(@PathVariable("code") String code) {
        return ApiResult.successFul(resourceServiceImpl.getTriggerEventTypeList(code));
    }

    /**
     * 设备分类
     */
    @ApiOperation("设备分类")
    @RequestMapping(value = "/subSystem/list", method = RequestMethod.GET)
    public ApiResult getSubSystemList() {
        return ApiResult.successFul(resourceServiceImpl.getSubSystemList());
    }

    @ApiOperation("联动方式列表")
    @RequestMapping(value = "/triggerType/list", method = RequestMethod.GET)
    public ApiResult getTriggerTypeList() {
        return ApiResult.successFul(resourceServiceImpl.getTriggerTypeVoList());
    }

    @ApiOperation("事件日志事件类型列表")
    @RequestMapping(value = "/log/eventType/list", method = RequestMethod.GET)
    public ApiResult getEventTypeList() {
        return ApiResult.successFul(resourceServiceImpl.getEventLogEventTypeList());
    }
}
