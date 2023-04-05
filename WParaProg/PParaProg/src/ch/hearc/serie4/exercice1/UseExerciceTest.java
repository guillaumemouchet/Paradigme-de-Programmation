
package ch.hearc.serie4.exercice1;

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
		int Sas = 3;

		Stade sas = new Stade(Sas);
		for(int i = 0; i < 4; i++)
			{
			new Thread(new Joueur(sas, "A")).start();
			}
		for(int i = 0; i < 4; i++)
			{
			new Thread(new Joueur(sas, "B")).start();
			}
		/*for(int i = 0; i < 4; i++)
			{
			new Thread(new Joueur(sas, "C")).start();
			}*/
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
