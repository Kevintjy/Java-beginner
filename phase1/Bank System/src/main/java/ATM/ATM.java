package main.java.ATM;

import Model.ATMs;

public class ATM {

    // id of the ATM
    public final String id;


    // number of five-dollar bills in this ATM
    private int fiveDollar;

    // same as above
    private int fiftyDollar;
    private int twentyDollar;
    private int tenDollar;

    // the withdrawable status of the the ATM
    public boolean closeWithdraw;


    /*
    Construct a new instance of ATM.
     */
    public ATM(String id) {

        this.id = id;
        fiftyDollar = 0;
        twentyDollar = 0;
        tenDollar = 0;
        fiveDollar = 0;
        closeWithdraw = false;
    }


    /*
    Check if this ATM is short for any kinds of bills.
     */
    public boolean checkAlert() {

        if(fiveDollar < 20 || fiftyDollar <20|| twentyDollar < 20|| tenDollar <20){
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

//    private void writeAlert(){
//        int shortFive = 0;
//        int shortTen = 0;
//        int shortTwenty = 0;
//        int shortFifty = 0;
//        if(fiveDollar < 20){
//            shortFive =  20 - fiveDollar;}
//        if(tenDollar < 20){
//            shortTen =  20 - tenDollar;}
//        if(twentyDollar < 20){
//            shortTwenty =  20 - twentyDollar;}
//        if(fiftyDollar < 20){
//            shortFifty =  20 - fiftyDollar;}
//        Alert a = new Alert(this, shortFive,shortTen,shortTwenty,shortFifty);
//        Manager.alertList.add(a);
//    }


    /*
    Calculate what kinds of bills should be given to the user when they withdraw money.
     */
    public void distributeMoney(int amount) {

        while (amount > 0) {
            if (amount > 250) {
                int numFifty = (amount / 50) - 4;
                fiftyDollar -= numFifty;
                amount -= numFifty * 50;

            } else if (amount > 50) {
                int numTwenty = (amount / 20) - 2;
                twentyDollar -= numTwenty;
                amount -= numTwenty * 20;

            } else if (amount > 20) {
                int numTen = (amount / 10) - 1;
                tenDollar -= numTen;
                amount -= numTen * 10;

            } else {
                int numFive = amount / 5;
                fiveDollar -= numFive;
                amount -= numFive * 5;
            }
        }
        checkAlert();
    }


    /*
    Add bills to this ATM.
     */
    public void AddMoney(int numFive, int numTen, int numTwenty, int numFifty) {

        fiveDollar += numFive;
        tenDollar += numTen;
        twentyDollar += numTwenty;
        fiftyDollar += numFifty;
    }


    public void SetMoney() {

        fiveDollar = 50;
        tenDollar = 50;
        twentyDollar = 50;
        fiftyDollar = 50;
    }


    @Override
     public String toString() {
        return "ATM{" +
                "id='" + id + '\'' +
                ", fiveDollar=" + fiveDollar +
                ", fiftyDollar=" + fiftyDollar +
                ", twentyDollar=" + twentyDollar +
                ", tenDollar=" + tenDollar +
                ", closeWithdraw=" + closeWithdraw +
                '}';
    }


    public int getFiveDollar() {
        return fiveDollar;
    }


    public int getFiftyDollar() {
        return fiftyDollar;
    }


    public int getTwentyDollar() {
        return twentyDollar;
    }


    public int getTenDollar() {
        return tenDollar;
    }
}
