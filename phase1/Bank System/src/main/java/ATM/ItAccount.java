package main.java.ATM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ItAccount implements Iterable<Account>, Serializable {

    // the list storing all current Accounts of a User
    private ArrayList<Account> accounts = new ArrayList<>();

    Account defaultAccount = null;


    /*
    Add Account to the ArrayList.
     */
    public void addAccounts(Account a) {
        accounts.add(a);
    }
    public void removeAccount(Account a){accounts.remove(a);}

    /*
    Get total balance of a user's accounts.
     */
    public double getTotalBalance() {
        double total = 0;
        for (Account a: this){
            total += a.getBalance();
        }
        return total;
    }


    public ArrayList<Account> getAccounts() {
        return accounts;
    }


    @Override
    public Iterator<Account> iterator() {
        return new accountIterator();
    }


    private class accountIterator implements Iterator<Account> {

        private int next;


        @Override
        public boolean hasNext() {
            return next < accounts.size();
        }


        @Override
        public Account next() {
            if (hasNext()) {
                return accounts.get(next++);
            }
            throw new NoSuchElementException();
        }

    }

}
