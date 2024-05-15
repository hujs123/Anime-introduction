package com.example.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtils {

    // 共享的密钥，应从配置、环境变量或密钥管理服务中获取
    private static final String SECRET_KEY = "yourSecretKey"; // 仅为示例，不要硬编码

    // 加密方法
    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        // 在实际应用中，应生成一个随机的IV
        IvParameterSpec iv = new IvParameterSpec(SECRET_KEY.substring(0, 16).getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }


    // 解密方法
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec iv = new IvParameterSpec(SECRET_KEY.substring(0, 16).getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedValue = cipher.doFinal(decodedValue);

        return new String(decryptedValue, StandardCharsets.UTF_8);
    }

    public static String decryptData(String encryptedData, String secretKey) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
