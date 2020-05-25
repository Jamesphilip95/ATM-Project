package com.company.Model;

public class BankAccount {
    private int accountNumber;
    private String title;
    private String firstName;
    private String secondName;
    private int balance;
    private String pin;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public BankAccount(int accountNumber, String title, String firstName, String secondName, String pin) {
        this.accountNumber = accountNumber;
        this.title = title;
        this.firstName = firstName;
        this.secondName = secondName;
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance =+ balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
