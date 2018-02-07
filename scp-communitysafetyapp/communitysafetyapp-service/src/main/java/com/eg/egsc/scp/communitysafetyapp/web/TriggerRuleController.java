/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web;

import com.alibaba.fastjson.JSONObject;
import com.eg.egsc.framework.service.base.web.BaseWebController;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceTreeService;
import com.eg.egsc.scp.communitysafetyapp.service.TriggerRuleService;
import com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.util.ParamUtils;
import com.eg.egsc.scp.communitysafetyapp.util.SafetyUtils;
import com.eg.egsc.scp.communitysafetyapp.util.ValidationUtils;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleCondition;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleDto;
import com.eg.egsc.scp.communitysafetyapp.web.result.ApiResult;
import com.eg.egsc.scp.eventcomponent.client.TriggerTypeClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.PARAMS_VALID;

/**
 * 联动规则
 *
 * @author xinghai
 * @since 2017/12/14
 */
@RestController
@RequestMapping(value = "/trigger/rule")
public class TriggerRuleController extends BaseWebController {

    @Autowired
    TriggerRuleService triggerRuleServiceImpl;

    @Autowired
    ResourceTreeService resourceTreeServiceImpl;

    @Autowired
    TriggerTypeClient triggerTypeClientImpl;

    /**
     * 联动规则新增
     *
     * @author xinghai
     * @date 2017/12/14 15:00
     */
    @ApiOperation("联动规则新增")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResult addTriggerRule(@Valid @RequestBody TriggerRuleDto triggerRuleDto) {
        triggerRuleServiceImpl.addTriggerRule(triggerRuleDto);
        return ApiResult.successFul("联动规则新增成功");
    }

    /**
     * 联动规则修改
     *
     * @author xinghai
     * @date 2017/12/19 9:28
     */
    @ApiOperation("联动规则修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResult updateTriggerRule(@RequestBody TriggerRuleDto triggerRuleDto) {
        triggerRuleServiceImpl.updateTriggerRule(triggerRuleDto);
        return ApiResult.successFul("联动规则修改成功");
    }

    /**
     * 联动规则删除
     *
     * @author xinghai
     * @date 2017/12/19 9:28
     */
    @ApiOperation("联动规则删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResult delTriggerRule(@RequestBody String triggerRuleId) {
        if (SafetyUtils.isEmpty(triggerRuleId)) {
            throw new SafetyException(PARAMS_VALID);
        }

        JSONObject json = JSONObject.parseObject(triggerRuleId);
        List<String> triggerIds = ParamUtils.toStringList(json.getString("triggerRuleId"), ",");
        triggerRuleServiceImpl.delTriggerRule(triggerIds);
        return ApiResult.successFul("联动规则删除成功");
    }

    /**
     * 联动规则查询
     *
     * @author xinghai
     * @date 2017/12/19 9:28
     */
    @ApiOperation("联动规则查询")
    @RequestMapping(value = "/get/{triggerRuleId}", method = RequestMethod.GET)
    public ApiResult getTriggerRule(@PathVariable("triggerRuleId") String triggerId) {
        return ApiResult.successFul(triggerRuleServiceImpl.getTriggerRuleById(triggerId));
    }

    /**
     * 联动规则列表查询(分页)
     *
     * @author xinghai
     * @date 2017/12/20 16:42
     * @Param
     */
    @ApiOperation("联动规则列表查询(分页)")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ApiResult getTriggerRuleList(@Valid @RequestBody TriggerRuleCondition condition) {
        if (null != condition.getPageNo() &&
                !ValidationUtils.isPositiveInteger(condition.getPageNo().toString())) {
            throw new SafetyException(ErrorCodeConstant.COMMON_PAGE_NO_ILLEGAL);
        }

        if (null != condition.getPageSize() &&
                !ValidationUtils.isPositiveInteger(condition.getPageSize().toString())) {
            LoggerUtil.info(condition.getPageSize().toString());
            throw new SafetyException(ErrorCodeConstant.COMMON_PAGE_SIZE_ILLEGAL);
        }

        if (null != condition.getEventType() &&
                !ValidationUtils.isPositiveInteger(condition.getEventType().toString())) {
            throw new SafetyException(ErrorCodeConstant.PARAMS_VALID);
        }

        return ApiResult.successFul(triggerRuleServiceImpl.getTriggerRulePage(condition));
    }

}
