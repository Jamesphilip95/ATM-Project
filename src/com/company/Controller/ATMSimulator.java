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
    public void withdraw(int accountNumber) {
        Display display = new Display();
       int amount = display.displayWithdraw();
       int balance = accounts.get(accountNumber).getBalance();
       if(amount>=balance){
          int type = display.displayInsufficientFunds();
          switch(type){
              case(1):
                  withdraw(accountNumber);
                  break;
              case(2):
                  mainMenu(accountNumber);
                  break;
              default:
                  display.displayInvalidNumber();
                  mainMenu(accountNumber);
          }

       }
       else{
          accounts.get(accountNumber).setBalance(balance-amount);
          dispense();
          display.displayDispense(amount);
          mainOrLogout(accountNumber);
       }

    }

    private void dispense() {
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
    public void addMoney(int accountNumber) {
        Display display = new Display();
        int money = display.displayAddMoney();
        accounts.get(accountNumber).setBalance(money);
        display.displayMoneyAdded(money);
        mainOrLogout(accountNumber);

    }

    private void mainOrLogout(int accountNumber) {
        Display display = new Display();
        switch (display.displayMainOrLogOut()) {
            case (1):
                mainMenu(accountNumber);
                break;
            case (2):
                logOut(accountNumber);
                break;
            default:
                display.displayInvalidNumber();
                mainOrLogout(accountNumber);
        }
    }
        private void login () {
            Display display = new Display();
            int accountNumber = display.displayLoginScreen1();
            String pin;
            if (!checkAccountNumber(accountNumber)) {
                int type = display.displayWrongAccountNumber();
                if (type == 1) {
                    login();
                } else
                    createAccount();
            } else {
                do {
                    pin = display.displayLoginScreen2();
                    if (!checkPin(accountNumber, pin)) {
                        display.displayWrongPin();
                    }
                } while (!checkPin(accountNumber, pin));

                mainMenu(accountNumber);
            }
        }

    private void mainMenu(int accountNumber){
        Display display = new Display();
       int type = display.displayMainMenu();
       switch(type){
           case(1):
               withdraw(accountNumber);
               break;
           case(2):
               addMoney(accountNumber);
               break;
           case(3):
               checkBalance(accountNumber);
               break;
           case(4):
               transferMoney(accountNumber);
               break;
           case(5):
               logOut(accountNumber);
               break;
       }
    }

    private void logOut(int accountNumber) {
        Display display = new Display();
        display.displayLogOut(accounts.get(accountNumber).getFirstName());
        startATM();
    }

    private void transferMoney(int accountNumber) {
    }

    private void checkBalance(int accountNumber) {
        Display display = new Display();
        display.displayBalance(accounts.get(accountNumber).getBalance());
        mainOrLogout(accountNumber);


    }


    private boolean checkAccountNumber(int accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    private boolean checkPin(int accountNumber, String pin) {
        return pin.equals(accounts.get(accountNumber).getPin());
    }
}
