package com.adroits.smartmedcare;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnInfoWindowClickListener,
        OnMapReadyCallback {

    private static final int DIALOG_REQUEST = 9001;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private Location location;
    private static ListView listView;

    private static double currentLat;
    private static double currentLng;


    static int counter = 0;
    private double selectedLat;
    private double selectedlng;
    private GoogleMap mMap;
    private int imageResource;

    private static LatLng infoWindowPosition;

    private static final double DEFAULT_RADIUS = 1000000;

    public static final double RADIUS_OF_EARTH_METERS = 6371009;

    private static final double
            SEATTLE_LAT = 47.60621,
            SEATTLE_LNG = -122.33207,
            SYDNEY_LAT = -33.867487,
            SYDNEY_LNG = 151.20699,
            NEWYORK_LAT = 40.714353,
            NEWYORK_LNG = -74.005973;

    private GoogleApiClient mClient;
    private GoogleApiClient mGoogleApiClient;

    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_with_map);

        //temperal


        //current location
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // Create the LocationRequest object
//        mLocationRequest = LocationRequest.create()
//                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
//                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
//                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        double lat = 6.433291;
        double longi = 3.426489;

        Bundle comingIntent = getIntent().getExtras();
        if (comingIntent == null) {
            //finish();
        } else {

            lat = (double) comingIntent.get("lat");
            longi = (double) comingIntent.get("long");

            Log.d("cor: ",String.valueOf(lat));

        }


        String city = "lagos";


        if (servicesOK() && initMap()) {
            //Toast.makeText(this, "ready to map", Toast.LENGTH_SHORT).show();


            goToLocation(lat, longi, 15);


//            MarkerOptions option1 = new MarkerOptions()
//                    .title("Union Bank")
//                    .position(new LatLng(6.4589849, 3.60152070000003))
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital));
//
//            mMap.addMarker(option1);
//
//            MarkerOptions option3 = new MarkerOptions()
//                    .title("Union Bank")
//                    .position(new LatLng(6.45595209999999, 3.38313990000006))
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital));
//
//
//            mMap.addMarker(option3);


        }

    }

    private void addMarker() {

    }

    private void geoLocate(String location) throws IOException {
        Geocoder gc = new Geocoder(this);

        List<Address> list = gc.getFromLocationName(location, 1);
        if (list.size() > 0) {
            Address add = list.get(0);
            String locality = add.getLocality();
            Toast.makeText(this, "found" + locality, Toast.LENGTH_SHORT).show();
            double lat = add.getLatitude();
            double lng = add.getLongitude();

            goToLocation(lat, lng, 15);
            //mMap.setMyLocationEnabled(true);
        }

    }

    private void geoLocateFromLatLng(Double lat, Double lng) throws IOException {
        Geocoder gc = new Geocoder(this);

        List<Address> list = gc.getFromLocation(lat, lng, 1);
        if (list.size() > 0) {
            Address add = list.get(0);
            String locality = add.getLocality();
            //Toast.makeText(this, "found" + locality, Toast.LENGTH_SHORT).show();


            goToLocation(lat, lng, 15);
            //mMap.setMyLocationEnabled(true);

        }

    }

    private String geoLocateFromLatLngReturn(Double lat, Double lng) throws IOException {
        Geocoder gc = new Geocoder(this);

        List<Address> list = gc.getFromLocation(lat, lng, 1);
        if (list.size() > 0) {
            Address add = list.get(0);
            String locality = add.getLocality();
            //Toast.makeText(this, "found" + locality, Toast.LENGTH_SHORT).show();


            goToLocation(lat, lng, 15);
            //mMap.setMyLocationEnabled(true);
            return locality;
        }
        return null;

    }

    public boolean servicesOK() {
        int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (result == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(result)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(result, this, DIALOG_REQUEST);
            dialog.show();
        } else {
        }
        return false;
    }

    private boolean initMap() {
        if (mMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
           mapFrag.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                }
            });
        }

        if (mMap != null) {
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_window, null);
                    TextView tvLocality = (TextView) v.findViewById(R.id.tvLocality);
                    TextView tvSnippet = (TextView) v.findViewById(R.id.tvSnippet);

                    LatLng latLng = marker.getPosition();
                    tvLocality.setText(marker.getTitle());
                    tvSnippet.setText(marker.getSnippet());

                    ImageView iv = (ImageView) v.findViewById(R.id.imageView1);
                    iv.setImageResource(imageResource);

                    return v;
                }
            });

        }

        return (mMap != null);

    }

    private void goToLocation(double lat, double lng, float zoom) {

        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);
    }

    private void animateToLocation(double lat, double lng, float zoom) {

        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.animateCamera(update);
    }

    @Override
    public void onConnected(Bundle bundle) {
        //Toast.makeText(this, "map is connected" , Toast.LENGTH_SHORT).show();
        int LOCATION_REFRESH_TIME = 1000;
        int LOCATION_REFRESH_DISTANCE = 5;


        // location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
//            LocationServices.FusedLocationApi.requestLocationUpdates(
//                    mGoogleApiClient, mLocationRequest, this);
        } else {
            currentLat = location.getLatitude();
            currentLng = location.getLongitude();

            //Toast.makeText(this, "map is connected" +currentLng , Toast.LENGTH_SHORT).show();
            handleNewLocation(location);
            MarkerOptions options = null;
            try {
                options = new MarkerOptions()
                        .title(geoLocateFromLatLngReturn(currentLat, currentLng))
                        .snippet("Current Location")
                        .position(new LatLng(currentLat, currentLng));
            } catch (IOException e) {
                e.printStackTrace();
            }

            mMap.addMarker(options);
            //if location i can get lat and lng and pass camera update
            //user animate camera
        }


        //Toast.makeText(this, String.valueOf(mCurrentLocation.getLatitude()), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Error-Location connection failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    private void handleNewLocation(Location location) {

        animateToLocation(currentLat, currentLng, 15);
        //Toast.makeText(this, "Current Latitude: " + String.valueOf(currentLat) + " Lng: " + String.valueOf(currentLng), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLocationChanged(Location location) {
        //Toast.makeText(this, "location Changed", Toast.LENGTH_SHORT).show();
        mCurrentLocation = location;
        location = location;
        currentLat = location.getLatitude();
        currentLng = location.getLongitude();
        handleNewLocation(location);

    }


    public void onTakeMeHere(View view) {

        if (selectedlng > 1 && selectedLat > 1) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "http://maps.google.com/maps?saddr=" + currentLat + ", " + currentLng + "&daddr=" + selectedLat + "," + selectedlng));
            startActivity(intent);

        } else {
            Toast.makeText(this, "Please select from the map or list the ATM point you want to navigate to",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //Toast.makeText(this, "Info window clicked",
        //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();

    }


}