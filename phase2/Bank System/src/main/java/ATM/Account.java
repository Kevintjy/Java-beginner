package main.java.ATM;

import Model.Users;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public abstract class Account implements Serializable {

    // balance of the account
    private double balance;

    // created date of the account
    String createdDate;

    // account number
    public String accountNum;

    // last transaction of this account
    public Transaction lastTransaction;

    // user id of this account owner
    public String userid;

    public ArrayList<String> sharedUserId = new ArrayList<>();

    public ArrayList<Transaction> pastTransaction = new ArrayList<>();


    /*
    Construct a new Account with given account number and user id.
     */
    public Account(String accountNum, String userid){

        this.accountNum = accountNum;
        SimpleDateFormat format = new SimpleDateFormat();
        this.createdDate = format.format(new Date());
        this.lastTransaction = null;
        this.userid = userid;
    }


    /*
    Return the balance of this account
     */

     public double getBalance(){
        return balance;
    }


    /*
    Set the balance of this account.
     */
     public void setBalance(double amount){
        this.balance = amount;
    }


    /*
    return the accountNum of this account.
     */
     public String getAccountNum(){
        return accountNum;
    }


    /*
    Display the account balance.
     */
     public double display(){
        return balance;
    }


    /*
    Add money to this account.
     */
     public void add(double amount){
        balance += amount;
    }


    /*
    Subtract money from this account.
     */
     public void loss(double amount){
        balance -= amount;
    }


    /*
    Deposit cash through ATM to this account.
     */
     public void deposit(HashMap<String, Object> saved, ATM atm){
        int five = (int)saved.get("fiveDollar");
        int ten =(int)saved.get("tenDollar");
        int twenty =(int)saved.get("twentyDollar");
        int fifty = (int)saved.get("fiftyDollar");
        double m = getBalance() + (five*5 + ten*10 + twenty*20 + fifty*50);
        setBalance(m);
        atm.AddMoney(saved);
    }

    /*
    Deposit cheque  into this account.
     */
     public void deposit(double amount){
        double m = getBalance() + amount;
        setBalance(m);
    }

     public User getOwner(){
        for (User u : Users.users){
            if (userid.equals(u.getId())){
                return u;
            }
        }
        return null;
    }

    public String getCreatedDate(){
         return createdDate;
    }

    public void shared(String id){
         if(Users.foundUser(id) != null){
             sharedUserId.add(id);
             Users.foundUser(id).account.addAccounts(this);
         }

    }
    @Override
     public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", createdDate='" + createdDate + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", lastTransaction=" + lastTransaction +
                ", userid='" + userid + '\'' +
                '}';
    }
}
