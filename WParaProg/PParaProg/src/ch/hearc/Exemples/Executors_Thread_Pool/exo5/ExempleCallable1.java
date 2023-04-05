
package ch.hearc.Exemples.Executors_Thread_Pool.exo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExempleCallable1
	{

	public static void main(String[] args)
		{

		Integer total = 0;
		ExecutorService executor = Executors.newFixedThreadPool(2);

		try
			{
			//Vu que c'est des Callable<T> on doit submit et non execute
			//Il aura donc une m�thode call() et non un run() -> @Override
			Future<Integer> f1 = executor.submit(new Calcule("c1"));
			total = total + f1.get();

			Future<Integer> f2 = executor.submit(new Calcule("c2"));
			total = total + f2.get();

			//On r�cup�re les valeurs avec des gets
			System.out.println("total = " + total);
			executor.shutdown(); // Le shutdown n'est pas propre et pourrait avoir d'autres
			}
		catch (Exception e)
			{

			System.out.println("OOOOh   " + e);
			}

		}
	}
