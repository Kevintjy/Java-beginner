package main.java.ATM;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

 public class Linecredit extends Account implements Serializable {

    // the type of the account
    String type;
     double limit;


    /*
    Construct a new LineCredit account.
     */
    public Linecredit(String account, String  u) {
        super(account,u);
        type = "Line Credit";
        limit = 1000.0;
    }


    /*
    Display the balance of the account.
     */
    public double display() {
        return getBalance() * -1;
    }


    /*
    Transfer money.
     */
    public boolean transferMoney(String account,double amount) {
        if(getBalance()- amount < -1*limit){
            return false;
        }
        else {
            Transaction t = new Transaction(account, accountNum, amount);
            t.transfer();
            return true;
        }
    }


    /*
    Pay bill.
     */
    public boolean payBill(String nonuser, double amount) {
        if(getBalance() - amount < -1*limit){
            return false;
        }
        else{
            double m = getBalance() - amount;
            setBalance(m);
            String message = accountNum + " paying " + amount + " to " + nonuser + "\n";
            try {
                FileWriter writer = new FileWriter("data/Outgoing", true);
                writer.write(message);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.lastTransaction = null;
            return true;
        }
    }


    /*
    Withdraw Money
     */
    public boolean withdraw(int amount, ATM atm) {

        // check if it's possible to withdraw
        if(atm.checkAlert() || amount >= 1000 || amount%5 != 0 || getBalance()- amount < -1*limit) {
            return false;
        } else {
            double m = getBalance() - amount;
            setBalance(m);
            atm.distributeMoney(amount);
            return true;
        }
    }

}
