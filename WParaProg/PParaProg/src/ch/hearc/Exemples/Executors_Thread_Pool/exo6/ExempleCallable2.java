
package ch.hearc.Exemples.Executors_Thread_Pool.exo6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExempleCallable2
	{

	public static void main(String[] args)
		{

		Integer total = 0;
		ExecutorService executor = Executors.newFixedThreadPool(3);
		try
			{
			Future<Integer> f1 = executor.submit(new Calcule("c1"));
			int duree = 500;
			boolean reprendre = true;
			while(reprendre)
				{//1ere appel  n boucle
				try
					{
					// On fait des appels après un certain temps
					total = total + f1.get(duree, TimeUnit.MILLISECONDS);
					reprendre = false;
					}
				catch (TimeoutException e)
					{
					duree = 50;
					System.out.println("C est long, en attend 50");
					}
				}
			//2eme aeppel normal
			Future<Integer> f2 = executor.submit(new Calcule("c2"));
			total = total + f2.get();

			System.out.println("total = " + total);
			executor.shutdown();
			}
		catch (Exception e)
			{

			System.out.println("OOOOh   " + e);
			}

		}
	}
