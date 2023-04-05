
package ch.hearc.Exemples.CyclicBarrier.exo1;

import java.util.Random;

class Passager implements Runnable
	{

	private SasCB s;
	private int monId;
	private Random generateur = new Random();

	Passager(int i, SasCB s)
		{
		monId = i;
		this.s = s;
		}

	private void trace(String m)
		{
		System.out.println("Passager" + monId + ":" + m);
		Thread.yield();
		}

	@Override
	public void run()
		{

		int duree = generateur.nextInt(10000);
		this.trace("attente de  " + duree + "  ms");
		try
			{
			Thread.sleep(duree);
			}
		catch (InterruptedException e)
			{
			this.trace(" probleme avec le sleep" + e);
			}

		this.trace("pret a entrer");
		try
			{
			s.passerLeSas(monId);
			}
		catch (InterruptedException e)
			{
			this.trace(" probleme dans le sas" + e);
			}

		this.trace("sorti");
		}

	}
