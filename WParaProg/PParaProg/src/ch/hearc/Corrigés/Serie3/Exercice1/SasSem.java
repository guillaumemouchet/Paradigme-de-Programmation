
package ch.hearc.Corrigés.Serie3.Exercice1;

import java.util.concurrent.Semaphore;

class SasSem
	{

	private final int capacite; // capacité du Sas
	private int contenu = 0;

	Semaphore entrer;
	Semaphore sortir;
	Semaphore mutex; //

	SasSem(int taille)
		{
		capacite = taille;
		entrer = new Semaphore(capacite, true);
		sortir = new Semaphore(0, true);
		mutex = new Semaphore(1); //semblable à un mutex

		}

	private synchronized void trace(String m)
		{
		System.out.println("     " + m);
		}

	public void passerLeSas(int qui) throws InterruptedException
		{

		try
			{
			//gestion de l'entree
			entrer.acquire(); //on essaie de rentrer
			mutex.acquire(); //on bloque pour éviter la concurrence
			contenu++; //on augmente le nombre dans le SAS
			if (contenu == capacite) // Si on atteint le max on annonce qu'on sort
				{
				this.trace(qui + "dedans + ferme l'entree");
				sortir.release(capacite);//Sas plein, réveil sortie
				}
			else
				{
				this.trace(qui + "dedans");
				}
			mutex.release();

			//gestion de la sortie
			sortir.acquire();
			mutex.acquire();
			contenu--;
			if (contenu == 0)
				{
				this.trace(qui + "ferme la sortie");
				entrer.release(capacite);//Sas vide, réveil entree
				}
			else
				{
				this.trace(qui + "sort");
				}
			mutex.release();
			}
		catch (Exception e)
			{
			System.err.println("probleme quelque part");
			}
		}

	}
