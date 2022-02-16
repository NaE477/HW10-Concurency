package com.concurrency;

import java.io.File;
import java.util.*;

public class Utilities {

    private static final Scanner sc = new Scanner(System.in);

    public static void menuViewer(ArrayList<String> options) {
        System.out.println("\033[0;32m" + "           ----------------------------------------------");
        for (String opt : options) {
            System.out.println("           |" + opt);
        }
        System.out.println("           ----------------------------------------------" + "\033[0m");
        System.out.print("           Option: ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printGreen(String input, Integer pauseTime) {
        try {
            System.out.println("\033[0;32m" + input + "\033[0m");
            Thread.sleep(pauseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printRed(String input, Integer pauseTime) {
        try {
            System.out.println("\033[0;31m" + input + "\033[0m");
            Thread.sleep(pauseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void iterateThroughCharSet(Set<Character> set, Integer time) {
        for (Character c : set) {
            Utilities.printGreen("\033[0;32m" + "           " + c.toString() + "  " + "\033[0m", time);
        }
        System.out.println();
    }

    public static void iterateThroughNumList(List<? extends Number> inpList) {
        System.out.print("           {");
        for (Number n : inpList) {
            System.out.print(n + ",");
        }
        System.out.println("}");
    }

    public static Integer timeChooser(Collection<?> collection) {
        if (collection.size() > 10) {
            return 100;
        } else return 250;
    }

    public static Integer timeChooser(Map<?, ?> collection) {
        if (collection.size() > 10) {
            return 20;
        } else return 250;
    }

    public static Integer intReceiver() {
        while (true) {
            try {
                int output = Integer.parseInt(sc.nextLine());
                if(output > 0) return output;
                else System.out.println("           Enter a number bigger than 0");
            } catch (NumberFormatException e) {
                Utilities.printRed("           Only numbers are allowed here.", 250);
                System.out.print("           ");
            }
        }
    }

    public static File filePathReceiver() {
        while (true) {
            System.out.print("           Path to file: ");
            String path = sc.nextLine();
            File file = new File(path);
            if (file.exists()) {
                return file;
            } else printRed("           Path not found",300);
        }
    }

    public static void listIntReceiver(ArrayList<Integer> inputList) {
        int count = 1;
        while (true) {
            System.out.print("           " + count + ": ");
            String input = sc.nextLine();
            if (!input.equals("e")) {
                try {
                    inputList.add(Integer.parseInt(input));
                    count++;
                } catch (NumberFormatException e) {
                    Utilities.printRed("           Only e or numbers are allowed here.", 250);
                }
            } else break;
        }
    }

    public static Character rangedBaseRandomizer(Character lowerBound, Character upperBound) {
        return (char) ((int) (Math.random() * (upperBound - lowerBound) + lowerBound));
    }
}
