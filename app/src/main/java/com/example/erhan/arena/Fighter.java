package com.example.erhan.arena;

import android.app.Application;
import java.util.Random;

/**
 * Created by Erhan on 2018-01-19.
 */

 public class Fighter
{
    public int level;
    public String name;
    public int strength;
    public int dexterity;
    public int endurance;
    public int defense;

    public int maxHp;
    public int curHp;
    public int maxSt;
    public int curSt;
    public int maxDmg;
    public int minDmg;
    public int curDmg;
    public float critChance;
    public float evasion;
    public float accuracy;
    public float enemyDmgReduc;
    public float stRechargeRate;

    public void setStartingStats()
    {
        level = 1;
        strength = 10;
        dexterity = 20;
        endurance = 10;
        defense = 10;

        maxSt = 1000;
        curSt = maxSt;
    }

    public void setDmg()
    {
        maxDmg = strength * 3;
        float f = maxDmg * 0.6f;
        minDmg = Math.round(f);
    }

    public void setCurDmg()
    {
        Random rand = new Random();
        curDmg = rand.nextInt(maxDmg + 1 - minDmg) + minDmg;
    }

    public void setCritChance()
    {
        critChance = (dexterity * 0.1f)+1;
    }

    public void setEvasion()
    {
        evasion = (dexterity * 0.09f);
    }

    public void setAccuracy()
    {
        accuracy = (dexterity * 0.16f);
    }

    public void setHp()
    {
        maxHp = (endurance * 5) +(level * 3);
        curHp = maxHp;
    }

    public void setStaminaRechargePercent()
    {
        stRechargeRate = (endurance / 10) + 50;
    }

    public void setEnemyDmgReduction()
    {
       enemyDmgReduc = ((defense)*0.01f) / (1+0.01f*(defense));
    }

    public void setAllStats()
    {
        setDmg();
        setHp();
        setCritChance();
        setEvasion();
        setAccuracy();
        setStaminaRechargePercent();
        setEnemyDmgReduction();

        curSt = maxSt;
    }
}
