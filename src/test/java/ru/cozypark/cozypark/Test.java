package ru.cozypark.cozypark;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 604800000);
        System.out.println(Jwts.builder()
                .setSubject(Long.toString(2L))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, "JWTSuperSecretKey")
                .compact());
    }
}
