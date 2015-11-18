package com.company;


import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by kruczjak on 26.04.14.
 */
public class Gunzip {
    public static void main(String[] args) {
        if (args.length<2) {
            System.out.println("gunzip: musisz podać źródło i wyjście");
            return;
        }

        try {
            decompressGzipFile(args[0],args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decompressGzipFile(String in,String out) throws IOException {
        FileInputStream fis = new FileInputStream(in);
        GZIPInputStream gis = new GZIPInputStream(fis);
        FileOutputStream fos = new FileOutputStream(out);

        byte[] buffer = new byte[1024];
        int len;
        while((len = gis.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }

        fos.close();
        gis.close();
    }
}
