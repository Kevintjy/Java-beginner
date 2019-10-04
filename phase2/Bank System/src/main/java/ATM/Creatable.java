package main.java.ATM;

import Model.Accounts;
import Model.Requests;
import Model.Staffs;
import Model.Users;

public interface Creatable {
    /*
   Create a new account.
    */
    default void createAccount(Request request) {


        Account newAccount;
        String accountNum = Integer.toString(Accounts.nextAccountNum);

        if (request.getAccountType().equals("Chequing")) {
            newAccount = new Chequing(accountNum, request.getUser().getId());
        } else if (request.getAccountType().equals("Saving")) {
            newAccount = new Saving(accountNum, request.getUser().getId());
        } else if (request.getAccountType().equals("Credit")) {
            newAccount = new Credit(accountNum, request.getUser().getId());
        } else if (request.getAccountType().equals("Line Credit")){
            newAccount = new Linecredit(accountNum, request.getUser().getId());
        } else {
            newAccount = new USaccount(accountNum, request.getUser().getId());
        }
        Accounts.nextAccountNum++;
        request.getUser().account.addAccounts(newAccount);
        Accounts.addNewAccount(newAccount);
        if (request.getUser().account.getAccounts().size() == 1) {
            request.getUser().account.defaultAccount = newAccount;
        }
        Requests.requests.remove(request);
    }



    /*
    Create a new User.
     */
    default boolean createUser(String name, String id, String password) {

        if (Users.UserIdExists(id) == null && Staffs.StaffIdExists(id) == null) {
            User user = new User(name, id, password);
            Users.addUser(user);
            return true;
        } else {
            return false;
        }
    }


}
