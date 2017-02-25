package com.adroits.smartmedcare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroits.smartmedcare.adapters.CategotyListAdapter;
import com.adroits.smartmedcare.adapters.InformationAdapter;
import com.adroits.smartmedcare.application.ElearnApplication;
import com.adroits.smartmedcare.dbmodels.DataProvider;
import com.adroits.smartmedcare.dbmodels.EmergencyPostObject;
import com.adroits.smartmedcare.utils.rest.model.Information;
import com.adroits.smartmedcare.utils.rest.model.course.CourseCategory;
import com.adroits.smartmedcare.utils.rest.service.SmartMedCareService;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConsoleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView signInBtn;
    TextView registerBtn;
    private Handler handler = new Handler();
    private RelativeLayout secondLayerBottomPanel;
    private boolean isAnimatingSlideOutPanel=false;
    private Integer selectedSection=null;

    @Inject
    Retrofit retrofit;
    Realm realm;

    public void postDelayedOnUI(Runnable work, long delay)
    {
        handler.postDelayed(work, delay);
    }

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_catelog);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("WELCOME MATTHEW");
        ElearnApplication.getComponent().inject(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
        secondLayerBottomPanel=(RelativeLayout) findViewById(R.id.secondLayerBottomPanel);
        secondLayerBottomPanel.setVisibility(View.INVISIBLE);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                slideInConsolePanel();
            }

        }, 1);

    }

    private void slideInConsolePanel()
    {
        Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.list_up);
        fromBottom.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                secondLayerBottomPanel.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {

            }
        });

        secondLayerBottomPanel.startAnimation(fromBottom);
    }

    private void slideOutConsolePanel()
    {
        Animation toBottom = AnimationUtils.loadAnimation(this, R.anim.list_down);
        toBottom.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                isAnimatingSlideOutPanel=true;
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                isAnimatingSlideOutPanel=false;
                secondLayerBottomPanel.setVisibility(View.INVISIBLE);

                if(selectedSection!=null)
                {
                    goToSelectedSection();

                }

            }
        });

        secondLayerBottomPanel.startAnimation(toBottom);
    }

    public void goToSelectedSection()
    {
        if(selectedSection!=null)
        {
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    //finish();
                }

            }, 1000);
