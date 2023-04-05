
package ch.hearc.Corrigés.Serie3.Exercice1;

public class LeSasSem
	{

	public static void main(String[] args)
		{

		int cstMaxpassagers = 6;
		int cstCapaciteSas = 3;

		SasSem leSas = new SasSem(cstCapaciteSas);
		for(int i = 0; i < cstMaxpassagers; i++)
			{
			new Thread(new PassagerSem(i + 1, leSas)).start();
			}
		}
	}
