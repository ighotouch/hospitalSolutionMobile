package com.adroits.smartmedcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.adroits.smartmedcare.adapters.FacilitiesListAdapter;
import com.adroits.smartmedcare.application.ElearnApplication;
import com.adroits.smartmedcare.dbmodels.DataProvider;
import com.adroits.smartmedcare.dbmodels.Facilities;
import com.adroits.smartmedcare.dbmodels.Facility;
import com.adroits.smartmedcare.utils.SessionManager;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class HospitalLocator extends AppCompatActivity  {

    int index = 0;

    Facilities facilitiesClicked;
    TextView globalText;
    String globalString = "Loading location..";
    private Handler handler = new Handler();
    static String deviceLocation;

    @Inject
    SessionManager sessionManager;

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_locator);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ElearnApplication.getComponent().inject(this);



        startIntro();
    }

    public void startIntro() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SectionFragment.newInstance(0, HospitalLocator.this, false))
                .commit();

    }



    public static class SectionFragment extends Fragment {
        private static final String TAG = "SectionFragment";
        private static HospitalLocator context = null;

        public static final String ARG_SECTION_NUMBER = "section_number";
        private int sectionNumber = 0;

        private boolean _backMode = false;
        Facilities facilitiesClicked;

        public void setBackMode(boolean backMode) {
            _backMode = backMode;
        }

        public boolean isBackMode() {
            return _backMode;
        }

        public Facilities getFacilityClicked() {
            return facilitiesClicked;
        }

        public void setFacilitiesClicked(Facilities facilities) {
            this.facilitiesClicked = facilities;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            sectionNumber = (getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 0);

        }


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SectionFragment newInstance(int sectionNumber, HospitalLocator activityContext, boolean backMode) {
            context = activityContext;
            SectionFragment fragment = new SectionFragment();
            fragment.setBackMode(backMode);
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);

            fragment.setArguments(args);


            return fragment;
        }

        public SectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(SectionFragment.TAG, "onCreateView() called for sectionNumber " + sectionNumber);
            if (context == null) {
                context = (HospitalLocator) getActivity();
            }


            switch (sectionNumber) {
                case 0: {
                    context.index = 0;
                    context.getSupportActionBar().setTitle("Facility Locator");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.facility_locator), null);
                    //context.loadFacilitiesTitle(fragmentView, _backMode);
                    context.globalText = (TextView) fragmentView.findViewById(R.id.descLabel);
                    String location =  context.sessionManager.getGeoLocation();
                    context.globalText.setText(location);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);

                    final List<Facilities> facilites = new ArrayList<>();
                    Facilities f1 = new Facilities("Government Hospitals", "hospital");
                    Facilities f2 = new Facilities("Primary HealthCare Centre", "hospital");
                    Facilities f4 = new Facilities("Private Hospitals", "hospital");
                    Facilities f3 = new Facilities("Pharmaceutical Stores", "hospital");

                    facilites.add(f1);
                    facilites.add(f2);
                    facilites.add(f3);
                    facilites.add(f4);

                    FacilitiesListAdapter adapter = new FacilitiesListAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new FacilitiesListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
