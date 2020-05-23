package com.company.Display;

import java.util.Scanner;

public class Display {
    Scanner scanner = new Scanner(System.in);
    public void startScreen(){
        System.out.println("Welcome to the ATM machine.\n" +
                "---------------------------\n"+
                        "Enter number to select feature.\n\n" +
                "1. Login\n" +
                "2. Create Account\n"
                );
        int num = Integer.parseInt(scanner.nextLine());
        if(num==1){
            loginScreen();
        }
        else if(num==2){
            CreateAccount();
        }
        else{
            System.out.println("Error, Enter valid number.\n");
            startScreen();
        }

    }

    private void CreateAccount() {
    }

    public void loginScreen(){

    }
}
