package com.bloodsport.whisper.core.annotation;

import java.lang.annotation.*;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       Whisper</p>
 * <p>@PackageName:     com.bloodsport.whisper.core.annotation</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Inherited
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Whisper {

    boolean value() default true;
}