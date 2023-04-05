
package ch.hearc.serie3.exercice1;


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
		int Max = 2;
		int Sas = 3;

		SasSem leSas = new SasSem(Sas);
		for(int i = 0; i < Max; i++)
			{
			new Thread(new PassagerSem1(i + 1, leSas)).start();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

