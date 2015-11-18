package com.company;


import java.io.*;
import java.util.Scanner;

/**
 * Created by kruczjak on 26.04.14.
 */
public class Cat {
    public static void main(String[] args) {
        if (args.length==0) {
            System.out.println("cat: musisz podać plik");
            return;
        }

        try {
            openFile(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openFile(String arg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(arg)));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
