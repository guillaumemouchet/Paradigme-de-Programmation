
package ch.hearc.Exemples.Semaphores.exemple0;

import java.util.concurrent.Semaphore;

class Automobiliste implements Runnable
	{

	private int monId;
	private Semaphore s;

	Automobiliste(int id, Semaphore s)
		{

		monId = id;
		this.s = s;

		}

	private void trace(String m)
		{
		System.out.println("Automobiliste" + monId + ":" + m);
		Thread.yield();
		}

	@Override
	public void run()
		{

		this.trace("je rentre");
		try
			{
			s.acquire();
			}
		catch (Exception e)
			{
			System.err.println("probleme.." + e);
			}
		this.trace("je me ballade en ville");
		s.release();
		this.trace("je sors");
		}
	}
