
package ch.hearc.serie1.ex3;

/*
 * Ecrivez un programme dont le thread principal lance et nomme trois nouveaux threads. Chaque thread ainsi créé doit effectuer 10 fois les actions suivantes :
• attendre un temps aléatoire compris entre 0 et 200 ms,
• puis afficher son nom
Le thread principal devra attendre la fin de l'exécution des trois threads qu'il a créés avant de terminer son exécution.
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
		Runnable run1 = createRunnable();
		Runnable run2 = createRunnable();
		Runnable run3 = createRunnable();

		Thread thread1 = new Thread(run1);
		Thread thread2 =  new Thread(run2);
		Thread thread3 = new Thread(run3);

		thread1.setName("Benjamin");
		thread2.setName("Guillaume");
		thread3.setName("DodoLePetitAsticot");

		thread1.start();
		thread2.start();
		thread3.start();


		try
			{
			thread1.join();
			thread2.join();
			thread3.join();

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
	private static Runnable createRunnable()
		{
		return new Runnable()
			{

			@Override
			public void run()
				{
					for(int i = 0; i < n; i++)
						{
						 try
							{
							Thread.sleep((int)(Math.random()*(max-min+1)+min));
							}
						catch (InterruptedException e)
							{
							e.printStackTrace();
							}
						 System.out.println(Thread.currentThread().getName());
						}
				}

			private int n = 10;
			private int max = 200;
			private int min = 0;
			};
		}
	}

