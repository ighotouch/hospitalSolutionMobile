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
import android.widget.TextView;

import com.adroits.smartmedcare.adapters.FacilitiesListAdapter;
import com.adroits.smartmedcare.adapters.InformationAdapter;
import com.adroits.smartmedcare.adapters.InformationPortalListAdapter;
import com.adroits.smartmedcare.application.ElearnApplication;
import com.adroits.smartmedcare.dbmodels.Facilities;
import com.adroits.smartmedcare.dbmodels.InformationCentre;
import com.adroits.smartmedcare.utils.rest.model.Information;
import com.adroits.smartmedcare.utils.rest.service.SmartMedCareService;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TopicOfTheWeekActivity extends AppCompatActivity {
    int index = 0;
    private Handler handler = new Handler();
    @Inject
    Retrofit retrofit;

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_portal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ElearnApplication.getComponent().inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        startIntro();
    }

    public void startIntro() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SectionFragment.newInstance(2, TopicOfTheWeekActivity.this, false))
                .commit();

    }


    public static class SectionFragment extends Fragment {
        private static final String TAG = "SectionFragment";
        private static TopicOfTheWeekActivity context = null;

        public static final String ARG_SECTION_NUMBER = "section_number";
        private int sectionNumber = 0;
        @Inject
        Retrofit retrofit;
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
            ElearnApplication.getComponent().inject(this);
            sectionNumber = (getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 0);

        }

        List<Information> informations;


        MaterialDialog dialog;

        public void showDialog(String msg) {
            MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                    .title("Loading")
                    .content(msg)
                    .cancelable(false)
                    .autoDismiss(false)
                    .progress(true, 0);

            dialog = builder.build();
            dialog.show();
        }

        public void dismissDialog() {
            dialog.dismiss();
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SectionFragment newInstance(int sectionNumber, TopicOfTheWeekActivity activityContext, boolean backMode) {
            context = activityContext;
            //ElearnApplication.getComponent().inject(this);
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
                context = (TopicOfTheWeekActivity) getActivity();
            }


            switch (sectionNumber) {
                case 0: {
                    context.index = 0;
                    context.getSupportActionBar().setTitle("Information Portal");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.information_center), null);
                    //context.loadFacilitiesTitle(fragmentView, _backMode);
                    RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);
                    final List<InformationCentre> facilites = new ArrayList<>();


                    InformationCentre f1 = new InformationCentre("Public Announcement", "announcement");
                    InformationCentre f2 = new InformationCentre("Events", "hospital");
//                    InformationCentre f3 = new InformationCentre("Awards", "awards");
//                    InformationCentre f5 = new InformationCentre("Ministry Offices", "officies");
//                    InformationCentre f6 = new InformationCentre("Ministry Officials", "profile");

                    facilites.add(f1);
                    facilites.add(f2);
