package main.java.ATM;

import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

 public class Chequing extends Account implements Serializable {

    // the type of the account
    String type;


    /*
    Construct a new Chequing account.
     */
    public Chequing(String num, String  u) {
        super(num, u);
        this.type = "Chequing";
    }


    /*
    Transfer money to a given account.
     */
    public boolean transferMoney(String account, double amount) {

        if (getBalance() > 0 && getBalance()- amount >= -100) {
            Transaction t = new Transaction(account, accountNum, amount); //create a new Transaction
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

        if (getBalance() > 0 && getBalance()- amount >= -100) {
            double m = getBalance() - amount;
            setBalance(m);
            String message = accountNum + " paying "+ amount + " to " + nonuser + "\n";
            try {
                FileWriter writer = new FileWriter("data/Outgoing", true);
                writer.write(message);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.lastTransaction = null;
            return true;
        } else {
            return false;
        }
    }


    /*
    Withdraw money on a given ATM.
     */
    public boolean withdraw(int amount, ATM atm) {
        if (atm.checkAlert() || amount >= 1000 || amount % 5 != 0 || getBalance()- amount < -100) {
            return false;
        } else {
            double m = getBalance() - amount;
            setBalance(m);
            atm.distributeMoney(amount);
            return true;
        }
    }

}
