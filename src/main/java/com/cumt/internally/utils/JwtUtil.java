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
     *
     * @param encryKey
     * @param minutes
     * @return
     */
    public static String getToken(Staff staff, String encryKey, long minutes) {
        long currentTime = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        map.put("staffId", staff.getStaffId());
//        map.put("staffWeight", staff.getStaffWeight());
        return Jwts.builder()
                .setId(UUID.randomUUID().toString()) //当前用户
                .setIssuedAt(new Date()) //签发日期
                .setSubject("staff") //说明
                .setIssuer("NNroc") //签发者信息
                .signWith(SignatureAlgorithm.HS256, encryKey) //加密方式
                .addClaims(map)
                .setExpiration(new Date(currentTime + minutes * 1000 * 60)) //过期时间
                .compact();
    }

    /**
     * 获取claims
     *
     * @param token
     * @param encryKey
     * @return
     */
    public static Claims getClaims(String token, String encryKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(encryKey).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
}
