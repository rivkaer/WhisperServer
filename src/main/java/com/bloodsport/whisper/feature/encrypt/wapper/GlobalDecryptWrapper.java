package com.bloodsport.whisper.feature.encrypt.wapper;

import com.bloodsport.whisper.core.annotation.SecurityParameter;
import com.bloodsport.whisper.core.exception.CustomException;
import com.bloodsport.whisper.core.util.EncryptUtils;
import com.bloodsport.whisper.model.ResultStateEnum;
import okio.BufferedSource;
import okio.Okio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       GlobalEncrytWrapper</p>
 * <p>@PackageName:     com.bloodsport.whisper.feature.encrypt.wapper</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@RestControllerAdvice
@Order(value = -2147483647)
public class GlobalDecryptWrapper implements RequestBodyAdvice {

    @Value("${encryption.privateKey}")
    String privateKey;

    @Override
    public boolean supports(MethodParameter returnType, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        boolean encrypt = false;
        Method method = returnType.getMethod();
        if (method != null && method.isAnnotationPresent(SecurityParameter.class)){
            boolean methodPresent = returnType.getMethod().isAnnotationPresent(SecurityParameter.class);
            if(methodPresent){
                //方法上标注的是否需要加密
                encrypt = returnType.getMethod().getAnnotation(SecurityParameter.class).inDecode();
            }
        }
        return encrypt;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        List<String> publicKeyEncryptedAESKey = httpInputMessage.getHeaders().getOrEmpty("encrypt-key");
        String aesKey = null;
        if (!publicKeyEncryptedAESKey.isEmpty() && !StringUtils.isEmpty((aesKey = publicKeyEncryptedAESKey.get(0)))){
            try {
                aesKey = EncryptUtils.decryptForRSA(aesKey, privateKey);
                BufferedSource buffer = Okio.buffer(Okio.source(httpInputMessage.getBody()));
                String body = buffer.readUtf8();
                String newBody = EncryptUtils.decryptForAES(body, aesKey);
                return new HttpInputMessage() {
                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream(newBody.getBytes("UTF-8"));
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        return httpInputMessage.getHeaders();
                    }
                };
            }catch (Exception ex){
                ex.printStackTrace();
                throw new CustomException(ResultStateEnum.DECRYPT_REQUEST_FAILURE);
            }
        }else {
            throw new CustomException(ResultStateEnum.NOT_ENCRYPT_REQUEST);
        }
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }
}
