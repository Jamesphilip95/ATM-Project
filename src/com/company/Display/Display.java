package com.company.Display;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Display {
    Scanner scanner = new Scanner(System.in);

    public int startScreen() {
        System.out.println("\nWelcome to the ATM machine.\n" +
                "---------------------------\n" +
                "Enter number to select feature.");
        int num = 0;
        while (num != 1 && num != 2) {
            System.out.println(
                    "1. Login\n" +
                            "2. Create Account");
            try {
                num = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }
            if (num != 1 && num != 2) {
                displayInvalidNumber();
            }
        }
        return num;
    }


    public String displayEnterPin() {
        String pin;
        do {
            System.out.println("Choose your 4 digit pin ");
            pin = scanner.nextLine();
            if (pin.length() != 4 || !pin.matches("[0-9]+")) {
                System.out.println("Invalid pin");
            }
        } while (pin.length() != 4 || !pin.matches("[0-9]+"));

        return pin;
    }

    public String[] displayEnterName() {
        String name;
        String[] names;
        do {
            System.out.println("Enter your Title, First Name and Last Name\n" +
                    "Example: Mr. John Smith");
            name = scanner.nextLine();
            names = name.split(" ");
            if (names.length != 3 || !names[0].contains(".")) {
                System.out.println("Error. Please Enter in format exampled");
            }
        }
        while (names.length != 3 || !names[0].contains("."));
        return names;
    }

    public String displayLoginScreen1() {
        System.out.println("-------------------------\n" +
                "Login\n" +
                "Please enter your account number:");
        return scanner.nextLine();
    }


    public String displayLoginScreen2() {
        System.out.println("Please enter your pin");
        return scanner.nextLine();
    }


    public int displayWrongAccountNumber() {
        int type = 0;
        System.out.println("No account under this number\n");
        while (type != 1 && type != 2) {
            System.out.println("1. Type in number again\n" +
                    "2. Create an account");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayInvalidNumber();

            }
            if (type > 2) {
                displayInvalidNumber();
            }
        }
        return type;
    }

    public void displayAccountMade(int accountNumber, String firstName) {
        System.out.println("Welcome " + firstName + ", you have successfully created an account\n" +
                "Your account number is: " + accountNumber);
    }

    public int displayMainMenu() {
        int type = 0;
        System.out.println("Main Menu\n" +
                "---------------------------\n" +
                "Enter number to select feature.");
        while (type < 1 || type > 5) {
            System.out.println(
                    "1. Withdraw money\n" +
                            "2. Add money\n" +
                            "3. Check balance\n" +
                            "4. Transfer money\n" +
                            "5. Log Out"
            );
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayInvalidNumber();
            }
            if (type < 0 || type > 5) {
                displayInvalidNumber();
            }
        }
        return type;
    }

    public void displayWrongPin() {
        System.out.println("You have entered the wrong pin");
    }

    public void displayInvalidNumber() {
        System.out.println("Error, Enter a valid number");
    }

    public int displayAddMoney() {
        System.out.println("Enter how much money you want to add");
        int type = 0;
        while (type<= 0) {
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayInvalidNumber();
            }
            if(type<0)System.out.println("Error, Enter a positive amount");
        }
        return type;
    }

    public void displayMoneyAdded(int money) {
        System.out.println("You successfully added £" + money + " to your account");
    }

    public int displayMainOrLogOut() {
        int type = 0;
        while (type == 0) {
            System.out.println("1. Return to Main Menu\n" +
                    "2. Log out");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayInvalidNumber();
            }
        }

        return type;
    }

    public void displayBalance(int balance) {
        System.out.println("Your balance is: £" + balance);
    }

    public void displayLogOut(String firstName) {
        System.out.println("You have successfully logged out\n" +
                "Goodbye " + firstName + "\n");
    }

    public int displayWithdraw() {
        int money = -10;
        int type = 0;
        while (money < 0 && (type < 1 || type > 6)) {
            System.out.println("Select the amount of my money you wish to withdraw\n" +
                    "1. 10\n" +
                    "2. 20\n" +
                    "3. 30\n" +
                    "4. 50\n" +
                    "5. 100\n" +
                    "6. Other");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            switch (type) {
                case (1):
                    money = 10;
                    break;
                case (2):
                    money = 20;
                    break;
                case (3):
                    money = 30;
                    break;
                case (4):
                    money = 50;
                    break;
                case (5):
                    money = 100;
                    break;
                case (6):
                    System.out.println("Enter an amount divisible by 10:");
                    try {
                        money = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {

                    }
                    while (money % 10 != 0 || money < 10) {
                        System.out.println("Invalid, Enter a positive amount divisible by 10");
                        try {
                            money = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {

                        }
                    }
                    break;
                default:
                    displayInvalidNumber();
            }
        }
        return money;
    }

    public int displayInsufficientFunds() {
        System.out.println("Error, you have Insufficient Funds\n");
        int type = 0;
        while (type!=1 && type !=2) {
            System.out.println("1. Select a different amount\n" +
                    "2. Main Menu");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayInvalidNumber();
            }
        }


        return type;

    }

    public void displayDispense(int amount) {
        System.out.println("Successful, please collect your £" + amount);
    }

    public String displayTransferAccountNumber() {
        System.out.println("Enter the account number you wish to transfer to: ");
    return scanner.nextLine();

        }

    public String displayTransferSurname() {
        System.out.println("Enter account holders surname: ");
        return scanner.nextLine();
    }

    public int displayTransferAmount(String firstName) {
        System.out.println("Successful, Please Enter the amount you want to transfer "+ firstName);
        int amount = 0;
        while(amount<=0){
            try {
                amount = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayInvalidNumber();
            }
            if(amount<0) System.out.println("Invalid, Enter a positive amount");
        }
        return amount;
    }

    public int displayWrongSurname() {
        System.out.println("Surname does not match account number");
        int type = 0;
        while (type !=1 && type!= 2) {
            System.out.println("1. Try surname again\n" +
                    "2. Main Menu");
            try {
                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayInvalidNumber();
            }
            if(type>2 || type<1)displayInvalidNumber();
        }
        return type;
    }
}

