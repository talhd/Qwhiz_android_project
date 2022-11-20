package com.example.Qwhiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;


public class MainActivity extends AppCompatActivity {
    final  static String FILENAME = "Player";
    final  static String ScoreKey = "userScores";
    private static final String TAG = "MainActivity";
    LottieAnimationView animationQuestion,animationBrain;

    SharedPreferences sharedPreferences;
    TextView welcomeTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(FILENAME,MODE_PRIVATE);
        welcomeTV=findViewById(R.id.welcomeTextView);
        animationQuestion=findViewById(R.id.questionAnimation);
        animationBrain=findViewById(R.id.brainAnimation);
        animationBrain.playAnimation();
        animationQuestion.playAnimation();

        CheckLogin();

    }

    public void startGameBtn(View view) {
        Intent statGameIntent = new Intent(this,GameOptionsActivity.class);
        startActivity(statGameIntent);
    }

    /*Check if the user already registered once , if not - activate the signup activity */
    public void CheckLogin() {
        if (sharedPreferences.contains("First_login")) {
            welcomeTV.setText("Welcome "+sharedPreferences.getString("name",null));
        }
        else // first enter to the game
        {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
            welcomeTV.setText("Welcome"+sharedPreferences.getString("name",null));
        }
    }




}