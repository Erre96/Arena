package com.example.erhan.arena;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //saveGame();

        BattleActivity.plr.setStartingStats();
        BattleActivity.plr.setAllStats();
        BattleActivity.ai.setAllStats();

        final Button button = findViewById(R.id.newGame);
        final Button loadButton = findViewById(R.id.loadGame);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChooseNameActivity.class);
                startActivity(intent);
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadGame();
                startActivity(new Intent(MainActivity.this, IdleActivity.class));
            }
        });
    }


    public void loadGame()
    {
        SharedPreferences mPrefs = getSharedPreferences("com.arena.unik", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");

        Log.d("MainActivity",json + ": test");

        BattleActivity.plr = new Player();
        BattleActivity.plr = gson.fromJson(json, Player.class);

      //  Log.d("MainActivity","Player name: " + player.name);
    }
/*
    public void saveGame()
    {
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        //Gson gson = new Gson();
        //String json = gson.toJson(BattleActivity.plr);
        prefsEditor.putString("MyObject", "David");
        prefsEditor.apply();
    }*/
}
