/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.service.EventLogService;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceService;
import com.eg.egsc.scp.communitysafetyapp.util.*;
import com.eg.egsc.scp.communitysafetyapp.web.dto.EventLogReq;
import com.eg.egsc.scp.communitysafetyapp.web.vo.EventLogVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.EventTypeVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.ExtendVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.ResponsePageVo;
import com.eg.egsc.scp.eventcomponent.client.EventLogClient;
import com.eg.egsc.scp.eventcomponent.client.TriggerLogClient;
import com.eg.egsc.scp.eventcomponent.dto.CommonECDto;
import com.eg.egsc.scp.eventcomponent.dto.FilterDto;
import com.eg.egsc.scp.eventcomponent.dto.OrderDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.*;
import static com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant.EVENT_TYPE_FACE_CAP_BEHAVIOR0;
import static com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant.EVENT_TYPE_FACE_CAP_BEHAVIOR1;
import static com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant.EVENT_TYPE_FACE_CAP_VIDEO;
import static com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant.ORDER_DESC;
import static com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant.START_TIME;
import static com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant.TOTAL;

/**
 * 事件日志
 *
 * @author xinghai
 * @since 2017/12/8
 */
@Service("eventLogServiceImpl")
public class EventLogServiceImpl implements EventLogService {

    @Resource
    private EventLogClient eventLogClientImpl;

    @Resource
    ResourceService resourceServiceImpl;

    @Resource
    TriggerLogClient triggerLogClientImpl;

    @Value("${egsc.config.urlpath}")
    private String urlPath;

    /**
     * 抓拍事件上报类型编码
     */
    private static final int[] eventTypeArr = {
            EVENT_TYPE_FACE_CAP_VIDEO,
            EVENT_TYPE_FACE_CAP_BEHAVIOR0,
            EVENT_TYPE_FACE_CAP_BEHAVIOR1
    };

    private static final String DATETIME_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE_CHINA = "GMT+8";
    private static final String AND = "and";

