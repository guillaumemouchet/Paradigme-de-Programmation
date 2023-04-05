
package ch.hearc.SA.Serie2.Ex1;

import java.util.concurrent.ForkJoinPool;

public class UseEx1
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
		ForkJoinPool pool = new ForkJoinPool();

		int[] array = new int[10000];

		for(int i = 0; i < array.length; i++)
			{
			array[i] = 1;
			}

		Calcul cal = new Calcul(array, 0, array.length);

		pool.invoke(cal);

		pool.shutdown();

		while(!pool.isTerminated())
			{
			System.out.println("J'attends la fin des threads du Pool");
			try
				{
				Thread.sleep(500);
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}

			}
		System.out.println(Calcul.result);

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
