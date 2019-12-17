package com.bloodsport.whisper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhisperApplicationTests {

    @Value("${encryption.publicKey}")
    private String publicKey;

    @Value("${encryption.privateKey}")
    private String privateKey;

    @Test
    void contextLoads() throws Exception {
    }
}
