
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple1;

public class CompteurPartage
	{

	// Sans synchronized
	public static void main(String[] args)
		{

		CompteurProtege cpt = new CompteurProtege("Compteur");

		Thread Clients[] = new Thread[6];
		for(int i = 0; i < Clients.length; i++)
			{
			Client c = new Client(i + 1, cpt);
			Clients[i] = new Thread(c);
			Clients[i].start();
			}

		}

	}
