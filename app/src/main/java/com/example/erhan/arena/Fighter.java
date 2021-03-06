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
    public float armorRating; //multiplier for enemy dmg, etc dmg * 0.8
    public float stRechargeRate;

    public int upgradePoints;

    public void setStartingStats()
    {
        level = 1;
        strength = 10;
        dexterity = 10;
        endurance = 10;
        defense = 10;

        maxSt = 100;
    }

    public void setDmg()
    {
        maxDmg = strength * 3;
        float f = maxDmg * 0.6f;
        minDmg = Math.round(f);
    }

    public void setCurDmg(float ar, int type)
    {
        Random rand = new Random();
        curDmg = rand.nextInt(maxDmg + 1 - minDmg) + minDmg;
        if(type == 2)
        {
            multiplyCurDmg(curDmg,1.3f);
        }

        if(type == 3)
        {
            multiplyCurDmg(curDmg,2.2f);
        }
        setCriticalHit();
        float f = curDmg * ar;
        curDmg = Math.round(f);
    }

    public void multiplyCurDmg(float curDmg, float multiplier)
    {
        curDmg*=multiplier;
        this.curDmg = Math.round(curDmg);
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
        accuracy = (dexterity * 0.21f);
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

    private void setSelfDmgReduction(int def) //Decreases your own attack damage against enemy
    {
       selfDmgReduc = ((def)*0.01f) / (1+0.01f*(def));
        float f = curDmg * (1 - selfDmgReduc);
        curDmg = Math.round(f);
    }

    private void setArmorRating()
    {
        float f = ((defense)*0.01f) / (1+0.01f*(defense));
        armorRating = 1- f;
    }

    public void setAllStats()
    {
        setDmg();
        setHp();
        setCriticalChance();
        setEvasion();
        setAccuracy();
        setStaminaRechargePercent();
        setArmorRating();

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

    public boolean checkIfTired()
    {
        if(curSt <= maxSt) {
            return true;
        }
        else
            return false;
    }

    public void rest()
    {
        curSt += 12;
        curHp+= maxHp/50;
    }
}
