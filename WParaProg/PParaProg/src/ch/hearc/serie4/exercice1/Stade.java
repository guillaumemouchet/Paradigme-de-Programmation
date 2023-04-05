
package ch.hearc.serie4.exercice1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Stade
	{

	private final Map<String, Integer> capacite = new HashMap<String, Integer>();
	private int contenu;
	private List<String> clubs;
	private boolean remplir;

	private final Lock l = new ReentrantLock();
	private final Condition entrerA = l.newCondition();
	private final Condition sortirA = l.newCondition();
	private final Condition entrerB = l.newCondition();
	private final Condition sortirB = l.newCondition();
	//private final Condition entrerC = l.newCondition();
	//private final Condition sortirC = l.newCondition();

	Stade(int taille)
		{
		capacite.put("A", taille);
		capacite.put("B", taille);
		capacite.put("C", taille);

		contenu = taille;
		remplir = true;
		clubs = new ArrayList<String>();
		}

	public void passerLeSas(Joueur qui) throws InterruptedException
		{
		l.lock();
		try
			{
			while(!remplir)
				{
				//si < 2 on peut mettre des clubs qu'importe
				if (clubs.size() <= 2)
					{
					System.out.println("SIZZZZZZZZZZZZE" + clubs.size());
					if (qui.equipe == "A")
						{
						entrerA.await();
						}
					if (qui.equipe == "B")
						{
						entrerB.await();
						}
					}
				else if (clubs.size() == 2)
					{ //on controle que le club du joueur est dedans
					if (clubs.contains(qui.equipe))
						{
						if (qui.equipe == "A")
							{
							entrerA.await();
							}
						if (qui.equipe == "B")
							{
							entrerB.await();
							}
						}
					}
				}
			contenu--;
			if (contenu == 0)
				{
				System.out.println("" + qui.Id + qui.equipe + " dedans et ferme la porte");
				remplir = false;
				if (qui.equipe == "A")
					{
					sortirA.signalAll();
					}
				if (qui.equipe == "B")
					{
					sortirB.signalAll();
					}
				clubs.add(qui.equipe);
				}
			else
				{
				System.out.println("" + qui.Id + qui.equipe + " dedans");
				clubs.add(qui.equipe);
				}

			}
		finally
			{
			l.unlock();
			}
		}

	public void sortirDuSas(Joueur qui) throws InterruptedException
		{
		l.lock();
		try
			{
			while(remplir)
				{
				if (qui.equipe == "A")
					{
					sortirA.await();
					}
				if (qui.equipe == "B")
					{
					sortirB.await();
					}
				}
			contenu++;
			if (contenu == capacite.get(qui.equipe))
				{
				System.out.println("" + qui.Id + qui.equipe + " ferme la porte");
				remplir = true;
				if (qui.equipe == "A")
					{
					entrerA.signalAll();
					}
				if (qui.equipe == "B")
					{
					entrerB.signalAll();
					}
				clubs.remove(clubs.indexOf(qui.equipe));

				}
			else
				{
				System.out.println("" + qui.Id + qui.equipe + " sort");
				clubs.remove(clubs.indexOf(qui.equipe));
				}

			}
		finally
			{
			l.unlock();
			}
		}
	}
