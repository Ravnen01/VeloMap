package lpiem.lecomte.com.velomap.Model;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by iem2 on 04/11/15.
 */
public class ItemClusterRendere extends DefaultClusterRenderer<ClusterMarker> {


    public ItemClusterRendere(Context context, GoogleMap map, ClusterManager<ClusterMarker> clusterManager) {
        super(context, map, clusterManager);

    }

    @Override
    protected void onClusterItemRendered(ClusterMarker clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
        marker.setIcon(clusterItem.getMarker().getIcon());
        marker.setSnippet(clusterItem.getMarker().getSnippet());



    }


}
