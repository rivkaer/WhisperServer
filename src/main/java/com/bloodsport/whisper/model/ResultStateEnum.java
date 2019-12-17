package com.bloodsport.whisper.model;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       ResultStateEnum</p>
 * <p>@PackageName:     com.bloodsport.whisper.model</p>
 * <b>
 * <p>@Description:     结果状态枚举</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
public enum ResultStateEnum {
    FAILURE(500, "服务异常,请联系管理员"),
    SUCCESSFUL(200, "successful"),
    LOGIN_USER_AUTH_FAILURE(403, "请重新登陆"),
    LOGIN_USER_NOT_FOUND(501, "找不到用户"),
    LOGIN_INFO_MISMATCH(502, "用户名密码不匹配");

    private int code;
    private String message;

    ResultStateEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultStateEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
