
package ch.hearc.serie4.exercice1bis;

import java.util.Random;

public class Athlete implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Athlete(int id, Team team, Stade stade)
		{
		this.id = id;
		this.team = team;
		this.stade = stade;

		this.random = new Random();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		try
			{
			this.stade.startTrainning(this);

			// trainning
			Thread.sleep(this.random.nextInt(500) + 500);

			this.stade.stopTrainning(this);
			}
		catch (InterruptedException e)
			{
			this.trace("Interrompu");
			}
		}

	@Override
	public String toString()
		{
		return "Team(" + this.getTeam() + "), Athlete (" + this.id + ")";
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Team getTeam()
		{
		return this.team;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	public void trace(String message)
		{
		System.out.println(this.toString() + " : " + message);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private int id;
	private Team team;
	private Stade stade;

	// Tools
	private Random random;
	}
