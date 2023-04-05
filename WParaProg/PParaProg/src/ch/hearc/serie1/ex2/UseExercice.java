
package ch.hearc.serie1.ex2;

/*
 *Ecrivez en java un programme qui utilise deux threads en parallèle :
• Le premier affichera les 26 lettres de l'alphabet ;
• Le second affichera les nombres de 1 à 26.
 */

public class UseExercice
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
		Runnable runAlpha = createRunnableAlpha();
		Runnable runNom = createRunnableNom();

		Thread threadAlpha = new Thread(runAlpha);
		Thread threadNom = new Thread(runNom);

		threadAlpha.start();
		threadNom.start();

		try
			{
			threadAlpha.join();
			threadNom.join();
			// Le thread courrant attend la fin du thread1
			// Le thread courrant attend la fin du thread2
			// Qui est le thread courrant? Le thread qui exÃ©cute la ligne de code
			// Ici le thread courrant est le thread main

			}
		catch (InterruptedException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private static Runnable createRunnableNom()
		{
		return new Runnable()
			{

			@Override
			public void run()
				{
				for(int i = 0; i <= number; i++)
					{
					System.out.print(i);
					}
				}

			private int number = 26;
			};
		}

	private static Runnable createRunnableAlpha()
		{
		return new Runnable()
			{

			@Override
			public void run()
				{
				for(char i = 'A'; i <= 'Z'; i++)
					{
					System.out.print(i);
					}
				}

			};
		}
	}
