import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class BeerAdmin {
    String apiKey = "?key=1511d0db4a1d6841481c672455358cff";
    String address = "http://api.brewerydb.com/v2/";
    HashMap<String, String> beers = new HashMap<>();


    //loading beer's and id's in hashmap
    public void loadBeerStyles() {
        try {
            String inline = "";
            URL url = new URL("http://api.brewerydb.com/v2/styles/" + apiKey);
            //Opens the connection to the BreweryDB
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //Set's the request method to get
            con.setRequestMethod("GET");
            con.connect();
            // if the responsecode is not 200 the connection failed
            int responsecode = con.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }

                JSONParser parse = new JSONParser();
                //try / catch ParseException
                try {
                    //parses the whole BreweryDB to JSON-Object
                    JSONObject job1 = (JSONObject) parse.parse(inline);
                    //searches for data in job1 and saves it in JSON-Array
                    JSONArray jarray = (JSONArray) job1.get("data");

                    //loops over the entire JSON-Array
                    for (int i = 0; i < jarray.size(); i++) {
                        //adds every data-line to JSON-Object: job2
                        JSONObject job2 = (JSONObject) jarray.get(i);
                        //adds every id and name to beers-hashmap
                        beers.put(job2.get("id").toString(), job2.get("name").toString());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //closes the scanner-Object
                sc.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Prints all Beer's and Id's in the beer hashmap
    public void printBeerStyles() {
        for (String key : beers.keySet()) {
            //key == key beers.get(key) == value of key
            System.out.println(key + " :: " + beers.get(key));
        }
    }

    //Prints all Beer's that contains the search String
    public void printBeerStyles(String search) {
        for (String key : beers.keySet()) {
            if (beers.get(key).contains(search)) {
                System.out.println(key + " :: " + beers.get(key));
            }
        }
    }
}