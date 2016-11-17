package br.ufrn.stronda.newlostandfound;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map1 is ready to be used.
        SupportMapFragment mapFragmentA = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragmentA.getMapAsync(this);

    }

    /**
     * Manipulates the map1 once available.
     * This callback is triggered when the map1 is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app. -5.8323,-35.2074909
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        maps = googleMap;
        maps.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng reitoria = new LatLng(-5.8396243,-35.2020049);
        LatLng imd = new LatLng(-5.8325288,-35.2056435);
        LatLng cet = new LatLng(-5.843550, -35.199261);
        LatLng cb = new LatLng(-5.8420593,-35.2019848);
        LatLng bczm = new LatLng(-5.839793, -35.198982);
        LatLng setor1 = new LatLng(-5.837527, -35.199564);
        LatLng setor2 = new LatLng(-5.840543, -35.197225);
        LatLng setor3 = new LatLng(-5.841103, -35.200399);
        LatLng setor4 = new LatLng(-5.842451, -35.199776);
        LatLng setor5 = new LatLng(-5.838520, -35.197563);

        maps.addMarker(new MarkerOptions().position(reitoria).title("REITORIA").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        maps.addMarker(new MarkerOptions().position(imd).title("IMD").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        maps.addMarker(new MarkerOptions().position(cet).title("C&T").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        maps.addMarker(new MarkerOptions().position(cb).title("CB").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        maps.addMarker(new MarkerOptions().position(bczm).title("BCZM").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        maps.addMarker(new MarkerOptions().position(setor1).title("SETOR I").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        maps.addMarker(new MarkerOptions().position(setor2).title("SETOR II").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        maps.addMarker(new MarkerOptions().position(setor3).title("SETOR III").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        maps.addMarker(new MarkerOptions().position(setor4).title("SETOR IV").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        maps.addMarker(new MarkerOptions().position(setor5).title("SETOR V").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        maps.moveCamera(CameraUpdateFactory.newLatLngZoom(reitoria, 15));
    }

}
