package com.eg.egsc.scp.communitysafetyapp.web.result;

/**
 * 返回结果实体类
 *
 * @author zpc
 * @since 2018/1/12
 */
public class ApiResult<T> {
    private Integer status;
    private boolean success;
    private T data;
    private String message;


    public ApiResult(Integer status, boolean success, T data, String message) {
        this.status = status;
        this.success = success;
        this.data = data;
        this.message = message;
    }

    /**
     * 成功的返回
     *
     * @param data 数据
     * @return 正常返回体
     */
    public static ApiResult successFul(Object data) {
        return new ApiResult(200, true, data, null);
    }

    /**
     * 错误的返回
     *
     * @param errorCode 错误码
     * @param message   错误信息
     * @return 错误返回体
     */
    public static ApiResult error(Integer errorCode, String message) {
        return new ApiResult(errorCode, false, null, message);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
