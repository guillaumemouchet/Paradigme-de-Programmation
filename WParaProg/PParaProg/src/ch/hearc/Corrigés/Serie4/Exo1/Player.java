package ch.hearc.Corrigés.Serie4.Exo1;

import java.util.Random;

public class Player extends Thread
{
	private int name;
    private Team team;
    private Stade stade;


    public Player(int name, Team team, Stade stade)
    {
        this.name = name;
        this.team = team;
        this.stade = stade;
    }

    @Override
    public void run()
    {
        try
        {
            sleep(new Random().nextInt(500));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        stade.enter(team);

        System.out.println(name + " of team " + team + " is playing");

        try
        {
            sleep(new Random().nextInt(500) + 500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println(name + " of team " + team + " stop playing");

        stade.leave(team);
    }


}
