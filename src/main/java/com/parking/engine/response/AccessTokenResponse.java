package com.parking.engine.response;

import lombok.Data;

@Data
public class AccessTokenResponse {
    String sub;
    String jti;
    String iat;
    String exp;
}
