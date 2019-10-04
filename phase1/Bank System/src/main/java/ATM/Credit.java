package main.java.ATM;

import java.io.Serializable;

 public class Credit extends Account implements Serializable {

    //The account type of this account
    String type;


    /*
    Construct a new Credit account
     */
    public Credit(String num, String u) {
        super(num, u);
        type = "Credit";

    }


    /*
    Display the account balance.
     */
    @Override
<<<<<<< HEAD
     protected double display(){
=======
    public double display(){
>>>>>>> 1fbe7848b8001b46a61285ce6fd6461b5ae4cb5d
        return getBalance() * -1;
    }


    /*
    Withdraw money on a given ATM.
     */
    public boolean withdraw(int amount, ATM atm) {
        if (atm.checkAlert() || amount >= 1000 || amount % 5 != 0) {
            return false;
        } else {
            double m = getBalance() - amount;
            setBalance(m);
            atm.distributeMoney(amount);
            return true;
        }
    }

}

