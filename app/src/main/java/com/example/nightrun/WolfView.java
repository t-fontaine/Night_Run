package com.example.nightrun;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class WolfView extends View {
    private Bitmap wolf[] = new Bitmap[2];
    private int wolfX = 10;
    private int wolfY;
    private int wolfSpeed;

    private int canvasWidth, canvasHeight;
    private int score,healthCounter;

    private Bitmap rabbit1[] = new Bitmap[2];
    private int rabbit1X, rabbit1Y, rabbit1Speed = 16;

    private Bitmap rabbit2[] = new Bitmap[2];
    private int rabbit2X, rabbit2Y, rabbit2Speed = 25;

    private Bitmap buck;
    private int buckX, buckY, buckSpeed = 18;

    private boolean touch = false;

    private Bitmap backgroundImage;
    private Bitmap hearts[] = new  Bitmap[2];
    private Paint scorePaint = new Paint();

    public WolfView(Context context) {
        super(context);

        wolf[0] = BitmapFactory.decodeResource(getResources(), R.drawable.wolfsmallflame2);
        wolf[1] = BitmapFactory.decodeResource(getResources(), R.drawable.rocketwolf4);
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.staticbackgroundwithtrees2);

        rabbit1[0] = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit2);
        rabbit1[1] = BitmapFactory.decodeResource(getResources(), R.drawable.rabbitsmallflame2);
        rabbit2[0] = BitmapFactory.decodeResource(getResources(), R.drawable.tophatrabbit2);
        rabbit2[1] = BitmapFactory.decodeResource(getResources(), R.drawable.tophatrabbitsmallflame2);

        buck = BitmapFactory.decodeResource(getResources(), R.drawable.buck3);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        hearts[0] = BitmapFactory.decodeResource(getResources(), R.drawable.pixelheart3);
        hearts[1] = BitmapFactory.decodeResource(getResources(), R.drawable.emptyheart4);

        wolfY = 550;    //why 550? Doesn't seem to change anything
        score = 0;
        healthCounter = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage, 0, 0, null);

        int minWolfY = wolf[0].getHeight();
        int maxWolfY = canvasHeight - wolf[0].getHeight();
        wolfY = wolfY + wolfSpeed;

        if (wolfY < minWolfY)   wolfY = minWolfY;
        if (wolfY > maxWolfY)   wolfY = maxWolfY;
        wolfSpeed = wolfSpeed + 2;
        if (touch) {
            canvas.drawBitmap(wolf[1], wolfX, wolfY, null);
            touch = false;
        }
        else    canvas.drawBitmap(wolf[0], wolfX, wolfY, null);

        rabbit1X = rabbit1X - rabbit1Speed;
        if (ateRabbitChecker(rabbit1X, rabbit1Y)) {
            SoundPlayer.playMunchSound();
            score = score + 10;
            rabbit1X = -100;
        }
        if (rabbit1X < 0) {
            rabbit1X = canvasWidth + 21;
            rabbit1Y = (int) Math.floor(Math.random() * (maxWolfY - minWolfY)) + minWolfY;
        }
        canvas.drawBitmap(rabbit1[0], rabbit1X, rabbit1Y, null);

        rabbit2X = rabbit2X - rabbit2Speed;
        if (ateRabbitChecker(rabbit2X, rabbit2Y)) {
            SoundPlayer.playMunchSound();
            score = score + 30;
            rabbit2X = -100;
        }
        if (rabbit2X < 0) {
            rabbit2X = canvasWidth + 21;
            rabbit2Y = (int) Math.floor(Math.random() * (maxWolfY - minWolfY)) + minWolfY;
        }
        canvas.drawBitmap(rabbit2[0], rabbit2X, rabbit2Y, null);

        buckX = buckX - buckSpeed;
        if (ateRabbitChecker(buckX, buckY)) {
            SoundPlayer.playMunchSound();
            buckX = -100;
            healthCounter--;
            if (healthCounter == 0) {
                Intent gameOverIntent = new Intent(getContext(), GameOver.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score", score);
                getContext().startActivity(gameOverIntent);
            }
        }
        if (buckX < 0) {
            buckX = canvasWidth + 21;
            buckY = (int) Math.floor(Math.random() * (maxWolfY - minWolfY)) + minWolfY;
        }
        canvas.drawBitmap(buck, buckX, buckY, null);

        canvas.drawText("Score: " + score, 20, 60, scorePaint);

        for (int i=0; i<3; i++) {
            int x = (int) (500 + hearts[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < healthCounter) {
                canvas.drawBitmap(hearts[0], x, y, null);
            }
            else {
                canvas.drawBitmap(hearts[1], x, y, null);
            }
        }
    }

    public boolean ateRabbitChecker(int x, int y) {
        if (wolfX < x && x < (wolfX + wolf[0].getWidth()) && wolfY < y && y < (wolfY + wolf[0].getHeight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;

            wolfSpeed = -22;
        }
        return true;
    }
}