//            if(selectedSection==1)
//            {
//                //Expert Locator
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//                builder1.setIcon(R.drawable.ic_launcher);
//                builder1.setTitle(R.string.app_name);
//                builder1.setMessage("Expert Locator is in development, coming soon");
//                builder1.setCancelable(false);
//                builder1.setNeutralButton("Close",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//
//                            }
//                        }
//                );
//
//                AlertDialog alert = builder1.create();
//                alert.show();
//            }
//            if(selectedSection==2)
//            {
//                //RM Tools
//                Intent intent = new Intent(MainActivity.this, InformationPortal.class);
//                MainActivity.this.startActivity(intent);
//            }
//            if(selectedSection==3)
//            {
//                Intent intent = new Intent(MainActivity.this, MyCoursesActivity.class);
//                MainActivity.this.startActivity(intent);
//            }
            if(selectedSection==4)
            {
                Intent intent = new Intent(ConsoleActivity.this, HospitalLocator.class);
                ConsoleActivity.this.startActivity(intent);
            }
            if(selectedSection==5)
            {
                Intent intent = new Intent(ConsoleActivity.this, MaritalStatusActivity.class);
                ConsoleActivity.this.startActivity(intent);
            }
            if(selectedSection==6)
            {
                Intent intent = new Intent(ConsoleActivity.this, InformationPortal.class);
                ConsoleActivity.this.startActivity(intent);
            }
            if(selectedSection==7)
            {
                Intent intent = new Intent(ConsoleActivity.this, MinistryPolice.class);
                ConsoleActivity.this.startActivity(intent);
            }
            if(selectedSection==8)
            {
                Intent intent = new Intent(ConsoleActivity.this, HealthAssessment.class);
                ConsoleActivity.this.startActivity(intent);
            }
            if(selectedSection==9)
            {
                Intent intent = new Intent(ConsoleActivity.this, PaymentActivity.class);
                ConsoleActivity.this.startActivity(intent);
            }
            if(selectedSection==10)
            {
                Intent intent = new Intent(ConsoleActivity.this, TopicOfTheWeekActivity.class);
                ConsoleActivity.this.startActivity(intent);
            }

        }
    }


    @Override
    public void onBackPressed() {
        new MaterialDialog.Builder(this)
                .title("Notice")
                .content("Are you sure you want to exit?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ConsoleActivity.this.finish();
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course_catelog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onHospitalLocator(View view) {
        selectedSection=4;
        if(!isAnimatingSlideOutPanel)
        {
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    slideOutConsolePanel();
                }

            }, 1);
        }
    }

    public void onMaternity(View view) {
        selectedSection=5;
        if(!isAnimatingSlideOutPanel)
        {
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    slideOutConsolePanel();
                }

            }, 1);
        }
    }

    public void onMinistryPolice(View view) {
        new MaterialDialog.Builder(this)
                .title("Report an offer")
                .content("Please complete the form")
                .inputType(InputType.TYPE_CLASS_TEXT )
                .inputType(InputType.TYPE_CLASS_TEXT )
                .inputType(InputType.TYPE_CLASS_TEXT )
                .input("Enter Offer name and report", null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        String messageToSend = input.toString();
                        EmergencyPostObject emergencyPostObject = new EmergencyPostObject();
                        emergencyPostObject.setText(messageToSend);

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"report@smarthealthcare.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Report");
                        i.putExtra(Intent.EXTRA_TEXT   , messageToSend);
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(ConsoleActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
//        selectedSection=7;
//        if(!isAnimatingSlideOutPanel)
//        {
//            handler.postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    slideOutConsolePanel();
//                }
//
//            }, 1);
//        }
    }

    public void onInformationPortal(View view) {
        selectedSection=6;
        if(!isAnimatingSlideOutPanel)
        {
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    slideOutConsolePanel();
                }

            }, 1);
        }
    }

    public void onRegistration(View view) {
        //
        List<String> facilitesForm = DataProvider.registrationForms;
        new MaterialDialog.Builder(this)
                .title("Facility Registration")
                .items(facilitesForm)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        selectedSection=9;
                        if(!isAnimatingSlideOutPanel)
                        {
                            handler.postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    slideOutConsolePanel();
                                }

                            }, 1);
                        }

                    }
                })

                .show();
    }

    public void onDrugVerification(View view) {
        new MaterialDialog.Builder(this)
                .title("Drug Validation")
                .content("Please enter the  number covered by the panel on the drug")
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .input("Enter digit", null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        String messageToSend = input.toString();
                        String number = "38353";

                        SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
                    }
                }).show();
    }

    public void onHealthTopic(View view) {
        new MaterialDialog.Builder(this)
                .title("Safe Exercise")
                .content("Let's face it, ladies: Doctor visits are short. And they're getting shorter. What if your doctor had more time? She might tell you the same things that OB-GYN Alyssa Dweck, MD, co-author of V Is for Vagina, wants you to know.\n" +
                        "\n" +
                        "Consider Dweck's tips your prescription for a lifetime of wellness.\n" +
                        "\n" +
                        "1. Zap your stress.\n" +
                        "\n" +
                        "\"The biggest issue I see in most of my patients is that they have too much on their plates and want to juggle it all. Stress can have significant health consequences, from infertility to higher risks of depression, anxiety, and heart disease. Find the stress-reduction method that works for you and stick with it.\"\n" +
                        "\n" +
                        "2. Stop dieting.\n" +
                        "\n" +
                        "\"Eating healthy doesn't mean you have to forgo your favorite glass of wine or a piece of chocolate cake now and then. The key is moderation. Get a mix of lean proteins, healthy fats, smart carbs, and fiber.\"\n" +
                        "\n" +
                        "3. Don't “OD” on calcium.\n" +
                        "\n" +
                        "\"Too much absorbed calcium can increase the risk of kidney stones and may even increase the risk of heart disease. If you're under 50, shoot for 1,000 milligrams per day, while over-50 women should be getting 1,200 milligrams per day mainly through diet -- about three servings of calcium-rich foods such as milk, salmon, and almonds.\"")
                .positiveText("Ok")
                .show();
    }

    public void onHealthRisk(View view) {
        selectedSection=8;
        if(!isAnimatingSlideOutPanel)
        {
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    slideOutConsolePanel();
                }

            }, 1);
        }
    }


    public void onEmergency(View view) {

        new MaterialDialog.Builder(this)
                .title("Send emergency report")
                .content("Please complete the form")
                .inputType(InputType.TYPE_CLASS_TEXT )
                .inputType(InputType.TYPE_CLASS_TEXT )
                .inputType(InputType.TYPE_CLASS_TEXT )
                .input("Enter Emergency", null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        String messageToSend = input.toString();
                        EmergencyPostObject emergencyPostObject = new EmergencyPostObject();
                        emergencyPostObject.setText(messageToSend);

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"emergency@smarthealthcare.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Emergency");
                        i.putExtra(Intent.EXTRA_TEXT   , messageToSend);
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(ConsoleActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
    }

    public void onTopicOfTheWeek(View view) {
        selectedSection=10;
        if(!isAnimatingSlideOutPanel)
        {
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    slideOutConsolePanel();
                }

            }, 1);
        }
    }
}