    /**
     * 获得事件日志列表
     *
     * @return responsePageVo
     */
    @Override
    public ResponsePageVo getEventLogList(EventLogReq eventLogReq) throws SafetyException {
        List resultList = new ArrayList();
        int totalRows = 0;
        if (validationTime(eventLogReq)) {
            LoggerUtil.info("开始时间不能大于结束时间");
            throw new SafetyException(STARTTIME_ENTTIME_ILLEGAL);
        } else if (StringUtils.isNotBlank(eventLogReq.getEventType()) &&
                !ValidationUtils.isPositiveInteger(eventLogReq.getEventType())) {
            LoggerUtil.info("事件类型必须是正整数");
            throw new SafetyException(EVENT_TYPE_ILLEGAL);
        }

        if (!ValidationUtils.isPositiveInteger(eventLogReq.getPageNo().toString())) {
            LoggerUtil.info("页码必须是正整数");
            throw new SafetyException(COMMON_PAGE_NO_ILLEGAL);
        }

        try {
            PageQueryDto queryEventDto = new PageQueryDto();
            queryEventDto.setPageNo(eventLogReq.getPageNo());
            queryEventDto.setPageSize(eventLogReq.getPageSize());
            List<FilterDto> filters = this.getFilterDto(eventLogReq);
            queryEventDto.setFilters(filters);

            queryEventDto.setOrder(new OrderDto(START_TIME, ORDER_DESC));

            ResponseDto responseDto = eventLogClientImpl.queryEventLog(queryEventDto);
            Object obj = responseDto.getData();
            if (obj != null) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) obj;
                JSONObject json = new JSONObject(linkedHashMap);
                resultList = getExtend(resultList, json);
                if (json.get(TOTAL) != null) {
                    totalRows = (int) json.get(TOTAL);
                }
            }
        } catch (Exception ex) {
            LoggerUtil.info("调用事件组件报错:{}", ex);
            throw new SafetyException(EVENT_LOG_GET_FAIL);
        }
        LoggerUtil.info("调用事件组件成功:{}", "success");
        return new ResponsePageVo(totalRows, eventLogReq.getPageNo(), resultList);
    }

    /**
     * 获得集合
     */
    private List getExtend(List resultList, JSONObject json) {
        List list = (List) json.get(SafetyConstant.ROWS);
        if (SafetyUtils.isEmpty(list)) return resultList;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> mapObj = (LinkedHashMap) list.get(i);
            if (mapObj == null) continue;
            EventLogVo eventLogVo = new JSONObject(mapObj).toJavaObject(EventLogVo.class);
            if (null != eventLogVo.getExtend() && ArrayUtils.contains(eventTypeArr, eventLogVo.getEventType())) {
                ExtendVo extendVo = getExtendValue((HashMap) eventLogVo.getExtend());
                eventLogVo.setExtend(extendVo);
            } else {
                eventLogVo.setExtend(null);
            }
            List<EventTypeVo> eventTypeVoList = resourceServiceImpl.getEventTypeList(""
                    + eventLogVo.getEventType());
            Map<Integer, String> eventTypeMap = SafetyUtils.getEventTypeMap(eventTypeVoList);
            //获取事件类型
            String eventTypeName = eventTypeMap.get(eventLogVo.getEventType());
            eventLogVo.setEventTypeName(eventTypeName);
            resultList.add(eventLogVo);
        }
        return resultList;
    }


    /**
     * 拼接URL
     *
     * @return extendVo
     */
    private ExtendVo getExtendValue(HashMap<String, Object> map) {
        ExtendVo extendVo = new ExtendVo();
        if (map == null) {
            return extendVo;
        }
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String str = entry.getKey();
            //视频上传人脸抓拍
            if ("FaceDetectInfo".equals(str)) {
                Object faceDetectInfo = map.get(str);
                JSONObject jsonObject = new JSONObject((LinkedHashMap) faceDetectInfo);
                if (jsonObject.get("FacePicUrl") == null) continue;
                String facePicUrl = jsonObject.get("FacePicUrl").toString();
                extendVo.setImgUrl(urlPath + facePicUrl);
                //分析API上传人脸抓拍
            } else if ("Images".equals(str)) {
                Object images = map.get(str);
                List list = (ArrayList) images;
                for (int i = 0; i < list.size(); i++) {
                    JSONObject jsonObject = new JSONObject((LinkedHashMap) list.get(i));
                    if (jsonObject.get("FaceImageURI") == null) continue;
                    String faceImageURI = jsonObject.get("FaceImageURI").toString();
                    extendVo.setImgUrl(urlPath + faceImageURI);
                }
            }
        }
        return extendVo;
    }

    /**
     * 添加条件
     *
     * @return List
     */
    private List<FilterDto> getFilterDto(EventLogReq eventLogReq) {
        List<FilterDto> filters = new ArrayList<>();
        if (StringUtils.isNotBlank(eventLogReq.getEventType())) {
            FilterDto filterDto = FilterUtils.getFilterDto("eventType", AND,
                    eventLogReq.getEventType(), "eq");
            filters.add(filterDto);
        }
        if (StringUtils.isNotBlank(eventLogReq.getStatus())) {
            FilterDto filterDto = FilterUtils.getFilterDto("status", AND,
                    eventLogReq.getStatus(), "eq");
            filters.add(filterDto);
        }
        if (eventLogReq.getStartTime() != null) {
            FilterDto filterDto = FilterUtils.getFilterDto("startTime", AND,
                    DateFormatUtils.format(eventLogReq.getStartTime(), DATETIME_FORMAT_LONG,
                            TimeZone.getTimeZone(TIME_ZONE_CHINA)), "gte");
            filters.add(filterDto);
        }
        if (eventLogReq.getEndTime() != null) {
            FilterDto filterDto = FilterUtils.getFilterDto("endTime", AND,
                    DateFormatUtils.format(eventLogReq.getEndTime(), DATETIME_FORMAT_LONG,
                            TimeZone.getTimeZone(TIME_ZONE_CHINA)), "lte");
            filters.add(filterDto);
        }
        return filters;
    }

    /**
     * @return String
     */
    @Override
    public String getTriggerResultsById(String id) throws SafetyException {
        String msg = null;
        if (!ValidationUtils.isEventLogIdValid(id)) {
            LoggerUtil.info("传入的事件日志id不合法");
            throw new SafetyException(EVENT_LOG_ID_ILLEGAL);
        }

        try {
            CommonECDto commonECDto = new CommonECDto();
            commonECDto.getMap().put("eventIds", id);
            ResponseDto responseDto = triggerLogClientImpl.getTriggerLogByEventIds(commonECDto);
            Object obj = responseDto.getData();
            if (obj != null) {
                List list = (List) obj;
                if (!SafetyUtils.isEmpty(list)) {
                    LinkedHashMap linkedHashMap = (LinkedHashMap) list.get(0);
                    JSONObject jsonObject = new JSONObject(linkedHashMap);
                    Map<String, String> map = (LinkedHashMap) jsonObject.get("triggerResult");
                    msg = map.get("message");
                }
            }
        } catch (Exception ex) {
            LoggerUtil.info(ex.getMessage());
            throw new SafetyException(TRIGGER_RESULT_GET_FAIL);
        }
        return msg;
    }

    /**
     * 校验开始时间不能大于结束时间
     */
    private boolean validationTime(EventLogReq eventLogReq) {
        if ((eventLogReq.getStartTime()) == null || (eventLogReq.getEndTime() == null)) {
            return false;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT_LONG);
            String startTime = DateFormatUtils.format(eventLogReq.getStartTime(),
                    DATETIME_FORMAT_LONG, TimeZone.getTimeZone(TIME_ZONE_CHINA));
            String endTime = DateFormatUtils.format(eventLogReq.getEndTime(),
                    DATETIME_FORMAT_LONG, TimeZone.getTimeZone(TIME_ZONE_CHINA));
            Date startDate = dateFormat.parse(startTime);
            Date endDate = dateFormat.parse(endTime);
            if (startDate.getTime() > endDate.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            LoggerUtil.info(e.getMessage());
            LoggerUtil.info("传入的时间不对");
        }
        return false;
    }

}
