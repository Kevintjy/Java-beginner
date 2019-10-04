package main.java.ATM;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

 public class Saving extends Account implements Serializable {

    // the type of the Account
    String type;

    // the interest rate
    private static final double rate = 1.001;


    /*
    Construct a new Saving Account.
     */
    public Saving(String account, String u) {
        super(account, u);
        type = "Saving";
    }


    /*
    Transfer money into a given account.
     */
    public boolean transferMoney(String account, double amount) {

        if ( getBalance() > 0 && getBalance()- amount >= 0) {
            Transaction t = new Transaction(account, accountNum, amount);
            t.transfer();
            return true;
        } else {
            return false;
        }
    }


    /*
    Pay bill.
     */
    public boolean payBill(String nonuser, double amount) {

        if (getBalance() > 0 && getBalance() - amount >= 0) {
            double m = getBalance() - amount;
            setBalance(m);

            // record the outgoing transaction to the file
            String message = accountNum + " paying " + amount + " to " + nonuser + "\n";
            try {
                FileWriter writer = new FileWriter("data/Outgoing", true);
                writer.write(message);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }


    /*
    Withdraw money on a given ATM.
     */
    public boolean withdraw(int amount, ATM atm) {

        if(atm.checkAlert() || amount >= 1000 || amount%5 != 0 || getBalance() - amount < 0) {
            return false;
        } else {
            double m = getBalance() - amount;
            setBalance(m);
            atm.distributeMoney(amount);
            return true;
        }
    }


    /*
    Pay interest to the Saving Account.
     */
    public void MonthlyIncrease(){
        this.setBalance(this.getBalance() * rate);
    }

}
