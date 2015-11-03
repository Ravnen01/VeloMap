package lpiem.lecomte.com.velomap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        contract=getIntent().getStringExtra("contract");


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setMyLocationEnabled(true);
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyB8f9QawVQLXqqZMQcIxihQCYSkIxGxzXY");
        try {
            GeocodingResult[] results =  GeocodingApi.geocode(context, contract).await();
            LatLng locContract=new LatLng(results[0].geometry.location.lat,results[0].geometry.location.lng);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locContract,10));
        } catch (Exception e) {
            e.printStackTrace();
        }

        AsyncStation asyncStation=new AsyncStation(contract,mMap,getApplicationContext());
        asyncStation.execute();


    }
}
