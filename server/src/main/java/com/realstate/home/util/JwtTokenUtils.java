package com.realstate.home.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtils {



    //토근 생성할때 넣어준 memberName 가져오기
    public static String getMemberNameByToken(String token, String key) {
        return extractClaims(token, key).get("memberName", String.class);
    }

    //기한 만료됐는지 확인
    public static boolean isExpired(String token, String key) {
        Date expiredDate = extractClaims(token, key).getExpiration();
        return expiredDate.before(new Date());
    }

    //토큰 생성했을때, 넣었던 데이터 추출하기(회원이름)
    public static Claims extractClaims(String token, String key) {
       return Jwts.parserBuilder().setSigningKey(getKey(key))
                .build().parseClaimsJws(token).getBody();
    }

    public static String createJwtToken(String memberName, String key, long expiredTimeMs) {
        Claims claims = Jwts.claims();

        //유저를 식별하는 값
        claims.put("memberName", memberName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(getKey(key), SignatureAlgorithm.HS256)
                .compact();
        //스트링으로 리턴
    }

    //키 변환하는 함수
    private static Key getKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
