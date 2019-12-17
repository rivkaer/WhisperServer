package com.bloodsport.whisper.feature.usercenter;

import com.bloodsport.whisper.core.annotation.SecurityParameter;
import com.bloodsport.whisper.example.KeyValueDatabase;
import com.bloodsport.whisper.interf.TokenHelper;
import com.bloodsport.whisper.model.UserTokenModel;
import com.bloodsport.whisper.model.pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       UserCenterController</p>
 * <p>@PackageName:     com.bloodsport.whisper.feature.usercenter</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/17</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@RestController
@RequestMapping(value = "/userCenter")
public class UserCenterController {

    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private KeyValueDatabase keyValueDatabase;

    @SecurityParameter
    @PostMapping(value = "/register")
    public UserDTO register(@RequestBody UserDTO user){
        String token = tokenHelper.create("1", user.getUsername(), 1000 * 60 * 60 * 24 * 7);
        UserDTO userDTO = new UserDTO();
        userDTO.setToken(token);
        userDTO.setUsername(user.getUsername());
        keyValueDatabase.insert("1", user.getPublicKey());
        return userDTO;
    }

    @SecurityParameter
    @PostMapping(value = "/login")
    public UserDTO login(@RequestBody UserDTO user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
