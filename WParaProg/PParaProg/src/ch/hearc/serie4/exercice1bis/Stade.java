
package ch.hearc.serie4.exercice1bis;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Stade
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Stade(int capaciteEquipe, int capaciteAthleteParEquipe)
		{
		// Inputs
			{
			this.capaciteEquipe = capaciteEquipe;
			this.capaciteAthleteParEquipe = capaciteAthleteParEquipe;
			}

		// Tools Conditions
			{
			this.lock = new ReentrantLock();
			this.nbAthletePerTeam = new int[Team.values().length];
			this.enterConditions = new Condition[Team.values().length];

			Arrays.fill(this.nbAthletePerTeam, 0); // Fill with zeros
			Arrays.fill(this.enterConditions, this.lock.newCondition()); // Fill new conditions
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void startTrainning(Athlete athlete) throws InterruptedException
		{
		this.lock.lock();

		try
			{
			int indexTeam = athlete.getTeam().ordinal();

			while(!isAbleToEnter(athlete))
				{
				enterConditions[indexTeam].await();
				}

			this.nbAthletePerTeam[indexTeam]++;
			System.out.println(athlete + " : ENTRE dans le stade");
			}
		finally
			{
			this.lock.unlock();
			}
		}

	public void stopTrainning(Athlete athlete) throws InterruptedException
		{
		this.lock.lock();

		try
			{
			System.out.println(athlete + " : SORT du stade");
			this.nbAthletePerTeam[athlete.getTeam().ordinal()]--;
			Arrays.stream(this.enterConditions).forEach(Condition::signalAll);
			}
		finally
			{
			this.lock.unlock();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private boolean isAbleToEnter(Athlete athlete)
		{
		int indexTeam = athlete.getTeam().ordinal();

		if (this.nbAthletePerTeam[indexTeam] > 0)
			{ return this.nbAthletePerTeam[indexTeam] < this.capaciteAthleteParEquipe; }

		return Arrays.stream(this.nbAthletePerTeam).filter(i -> i > 0).count() < this.capaciteEquipe;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private int capaciteEquipe;
	private int capaciteAthleteParEquipe;

	// Tools Conditions
	private final Lock lock;
	private int[] nbAthletePerTeam;
	private Condition[] enterConditions;
	}
