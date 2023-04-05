
package ch.hearc.Exemples.Semaphores.exemple1;

import java.util.concurrent.Semaphore;

class SasSem
	{

	private final int capacite; // capcité du Sas

	Semaphore entrer;
	Semaphore sortir;

	SasSem(int taille)
		{
		capacite = taille;
		entrer = new Semaphore(capacite, true);
		sortir = new Semaphore(0, true);
		}

	private synchronized void trace(String m)
		{
		System.out.println("            " + m);

		}

	public void passerLeSas(int qui) throws InterruptedException
		{

		try
			{

			//gestion de l'entree
			entrer.acquire();

			if (entrer.availablePermits() == 0)
				{
				this.trace(qui + "dedans +ferme l'entree");
				sortir.release(capacite);//Sas plein, réveil sortie => deux fois release
				}
			else
				{
				this.trace(qui + "dedans");
				}

			//gestion de la sortie

			sortir.acquire();

			if (sortir.availablePermits() == 0)
				{
				this.trace(qui + "ferme la sortie");
				entrer.release(capacite);//Sas vide, réveil entree
				}
			else
				{
				this.trace(qui + "sort");
				}

			}
		catch (Exception e)
			{
			System.err.println("probleme quelque part");
			}
		}

	}
