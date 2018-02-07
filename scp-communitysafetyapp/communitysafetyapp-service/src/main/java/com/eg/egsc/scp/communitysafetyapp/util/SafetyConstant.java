/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

/**
 * @author xinghai
 * @since 2017/12/27
 */
public class SafetyConstant {

    private SafetyConstant() {
    }

    public static final Integer EVENT_TYPE_FACE_CAP_VIDEO = 20113;//人脸抓拍

    public static final Integer EVENT_TYPE_FACE_CAP_BEHAVIOR0 = 25010;//人脸抓拍(API分析1)

    public static final Integer EVENT_TYPE_FACE_CAP_BEHAVIOR1 = 25050;//人脸抓拍(API分析2)

    public static final String TOTAL = "total";

    public static final String ROWS = "rows";

    public static final String START_TIME = "startTime";

    public static final String ORDER_DESC = "desc";

    //缓存过期时间
    public static final Integer REDIS_EXPIRE_TIME = 60;
}
