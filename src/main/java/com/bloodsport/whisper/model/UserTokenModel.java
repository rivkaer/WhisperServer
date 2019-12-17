package com.bloodsport.whisper.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       UserTokenModel</p>
 * <p>@PackageName:     com.bloodsport.whisper.model</p>
 * <b>
 * <p>@Description:     用户token信息模型</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/17</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Data
public class UserTokenModel implements Serializable {
    private String userId;
    private Date Expiration;

    public UserTokenModel() {
    }

    public UserTokenModel(String userId, Date expiration) {
        this.userId = userId;
        Expiration = expiration;
    }
}
