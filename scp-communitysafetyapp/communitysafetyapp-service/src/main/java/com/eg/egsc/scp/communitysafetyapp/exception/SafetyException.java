/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.exception;

import com.eg.egsc.common.exception.CommonException;

/**
 * 小区安全自定义异常类
 *
 * @author xinghai
 * @since 2017/12/11
 */
public class SafetyException extends CommonException {

    private static final long serialVersionUID = -4033102052233433580L;

    public SafetyException(String message) {
        super(message);
    }

    /**
     * @param code
     * @param message
     */
    public SafetyException(String code, String message) {
        super(code, message);
    }
}
