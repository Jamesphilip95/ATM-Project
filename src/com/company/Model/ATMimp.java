package com.company.Model;

import com.company.Display.Display;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ATMimp implements ATM {
    Map<Integer, BankAccount> accounts = new HashMap<>();
    Random random = new Random();
    Display display = new Display();

    @Override
    public void withdraw(int amount) {

    }

    @Override
    public int transfer(int amount) {
        return 0;
    }

    @Override
    public void createAccount(String[] names, String pin) {
        String accountString = "";
      for(int i = 0; i<7; i++){
          int n = random.nextInt(10) + 0;
          accountString += Integer.toString(n);
      }
     int accountNumber = Integer.parseInt(accountString);
    BankAccount account = new BankAccount(accountNumber, names[0],names[1],names[2],pin);
    accounts.put(accountNumber,account);
    display.displayAccountMade(accountNumber);


    }

    @Override
    public int addMoney(int amount) {
        return 0;
    }
}
