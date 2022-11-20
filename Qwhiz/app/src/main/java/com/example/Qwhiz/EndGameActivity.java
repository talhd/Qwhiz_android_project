package com.example.Qwhiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {
    TextView timeTV ,scoreTV,categoryTV;
    Intent gamePlayed;
    Button endSessionBtn ;
    int score;
    String Category,time,difficulty;
    final  static  String NewScoreBundle = "NewScore";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        gamePlayed=getIntent();
        timeTV=findViewById(R.id.timePlayedTV);
        scoreTV=findViewById(R.id.scorePlayedTV);
        categoryTV=findViewById(R.id.categoryPlayedTV);
        endSessionBtn=findViewById(R.id.exitButton);
        setTextViews();
    }
    public void setTextViews()
    {
        difficulty=gamePlayed.getStringExtra("Difficulty");
        timeTV.setText(gamePlayed.getStringExtra("TimePlayed"));
        scoreTV.setText(gamePlayed.getStringExtra("Points"));
        categoryTV.setText(gamePlayed.getStringExtra("Category")+"\n"+difficulty);
        score = Integer.parseInt(gamePlayed.getStringExtra("Points"));
        time = gamePlayed.getStringExtra("TimePlayed");
        Category = gamePlayed.getStringExtra("Category")+"\n"+difficulty;
    }

    public  void toScoreScreen()
    {
        Intent intent = new Intent(this,ScoresActivity.class);
        intent.putExtra(NewScoreBundle,new Score(score,time,Category));
        startActivity(intent);
        finish();

    }
    public void goToMain(View view) {
        toScoreScreen();
    }

}