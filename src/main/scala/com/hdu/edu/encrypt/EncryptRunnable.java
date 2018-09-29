package com.hdu.edu.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

public class EncryptRunnable implements Runnable {
    byte[] data = null;
    String algorithm = null;
    int core = 0;
    public EncryptRunnable(byte[] data, String algorithm, int core){
        this.data = data;
        this.algorithm = algorithm;
        this.core = core;

    }
    public byte[] getKey() throws  Exception{
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    public Key toKey(byte[] keyByte){
        return new SecretKeySpec(keyByte, algorithm);
    }

    public void IDEA(byte[] data) throws Exception{
        byte[] keyByte = getKey();
        Key key = toKey(keyByte);
        Cipher cipher = Cipher.getInstance("IDEA/ECB/PKCS5Padding","BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypt_data = cipher.doFinal(data);
        cipher.init(Cipher.DECRYPT_MODE, key);
        cipher.doFinal(encrypt_data);
    }
    @Override
    public void run() {
        try{

        //    System.out.println("core" + core);
            IDEA(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
