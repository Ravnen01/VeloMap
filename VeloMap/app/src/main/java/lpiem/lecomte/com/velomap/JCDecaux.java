package lpiem.lecomte.com.velomap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import lpiem.lecomte.com.velomap.Model.Contract;

/**
 * Created by LECOMTE on 13/10/2015.
 */
public class JCDecaux {

    private static String apikey="626f84cb00f2d7868af5b65d34317c54c2158acd";

    private static JCDecaux ourInstance = new JCDecaux();

    public static JCDecaux getInstance() {
        return ourInstance;
    }

    private JCDecaux() {
    }

    public ArrayList<Contract> listJCDContract(){
        ArrayList<Contract> listContract=new ArrayList<>();

        String myurl= "https://api.jcdecaux.com/vls/v1/contracts?apiKey="+apikey;

        URL url = null;
        try {
            url = new URL(myurl);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
                /*
                 * InputStreamOperations est une classe complémentaire:
                 * Elle contient une méthode InputStreamToString.
                 */
            String result = inputStream.toString();

            JSONArray array = new JSONArray(result);

            for(int i=0;i<array.length();i++){

                listContract.add(new Contract(new JSONObject(array.getString(i))));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listContract;
    }

}
