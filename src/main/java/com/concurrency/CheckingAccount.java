package com.concurrency;

public class CheckingAccount {
    private int balance;
    public CheckingAccount(int initialBalance) {
        balance = initialBalance;
    }

    public boolean withdraw(int amount) {
        if (amount <= balance) {
            try {
                Thread.sleep((int) (Math.random() * 200));
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            balance -= amount;
            return true;
        }
        return false;
    }
}
