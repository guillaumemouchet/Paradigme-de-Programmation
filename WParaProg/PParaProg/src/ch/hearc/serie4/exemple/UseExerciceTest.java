
package ch.hearc.serie4.exemple;

public class UseExerciceTest
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		int Max = 10;
		int Sas = 3;

		Sas sas = new Sas(Sas);
		for(int i = 0; i < Max; i++)
			{
			new Thread(new PassagerSem1(sas)).start();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
