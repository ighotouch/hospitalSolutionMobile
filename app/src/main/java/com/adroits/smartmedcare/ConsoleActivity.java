package com.adroits.smartmedcare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adroits.smartmedcare.adapters.CategotyListAdapter;
import com.adroits.smartmedcare.utils.rest.model.course.CourseCategory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
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
        getSupportActionBar().setTitle("WELLCOME MATTHEW");


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
//            if(selectedSection==5)
//            {
//                Intent intent = new Intent(MainActivity.this, MaternitySection.class);
//                MainActivity.this.startActivity(intent);
//            }
//            if(selectedSection==6)
//            {
//                Intent intent = new Intent(MainActivity.this, HealthTopic.class);
//                MainActivity.this.startActivity(intent);
//            }
//            if(selectedSection==7)
//            {
//                Intent intent = new Intent(MainActivity.this, MinistryPolice.class);
//                MainActivity.this.startActivity(intent);
//            }
//            if(selectedSection==8)
//            {
//                Intent intent = new Intent(MainActivity.this, HealthAssessment.class);
//                MainActivity.this.startActivity(intent);
//            }
//            if(selectedSection==9)
//            {
//                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
//                MainActivity.this.startActivity(intent);
//            }

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
}
