package com.adroits.smartmedcare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.adroits.smartmedcare.adapters.EventsAdapter;
import com.adroits.smartmedcare.adapters.FacilitiesListAdapter;
import com.adroits.smartmedcare.adapters.InformationPortalListAdapter;
import com.adroits.smartmedcare.dbmodels.Facilities;
import com.adroits.smartmedcare.dbmodels.InformationCentre;
import com.adroits.smartmedcare.utils.ClearableEditText;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

public class HealthAssessment extends AppCompatActivity {
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
                .replace(R.id.fragmentContainer, SectionFragment.newInstance(0, HealthAssessment.this, false))
                .commit();

    }

    public void onCalculate(View view) {

    }

    public static class SectionFragment extends Fragment {
        private static final String TAG = "SectionFragment";
        private static HealthAssessment context = null;

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
        public static SectionFragment newInstance(int sectionNumber, HealthAssessment activityContext, boolean backMode) {
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
                context = (HealthAssessment) getActivity();
            }


            switch (sectionNumber) {
                case 0: {
                    context.index = 0;
                    context.getSupportActionBar().setTitle("Health Assessment");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.information_center), null);
                    ImageView image = (ImageView) fragmentView.findViewById(R.id.headerImage);
                    TextView text = (TextView) fragmentView.findViewById(R.id.descLabel);
                    text.setText("Health Assessment");
                    image.setVisibility(View.GONE);
                    //context.loadFacilitiesTitle(fragmentView, _backMode);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);

                    final List<InformationCentre> facilites = new ArrayList<>();
                    InformationCentre f1 = new InformationCentre("Obesity","info");
                    InformationCentre f2 = new InformationCentre("Mental Health","info");
                    InformationCentre f3 = new InformationCentre("Smoking","info");
                    InformationCentre f4 = new InformationCentre("Physical Activities","info");
                    InformationCentre f5 = new InformationCentre("Heart Disease","info");
                    InformationCentre f6 = new InformationCentre("Diabetes","info");


                    facilites.add(f1);
                    facilites.add(f2);
                    facilites.add(f3);
                    facilites.add(f4);
                    facilites.add(f5);
                    facilites.add(f6);


                    InformationPortalListAdapter adapter = new InformationPortalListAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new InformationPortalListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                        InformationCentre selected = facilites.get(position);
                            if(selected.getTitle().equals("Obesity")){
                                context.eventsIntro();
                            }

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
                    context.getSupportActionBar().setTitle("Obesity");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.assessment), null);
                    ClearableEditText passwordConfirm = (ClearableEditText) fragmentView.findViewById(R.id.age);
                    passwordConfirm.setHintText("Age");
                    passwordConfirm.setTextColor(Color.BLACK);
                    passwordConfirm.setHintTextColor(Color.LTGRAY);
                    passwordConfirm.getInternalEditText().setInputType(InputType.TYPE_CLASS_PHONE );
                    passwordConfirm.setCloseIcon(R.drawable.close);
                    passwordConfirm.setStartIcon(R.drawable.age);

                    final ClearableEditText weight = (ClearableEditText) fragmentView.findViewById(R.id.weight);
                    weight.setHintText("Weight");
                    weight.setTextColor(Color.BLACK);
                    weight.setHintTextColor(Color.LTGRAY);
                    weight.getInternalEditText().setInputType(InputType.TYPE_CLASS_PHONE );
                    weight.setCloseIcon(R.drawable.close);
                    weight.setStartIcon(R.drawable.weight);

                    final ClearableEditText heighet = (ClearableEditText) fragmentView.findViewById(R.id.height);
                    Button button = (Button) fragmentView.findViewById(R.id.button);
                    heighet.setHintText("Height");
                    heighet.setTextColor(Color.BLACK);
                    heighet.setHintTextColor(Color.LTGRAY);
                    heighet.getInternalEditText().setInputType(InputType.TYPE_CLASS_PHONE);
                    heighet.setCloseIcon(R.drawable.close);
                    heighet.setStartIcon(R.drawable.height);



                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                final int bmi = Integer.valueOf(weight.getText().toString()) * (Integer.valueOf(heighet.getText().toString()) * Integer.valueOf(heighet.getText().toString()));
                                new MaterialDialog.Builder(getActivity())
                                        .title("Obesity Assessment Result")
                                        .content("BMI: " + String.valueOf(bmi) + "\n" +
                                                "underweight (BMI less than 18.5)\n" +
                                                "normal weight (BMI between 18.5 & 24.9)\n" +
                                                "overweight (BMI between 25.0 & 29.9)\n" +
                                                "obese (BMI 30.0 and above)")
                                        .positiveText("OK")
                                        .show();
                            }catch (Exception e){

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
                    //context.loadCourseList(fragmentView, _backMode);

                    return null;

                }
            }


        }

    }





    public void mapIntro()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment= SectionFragment.newInstance(1, HealthAssessment.this, false);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_right, R.anim.push_out_to_left);
        ft.replace(R.id.fragmentContainer, fragment).commit();

    }

    public void eventsIntro()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment= SectionFragment.newInstance(2, HealthAssessment.this, false);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_right, R.anim.push_out_to_left);
        ft.replace(R.id.fragmentContainer, fragment).commit();

    }

    public void announcementIntro()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment= SectionFragment.newInstance(3, HealthAssessment.this, false);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_right, R.anim.push_out_to_left);
        ft.replace(R.id.fragmentContainer, fragment).commit();

    }

    public void officialsIntro()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment= SectionFragment.newInstance(4, HealthAssessment.this, false);

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

                        Intent intent = new Intent(HealthAssessment.this, ConsoleActivity.class);
                        HealthAssessment.this.startActivity(intent);
                    }



    }

    public void goBack(int viewToReturnTo)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_left2, R.anim.push_out_to_right);

        int newViewIndex = viewToReturnTo;
        SectionFragment section= SectionFragment.newInstance(viewToReturnTo, HealthAssessment.this, true);


        ft.replace(R.id.fragmentContainer, section).commit();
    }



}

