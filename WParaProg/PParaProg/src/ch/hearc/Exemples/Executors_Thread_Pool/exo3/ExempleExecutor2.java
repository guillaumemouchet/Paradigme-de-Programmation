
package ch.hearc.Exemples.Executors_Thread_Pool.exo3;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExempleExecutor2
	{

	static int nbThreads;
	static int taillePool;

	public static void main(String[] args)
		{

		nbThreads = 30;
		taillePool = 5;

		ExecutorService executor = Executors.newFixedThreadPool(taillePool);
		for(int i = 0; i < nbThreads; i++)
			{
				executor.execute(new UnRunnable(i, "Bonjour de" + i));
			}

		try
			{
			// On dit qu'on veut terminer dans 1500
			@SuppressWarnings("unused")
			boolean b = executor.awaitTermination(1500, TimeUnit.MILLISECONDS);
			}
		catch (InterruptedException e)
			{
			System.err.println("Bizarre et surprenant");
			}

		System.out.println(" La recreation est terminee... ");

		//On récupère tout les threads qui n'ont pas fini
		List<Runnable> liste = executor.shutdownNow();
		System.out.println(" Tout le monde est termine... ");

		liste.forEach((e) -> {
		System.out.println(" " + ((UnRunnable)e).monNumero + "n'a jamais commence");
		});
		}
	}
