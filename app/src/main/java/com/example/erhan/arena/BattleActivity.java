package com.example.erhan.arena;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;

public class BattleActivity extends AppCompatActivity {


    public static String winner;
    public String message;
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

        plr.setAllStats();
        ai.setAllStats();

        winner = null;

        setInfoPlr();
        setInfoAI();

        //text references
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);
        battleInfo1.setText(getString(R.string.doSome));

        //button references
        final Button attackButton = findViewById(R.id.attack);
        final Button specialButton = findViewById(R.id.special);
        final Button rageButton = findViewById(R.id.rage);
        final Button restButton = findViewById(R.id.rest);



        //on click listeners
        attackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                attack(1);
                }
            });

        specialButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                attack(2);
            }
        });

        rageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                attack(3);
            }
        });

        restButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean tired = plr.checkIfTired();
                if(tired)
                {
                    plr.rest();
                    refreshTableInfo();
                    sendBattleMessage(getString(R.string.youRest));
                    plrActive = true;
                    startStop();
                }
                if(!tired) {
                    sendBattleMessage(getString(R.string.fullOfEnergy));
                    startStop();
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
                        plr.level++;
                        plr.upgradePoints+=5;
                        startActivity(new Intent(BattleActivity.this, LevelUpActivity.class));
                    }
                    else if(winner == "ai")
                    {
                        startActivity(new Intent(BattleActivity.this, MainActivity.class));
                        plr.upgradePoints = 0;
                    }
                }
                if(plrActive)
                {
                    plrActive = false;
                    refreshTableInfo();
                    boolean enemyDead = checkIfDeadAI();
                    if (enemyDead)
                    {
                        battleInfo1.setText(getString(R.string.plrWon));
                        winner = "plr";
                        startTimer();
                    }
                    if(!enemyDead)
                    {
                        aiActive = true;
                        startTimer();
                        attackManagerAI(1);
                    }
                }

                else if(aiActive)
                {
                    aiActive = false;
                    raiseEnergyForAll();
                    refreshTableInfo();
                    boolean plrDead = checkIfDeadPlr();
                    if(plrDead)
                    {
                        battleInfo1.setText(getString(R.string.aiWon));
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
            battleInfo1.setText(getString(R.string.doSome));
        }
    }

    public void attackManagerPlr(int type)
    {
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);

        if(type == 2)
        {
            plr.curSt-=25;
            refreshTableInfo();
        }

        if(type == 3)
        {
            plr.curSt-=75;
            refreshTableInfo();
        }

        boolean hit = ai.determineIfHit(plr);
        if (hit) {
            plr.setCurDmg(ai.armorRating, type);
            String dmg = Integer.toString(plr.curDmg);
            battleInfo1.setText(getString(R.string.plrAttack)+dmg);
            //battleInfo1.setText(String.valueOf(plrActive+String.valueOf(aiActive)));
            ai.curHp -= plr.curDmg;
            setInfoAI();
        }

        if (!hit) {
            battleInfo1.setText(getString(R.string.plrMiss));
        }
    }

    public void attackManagerAI(int type)
    {
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);

        boolean hit = plr.determineIfHit(ai);
        if (hit) {
            ai.setCurDmg(plr.armorRating, type);
            String dmg = Integer.toString(ai.curDmg);
            battleInfo1.setText(getString(R.string.aiAtt)+dmg);
            //battleInfo1.setText(String.valueOf(plrActive+String.valueOf(aiActive)));
            plr.curHp -= ai.curDmg;
            setInfoPlr();
        }

        if (!hit) {
            battleInfo1.setText(getString(R.string.aiMiss));
        }
    }

    public void setInfoPlr()
    {
        final TextView infoPlr = findViewById((R.id.infoPlr));
        final TextView infoPlr2 = findViewById((R.id.infoPlr2));
        final TextView namePlr = findViewById(R.id.namePlr);

        namePlr.setText(plr.name+ (" (You)"));
        infoPlr.setText("HP : "+plr.curHp+" / "+plr.maxHp);
        infoPlr2.setText("EN : "+plr.curSt+" %");
    }

    public void setInfoAI()
    {
        final TextView infoAI = findViewById((R.id.infoAI));
        final TextView infoAI2 = findViewById((R.id.infoAI2));

        infoAI.setText("HP : "+ai.curHp+" / "+ai.maxHp);
        infoAI2.setText("EN : "+ai.curSt+" %");
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

    public void raiseEnergyForAll()
    {
        if(plr.curSt<plr.maxSt )
        {
            plr.curSt += plr.maxSt/25;
        }
        if(ai.curSt<ai.maxSt )
        {
            ai.curSt += ai.maxSt / 25;
        }
    }

    public void refreshTableInfo()
    {
        setInfoPlr();
        setInfoAI();
    }

    public void attack(int type)
    {
        if (!plrActive && !aiActive && winner == null) {
            if(type == 1) {
                attackMove(1);
            }

            if(type == 2) {
                boolean possible = checkIfPossible(2,plr.curSt);
                if(possible) {
                    attackMove(2);
                }

                if(!possible)
                {
                    sendBattleMessage(getString(R.string.notEnoughEnergy));
                    startStop();
                }
            }

            if(type == 3) {
                boolean possible = checkIfPossible(3,plr.curSt);
                if(possible) {
                    attackMove(3);
                }

                if(!possible)
                {
                    sendBattleMessage(getString(R.string.notEnoughEnergy));
                    startStop();
                }
            }
        }
    }

    public void attackMove(int type)
    {
        plrActive = true;
        startStop();
        attackManagerPlr(type);
    }

    public boolean checkIfPossible(int type, int energy)
    {
        if(type == 2) {
            if (energy >= 25) {
                return true;
            }
            else return false;

        }
        if(type == 3) {
            if (energy >= 75) {
                return true;
            }
            else return false;

        }
        return true;
    }

    public void sendBattleMessage(String message)
    {
        final TextView battleInfo1 = findViewById(R.id.battleInfo1);

        battleInfo1.setText(message);
    }
}

