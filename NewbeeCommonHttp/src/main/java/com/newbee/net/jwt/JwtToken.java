package com.newbee.net.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: zheng.th
 * @Date: 2018/11/22 11:58
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
