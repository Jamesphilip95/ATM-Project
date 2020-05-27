package com.company.Model;

public interface ATM {
void withdraw(int accountNumber);
void transfer(int account1, int account2, int amount);
void createAccount();
void addMoney(int accountNumber);
}
