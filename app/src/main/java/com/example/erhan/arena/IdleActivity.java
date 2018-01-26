package com.example.erhan.arena;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IdleActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);

        final TextView nt =  findViewById(R.id.nameTitle);
        nt.setText(MainActivity.fighter.name.toString());

        final Button nextBattleButton = findViewById(R.id.nextBattle);

        nextBattleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BattleActivity.class);
                startActivity(intent);
            }
        });
    }
}


