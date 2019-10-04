package Model;

import main.java.ATM.Staff;

import java.io.*;
import java.util.ArrayList;

public class Staffs {

    private final String STAFF_FOLDER = "data/StaffFolder";

    // the list storing all current existing Staffs.
    public static ArrayList<Staff> staffs = new ArrayList<>();


    public static ArrayList<Staff> getStaffs() {
        return staffs;
    }

    /*
        Add a Staff to the ArrayList.
         */
    public static void addStaff(Staff s) {
        staffs.add(s);
    }


    /*
    Load all Staffs to the list.
     */
    public void loadStaffs() {

        File file = new File(STAFF_FOLDER);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                try {
                    FileInputStream fileIn = new FileInputStream(STAFF_FOLDER + "/" + f.getName());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    Staff e = (Staff) in.readObject();
                    staffs.add(e);
                    in.close();
                    fileIn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
    Write information of each Staff back to file.
     */
    public void writeStaffsBack() {
        for (Staff s: staffs) {
            try {
                FileOutputStream fileOut = new FileOutputStream(STAFF_FOLDER + "/"+ s.getId() + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(s);
                out.close();
                fileOut.close();
            } catch(Exception i) {
                i.printStackTrace();
            }
        }
    }


    /*
    Staff login to the system.
     */
    public Staff login(String id, String pw) {
        for (Staff s: staffs) {
            if((s.getId().equals(id)) && (s.getPassword().equals(pw))) {
                return s;
            }
        }
        return null;
    }


    /*
    Return the Staff with given staff id.
     */
    public static Staff StaffIdExists(String id) {
        for (Staff s: staffs) {
            if (id.equals(s.getId())) {
                return s;
            }
        }
        return null;
    }
}
