package Model;

import main.java.ATM.ATM;

import java.io.*;
import java.util.ArrayList;

 public class ATMs {

    // list of working ATMs
    public static ArrayList<ATM> workATMs = new ArrayList<>();

    // list of non-working ATMs
    public static ArrayList<ATM> nonWorkATMs = new ArrayList<>();

    // the path of data being stored
    private final String ATM_FILE = "data/ATMData";


    /*
    Load ATMs to two lists.
     */
    public void loadATMs() {

        try {
            BufferedReader in = new BufferedReader(new FileReader(ATM_FILE));
            String line = in.readLine();
            while (line != null) {
                String[] l = line.split(",");
                ATM a = new ATM(l[1]);
                a.AddMoney(Integer.parseInt(l[2]),Integer.parseInt(l[3]),Integer.parseInt(l[4]),Integer.parseInt(l[5]));
                if (l[0].equals("work")) {
                    workATMs.add(a);
                } else {
                    a.closeWithdraw = true;
                    nonWorkATMs.add(a);
                }
                line = in.readLine();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    Record the information of ATM to the file.
     */
    public void writeBackToFile(){

        try{
            FileWriter writer = new FileWriter(ATM_FILE);
            FileWriter writer1 = new FileWriter("data/Alert");
            writer.close();
            writer1.close();
        } catch (Exception e ) {
            e.printStackTrace();
        }
        for (ATM atm: workATMs) {
            try {
                FileWriter writer = new FileWriter(ATM_FILE, true);
                writer.write("work"+ ",");
                writer.write(atm.id + ",");
                writer.write(atm.getFiveDollar()+ ",");
                writer.write(atm.getTenDollar() + ",");
                writer.write(atm.getTwentyDollar()+ ",");
                writer.write(atm.getFiftyDollar() + "\n");
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (ATM atm: nonWorkATMs) {
            try {
                FileWriter writer = new FileWriter(ATM_FILE,true);
                writer.write("nonWork"+",");
                writer.write(atm.id + ",");
                writer.write(atm.getFiveDollar() + ",");
                writer.write(atm.getTenDollar() + ",");
                writer.write(atm.getTwentyDollar()+ ",");
                writer.write(atm.getFiftyDollar() + "\n");
                writer.close();
                FileWriter writer1 = new FileWriter("data/Alert",true);
                String alertMessage = atm.id + " has " + atm.getFiveDollar() + " five dollar " +
                        atm.getTenDollar() + " ten dollar " + atm.getTwentyDollar() + " twenty dollar " +
                        atm.getFiftyDollar() + " fifty dollar\n";
                writer1.write(alertMessage);
                writer1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /*
    Find a ATM with a given id
     */
    public static ATM findATM(String id) {
        for (ATM atm:workATMs) {
            if (atm.id.equals(id)) {
                return atm;
            }
        }
        for (ATM atm:nonWorkATMs) {
            if (atm.id.equals(id)) {
                return atm;
            }
        }
        return null;
    }

}
