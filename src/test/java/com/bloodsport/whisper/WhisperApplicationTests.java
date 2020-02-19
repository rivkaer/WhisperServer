package com.bloodsport.whisper;

import com.bloodsport.whisper.core.util.EncryptUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class WhisperApplicationTests {

    @Value("${encryption.publicKey}")
    private String publicKey;

    @Value("${encryption.privateKey}")
    private String privateKey;

    @Test
    void contextLoads() throws Exception {

        String requestBody = "{" +
                "\"username\": \"xiaokeai\"," +
                "\"password\": \"123456\"," +
                "\"publicKey\": \"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDuBYGZkLQMpbaZCHJFbLGa3KT8r6xbMF6jo6hyukWzpQcar8YuFns4NZmRH1XTOanIH8olPV7pBPbub/HzKRYYqJlaBIyjPpMMrCGZj+hrouhlX/h2K6kJWFU4SmTrdv7EDRUBUHEWSCtY+X/2DASS9kKsJbrAJ/hfd9wmIu034QIDAQAB\"" +
                "}";
        String responseBody = "dnTHk4JbdZw4SUIYljl8gW1rlsVWXILVZcwaOYyZM/9keANDQYQ2/p72j4znexqufLoiDT27SqLG6GJ/6ahmWsiau/tp2VdmEO3Dm9QIfwDXzmJn6rby51yBoISiTdjYZyGXW5H8DC4UkdqmwFk9WOAvER2UBxvGP/WWwugugky8lWtZRn2EprKi/rBjOKKH0vaFFxmjyNrMiCtR3xz8pU6ufq2yK/IJDeV9OJw1p2LzPox4DZPG8ptxKinuOUYKrendMxy/pA12PJL9qlLrMZ10aGLxh5+JhNBfh4SMoSjGFD5SfZWRa1P9wDsDV8Hk/X14Vj5nQ2J2CQZn/jNpPAbLpGZpqtt1tXjJd1UkM/Ymzt9NRVQ43EDQ961gZjMn";

        String aesKey = generalRandomKey(1 << 4);
        String encrypedBody = EncryptUtils.encryptForAES(requestBody, aesKey);
        aesKey = EncryptUtils.encryptForRSA(aesKey, publicKey);

        String decryptAesKey = EncryptUtils.decryptForRSA(aesKey, privateKey);
        String body = EncryptUtils.decryptForAES(encrypedBody, decryptAesKey);

        // 请求示例
        System.out.println(aesKey);
        System.out.println(encrypedBody + "\n");
        // 响应示例
        System.out.println(decryptAesKey);
        System.out.println(body);
    }

    /**
     * 随机生成指定位数的key
     * @param len 长度
     * @return 随机key字符串
     */
    private static String generalRandomKey(int len) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }
}
