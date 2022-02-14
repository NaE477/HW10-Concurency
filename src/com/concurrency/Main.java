package com.concurrency;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        label:
        while (true) {
            ArrayList<String> menu = new ArrayList<>();
            menu.add("Welcome to HW10-Concurrency");
            menu.add("Assignments: ");
            menu.add("1-Integer Sleep");
            menu.add("2-Checking Account");
            menu.add("3-Fixed Checking Account");
            menu.add("4-File Size Calculator");

            menu.add("0-Exit");
            Utilities.menuViewer(menu);
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    intSleepControl();
                    break;
                case "2":
                    incorrectCheckingAccountControl();
                    break;
                case "3":
                    correctCheckingAccountControl();
                    break;
                case "4":
                    sizeCalculator();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("Wrong Option.");
                    break;
            }
        }
    }

    //Assignment #1
    public static void intSleepControl() {
        System.out.println();
        IntSleep intSleep = new IntSleep();
        long now = System.currentTimeMillis();
        System.out.println("           Start at: " + now);
        intSleep.start();
        while (true) {
            if (System.currentTimeMillis() - now == 2000) {
                intSleep.setStopped(true);
                System.out.println("           Interrupted at: " + System.currentTimeMillis());
                break;
            }
        }
    }

    //Assignment #2
    public static void incorrectCheckingAccountControl() {
        final CheckingAccount ca = new CheckingAccount(100); //Withdrawal is from the same account with both husband and wife
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++)
                System.out.println(name + " withdraws $10: " +
                        ca.withdraw(10));
        };
        Thread thdHusband = new Thread(r);
        thdHusband.setName("Husband");
        Thread thdWife = new Thread(r);
        thdWife.setName("Wife");
        thdHusband.start();
        thdWife.start();
    }

    //Assignment #3
    public static void correctCheckingAccountControl() {
        Runnable r = () -> {
            final CheckingAccount ca = new CheckingAccount(100); //Each thread was feeding from same account,fixed
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                System.out.println(name + " withdraws $10: " +
                        ca.withdraw(10));
            }
        };
        Thread thdHusband = new Thread(r);
        thdHusband.setName("Husband");
        Thread thdWife = new Thread(r);
        thdWife.setName("Wife");
        thdHusband.start();
        thdWife.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Assignment #4
    public static void sizeCalculator(){
        File file = Utilities.filePathReceiver();
        System.out.print("           Number of threads: ");
        Integer threads = Utilities.intReceiver();
        SizeCalculator sizeCalculator = new SizeCalculator(file,threads);
        try {
            Utilities.printGreen("           " + sizeCalculator.calcFilesSize(),400);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
