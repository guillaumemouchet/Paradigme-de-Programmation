
package ch.hearc.Corrigés.Serie3.Exercice2;

import java.util.concurrent.Semaphore;

public class UsePhilosophe
	{

	public static void main(String[] args)
		{
		final int NB_PHILO = 5;

		Semaphore mutex = new Semaphore(1);
		Semaphore[] fourchettes = new Semaphore[NB_PHILO];
		Philosophe[] philos = new Philosophe[NB_PHILO];

		for(int i = 0; i < NB_PHILO; i++)
			{
			fourchettes[i] = new Semaphore(1);
			}

		for(int i = 0; i < NB_PHILO; i++)
			{
			philos[i] = new Philosophe(i, fourchettes[i], fourchettes[(i + 1) % NB_PHILO], mutex);
			new Thread(philos[i]).start();
			}
		}
	}
