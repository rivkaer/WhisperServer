package com.bloodsport.whisper.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       ResultSkip</p>
 * <p>@PackageName:     com.bloodsport.whisper.core.annotation</p>
 * <b>
 * <p>@Description:     需要跳过全局统一异常处理的结果标注</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultSkip {

    boolean value() default true;

}
