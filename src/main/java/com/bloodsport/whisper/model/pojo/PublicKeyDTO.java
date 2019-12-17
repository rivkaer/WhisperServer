package com.bloodsport.whisper.model.pojo;

import lombok.Data;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       PublicKeyDTO</p>
 * <p>@PackageName:     com.bloodsport.whisper.model.pojo</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Data
public class PublicKeyDTO {
    private String publicKey;

    public PublicKeyDTO() {
    }

    public PublicKeyDTO(String publicKey) {
        this.publicKey = publicKey;
    }
}
