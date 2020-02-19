package com.bloodsport.whisper.feature.usercenter;

import com.bloodsport.whisper.core.annotation.SecurityParameter;
import com.bloodsport.whisper.example.KeyValueDatabase;
import com.bloodsport.whisper.interf.TokenHelper;
import com.bloodsport.whisper.model.pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
    public UserDTO register(@RequestBody UserDTO user, HttpServletResponse response){
        String token = tokenHelper.create("1", user.getUsername(), 1000 * 60 * 60 * 24 * 7);
        UserDTO userDTO = new UserDTO();
        userDTO.setToken(token);
        userDTO.setUsername(user.getUsername());
        // 手动将token添加到response
        response.addHeader("token", token);
        // 将公钥插入到key-value数据库中
        keyValueDatabase.insert("1", user.getPublicKey());
        return userDTO;
    }
}
