package com.bloodsport.whisper.core.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       Base64Utils</p>
 * <p>@PackageName:     com.bloodsport.whisper.core.util</p>
 * <b>
 * <p>@Description:     编码工具</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
public class EncodeUtils {

    /**
     * Base64编码
     * @param bytes 待编码数据
     * @return 编码后的数据
     */
    public static byte[] encodeBase64(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }

    /**
     * Base64解码
     * @param bytes 待解码数据
     * @return 解码后的数据
     */
    public static byte[] decodeBase64(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }
}
