package com.lt.dailytest.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author tong.luo
 * @description JwtToken
 * @date 2021/11/17 15:27
 */
@Component
public class JwtToken {

    private static long expireSeeconds = 1000 * 60 * 60;
    private static final String tokenSign = "1000 * 60 * 60";

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary("mall-user");
        return new SecretKeySpec(bytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 生成token的方法
     *
     * @param userid
     * @param expireSeecondsUse
     * @return
     */
    public static String generatorToken(String userid, int expireSeecondsUse) {
        if (expireSeecondsUse > 1) {
            expireSeeconds = expireSeecondsUse * 1000;
        }
        return Jwts.builder().setSubject(userid)
                .setExpiration(new Date(System.currentTimeMillis() + expireSeeconds))
                .signWith(SignatureAlgorithm.HS512, tokenSign)
                .compact();
       /* return Jwts.builder().claim("username", userid)
                .setExpiration(DateUtils.parseDate(DateUtils.addSecond(expire), DateUtils.yyyy_MM_dd_hh_mm_ss))
                .signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();*/
    }

    /**
     * 根据token获取token中的信息
     *
     * @param token
     * @return
     */
    public static String getTokenInfo(String token) {
        /*Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get("username").toString();*/
        String subject = Jwts.parser().setSigningKey(tokenSign).parseClaimsJws(token).getBody().getSubject();
        return subject;
    }

    public static void main(String[] args) throws Exception{
        String usernam = "123123123123";
        String s = JwtToken.generatorToken(usernam, 5);
        System.out.println(s);

        String tokenInfo = JwtToken.getTokenInfo(usernam);
        System.out.println(tokenInfo);
        Thread.sleep(6 * 1000);
         tokenInfo = JwtToken.getTokenInfo(usernam);
        System.out.println(tokenInfo);


    }
}
