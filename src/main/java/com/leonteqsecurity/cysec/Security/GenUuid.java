package com.leonteqsecurity.cysec.Security;

import java.util.UUID;

public class GenUuid {
    public static String generateShortUUID(int length) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString.substring(0, length);
    }
}
