package com.example.erhan.arena;

import android.app.Application;

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

    public int hp;
    public int st;
    public int dmg;
    public float critChance;
    public float dodgeChance;
    public float enemyDmgReduc;
    public float stRechargeRate;

    public void setStartingStats()
    {
        strength = 10;
        dexterity = 10;
        endurance = 10;
        defense = 10;

        st = 1000;
    }

    public void setDmg()
    {
        dmg = strength * 3;
    }

    public void setCritChance()
    {
        critChance = dexterity * 0.1f;
    }

    public void setDodgeChance()
    {
        dodgeChance = dexterity * 0.1f;
    }

    public void setHp()
    {
        hp = (endurance * 5) +(level * 3);
    }

    public void setStaminaRechargePercent()
    {
        stRechargeRate = (endurance / 10) + 50;
    }

    public void setEnemyDmgReduction(){enemyDmgReduc = defense / 10;}

    public void setAll()
    {
        setDmg();
        setHp();
        setCritChance();
        setDodgeChance();
        setStaminaRechargePercent();
        setEnemyDmgReduction();
    }
}
