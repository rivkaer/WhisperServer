package com.bloodsport.whisper.core.wrapper;

import com.bloodsport.whisper.core.annotation.ResultSkip;
import com.bloodsport.whisper.model.BaseResultDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       GlobalApiResultWrapper</p>
 * <p>@PackageName:     com.bloodsport.whisper.core.wrapper</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@RestControllerAdvice
@Order(value = 1)
public class GlobalApiResultWrapper implements ResponseBodyAdvice<Object> {

    /**
     * 不需要拦截的类路径，这里写的是Class
     * 如果该类所在项目没有相关的依赖，可以换成String-类的全路径
     */
    private static final List<Class<?>> SKIP_CLASS_LIST = new ArrayList<>(2);

    @Override
    public boolean supports(MethodParameter returnModelType, Class<? extends HttpMessageConverter<?>> converter) {
        if (SKIP_CLASS_LIST.contains(returnModelType.getDeclaringClass())) {
            return false;
        }
        Method returnTypeMethod = returnModelType.getMethod();
        if (returnTypeMethod != null) {
            boolean isSkip = !returnTypeMethod.isAnnotationPresent(ResultSkip.class);
            return isSkip;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (MediaType.IMAGE_JPEG.getType().equalsIgnoreCase(mediaType.getType())) {
            return body;
        }
        if (body instanceof BaseResultDTO) {
            return body;
        }
        return new BaseResultDTO<>(body);
    }
}
