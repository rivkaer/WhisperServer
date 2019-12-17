package com.bloodsport.whisper.model;

import com.bloodsport.whisper.model.ResultStateEnum;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       BaseResultDTO</p>
 * <p>@PackageName:     com.bloodsport.whisper.model.pojo</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Data
public class BaseResultDTO<T> {
    @SerializedName(value = "code")
    private int code;
    @SerializedName(value = "msg")
    private String message;
    @SerializedName(value = "data")
    private T data;

    public BaseResultDTO() {
    }

    public BaseResultDTO(T data) {
        this(ResultStateEnum.SUCCESSFUL.getCode(), ResultStateEnum.SUCCESSFUL.getMessage(), data);
    }

    public BaseResultDTO(ResultStateEnum result) {
        this(result.getCode(), result.getMessage());
    }

    public BaseResultDTO(int code, String message) {
        this(code, message, null);
    }

    public BaseResultDTO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
