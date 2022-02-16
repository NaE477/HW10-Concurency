package com.concurrency;

public class IntSleep extends Thread {

    Boolean stopped = false;

    @Override
    public void run() {
        int count = 1;
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                if(!stopped) {
                    System.out.println("           " + count++ + ":Hello");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public void setStopped(Boolean stopped) {
        synchronized (this) {
            this.stopped = stopped;
        }
    }
}
