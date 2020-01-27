import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class BeerAdmin {
        String apiKey = "?key=1511d0db4a1d6841481c672455358cff";
        String address = "http://api.brewerydb.com/v2/";
        HashMap<String, String> beer = new HashMap<>();



    public void loadBeerStyles(){
        try {
            String inline = "";
            URL url = new URL("http://api.brewerydb.com/v2/styles/"+apiKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responsecode = con.getResponseCode();
            if(responsecode != 200){
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            }
            else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()){
                    System.out.println(inline);
                    inline += sc.nextLine();
                }

                JSONParser parse = new JSONParser();
                try {
                    JSONObject job1 = (JSONObject)parse.parse(inline);
                    JSONArray jarray1 = (JSONArray) job1.get("data");

                    for (int i = 0; i<jarray1.size(); i++){
                        JSONObject job2 = (JSONObject)jarray1.get(i);
                        beer.put(job2.get("id").toString(), job2.get("name").toString());
                    }
                }catch (ParseException e){
                    e.printStackTrace();
                }
                sc.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}