
package ch.hearc.Exemples.Executors_Thread_Pool.exo4;

import java.util.concurrent.Executors;

public class ExempleExecutor3
	{

	static int nbThreads;

	public static void main(String[] args)
		{

		nbThreads = 10;

		for(int i = 0; i < nbThreads; i++)
			{
			//Ne pas oublier le start dans la default factory
			Executors.defaultThreadFactory().newThread(new UnRunnable(i, "Bonjour de" + i)).start();
			}

		// N'a pas de quoi shutdown ou autre
		System.err.println("rien à faire");
		}
	}
