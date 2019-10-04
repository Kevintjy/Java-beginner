package main.java.ATM;

import Model.Users;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    /*
    Construct a new Account with given account number and user id.
     */
<<<<<<< HEAD
     protected Account(String accountNum, String userid){
=======
    public Account(String accountNum, String userid){
>>>>>>> 1fbe7848b8001b46a61285ce6fd6461b5ae4cb5d

        this.accountNum = accountNum;
        SimpleDateFormat format = new SimpleDateFormat();
        this.createdDate = format.format(new Date());
        this.lastTransaction = null;
        this.userid = userid;
    }


    /*
    Return the balance of this account
     */
<<<<<<< HEAD
     protected double getBalance() {
=======
     public double getBalance() {
>>>>>>> 1fbe7848b8001b46a61285ce6fd6461b5ae4cb5d
        return balance;
    }


    /*
    Set the balance of this account.
     */
<<<<<<< HEAD
     protected void setBalance(double amount){
=======
     public void setBalance(double amount){
>>>>>>> 1fbe7848b8001b46a61285ce6fd6461b5ae4cb5d
        this.balance = amount;
    }


    /*
    return the accountNum of this account.
     */
<<<<<<< HEAD
     protected String getAccountNum(){
=======
     public String getAccountNum(){
>>>>>>> 1fbe7848b8001b46a61285ce6fd6461b5ae4cb5d
        return accountNum;
    }


    /*
    Display the account balance.
     */
<<<<<<< HEAD
     protected double display(){
=======
     public double display(){
>>>>>>> 1fbe7848b8001b46a61285ce6fd6461b5ae4cb5d
        return balance;
    }


    /*
    Add money to this account.
     */
<<<<<<< HEAD
     protected void add(double amount){
=======
     public void add(double amount){
>>>>>>> 1fbe7848b8001b46a61285ce6fd6461b5ae4cb5d
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
     public void deposit(int five,int ten,int twenty, int fifty, ATM atm){
        double m = getBalance() + (five*5 + ten*10 + twenty*20 + fifty*50);
        setBalance(m);
        atm.AddMoney(five,ten,twenty,fifty);
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
