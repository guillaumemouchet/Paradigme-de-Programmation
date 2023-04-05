
package ch.hearc.Exemples.Verrous_avanc�s.Sas.exo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Sas1
	{

	private final int capacite; // capcit� du Sas
	private int contenu; //nombre de places libres
	private boolean remplir;//true phase de remplissage

	private final Lock l = new ReentrantLock(); //moniteur
	private final Condition entrer = l.newCondition();// condition pour l'entr�e
	private final Condition sortir = l.newCondition();// condition pour la sortie

	Sas1(int taille)
		{
		capacite = taille;
		contenu = taille;
		remplir = true;

		}

	public void entrerDansSas(int qui) throws InterruptedException
		{
		l.lock();
		try
			{

			while(!remplir)
				{
				entrer.await();
				}
			contenu--;
			if (contenu == 0)
				{
				System.out.println("" + qui + "dedans +ferme l'entree");
				remplir = false;
				sortir.signal(); //sas plein, réveil cote sortie
				}
			else
				{
				System.out.println(" " + qui + "dedans");
				entrer.signal();
				} //On rentre au compte goutte?
			}
		finally
			{
			l.unlock();// toujours execut�
			}
		}

	public void sortirDuSas(int qui) throws InterruptedException
		{
		l.lock();
		try
			{

			while(remplir)
				{
				System.out.println("" + qui + "devant la sortie");
				sortir.await();
				}
			contenu++;
			if (contenu == capacite)
				{
				System.out.println("" + qui + "ferme la sortie");
				remplir = true;
				entrer.signal(); //sas vide, réveil coté entree
				}
			else
				{
				System.out.println(" " + qui + "sort");
				sortir.signal();
				} //Effet On sort au compte goutte
			}
		finally
			{
			l.unlock();// toujours execut�
			}
		}

	}
