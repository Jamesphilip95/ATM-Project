package com.company.Controller;

import com.company.Display.Display;
import com.company.Model.ATM;
import com.company.Model.ATMimp;

public class ATMManager {
Display display = new Display();
ATM atm = new ATMimp();
String[] names;
String pin;
public void startATM(){
   int startType = display.startScreen();
   if(startType==1){
       display.displayLoginScreen();
   }
   else
      names = display.displayEnterName();
      pin = display.displayEnterPin();
      atm.createAccount(names, pin);
      display.displayLoginScreen();
}
}
