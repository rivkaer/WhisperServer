package com.bloodsport.whisper.core.support;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.function.Predicate;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       GsonSupportHandler</p>
 * <p>@PackageName:     com.bloodsport.whisper.core.support</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Configuration
@EnableWebMvc
public class GsonSupportHandler extends WebMvcConfigurationSupport {

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 移除Jackson转换器
        converters.removeIf(new Predicate<HttpMessageConverter<?>>() {
            @Override
            public boolean test(HttpMessageConverter<?> httpMessageConverter) {
                return httpMessageConverter instanceof MappingJackson2HttpMessageConverter;
            }
        });
        // 在对数据加密时会进行序列化后进行加密
        // 增加gson转换器
        converters.add(new GsonHttpMessageConverter());
        super.extendMessageConverters(converters);
    }
}
