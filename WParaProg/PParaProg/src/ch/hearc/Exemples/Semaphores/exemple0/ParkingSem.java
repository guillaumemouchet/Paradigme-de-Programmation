
package ch.hearc.Exemples.Semaphores.exemple0;

import java.util.concurrent.Semaphore;

public class ParkingSem
	{

	public static void main(String[] args)
		{

		final int cstMasAutos;
		final int csTailleParking;

		cstMasAutos = 10;
		csTailleParking = 4;

		Semaphore leParking = new Semaphore(csTailleParking);
		Thread lesAutomobilistes[] = new Thread[cstMasAutos];

		for(int i = 0; i < cstMasAutos; i++)
			{
			Automobiliste a = new Automobiliste(i + 1, leParking);
			lesAutomobilistes[i] = new Thread(a);
			lesAutomobilistes[i].start();
			}
		}
	}
