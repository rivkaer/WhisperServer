package com.bloodsport.whisper.feature.encrypt;

import com.bloodsport.whisper.core.annotation.SecurityParameter;
import com.bloodsport.whisper.model.pojo.PublicKeyDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       EncryptionController</p>
 * <p>@PackageName:     com.bloodsport.whisper.feature.encrypt</p>
 * <b>
 * <p>@Description:     加密解密功能控制器</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@RestController
@RequestMapping(value = "/encrypt")
public class EncryptionController {

    @Value("${encryption.publicKey}")
    String publicKey;

    @SecurityParameter
    @GetMapping(value = "/fetchPublicKey")
    public PublicKeyDTO fetchPublicKey(){
        return new PublicKeyDTO(publicKey.replaceAll("\\\\", ""));
    }
}
