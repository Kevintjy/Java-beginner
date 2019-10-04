package Test;

import Model.*;
import main.java.ATM.*;

public class CreateDataForTest {
    public static void main(String[] args) {
        ATM atm1 = new ATM("1");
        ATM atm2 = new ATM("2");
        ATM atm3 = new ATM("3");
        ATM atm4 = new ATM("4");
        User user1 = new User("name1","1","1234"); // User has two accounts(credit and line credit)
        User user2 = new User("name2", "2","1234"); // User has two accounts(chequing and Saving)
        User user3 = new User("name3", "3","1234"); // User has four accounts(credit, line credit, chequing and Saving)
        User user4 = new User("name4", "4","1234"); // User has two chequing accounts
        User user5 = new User("name5", "5","1234"); // User has no account, but he requesting a chequing and credit account)
        Account account1 = new Credit("1","1");
        Account account2 = new Linecredit("2","1");
        Account account3 = new Chequing("3","2");
        Account account4 = new Saving("4","2");
        Account account5 = new Credit("5","3");
        Account account6 = new Linecredit("6","3");
        Account account7 = new Chequing("7","3");
        Account account8 = new Saving("8","3");
        Account account9 = new Chequing("9","4");
        Account account10 = new Chequing("10","4");
        Manager manager1 = new Manager("manager1","1","1234");
        Manager manager2 = new Manager("manager2","2","1234");
        Users.users.add(user1);
        Users.users.add(user2);
        Users.users.add(user3);
        Users.users.add(user4);
        Users.users.add(user5);
        Managers.managers.add(manager1);
        Managers.managers.add(manager2);
        Accounts.addNewAccount(account1);
        Accounts.addNewAccount(account2);
        Accounts.addNewAccount(account3);
        Accounts.addNewAccount(account4);
        Accounts.addNewAccount(account5);
        Accounts.addNewAccount(account6);
        Accounts.addNewAccount(account7);
        Accounts.addNewAccount(account8);
        Accounts.addNewAccount(account9);
        Accounts.addNewAccount(account10);
        manager1.managerAddMoney(atm1);
        manager1.managerAddMoney(atm2);
        ATMs.nonWorkATMs.add(atm3);
        ATMs.nonWorkATMs.add(atm4);
        user5.sendRequest("Chequing");
        user5.sendRequest("Credit");
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


    }


}
