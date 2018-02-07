/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 日志打印工具类
 *
 * @Create In Dec 13, 2017 By zpc
 */
public class LoggerUtil {
    private LoggerUtil() {
    }

    private static final Integer MESSAGE_MAX_LENGTH = 512;

    /**
     * 日志信息打印
     */
    public static void info(String message) {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        StringBuilder logInfo = new StringBuilder(MESSAGE_MAX_LENGTH);
        logInfo.append(packInfoMessage(methodName, lineNumber));
        logInfo.append(message);
        try {
            org.apache.log4j.Logger logger = LogManager.getLogger(Class.forName(className).getName());
            logger.info(logInfo.toString());
        } catch (ClassNotFoundException ex) {
            throw new SafetyException(ex.getMessage());
        }
    }

    /**
     * 日志信息打印
     */
    public static void info(String message, Object... vars) {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        StringBuilder logInfo = new StringBuilder(MESSAGE_MAX_LENGTH);
        logInfo.append(packInfoMessage(methodName, lineNumber));
        try {
            if (null != vars) {
                for (Object var : vars) {
                    String[] strArr = message.split("\\{\\}", 2);
                    if (null == var) {
                        var = "null";
                    }
                    message = strArr[0] + String.valueOf(var) + strArr[1];
                }
            }
            logInfo.append(message);
            Logger logger = LogManager.getLogger(Class.forName(className).getName());
            logger.info(logInfo.toString());
        } catch (ClassNotFoundException ex) {
            throw new SafetyException(ex.getMessage());
        }
    }

    private static String packInfoMessage(String methodName, int lineNumber) {
        StringBuilder logInfo = new StringBuilder(MESSAGE_MAX_LENGTH);
        logInfo.append("[");
        logInfo.append(methodName);
        logInfo.append("]");
        logInfo.append("[");
        logInfo.append(lineNumber);
        logInfo.append("]");
        logInfo.append(" INFO:");
        return logInfo.toString();
    }
}
