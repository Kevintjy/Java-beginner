package Model;

import main.java.ATM.Account;
import main.java.ATM.Staff;
import main.java.ATM.User;
import Model.Users;

import java.io.*;
import java.util.ArrayList;

 public class Accounts {


     private DataPath dataPath = DataPath.getDataPath();

    // the path of data being stored
    private final String ACCOUNT_FOLDER = dataPath.getPath("Accounts");

    // the list storing all Accounts
    public static ArrayList<Account> accounts = new ArrayList<>();

    public static int nextAccountNum = 1;


    /*
    Load accounts to the list.
     */
    public void loadAccounts() {

        File file = new File(ACCOUNT_FOLDER);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                try {
                    FileInputStream fileIn = new FileInputStream(ACCOUNT_FOLDER + "/" + f.getName());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    Account e = (Account) in.readObject(); // initialize an account
                    addAccount(e); // add the account to its corresponding user
                    accounts.add(e); // add it into the list
                    in.close();
                    fileIn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
    Match the account to its user.
     */
    public void addAccount(Account a){

        for (User u: Users.users){
            if (u.getId().equals(a.userid)){
                for(Account acc:u.account){
                    if (acc.accountNum.equals(a.accountNum)){
                        u.account.removeAccount(acc);
                    }
                }
                u.account.addAccounts(a);
            }
        }
        for (Staff s: Staffs.getStaffs()){
            if (s.getId().equals(a.userid)){
                for(Account acc:s.account){
                    if (acc.accountNum.equals(a.accountNum)){
                        s.account.removeAccount(acc);
                    }
                }
                s.account.addAccounts(a);
            }
        }
        for (String id: a.sharedUserId){
            for (User u: Users.users){
                if (u.getId().equals(id)){
                    for(Account acc:u.account){
                        if (acc.accountNum.equals(a.accountNum)){
                            u.account.removeAccount(acc);
                        }
                    }
                    u.account.addAccounts(a);
                }
            }
            for (Staff s: Staffs.getStaffs()){
                if (s.getId().equals(id)){
                    for(Account acc:s.account){
                        if (acc.accountNum.equals(a.accountNum)){
                            s.account.removeAccount(acc);
                        }
                    }
                    s.account.addAccounts(a);
                }
            }
        }
    }


    /*
    Add an account to the list.
     */
    public static void addNewAccount(Account u){
        accounts.add(u);
    }


    /*
    Record account information in txt file.
     */
    public void writeAccountsBack(){

        for (Account u: accounts){
            try {
                FileOutputStream fileOut = new FileOutputStream(ACCOUNT_FOLDER + "/"+ u.getAccountNum() + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(u);
                out.close();
                fileOut.close();
            }catch(Exception i) {
                i.printStackTrace();
            }
        }
    }


    /*
    Check if the account number already exists.
     */
    public static boolean accountNumExists(String num){

        for(Account a: accounts){
            if(a.getAccountNum().equals(num)){
                return true;
            }
        }
        return false;
    }


    /*
    Find an account when a account number is given.
     */
    public static Account foundAccountNum(String accountNum){

        for (Account account: accounts){
            if (account.getAccountNum().equals(accountNum)) return account;
        }
        return null;
    }

}
