package com.leonteqsecurity.cysec.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@AllArgsConstructor
public class HeaderCaptureFilter extends OncePerRequestFilter {

    private final ApiKeyService apiKeyService;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper for JSON serialization

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Check if the request is for the specific endpoint
        if ("/api/v1/movies/".equals(request.getRequestURI())) {
            // Capture specific headers
            String apiKey = request.getHeader("X-API-Key");
            String appId = request.getHeader("X-App-Id");

            // Check if headers are missing
            if (Objects.isNull(apiKey) || Objects.isNull(appId)) {
                // Return a 400 Bad Request status with a JSON message
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setContentType("application/json");
                response.getWriter().write(createJsonResponse("Missing headers: X-API-Key and X-App-Id are required."));
                return;
            }

            // Log or process the headers if they are present
            System.out.println("X-API-Key: " + apiKey);
            System.out.println("X-App-Id: " + appId);
            boolean apiKeyValid = apiKeyService.validateApiKey(apiKey);
            boolean appIdValid = apiKeyService.validateAppId(appId);
            if (apiKeyValid && appIdValid) {
                response.setStatus(HttpStatus.OK.value());
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write(createJsonResponse("Authorized keys are required."));
                return;
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    private String createJsonResponse(String message) throws IOException {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", message);
        return objectMapper.writeValueAsString(responseMap);
    }
}
