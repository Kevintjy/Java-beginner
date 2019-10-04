package main.java.ATM;

import java.io.Serializable;

 public class Credit extends Account implements Serializable {

    //The account type of this account
    String type;
    double limit;


    /*
    Construct a new Credit account
     */
    public Credit(String num, String u) {
        super(num, u);
        type = "Credit";
        limit = 1000.0;

    }


    /*
    Display the account balance.
     */
    @Override
    public double display(){
        return getBalance() * -1;
    }


    /*
    Withdraw money on a given ATM.
     */
    public boolean withdraw(int amount, ATM atm) {
        if (atm.checkAlert() || amount >= 1000 || amount % 5 != 0 || getBalance()- amount < -1*limit) {
            return false;
        } else {
            double m = getBalance() - amount;
            setBalance(m);
            atm.distributeMoney(amount);
            return true;
        }
    }

}

