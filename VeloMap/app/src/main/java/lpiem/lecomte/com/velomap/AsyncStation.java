package lpiem.lecomte.com.velomap;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;


import org.json.JSONArray;
import org.json.JSONException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import lpiem.lecomte.com.velomap.Model.ClusterMarker;

import lpiem.lecomte.com.velomap.Model.ItemClusterRendere;
import lpiem.lecomte.com.velomap.Model.Station;

/**
 * Created by iem2 on 14/10/15.
 */
public class AsyncStation extends AsyncTask {
    private ArrayList<Station> listStation=new ArrayList();

    private String contract;
    private GoogleMap mMap;
    private static String apikey="626f84cb00f2d7868af5b65d34317c54c2158acd";
    private Context context;
    private ClusterManager<ClusterMarker> mClusterManager;

    public AsyncStation(String contract, GoogleMap mMap, Context context) {
        this.contract = contract;
        this.mMap = mMap;
        this.context=context;
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
    private void setUpClusterer() {
        // Declare a variable for the cluster manager.


        // Position the map.


        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<>(context, mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mClusterManager.setRenderer(new ItemClusterRendere(context, mMap,mClusterManager));
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = LayoutInflater.from(context).inflate(R.layout.info_windows_marker, null);
                TextView tvNomStation = (TextView) v.findViewById(R.id.tvNomStation);
                TextView tvAdressStation = (TextView) v.findViewById(R.id.tvAdresseStation);
                TextView tvNbPlace = (TextView) v.findViewById(R.id.tvNbPlace);
                TextView tvNbVelo = (TextView) v.findViewById(R.id.tvNbVelo);

                tvNomStation.setText(listStation.get(Integer.parseInt(marker.getSnippet())).getName());
                tvAdressStation.setText(listStation.get(Integer.parseInt(marker.getSnippet())).getAdress());
                tvNbPlace.setText("" + listStation.get(Integer.parseInt(marker.getSnippet())).getAvailableBikeStands());
                tvNbVelo.setText("" + listStation.get(Integer.parseInt(marker.getSnippet())).getAvailableBikes());
                return v;
            }
        });
        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<ClusterMarker>() {
            @Override
            public boolean onClusterClick(Cluster<ClusterMarker> cluster) {

                mMap.animateCamera(CameraUpdateFactory.newLatLng(cluster.getPosition()));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.getCameraPosition().zoom+1));
                return false;
            }
        });




        mMap.setOnCameraChangeListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());


    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        setUpClusterer();

        for (int i = 0; i < listStation.size(); i++) {
            createMarker(listStation.get(i), i);
        }





    }
    private void createMarker(Station station,int index){
        LatLng position=new LatLng(station.getLat(),station.getLng());
        final MarkerOptions marker=new MarkerOptions()
                .title(station.getName())
                .snippet("" + index)
                .position(position);
        if(station.getAvailableBikes()>0) {
            if(station.isBonus()) {
                marker.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_vert_bonus));
            }else{
                marker.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_vert));
            }
        }else{
            if(station.isBonus()){
                marker.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_rouge_bonus));
            }else{
                marker.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_rouge));
            }
        }




        ClusterMarker test = new ClusterMarker(station.getLat(), station.getLng(),marker);
        mClusterManager.addItem(test);




    }


}