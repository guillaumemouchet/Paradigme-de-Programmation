
package ch.hearc.serie4.exercice1bis;

import java.util.LinkedList;
import java.util.List;

public class UseEntrainement
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		final int teamCapacity = 2;
		final int athletePerTeamCapacity = 5;
		final int athletePerTeam = 10;

		Team[] teams = Team.values();
		Stade stade = new Stade(teamCapacity, athletePerTeamCapacity);
		List<Athlete> listAthletes = new LinkedList<Athlete>();

		for(Team team:teams)
			{
			for(int i = 0; i < athletePerTeam; i++)
				{
				listAthletes.add(new Athlete(i + 1, team, stade));
				}
			}

		listAthletes//
				.stream()//
				.parallel()//
				.map(Thread::new)//
				.forEach(Thread::start);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	// Rien

	}
