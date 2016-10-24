package com.adroits.smartmedcare;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private View logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo =  findViewById(R.id.splashLogo);
        logo.setVisibility(View.VISIBLE);
        logo.setAlpha(0.0f);


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
}
