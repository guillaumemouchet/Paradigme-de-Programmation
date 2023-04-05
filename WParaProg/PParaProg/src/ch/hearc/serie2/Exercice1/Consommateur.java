
package ch.hearc.serie2.Exercice1;

public class Consommateur implements Runnable
	{

	private Buffer bfr;

	public Consommateur(Buffer m)
		{
		this.bfr = m;
		}

	@Override
	public void run()
		{
		while(true)
			{
			String name = Thread.currentThread().getName();
			synchronized (bfr)
				{
				try
					{
					System.out.println(name + " waiting to get notified at time:" + System.currentTimeMillis());
					bfr.wait();
					}
				catch (InterruptedException e)
					{
					e.printStackTrace();
					}
				System.out.println(name + " consommateur thread got notified at time:" + System.currentTimeMillis());
				//process the message now
				System.out.println(name + " processed: " + Integer.toString(bfr.getBuffer()));
				}
			}
		}

	}
