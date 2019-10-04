package main.java.ATM;

import Model.ATMs;

import java.util.HashMap;

public class ATM {

    // id of the ATM
    public final String id;


    // number of bills in this ATM
    public HashMap<String,Integer> money;


    // the withdrawable status of the the ATM
    public boolean closeWithdraw;


    /*
    Construct a new instance of ATM.
     */
    public ATM(String id) {
        money = new HashMap<>();
        this.id = id;
        money.put("fiftyDollar",0);
        money.put("twentyDollar",0);
        money.put("fiveDollar",0);
        money.put("tenDollar",0);
        closeWithdraw = false;
        ATMs.nonWorkATMs.add(this);
    }


    /*
    Check if this ATM is short for any kinds of bills.
     */
    public boolean checkAlert() {

        if(money.get("fiftyDollar") < 20 || money.get("twentyDollar") <20|| money.get("tenDollar") < 20||
                money.get("fiveDollar")  <20){
            if(!closeWithdraw) {
                closeWithdraw = true;
            }
            ATMs.workATMs.remove(this);
            ATMs.nonWorkATMs.add(this);
            return true;
        } else {
            return false;
        }
    }


    /*
    Calculate what kinds of bills should be given to the user when they withdraw money.
     */
    public void distributeMoney(int amount) {

        while (amount > 0) {
            if (amount > 250) {
                int numFifty = (amount / 50) ;
                int newAmount = money.get("fiftyDollar") - numFifty;
                money.put("fiftyDollar", newAmount);
                amount -= numFifty * 50;

            } else if (amount > 50) {
                int numTwenty = (amount / 20) ;
                int newAmount = money.get("twentyDollar") - numTwenty;
                money.put("twentyDollar", newAmount);
                amount -= numTwenty * 20;

            } else if (amount > 20) {
                int numTen = (amount / 10) ;
                int newAmount = money.get("tenDollar") - numTen;
                money.put("tenDollar", newAmount);
                amount -= numTen * 10;

            } else {
                int numFive = (amount / 5);
                int newAmount = money.get("fiveDollar") - numFive;
                money.put("fiveDollar", newAmount);
                amount -= numFive * 5;
            }
        }
        checkAlert();
    }


    /*
    Add bills to this ATM.
     */
    public void AddMoney(HashMap<String, Object> saved) {
        int amount;
        amount =  money.get("fiveDollar") + (int)saved.get("fiveDollar");
        money.put("fiveDollar",amount);
        amount = money.get("tenDollar") + (int)saved.get("tenDollar");
        money.put("tenDollar",amount);
        amount = money.get("twentyDollar") + (int)saved.get("twentyDollar");
        money.put("twentyDollar",amount);
        amount = money.get("fiftyDollar") + (int)saved.get("fiftyDollar");
        money.put("fiftyDollar",amount);
    }


    public void SetMoney() {

        money.put("fiveDollar",50);
        money.put("tenDollar",50);
        money.put("twentyDollar",50);
        money.put("fiftyDollar",50);
        closeWithdraw = false;
    }


    @Override
     public String toString() {
        return "ATM{" +
                "id='" + id + '\'' +
                ", fiveDollar=" + money.get("fiveDollar") +
                ", fiftyDollar=" + money.get("fiftyDollar") +
                ", twentyDollar=" + money.get("twentyDollar") +
                ", tenDollar=" + money.get("tenDollar") +
                ", closeWithdraw=" + closeWithdraw +
                '}';
    }


    public int getFiveDollar() {
        return money.get("fiftyDollar");
    }


    public int getFiftyDollar() {
        return money.get("fiftyDollar");
    }


    public int getTwentyDollar() {
        return money.get("twentyDollar");
    }


    public int getTenDollar() {
        return money.get("tenDollar");
    }
}
