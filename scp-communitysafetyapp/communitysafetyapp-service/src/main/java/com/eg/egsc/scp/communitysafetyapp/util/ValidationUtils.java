/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数有效性校验
 * @author xinghai
 * @since 2018/1/29
 */
public class ValidationUtils {
    private ValidationUtils() {
    }

    /**
     * 正则表达
     * @param regex
     * @param original
     * @return
     */
    private static boolean isMatch(String regex, String original){
        if (original == null || original.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(original);
        return isNum.matches();
    }

    /**
     * 判断uuid有效
     * @param param
     * @return
     */
    public static boolean isUuidValid(String param) {
        return isMatch("[0-9a-zA-Z]+", param);
    }

    /**
     * 判断事件类型有效
     * @param param
     * @return
     */
    public static boolean isEventLogIdValid(String param) {
        return isMatch("[0-9a-zA-Z]+", param);
    }

    /**
     * 校验设备编码有效性
     * @param param
     * @return
     */
    public static boolean isDeviceCodeValid(String param) {
        return isMatch("20[0-9]+", param);
    }

    /**
     * 校验事件类型编码有效性
     * @param param
     * @return
     */
    public static boolean isEventTypeValid(String param) {
        return isMatch("^[1-9]\\d*$", param);
    }

    /**
     * 正整数
     * @param original
     * @return
     */
    public static boolean isPositiveInteger(String original) {
        return isMatch("^\\+{0,1}[1-9]\\d*", original);
    }

    /**
     * 小数
     * @param original
     * @return
     */
    public static boolean isDecimal(String original){
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", original);
    }

}