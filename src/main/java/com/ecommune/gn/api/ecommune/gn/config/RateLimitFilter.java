package com.ecommune.gn.api.ecommune.gn.config;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitFilter extends OncePerRequestFilter {
    private static final int HTTP_TOO_MANY_REQUESTS = 429;
    private final Map<String, Long> requestTimestamps = new ConcurrentHashMap<>();

    private static final long LIMIT_INTERVAL_MS = 1000; // 1 seconde

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (shouldFilter(request)) {
            String clientIp = request.getRemoteAddr(); // Ou une clé unique (userId, token, etc.)
            long now = System.currentTimeMillis();
            Long lastRequestTime = requestTimestamps.get(clientIp);

            if (lastRequestTime != null && now - lastRequestTime < LIMIT_INTERVAL_MS) {
                response.setStatus(HTTP_TOO_MANY_REQUESTS);
                response.getWriter().write("Too many requests: only 1 request per second is allowed.");
                return;
            }

            requestTimestamps.put(clientIp, now);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Appliquer uniquement sur le login
        return !request.getServletPath().equals("/api/v1/auth/login");
    }

    private boolean shouldFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/v1/auth/login")
                && request.getMethod().equalsIgnoreCase("POST");
    }
}
