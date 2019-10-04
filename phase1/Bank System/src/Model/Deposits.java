package Model;

import Model.ATMs;
import Model.Accounts;
import main.java.ATM.ATM;
import main.java.ATM.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


 public class Deposits {

    // the path of the deposits' data
    private final String DEPOSITS_FILE = "data/deposit";


    /*
    Load the initial deposits in the the system.
     */
    public void loadDeposits() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(DEPOSITS_FILE));
            String line = in.readLine();
            while (line != null) {
                String[] l = line.split(",");
                Account account = Accounts.foundAccountNum(l[0]);
                if( account != null){
                    if (l.length == 2) {
                    account.deposit(Double.valueOf(l[1]));
                    } else if (l.length == 6) {
                        ATM atm = ATMs.findATM(l[1]);
                        account.deposit(Integer.parseInt(l[2]),
                            Integer.parseInt(l[3]), Integer.parseInt(l[4]), Integer.parseInt(l[5]), atm);
                    }
                }
                line = in.readLine();
            }
            in.close();
            FileWriter erase = new FileWriter(DEPOSITS_FILE);
            erase.write("");
            erase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
