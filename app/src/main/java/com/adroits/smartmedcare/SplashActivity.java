package com.adroits.smartmedcare;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adroits.smartmedcare.application.ElearnApplication;
import com.adroits.smartmedcare.utils.SessionManager;
import com.adroits.smartmedcare.utils.utils.CommonConstants;
import com.adroits.smartmedcare.utils.utils.FetchAddressIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private View logo;

    protected Location mLastLocation;
    private AddressResultReceiver mResultReceiver;
    Double latitude;
    Double longitude;
    final static int REQUEST_LOCATION = 199;
    GoogleApiClient mGoogleApiClient;

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ElearnApplication.getComponent().inject(this);
        logo =  findViewById(R.id.splashLogo);
        logo.setVisibility(View.VISIBLE);
        logo.setAlpha(0.0f);

        mResultReceiver = new AddressResultReceiver(new Handler());
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            logo.animate().alpha(1.0f).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            /* Create an Intent that will start the Menu-Activity. */
                            SplashActivity.this.finish();
                            Intent intent = new Intent(SplashActivity.this, ConsoleActivity.class);


                            // create the transition animation - the images in the layouts
                            // of both activities are defined with android:transitionName="robot"
                            ActivityOptions options = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                options = ActivityOptions
                                        .makeSceneTransitionAnimation(SplashActivity.this, logo, "robot");
                                // start the new activity
                                startActivity(intent, options.toBundle());
                            }

                            //else make normal intent


                        }
                    }, SPLASH_DISPLAY_LENGTH);

                }


                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        createLocationRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string
            // or an error message sent from the intent service.
            String mAddressOutput = resultData.getString(CommonConstants.RESULT_DATA_KEY);
           // updateUI(mAddressOutput);

            sessionManager.saveGeolocation(mAddressOutput);

            // Show a toast message if an address was found.
            if (resultCode == CommonConstants.SUCCESS_RESULT) {
                //showToast(getString(R.string.address_found));
            }

        }
    }

    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                final LocationSettingsStates locationSettingsStates = locationSettingsResult.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.
                        if (ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                            Toast.makeText(SplashActivity.this, "No permission", Toast.LENGTH_LONG);
                            return;
                        }
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                mGoogleApiClient);
                        if (mLastLocation != null) {
                            Toast.makeText(SplashActivity.this, String.valueOf(mLastLocation.getLatitude()), Toast.LENGTH_LONG).show();
                            latitude = mLastLocation.getLatitude();
                            longitude = mLastLocation.getLongitude();
                            sessionManager.setCoordinates(latitude.floatValue(),longitude.floatValue());
                            // mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
                            startIntentService();
                            //requestLocationCall(latitude, longitude);
                        }

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    SplashActivity.this,
                                    REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.

                        break;
                }

            }
        });

    }

    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(CommonConstants.RECEIVER, mResultReceiver);
        intent.putExtra(CommonConstants.LOCATION_DATA_EXTRA, mLastLocation);
        startService(intent);
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
