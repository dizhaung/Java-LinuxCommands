package com.company;

import sun.misc.Regexp;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kruczjak on 26.04.14.
 */
public class Grep {
    public static void main(String[] args) {
        if (args.length==0) {
            System.out.print("No argument");
            return;
        }
        grep(args[0]);
    }

    private static void grep(String pattern) {
        Pattern p = Pattern.compile(pattern);

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())    {
            String line = in.nextLine();
            Matcher m = p.matcher(line);
            if (m.find())
                System.out.println(line);
        }
    }
}
