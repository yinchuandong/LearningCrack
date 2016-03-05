package com.example.learningcrack;

/**
 * Created by yinchuandong on 16/3/5.
 */

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class e {
    public static String a(String paramString1, String paramString2) {
        return a(a(b(paramString1.getBytes()), paramString2.getBytes()));
    }

    public static String a(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null)
            return "";
        StringBuffer localStringBuffer = new StringBuffer(2 * paramArrayOfByte.length);
        for (int i = 0; ; i++) {
            if (i >= paramArrayOfByte.length)
                return localStringBuffer.toString();
            a(localStringBuffer, paramArrayOfByte[i]);
        }
    }

    private static void a(StringBuffer paramStringBuffer, byte paramByte) {
        paramStringBuffer.append("0123456789ABCDEF".charAt(0xF & paramByte >> 4)).append("0123456789ABCDEF".charAt(paramByte & 0xF));
    }

    public static byte[] a(String paramString) {
        int i = paramString.length() / 2;
        byte[] arrayOfByte = new byte[i];
        for (int j = 0; ; j++) {
            if (j >= i)
                return arrayOfByte;
            arrayOfByte[j] = Integer.valueOf(paramString.substring(j * 2, 2 + j * 2), 16).byteValue();
        }
    }

    private static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
        try {
            Cipher localCipher = Cipher.getInstance("AES");
            localCipher.init(1, localSecretKeySpec);
            return localCipher.doFinal(paramArrayOfByte2);
        }catch (Exception ex){
            return null;
        }

    }

    public static String b(String paramString1, String paramString2) {
        return new String(b(b(paramString1.getBytes()), a(paramString2)));
    }

    private static byte[] b(byte[] paramArrayOfByte) {
        try{
            KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            localSecureRandom.setSeed(paramArrayOfByte);
            localKeyGenerator.init(128, localSecureRandom);
            return localKeyGenerator.generateKey().getEncoded();
        }catch (Exception ex){
            return null;
        }
    }

    private static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {

        try {
            SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
            Cipher localCipher = Cipher.getInstance("AES");
            localCipher.init(2, localSecretKeySpec);
            return localCipher.doFinal(paramArrayOfByte2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
