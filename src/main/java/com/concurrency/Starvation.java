package com.concurrency;

public class Starvation {

    public static final Object obj1 = new Object();
    public static final Object obj2 = new Object();

    public static void main(String[] args) {
        DeadLockThread1 dlt1 = new DeadLockThread1();
        DeadLockThread2 dlt2 = new DeadLockThread2();
        dlt1.start();
        dlt2.start();
    }

    private static class DeadLockThread1 extends Thread {
        public void run() {
            synchronized (obj1) {
                System.out.println("Thread 1: Locking obj1...");

                System.out.println("Thread 1: Waiting for obj2 to unlock...");

                synchronized (obj2) {
                    System.out.println("Thread 1: locking 1 & 2...");
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static class DeadLockThread2 extends Thread {
        public void run() {
            synchronized (obj1) {
                System.out.println("Thread 2: Locking obj2...");

                System.out.println("Thread 2: Waiting for obj1 to unlock...");

                synchronized (obj2) {
                    System.out.println("Thread 2: locking 1 & 2...");
                }
            }
        }
    }
}
