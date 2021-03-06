package com.adroits.smartmedcare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.TextView;


import com.adroits.smartmedcare.adapters.EventsAdapter;
import com.adroits.smartmedcare.adapters.FacilitiesListAdapter;
import com.adroits.smartmedcare.adapters.InformationPortalListAdapter;
import com.adroits.smartmedcare.dbmodels.Facilities;
import com.adroits.smartmedcare.dbmodels.InformationCentre;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

public class MinistryPolice extends AppCompatActivity {
    int index = 0;
    private Handler handler = new Handler();

    @Override
    public void finish()
    {
        super.finish();

            overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_portal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startIntro();
    }

    public void startIntro()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SectionFragment.newInstance(0, MinistryPolice.this, false))
                .commit();

    }

    public static class SectionFragment extends Fragment {
        private static final String TAG = "SectionFragment";
        private static MinistryPolice context = null;

        public static final String ARG_SECTION_NUMBER = "section_number";
        private int sectionNumber = 0;

        private boolean _backMode = false;

        public void setBackMode(boolean backMode) {
            _backMode = backMode;
        }

        public boolean isBackMode() {
            return _backMode;
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
        public static SectionFragment newInstance(int sectionNumber, MinistryPolice activityContext, boolean backMode) {
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
                context = (MinistryPolice) getActivity();
            }


            switch (sectionNumber) {
                case 0: {
                    context.index = 0;
                    context.getSupportActionBar().setTitle("Ministry Police");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.information_center), null);
                    ImageView image = (ImageView) fragmentView.findViewById(R.id.headerImage);
                    TextView text = (TextView) fragmentView.findViewById(R.id.descLabel);
                    text.setText("Ministry Police");
                    image.setVisibility(View.GONE);
                    //context.loadFacilitiesTitle(fragmentView, _backMode);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);

                    final List<InformationCentre> facilites = new ArrayList<>();
                    InformationCentre f1 = new InformationCentre("Report an Official","info");
                    InformationCentre f2 = new InformationCentre("Commend an Official","info");
                    InformationCentre f3 = new InformationCentre("Report Health Hazard","info");
                    InformationCentre f4 = new InformationCentre("Report Hospital","info");
                    InformationCentre f5 = new InformationCentre("Report Pharmacy","info");

                    facilites.add(f1);
                    facilites.add(f2);
                    facilites.add(f3);
                    facilites.add(f4);
                    facilites.add(f5);

                    InformationPortalListAdapter adapter = new InformationPortalListAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new InformationPortalListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
//                        InformationCentre selected = facilites.get(position);
//                            if(selected.getTitle().equals("Public Announcement")){
//                                context.announcementIntro();
//                            }else if(selected.getTitle().equals("Awards")){
//                                context.eventsIntro();
//                            }else if(selected.getTitle().equals("About The Ministry")){
//                                context.eventsIntro();
//                            }else if(selected.getTitle().equals("Ministry Offices")){
//                                context.eventsIntro();
//                            }else if(selected.getTitle().equals("Ministry Officials")){
//                                context.officialsIntro();
//                            }else {
//                                context.eventsIntro();
//                            }

                        }
                    });


                return fragmentView;
                }

                case 1: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Category");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.facility_locator), null);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);

                    final List<Facilities> facilites = new ArrayList<>();
                    Facilities f1 = new Facilities("Around my Location","stores");
                    Facilities f2 = new Facilities("Select Location","hospital");


                    facilites.add(f1);
                    facilites.add(f2);


                    FacilitiesListAdapter adapter = new FacilitiesListAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new FacilitiesListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Facilities selected = facilites.get(position);
                            String areas[] = {"Delta","lagos","Ogun","Sokoto"};
                            if(selected.getTitle().equals("Select Location")){
                                new MaterialDialog.Builder(context)
                                        .title("Location")
                                        .items(areas)
                                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                            @Override
                                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                                Intent intent = new Intent(context, DetailActivity.class);
                                                context.startActivity(intent);
                                                return true;
                                            }
                                        })
                                        .positiveText("Choose")
                                        .show();
                            }else {

                                Intent intent = new Intent(context, DetailActivity.class);
                                context.startActivity(intent);
                            }

                        }
                    });

                    return fragmentView;
                }

                case 2: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Events");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.events), null);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);

                    final List<Facilities> facilites = new ArrayList<>();
                    Facilities f1 = new Facilities("Ebola Lectures","event");
                    Facilities f2 = new Facilities("Fight Against Cancer","events");


                    facilites.add(f1);
                    facilites.add(f2);


                    EventsAdapter adapter = new EventsAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new EventsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Facilities selected = facilites.get(position);
                            String areas[] = {"Delta","lagos","Ogun","Sokoto"};
                            if(selected.getTitle().equals("Select Location")){
                                new MaterialDialog.Builder(context)
                                        .title("Location")
                                        .items(areas)
                                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                            @Override
                                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                                Intent intent = new Intent(context, DetailActivity.class);
                                                context.startActivity(intent);
                                                return true;
                                            }
                                        })
                                        .positiveText("Choose")
                                        .show();
                            }else {

                                Intent intent = new Intent(context, DetailActivity.class);
                                context.startActivity(intent);
                            }

                        }
                    });

                    return fragmentView;
                }

                case 3: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Announcement");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.events), null);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);
                    TextView textView = (TextView) fragmentView.findViewById(R.id.descLabel);
                    textView.setText("Announcements");

                    final List<Facilities> facilites = new ArrayList<>();
                    Facilities f1 = new Facilities("Free Heath Care","announcement");


                    facilites.add(f1);


                    EventsAdapter adapter = new EventsAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new EventsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
//

                        }
                    });

                    return fragmentView;
                }

                case 4: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Ministry Officials");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.officials), null);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);
                    TextView textView = (TextView) fragmentView.findViewById(R.id.descLabel);
                    textView.setText("Ministry Officials");

                    final List<Facilities> facilites = new ArrayList<>();
                    Facilities f1 = new Facilities("Dr Adeyemi folare","profile");


                    facilites.add(f1);


                    EventsAdapter adapter = new EventsAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new EventsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
//

                        }
                    });

                    return fragmentView;
                }


                default: {
                   // View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.knowledge_repo_intro), null);
                    //context.loadCourseList(fragmentView, _backMode);

                    return null;

                }
            }


        }

    }

    

   

    public void mapIntro()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment= SectionFragment.newInstance(1, MinistryPolice.this, false);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_right, R.anim.push_out_to_left);
        ft.replace(R.id.fragmentContainer, fragment).commit();

    }

    

    @Override
    public void onBackPressed()
    {
                    if(index == 1) {

                        goBack(0);
                    }else if (index == 0){
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                finish();
                            }

                        }, 1000);

                        Intent intent = new Intent(MinistryPolice.this, ConsoleActivity.class);
                        MinistryPolice.this.startActivity(intent);
                    }



    }

    public void goBack(int viewToReturnTo)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_left2, R.anim.push_out_to_right);

        int newViewIndex = viewToReturnTo;
        SectionFragment section= SectionFragment.newInstance(viewToReturnTo, MinistryPolice.this, true);


        ft.replace(R.id.fragmentContainer, section).commit();
    }



}

