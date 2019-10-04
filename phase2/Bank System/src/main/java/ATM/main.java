//package main.java.ATM;
//
//import Model.*;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Scanner;
//
//public class main {
//    private Scanner in = new Scanner(System.in);
//    private Users users = new Users();
//    private Accounts accounts = new Accounts();
//    private ATMs atms = new ATMs();
//    private Managers managers = new Managers();
//    private Requests requests = new Requests();
//    private Deposits deposits = new Deposits();
//
//    public void toInitial(){
//        managers.loadManager();
//        atms.loadATMs();
//        users.loadUsers();
//        accounts.loadAccounts();
//        requests.loadRequest();
//        deposits.loadDeposits();
//        update_saving();
//    }
//
//    public void eventExit(){
//        users.writeUsersBack();
//        managers.writeBackToFile();
//        atms.writeBackToFile();
//        accounts.writeAccountsBack();
//        requests.writeBackToFile();
//    }
//
//    public void update_saving() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String date = format.format(new Date());
//        String year = date.split("-")[0];
//        String month = date.split("-")[1];
//        String day = date.split("-")[2];
//        if (day.equals("01")){
//            try {
//                BufferedReader in = new BufferedReader(new FileReader("data/config_file"));
//                String line = in.readLine();
//                String configYear = line.split("-")[0];
//                String configMonth = line.split("-")[1];
//                in.close();
//                if ((configYear.equals(year) && Integer.parseInt(month) == Integer.parseInt(configMonth) + 1) ||
//                        ( Integer.parseInt(year) == Integer.parseInt(configYear) + 1
//                                && Integer.parseInt(configMonth) == 12 && Integer.parseInt(month) == 1)){
//                    try{
//                        FileWriter writer = new FileWriter("data/config_file");
//                        writer.write(date);
//                        writer.close();}
//                    catch (Exception e){e.printStackTrace();}
//                    for (Account a: Accounts.accounts){
//                        if (a instanceof Saving){
//                            ((Saving) a).MonthlyIncrease();
//                        }
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }}
//    }
//
//
//
//    public void mainFirstPage(){
//        System.out.println("enter a number\n");
//        System.out.println("1. I am an user \n");
//        System.out.println("2. I am an manager \n");
//        String c = in.nextLine();
//        if (c.equals("1")){
//            userLogin();
//        }
//        else if (c.equals("2")){
//            managerLogin();
//        }
//        else{
//            System.out.println("I cannot read, try again");
//            mainFirstPage();
//        }
//    }
//
//    public void userLogin(){
//        System.out.println("id:\n");
//        String id = in.nextLine();
//        System.out.println("password: \n");
//        String password = in.nextLine();
//        if (users.login(id, password) != null){
//            System.out.println("login in successfully\n");
//            UserFirstPage((users.login(id, password)));
//        }
//        else{
//            System.out.println("fail to login\nback to main page\n");
//            mainFirstPage();
//        }
//
//    }
//
//    public void managerLogin(){
//        System.out.println("id:\n");
//        String id = in.nextLine();
//        System.out.println("password: \n");
//        String password = in.nextLine();
//        if (managers.login(id, password) != null){
//            System.out.println("login in successfully\n");
//            ManagerFirstPage((managers.login(id, password)));
//        }
//        else{
//            System.out.println("fail to login\nback to main page\n");
//            mainFirstPage();
//        }
//
//    }
//    public void ManagerFirstPage(Manager m){
//        System.out.println("enter a num\n");
//        System.out.println("1.create a user\n");
//        System.out.println("2.check requests\n");
//        System.out.println("3.check ATMs\n");
//        System.out.println("4.undo a transaction\n");
//        System.out.println("5.change password\n");
//        System.out.println("enter [out] to sign out.\n");
//        System.out.println("enter [shut down] to shut down the system\n");
//        String n = in.nextLine();
//        if (n.equals("1")){
//            createUserPage(m);
//        }
//        else if (n.equals("2")){
//            checkRequestPage(m);
//        }
//        else if (n.equals("3")){
//            checkATMPage(m);
//        }
//        else if (n.equals("4")){
//            UndoTransactionPage(m);
//        }
//        else if (n.equals("5")){
//            changeManagerPasswordPage(m);
//        }
//        else if (n.equals("out")){
//            mainFirstPage();
//        }
//        else if (n.equals("shut down")){
//            eventExit();
//        }
//        else{
//            ManagerFirstPage(m);
//        }
//    }
//
//    public void changeManagerPasswordPage(Manager m) {
//        System.out.println("please enter [1] if you wish to change password:");
//        if (!in.nextLine().equals("1")) {
//            System.out.println("Redirecting to Manager Main Page. \n");
//            ManagerFirstPage(m);
//        } else {
//            while (true) {
//                boolean passwordFormat = true;
//                System.out.println("please enter the new Password, Length between 4 to 6 letters, integers only.\n");
//                String newPassword = in.nextLine();
//                try {
//                    Integer.parseInt(newPassword);
//                } catch (NumberFormatException e) {
//                    passwordFormat = false;
//                }
//                System.out.println("please enter the new Password again\n");
//                String confirmPassword = in.nextLine();
//                if (newPassword.equals(confirmPassword) && passwordFormat) {
//                    m.changePassword(newPassword);
//                    System.out.println("Your password has changed successfully. \n");
//                    break;
//                } else if (!newPassword.equals(confirmPassword)) {
//                    System.out.println("You entered two different password. \n");
//                } else {
//                    System.out.println("password format incorrect, please try again.\n");
//                }
//            }
//        }
//        ManagerFirstPage(m);
//    }
//
//    public void UndoTransactionPage(Manager m){
//        System.out.println("enter an account number");
//        String k = in.nextLine();
//        Account acc = Accounts.foundAccountNum(k);
//        if(acc == null){
//            System.out.println("not a valid account");
//            ManagerFirstPage(m);
//        }
//        else{
//            System.out.println("enter the password by user");
//            String pass = in.nextLine();
//            if (users.login(acc.getOwner().getId(), pass) != null){
//                System.out.println("undo the last transaction" + acc.lastTransaction + "? \n [Y/N]" );
//                String s = in.nextLine();
//                if (s.equals("Y")){
//                    if(m.undoTransaction(acc.lastTransaction)){
//                        System.out.println("undo last transaction success");
//                        ManagerFirstPage(m);
//                    }
//                    else{
//                        System.out.println("can not undo the last transaction");
//                        ManagerFirstPage(m);
//                    }}
//                else{
//                    ManagerFirstPage(m);
//                }
//            }
//            else{
//                System.out.println("the password is wrong \n");
//                ManagerFirstPage(m);
//
//            }
//        }
//    }
//
//
//
//    public void checkATMPage(Manager m){
//        if(ATMs.nonWorkATMs.size() == 0){
//            System.out.println("All ATM works");
//            ManagerFirstPage(m);
//        }
//        else{
//            for (int i = 0; i < ATMs.nonWorkATMs.size(); i++){
//                System.out.println( ATMs.nonWorkATMs.get(i));
//            }
//            System.out.println("choose the ATM id to add");
//            String k = in.nextLine();
//            if (ATMs.findATM(k) != null){
//                ATM a = ATMs.findATM(k);
//                m.managerAddMoney(a);
//                System.out.println("money add to " + a);
//                ManagerFirstPage(m);
//            }
//            else{
//                System.out.println("invalid ATM id\n");
//                System.out.println("fail to add, back to your main page");
//                ManagerFirstPage(m);
//            }
//        }
//    }
//
//    public void createUserPage(Manager m){
//        System.out.println("enter a user name\n");
//        String k = in.nextLine();
//        System.out.println("enter an user id\n");
//        String n = in.nextLine();
//        if(m.createUser(k, n, "1234")){
//            System.out.println("user created\n");
//            ManagerFirstPage(m);
//        }
//        else{
//            System.out.println("fail to create User, enter a new id");
//            System.out.println("try again [T] or exit [E]\n");
//            String a = in.nextLine();
//            if(a.equals("T")){
//                createUserPage(m);
//            }
//            else{
//                ManagerFirstPage(m);
//            }
//        }
//    }
//
//    public void checkRequestPage(Manager m){
//        if(Requests.requests.size() == 0){
//            System.out.println("There is no request \n");
//            ManagerFirstPage(m);
//        }
//        else{
//            System.out.println(Requests.requests.get(0));
//            System.out.println("create account ? [Y/N]");
//            String a = in.nextLine();
//            if (a.equals("Y")){
//                System.out.println("enter an account number");
//                String k = in.nextLine();
//                if(m.createAccount(k)){
//                    System.out.println("account created");
//                    ManagerFirstPage(m);
//                }
//                else{
//                    System.out.println("fail to create account, enter a new account number");
//                    System.out.println("try again [T] or exit [E]\n");
//                    String b = in.nextLine();
//                    if(b.equals("T")){
//                        checkRequestPage(m);
//                    }
//                    if(b.equals("E")){
//                        ManagerFirstPage(m);
//                    }
//                }
//            }
//            else{
//                System.out.println("request rejected");
//                Requests.requests.remove(0);
//                ManagerFirstPage(m);
//            }
//        }
//    }
//
//    public void UserFirstPage(User u){
//        System.out.println("enter a num\n");
//        System.out.println("1.choose account\n");
//        System.out.println("2.send request\n");
//        System.out.println("3.change password\n");
//        System.out.println("4.totalBalance\n");
//        System.out.println("5.change primary account\n");
//        System.out.println("enter [out] if you want to sign out.\n");
//
//        String n;
//        while(true){
//            n = in.nextLine();
//            if (n.equals("1")|| n.equals("2")|| n.equals("3") || n.equals("4") ||
//                    n.equals("5")|| n.equals("out")){
//                break;
//            } else{
//                System.out.println("invalid input, please try again.\n ");
//                System.out.println("1.choose account\n");
//                System.out.println("2.send request\n");
//                System.out.println("3.change password\n");
//                System.out.println("4.totalBalance\n");
//                System.out.println("5.change primary account\n");
//            }
//        }
//
//
//        if (n.equals("out")){
//            System.out.println("User " + u.getId() + " signed out successfully.");
//            mainFirstPage();
//        }
//        else if (n.equals("1")){
//            choosePageOne(u);
//        }
//        else if(n.equals("2")){
//            sendRequestPage(u);
//        }
//        else if(n.equals("3")){
//            changePasswordPage(u);
//        }
//        else if(n.equals("4")){
//            totalBalancePage(u);
//        }
//        else{
//            changePrimaryPage(u);
//        }
//    }
//
//    public void choosePageOne(User u){
//        System.out.println("you have these accounts\n");
//        for (Account a:u.account){
//            System.out.println(a.getAccountNum() + "\n");
//        }
//        System.out.println("\n");
//        System.out.println("enter your account number to choose your account\n");
//        String n = in.nextLine();
//        for (Account a:u.account){
//            if (a.getAccountNum().equals(n)){
//                if(a instanceof  Saving || a instanceof Chequing){
//                    DebtPage(a);
//                }
//                else{
//                    CreditPage(a);
//                }
//                return;
//            }
//        }
//        System.out.println("You enter an wrong account number\n");
//        System.out.println("enter 'try' to try again\n");
//        System.out.println("enter 'out' to login out\n");
//        String m = in.nextLine();
//        if (m.equals("try")){
//            choosePageOne(u);
//        }
//        else if(m.equals("out")){
//            mainFirstPage();
//        }
//        else {
//            System.out.println("cannot read\n");
//            choosePageOne(u);
//        }
//
//    }
//
//    public void sendRequestPage(User u){
//        System.out.println("enter the number represented the type of account you with to open \n");
//        System.out.println("1. Chequing Account");
//        System.out.println("2. Saving Account");
//        System.out.println("3. Credit Account");
//        System.out.println("4. Line Credit Account \n");
//
//        String entry;
//        while(true){
//            entry = in.nextLine();
//            if (entry.equals("1")||entry.equals("2")||entry.equals("3")||entry.equals("4")){
//                break;
//            } else{
//                System.out.println("invalid input, please try again \n");
//                System.out.println("enter the number represented the type of account you with to open \n");
//                System.out.println("1. Chequing Account");
//                System.out.println("2. Saving Account");
//                System.out.println("3. Credit Account");
//                System.out.println("4. Line Credit Account \n");
//            }
//        }
//
//        if (entry.equals("1")){
//            u.sendRequest("Chequing");
//            System.out.println("Chequing account creation request sent. \n");
//        }
//        else if (entry.equals("2")){
//            u.sendRequest("Saving");
//            System.out.println("Saving account creation request sent. \n");
//        }
//        else if (entry.equals("3")){
//            u.sendRequest("Credit");
//            System.out.println("Credit account creation request sent. \n");
//        }
//        else {
//            u.sendRequest("Linecredit");
//            System.out.println("Line Credit account creation request sent. \n");
//        }
//
//        UserFirstPage(u);
//    }
//
//    public void changePasswordPage(User u){
//        System.out.println("please enter [1] if you wish to change password:");
//        if (!in.nextLine().equals("1")){
//            System.out.println("Redirecting to User Main Page. \n");
//            UserFirstPage(u);
//        }
//        else{
//            while(true){
//                boolean passwordFormat = true;
//                System.out.println("please enter the new Password, Length between 4 to 6 letters, integers only.\n");
//                String newPassword = in.nextLine();
//                try{
//                    Integer.parseInt(newPassword);
//                }catch (NumberFormatException e){
//                    passwordFormat = false;
//                }
//                System.out.println("please enter the new Password again\n");
//                String confirmPassword = in.nextLine();
//                if(newPassword.equals(confirmPassword) && passwordFormat){
//                    u.changePassword(newPassword);
//                    System.out.println("Your password has changed successfully. \n");
//                    break;
//                }
//                else if(!newPassword.equals(confirmPassword)) {
//                    System.out.println("You entered two different password. \n");
//                }else {
//                    System.out.println("password format incorrect, please try again.\n");
//                }
//            }
//        }
//
//        UserFirstPage(u);
//    }
//
//
//    public void totalBalancePage(User u){
//        System.out.println("please enter [1] if you wish to see total balance:");
//        System.out.println("enter other keys if you want to go back. \n");
//        if (in.nextLine().equals("1")) {
//            System.out.println("User " + u.getName() + ": \n" +
//                    "Your total balance is " + u.getTotalBalance() + ". \n");
//        }
//        UserFirstPage(u);
//    }
//
//    public void changePrimaryPage(User u){
//        System.out.println("Your current primary account is" + u.account.defaultAccount);
//        if (u.account.getAccounts().size() == 1) {
//            System.out.println("You only have one account. " +
//                    "you do not have the option to switch your primary account to other account. \n");
//            UserFirstPage(u);
//        }
//        else {
//            System.out.println("please enter [1] if you want to proceed.");
//            System.out.println("enter other keys if you want to go back. \n");
//            if(!in.nextLine().equals("1")){
//                UserFirstPage(u);
//            }
//            else {
//                System.out.println("You have these accounts.");
//                int count = 1;
//                for (Account account: u.account.getAccounts()){
//                    System.out.println(count + ". " + account.accountNum);
//                    count++;
//                }
//
//                System.out.println("\nplease choose the account you want to set as primary account.");
//                System.out.println("please enter the number:\n");
//
//                int select;
//                while (true){
//                    select = Integer.parseInt(in.nextLine());
//                    if(select > 0 && select < count){
//                        break;
//                    }
//                    else {
//                        System.out.println("Invalid input, please try again \n");
//                        System.out.println("You have these accounts.");
//                        count = 1;
//                        for (Account account: u.account.getAccounts()){
//                            System.out.println(count + ". " + account.accountNum);
//                            count++;
//                        }
//
//                        System.out.println("\nplease choose the account you want to set as primary account.");
//                        System.out.println("please enter the number:\n");
//
//                    }
//                }
//                u.account.defaultAccount = u.account.getAccounts().get(select - 1);
//                System.out.println("Your primary account has been changed successfully.\n");
//                UserFirstPage(u);
//            }
//        }
//    }
//
//
//    public void CreditPage(Account a){
//        System.out.println("enter an number\n");
//        System.out.println("1.check account's information\n");
//        System.out.println("2.withdraw\n");
//        System.out.println("3.deposit\n");
//
//        String m;
//        while(true){
//            m = in.nextLine();
//            if (m.equals("1")||m.equals("2")||m.equals("3")){
//                break;
//            }else{
//                System.out.println("invalid input, please try again\n");
//                System.out.println("enter an number\n");
//                System.out.println("1.check account's information\n");
//                System.out.println("2.withdraw\n");
//                System.out.println("3.deposit\n");
//            }
//        }
//
//        if (m.equals("1")){
//            AccountInfoPage(a);
//        }
//        else if(m.equals("2")){
//            withdrawPage(a);
//        }
//        else{
//            depositPage(a);
//        }
//
//        UserFirstPage(a.getOwner());
//
//    }
//
//    public void DebtPage(Account a){
//        System.out.println("enter an number\n");
//        System.out.println("1.check account's information\n");
//        System.out.println("2.withdraw\n");
//        System.out.println("3.deposit\n");
//        System.out.println("4.pay bill\n");
//        System.out.println("5.transfer\n");
//
//        String m;
//        while(true){
//            m = in.nextLine();
//            if (m.equals("1")||m.equals("2")||m.equals("3")||m.equals("4")||m.equals("5")){
//                break;
//            }else{
//                System.out.println("invalid input, please try again\n");
//                System.out.println("enter an number\n");
//                System.out.println("1.check account's information\n");
//                System.out.println("2.withdraw\n");
//                System.out.println("3.deposit\n");
//                System.out.println("4.pay bill\n");
//                System.out.println("5.transfer\n");
//            }
//        }
//
//        if (m.equals("1")){
//            AccountInfoPage(a);
//        }
//        else if(m.equals("2")){
//            withdrawPage(a);
//        }
//        else if(m.equals("3")){
//            depositPage(a);
//        }
//        else if(m.equals("4")){
//            payBillPage(a);
//        }
//        else{
//            transferPage(a);
//        }
//
//        UserFirstPage(a.getOwner());
//
//    }
//
//    public void AccountInfoPage(Account a){
//        System.out.println("this is your account information\n");
//        System.out.println(a+"\n\n");
//        System.out.println("enter 'a' to enter an account number\n");
//        System.out.println("enter 'u' to go to user page\n");
//        System.out.println("enter 'out' to log out");
//        String m;
//        while (true){
//            m = in.nextLine();
//            if (m.equals("a") || m.equals("u") || m.equals("out")){
//                break;
//            }
//            else{
//                System.out.println("invalid input, please try again.\n");
//                System.out.println("this is your account information\n");
//                System.out.println(a+"\n\n");
//                System.out.println("enter 'a' to enter an account number\n");
//                System.out.println("enter 'u' to go to user page\n");
//                System.out.println("enter 'out' to log out");
//            }
//        }
//        if (m.equals("a")){
//            choosePageOne(a.getOwner());
//        }
//        else if(m.equals("u")){
//            UserFirstPage(a.getOwner());
//        }
//        else {
//            mainFirstPage();
//        }
//
//        UserFirstPage(a.getOwner());
//
//    }
//
//    public void withdrawPage(Account a){
//        System.out.println("this is the account balance:");
//        System.out.println(a.getBalance() + "\n");
//
//        if (atms.workATMs.size() == 0){
//            System.out.println("no ATM available, please contact the manager.\n");
//            UserFirstPage(a.getOwner());
//        }
//        else {
//            System.out.println("please enter the withdraw amount");
//            int amount = 0;
//            while (true){
//                boolean amountFormatCheck = true;
//                try {
//                    amount = Integer.parseInt(in.nextLine());
//                } catch (NumberFormatException e){
//                    amountFormatCheck = false;
//                }
//                if(amount < 0 || !amountFormatCheck){
//                    System.out.println("invalid input, please enter the withdraw amount again");
//                }
//                else{
//                    break;
//                }
//            }
//
//            System.out.println("please select the ATM number to withdraw from");
//            int selectAtm = 1;
//            int chosenAtm = 0;
//            while (true) {
//                boolean atmFormatCheck = true;
//                for (ATM atm : atms.workATMs) {
//                    System.out.println(Integer.toString(selectAtm) + ". " + atm.id);
//                    selectAtm++;
//                }
//                try {
//                    chosenAtm = Integer.parseInt(in.nextLine()) - 1;
//                }catch (NumberFormatException e){
//                    atmFormatCheck = false;
//                }
//                if (chosenAtm <= -1 || chosenAtm >= selectAtm || !atmFormatCheck) {
//                    System.out.println("invalid input, please select the ATM number" +
//                            " to withdraw the money again: \n");
//                    selectAtm = 1;
//                } else {
//                    break;
//                }
//            }
//
//            if (a instanceof Credit) {
//                if (((Credit) a).withdraw(amount, atms.workATMs.get(chosenAtm))) {
//                    System.out.println("withdraw successful\n");
//                } else {
//                    System.out.println("withdraw unsuccessful\n");
//                }
//            }
//
//            if (a instanceof Linecredit) {
//                if (((Linecredit) a).withdraw(amount, atms.workATMs.get(chosenAtm))) {
//                    System.out.println("withdraw successful\n");
//                } else {
//                    System.out.println("withdraw unsuccessful\n");
//                }
//            }
//
//            if (a instanceof Chequing) {
//                if (((Chequing) a).withdraw(amount, atms.workATMs.get(chosenAtm))) {
//                    System.out.println("withdraw successful\n");
//                } else {
//                    System.out.println("withdraw unsuccessful\n");
//                }
//            }
//
//            if (a instanceof Saving) {
//                if (((Saving) a).withdraw(amount, atms.workATMs.get(chosenAtm))) {
//                    System.out.println("withdraw successful\n");
//                } else {
//                    System.out.println("withdraw unsuccessful\n");
//                }
//            }
//
//            System.out.println("Your new account balance is " + a.display() + "\n");
//            UserFirstPage(a.getOwner());
//        }
//    }
//
//    public void depositPage(Account a){
//        System.out.println("s");
////        System.out.println("this is the account balance:");
////        System.out.println(a.display() + "\n");
////
////        if (atms.workATMs.size() == 0 && atms.nonWorkATMs.size() == 0){
////            System.out.println("no ATM available, please contact the manager.\n");
////            UserFirstPage(a.getOwner());
////        }
////        else {
////            System.out.println("please select a number: \n");
////            System.out.println("1. deposit cash");
////            System.out.println("2. deposit cheque\n");
////
////            String enter;
////            while (true) {
////                enter = in.nextLine();
////                if (enter.equals("1") || enter.equals("2")) {
////                    break;
////                } else {
////                    System.out.println("invalid input, please try again.");
////                    System.out.println("please select a number: \n");
////                    System.out.println("1. deposit cash");
////                    System.out.println("2. deposit cheque\n");
////                }
////            }
////
////            if (enter.equals("1")) {
////
////                System.out.println("please enter the amount of fifty dollar bills: \n");
////                int fiftyDollar;
////                while (true) {
////                    fiftyDollar = Integer.parseInt(in.nextLine());
////                    if (fiftyDollar < 0) {
////                        System.out.println("invalid input, please enter the amount of fifty dollar bills again: \n");
////                    } else {
////                        break;
////                    }
////                }
////
////                System.out.println("please enter the amount of twenty dollar bills: \n");
////                int twentyDollar;
////                while (true) {
////                    twentyDollar = Integer.parseInt(in.nextLine());
////                    if (twentyDollar < 0) {
////                        System.out.println("invalid input, please enter the amount of twenty dollar bills again: \n");
////                    } else {
////                        break;
////                    }
////                }
////
////                System.out.println("please enter the amount of ten dollar bills: \n");
////                int tenDollar;
////                while (true) {
////                    tenDollar = Integer.parseInt(in.nextLine());
////                    if (tenDollar < 0) {
////                        System.out.println("invalid input, please enter the amount of ten dollar bills again: \n");
////                    } else {
////                        break;
////                    }
////                }
////
////                System.out.println("please enter the amount of five dollar bills: \n");
////                int fiveDollar;
////                while (true) {
////                    fiveDollar = Integer.parseInt(in.nextLine());
////                    if (fiveDollar < 0) {
////                        System.out.println("invalid input, please enter the amount of five dollar bills again: \n");
////                    } else {
////                        break;
////                    }
////                }
////
////                System.out.println("please select the ATM number to deposit the money");
////                int selectAtm = 1;
////                int chosenAtm;
////                while (true) {
////                    for (ATM atm : atms.workATMs) {
////                        System.out.println(selectAtm + ". " + atm.id);
////                        selectAtm++;
////                    }
////
////                    for (ATM atm : atms.nonWorkATMs) {
////                        System.out.println(selectAtm + ". " + atm.id);
////                        selectAtm++;
////                    }
////                    chosenAtm = Integer.parseInt(in.nextLine()) - 1;
////                    if (chosenAtm < 0 || chosenAtm >= selectAtm) {
////                        System.out.println("invalid input, please select the ATM number to deposit the money again: \n");
////                        selectAtm = 1;
////                    } else {
////                        break;
////                    }
////                }
////
////                if (chosenAtm < selectAtm - atms.nonWorkATMs.size() - 1) {
////                    a.deposit(fiveDollar, tenDollar, twentyDollar, fiftyDollar, atms.workATMs.get(chosenAtm));
////                } else {
////                    a.deposit(fiveDollar, tenDollar, twentyDollar, fiftyDollar,
////                            atms.nonWorkATMs.get(chosenAtm - atms.workATMs.size()));
////                    System.out.println("Deposit Successful.");
////                    System.out.println("Your new account balance is " + a.display() + "\n");
////                    UserFirstPage(a.getOwner());
////                }
////            } else {
////                System.out.println("please enter the cheque amount: \n");
////                double chequeAmount;
////                while (true) {
////                    chequeAmount = Double.parseDouble(in.nextLine());
////                    if (chequeAmount < 0) {
////                        System.out.println("invalid input, please try again");
////                        System.out.println("please enter the cheque amount: \n");
////                    } else {
////                        break;
////                    }
////                }
////
////                a.deposit(chequeAmount);
////                System.out.println("Deposit Successful.");
////                System.out.println("Your new account balance is " + a.display() + "\n");
////                UserFirstPage(a.getOwner());
////            }
////        }
////    }
////
////    public void payBillPage(Account a){
////        System.out.println("this is the account balance:");
////        System.out.println(a.display() + "\n");
////        System.out.println("please enter the name of receiver: \n");
////        String name = in.nextLine();
////        System.out.println("please enter the amount you wish to pay to the receiver: \n");
////
////        double amount = 0;
////        while (true){
////            boolean amountFormatCheck = true;
////            try {
////                amount = Double.parseDouble(in.nextLine());
////            } catch (NumberFormatException e){
////                amountFormatCheck = false;
////            }
////            if(amount < 0 || !amountFormatCheck){
////                System.out.println("invalid input, please enter the amount you wish to pay to the receiver: \n");
////            }
////            else{
////                break;
////            }
////        }
////
////        if (a instanceof Linecredit){
////            if (((Linecredit) a).payBill(name, amount)){
////                System.out.println( a.getAccountNum() + " paying "+ amount + " to " + name + "\n");
////            } else{
////                System.out.println("pay bill unsuccessful \n");
////            }
////        }
////
////        if (a instanceof Chequing){
////            if (((Chequing) a).payBill(name, amount)){
////                System.out.println( a.getAccountNum() + " paying "+ amount + " to " + name + "\n");
////            } else{
////                System.out.println("pay bill unsuccessful \n");
////            }
////        }
////
////        if (a instanceof Saving){
////            if (((Saving) a).payBill(name, amount)){
////                System.out.println( a.getAccountNum() + " paying "+ amount + " to " + name + "\n");
////            } else{
////                System.out.println("pay bill unsuccessful \n");
////            }
////        }
////
////        System.out.println("Your new account balance is " + a.display() + "\n");
////        UserFirstPage(a.getOwner());
////
////    }
////
////    public void transferPage(Account a){
////        System.out.println("this is the account balance:");
////        System.out.println(a.getBalance() + "\n");
////
////
////        System.out.println("please enter the account number you wish to transfer money to: \n");
////        String accountNum;
////        while (true){
////            accountNum = in.nextLine();
////            if (!accounts.accountNumExists(accountNum)) {
////                System.out.println("account does not exist.");
////                System.out.println("please re-enter the account number you wish to transfer money to: \n");
////            } else{
////                break;
////            }
////        }
////
////        System.out.println("please enter the amount you wish to send: \n");
////        double amount = 0;
////        while (true){
////            boolean amountFormatCheck = true;
////            try {
////                amount = Double.parseDouble(in.nextLine());
////            }catch (NumberFormatException e){
////                amountFormatCheck = false;
////            }
////            if (amount < 0 || !amountFormatCheck){
////                System.out.println("invalid input, please enter the amount you wish to transfer again: \n");
////            }else{
////                break;
////            }
////        }
////
////        if (a instanceof Linecredit){
////            if (((Linecredit) a).transferMoney(accountNum, amount)){
////                System.out.println(a.getAccountNum() + " transfer " + amount +
////                        " to " + accountNum + "successfully \n");
////            } else{
////                System.out.println("transfer unsuccessful\n");
////            }
////        }
////
////        if (a instanceof Chequing){
////            if (((Chequing) a).transferMoney(accountNum, amount)){
////                System.out.println(a.getAccountNum() + " transfer " + amount +
////                        " to " + accountNum + "successfully \n");
////            } else{
////                System.out.println("transfer unsuccessful\n");
////            }
////        }
////
////        if (a instanceof Saving){
////            if (((Saving) a).transferMoney(accountNum, amount)){
////                System.out.println(a.getAccountNum() + " transfer " + amount +
////                        " to " + accountNum + "successfully \n");
////            } else{
////                System.out.println("transfer unsuccessful\n");
////            }
////        }
////
////        System.out.println("Your new account balance is " + a.display() + "\n");
////        UserFirstPage(a.getOwner());
////    }
//
//
//
//    public static void main(String[] args) {
//        main main = new main();
//        main.toInitial();
//        main.mainFirstPage();
//        main.eventExit();
//
//
//
//    }
//}
