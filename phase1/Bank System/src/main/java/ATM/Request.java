package main.java.ATM;

import Model.Requests;
import Model.Users;

public class Request {

    // the user who sends the account creation request
    private User user;

    // the type of account the user request to create
    private String accountType;


    /*
    Construct a new Request.
     */
    public Request(String userId, String accountType) {
        this.user = Users.UserIdExists(userId);
        this.accountType = accountType;
    }


    /*
    Record the Request.
     */
    public void recordRequest() {
        Requests.addRequest(this);
    }


    public User getUser() {
        return user;
    }


    public String getAccountType() {
        return accountType;
    }


    @Override
     public String toString() {
        return "request is from " + user.getName() +
                "(user id: "+ user.getId() +
                ") for opening '" + accountType + " Account' \n";
    }
}
