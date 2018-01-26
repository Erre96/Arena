package com.example.erhan.arena;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BattleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        //text references
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);
        battleInfo1.setText("Make your move");

        //button references
        final Button attackButton = findViewById(R.id.attack);

        //on click listeners
        attackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                battleInfo1.setText("You attack the enemy");
            }
        });

    }
}

