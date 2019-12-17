package com.bloodsport.whisper.helper;

import com.bloodsport.whisper.interf.TokenHelper;
import com.bloodsport.whisper.model.UserTokenModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       JwtTokenHelper</p>
 * <p>@PackageName:     com.bloodsport.whisper.helper</p>
 * <b>
 * <p>@Description:     Class Description</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/17</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Service
@Primary
public class JwtTokenHelper implements TokenHelper {

    private static final String JWT_SECRET_KEY = "qNAvM/YQRWVAY";

    private static final String ISSUER_NAME = JwtTokenHelper.class.getSimpleName();

    @Override
    public String create(String userId, String sub, long timestamp) {
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(userId)
                .setSubject(sub)
                .setIssuer(ISSUER_NAME)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey);
        if (timestamp >= 0) {
            long expMillis = nowMillis + timestamp;
            Date expDate = new Date(expMillis);
            // 设置过期时间
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    @Override
    public boolean validate(String token) {
        UserTokenModel analyse = analyse(token);
        return !StringUtils.isEmpty(analyse.getUserId());
    }

    @Override
    public UserTokenModel analyse(String token) {
        UserTokenModel userTokenModel = new UserTokenModel();
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
            Date expiration = body.getExpiration();
            userTokenModel.setUserId(body.getId());
            userTokenModel.setExpiration(expiration);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return userTokenModel;
    }

    /**
     * 使用AES加密的密钥
     * @return 密钥
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(JWT_SECRET_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
