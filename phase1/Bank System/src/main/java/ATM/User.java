package main.java.ATM;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

<<<<<<< HEAD
public class User implements Serializable {
=======
 public class User implements Serializable {
>>>>>>> 1fbe7848b8001b46a61285ce6fd6461b5ae4cb5d

    // the date of creation of this User
    String date;

    // the identification of User
    private String id;

    // The password of User
    private String password;

    // the name of User
    private String name;

    // all accounts this User has
    public ItAccount account;


    /*
    Construct a new User.
     */
    public User(String name, String id, String password){
        this.name = name;
        this.id = id;
        SimpleDateFormat format = new SimpleDateFormat();
        this.date = format.format(new Date());
        this.password = password;
        this.account = new ItAccount();
    }


    public String getPassword() {
        return password;
    }


    public double getTotalBalance() {
        return account.getTotalBalance();
    }


    /*
    Change password.
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    /*
    Find the User's account with given account number.
     */
    public Account checkAccount(String accountNum) {

        try {
            for (Account i: this.account) {
                if (i.getAccountNum().equals(accountNum)) {
                    return i;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
    Change primary account.
     */
    public void changePrimaryAccount(String accountNum){

        try {
            for (Account i: this.account) {
                if (i.getAccountNum().equals(accountNum)) account.defaultAccount = i;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /*
    Send a request of new account creation.
     */
    public void sendRequest(String accountType){
        Request request = new Request(id, accountType);
        request.recordRequest();
    }

}
