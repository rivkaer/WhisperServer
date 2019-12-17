package com.bloodsport.whisper.model.pojo;

import lombok.Data;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       UserDTO</p>
 * <p>@PackageName:     com.bloodsport.whisper.model.pojo</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/17</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String token;
    private String publicKey;
}
