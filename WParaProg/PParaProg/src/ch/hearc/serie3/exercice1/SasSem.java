
package ch.hearc.serie3.exercice1;

import java.util.concurrent.Semaphore;

public class SasSem
	{

	private final int capacite;

	Semaphore entrer;
	Semaphore sortir;
	Semaphore lock;

	SasSem(int taille)
		{
		capacite = taille;
		entrer = new Semaphore(capacite, true);
		sortir = new Semaphore(0, true);
		lock = new Semaphore(1, true);
		}

	private synchronized void trace(String m)
		{
		System.out.println("           " + m);
		}

	public void passerLeSas(int qui) throws InterruptedException
		{

		try
			{

			lock.acquire();
			entrer.acquire();
			if (entrer.availablePermits() == 0)
				{
				this.trace(qui + "dedans + ferme l'entrée");
				sortir.release(capacite);
				}
			else
				{
				this.trace(qui + "dedans");
				}
			lock.release();

			sortir.acquire();

			if (sortir.availablePermits() == 0)
				{
				this.trace(qui + "ferme la sortie");
				entrer.release(capacite);
				}
			else
				{
				this.trace(qui + " sort");
				}

			}
		catch (Exception e)
			{
			System.err.println("PROBLEME");
			}
		}
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
