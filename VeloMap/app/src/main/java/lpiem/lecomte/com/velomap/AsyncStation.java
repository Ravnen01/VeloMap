package lpiem.lecomte.com.velomap;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import lpiem.lecomte.com.velomap.Model.Contract;
import lpiem.lecomte.com.velomap.Model.Station;

/**
 * Created by iem2 on 14/10/15.
 */
public class AsyncStation extends AsyncTask {
    private ArrayList<Station> listStation=new ArrayList();

    private String contract;
    private GoogleMap mMap;
    private static String apikey="626f84cb00f2d7868af5b65d34317c54c2158acd";

    public AsyncStation(String contract, GoogleMap mMap) {
        this.contract = contract;
        this.mMap = mMap;
    }

    @Override
    protected Object doInBackground(Object[] params) {


        String myurl= "https://api.jcdecaux.com/vls/v1/stations?contract="+contract+"&apiKey="+apikey;

        URL url = null;
        try {
            url = new URL(myurl);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));



            String result = in.readLine();

            JSONArray array = new JSONArray(result);

            for(int i=0;i<array.length();i++){

                listStation.add(new Station(array.getJSONObject(i)));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        for(int i=0;i<listStation.size();i++){
            createMarker(listStation.get(i));
        }



    }
    private void createMarker(Station station){
        LatLng position=new LatLng(station.getLat(),station.getLng());
        mMap.addMarker(new MarkerOptions()
                .title(station.getName())
                .snippet(station.getAdress()+"\nPace libre:"+station.getAvailableBikeStands()+"\nVelo libre:"+station.getAvailableBikes())
                .position(position));
    }
}
