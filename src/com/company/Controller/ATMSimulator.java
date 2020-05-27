package com.company.Controller;

import com.company.Display.Display;
import com.company.Model.ATM;
import com.company.Model.BankAccount;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ATMSimulator implements ATM {

    static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
    static Logger log = Logger.getLogger(ATMSimulator.class.getName());

    Map<Integer, BankAccount> accounts = new HashMap<>();
    Random random = new Random();

    public void startATM() {

        initialiseLogging();
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
        if (amount >= balance) {
            int type = display.displayInsufficientFunds();
            switch (type) {
                case (1):
                    withdraw(accountNumber);
                    break;
                case (2):
                    mainMenu(accountNumber);
                    break;
                default:
                    display.displayInvalidNumber();
                    mainMenu(accountNumber);
            }

        } else {
            accounts.get(accountNumber).setBalance(balance - amount);
            dispense();
            log.trace(accountNumber + ": Withdrew  £" +amount);
            log.trace("---------------");

            display.displayDispense(amount);
            mainOrLogout(accountNumber);
        }

    }

    private void dispense() {
    }

    @Override
    public void transfer(int account1, int account2, int amount) {
        int balance1 = accounts.get(account1).getBalance();
        int balance2 = accounts.get(account2).getBalance();
        accounts.get(account2).setBalance(balance2 + amount);
        accounts.get(account1).setBalance(balance1 - amount);
        System.out.println("You have successfully transferred £" +amount +" to " +accounts.get(account2).getFirstName()+"'s account");
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
        display.displayAccountMade(accountNumber, accounts.get(accountNumber).getFirstName());
        log.trace("------------------------------");
        log.trace("Account created -> account number:" +accountNumber + ", Name: " +names[0] + " " + names[1] + " " +names[2]);
        log.trace("---------------");
        login();
    }

    @Override
    public void addMoney(int accountNumber) {
        Display display = new Display();
        int money = display.displayAddMoney();
        accounts.get(accountNumber).setBalance(money);
        display.displayMoneyAdded(money);
        log.trace(+accountNumber+ ": added £" +money);
        log.trace("---------------");
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

    private void login() {
        Display display = new Display();
        String accountNumber = display.displayLoginScreen1();
        String pin;
        int type = 0;
        if (!checkAccountNumber(accountNumber)) {
            while (type != 1 && type != 2)
                type = display.displayWrongAccountNumber();
            if (type == 1) {
                login();
            } else if (type == 2) {
                createAccount();
            } else {
                display.displayInvalidNumber();

            }
        } else {
            do {
                pin = display.displayLoginScreen2();
                if (!checkPin(Integer.parseInt(accountNumber), pin)) {
                    display.displayWrongPin();
                }
            } while (!checkPin(Integer.parseInt(accountNumber), pin));
            System.out.println("You have successfully logged in \n");
            log.trace(accountNumber+": Logged in");
            log.trace("---------------");
            mainMenu(Integer.parseInt(accountNumber));
        }
    }

    private void mainMenu(int accountNumber) {
        Display display = new Display();
        int type = display.displayMainMenu();
        switch (type) {
            case (1):
                withdraw(accountNumber);
                break;
            case (2):
                addMoney(accountNumber);
                break;
            case (3):
                checkBalance(accountNumber);
                break;
            case (4):
                transferMoney(accountNumber);
                break;
            case (5):
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
        Display display = new Display();
        int type = 0;
        String accountNumber2 = display.displayTransferAccountNumber();
        if (!checkAccountNumber(accountNumber2)) {
            while (type != 1 && type != 2)
                type = display.displayWrongAccountNumber();
            if (type == 1) {
                transferMoney(accountNumber);
            } else if (type == 2) {
                createAccount();
            } else {
                display.displayInvalidNumber();

            }
        } else if (checkAccountSurname(display.displayTransferSurname(), accountNumber2)) {
            int amount = display.displayTransferAmount(accounts.get(Integer.parseInt(accountNumber2)).getFirstName());
            int balance1 = accounts.get(accountNumber).getBalance();
            if (amount > balance1) {
                display.displayInsufficientFunds();
                mainMenu(accountNumber);
            } else {
                transfer(accountNumber, Integer.parseInt(accountNumber2), amount);
                log.trace(accountNumber + ":transferred £" +amount+ "to " + Integer.parseInt(accountNumber2));
                log.trace("---------------");
                mainOrLogout(accountNumber);
            }

        } else {
            int type3 = display.displayWrongSurname();
            switch (type3) {
                case (1):
                    transferMoney(accountNumber);
                    break;
                case (2):
                    mainMenu(accountNumber);
                    break;
                default:
                    display.displayInvalidNumber();
                    mainMenu(accountNumber);
            }

        }
    }

    private boolean checkAccountSurname(String surname, String accountNumber) {

        return accounts.get(Integer.parseInt(accountNumber)).getSecondName().equals(surname);
    }


    private void checkBalance(int accountNumber) {
        Display display = new Display();
        display.displayBalance(accounts.get(accountNumber).getBalance());
        mainOrLogout(accountNumber);


    }


    private boolean checkAccountNumber(String accountNumber) {
        try {
            Integer.parseInt(accountNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return accounts.containsKey(Integer.parseInt(accountNumber));
    }

    private boolean checkPin(int accountNumber, String pin) {
        return pin.equals(accounts.get(accountNumber).getPin());
    }
    public static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
    }
}
