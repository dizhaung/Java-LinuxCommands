package com.company;

import java.util.*;

/**
 * Created by kruczjak on 26.04.14.
 */
public class Sort {
    public static void main(String[] args) {
        sort();
    }

    private static void sort() {
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<String>();

        while (in.hasNextLine())    {
            list.add(in.nextLine());
        }
        Collections.sort(list);
        System.out.println();
        for (String one : list)
            System.out.println(one);
    }
}
