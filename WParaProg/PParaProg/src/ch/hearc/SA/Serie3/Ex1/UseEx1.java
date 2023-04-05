
package ch.hearc.SA.Serie3.Ex1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UseEx1
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
		ScheduledExecutorService executorService;
		executorService = Executors.newSingleThreadScheduledExecutor();
		try
			{
			Thread.sleep(2000);
			}
		catch (InterruptedException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		executorService.scheduleAtFixedRate(new Redacteur(), 0, 10, TimeUnit.SECONDS);
		try
			{
			Thread.sleep(3000);
			}
		catch (InterruptedException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		executorService.scheduleAtFixedRate(new Lecteur(), 0, 5, TimeUnit.SECONDS);

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
