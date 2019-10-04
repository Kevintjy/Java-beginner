package Model;

import main.java.ATM.Manager;

import java.io.*;
import java.util.ArrayList;

 public class Managers {

    // the list storing all of current existing Managers
    public static ArrayList<Manager> managers = new ArrayList<>();

    private DataPath dataPath = DataPath.getDataPath();

    private final String MANAGER_DATA = dataPath.getPath("Managers");

    /*
    Load managers to the list.
     */
    public void loadManager() {

        try {
            BufferedReader in = new BufferedReader(new FileReader(MANAGER_DATA));
            String line = in.readLine();
            while (line != null){
                String[] l = line.split(",");
                Manager manager = new Manager(l[1], l[2],l[3]);
                manager.date = l[0];
                managers.add(manager);
                line = in.readLine();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    Record information of each Manager to the file.
     */
    public void writeBackToFile() {

        try {
            FileWriter writer0 = new FileWriter(MANAGER_DATA);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Manager manager: managers) {
            try {
                FileWriter writer = new FileWriter(MANAGER_DATA,true);
                writer.write(manager.date + ",");
                writer.write(manager.getName() + ",");
                writer.write(manager.getId() + ",");
                writer.write(manager.getPassword() + "\n");
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /*
    Manager login to the system.
     */
    public Manager login(String id, String pw) {

        for (Manager m: managers){
            if ((m.getId().equals(id)) && (m.getPassword().equals(pw))) {
                return m;
            }
        }
        return null;
    }



}
