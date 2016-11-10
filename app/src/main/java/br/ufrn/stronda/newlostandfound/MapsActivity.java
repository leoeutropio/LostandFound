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

    private GoogleMap mapA,mapP;
    TabHost tabHost;
    TabHost.TabSpec abas1,abas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        tabHost=(TabHost) findViewById(R.id.tabHostMaps);
        tabHost.setup();

        abas1 = tabHost.newTabSpec("Achados");
        abas1.setContent(R.id.map);
        abas1.setIndicator("Achados");
        tabHost.addTab(abas1);

        abas2 = tabHost.newTabSpec("Perdidos");
        abas2.setContent(R.id.maps);
        abas2.setIndicator("Perdidos");
        tabHost.addTab(abas2);

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#270A2B")); // unselected
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#9c7b00"));
            tv.setTextSize(15);
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#C0C0C0")); // selected
        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(Color.parseColor("#9c7b00"));
        tv.setTextSize(15);



        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#270A2B")); // unselected
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#9c7b00"));
                    tv.setTextSize(15);

                }

                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#C0C0C0")); // selected
                TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(Color.parseColor("#9c7b00"));
                tv.setTextSize(15);

                setMaps1();
                setMaps2();


            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragmentA = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        SupportMapFragment mapFragmentP = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragmentA.getMapAsync(this);
        mapFragmentP.getMapAsync(this);

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

    private void mapaAchados(){
        // Add a marker in UFRN and move the camera

        mapA.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng reitoria = new LatLng(-5.8396243,-35.2020049);
        mapA.addMarker(new MarkerOptions().position(reitoria).title("Reitoria").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mapA.moveCamera(CameraUpdateFactory.newLatLngZoom(reitoria, 16) );
    }

    private void mapaPerdidos(){
        // Add a marker in UFRN and move the camera

        mapA.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng reitoria = new LatLng(-5.8396243,-35.2020049);
        mapA.addMarker(new MarkerOptions().position(reitoria).title("Reitoria").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mapA.moveCamera(CameraUpdateFactory.newLatLngZoom(reitoria , 16) );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapA = googleMap;
        setMaps1();
        setMaps2();
    }

    private void setMaps2() {
        if(tabHost.getCurrentTab() == 1){
            mapaPerdidos();
        }
    }

    private void setMaps1() {
        if(tabHost.getCurrentTab() == 0){
            mapaAchados();
        }
    }


}
