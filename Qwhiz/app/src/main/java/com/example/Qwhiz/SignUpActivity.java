package com.example.Qwhiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText playerNameEditText;
    Button btnLogin;
    String name;

    final static String FILENAME = MainActivity.FILENAME;
    final static String TAG =  "SignUpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent intent = getIntent();
        btnLogin=findViewById(R.id.btnLogin);
        playerNameEditText = findViewById(R.id.playernameId);
        playerNameEditText.setText("");
        sharedPreferences = getSharedPreferences(FILENAME, MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (playerNameEditText.getText().toString().isEmpty())
                {
                    Snackbar.make(v,"invalid value",Snackbar.LENGTH_SHORT).show();
                }
                else
                    SignUp(name);
            }


        });




    }

    public void SignUp(String playername) {
        try {

            editor = sharedPreferences.edit();
            editor.putString("name", playerNameEditText.getText().toString());
            editor.putBoolean("isSignUp",true);
            editor.putBoolean("First_login",true);
            editor.commit(); // saving
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
