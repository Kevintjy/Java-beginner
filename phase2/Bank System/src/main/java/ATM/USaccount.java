package main.java.ATM;

import Model.Accounts;
import Model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class USaccount extends Account implements Serializable {

    //The account type of this account
    String type;

    /*
   Construct a new Credit account
    */
    public USaccount(String num, String u){
        super(num, u);
        type = "US Account";

    }

    public double display(){
        return getBalance()*CurrencyConverter.convert(Currency.CAD,Currency.USD);
    }

    public boolean transferMoney(String account, double amount) {
        double US = getBalance() * CurrencyConverter.convert(Currency.CAD, Currency.USD);
        double CAD = amount * CurrencyConverter.convert(Currency.USD, Currency.CAD);
        if (US > 0 && US - amount >= 0) {
            Transaction t = new Transaction(account, accountNum, CAD); //create a new Transaction
            t.transfer();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + display()+"US$" +
                ", createdDate='" + createdDate + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }

    public static void main(String[] args) {
        USaccount a = new USaccount("1","11");
        USaccount S = new USaccount("2","11");
        Chequing C = new Chequing("3","11");
        User U = new User("haha","11","1234");
        Users.addUser(U);
        U.account.addAccounts(a);
        U.account.addAccounts(S);
        U.account.addAccounts(C);
        C.setBalance(1000);
        C.transferMoney("1",700);
        a.transferMoney("2", 746);
        S.transferMoney("3",745);
        System.out.println(C.display());
        System.out.println(a.display());
        System.out.println(S.display());
        System.out.println(a);
        System.out.println(S);
        System.out.println(C);
    }
}
