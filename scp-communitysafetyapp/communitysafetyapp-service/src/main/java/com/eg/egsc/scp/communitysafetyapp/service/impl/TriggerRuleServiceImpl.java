/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eg.egsc.common.component.utils.BeanUtils;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.scp.communitysafetyapp.enums.ExceptionStatusEnum;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceService;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceTreeService;
import com.eg.egsc.scp.communitysafetyapp.service.TriggerRuleService;
import com.eg.egsc.scp.communitysafetyapp.util.FilterUtils;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.util.SafetyUtils;
import com.eg.egsc.scp.communitysafetyapp.util.ValidationUtils;
import com.eg.egsc.scp.communitysafetyapp.web.dto.EventTypeCondition;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleCondition;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleDto;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggersDto;
import com.eg.egsc.scp.communitysafetyapp.web.vo.EventTypeVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.ResponsePageVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerRuleVo;
import com.eg.egsc.scp.devicemgmt.dto.DeviceDetailResponseDto;
import com.eg.egsc.scp.eventcomponent.client.TriggerClient;
import com.eg.egsc.scp.eventcomponent.dto.FilterDto;
import com.eg.egsc.scp.eventcomponent.dto.OrderDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDelDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDetailDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.PARAMS_VALID;
import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.REQUEST_EVENT_TIMEOUT;
import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.TRIGGER_DEL_FAIL;
import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.TRIGGER_GET_FAIL;

/**
 * @author xinghai
 * @since 2017/12/14
 */
@Service("triggerRuleServiceImpl")
public class TriggerRuleServiceImpl implements TriggerRuleService {

    @Resource
    TriggerClient triggerClientImpl;

    @Resource
    ResourceService resourceServiceImpl;

    @Resource
    ResourceTreeService resourceTreeServiceImpl;

    private static final String EVENT_TYPE = "eventType";

    private static Map<String, EventTypeCondition> deviceTypeMap = new HashMap();


    @Override
    /**
     * 新增联动规则
     * @param [triggerRuleDto]
     * @return void
     */
    public void addTriggerRule(TriggerRuleDto triggerRuleDto) throws SafetyException {
        TriggerDto triggerDto = new TriggerDto();
        if (!ValidationUtils.isUuidValid(triggerRuleDto.getEventSourceCode())) {
            throw new SafetyException(PARAMS_VALID);
        }
        if (!ValidationUtils.isEventTypeValid(triggerRuleDto.getEventType().toString())) {
            throw new SafetyException(PARAMS_VALID);
        }
        try {
            List<TriggersDto> triggerDtoList = triggerRuleDto.getTriggers();
            getTriggerDto(triggerRuleDto, triggerDto, triggerDtoList);
            triggerClientImpl.saveTrigger(triggerDto);
        } catch (Exception ex) {
            LoggerUtil.info(ExceptionStatusEnum.TRIGGER_ADD_FAIL.getReasonPhrase() + "->"
                    + ex.getMessage());
            throw new SafetyException(ex.getMessage());
        }
    }


    @Override
    /**
     * 修改联动规则
     * @param [triggerRuleDto]
     * @return void
     */
    public void updateTriggerRule(TriggerRuleDto triggerRuleDto) throws SafetyException {
        String uuId = triggerRuleDto.getTriggerRuleId();
        if (uuId == null || !ValidationUtils.isUuidValid(uuId)) {
            throw new SafetyException(PARAMS_VALID);
        }
        TriggerDto triggerDto = new TriggerDto();
        try {
            triggerDto.setUuid(uuId);
            List<TriggersDto> triggerDtoList = triggerRuleDto.getTriggers();
            getTriggerDto(triggerRuleDto, triggerDto, triggerDtoList);
            triggerClientImpl.updateTrigger(triggerDto);
        } catch (Exception ex) {
            LoggerUtil.info(ExceptionStatusEnum.TRIGGER_UPDATE_FAIL.getReasonPhrase() + "->"
                    + ex.getMessage());
            throw new SafetyException(ex.getMessage());
        }
    }

    @Override
    /**
     * 删除联动规则
     * @param [triggerRuleIds]
     * @return void
     */
    public void delTriggerRule(List<String> triggerRuleIds) throws SafetyException {
        TriggerDelDto epsTriggerDelDto = new TriggerDelDto();
        try {
            if (!SafetyUtils.isEmpty(triggerRuleIds)) {
                epsTriggerDelDto.setTriggerIdList(triggerRuleIds);
                triggerClientImpl.deleteTrigger(epsTriggerDelDto);
            } else {
                throw new SafetyException(ExceptionStatusEnum.PARAM_INVALID.getValue(),
                        ExceptionStatusEnum.PARAM_INVALID.getMessage());
            }
        } catch (Exception ex) {
            LoggerUtil.info(ExceptionStatusEnum.TRIGGER_DEL_FAIL.getReasonPhrase()
                    + "->" + ex.getMessage());
            throw new SafetyException(TRIGGER_DEL_FAIL);
        }
    }

