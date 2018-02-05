package com.example.erhan.arena;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);
        refreshText();


        //button references
        final Button strButton = findViewById(R.id.plusStrength);
        final Button dexButton = findViewById(R.id.plusDexterity);
        final Button defButton = findViewById(R.id.plusDefense);
        final Button endButton = findViewById(R.id.plusEndurance);
        final Button nextButton = findViewById(R.id.nextButton);


        //on click listeners
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LevelUpActivity.this, IdleActivity.class));
                }
            });

        strButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(BattleActivity.plr.upgradePoints > 0) {
                    BattleActivity.plr.upgradeStat("str");
                    refreshText();
                }
            }
        });

        dexButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(BattleActivity.plr.upgradePoints > 0) {
                    BattleActivity.plr.upgradeStat("dex");
                    refreshText();
                }
            }
        });

        defButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(BattleActivity.plr.upgradePoints > 0) {
                    BattleActivity.plr.upgradeStat("def");
                    refreshText();
                }
            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(BattleActivity.plr.upgradePoints > 0) {
                    BattleActivity.plr.upgradeStat("end");
                    refreshText();
                }
            }
        });
    }

    public void refreshText()
    {
        //text references
        final TextView strValue = findViewById(R.id.strengthValue);
        final TextView dexValue = findViewById(R.id.dexterityValue);
        final TextView defValue = findViewById(R.id.defenseValue);
        final TextView endValue = findViewById(R.id.enduranceValue);
        final TextView plValue = findViewById(R.id.pointsLeftValue);

        strValue.setText(String.valueOf(BattleActivity.plr.strength));
        dexValue.setText(String.valueOf(BattleActivity.plr.dexterity));
        defValue.setText(String.valueOf(BattleActivity.plr.defense));
        endValue.setText(String.valueOf(BattleActivity.plr.endurance));
        plValue.setText(String.valueOf(BattleActivity.plr.upgradePoints));


    }
}
