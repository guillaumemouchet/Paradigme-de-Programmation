
package ch.hearc.serie3.exercice2;

public class Philo implements Runnable
	{

	int i;

	public Philo(int i)
		{
		this.i = i;
		}

	@Override
	public void run()
		{
		new Philo(i);
		System.out.println("Le philosophe " + i + " est arrivé");
		while(true)
			{
			Monitor.prendre_une_fourchette(i);
			Monitor.poser_fourchette(i);

			}
		}
	}
