package com.test;

import java.io.*;

public class InBox {
    public static void main(String[] args) throws Exception {
        String line = null;
        try {
            String[] cmd = {"cmd", "/C", "echo hello "};
            Process process = Runtime.getRuntime().exec(cmd);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            bw.write("dir");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
