
package ch.hearc.Exemples.Executors_Thread_Pool.exo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExempleExecutor1
	{

	static int nbThreads;
	static int tailPool;

	public static void main(String[] args)
		{

		nbThreads = 10;
		tailPool = 5;

		ExecutorService executor = Executors.newFixedThreadPool(tailPool);

		for(int i = 0; i < nbThreads; i++)
			{
			executor.execute(new UnRunnable(i, "Bonjour de" + i));
			}


		executor.shutdown();

		//Test pour s'assurer de la fin des threads
		while(!executor.isTerminated())
			{
			System.out.println("J'attends la fin des threads du Pool");
			try
				{
				Thread.sleep(300);
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}

			}
		System.out.println("Finished all threads");

		}

	}
