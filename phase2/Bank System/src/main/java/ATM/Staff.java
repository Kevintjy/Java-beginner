package main.java.ATM;

import Model.Accounts;
import Model.Staffs;

import java.io.Serializable;


public class Staff extends User implements Creatable, Serializable {
    /*
    Construct a new Staff. The new Staff has one account for each type by the time of initialization.
     */
    public Staff(String name, String id, String password){
        super(name, id, password);

//        Account chequing = new Chequing(Integer.toString(Accounts.nextAccountNum) ,id);
//        Accounts.addNewAccount(chequing);
//        Accounts.nextAccountNum++;
//
//        Account saving = new Saving(Integer.toString(Accounts.nextAccountNum) ,id);
//        Accounts.addNewAccount(saving);
//        Accounts.nextAccountNum++;
//
//        Account credit = new Credit(Integer.toString(Accounts.nextAccountNum) ,id);
//        Accounts.addNewAccount(credit);
//        Accounts.nextAccountNum++;
//
//        Account lineCredit = new Linecredit(Integer.toString(Accounts.nextAccountNum) ,id);
//        Accounts.addNewAccount(lineCredit);
//        Accounts.nextAccountNum++;
//
//        Account UsAccount = new USaccount(Integer.toString(Accounts.nextAccountNum), id);
//        Accounts.addNewAccount(UsAccount);
//        Accounts.nextAccountNum++;
//
//        Staffs.staffs.add(this);
    }

}
