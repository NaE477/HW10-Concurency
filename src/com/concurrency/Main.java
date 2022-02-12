package com.concurrency;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            ArrayList<String> menu = new ArrayList<>();
            menu.add("Welcome to HW10-Concurrency");
            menu.add("Assignments: ");
            menu.add("1-Integer Sleep");
            menu.add("2-Checking Account");

            menu.add("0-Exit");
            Utilities.menuViewer(menu);
            String opt = sc.nextLine();
            if (opt.equals("1")) {
                intSleepControl();
            } else if (opt.equals("2")) {
                checkingAccountControl();
            } else if (opt.equals("0")) {
                break;
            } else {
                System.out.println("Wrong Option.");
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
    public static void checkingAccountControl(){
        final CheckingAccount ca = new CheckingAccount(100);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++)
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
    }
}
