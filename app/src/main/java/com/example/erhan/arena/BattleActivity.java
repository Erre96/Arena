package com.example.erhan.arena;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;

public class BattleActivity extends AppCompatActivity {


    public static Player plr = new Player();
    public static AI ai = new AI();

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 2000;
    private boolean timerRunning;
    boolean plrActive = false;
    boolean aiActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        //text references
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);

        //button references
        final Button attackButton = findViewById(R.id.attack);
        battleInfo1.setText("Make your move");



        //on click listeners
        attackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!plrActive && !aiActive) {
                    plrActive = true;
                    startStop();
                    boolean hit = ai.determineIfHit(plr);
                    if (hit) {
                        plr.setCurDmg();
                        String dmg = Integer.toString(plr.curDmg);
                        battleInfo1.setText("   You attack the enemy" + "\n" + "   Damage : " + dmg + "    " + plr.maxDmg + "  " + plr.minDmg);
                        //battleInfo1.setText(String.valueOf(plrActive+String.valueOf(aiActive)));
                        ai.curHp -= plr.curDmg;
                    }

                    if (!hit) {
                        battleInfo1.setText("You missed");
                    }
                }

            }
        });
    }

    public void startStop()
    {
        if(timerRunning)
        {
            stopTimer();
        }
        else{startTimer();}
    }

    public void startTimer()
    {
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
             //  battleInfo1.setText("Countdown : "+millisUntilFinished/1000);
                //battleInfo1.setText(String.valueOf(plrActive+String.valueOf(aiActive)));
            }

            @Override
            public void onFinish() {
                stopTimer();
                if(plrActive)
                {
                    plrActive = false;
                    aiActive = true;
                    startTimer();
                    battleInfo1.setText("   Enemy attacks you" + "\n" + "   Damage : " + ai.maxDmg);
                    plr.curHp -= ai.curDmg;
                }

                else if(aiActive)
                {
                    aiActive = false;
                    //battleInfo1.setText(String.valueOf(plrActive+String.valueOf(aiActive)));
                }
            }
        }.start();
        timerRunning = true;
    }


    public void stopTimer()
    {
        countDownTimer.cancel();
        timerRunning = false;
    }
}

