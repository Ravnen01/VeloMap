package lpiem.lecomte.com.velomap.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by iem2 on 03/11/15.
 */
public class ClusterMarker implements ClusterItem {
    private final LatLng mPosition;
    private MarkerOptions marker;

    public ClusterMarker(double lat, double lng,MarkerOptions marker) {
        mPosition = new LatLng(lat, lng);
        this.marker=marker;
    }
    @Override
    public LatLng getPosition() {
        return mPosition;
    }



    public MarkerOptions getMarker() {
        return marker;
    }

    public void setMarker(MarkerOptions marker) {
        this.marker = marker;
    }
}
