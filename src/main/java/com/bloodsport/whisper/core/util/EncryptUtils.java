package com.bloodsport.whisper.core.util;

import org.bouncycastle.util.encoders.Base64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       EncryptUtils</p>
 * <p>@PackageName:     com.bloodsport.whisper.core.util</p>
 * <b>
 * <p>@Description:     加解密工具类</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/16</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
public class EncryptUtils {

    private static final String DEFAULT_CHARSET_NAME = "UTF-8";

    /**
     * RSA 公钥加密
     *
     * @param str       待加密数据
     * @param publicKey 公钥
     * @return 加密后密文
     * @throws Exception 加密过程中的异常
     */
    public static String encryptForRSA(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = EncodeUtils.decodeBase64(publicKey.getBytes(DEFAULT_CHARSET_NAME));
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] outStr = EncodeUtils.encodeBase64(cipher.doFinal(str.getBytes(DEFAULT_CHARSET_NAME)));
        return new String(outStr, 0, outStr.length);
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密过的密文
     * @param privateKey 私钥
     * @return 解密后的明文
     * @throws Exception 解密过程中的异常
     */
    public static String decryptForRSA(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = EncodeUtils.decodeBase64(str.getBytes(DEFAULT_CHARSET_NAME));
        //base64编码的私钥
        byte[] decoded = EncodeUtils.decodeBase64(privateKey.getBytes(DEFAULT_CHARSET_NAME));
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        byte[] bytes = cipher.doFinal(inputByte);
        return new String(bytes, 0, bytes.length);
    }


    /**
     * AES加密
     *
     * @param str 明文
     * @param key 密钥
     * @return 密文
     */
    public static String encryptForAES(String str, String key) throws Exception {
        byte[] raw = key.getBytes(DEFAULT_CHARSET_NAME);
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        //"算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(str.getBytes(DEFAULT_CHARSET_NAME));
        byte[] bytes = EncodeUtils.encodeBase64(encrypted);
        return new String(bytes, 0, bytes.length);
    }

    /**
     * AES解密
     *
     * @param str 密文
     * @param key 密钥
     * @return 明文
     */
    public static String decryptForAES(String str, String key) throws Exception {
        byte[] raw = key.getBytes(DEFAULT_CHARSET_NAME);
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] encrypted1 = EncodeUtils.decodeBase64(str.getBytes(DEFAULT_CHARSET_NAME));//先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, DEFAULT_CHARSET_NAME);
    }
}
