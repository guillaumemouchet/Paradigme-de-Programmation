
package ch.hearc.Exemples.Executors_Thread_Pool.exo8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExempleCallable4
	{

	static int nbThreads;
	static int taillePool;

	public static void main(String[] args)
		{

		int somme = 0;

		nbThreads = 10;
		taillePool = 5;

		ExecutorService executor = Executors.newFixedThreadPool(taillePool);
		List<Calcule> lesTaches = new ArrayList<Calcule>();

		System.out.println("main = contruit la liste de calculs");
		for(int i = 1; i <= nbThreads; i++)
			{
			lesTaches.add(new Calcule("C" + i));
			}

		System.out.println("main : lancer les resultats");
		try
			{
			//Permet de récupèrer des listes de résultats futures
			List<Future<Integer>> lesresultats = executor.invokeAll(lesTaches);
			for(Future<Integer> res:lesresultats)
				{
				try
					{
					int x = res.get();
					System.out.println("main : " + x + "recu a  " + new Date());
					somme = somme + x;
					}
				catch (Exception e)
					{
					e.printStackTrace();
					}

				}
			}
		catch (InterruptedException e)
			{
			System.out.println(e + " ne devrait pas arriver");
			}
		System.out.println("main: le calcul   " + somme);
		executor.shutdown();

		}
	}
