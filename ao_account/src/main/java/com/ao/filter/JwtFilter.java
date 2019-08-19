package com.ao.filter;

import com.ao.exception.AccountException;
import entity.Constanct;
import entity.ResultEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws AccountException {
        try {
            String token = request.getHeader(Constanct.HEAD_AUTHORIZATION);
            if (token != null && !"".equals(token)) {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {
                    String newToken = jwtUtil.createJWT(
                            claims.getId(),
                            claims.getSubject(),
                            Constanct.ACCOUNT_CLAIMS);
                    response.setHeader("token", newToken);
                    return Constanct.ACCOUNT_CLAIMS.equals(claims.get(Constanct.ROLES));
                }
            }
        } catch (SignatureException e) {
            e.printStackTrace();
            logger.error("================{}=============", e.getMessage());
            throw new AccountException(ResultEnum.TOKEN_ERROR.getMsg());
        }
        throw new AccountException(ResultEnum.FAILED.getMsg());
    }
}
