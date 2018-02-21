package com.example.erhan.arena;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class statsInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_info);
        BattleActivity.plr.setAllStats();
        //Button references
        final Button homeButton = findViewById(R.id.homeButton);
        final Button attributesButton = findViewById(R.id.attributesButton);

        //Text references
        TextView hpValue = findViewById(R.id.hpValue);
        TextView dmgValue = findViewById(R.id.dmgValue);
        TextView criticalChance = findViewById(R.id.criticalChanceValue);
        TextView dmgReduction = findViewById(R.id.dmgReductionPercent);
        TextView matchesWonValue = findViewById(R.id.matchesWonValue);


        hpValue.setText(String.valueOf(BattleActivity.plr.maxHp));
        dmgValue.setText(String.valueOf(BattleActivity.plr.minDmg+" - "+BattleActivity.plr.maxDmg));
        criticalChance.setText(String.valueOf(BattleActivity.plr.critChance+" %"));
        float f = (1 - BattleActivity.plr.armorRating) * 100;
        dmgReduction.setText(String.valueOf(f)+" %");
        matchesWonValue.setText(String.valueOf(BattleActivity.plr.level-1));


        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(statsInfoActivity.this, IdleActivity.class));
            }
        });

        attributesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(statsInfoActivity.this, LevelUpActivity.class));
            }
        });
    }
}
