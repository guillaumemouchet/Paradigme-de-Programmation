
package ch.hearc.Exemples.Executors_Thread_Pool.exo7;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExempleCallable3
	{

	static int nbThreads;
	static int taillePool;

	public static void main(String[] args)
		{

		int somme = 0;

		nbThreads = 10;
		taillePool = 5;

		ExecutorService executor = Executors.newFixedThreadPool(taillePool);

		ArrayList<Future<Integer>> lesresultats = new ArrayList<Future<Integer>>();

		// InvokeAll à la main donc c'est immonde
		System.out.println("main = je lance les calculs");
		for(int i = 1; i <= nbThreads; i++)
			{
			Future<Integer> f = executor.submit(new Calcule("c" + i));
			lesresultats.add(f);
			}
		System.out.println("main : attendre les resultats");

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
		System.out.println("main: le calcul   " + somme);
		executor.shutdown();
		}
	}
