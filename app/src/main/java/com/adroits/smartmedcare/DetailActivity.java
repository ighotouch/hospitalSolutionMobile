package com.adroits.smartmedcare;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adroits.smartmedcare.application.ElearnApplication;
import com.adroits.smartmedcare.dbmodels.DataProvider;
import com.adroits.smartmedcare.dbmodels.Facility;
import com.adroits.smartmedcare.utils.SessionManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity implements
        OnMapReadyCallback {
    private GoogleMap mMap;
    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_with_map);
        ElearnApplication.getComponent().inject(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {
        float Latitude = sessionManager.getLatitude();
        float longitude = sessionManager.getLongitude();

        LatLng selectedCoordinates = new LatLng(Latitude, longitude);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedCoordinates, 13));

        map.addMarker(new MarkerOptions()
                .title(sessionManager.getGeoLocation())
                .snippet("")
                .position(selectedCoordinates));


        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pham))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(selectedCoordinates));
        addMarkersToMap(map);
    }

    private void addMarkersToMap(GoogleMap map) {
        map.clear();
        for (Facility facility : DataProvider.facilities) {
            LatLng ll = new LatLng(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
            BitmapDescriptor bitmapMarker;
            switch (facility.getFacilityType()) {
                case "general hospital":
                    bitmapMarker = BitmapDescriptorFactory.fromResource(R.drawable.pham);
                    break;

                default:
                    bitmapMarker = BitmapDescriptorFactory.fromResource(R.drawable.pham);
                    break;
            }
            map.addMarker(new MarkerOptions()
                    .icon(bitmapMarker)
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(ll));

        }
    }


}