package com.ecommune.gn.api.ecommune.gn.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class EncryptionService {

    private static final String ALGORITHM = "AES";

    private SecretKeySpec secretKeySpec;

    @Autowired
    private EncryptionProperties encryptionProperties;

    @PostConstruct
    public void init() {
        String key = encryptionProperties.getSecretKey();
        if (key.length() != 16) {
            throw new IllegalArgumentException("La clé secrète doit faire 16 caractères");
        }
        secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
    }

    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Erreur de chiffrement", e);
        }
    }

    public String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            return new String(cipher.doFinal(decoded));
        } catch (Exception e) {
            throw new RuntimeException("Erreur de déchiffrement", e);
        }
    }
}

