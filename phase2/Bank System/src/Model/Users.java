package Model;

import main.java.ATM.User;

import java.io.*;
import java.util.ArrayList;


public class Users {

    private DataPath dataPath = DataPath.getDataPath();

    private final String USER_FOLDER = dataPath.getPath("Users");

    // the list storing all current existing Users.
    public static ArrayList<User> users = new ArrayList<>();


    public static ArrayList<User> getUsers() {
         return users;
     }

     /*
     Add a User to the ArrayList.
     */
    public static void addUser(User u) {
        users.add(u);
    }


    /*
    Load all Users to the list.
     */
    public void loadUsers() {

        File file = new File(USER_FOLDER);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                try {
                    FileInputStream fileIn = new FileInputStream(USER_FOLDER + "/" + f.getName());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    User e = (User) in.readObject();
                    users.add(e);
                    in.close();
                    fileIn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
    Write information of each User back to file.
     */
    public void writeUsersBack() {
        for (User u: users) {
            try {
                FileOutputStream fileOut = new FileOutputStream(USER_FOLDER + "/"+ u.getId() + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(u);
                out.close();
                fileOut.close();
            } catch(Exception i) {
                i.printStackTrace();
            }
        }
    }


    /*
    User login to the system.
     */
    public User login(String id, String pw) {
        for (User u: users) {
            if((u.getId().equals(id)) && (u.getPassword().equals(pw))) {
                return u;
            }
        }
        return null;
    }


    /*
    Return the User with given user id.
     */
    public static User UserIdExists(String id) {
        for (User u: users) {
            if (id.equals(u.getId())) {
                return u;
            }
        }
        return null;
    }
    public  static  User foundUser(String id){

        for (User user: users){
            if (user.getId().equals(id)) return user;
        }
        return null;
    }


}

