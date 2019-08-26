package com.ao.interceptor;

import com.ao.exception.AccountException;
import com.feilong.core.Validator;
import entity.Constanct;
import entity.ResultEnum;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static util.ContextHolderUtils.getAuthorization;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws AccountException {
        try {
            String token = getAuthorization();
            if (Validator.isNotNullOrEmpty(token)) {
                Claims claims = jwtUtil.parseJWT(token);
                if (Validator.isNotNullOrEmpty(claims)) {
                    String newToken = jwtUtil.createJWT(
                            claims.getId(),
                            claims.getSubject(),
                            Constanct.ACCOUNT_CLAIMS);
                    response.setHeader(Constanct.TOKEN, Constanct.TOKEN_PREFIX + newToken);
                    return Constanct.ACCOUNT_CLAIMS.equals(claims.get(Constanct.ROLES));
                }
            }
        } catch (Exception e) {
            throw new AccountException(ResultEnum.TOKEN_ERROR, e);
        }
        throw new AccountException(ResultEnum.FAILED);
    }
}
