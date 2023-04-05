
package ch.hearc.serie4.exemple;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sas
	{

	private final int capacite;
	private int contenu;
	private boolean remplir;

	private final Lock l = new ReentrantLock();
	private final Condition entrer = l.newCondition();
	private final Condition sortir = l.newCondition();

	Sas(int taille)
		{
		capacite = taille;
		contenu = taille;
		remplir = true;
		}

	public void passerLeSas(int qui) throws InterruptedException
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
				System.out.println("" + qui + "dedans et ferme la porte");
				remplir = false;
				sortir.signalAll();
				}
			else
				{
				System.out.println("" + qui + "dedans");
				}

			}
		finally
			{
			l.unlock();
			}
		}

	public void sortirDuSas(int qui) throws InterruptedException
		{
		l.lock();
		try
			{
			while(remplir)
				{
				sortir.await();
				}
			contenu++;
			if (contenu == capacite)
				{
				System.out.println("" + qui + "ferme la porte");
				remplir = true;
				entrer.signalAll();
				}
			else
				{
				System.out.println("" + qui + "sort");
				}

			}
		finally
			{
			l.unlock();
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
