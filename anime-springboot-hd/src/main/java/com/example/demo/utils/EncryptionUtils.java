package com.example.demo.utils;


import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class EncryptionUtils {

    // 共享的密钥，应从配置、环境变量或密钥管理服务中获取
    private static final String SECRET_KEY = "c6410b3e36ad900f03e10412a14cc93b"; // 仅为示例，不要硬编码
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    // 假设你已经有了安全的密钥和IV生成方式
    // 这里仅为示例，密钥和IV应该是随机生成的
    private static final byte[] IV = "qazwsxed".getBytes(StandardCharsets.UTF_8); // IV必须是16字节


    // 加密方法
    public String selfencrypt(String data) throws Exception {

        return data;
    }

    // 解密方法
    public String selfdecrypt(String encryptedData) throws Exception {

        return encryptedData;
    }

    public String encryptdata(String encryptedData, String secretKey) {

        return encryptedData;
    }

    public String decryptdata(String encryptedData, String secretKey) throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {
        byte[] ciphertext = Base64.getDecoder().decode(encryptedData);

        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec(IV);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        byte[] decrypted = cipher.doFinal(ciphertext);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
    // 注意：上述代码中的密钥和IV是硬编码的，仅用于示例。
    // 在实际应用中，你应该使用安全的方式生成和存储这些值。
}
