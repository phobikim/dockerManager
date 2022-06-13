package com.docker.dockermanager.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES64 {
    public static String alg = "AES/CBC/PKCS5Padding";
    private final String key = "aestest_2022_06_12";
    private final String iv = key.substring(0,8);

    public String encrypt(String text) throws Exception{
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes() , "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes() , "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE , keySpec , ivParameterSpec);

        byte[] decodeBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodeBytes);
        return new String(decrypted , "UTF-8");
    }
}
