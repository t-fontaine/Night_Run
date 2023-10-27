package com.example.nightrun;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int munchSound;
    private static int overSound;
    private static int introHowl;

    public SoundPlayer(Context context) {
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        introHowl = soundPool.load(context, R.raw.howl, 1);
        munchSound = soundPool.load(context,R.raw.munch, 1);
        overSound = soundPool.load(context, R.raw.gameover1, 1);
    }

    public static void playIntroHowl() {
        soundPool.play(introHowl, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void playMunchSound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(munchSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void playOverSound() {
        soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
