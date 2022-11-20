package com.example.Qwhiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import java.util.Locale;


public class GameOptionsActivity extends AppCompatActivity  {
    Button firstButton,secondButton,thirdButton,fourthButton,fifthButton,sixthButton;
    String selectedDifficulty=null;
    Button clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);
        firstButton=findViewById(R.id.firstBtnC);
        secondButton=findViewById(R.id.secondBtnC);
        thirdButton=findViewById(R.id.thirdBtnC);
        fourthButton=findViewById(R.id.fourthBtnC);
        fifthButton=findViewById(R.id.fifthBtnC);
        sixthButton=findViewById(R.id.sixthBtnC);
        firstButton.setOnClickListener(button_Listener);
        secondButton.setOnClickListener(button_Listener);
        thirdButton.setOnClickListener(button_Listener);
        fourthButton.setOnClickListener(button_Listener);
        fifthButton.setOnClickListener(button_Listener);
        sixthButton.setOnClickListener(button_Listener);

    }
    public void difficultyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Difficulty");
        String[]  difficulties = {"Easy","Medium","Hard"};
        builder.setItems(difficulties, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        selectedDifficulty = "Easy";
                        break;
                    case 1:
                        selectedDifficulty="Medium";
                        break;
                    case 2:
                        selectedDifficulty="Hard";
                }
                if(selectedDifficulty!=null) {
                    Intent startGame = new Intent(getApplicationContext(), InGameActivity.class);
                    startGame.putExtra("Category", clicked.getText().toString());
                    startGame.putExtra("Difficulty",selectedDifficulty);
                    startActivity(startGame);
                    finish();
                }
            }

        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private final View.OnClickListener button_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clicked = findViewById(v.getId());
            difficultyDialog();
        }
    };

}