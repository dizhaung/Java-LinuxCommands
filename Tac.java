package com.company;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by kruczjak on 26.04.14.
 */
public class Tac {
    public static void main(String[] args) {
        if (args.length==0) {
            System.out.println("tac: musisz podać plik");
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

        Stack<String> tmp = new Stack<String>();
        while ((inputLine = in.readLine()) != null)
            tmp.push(inputLine);
        in.close();

        for(int i=tmp.size()-1;i>=0;i--) {
            System.out.println(tmp.pop());
        }
    }
}
