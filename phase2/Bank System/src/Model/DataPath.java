package Model;

import java.util.HashMap;

public class DataPath {

    // the hashmap that stores paths of data
    public static HashMap<String, String> path;

    private static DataPath data;

    /*
    Construct a new DataPath class.
     */
    private DataPath() {
        path = new HashMap<>();
        path.put("Managers", "data/ManagerData");
        path.put("Users", "data/UserFolder");
        path.put("Accounts", "data/AccountFolder");
        path.put("ATMs", "data/ATMData");
        path.put("Requests", "data/RequestData");
        path.put("Deposits", "data/deposit");
    }


    public static DataPath getDataPath() {

        if (path == null) {
            data = new DataPath();
        }

        return data;
    }


    public String getPath(String className) {
        return path.get(className);
    }

    /*
    Change the path of data
     */
    public void changePath(String className, String newPathName) {
        path.replace(className, newPathName);
    }


    /*
    add new data path into the hashmap.
     */
    public void addPath(String newClass, String newPath) {
        path.put(newClass, newPath);
    }


}
