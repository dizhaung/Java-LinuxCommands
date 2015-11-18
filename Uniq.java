package com.company;

import java.util.*;

/**
 * Created by kruczjak on 26.04.14.
 */
public class Uniq {
    public static void main(String[] args) {
        uniq();
    }

    private static void uniq() {
        Scanner in = new Scanner(System.in);
        Set<String> set = new LinkedHashSet<String>();

        while (in.hasNextLine())    {
            set.add(in.nextLine());
        }

        System.out.println();
        for (String one : set)
            System.out.println(one);
    }
}
