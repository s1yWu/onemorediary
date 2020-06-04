package com.verena.s1y.onemorediary.interceptor;

import com.verena.s1y.onemorediary.exception.BaseException;
import com.verena.s1y.onemorediary.server.TokenServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    public final static String TOKEN = "token";
    @Autowired
    private TokenServer tokenServer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Login annotation;
        // 如果处理对象是一个处理方法，则获取到方法上的注解
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod)handler).getMethodAnnotation(Login.class);
            // 否则直接放过拦截的请求
        } else {
            return true;
        }
        // 说明此方法没有Login注解
        if (annotation != null) {
            return true;
        }

        // 从请求头获取token
        String token = request.getHeader("token");


        // 如果还是没有token,则抛异常
        if (StringUtils.isEmpty(token)) {
            throw new BaseException(9999,"token 为空","not find token");
        }

        try {
            if (tokenServer.checkToken(token)){
                request.setAttribute(TOKEN, tokenServer.updateToken(token).replaceAll("\r|\n*",""));
                return true;
            }else {
                throw new BaseException(9999,"token 解析异常","token error");
            }
        }catch (Exception e){
            request.setAttribute(TOKEN, token);
            throw new BaseException(9999,"token 解析异常","token error");
        }

    }
}
