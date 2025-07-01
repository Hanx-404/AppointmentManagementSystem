package com.niit.adminservice.filter;




import com.niit.adminservice.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-06-30)
 */

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    // 定义不需要token验证的路径
    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/login", "/error", "/static/**", "/js/**", "/admin/login"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        logger.info("Current request path: " + path);

        return EXCLUDE_PATHS.stream().anyMatch(p -> {
            if (p.endsWith("/**")) {
                String basePath = p.substring(0, p.length() - 3);
                return path.startsWith(basePath);
            }
            return path.contains(p);
        });
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authToken = request.getHeader("Authorization");

            if (!StringUtils.hasText(authToken) || !authToken.startsWith("Bearer ")) {
                sendUnauthorizedResponse(response, "未登录");
                return;
            }

            // 提取token
            authToken = authToken.substring(7);

            // 验证token合法性
            if (!jwtUtil.validateToken(authToken)) {
                sendUnauthorizedResponse(response, "无效的token");
                return;
            }

            // 获取用户名
            String username = jwtUtil.getUsernameFromToken(authToken);

            // 从Redis验证token
            Object tokenInRedis = redisTemplate.opsForValue().get(username);
            if (tokenInRedis == null || !tokenInRedis.equals(authToken)) {
                sendUnauthorizedResponse(response, "登录已过期，请重新登录");
                return;
            }

            // 更新token过期时间（可选）
            redisTemplate.expire(username, jwtUtil.getExpiration(), TimeUnit.SECONDS);

            // 继续处理请求
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            sendUnauthorizedResponse(response, "验证失败：" + e.getMessage());
        }
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("{\"code\":401,\"message\":\"" + message + "\"}");
    }
}
