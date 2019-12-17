package com.bloodsport.whisper.core.wrapper;

import com.bloodsport.whisper.core.exception.CustomException;
import com.bloodsport.whisper.model.ResultStateEnum;
import com.bloodsport.whisper.model.BaseResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       GlobalExceptionWrapper</p>
 * <p>@PackageName:     com.bloodsport.whisper.core.wrapper</p>
 * <b>
 * <p>@Description:     全局统一异常处理</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@ControllerAdvice
public class GlobalExceptionWrapper {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionWrapper.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultExceptionHandler(HttpServletRequest request, Exception ex, HttpServletResponse response){
        //系统级异常
        BaseResultDTO result = new BaseResultDTO(ResultStateEnum.FAILURE);
        if (ex instanceof CustomException){
            CustomException exception = (CustomException) ex;
            result.setCode(exception.getCode());
            result.setMessage(exception.getMessage());
        }
        logger.error("系统处理异常:" + ex.getMessage(), ex);
        return result;
    }
}
