package com.foodona.foodona.Restarant.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.foodona.foodona.MainActivity;
import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Utils.AppPreferences;


public class Splash_Activity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    RelativeLayout rel;
    String id;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //This method is used so that your splash activity
        //can cover the entire screen.
        setContentView(R.layout.activity_splash);
        rel=findViewById(R.id.rel);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        id = AppPreferences.getSavedUser(Splash_Activity.this).getId();

        rel.setAnimation(uptodown);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* if (SharedPrefManager.getInstance(SplashActivity.this).isLoggedIn()) {
                    finish();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }*/

                if (id.equals("-1")) {
                    Intent intent = new Intent(Splash_Activity.this, Sign_In_Activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
