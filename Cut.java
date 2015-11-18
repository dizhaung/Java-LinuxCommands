package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cut {
    private File file;
    private CutSwitches switcher;
    private List<CutRange> cutRangeList = new ArrayList<CutRange>();
    private char delimeter='\t';

    public static void main(String[] args) {
        if (args.length==0) {
            System.out.println("cut: musisz podać listę bajtów, znaków albo pól");
            System.out.println("Napisz „cut --help” dla uzyskania informacji.");
            return;
        }

        try {
            parseArgs(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void parseArgs(String[] args) throws FileNotFoundException {
        Cut cut = new Cut();
        for(int i=0;i<args.length;i++)  {
            if (args[i].charAt(0)!='-') {
                if (cut.file!=null) throw new BadAttributesException();
                cut.file = new File(args[i]);
                continue;
            }

            for (CutSwitches switcher : CutSwitches.values())   {
                if (switcher.switcher.equals(args[i]))  {
                    if (i+1<args.length) i++;
                    switcher.processArg(cut, args[i]);
                    break;
                }
            }
        }
        cut.execute();
    }

    private void execute() throws FileNotFoundException {
        Scanner myScanner = new Scanner(file);
        while (myScanner.hasNextLine()) {
            String line = myScanner.nextLine();
            switcher.cut(line, this);
        }
        myScanner.close();
    }

    public CutSwitches getSwitcher() {
        return switcher;
    }

    public void setSwitcher(CutSwitches switcher) {
        this.switcher = switcher;
    }

    public void setDelimeter(char delimeter) {
        this.delimeter = delimeter;
    }

    public char getDelimeter() {
        return delimeter;
    }

    public List<CutRange> getCutRangeList() {
        return cutRangeList;
    }

    public void addToList(CutRange cutRange) {
        this.cutRangeList.add(cutRange);
    }

    private static class BadAttributesException extends RuntimeException {
        private BadAttributesException() {
            super("Argumenty nie są poprawne");
        }
    }
}