    @Override
    /**
     * 根据id获取联动规则
     * @param [triggerRuleId]
     * @return com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerRuleVo
     */
    public TriggerRuleVo getTriggerRuleById(String triggerRuleId) throws SafetyException {
        TriggerDto triggerDto = new TriggerDto();
        try {
            triggerDto.setUuid(triggerRuleId);
            ResponseDto responseDto = triggerClientImpl.queryTriggerById(triggerDto);
            Map map = (LinkedHashMap) responseDto.getData();
            return getTriggerRuleVo(map);
        } catch (Exception ex) {
            LoggerUtil.info(ExceptionStatusEnum.REQUEST_TIMEOUT.getReasonPhrase()
                    + "->" + ex.getMessage());
            throw new SafetyException(REQUEST_EVENT_TIMEOUT);
        }
    }

    @Override
    /**
     * 联动规则分页查询
     * @param [condition]
     * @return com.eg.egsc.scp.communitysafetyapp.web.vo.ResponsePageVo
     */
    public ResponsePageVo getTriggerRulePage(TriggerRuleCondition condition) throws SafetyException {
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageNo(condition.getPageNo());
        pageQueryDto.setPageSize(condition.getPageSize());
        List<FilterDto> filters = getFilterDtoList(condition);
        pageQueryDto.setFilters(filters);
        pageQueryDto.setOrder(new OrderDto("updateTime", "desc"));
        int totalRows = 0;
        List<TriggerRuleVo> triggerRuleVoList = null;
        try {
            ResponseDto responseDto = triggerClientImpl.queryTriggerPage(pageQueryDto);
            LinkedHashMap linkedHashMap = (LinkedHashMap) responseDto.getData();
            if (linkedHashMap == null) {
                return new ResponsePageVo(totalRows, condition.getPageNo(), triggerRuleVoList);
            }
            PageInfo<TriggerDto> pageInfo = new JSONObject(linkedHashMap).toJavaObject(PageInfo.class);
            if (null != pageInfo) {
                totalRows = (int) pageInfo.getTotal();
                triggerRuleVoList = getTriggerRuleVoList(pageInfo.getRows());
            }
        } catch (Exception ex) {
            LoggerUtil.info(ExceptionStatusEnum.TRIGGER_GET_FAIL.getReasonPhrase()
                    + "->" + ex.getMessage());
            throw new SafetyException(TRIGGER_GET_FAIL);
        }

        return new ResponsePageVo(totalRows, condition.getPageNo(), triggerRuleVoList);
    }

    /**
     * 获取联动规则对象
     *
     * @param triggerRuleDto
     * @param triggerDto
     * @param triggerDtoList
     */
    private void getTriggerDto(TriggerRuleDto triggerRuleDto, TriggerDto triggerDto, List<TriggersDto> triggerDtoList) {
        List<TriggerDetailDto> triggerDetailDtoList = new ArrayList<>();
        for (TriggersDto trigger : triggerDtoList) {
            TriggerDetailDto triggerDetailDto = new TriggerDetailDto();
            triggerDetailDto.setTo(trigger.getTo());
            triggerDetailDto.setMethod(trigger.getMethod());
            triggerDetailDto.setParams(trigger.getParams());
            //当isSequential为false时为空
            if (triggerRuleDto.getIsSequential()) {
                triggerDetailDto.setOrder(trigger.getOrder());
            }
            triggerDetailDtoList.add(triggerDetailDto);
        }

        triggerDto.setEventSourceCode(triggerRuleDto.getEventSourceCode());
        triggerDto.setEventType(triggerRuleDto.getEventType());
        triggerDto.setIsSequential(triggerRuleDto.getIsSequential());
        triggerDto.setTriggers(triggerDetailDtoList);
        triggerDto.setCreator(triggerRuleDto.getCreator());
    }

