package com.example.manish.maps;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button map,satellite,hybridbtn,tokyobtn,dublinbtn,seatlebtn;
    GoogleMap gmap;
    Boolean mapReady=false;
    MarkerOptions jkHOSPITAL,MYHOME,dublinmark,seatlemark,tokyomark;
    PolygonOptions polyline;
    CircleOptions JKCIRCLE;
    CameraPosition camerpos_for_tokyo=CameraPosition.builder()
            .target(new LatLng(35.6895,139.6917))
            .bearing(90)
            .zoom(17)
            .tilt(45)
            .build();
    CameraPosition camerpos_for_dublin=CameraPosition.builder()
            .target(new LatLng(53.3478,-6.2597))
            .bearing(90)
            .zoom(17)
            .tilt(45)
            .build();

    CameraPosition camerpos_for_seatle=CameraPosition.builder()
            .target(new LatLng(47.6204,-122.3491))
            .bearing(90)
            .zoom(17)
            .tilt(45)
            .build();
    CameraPosition camerpos_for_ghar=CameraPosition.builder()
            .target(new LatLng(28.632820,77.090192))
            .bearing(0)
            .zoom(17)
            .tilt(25)
            .build();
    CameraPosition camerpos_for_newyork=CameraPosition.builder()
            .target(new LatLng(28.606820,77.060042))
            .bearing(0)
            .zoom(1)
            .tilt(25)
            .build();


    LatLng linept1=new LatLng(28.632768,77.092420);
    LatLng linept2=new LatLng(28.633723,77.088842);
    LatLng linept3=new LatLng(28.634579,77.092513);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map=(Button)findViewById(R.id.btnMap);
        satellite=(Button)findViewById(R.id.btnSatellite);
        hybridbtn=(Button)findViewById(R.id.btnHybrid);
        tokyobtn=(Button)findViewById(R.id.btnTokyo);
        seatlebtn=(Button)findViewById(R.id.btnSeattle);
        dublinbtn=(Button)findViewById(R.id.btnDublin);

        jkHOSPITAL=new MarkerOptions()
                .position(new LatLng(28.633723,77.088842))
                .title("JK-HOSPITAL")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round));

        MYHOME=new MarkerOptions()
                .position(new LatLng(28.632820,77.090192))
                .title("HOME");


        tokyomark=new MarkerOptions()
                .position(new LatLng(35.6895,139.6917))
                .title("HOME");
        seatlemark=new MarkerOptions()
                .position(new LatLng(47.6204,-122.3491))
                .title("HOME");
        dublinmark=new MarkerOptions()
                .position(new LatLng(53.3478,-6.2597))
                .title("HOME");


        JKCIRCLE=new CircleOptions().center(new LatLng(28.633723,77.088842))
                .radius(100)
                .strokeColor(Color.GREEN)
                .fillColor(Color.LTGRAY);


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                    gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                    gmap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        hybridbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                    gmap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });
        tokyobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                    animate_function(camerpos_for_tokyo);
                }
            }
        });
        seatlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate_function(camerpos_for_seatle);
            }
        });

        dublinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate_function(camerpos_for_dublin);
            }
        });



        MapFragment mapFragment=(MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {//callback function
        mapReady=true;
        gmap=googleMap;
        gmap.addMarker(jkHOSPITAL);//to add a marker over a specific place you want
        gmap.addMarker(MYHOME);
        gmap.addCircle(JKCIRCLE);//circle bana sakte h ek LatLng k aroung or desired radius and desired color and pattern
        gmap.addMarker(tokyomark);
        gmap.addMarker(dublinmark);
        gmap.addMarker(seatlemark);
        gmap.addPolyline(new PolylineOptions().geodesic(true)
        .add(linept1)
        .add(linept2)
        .add(linept3)
        .add(linept1));//line bana skte h join krne k liye
        // gmap.moveCamera(CameraUpdateFactory.newCameraPosition(camerpos_for_newyork));
        gmap.moveCamera(CameraUpdateFactory.newCameraPosition(camerpos_for_ghar));
    }
    private void animate_function(CameraPosition pos)
    {
        gmap.animateCamera(CameraUpdateFactory.newCameraPosition(pos),4000,null);
    }
}