package com.company.Model;

public interface ATM {
void withdraw(int amount);
int transfer(int amount);
void createAccount(String[] names, String pin);
int addMoney(int amount);
}
