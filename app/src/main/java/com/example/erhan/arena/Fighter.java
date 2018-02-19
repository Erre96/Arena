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
    public float selfDmgReduc;
    public float stRechargeRate;

    public int upgradePoints;

    public void setStartingStats()
    {
        level = 1;
        strength = 13;
        dexterity = 5;
        endurance = 10;
        defense = 1110;

        maxSt = 1000;
    }

    public void setDmg()
    {
        maxDmg = strength * 3;
        float f = maxDmg * 0.6f;
        minDmg = Math.round(f);
    }

    public void setCurDmg(int def)
    {
        Random rand = new Random();
        curDmg = rand.nextInt(maxDmg + 1 - minDmg) + minDmg;
        setCriticalHit();
        setSelfDmgReduction(def);
    }

    public void setCriticalChance()
    {
        critChance = (dexterity * 0.1f)+1;
    }
    private void setCriticalHit()
    {
        Random rand = new Random();
        int v = rand.nextInt(100);
        if(v < critChance)
        {
            curDmg *= 1.5f;
        }
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
        maxHp = (endurance * 6) +(level * 3);
        curHp = maxHp;
    }

    public void setStaminaRechargePercent()
    {
        stRechargeRate = (endurance / 10) + 50;
    }

    private void setSelfDmgReduction(int def)
    {
       selfDmgReduc = ((def)*0.01f) / (1+0.01f*(def));
        float f = curDmg * (1 - selfDmgReduc);
        curDmg = Math.round(f);
    }

    public void setAllStats()
    {
        setDmg();
        setHp();
        setCriticalChance();
        setEvasion();
        setAccuracy();
        setStaminaRechargePercent();

        curSt = maxSt / 4;
    }

    void upgradeStat(String type)
    {
        switch (type) {
            case "str" :
                BattleActivity.plr.strength++;
                BattleActivity.plr.upgradePoints--;
                break;
        }

        switch (type) {
            case "dex" :
                BattleActivity.plr.dexterity++;
                BattleActivity.plr.upgradePoints--;
                break;
        }

        switch (type) {
            case "def" :
                BattleActivity.plr.defense++;
                BattleActivity.plr.upgradePoints--;
                break;
        }

        switch (type) {
            case "end" :
                BattleActivity.plr.endurance++;
                BattleActivity.plr.upgradePoints--;
                break;
        }
    }
}
