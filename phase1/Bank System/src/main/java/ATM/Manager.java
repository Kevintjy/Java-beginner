package main.java.ATM;


import Model.Users;
import Model.ATMs;
import Model.Accounts;
import Model.Requests;

import java.text.SimpleDateFormat;
import java.util.Date;

 public class Manager {

    // the date of creation of this the Manager
    public String date;

    // The identification of the Manager
    private String id;

    // the password of the Manager
    private String password;

    // the name of the Manager
    private String name;


    /*
    Construct a new Manager.
     */
    public Manager(String name, String id, String password) {

        this.name = name;
        this.id = id;
        SimpleDateFormat format = new SimpleDateFormat();
        this.date = format.format(new Date());
        this.password = password;
    }


    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }


    public String getId() {
        return id;
    }


    /*
    Change password of the Manager.
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }


    /*
    Create a new account.
     */
    public boolean createAccount(String accountNum) {

        if (Requests.requests.size() > 0) {
            Request request = Requests.requests.get(0);
            Account newAccount;

            if (! Accounts.accountNumExists(accountNum)) {
                if (request.getAccountType().equals("Chequing")) {
                    newAccount = new Chequing(accountNum, request.getUser().getId());
                } else if (request.getAccountType().equals("Saving")) {
                    newAccount = new Saving(accountNum, request.getUser().getId());
                } else if(request.getAccountType().equals("Credit")) {
                    newAccount = new Credit(accountNum, request.getUser().getId());
                } else {
                    newAccount = new Linecredit(accountNum, request.getUser().getId());
                }
                request.getUser().account.addAccounts(newAccount);
                Accounts.addNewAccount(newAccount);
                if (request.getUser().account.getAccounts().size() == 1) {
                    request.getUser().account.defaultAccount = newAccount;
                }
                Requests.requests.remove(0);
                return true;
            }

        } else {
                System.out.println("fail to create account, enter a new account num");
                return false;
        }

        return false;
    }


    /*
    Create a new User.
     */
    public boolean createUser(String name, String id, String password) {

        if (Users.UserIdExists(id) == null) {
            User user = new User(name, id, password);
            Users.addUser(user);
            return true;
        } else {
            return false;
        }
    }


    /*
    Undo the most transaction of an account.
     */
    public boolean undoTransaction(Account account) {

        if (account.lastTransaction != null) {
            return account.lastTransaction.undoTrans();
        }
        return false;
    }


    /*
    Add bills into a given atm.
     */
    public void managerAddMoney(ATM atm) {

        atm.SetMoney();
        atm.closeWithdraw = false;
        ATMs.nonWorkATMs.remove(atm);
        ATMs.workATMs.add(atm);
    }

//     static void main(String[] args) {
//        Users users = new Users();
//        Accounts accounts = new Accounts();
//        ATMs atms = new ATMs();
//        Managers managers = new Managers();
//        managers.loadManager();
//        atms.loadATMs();
//        Manager manager = new Manager("AAA","001","123");
//        managers.managers.add(manager);
//        manager.createUser("Kevin", "1", "123");
//        manager.createUser("Alvin", "2", "000");
//        User u = users.login("1", "123");
//        ATM atm = ATMs.workATMs.get(0);
//
//
//        //Test Kit 1. (Regular Transaction)
//        User a = users.login("2","000");
//        u.sendRequest("Chequing");
//        manager.createAccount("1");
//        a.sendRequest("Credit");
//        manager.createAccount("2");
//        Account account1 = Accounts.foundAccountNum("1");
//        Account account2 = Accounts.foundAccountNum("2");
//        account1.add(800);
//        ((Chequing)account1).transferMoney("2",50.0);
//        if (account2.getBalance() == -50.0) System.out.println("Transfer Money Successful.");
//        account1.deposit(0,0,0,10, atm);
//        ((Chequing)account1).transferMoney("2", 500.0);
//        if (manager.undoTransaction(account1)) System.out.println("Transfer Money Successful");
//        ((Chequing) account1).withdraw(625, atm);
//        if (!manager.undoTransaction(account1)) System.out.println("UndoTransfer Unsuccessful");
//        account1.deposit(0,0,0,10, atm);
//        ((Chequing) account1).payBill("Bell Inc.", 100.00);
//        if (!manager.undoTransaction(account1)) System.out.println("UndoTransfer Unsuccessful");
//
//
//        //Test Kit 2. (Regular withdraw.) not complete yet...
////        u.sendRequest("Chequing");
////        u.sendRequest("Saving");
////        u.sendRequest("Credit");
////        manager.createAccount("1");
////        manager.createAccount("2");
////        manager.createAccount("3");
////        Account account1 = Accounts.foundAccountNum("1");
////        Account account2 = Accounts.foundAccountNum("2");
////        Account account3 = Accounts.foundAccountNum("3");
////        if (u.getTotalBalance() == 0) System.out.println("user initialized.");
////        account1.add(500);
////        account2.add(500);
////        account3.add(500);
////        ((Chequing)account1).withdraw(207, atm); //Fail
////        ((Chequing)account1).withdraw(505, atm); //Success.
////        ((Chequing)account1).withdraw(50, atm); //Fail.
////        ((Saving)account2).withdraw(500,atm); //Fail.
////        ((Credit)account3).withdraw(500,atm); //Fail.
////        manager.managerAddMoney(atm,0,0,0,20);
////        ((Credit)account3).withdraw(1000, atm); //Fail.
////        ((Saving)account2).withdraw(500,atm); //Fail.
//
//
//
//
//
//
//        atms.writeBackToFile();
//        users.writeUsersBack();
//        accounts.writeAccountsBack();
//        managers.writeBackToFile();
//
//    }
}
