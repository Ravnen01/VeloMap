package lpiem.lecomte.com.velomap;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

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

    public JSONArray listJCDContract(){

        DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost httppost = new HttpPost("http://someJSONUrl/jsonWebService");

        httppost.setHeader("Content-type", "application/json");



    }

}
