
package ch.hearc.serie4.exercice1;

public class Joueur implements Runnable
	{

	private Stade s;
	public String equipe;
	public int Id;
	private static final Object mutex = new Object();
	private static int cpt = 1;

	Joueur(Stade s, String equipe)
		{
		synchronized (mutex)
			{
			Id = cpt++;
			}
		this.equipe = equipe;
		this.s = s;
		}

	private void trace(String m)
		{
		System.out.println("Joueur " + Id + ":" + equipe + m);
		Thread.yield();
		}

	@Override
	public void run()
		{

		this.trace("Pret a entrer");

		try
			{
			s.passerLeSas(this);
			this.trace("j'y suis");
			s.sortirDuSas(this);
			this.trace("Sorti");
			}
		catch (InterruptedException e)
			{
			this.trace("passager " + Id + "interrompu" + e);
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
