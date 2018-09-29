package com.hdu.edu.encrypt;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.RandomAccessFile;
import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;

/**
 * 实现
 */
public class EncrpytTest {


    public byte[] getKey(int size, String algorithm) throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm,"BC");
        keyGenerator.getProvider();
        keyGenerator.init(size);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();

    }

    public Key toKey(byte[] keyByte, String algorithm){
        return new SecretKeySpec(keyByte, algorithm);
    }

    public void encrpy(String algorithm, byte[] data) throws Exception{
        // key的长度
        int keySize = 128;
        String cipherAlgorithm = null;
        // 添加加密算法和模式
        if (algorithm.equals("ARC4")){
            cipherAlgorithm = algorithm;
        }else{
            cipherAlgorithm = algorithm + "/ECB/PKCS5Padding";
        }

        if (algorithm.equals("DES")){
            keySize = 64;
        }

        // 获取加密密钥
        byte[] keyByte = getKey(keySize, algorithm);
        Key key = toKey(keyByte, algorithm);


        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(data);
        cipher.init(Cipher.DECRYPT_MODE, key);
         cipher.doFinal(result);

    }

    public void hash(String algorithm, byte[] data) throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md=MessageDigest.getInstance(algorithm);
        byte[] result = md.digest(data);
        System.out.println(new String(Hex.encode(result)));
    }


    public static void main(String[] args) throws Exception{

//        for (int i = 1; i < 8; i++) {
//            long start = System.currentTimeMillis();
//             try {
//                RandomAccessFile accessFile = new RandomAccessFile("E:\\data\\test\\part"+i+".mkv", "rw");
//                int size = (int) accessFile.length();
//                byte[] data = new byte[size];
//                accessFile.read(data, 0, size);
//                new EncrpytTest().encrpy("ARC4", data);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            long end = System.currentTimeMillis();
//            System.out.println("时间：" + (end - start));
//        }


//        System.out.println(new String(Hex.encode(result)));
        String[] hashs = new String[]{"Tiger", "RipeMD160", "SHA1", "RipeMD128", "MD5"};
        String[] encryptions = new String[]{"IDEA", "DES","AES", "Blowfish", "RC4"};
        for (int i = 0; i < hashs.length; i++){
            long start = System.currentTimeMillis();
            RandomAccessFile accessFile = new RandomAccessFile("E:\\data\\test\\part3.mkv", "rw");
            int size = (int) accessFile.length();
            byte[] data = new byte[size];
            accessFile.read(data, 0, size);
            new EncrpytTest().hash(hashs[i], data);
            long end = System.currentTimeMillis();
            System.out.println("执行时间：" + (end - start));
        }
    }
}
