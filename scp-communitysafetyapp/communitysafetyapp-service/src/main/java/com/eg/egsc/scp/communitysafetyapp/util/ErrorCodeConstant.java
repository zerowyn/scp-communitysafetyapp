/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

/**
 * 错误码常量类
 *
 * @author xinghai
 * @since 2017/12/11
 */
public class ErrorCodeConstant {
    private ErrorCodeConstant() {
    }

    public static final String DEVICE_GET_FAIL = "communitysafetyapp.device.get.fail";
    public static final String PARAMS_VALID = "communitysafetyapp.common.params.valid";
    public static final String REQUEST_EVENT_TIMEOUT = "communitysafetyapp.event.request.timeOut";
    public static final String REQUEST_MDM_TIMEOUT = "communitysafetyapp.mdm.request.timeOut";
    public static final String REQUEST_DEVICE_TIMEOUT = "communitysafetyapp.device.request.timeOut";
    public static final String TRIGGER_GET_FAIL = "communitysafetyapp.trigger.get.fail";
    public static final String TRIGGER_DEL_FAIL = "communitysafetyapp.trigger.delete.fail";
    public static final String EVENT_LOG_GET_FAIL = "communitysafetyapp.eventLog.get.fail";
    public static final String TRIGGER_RESULT_GET_FAIL = "communitysafetyapp.trigger.result.get.fail";
    public static final String TRIGGER_TYPE_FAIL = "communitysafetyapp.trigger.type.fail";
    public static final String TRIGGER_DEVICECODE_ILLEGAL = "communitysafetyapp.trigger.params.deviceCode.illegal";
    public static final String STARTTIME_ENTTIME_ILLEGAL = "communitysafetyapp.eventLog.get.time.illegal";
    public static final String EVENT_TYPE_ILLEGAL = "communitysafetyapp.eventLog.get.eventType.illegal";
    public static final String EVENT_LOG_ID_ILLEGAL = "communitysafetyapp.eventLog.get.logId.illegal";
    public static final String COMMON_PAGE_SIZE_ILLEGAL = "communitysafetyapp.common.params.pageSize.illegal";
    public static final String COMMON_PAGE_NO_ILLEGAL = "communitysafetyapp.common.params.pageNo.illegal";
}

