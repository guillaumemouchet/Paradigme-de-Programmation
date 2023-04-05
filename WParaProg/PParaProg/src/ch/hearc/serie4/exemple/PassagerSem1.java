
package ch.hearc.serie4.exemple;



public class PassagerSem1 implements Runnable
	{
	private Sas s;
	private int Id;
	private static final Object mutex = new Object();
	private static int cpt = 1;

	PassagerSem1(Sas s)
		{
		synchronized(mutex) {
		Id = cpt++;
		}
		this.s = s;
		}

	private void trace(String m)
		{
		System.out.println("Passager " + Id + ":" + m);
		Thread.yield();
		}

	@Override
	public void run()
		{

		this.trace("Pret a entrer");

		try
			{
			s.passerLeSas(Id);
			this.trace("j'y suis");
			s.sortirDuSas(Id);
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

