package com.leonteqsecurity.cysec.Security;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ApiKeyService {
    private final Map<String, String> apiKeys = new HashMap<>();
    private final Map<String, String> appIds = new HashMap<>();
    public  Map<String,String> generateAllKeys(String clientID) {
        String appKey = generateApiKey(clientID);
        String appIdKey = generateAppId(clientID);
        apiKeys.put(appKey, clientID);
        appIds.put(appIdKey, clientID);
        Map<String,String> result = new HashMap<>();
        result.put("appKey", appKey);
        result.put("appIdKey", appIdKey);
        result.put("clientID", clientID);
        return result;
    }
    public String generateApiKey(String clientId) {
        String apiKey = UUID.randomUUID().toString();
        apiKeys.put(apiKey, clientId);
        return apiKey;
    }

    public String generateAppId(String clientId) {
        String appId = UUID.randomUUID().toString();
        appIds.put(appId, clientId);
        return appId;
    }

    public boolean validateApiKey(String apiKey) {
        return apiKeys.containsKey(apiKey);
    }

    public boolean validateAppId(String appId) {
        return appIds.containsKey(appId);
    }
}
