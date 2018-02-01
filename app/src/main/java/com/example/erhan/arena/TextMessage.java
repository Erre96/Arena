package com.example.erhan.arena;

/**
 * Created by Erhan on 2018-01-31.
 */

public class TextMessage {
    public String plrAtt[] = new String[2];
    public String aiAtt[] = new String[2];
    public String plrWon[] = new String[2];
    public String aiWon[] = new String[2];
    public String plrMiss[] = new String[2];
    public String aiMiss[] = new String[2];

    public String doSome[] = new String[2];

    public void setAllMessages()
    {
        plrAtt[0] = "You attacked the enemy"+"\n"+"Damage : ";

        aiAtt[0] = "The enemy attacked you"+"\n"+"Damage : ";

        plrWon[0] = "You defeated the enemy. Congratulations!";

        aiWon[0] = "You were defeated... Better luck next time...";

        plrMiss[0] = "You missed";

        aiMiss[0] = "The opponent missed";

        doSome[0] = "Do something";
    }
}
