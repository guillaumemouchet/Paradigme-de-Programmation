
package ch.hearc.Exemples.Executors_Thread_Pool.exo8;

import java.util.Random;
import java.util.concurrent.Callable;

class Calcule implements Callable<Integer>
	{

	String monNom;
	private Random generateur = new Random();

	Calcule(String n)
		{
		monNom = n;
		}

	@Override
	public Integer call()
		{

		Integer resultat = generateur.nextInt(1000);
		try
			{

			Thread.sleep(resultat * 10);
			}
		catch (Exception e)
			{
			System.out.println("Rien ne va " + e);
			}
		System.out.println(monNom + "rend" + resultat);

		return resultat;

		}

	}
