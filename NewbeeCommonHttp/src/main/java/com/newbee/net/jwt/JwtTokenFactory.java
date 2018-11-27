/*
package com.newbee.net.jwt;

import com.newbee.net.dto.LoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @Author: zheng.th
 * @Date: 2018/11/22 17:29
 *//*

public class JwtTokenFactory {

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5*60*1000;

    public String createAccessJwtToken(LoginDTO userContext) {
        if (StringUtils.isBlank(userContext.getUserName())){
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }
        if (userContext.getRole() ==null || userContext.getRole().isEmpty()){
            throw new IllegalArgumentException("User doesn't have any role");
        }
        if (userContext.getPermission() ==null || userContext.getPermission().isEmpty()){
            throw new IllegalArgumentException("User doesn't have any privileges");
        }
        Claims claims = Jwts.claims().setSubject(userContext.getUserName());
        claims.put("scopes", userContext.getPermission());
        Date date = new Date();
        Date expireDate = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        */
/*iss: jwt签发者
        sub: jwt所面向的用户
        aud: 接收jwt的一方
        exp: jwt的过期时间，这个过期时间必须要大于签发时间
        nbf: 定义在什么时间之前，该jwt都是不可用的.
        iat: jwt的签发时间
        jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。*//*

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userContext.getUserName())
                .setIssuedAt(date)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, userContext.getSecret())
                .compact();
        return token;
    }

}
*/
