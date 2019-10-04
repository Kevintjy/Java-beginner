package main.java.ATM;

import Model.Accounts;

import java.io.Serializable;

 public class Transaction implements Serializable {

    // the recipient's Account.
    private Account transIn;

    // the sender's Account
    private Account transOut;

    // the amount of money that has been transferred
    private double amount;


    /*
    Construct a new Transaction.
     */
    public Transaction(String in, String out, double amount) {

        this.transIn = Accounts.foundAccountNum(in);
        this.transOut = Accounts.foundAccountNum(out);
        this.amount = amount;

        if ((this.transIn != null) && (this.transOut != null)) {
            this.transIn.lastTransaction = this;
            this.transOut.lastTransaction = this;
        }
    }


    /*
    Transfer money.
     */
    public void transfer() {
        transIn.add(amount);
        transOut.loss(amount);
    }


    /*
    Undo the most recent transaction.
    */
    public boolean undoTrans() {
        if (this.transIn.getBalance() >= this.amount || this.transIn instanceof Credit
                || this.transIn instanceof Linecredit) {

            // exchange the the position of the sender and the recipient.
            Transaction a = new Transaction(transOut.accountNum, transIn.accountNum, amount);

            // update the lastTransaction
            this.transIn.lastTransaction = a;
            this.transOut.lastTransaction = a;
            a.transfer();
            return true;
        } else {
            return false;
        }
    }


    @Override
     public String toString() {
        return "Transaction{" +
                "transIn=" + transIn +
                ", transOut=" + transOut +
                ", amount=" + amount +
                '}';
    }
}

