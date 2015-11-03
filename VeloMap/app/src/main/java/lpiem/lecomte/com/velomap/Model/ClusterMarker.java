package lpiem.lecomte.com.velomap.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by iem2 on 03/11/15.
 */
public class ClusterMarker implements ClusterItem {
    private final LatLng mPosition;

    public ClusterMarker(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }
    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
