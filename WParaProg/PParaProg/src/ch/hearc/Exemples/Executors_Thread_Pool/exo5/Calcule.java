
package ch.hearc.Exemples.Executors_Thread_Pool.exo5;

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
		System.out.println(monNom + " rend  " + resultat);

		return resultat;

		}
	}
