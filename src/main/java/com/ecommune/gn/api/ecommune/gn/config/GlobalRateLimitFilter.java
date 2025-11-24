package com.ecommune.gn.api.ecommune.gn.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GlobalRateLimitFilter extends OncePerRequestFilter {
    private static final int HTTP_TOO_MANY_REQUESTS = 429;
    private static final long LIMIT_INTERVAL_MS = 1000; // 1 requête/sec globale
    private volatile long lastRequestTime = 0;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long now = System.currentTimeMillis();
        synchronized (this) {
            if (now - lastRequestTime < LIMIT_INTERVAL_MS) {
                response.setStatus(HTTP_TOO_MANY_REQUESTS);
                response.getWriter().write("Too many requests: only 1 request per second is allowed.");
                return;
            }
            lastRequestTime = now;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
    	String path = request.getServletPath();
    	return path.startsWith("/swagger") ||
               path.startsWith("/v3/api-docs") ||
               path.startsWith("/swagger-ui") ||
               path.startsWith("/actuator") ||
               path.startsWith("/favicon");
    }
}
