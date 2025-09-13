package com.example.bankcards.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionUtil{
    private static final String ALGORITHM = "AES";
    private final SecretKeySpec secretKey;

    public EncryptionUtil(@Value("${encryption.secret-key}") String secretKey){
        if(secretKey.length() != 16 && secretKey.length() != 24 && secretKey.length() != 32){
            throw new IllegalArgumentException("Secret key must be 16, 24 or 32 characters long");
        }
        this.secretKey = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
    }

    public String encrypt(String data){
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch(Exception e){
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    public String decrypt(String encryptedData){
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch(Exception e){
            throw new RuntimeException("Error decrypting data", e);
        }
    }
}
