
package ch.hearc.Corrigés.Serie4.Exo1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Stade
	{

	private Lock lock;
	private Condition condMax;
	private Condition condTeam;

	private List<Team> players;
	private int max;

	public Stade(int max)
		{
		this.max = max;
		players = new ArrayList<>();

		// Les conditions sont toujours liéée à un ReentrantLock
		lock = new ReentrantLock();
		condMax = lock.newCondition();
		condTeam = lock.newCondition();
		}

	public void enter(Team team)
		{
		lock.lock();

		try
			{
			int NBTeam = countTeam(team);
			boolean waitMaxTeam = NBTeam == max; // Check max team condition
			boolean waitTeam = !players.contains(team) && new HashSet<>(players).size() == 2;
			//Check that your team is in the stade

			//Check if max condition are there
			//await so when the leave is called maybe one of it is open and you can go
			while(waitMaxTeam || waitTeam)
				{
				if (waitMaxTeam)
					{
					condMax.await();
					}

				if (waitTeam)
					{
					condTeam.await();
					}

				waitMaxTeam = NBTeam == max;
				waitTeam = !players.contains(team) && new HashSet<>(players).size() == 2;
				}

			players.add(team);

			System.out.println(players);
			}
		catch (Exception e)
			{
			e.printStackTrace();
			}
		finally
			{
			lock.unlock();
			}
		}

	public void leave(Team team)
		{
		lock.lock();

		try
			{
			players.remove(team);

			//Libère tous les joueurs des autre équipes qui voudrait rentrer
			if (!players.contains(team))
				{
				condTeam.signalAll();
				}

			condMax.signalAll(); //permet de rentrer par rapport à la taille max du stade

			System.out.println(players);
			}
		catch (Exception e)
			{
			e.printStackTrace();
			}
		finally
			{
			lock.unlock();
			}
		}


	// Get the numbers of player of the chosen team
	int countTeam(Team entryTeam)
		{

		int count = 0;

		for(Team team:players)
			{
			if (team == entryTeam)
				{
				count++;
				}

			}

		return count;

		}

	}
