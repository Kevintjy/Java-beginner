package Model;

import Model.ATMs;
import Model.Accounts;
import main.java.ATM.ATM;
import main.java.ATM.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;


public class Deposits {

     private DataPath dataPath = DataPath.getDataPath();

    // the path of the deposits' data
    private final String DEPOSITS_FILE = dataPath.getPath("Deposits");


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
                        HashMap<String, Object> saved = new HashMap<String, Object>();
                        saved.put("fiveDollar",Integer.parseInt(l[2]));
                        saved.put("tenDollar",Integer.parseInt(l[3]));
                        saved.put("twentyDollar",Integer.parseInt(l[4]));
                        saved.put("fiftyDollar",Integer.parseInt(l[5]));
                        account.deposit(saved, atm);
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
