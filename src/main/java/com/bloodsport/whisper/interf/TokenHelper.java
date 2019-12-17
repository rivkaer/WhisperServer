package com.bloodsport.whisper.interf;

import com.bloodsport.whisper.model.UserTokenModel;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       TokenHelper</p>
 * <p>@PackageName:     com.bloodsport.whisper.helper</p>
 * <b>
 * <p>@Description:     token助手</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/17</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
public interface TokenHelper {

    /**
     * 创建token
     * @param userId 用户id
     * @param sub 校验主题
     * @param timestamp 超时时间
     * @return token
     */
    String create(String userId, String sub, long timestamp);

    /**
     * 校验token是否有效
     * @param token token字符串
     * @return 校验结果
     */
    boolean validate(String token);

    /**
     * 校验用户数据
     * @param token token字符串
     * @return token中包含的用户信息
     */
    UserTokenModel analyse(String token);
}