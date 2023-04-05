
package ch.hearc.Exemples.Executors_Thread_Pool.exo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExempleExecutor1Bis
	{

	static int nbThreads;

	public static void main(String[] args)
		{

		nbThreads = 5;

		int nbc = Runtime.getRuntime().availableProcessors();
		System.out.println("la machine dispose de " + nbc + "coeurs.");

		ExecutorService executor = Executors.newWorkStealingPool(); // Work with the number of processor
		for(int i = 0; i < nbThreads; i++)
			{
			executor.execute(new UnRunnable(i, "Bonjour de" + i)); // lance start directement
			}

		executor.shutdown();

		// Tester si tout se termine bien
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
