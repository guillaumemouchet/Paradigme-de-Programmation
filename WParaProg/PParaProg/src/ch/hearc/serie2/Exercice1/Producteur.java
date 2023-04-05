
package ch.hearc.serie2.Exercice1;

public class Producteur implements Runnable
	{

	private Buffer bfr;
	private int Incremented_value = 0;

	public Producteur(Buffer bfr)
		{
		this.bfr = bfr;
		}

	@Override
	public void run()
		{
		while(true)
			{
			String name = Thread.currentThread().getName();
			System.out.println(name + " started");
			try
				{
				Thread.sleep(1000);
				synchronized (bfr)
					{
					bfr.setBuffer(Incremented_value++);
					//bfr.notify();
					bfr.notifyAll();
					}
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}

			}
		}

	}
