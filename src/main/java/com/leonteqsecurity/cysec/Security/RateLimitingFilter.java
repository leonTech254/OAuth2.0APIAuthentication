package com.leonteqsecurity.cysec.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final long LIMIT_TIME_PERIOD = TimeUnit.MINUTES.toMillis(1); // 1 minute
    private final ConcurrentHashMap<String, RateLimit> rateLimitMap = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIp = request.getRemoteAddr();
        RateLimit rateLimit = rateLimitMap.computeIfAbsent(clientIp, k -> new RateLimit());

        synchronized (rateLimit) {
            if (System.currentTimeMillis() - rateLimit.startTime > LIMIT_TIME_PERIOD) {
                // Reset the rate limit
                rateLimit.startTime = System.currentTimeMillis();
                rateLimit.requests = 0;
            }

            // Max 10 requests
            int MAX_REQUESTS = 10;
            if (rateLimit.requests < MAX_REQUESTS) {
                rateLimit.requests++;
                filterChain.doFilter(request, response); // Proceed with the request
            } else {
                // Use 429 status code for Too Many Requests
                response.sendError(429, "Rate limit exceeded");
            }
        }
    }

    private static class RateLimit {
        long startTime = System.currentTimeMillis();
        int requests = 0;
    }
}
