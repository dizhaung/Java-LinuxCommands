package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by kruczjak on 26.04.14.
 */
public class Tee {
    public static void main(String[] args) {
        if (args.length==0) {
            tee();
        } else
            try {
                tee(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private static void tee() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(line);
    }

    private static void tee(String arg) throws IOException {
        FileWriter fstream = new FileWriter(arg, true); //true tells to append data.
        BufferedWriter out = new BufferedWriter(fstream);

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        out.write(line);
        System.out.println(line);

        out.close();
    }
}
