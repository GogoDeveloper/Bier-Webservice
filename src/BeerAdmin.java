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
        String mii = "http://api.brewerydb.com/v2/";
        HashMap<String, String> beer = new HashMap<>();
        BufferedReader br = null;


    public void loadBeerStyles(){
        try {
            String inline = "";
            URL url = new URL("http://api.brewerydb.com/v2/styles/"+apiKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responsecode = con.getResponseCode();
            if(responsecode != 200){
                throw new RuntimeException("HttpResponseCode: "+responsecode);
            }
            else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()){

                    String[] lineParts = inline.split(";");
                    beer.put(lineParts[0], lineParts[1]);
                }
                System.out.println(beer.get(0));
                sc.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null){
                    br.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
