
package ch.hearc.serie6;

import java.util.concurrent.CyclicBarrier;

public class Sas
	{

	private int total = 0;
	private int totalPourcent= 0;
	private CyclicBarrier sas;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	Sas(int nbThread)
		{
		sas = new CyclicBarrier(nbThread);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public int calculer(int total)
		{
		try
			{
			this.total += total;
			sas.await();
			}
		catch (Exception e)
			{
			System.err.println("Oulala soucis");
			}
			return this.total;
		}

	public void sortir(double sumPourcent)
		{
		try
			{
			this.totalPourcent += sumPourcent;
			sas.await();
			}
		catch (Exception e)
			{
			System.err.println("Oulala soucis");
			}

		System.out.println("sssssssssssssssss"+ totalPourcent);
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
