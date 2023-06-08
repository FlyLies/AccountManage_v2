package com.ltr.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String Key = "tianren";
    private static final Long expire = 3600000L;

    /**
     * 生成Jwt令牌
     * claims JWT第二部分负载 payload 中存储的内容
     * */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)                                             //自定义信息（有效载荷）
                .signWith(SignatureAlgorithm.HS256, Key)                       //签名算法（头部）
                .setExpiration(new Date(System.currentTimeMillis() + expire))  //过期时间
                .compact();
    }

    /**
     * 解析JWT令牌
     * jwt JWT令牌
     * JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(Key)   //指定签名密钥
                .parseClaimsJws(jwt)  //指定令牌Token
                .getBody();
    }

}
