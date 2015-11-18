package com.company;


import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

/**
 * Created by kruczjak on 26.04.14.
 */
public class Gzip {
    public static void main(String[] args) {
        if (args.length<2) {
            System.out.println("gzip: musisz podać źródło i wyjście");
            return;
        }

        try {
            compressGzipFile(args[0],args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compressGzipFile(String in, String out) throws IOException {
        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);

        GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
        byte[] buffer = new byte[1024];
        int len;
        while((len=fis.read(buffer)) != -1){
            gzipOS.write(buffer, 0, len);
        }

        gzipOS.close();
        fos.close();
        fis.close();
    }
}
