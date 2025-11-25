package com.cqgc.interceptor;

import com.cqgc.threadLocal.BaseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前，先清理ThreadLocal
        BaseContext.removeCurrentId();
        
        // 从请求头中获取用户ID（前端在登录后需要在请求头中携带 userId）
        Long userId = null;
        String userIdStr = request.getHeader("userId");
        if (userIdStr != null && !userIdStr.isEmpty()) {
            try {
                userId = Long.parseLong(userIdStr);
                log.debug("LoginInterceptor preHandle: 从请求头获取用户ID: {}", userId);
            } catch (NumberFormatException e) {
                log.warn("LoginInterceptor preHandle: 请求头中的userId格式错误: {}", userIdStr);
            }
        }

        if (userId == null) {
            // 如果请求头中没有，尝试从Session中获取（兼容Session方案）
            Object userIdObj = request.getSession().getAttribute("userId");
            if (userIdObj != null) {
                userId = (Long) userIdObj;
                log.debug("LoginInterceptor preHandle: 从Session获取用户ID: {}", userId);
            }
        }

        if (userId == null) {
            log.debug("LoginInterceptor preHandle: 未找到用户ID（请求头和Session中都没有），拒绝访问");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":0,\"msg\":\"未登录或登录已过期\"}");
            return false;
        }

        BaseContext.setCurrentId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在请求完成后，清理ThreadLocal中的数据，防止内存泄漏
        BaseContext.removeCurrentId();
        log.debug("LoginInterceptor afterCompletion: 清理ThreadLocal");
    }
}