    /**
     * 获取联动规则列表
     */
    private List<TriggerRuleVo> getTriggerRuleVoList(List list) {
        List<TriggerRuleVo> triggerRuleVoList = new ArrayList<>();

        if (SafetyUtils.isEmpty(list)) {
            return triggerRuleVoList;
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> mapObj = (LinkedHashMap) list.get(i);
            if (mapObj == null) continue;
            TriggerDto triggerDto = new JSONObject(mapObj).toJavaObject(TriggerDto.class);
            TriggerRuleVo triggerRuleVo = new TriggerRuleVo();
            BeanUtils.copyProperties(triggerRuleVo, triggerDto);
            triggerRuleVo.setIsSequential(triggerDto.getIsSequential());
            List<EventTypeVo> triggerEventTypeList = resourceServiceImpl.getEventTypeList(
                    triggerRuleVo.getEventType() + "");
            Map<Integer, String> eventTypeMap = SafetyUtils.getEventTypeMap(triggerEventTypeList);
            triggerRuleVo.setEventTypeName(eventTypeMap.get(triggerDto.getEventType()));
            triggerRuleVoList.add(triggerRuleVo);
        }

        return triggerRuleVoList;
    }

    /**
     * 获取联动规则详情
     */
    private TriggerRuleVo getTriggerRuleVo(Map map) {
        TriggerRuleVo triggerRuleVo = new TriggerRuleVo();
        if (null != map) {
            TriggerDto triggerDto = new JSONObject(map).toJavaObject(TriggerDto.class);
            BeanUtils.copyProperties(triggerRuleVo, triggerDto);
            triggerRuleVo.setIsSequential(triggerDto.getIsSequential());
            try {
                if (!SafetyUtils.isEmpty(triggerDto.getEventSourceCode())) {
                    DeviceDetailResponseDto dto = resourceTreeServiceImpl.queryDeviceDetail(triggerDto.getEventSourceCode());
                    // 获取设备名称
                    if (dto != null) triggerRuleVo.setDeviceName(dto.getDeviceName());
                }
            }catch (SafetyException e){
                LoggerUtil.info(e.getMessage());
            }

            List<EventTypeVo> triggerEventTypeList = resourceServiceImpl.getEventTypeList(
                    triggerRuleVo.getEventType() + "");
            Map<Integer, String> eventTypeMap = SafetyUtils.getEventTypeMap(triggerEventTypeList);
            triggerRuleVo.setEventTypeName(eventTypeMap.get(triggerDto.getEventType()));
            triggerRuleVo.setDeviceType(triggerDto.getDeviceTypeName());
        }

        return triggerRuleVo;
    }

    /**
     * 获取查询条件列表
     */
    private List<FilterDto> getFilterDtoList(TriggerRuleCondition condition) {
        List<FilterDto> filters = new ArrayList<>();
        if (null != condition.getEventType()) {
            filters.add(FilterUtils.getFilterDto(EVENT_TYPE, "and",
                    condition.getEventType().toString(), "eq"));
        } else {
            if (null != condition.getDeviceType()) {
                EventTypeCondition eventTypes = getEventTypes(condition);
                if (eventTypes != null && eventTypes.getEventTypeMin() >= 0) {
                    filters.add(FilterUtils.getFilterDto(EVENT_TYPE, "and",
                            eventTypes.getEventTypeMin() + "", "gte"));
                    filters.add(FilterUtils.getFilterDto(EVENT_TYPE, "and",
                            eventTypes.getEventTypeMax() + "", "lte"));
                }
            }
        }
        if (!SafetyUtils.isEmpty(condition.getEventSourceCode())) {
            filters.add(FilterUtils.getFilterDto("eventSourceCode", "and",
                    condition.getEventSourceCode(), "in"));
        }
        return filters;
    }

    /**
     * 拼接eventTpe
     */
    private EventTypeCondition getEventTypes(TriggerRuleCondition condition) {
        if (StringUtils.isNotBlank(condition.getDeviceType())) {
            String str = condition.getDeviceType();
            int deviceType = Integer.parseInt(str);
            return deviceTypeMap.get("type" + deviceType);
        }
        return null;
    }

    static {
        deviceTypeMap.put("type" + 2004, setMinAndMax(0, 19999));
        deviceTypeMap.put("type" + 2001, setMinAndMax(20000, 29999));
        deviceTypeMap.put("type" + 2009, setMinAndMax(30000, 39999));
        deviceTypeMap.put("type" + 2013, setMinAndMax(40000, 49999));
        deviceTypeMap.put("type" + 2016, setMinAndMax(50000, 59999));
        deviceTypeMap.put("type" + 2018, setMinAndMax(60000, 69999));
        deviceTypeMap.put("type" + 2017, setMinAndMax(70000, 79999));
        deviceTypeMap.put("type" + 2019, setMinAndMax(80000, 89999));
    }
    /**
     * 设置最大最小值
     *
     * @param min
     * @param max
     * @return
     */
    private static EventTypeCondition setMinAndMax(int min, int max) {
        return new EventTypeCondition(min, max);
    }
}
