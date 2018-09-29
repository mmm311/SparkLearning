package com.hdu.edu.encrypt;

import org.dmg.pmml.True;

import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EncryptMain {
    /**
     *
     * @param cores cpu 个数
     *
     */
    public void encrypt(int cores, int part, String algorithm) throws Exception{
        String path = "E:\\data\\test\\part" + part + ".mkv";
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        CountDownLatch latch = new CountDownLatch(cores);

        RandomAccessFile accessFile = new RandomAccessFile(path, "rw");
        int length =(int) accessFile.length();
        int size = length / cores;
        byte[] data = new byte[size];

        for (int i = 1; i <= cores; i++){
            accessFile.read(data);
            accessFile.seek(size);
            service.execute(new EncryptRunnable(data, algorithm,cores));
        }


        service.shutdown();
        while (true){
            if (service.isTerminated()){
                long end = System.currentTimeMillis();
                System.out.println("part" + part + ", cores" + cores + ", 用时：" + (end - start) * 1.0 / 1000 + "ms");
                if (part < 4){
                    part = part + 1;
                    new EncryptMain().encrypt(cores,  part,"IDEA");
                }else{
                    part = 1;
                    if (cores < 4) {
                        cores = cores + 1;
                        new EncryptMain().encrypt(cores,  part,"IDEA");

                    }
                }

                break;
            }

        }
    }

    public static void main(String[] args) throws Exception{
        new EncryptMain().encrypt(1,  1,"IDEA");

    }
}
