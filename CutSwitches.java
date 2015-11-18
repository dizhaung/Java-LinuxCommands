package com.company;

import sun.misc.Regexp;

import java.io.File;
import java.util.*;

/**
 * Created by kruczjak on 26.04.14.
 */
public enum CutSwitches {
    CHARACTERS("-c") {
        @Override
        public void cut(String line, Cut cut) {
            int max = line.length();

            Set<Integer> set = new HashSet<Integer>();

            for (CutRange cutRange : cut.getCutRangeList())  {
                Integer start = cutRange.getStart();
                Integer end = cutRange.getEnd();
                if (start==null) start = 1;
                if (end==null) end = max;
                for (;start<=end;start++)
                    set.add(start);
            }

            for (int i=0;i<max;i++) {
                if (set.contains(i+1))
                    System.out.print(line.charAt(i));
            }
            System.out.println();
        }
    },
    FIELDS("-f") {
        @Override
        public void cut(String line, Cut cut) {
            String[] splited = line.split(String.valueOf(cut.getDelimeter()));
            int max = splited.length;

            Set<Integer> set = new HashSet<Integer>();

            for (CutRange cutRange : cut.getCutRangeList())  {
                Integer start = cutRange.getStart();
                Integer end = cutRange.getEnd();
                if (start==null) start = 1;
                if (end==null) end = max;
                for (;start<=end;start++)
                    set.add(start);
            }

            for (int i=0;i<max;i++) {
                if (set.contains(i+1))
                    System.out.print(splited[i] + cut.getDelimeter());
            }
            System.out.println();
        }
    },
    DELIMETER("-d") {
        @Override
        public void processArg(Cut cut, String arg) {
            if (arg.length()!=1) throw new DelimeterMustBeCharException();
            cut.setDelimeter(arg.charAt(0));
        }
    },
    HELP("--help")  {
        @Override
        public void processArg(Cut cut, String arg) {
            System.out.println("Składnia: cut OPCJA... [PLIK]...");
            System.out.println("Wypisywanie wybranych części linii z każdego PLIKU na standardowe wyjście.");
            System.out.println("-c \t wypisanie tylko tych znaków");
            System.out.println("-f \t wypisanie pól");
            System.out.println("-d \t użycie OGRANICZNIKA zamiast TABa jako separatora (działa tylko z -f)");
            System.out.println("\nUżyć można tylko jednej z opcji -b, -c albo -f. Każda LISTA składa się z\n" +
                    "jednego zakresu lub wielu zakresów oddzielonych przecinkami. Przefiltrowane\n" +
                    "dane wejściowe są wypisywane w tym samym porządku, w jakim są czytane i są\n" +
                    "wypisywane tylko raz.\n" +
                    "Każdy zakres to:\n" +
                    "\n" +
                    "  N     N-ty bajt, znak lub pole, liczone od 1\n" +
                    "  N-    od N-tego bajtu, znaku lub pola do końca linii\n" +
                    "  N-M   od N-tego do M-tego (włącznie) bajtu, znaku lub pola\n" +
                    "  -M    od pierwszego do M-tego (włącznie) bajtu, znaku lub pola\n");
            System.exit(0);
        }
    };

    String switcher;

    CutSwitches(String switcher) {
        this.switcher=switcher;
    }

    public void processArg(Cut cut, String arg) {
        if (cut.getSwitcher()!=null) throw new MultipleSwitchesException();

        StringTokenizer stringTokenizer = new StringTokenizer(arg,",");
        while(stringTokenizer.hasMoreTokens())  {
            String token = stringTokenizer.nextToken();
            Integer i = parse(token);
            if (i!=null) {
                if (i==0) throw new BadRangeException();
                if (i<0) cut.addToList(new CutRange(null,-i));
                else cut.addToList(new CutRange(i,i));
            } else  {
                StringTokenizer tokenSpace = new StringTokenizer(token,"-");
                if (tokenSpace.countTokens()>2) throw new BadRangeException();

                Integer first = parse(tokenSpace.nextToken());
                if (first==null || first<1) throw new BadRangeException();

                if (tokenSpace.countTokens()==0)    {
                    cut.addToList(new CutRange(first, null));
                    continue;
                }

                String second = tokenSpace.nextToken();
                Integer secondInt = parse(second);
                if (secondInt == null || secondInt < 1 || secondInt < first) throw new BadRangeException();
                cut.addToList(new CutRange(first, secondInt));
            }
        }

        cut.setSwitcher(this);
    }

    public void cut(String line, Cut cut) {

    }

    private Integer parse(String s) {
        if (s==null) return null;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static class DelimeterMustBeCharException extends RuntimeException {
        private DelimeterMustBeCharException() {
            super("Ogranicznik musi być pojedynczym znakiem");
        }
    }

    private class MultipleSwitchesException extends RuntimeException {
        private MultipleSwitchesException() {
            super("Nie możesz użyć kilku parametrów (-b,-c,-f)");
        }
    }

    private class BadRangeException extends RuntimeException {
        private BadRangeException() {
            super("Błędnie podany zasięg!");
        }
    }
}
