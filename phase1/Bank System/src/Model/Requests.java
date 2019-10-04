package Model;

import main.java.ATM.Request;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

 public class Requests {

    // the list storing all Requests that have not been processed
    public static ArrayList<Request> requests = new ArrayList<>();

    private static final String REQUEST_DATA = "data/RequestData";


    /*
    Add a new Request to the ArrayList.
     */
    public static void addRequest(Request request){
        requests.add(request);
    }


    /*
    Load Requests to the ArrayList.
     */
    public void loadRequest() {

        try {
            BufferedReader in = new BufferedReader(new FileReader(REQUEST_DATA));
            String line = in.readLine();
            while (line != null) {
                String[] l = line.split(",");
                Request request = new Request(l[0],l[1]);
                requests.add(request);
                line = in.readLine();
            }
            in.close();}
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    Write the details of each Request back to the file.
     */
    public void writeBackToFile() {

        try {
            FileWriter writer = new FileWriter(REQUEST_DATA);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Request request: requests) {
            try {
                FileWriter writer = new FileWriter(REQUEST_DATA,true);
                writer.write(request.getUser().getId() + ",");
                writer.write(request.getAccountType() + "\n");
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
