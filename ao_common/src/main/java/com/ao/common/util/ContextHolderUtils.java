package com.ao.common.util;

import com.ao.common.entity.Constanct;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ContextHolderUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取 jwt 令牌，必须以 Bearer  开头，否则返回 null
     * @return
     */
    public static String getAuthorization() {
        String authorization = getRequest().getHeader(Constanct.AUTHORIZATION);
        if (authorization.startsWith(Constanct.TOKEN_PREFIX)) {
            return authorization.replace(Constanct.TOKEN_PREFIX, "");
        }
        return null;
    }

}