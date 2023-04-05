
package ch.hearc.SA.Serie1.Ex2;

import java.util.concurrent.Exchanger;

public class UseEx2
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

		int[] tableau1 = new int[] { 45, 7, 0, 9, 12 };
		int[] tableau2 = new int[] { 4, 11, 4, 1, 8, 0, 12};

		Exchanger<Integer> exchanger = new Exchanger<>();
		ExchangerEx2 e1 = new ExchangerEx2(exchanger, tableau1);
		ExchangerEx2 e2 = new ExchangerEx2(exchanger, tableau2);
		e1.start();
		e2.start();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
