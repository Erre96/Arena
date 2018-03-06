package com.example.erhan.arena;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class IdleActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);

        final TextView nt =  findViewById(R.id.nameTitle);
        nt.setText(BattleActivity.plr.name+"    Lv : "+String.valueOf(BattleActivity.plr.level));

        final Button nextBattleButton = findViewById(R.id.nextBattle);
        final Button statsButton = findViewById(R.id.stats);
        final Button saveButton = findViewById(R.id.saveGame);


        //on click listeners
        nextBattleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BattleActivity.class);
                startActivity(intent);
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LevelUpActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                saveGame();
            }
        });
    }

    public void saveGame()
    {
        SharedPreferences mPrefs = getSharedPreferences("com.arena.unik", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(BattleActivity.plr);
        prefsEditor.putString("MyObject", json);
        prefsEditor.apply();
        Log.d("David",json + ": test");
    }
}


