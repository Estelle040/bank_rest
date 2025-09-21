package com.example.bankcards.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionUtil {
    private static final String SECRET_KEY = "1234567890123456"; // 16 символов = 128 бит
    private static final String ALGORITHM = "AES";

    public String encrypt(String plainText) {
        try{
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
        }
        catch (Exception e) {
            throw new RuntimeException("Error while encrypting: " + plainText, e);
        }
    }

    public String decrypt(String encryptedText) {
        try{
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] bytes = Base64.getDecoder().decode(encryptedText);
            return new String(cipher.doFinal(bytes));
        }
        catch (Exception e) {
            throw new RuntimeException("Error while decrypting: " + encryptedText, e);
        }
    }
}
