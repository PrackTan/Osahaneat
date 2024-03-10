package com.cybersoft.osahaneat.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JWTHelper {
    ///get key application
    @Value("${custom.token.key}")
    private String keyToken;
    private long expirationTime = 8 * 60 * 60 * 1000;

    // -------------genereateToken------------------------
    public String generateToken(String data) {
        /// Decoders key from POM.XML by @VALUES
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyToken));
        Date date = new Date();
        long currentDateMillis = date.getTime() + expirationTime;
        Date expiredDate = new Date(currentDateMillis);

        String token = Jwts.builder()
                .subject(data)///dữ liệu mã hóa được truyền vào
                .signWith(key)
                .expiration(expiredDate)
                .compact();

        return token;
    }
    ////---------------- Parse TOKEN----------------
    public String parserToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyToken));
        //----------- for new version JWT SPring security----------
        String data = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
        return data;
    }
}
