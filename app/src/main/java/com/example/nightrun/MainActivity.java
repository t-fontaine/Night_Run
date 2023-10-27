/**
 * Laura Messick
 * and
 * Trey Fontaine
 */


package com.example.nightrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private WolfView wolfgameview;
    private Handler handler = new Handler();
    private final static long Interval = 30;

    public SoundPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        sound = new SoundPlayer(this);

        wolfgameview = new WolfView(this);
        setContentView(wolfgameview);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        wolfgameview.invalidate();
                    }
                });
            }
        }, 0, Interval );
    }
}