package com.example.Qwhiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ScoresActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MenuItem item;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList< Score > scores;
    final static  String TAG = "ScoresActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        Score score = getIntent().getExtras().getParcelable(EndGameActivity.NewScoreBundle);
        loadData();
        drawerLayout = findViewById(R.id.drawerLayoutMission);
        navigationView = findViewById(R.id.nav_view_Missions);



        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout
                        , toolbar, R.string.open_nav_bar, R.string.close_nav_bar);
        drawerLayout.addDrawerListener((actionBarDrawerToggle));
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMissions);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ScoresAdapter scoresAdapter = new ScoresAdapter(scores,this);
        recyclerView.setAdapter(scoresAdapter);
        scores.add(score);
        saveData();
        scoresAdapter.notifyDataSetChanged();
        View headerTitle = navigationView.getHeaderView(0);

        TextView playerName = (TextView)headerTitle.findViewById(R.id.nav_Tiltle);
        playerName.setText(sharedPreferences.getString("name","null"));
        Log.d(TAG,"name: "+playerName.getText().toString());



    }

    private void loadData() {

        sharedPreferences = getSharedPreferences(MainActivity.FILENAME, MODE_PRIVATE);

        Gson gson = new Gson();


        String json = sharedPreferences.getString(MainActivity.ScoreKey, null);

        Type type = new TypeToken<ArrayList<Score >>() {}.getType();

        scores = gson.fromJson(json, type);

        if (scores == null) {

            scores = new ArrayList<>();
        }
    }
    private void saveData() {

        sharedPreferences = getSharedPreferences(MainActivity.FILENAME, MODE_PRIVATE);


        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(scores);



        editor.putString(MainActivity.ScoreKey, json);

        editor.commit();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.home_item:

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();

            case R.id.score_item:
                Toast.makeText(this, "error, already here", Toast.LENGTH_SHORT).show();
            case R.id.profile_item:
                Toast.makeText(this, "profileItem", Toast.LENGTH_SHORT).show();

        }

        return false;
    }
}