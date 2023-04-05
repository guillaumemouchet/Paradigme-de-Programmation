
package ch.hearc.SA.Serie1.Ex2;

import java.util.Arrays;
import java.util.concurrent.Exchanger;

public class ExchangerEx2 extends Thread
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ExchangerEx2(Exchanger<Integer> exchanger, int[] tableau)
		{
		this.exchanger = exchanger;
		this.tableau = tableau;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void run()
		{
		System.out.println("Tri du tableau");

		int max = Arrays.stream(tableau).max().getAsInt();
		try
			{
			Globalmax = exchanger.exchange(max);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}

		if (max >= Globalmax)
			{
			System.out.println("Max is "+ max);
			}
		}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Exchanger<Integer> exchanger;
	private int[] tableau;
	private int Globalmax;
	}
