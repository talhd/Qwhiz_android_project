package com.example.Qwhiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@SuppressWarnings({"deprecation","UseCompatLoadingForDrawables","SetTextI18n","NonConstantResourceId"})
public class InGameActivity extends AppCompatActivity {
    private static final String TAG = "InGameActivity";
    Intent game;
    List<QuestionObject>questions;
    ArrayList<Button> randomOrderedButtons;
    private Integer index_Current_Question=0,current_Points=0,number_of_Clues=3;
    long startTime = System.currentTimeMillis();
    FloatingActionButton sound_Control,getClue;
    FloatingActionMenu floatingMenu;
    LottieAnimationView correctAnimation,wrongAnimation;
    ProgressBar progBar;
    TextView questionTV, progressTV,timerTV,pointsTV;
    Button firstOpt,secondOpt,thirdOpt,fourthOpt;
    CountDownTimer question_Timer;
    MediaPlayer background_Music;
    private Boolean isMusicOn=true,isSameQuestion=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        game=getIntent();
        chooseBackgroundMusic();
        progBar=findViewById(R.id.progressBar);
        floatingMenu=findViewById(R.id.floatingActionMenu);
        correctAnimation=findViewById(R.id.correctAnim);
        wrongAnimation=findViewById(R.id.wrongAnim);
        pointsTV=findViewById(R.id.pointsTextView);
        questionTV=findViewById(R.id.questionTextView);
        firstOpt=findViewById(R.id.firstOptionBtn);
        secondOpt=findViewById(R.id.secondOptionBtn);
        thirdOpt=findViewById(R.id.thirdOptionBtn);
        fourthOpt=findViewById(R.id.fourthOptionBtn);
        timerTV=findViewById(R.id.timerTV);
        firstOpt.setOnClickListener(button_Listener);
        secondOpt.setOnClickListener(button_Listener);
        thirdOpt.setOnClickListener(button_Listener);
        fourthOpt.setOnClickListener(button_Listener);
        progressTV=findViewById(R.id.questionCountProgress);
        sound_Control=findViewById(R.id.soundFAB);
        getClue=findViewById(R.id.clueFAB);
        parse_Questions();
        progBar.setMax(questions.size());
        pointsTV.setText(Integer.toString(current_Points));
        correctAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                correctAnimation.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        wrongAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                wrongAnimation.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        nextQuestion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        background_Music.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        background_Music.start();
    }

    public void chooseBackgroundMusic() {
        String difficulty = game.getStringExtra("Difficulty");
        background_Music = MediaPlayer.create(getApplicationContext(),R.raw.komiku_the_moment_of_truth_hard);
        background_Music.setVolume(0.5f,0.5f);
        background_Music.start();
    }

    public void activateAnimation(boolean isAnswerCorrect)
    {
        if(isAnswerCorrect){
            correctAnimation.playAnimation();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    correctAnimation.clearAnimation();
                }
            },200);
            correctAnimation.setVisibility(View.VISIBLE);
        }
        else{
            wrongAnimation.playAnimation();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    wrongAnimation.clearAnimation();
                }
            },200);
            wrongAnimation.setVisibility(View.VISIBLE);
        }
    }
    /*Selects json file upon user choices and parse the json into a list*/
    public  void parse_Questions() {
        try {
            String category = game.getStringExtra("Category");
            String difficulty = game.getStringExtra("Difficulty");
            InputStream in = null;
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            switch (category) {
                case "General Knowledge": {
                    if (difficulty.equals("Easy"))
                        in = getResources().openRawResource(R.raw.general_knowledge_easy);
                    if (difficulty.equals("Medium"))
                        in = getResources().openRawResource(R.raw.general_knowledge_medium);
                    if (difficulty.equals("Hard"))
                        in = getResources().openRawResource(R.raw.general_knowledge_hard);
                    break;
                }
                case "History": {
                    if (difficulty.equals("Easy"))
                        in = getResources().openRawResource(R.raw.history_easy);
                    if (difficulty.equals("Medium"))
                        in = getResources().openRawResource(R.raw.history_medium);
                    if (difficulty.equals("Hard"))
                        in = getResources().openRawResource(R.raw.history_hard);
                    break;
                }
                case "Mythology":
                    if (difficulty.equals("Easy"))
                        in = getResources().openRawResource(R.raw.mythology_easy);
                    if (difficulty.equals("Medium"))
                        in = getResources().openRawResource(R.raw.mythology_medium);
                    if (difficulty.equals("Hard"))
                        in = getResources().openRawResource(R.raw.mythology_hard);
                    break;
                case "Science and Nature":
                    if (difficulty.equals("Easy"))
                        in = getResources().openRawResource(R.raw.science_and_nature_easy);
                    if (difficulty.equals("Medium"))
                        in = getResources().openRawResource(R.raw.science_and_nature_medium);
                    if (difficulty.equals("Hard"))
                        in = getResources().openRawResource(R.raw.science_and_nature_hard);
                    break;
                case "Science-    Computers":
                    if (difficulty.equals("Easy"))
                        in = getResources().openRawResource(R.raw.science_computers_easy);
                    if (difficulty.equals("Medium"))
                        in = getResources().openRawResource(R.raw.science_computers_medium);
                    if (difficulty.equals("Hard"))
                        in = getResources().openRawResource(R.raw.science_computers_hard);
                    break;
                case "Vehicles":
                    if (difficulty.equals("Easy"))
                        in = getResources().openRawResource(R.raw.vehicles_easy);
                    if (difficulty.equals("Medium"))
                        in = getResources().openRawResource(R.raw.vehicles_medium);
                    if (difficulty.equals("Hard"))
                        in = getResources().openRawResource(R.raw.vehicles_hard);
                    break;
            }
            // convert JSON array to questions list
            questions = Arrays.asList(mapper.readValue(in, QuestionObject[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*Upon user answer selected , check if it's the right answer and play animation upon
    * right/wrong choice */
    private final View.OnClickListener button_Listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.firstOptionBtn:
                    if(firstOpt.getText()== questions.get(index_Current_Question).correct_answer) {
                        current_Points+=10;
                        pointsTV.setText(Integer.toString(current_Points));
                        activateAnimation(true);
                    }
                    else activateAnimation(false);
                    break;
                case R.id.secondOptionBtn:
                    if(secondOpt.getText()== questions.get(index_Current_Question).correct_answer){
                        current_Points+=10;
                        pointsTV.setText(Integer.toString(current_Points));
                        activateAnimation(true);
                    }
                    else activateAnimation(false);
                    break;
                case R.id.thirdOptionBtn:
                    if(thirdOpt.getText()== questions.get(index_Current_Question).correct_answer){
                        current_Points+=10;
                        pointsTV.setText(Integer.toString(current_Points));
                        activateAnimation(true);
                    }else activateAnimation(false);
                    break;
                case R.id.fourthOptionBtn:
                    if(fourthOpt.getText()== questions.get(index_Current_Question).correct_answer){
                        current_Points+=10;
                        pointsTV.setText(Integer.toString(current_Points));
                        activateAnimation(true);
                    }else activateAnimation(false);
                    break;
            }
            index_Current_Question++;
            question_Timer.cancel();
            nextQuestion();
        }
    };
    private void nextQuestion()
    {

        if(number_of_Clues>=0){
            firstOpt.setVisibility(View.VISIBLE);
            secondOpt.setVisibility(View.VISIBLE);
            thirdOpt.setVisibility(View.VISIBLE);
            fourthOpt.setVisibility(View.VISIBLE);
        }
            question_Timer = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                int time = (int)(millisUntilFinished / 1000);
                timerTV.setText(Integer.toString(time));
            }

            public void onFinish() {
                timerTV.setText("Time's up");
                index_Current_Question++;
                nextQuestion();
            }
        }.start();

        randomOrderedButtons = buttonsShuffle();
        isSameQuestion=false;
        if (questions.size() > index_Current_Question) {
            progressTV.setText((index_Current_Question+1)+"/"+questions.size());
            progBar.incrementProgressBy(1);
            questionTV.setText(questions.get(index_Current_Question).getQuestion());
            randomOrderedButtons.get(0).setText(questions.get(index_Current_Question).correct_answer);
            randomOrderedButtons.get(1).setText(questions.get(index_Current_Question).incorrect_answers.get(0));
            randomOrderedButtons.get(2).setText(questions.get(index_Current_Question).incorrect_answers.get(1));
            randomOrderedButtons.get(3).setText(questions.get(index_Current_Question).incorrect_answers.get(2));
        }
        /*No questions left - finish the game and sent the info about the current game to endGameActivity*/
        else {
            if(questions.size()==index_Current_Question) {
                long endTime = System.currentTimeMillis();
                int totalTime = (int) ((endTime - startTime) / 1000);
                Integer answeredRight = current_Points/10;
                Intent endGameIntent = new Intent(InGameActivity.this, EndGameActivity.class);
                endGameIntent.putExtra("Points", answeredRight.toString());
                endGameIntent.putExtra("Category", game.getStringExtra("Category"));
                endGameIntent.putExtra("TimePlayed", secondsToString(totalTime));
                endGameIntent.putExtra("Difficulty",game.getStringExtra("Difficulty"));
                startActivity(endGameIntent);
                finish();
            }
        }
    }
    private String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }


    /*Mute/Unmute background music*/
    public void controlSound(View view) {
        if(isMusicOn) {
            background_Music.setVolume(0.0f, 0.0f);
            sound_Control.setImageDrawable(getResources().getDrawable(R.drawable.unmute_audio_icon));
            isMusicOn=false;
            floatingMenu.close(true);
        }
        else{
            background_Music.setVolume(1.0f, 1.0f);
            sound_Control.setImageDrawable(getResources().getDrawable(R.drawable.no_audio_icon));
            isMusicOn=true;
            floatingMenu.close(true);
        }
    }

    public void getClue(View view) {
        /*Check if the user already used all of his clues .
          Check if the user is trying to use the clue button twice on the same question.*/
        if (number_of_Clues > 0) {
            if(!isSameQuestion){
            //"Remove" 2 out of 3 wrong answers
            isSameQuestion=true;
            int count = 2;
            randomOrderedButtons = buttonsShuffle();
            for (int i = 0; i < randomOrderedButtons.size(); i++) {
                if (count > 0){
                    if(randomOrderedButtons.get(i).getText() != questions.get(index_Current_Question).correct_answer) {
                       randomOrderedButtons.get(i).setVisibility(View.INVISIBLE);
                       count--;
                }
            }
                else
                    break;
            }
            number_of_Clues--;
            }
            else
                Toast.makeText(getApplicationContext(),"ALREADY USED CLUE!",Toast.LENGTH_SHORT).show();
        }
        else
           Toast.makeText(getApplicationContext(),"YOU ARE OUT OF CLUES!",Toast.LENGTH_SHORT).show();
        floatingMenu.close(true);
    }

    /*Shuffling the buttons - each game the answers will be represented on different buttons*/
    public ArrayList<Button> buttonsShuffle(){
        ArrayList<Button> randButtons = new ArrayList<>();
        randButtons.add(firstOpt);
        randButtons.add(secondOpt);
        randButtons.add(thirdOpt);
        randButtons.add(fourthOpt);
        Collections.shuffle(randButtons);
        return randButtons;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        number_of_Clues=3;
        isSameQuestion=false;
    }
}

