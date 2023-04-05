
package ch.hearc.SA.Serie2.Ex1;

public class Logger implements Runnable
	{

	private Calcul cal;
	private Boolean stop = false;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	Logger(Calcul cal)
		{
		this.cal = cal;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void run()
		{
		while(!stop)
			{
			logger();
			try
				{
				Thread.sleep(1000);
				}
			catch (InterruptedException e)
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		}

	public void setStop(Boolean value)
		{
		this.stop = value;
		}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void logger()
		{
		System.out.println("Nombre de coeur utilis� : " + Runtime.getRuntime().availableProcessors());
		System.out.println("Taille du pool : " + (cal.end - cal.start));
		System.out.println("Le nombre de threads actuellement actives");
		System.out.println("Le nombres de threads ayant fini leurs travaux");
		System.out.println("L��tat de l�ex�cuteur (Executor)");
		System.out.println("-------------------------------------------------");
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
