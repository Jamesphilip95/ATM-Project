package com.company.Controller;

import com.company.Display.Display;
import com.company.Model.ATM;
import com.company.Model.BankAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ATMSimulator implements ATM {
    Map<Integer, BankAccount> accounts = new HashMap<>();
    Random random = new Random();

    public void startATM() {

        Display display = new Display();
        int startType = display.startScreen();
        if (startType == 1) {
            login();
        } else
            createAccount();
    }


    @Override
    public void withdraw(int amount) {

    }

    @Override
    public int transfer(int amount) {
        return 0;
    }

    @Override
    public void createAccount() {
        Display display = new Display();
        String[] names = display.displayEnterName();
        String pin = display.displayEnterPin();
        String accountString = "";
        for (int i = 0; i < 7; i++) {
            int n = random.nextInt(10) + 0;
            accountString += Integer.toString(n);
        }
        int accountNumber = Integer.parseInt(accountString);
        BankAccount account = new BankAccount(accountNumber, names[0], names[1], names[2], pin);
        accounts.put(accountNumber, account);
        display.displayAccountMade(accountNumber);
        login();
    }

    @Override
    public int addMoney(int amount) {
        return 0;
    }

    public void login() {
        Display display = new Display();
        int accountNumber = display.displayLoginScreen1();
        String pin;
       if(!checkAccountNumber(accountNumber)){
          int type = display.displayWrongAccountNumber();
          if (type == 1) {
             login();
          } else
             createAccount();
       }
       do {
           pin = display.displayLoginScreen2();
           if(!checkPin(accountNumber,pin)){
              display.displayWrongPin();
           }
       }while(!checkPin(accountNumber,pin));

             display.displayMainMenu();
          }


    public boolean checkAccountNumber(int accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public boolean checkPin(int accountNumber, String pin) {
        return pin.equals(accounts.get(accountNumber).getPin());
    }
}
