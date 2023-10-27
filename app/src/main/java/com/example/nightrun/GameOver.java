package com.example.nightrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    private Button startGameAgain;
    private TextView scoreDisplay;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        SoundPlayer.playOverSound();

        score = getIntent().getExtras().get("score").toString();

        startGameAgain = (Button) findViewById(R.id.play_again);
        scoreDisplay = (TextView) findViewById(R.id.scoreText);

        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(GameOver.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        scoreDisplay.setText("Score: " + score);
    }
}