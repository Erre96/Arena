package com.example.erhan.arena;

import java.util.Random;

/**
 * Created by Erhan on 2018-01-29.
 */

public class AI extends Fighter {
    @Override
    public void setStartingStats() {
        int lv = BattleActivity.plr.level;

        strength = 3+ ((lv * 3));
        endurance = 2+ ((lv * 4));
        dexterity = 1+ ((lv * 1));
        defense = 4 + ((lv * 2));
    }


    @Override
    public void setAllStats() {
        setStartingStats();
        super.setAllStats();
    }

    public boolean determineIfHit(Fighter plr)
    {
        Random rand = new Random();
        float chance = ((plr.accuracy - evasion)/plr.accuracy)*100;

        if(chance < 0)
            return false;
        else if(rand.nextInt(100) > chance)
            return false;
        else
            return true;
    }
}

