package com.example.erhan.arena;

import java.util.Random;

/**
 * Created by Erhan on 2018-01-29.
 */

public class Player extends Fighter {


    public boolean determineIfHit(AI ai)
    {
        Random rand = new Random();
        float chance = ((ai.accuracy - evasion)/ai.accuracy)*100;

        if(chance < 0)
            return false;
        else if(rand.nextInt(100) > chance)
            return false;
        else
            return true;
    }
}
