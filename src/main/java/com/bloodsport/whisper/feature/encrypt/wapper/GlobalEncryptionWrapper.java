package com.bloodsport.whisper.feature.encrypt.wapper;

import com.bloodsport.whisper.core.annotation.SecurityParameter;
import com.bloodsport.whisper.core.exception.CustomException;
import com.bloodsport.whisper.core.util.EncryptUtils;
import com.bloodsport.whisper.example.KeyValueDatabase;
import com.bloodsport.whisper.helper.JsonSerializer;
import com.bloodsport.whisper.interf.TokenHelper;
import com.bloodsport.whisper.model.ResultStateEnum;
import com.bloodsport.whisper.model.UserTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       GlobalEncrytWrapper</p>
 * <p>@PackageName:     com.bloodsport.whisper.feature.encrypt.wapper</p>
 * <b>
 * <p>@Description:     对相应体进行加密</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@RestControllerAdvice
@Order(value = 2)
public class GlobalEncryptionWrapper implements ResponseBodyAdvice<Object> {

    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private KeyValueDatabase keyValueDatabase;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        boolean encrypt = false;
        Method method = returnType.getMethod();
        if (method != null && method.isAnnotationPresent(SecurityParameter.class)) {
            boolean methodPresent = returnType.getMethod().isAnnotationPresent(SecurityParameter.class);
            if (methodPresent) {
                //方法上标注的是否需要加密
                encrypt = returnType.getMethod().getAnnotation(SecurityParameter.class).outEncode();
            }
        }
        return encrypt;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpMethod method = serverHttpRequest.getMethod();
        String body = JsonSerializer.getSerializer().toJson(obj);
        if (method == HttpMethod.GET) {
            return body;
        }
        List<String> listResponseToken = serverHttpResponse.getHeaders().getOrEmpty("token");
        List<String> listRequestToken = serverHttpRequest.getHeaders().getOrEmpty("token");
        String token = null;
        if ((listRequestToken.isEmpty() && listResponseToken.isEmpty())
                || (StringUtils.isEmpty((token = (listRequestToken.isEmpty() ? "" : listRequestToken.get(0)))) && StringUtils.isEmpty((token = (listResponseToken.isEmpty()? "": listResponseToken.get(0))))
                || !tokenHelper.validate(token))) {
            serverHttpResponse.getHeaders().add("blend", String.valueOf(0));
        }
        try {
            String key = generalRandomKey(1 << 4);
            body = encryptResponseMessage(body, key);
            if (!StringUtils.isEmpty(token)) {
                UserTokenModel userTokenModel = tokenHelper.analyse(token);
                String publicKey = keyValueDatabase.find(userTokenModel.getUserId());
                key = EncryptUtils.encryptForRSA(key, publicKey);
            }
            serverHttpResponse.getHeaders().add("encrypt-key", key);
            return body;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CustomException(ResultStateEnum.ENCRYPT_RESPONSE_FAILURE);
        }
    }

    /**
     * 对信息进行加密
     *
     * @param message 消息加密
     */
    private String encryptResponseMessage(String message, String aesKey) throws Exception {
        return EncryptUtils.encryptForAES(message, aesKey);
    }

    /**
     * 随机生成指定位数的key
     *
     * @param len 长度
     * @return 随机key字符串
     */
    private static String generalRandomKey(int len) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }
}
