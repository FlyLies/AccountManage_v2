package com.ltr.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ltr.pojo.Result;
import com.ltr.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //option请求，放行
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            log.info("OPTIONS请求，放行");
            return true;
        }
        //获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url: {}", url);
        //判断是否为login
        //if (url.contains("/login")) {
        //    log.info("登录，放行...");
        //    return true;
        //}
        //获取请求头中的令牌
        String jwt = request.getHeader("token");
        log.info("token: {}", jwt);
        //判断令牌是否存在，不存在就返回登录页面
        if (!StringUtils.hasLength(jwt)) {
            log.info("令牌为空，返回登录界面");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //解析令牌
        try {
            Claims claims = JwtUtils.parseJwt(jwt);
            log.info("令牌解析结果：{}", claims);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回登录界面");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