//                        ModuleCourse course = categories.get(position);
//                        startModule(course);
                            context.facilitiesClicked = facilites.get(position);
                            setFacilitiesClicked(facilites.get(position));
                            context.mapIntro();

                        }
                    });


                    return fragmentView;
                }

                case 1: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Facility Locator");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.facility_locator), null);
                    context.globalText = (TextView) fragmentView.findViewById(R.id.descLabel);
                    String location =  context.sessionManager.getGeoLocation();
                    context.globalText.setText(location);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);

                    final List<Facilities> facilites = new ArrayList<>();
                    Facilities f1 = new Facilities("Around my Location", "stores");
                    Facilities f2 = new Facilities("Select Location", "hospital");


                    facilites.add(f1);
                    facilites.add(f2);


                    FacilitiesListAdapter adapter = new FacilitiesListAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new FacilitiesListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Facilities selected = facilites.get(position);
                            String areas[] = {"Delta", "lagos", "Ogun", "Sokoto"};
                            if (selected.getTitle().equals("Select Location")) {

                                Log.d("facilitycli:", context.facilitiesClicked.getTitle());

                                new MaterialDialog.Builder(context)
                                        .title("LGA")
                                        .items(DataProvider.lgaNames)
                                        .itemsCallback(new MaterialDialog.ListCallback() {
                                            @Override
                                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                                String selectedLga = String.valueOf(text);

                                                if (context.facilitiesClicked.getTitle().equals("Government Hospitals")) {
                                                    List<String> hospitalUnderSelectedLga = new ArrayList<String>();
                                                    for (Facility generalHospital : DataProvider.facilities) {
                                                        if (generalHospital.getLga().equals(selectedLga) && generalHospital.getFacilityType().equalsIgnoreCase("general hospital")) {
                                                            Log.d("selected Lga:", generalHospital.getLga().getAddress());
                                                            hospitalUnderSelectedLga.add(generalHospital.getTitle());
                                                        }
                                                    }
                                                    if (hospitalUnderSelectedLga.size() > 0) {
                                                        new MaterialDialog.Builder(context)
                                                                .title("General Hospitals in " + selectedLga)
                                                                .items(hospitalUnderSelectedLga)
                                                                .itemsCallback(new MaterialDialog.ListCallback() {
                                                                    @Override
                                                                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                                                        String selectGeneralHospital = String.valueOf(text);
                                                                        for (Facility generalHospital : DataProvider.facilities) {
                                                                            if (generalHospital.getTitle().equals(selectGeneralHospital)) {
                                                                                Log.d("selected Lga:", "Starting map");
                                                                                Intent intent = new Intent(context, DetailActivity.class);
                                                                                intent.putExtra("lat", Double.valueOf(generalHospital.getLatitude()));
                                                                                intent.putExtra("long", Double.valueOf(generalHospital.getLongitude()));
                                                                                context.startActivity(intent);
                                                                            }
                                                                        }


                                                                    }
                                                                })

                                                                .show();

                                                    } else {
                                                        new MaterialDialog.Builder(context)
                                                                .title("General Hospitals in " + selectedLga)
                                                                .content("No general hospital listed in " + selectedLga + " for now!")
                                                                .positiveText("Ok")
                                                                .show();
                                                    }
                                                } else if (context.facilitiesClicked.getTitle().equals("Private Hospitals")) {
                                                    List<String> hospitalUnderSelectedLga = new ArrayList<String>();
                                                    for (Facility privateHospital : DataProvider.facilities) {
                                                        if (privateHospital.getLga().equals(selectedLga)) {
                                                            Log.d("selected Lga:", privateHospital.getLga().getAddress());
                                                            hospitalUnderSelectedLga.add(privateHospital.getTitle());
                                                        }
                                                    }
                                                    if (hospitalUnderSelectedLga.size() > 0) {
                                                        new MaterialDialog.Builder(context)
                                                                .title("Private Hospitals in " + selectedLga)
                                                                .items(hospitalUnderSelectedLga)
                                                                .itemsCallback(new MaterialDialog.ListCallback() {
                                                                    @Override
                                                                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                                                        String selectGeneralHospital = String.valueOf(text);
                                                                        for (Facility privateHospital : DataProvider.facilities) {
                                                                            if (privateHospital.getTitle().equals(selectGeneralHospital)) {
                                                                                Log.d("selected Lga:", "Starting map");
                                                                                Intent intent = new Intent(context, DetailActivity.class);
                                                                                intent.putExtra("lat", Double.valueOf(privateHospital.getLatitude()));
                                                                                intent.putExtra("long", Double.valueOf(privateHospital.getLongitude()));
                                                                                context.startActivity(intent);
                                                                            }
                                                                        }


                                                                    }
                                                                })

                                                                .show();

                                                    } else {
                                                        new MaterialDialog.Builder(context)
                                                                .title("General Hospitals in " + selectedLga)
                                                                .content("No general hospital listed in " + selectedLga + " for now!")
                                                                .positiveText("Ok")
                                                                .show();
                                                    }
                                                } else if (context.facilitiesClicked.getTitle().equals("Pharmaceutical Stores")) {
                                                    List<String> hospitalUnderSelectedLga = new ArrayList<String>();
                                                    for (Facility pharmaceuticalStores : DataProvider.facilities) {
                                                        if (pharmaceuticalStores.getLga().equals(selectedLga)) {
                                                            Log.d("selected Lga:", pharmaceuticalStores.getLga().getAddress());
                                                            hospitalUnderSelectedLga.add(pharmaceuticalStores.getTitle());
                                                        }
                                                    }
                                                    if (hospitalUnderSelectedLga.size() > 0) {
                                                        new MaterialDialog.Builder(context)
                                                                .title("Pharmaceutical Stores in " + selectedLga)
                                                                .items(hospitalUnderSelectedLga)
                                                                .itemsCallback(new MaterialDialog.ListCallback() {
                                                                    @Override
                                                                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                                                        String selectGeneralHospital = String.valueOf(text);
                                                                        for (Facility pharmaceuticalStores : DataProvider.facilities) {
                                                                            if (pharmaceuticalStores.getTitle().equals(selectGeneralHospital)) {
                                                                                Log.d("selected Lga:", "Starting map");
                                                                                Intent intent = new Intent(context, DetailActivity.class);
                                                                                intent.putExtra("lat", Double.valueOf(pharmaceuticalStores.getLatitude()));
                                                                                intent.putExtra("long", Double.valueOf(pharmaceuticalStores.getLongitude()));
                                                                                context.startActivity(intent);
                                                                            }
                                                                        }


                                                                    }
                                                                })

                                                                .show();

                                                    } else {
                                                        new MaterialDialog.Builder(context)
                                                                .title("General Hospitals in " + selectedLga)
                                                                .content("No general hospital listed in " + selectedLga + " for now!")
                                                                .positiveText("Ok")
                                                                .show();
                                                    }
                                                }else {
                                                    Intent intent = new Intent(context, DetailActivity.class);
                                                    context.startActivity(intent);
                                                }

                                            }
                                        })
                                        .positiveText("Choose")
                                        .show();
                            } else {

                                Intent intent = new Intent(context, DetailActivity.class);


                                context.startActivity(intent);
                            }

                        }
                    });

                    return fragmentView;
                }


                default: {
                    //context.loadCourseList(fragmentView, _backMode);

                    return null;

                }
            }


        }

    }

    private void goToMap() {
    }





    public void mapIntro() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment = SectionFragment.newInstance(1, HospitalLocator.this, false);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_right, R.anim.push_out_to_left);
        ft.replace(R.id.fragmentContainer, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        if (index == 1) {

            goBack(0);
        } else if (index == 0) {
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    finish();
                }

            }, 1000);

            Intent intent = new Intent(HospitalLocator.this, ConsoleActivity.class);
            HospitalLocator.this.startActivity(intent);
        }


    }

    public void goBack(int viewToReturnTo) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_left2, R.anim.push_out_to_right);

        int newViewIndex = viewToReturnTo;
        SectionFragment section = SectionFragment.newInstance(viewToReturnTo, HospitalLocator.this, true);


        ft.replace(R.id.fragmentContainer, section).commit();
    }



}

