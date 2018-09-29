package com.hdu.edu.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.RandomAccessFile;
import java.security.Key;
import java.security.Security;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Boolean>{
    private int cores;
    private int offset;
    private int size;
    private  final RandomAccessFile accessFile;

    final String algorithm = "IDEA";

    public ForkJoinTest(int cores, RandomAccessFile accessFile){
        this.cores = cores;
        this.offset = offset;
        this.size = size;
        this.accessFile = accessFile;
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

    public boolean IDEA(byte[] data) throws Exception{
        byte[] keyByte = getKey();
        Key key = toKey(keyByte);
        Cipher cipher = Cipher.getInstance("IDEA/ECB/PKCS5Padding","BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypt_data = cipher.doFinal(data);
        cipher.init(Cipher.DECRYPT_MODE, key);
        cipher.doFinal(encrypt_data);
        return true;
    }

    @Override
    protected Boolean compute() {
        boolean canCampute = cores == 1;
        System.out.println(canCampute);
        boolean flag = true;
        byte[] data = new byte[1024 * 1024 * 100];
        try{
            accessFile.read(data, 0, size);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (canCampute){
            try {
                IDEA(data);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            ForkJoinTest leftTask = new ForkJoinTest( offset + size,  accessFile);
            ForkJoinTest rightTask = new ForkJoinTest(cores - cores / 2, accessFile);
            leftTask.fork();
            rightTask.fork();
            boolean leftResult = leftTask.join();
            boolean rightResult = rightTask.join();
            flag = flag && leftResult && rightResult;
        }
        return flag;
    }

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RandomAccessFile accessFile = new RandomAccessFile("E:\\data\\test\\part3.mkv", "rw");
        ForkJoinTest task = new ForkJoinTest(1,accessFile);
        forkJoinPool.submit(task);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
