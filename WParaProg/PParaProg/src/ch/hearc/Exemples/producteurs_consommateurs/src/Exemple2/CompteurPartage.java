
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple2;

public class CompteurPartage
	{

	// Synchronized sur la function inc et dec
	public static void main(String[] args)
		{

		CompteurProtege cpt = new CompteurProtege("Cpt");

		Thread Clients[] = new Thread[6];
		for(int i = 0; i < Clients.length; i++)
			{
			Client c = new Client(i + 1, cpt);
			Clients[i] = new Thread(c);
			Clients[i].start();

			}

		}

	}
