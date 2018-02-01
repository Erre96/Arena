package com.example.erhan.arena;

import android.content.Intent;
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


    public static String winner;
    public static int lang = 0;
    public static TextMessage tm = new TextMessage();
    public static Player plr = new Player();
    public static AI ai = new AI();

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 1600;
    private boolean timerRunning;
    boolean plrActive = false;
    boolean aiActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        winner = null;

        setInfoPlr();
        setInfoAI();

        //text references
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);

        //button references
        final Button attackButton = findViewById(R.id.attack);
        battleInfo1.setText(tm.doSome[lang]);



        //on click listeners
        attackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!plrActive && !aiActive && winner == null) {
                    plrActive = true;
                    startStop();
                    attackManagerPlr();
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
                if(winner != null)
                {
                    if(winner == "plr")
                    {
                        startActivity(new Intent(BattleActivity.this, MainActivity.class));
                    }
                    else if(winner == "ai")
                    {
                        startActivity(new Intent(BattleActivity.this, MainActivity.class));
                    }
                }
                if(plrActive)
                {
                    plrActive = false;
                    boolean enemyDead = checkIfDeadAI();
                    if (enemyDead)
                    {
                        battleInfo1.setText(tm.plrWon[lang]);
                        winner = "plr";
                        startTimer();
                    }
                    if(!enemyDead)
                    {
                        aiActive = true;
                        startTimer();
                        attackManagerAI();
                    }
                }

                else if(aiActive)
                {
                    aiActive = false;
                    boolean plrDead = checkIfDeadPlr();
                    if(plrDead)
                    {
                        battleInfo1.setText(tm.aiWon[lang]);
                        winner = "ai";
                        startTimer();
                    }
                    //battleInfo1.setText(String.valueOf(plrActive+String.valueOf(aiActive)));
                }
            }
        }.start();
        timerRunning = true;
    }


    public void stopTimer()
    {
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);

        countDownTimer.cancel();
        timerRunning = false;
        if(winner == null) {
            battleInfo1.setText(tm.doSome[lang]);
        }
    }

    public void attackManagerPlr()
    {
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);

        boolean hit = ai.determineIfHit(plr);
        if (hit) {
            plr.setCurDmg();
            String dmg = Integer.toString(plr.curDmg);
            battleInfo1.setText(tm.plrAtt[lang]+dmg);
            //battleInfo1.setText(String.valueOf(plrActive+String.valueOf(aiActive)));
            ai.curHp -= plr.curDmg;
            setInfoAI();
        }

        if (!hit) {
            battleInfo1.setText(tm.plrMiss[lang]);
        }
    }

    public void attackManagerAI()
    {
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);

        boolean hit = plr.determineIfHit(ai);
        if (hit) {
            ai.setCurDmg();
            String dmg = Integer.toString(ai.curDmg);
            battleInfo1.setText(tm.aiAtt[lang]+dmg);
            //battleInfo1.setText(String.valueOf(plrActive+String.valueOf(aiActive)));
            plr.curHp -= ai.curDmg;
            setInfoPlr();
        }

        if (!hit) {
            battleInfo1.setText(tm.aiMiss[lang]);
        }
    }

    public void setInfoPlr()
    {
        final TextView infoPlr = findViewById((R.id.infoPlr));
        final TextView infoPlr2 = findViewById((R.id.infoPlr2));
        final TextView namePlr = findViewById(R.id.namePlr);

        namePlr.setText(plr.name+ (" (You)"));
        infoPlr.setText("Hp : "+plr.curHp+" / "+plr.maxHp);
        infoPlr2.setText("St : "+plr.curSt+" / "+plr.maxSt);
    }

    public void setInfoAI()
    {
        final TextView infoAI = findViewById((R.id.infoAI));
        final TextView infoAI2 = findViewById((R.id.infoAI2));

        infoAI.setText("Hp : "+ai.curHp+" / "+ai.maxHp);
        String g = Integer.toString(ai.dexterity);
        infoAI2.setText("St : "+ai.curSt+" / "+ai.maxSt);
    }

    public boolean checkIfDeadAI()
    {
        if(ai.curHp < 1)
        {
            return true;
        }
        else
            return false;
    }

    public boolean checkIfDeadPlr()
    {
        if(plr.curHp < 1)
        {
            return true;
        }
        else
            return false;
    }
}

