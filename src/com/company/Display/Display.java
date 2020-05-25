package com.company.Display;


import java.util.Scanner;

public class Display {
    Scanner scanner = new Scanner(System.in);

    public int startScreen() {
        System.out.println("Welcome to the ATM machine.\n" +
                "---------------------------\n" +
                "Enter number to select feature.\n\n" +
                "1. Login\n" +
                "2. Create Account\n"
        );
        int num = Integer.parseInt(scanner.nextLine());

        if (num != 1 && num != 2) {
            System.out.println("Error, Enter valid number.\n");
            startScreen();
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

    public int displayLoginScreen1() {
        System.out.println("Login\n" +
                "Please enter your account number:");
        return Integer.parseInt(scanner.nextLine());
    }
//

    public String displayLoginScreen2() {
        System.out.println("Please enter your pin");
        return scanner.nextLine();
    }




    public int displayWrongAccountNumber(){
        System.out.println("No account under this number\n" +
                    "1. Type in number again\n" +
                    "2. Create an account");
            return Integer.parseInt(scanner.nextLine());

    }
    public void displayAccountMade(int accountNumber){
        System.out.println("You have successfully created an account\n" +
                "Your account number is: " +accountNumber);
    }

    public void displayMainMenu(){
        System.out.println("You have successfully logged in.\n" +
                "---------------------------\n" +
                "Enter number to select feature.\n\n" +
                "1. Withdraw money\n" +
                "2. Add money\n" +
                "3. Check balance\n" +
                "4. Transfer money\n" +
                "5. Return Card"
        );
    }

    public void displayWrongPin() {
        System.out.println("You have entered the wrong pin");
    }
}
