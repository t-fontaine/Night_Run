package com.example.nightrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //SoundPlayer.playIntroHowl();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // splash screen appears for 7 seconds
                    sleep(7000);
                    //SoundPlayer.playIntroHowl();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(mainIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}