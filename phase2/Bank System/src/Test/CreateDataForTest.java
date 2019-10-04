package Test;

import Model.*;
import main.java.ATM.*;

public class CreateDataForTest {
    public static void main(String[] args) {
        ATM atm1 = new ATM("1");
        ATM atm2 = new ATM("2");
        ATM atm3 = new ATM("3");
        ATM atm4 = new ATM("4");
        ATM atm5 = new ATM("5");
        ATM atm6 = new ATM("6");
        User user1 = new User("name1","1","1234"); // User has two accounts(credit and line credit)
        User user2 = new User("name2", "2","1234"); // User has two accounts(chequing and Saving)
        User user3 = new User("name3", "3","1234"); // User has four accounts(credit, line credit, chequing and Saving)
        User user4 = new User("name4", "4","1234"); // User has two chequing accounts
        User user5 = new User("name5", "5","1234"); // User has no account, but he requesting a chequing and credit account)


        Manager manager1 = new Manager("manager1","1","1234");
        Manager manager2 = new Manager("manager2","2","1234");

        Users.users.add(user1);
        Users.users.add(user2);
        Users.users.add(user3);
        Users.users.add(user4);
        Users.users.add(user5);


//        Account account1 = new Credit("1","1");
//        Account account2 = new Linecredit("2","1");
//        Account account3 = new Chequing("3","2");
//        Account account4 = new Saving("4","2");
//        Account account5 = new Credit("5","3");
//        Account account6 = new Linecredit("6","3");
//        Account account7 = new Chequing("7","3");
//        Account account8 = new Saving("8","3");
//        Account account9 = new Chequing("9","4");
//        Account account10 = new Chequing("10","4");

        user1.sendRequest("Credit");
        user1.sendRequest("Line Credit");
        user2.sendRequest("Chequing");
        user2.sendRequest("Saving");
        user3.sendRequest("Credit");
        user3.sendRequest("Line Credit");
        user3.sendRequest("Chequing");
        user3.sendRequest("Saving");
        user4.sendRequest("Chequing");
        user4.sendRequest("Line Credit");

        user5.sendRequest("Chequing");
        user5.sendRequest("Credit");
        user4.sendRequest("Saving");
        user4.sendRequest("Credit");
        user3.sendRequest("US Account");
        user3.sendRequest("Line Credit");
        user2.sendRequest("Saving");
        user2.sendRequest("Line Credit");
        user1.sendRequest("Chequing");
        user1.sendRequest("US Account");

        for(int i = 0; i < 20; i++){
            manager1.createAccount(Requests.requests.get(0));}

        ((Chequing)user2.account.getAccounts().get(0)).transferMoney("1",0.0);

        Staff staff1 = new Staff("staff1", "10", "1234"); // five accounts created.
        Staff staff2 = new Staff("Staff2", "11", "1234");

        Staffs.staffs.add(staff1);
        Staffs.staffs.add(staff2);

        Managers.managers.add(manager1);
        Managers.managers.add(manager2);
//        Accounts.addNewAccount(account1);
//        Accounts.addNewAccount(account2);
//        Accounts.addNewAccount(account3);
//        Accounts.addNewAccount(account4);
//        Accounts.addNewAccount(account5);
//        Accounts.addNewAccount(account6);
//        Accounts.addNewAccount(account7);
//        Accounts.addNewAccount(account8);
//        Accounts.addNewAccount(account9);
//        Accounts.addNewAccount(account10);
//        Accounts.addNewAccount(account11);
//        Accounts.addNewAccount(account12);
//        Accounts.addNewAccount(account13);
//        Accounts.addNewAccount(account14);
//        ATMs.nonWorkATMs.add(atm1);
//        ATMs.nonWorkATMs.add(atm2);
//        ATMs.nonWorkATMs.add(atm3);
//        ATMs.nonWorkATMs.add(atm4);
        manager1.managerAddMoney(atm1);
        manager1.managerAddMoney(atm2);
        manager1.managerAddMoney(atm3);

        Accounts acc = new Accounts();
        acc.writeAccountsBack();
        Users u = new Users();
        u.writeUsersBack();
        Managers man = new Managers();
        man.writeBackToFile();
        ATMs atms = new ATMs();
        atms.writeBackToFile();
        Requests requests = new Requests();
        requests.writeBackToFile();
        Staffs staffs = new Staffs();
        staffs.writeStaffsBack();


    }


}
