package com.parking.engine.security;

import com.google.gson.Gson;
import com.parking.engine.dto.JwtDTO;
import com.parking.engine.response.AccessTokenResponse;
import com.parking.engine.utils.JwtsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    /**
     * Create jwt from user
     * @param userId
     * @return
     */
    public String generateToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Long.parseLong(jwtExpiration));
        return JwtsUtils.setJwtToken(new HashMap<>(), userId, jwtSecret, expiryDate);
    }

    /**
     * Get UserId by jwt
     * @param token
     * @return
     */
    public String getUserIdByJWT(String token) {
        JwtDTO jwt = JwtsUtils.decodeJWT(token);
        Gson gson = new Gson();
        AccessTokenResponse response = gson.fromJson(jwt.getPayload(), AccessTokenResponse.class);
        return response.getSub();
    }
}
