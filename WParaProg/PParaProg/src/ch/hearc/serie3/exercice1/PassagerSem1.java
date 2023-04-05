
package ch.hearc.serie3.exercice1;



public class PassagerSem1 implements Runnable
	{
	private SasSem s;
	private int Id;

	PassagerSem1(int i, SasSem s)
		{
		Id = i;
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
			}
		catch (InterruptedException e)
			{
			// TODOc handle exception
			this.trace("passager " + Id + "interrompu" + e);
			}
		this.trace("Sorti");
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

