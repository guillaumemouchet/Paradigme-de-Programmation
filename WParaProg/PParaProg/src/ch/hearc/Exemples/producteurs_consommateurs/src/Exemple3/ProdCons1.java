
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple3;

public class ProdCons1
	{

	// Utilisation d'un objet à synchroniser, mis sur ces méthodes
	public static void main(String[] args)
		{

		BoiteMessage boite = new BoiteMessage();

		Thread cons = new Thread(new Consommateur(boite));

		Thread prods[] = new Thread[3];

		cons.start(); //demarre

		for(int i = 0; i < prods.length; i++)
			{
			prods[i] = new Thread(new Producteur(i + 1, boite));
			prods[i].start();// demarrer
			}

		}
	}
