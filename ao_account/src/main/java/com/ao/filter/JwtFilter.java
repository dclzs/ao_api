package com.ao.filter;

import com.ao.exception.AccountException;
import entity.Constanct;
import entity.ResultEnum;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws AccountException {
        String token = request.getHeader(Constanct.HEAD_AUTHORIZATION);
        if (token != null && !"".equals(token)) {
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                return Constanct.ACCOUNT_CLAIMS.equals(claims.get(Constanct.ROLES));
            }
        }
        throw new AccountException(ResultEnum.FAILED.getMsg());
    }
}