//                    facilites.add(f3);
//                    facilites.add(f5);
//                    facilites.add(f6);

                    InformationPortalListAdapter adapter = new InformationPortalListAdapter(facilites);
                    rvContacts.setAdapter(adapter);
                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                    adapter.setOnItemClickListener(new InformationPortalListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            InformationCentre selected = facilites.get(position);
                            if (selected.getTitle().equals("Public Announcement")) {
                                context.announcementIntro();
                            } else if (selected.getTitle().equals("Awards")) {
                                context.eventsIntro();
                            } else if (selected.getTitle().equals("About The Ministry")) {
                                context.eventsIntro();
                            } else if (selected.getTitle().equals("Ministry Offices")) {
                                context.eventsIntro();
                            } else if (selected.getTitle().equals("Ministry Officials")) {
                                context.officialsIntro();
                            } else {
                                context.eventsIntro();
                            }

                        }
                    });


                    return fragmentView;
                }

                case 1: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Topic of The Week");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.facility_locator), null);
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
                                new MaterialDialog.Builder(context)
                                        .title("Location")
                                        .items(areas)
                                        .cancelable(false)
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
                            } else {

                                Intent intent = new Intent(context, DetailActivity.class);
                                context.startActivity(intent);
                            }

                        }
                    });

                    return fragmentView;
                }

                case 2: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Topics of the Week");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.events), null);
                    final RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);

                    showDialog("Loading Events");

                    SmartMedCareService taskService = this.retrofit.create(SmartMedCareService.class);
                    Call<List<Information>> call = taskService.getTopicOfTheWeek();
                    call.enqueue(new Callback<List<Information>>() {
                        @Override
                        public void onResponse(Call<List<Information>> call, Response<List<Information>> response) {
                            if (response.isSuccessful()) {
                                informations = response.body();
                                dismissDialog();
                                if (informations.size() > 0) {
                                    InformationAdapter adapter = new InformationAdapter(informations);
                                    rvContacts.setAdapter(adapter);
                                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                                    adapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Information selected = informations.get(position);


                                        }
                                    });
                                }else{
                                    new MaterialDialog.Builder(context)
                                            .title("Oops")
                                            .cancelable(false)
                                            .content("No record available at this time!")
                                            .positiveText("OK")
                                            .show();
                                }
                            } else {
                                new MaterialDialog.Builder(context)
                                        .title("Error")
                                        .content(response.errorBody().toString())
                                        .positiveText("OK")
                                        .show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Information>> call, Throwable t) {
                            // something went completely south (like no internet connection)
                            Log.d("Error", t.getMessage());
                        }
                    });






                    return fragmentView;
                }

                case 3: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Announcement");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.events), null);
                    final RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);
                    TextView textView = (TextView) fragmentView.findViewById(R.id.descLabel);
                    textView.setText("");


                    showDialog("Loading Announcements");

                    SmartMedCareService taskService = this.retrofit.create(SmartMedCareService.class);
                    Call<List<Information>> call = taskService.getInformations();
                    call.enqueue(new Callback<List<Information>>() {
                        @Override
                        public void onResponse(Call<List<Information>> call, Response<List<Information>> response) {
                            if (response.isSuccessful()) {
                                informations = response.body();
                                dismissDialog();
                                if (informations.size() > 0) {
                                    InformationAdapter adapter = new InformationAdapter(informations);
                                    rvContacts.setAdapter(adapter);
                                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                                    adapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
//

                                        }
                                    });
                                }else{
                                    new MaterialDialog.Builder(context)
                                            .title("Oops")
                                            .content("No record available at this time!")
                                            .positiveText("OK")
                                            .show();
                                }
                            } else {
                                new MaterialDialog.Builder(context)
                                        .title("Error")
                                        .content(response.errorBody().toString())
                                        .positiveText("OK")
                                        .show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Information>> call, Throwable t) {
                            // something went completely south (like no internet connection)
                            Log.d("Error", t.getMessage());
                        }
                    });




                    return fragmentView;
                }

                case 4: {
                    context.index = 1;
                    context.getSupportActionBar().setTitle("Ministry Officials");
                    View fragmentView = inflater.inflate(context.getResources().getLayout(R.layout.officials), null);
                    final RecyclerView rvContacts = (RecyclerView) fragmentView.findViewById(R.id.list_items);
                    TextView textView = (TextView) fragmentView.findViewById(R.id.descLabel);
                    textView.setText("Ministry Officials");

                    showDialog("Loading Events");

                    SmartMedCareService taskService = this.retrofit.create(SmartMedCareService.class);
                    Call<List<Information>> call = taskService.getOfficials();
                    call.enqueue(new Callback<List<Information>>() {
                        @Override
                        public void onResponse(Call<List<Information>> call, Response<List<Information>> response) {
                            if (response.isSuccessful()) {
                                informations = response.body();
                                dismissDialog();
                                if (informations.size() > 0) {
                                    InformationAdapter adapter = new InformationAdapter(informations);
                                    rvContacts.setAdapter(adapter);
                                    rvContacts.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

                                    adapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Information selected = informations.get(position);


                                        }
                                    });
                                }else{
                                    new MaterialDialog.Builder(context)
                                            .title("Oops")
                                            .content("No record available at this time!")
                                            .positiveText("OK")
                                            .show();
                                }
                            } else {
                                new MaterialDialog.Builder(context)
                                        .title("Error")
                                        .content(response.errorBody().toString())
                                        .positiveText("OK")
                                        .show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Information>> call, Throwable t) {
                            // something went completely south (like no internet connection)
                            Log.d("Error", t.getMessage());
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


    public void eventsIntro() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment = SectionFragment.newInstance(2, TopicOfTheWeekActivity.this, false);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_right, R.anim.push_out_to_left);
        ft.replace(R.id.fragmentContainer, fragment).commit();

    }

    public void announcementIntro() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment = SectionFragment.newInstance(3, TopicOfTheWeekActivity.this, false);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_right, R.anim.push_out_to_left);
        ft.replace(R.id.fragmentContainer, fragment).commit();

    }

    public void officialsIntro() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SectionFragment fragment = SectionFragment.newInstance(4, TopicOfTheWeekActivity.this, false);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_right, R.anim.push_out_to_left);
        ft.replace(R.id.fragmentContainer, fragment).commit();

    }

    @Override
    public void onBackPressed() {

            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    finish();
                }

            }, 1000);

            Intent intent = new Intent(TopicOfTheWeekActivity.this, ConsoleActivity.class);
            TopicOfTheWeekActivity.this.startActivity(intent);



    }

    public void goBack(int viewToReturnTo) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.pull_in_from_left2, R.anim.push_out_to_right);

        int newViewIndex = viewToReturnTo;
        SectionFragment section = SectionFragment.newInstance(viewToReturnTo, TopicOfTheWeekActivity.this, true);


        ft.replace(R.id.fragmentContainer, section).commit();
    }


}

