package com.parking.engine.utils;

import com.parking.engine.dto.JwtDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class JwtsUtils {
    private static final String DOT = "\\.";

    /**
     * Set Jwt token initial
     * @param claims
     * @param subject
     * @param secret
     * @param expiryDate
     * @return
     */
    public static String setJwtToken(Map<String, Object> claims, String subject, String secret, Date expiryDate) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
            Instant now = Instant.now();
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                //.setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
                .setExpiration(expiryDate)
                .signWith(hmacKey)
                .compact();
    }

    /**
     * @param secretKey
     * @param claims
     * @param subject
     * @return
     */
    public static String encodeJWT(String secretKey, Map<String, Object> claims, String subject) {
        try {
            byte[] decodeSecret = org.apache.commons.codec.binary.Base64.decodeBase64(secretKey);
            return Jwts.builder()
                    .addClaims(claims)
                    .setSubject(subject)
                    .signWith(SignatureAlgorithm.HS256, decodeSecret)
                    .compact();
        } catch (Exception ex) {
            log.error("Error when encode jwt:", ex);
        }
        return null;
    }


    /**
     * Decode JWT
     * @return JwtDTO
     */
    public static JwtDTO decodeJWT(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        String[] splitToken = token.split(DOT);
        String encodedHeader = splitToken[0];
        String header = getData(encodedHeader);
        String encodedBody = splitToken.length > 1 ? splitToken[1] : StringUtils.EMPTY;
        String payload = getData(encodedBody);
        String encodedSignature = splitToken.length > 2 ? splitToken[2] : StringUtils.EMPTY;
        String signature = getData(encodedSignature);
        return new JwtDTO(header, payload, signature);
    }

    /**
     * Decode
     * @param encode
     * @return
     */
    private static String getData(String encode) {
        org.apache.commons.codec.binary.Base64 base64Url = new org.apache.commons.codec.binary.Base64(true);
        return new String(base64Url.decode(encode));
    }

}
