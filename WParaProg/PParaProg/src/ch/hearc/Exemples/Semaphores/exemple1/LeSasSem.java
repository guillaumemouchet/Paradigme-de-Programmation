
package ch.hearc.Exemples.Semaphores.exemple1;

public class LeSasSem
	{

	public static void main(String[] args)
		{

		int cstMaxpassagers = 2;
		int cstCapaciteSas = 3;

		cstCapaciteSas = 5;
		cstMaxpassagers = 5;
		SasSem leSas = new SasSem(cstCapaciteSas);
		for(int i = 0; i < cstMaxpassagers; i++)
			{
			new Thread(new PassagerSem(i + 1, leSas)).start();
			}
		}
	}
