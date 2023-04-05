
package ch.hearc.Exemples.Verrous_avancés.Sas.exo1;

public class LeSas1
	{

	public static void main(String[] args)
		{

		int cstMaxpassagers = 10;
		int cstCapaciteSas = 5;

		Sas1 leSas = new Sas1(cstCapaciteSas);
		for(int i = 0; i < cstMaxpassagers; i++)
			{
			new Thread(new Passager1(leSas)).start();
			}
		}
	}
