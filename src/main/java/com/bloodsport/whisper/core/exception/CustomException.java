package com.bloodsport.whisper.core.exception;

import com.bloodsport.whisper.model.ResultStateEnum;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       CustomException</p>
 * <p>@PackageName:     com.bloodsport.whisper.core.exception</p>
 * <b>
 * <p>@Description:     自定义异常，后续衍生异常均要继承该异常</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
public class CustomException extends RuntimeException {

    private static ResultStateEnum DEFAULT_EXCEPTION_INFO = ResultStateEnum.FAILURE;
    // 异常code
    private int code;

    public CustomException() {
        this(DEFAULT_EXCEPTION_INFO.getCode(), DEFAULT_EXCEPTION_INFO.getMessage());
    }

    public CustomException(ResultStateEnum stateEnum) {
        this(stateEnum.getCode(), stateEnum.getMessage());
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
