package com.ecommune.gn.api.ecommune.gn.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionProperties {

    @Value("${encryption.secret-key}")
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }
}