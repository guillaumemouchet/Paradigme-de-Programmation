
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple4;

public class Main
	{

	// Synchronisation des messages directement
	public static void main(String[] args)
		{
		Message msg = new Message("process it");
		Consommateur consommateur1 = new Consommateur(msg);
		new Thread(consommateur1, "Consommateur1").start();

		Consommateur consommateur2 = new Consommateur(msg);
		new Thread(consommateur2, "Consommateur2").start();

		Producteur producteur = new Producteur(msg);
		new Thread(producteur, "producteur").start();
		System.out.println("All the threads are started");
		}

	}
