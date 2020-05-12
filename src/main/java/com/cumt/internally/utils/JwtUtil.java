package com.cumt.internally.utils;

import com.cumt.internally.model.Staff;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author NNroc
 * @date 2020/5/12 19:57
 */
public class JwtUtil {
    /**
     * 获取token
     * @param encryKey
     * @param minutes
     * @return
     */
    public static String getToken(Staff staff, String encryKey, long minutes){
        long currentTime = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
//        map.put("userId", user.getUserId());
        return Jwts.builder()
                .setId(UUID.randomUUID().toString()) //当前用户
                .setIssuedAt(new Date()) //签发日期
                .setSubject("system") //说明
                .setIssuer("npy") //签发者信息
                .signWith(SignatureAlgorithm.HS256, encryKey) //加密方式
                .addClaims(map)
                .setExpiration(new Date(currentTime + minutes * 1000 * 60)) //过期时间
                .compact();
    }

    /**
     * 验证是否到时间
     * @param token
     * @param encryKey
     * @return
     */
    public static boolean isExpiration(String token, String encryKey){
        try {
            long currentTime = System.currentTimeMillis();
            if (Jwts.parser().setSigningKey(encryKey).parseClaimsJws(token).getBody().getExpiration().after(new Date(currentTime))){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取claims
     * @param token
     * @param encryKey
     * @return
     */
    public static Claims getClamis(String token, String encryKey){
        try {
            Claims claims = Jwts.parser().setSigningKey(encryKey).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }

//    public static void main(String[] args) {
//        String token =
//"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjOTRmMTYyNy00MzMxLTQzZmMtYWY4My1iNmM5ZTA1M2UzNTIiLCJpYXQiOjE1NjcwODQ1MzUsInN1YiI6InN5c3RlbSIsImlzcyI6Im5weSIsInVzZXJJZCI6MTM4OSwidXNlcm5hbWUiOiIxMSIsImV4cCI6MTU2NzE5MjUzNX0.hUssKQEWZwg59tCls7FseXtvkde6XQ44FVSM1R437Rw";
//        System.out.println(JwtUtil.getClamis(token, "salt").get("userId"));
//        System.out.println(JwtUtil.getClamis(token, "salt").get("username"));
//    }
}
