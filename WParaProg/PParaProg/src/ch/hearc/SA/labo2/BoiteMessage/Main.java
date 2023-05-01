
package ch.hearc.SA.labo2.BoiteMessage;

public class Main
	{

	// Synchronisation des messages directement
	public static void main(String[] args)
	{
			Message msg = new Message(10);
			Consommateur consommateur1 = new Consommateur(msg);
			new Thread(consommateur1, "Consommateur1").start();

			Consommateur consommateur2 = new Consommateur(msg);
			new Thread(consommateur2, "Consommateur2").start();

			Producteur producteur1 = new Producteur(msg);
			new Thread(producteur1, "Producteur1").start();
			Producteur producteur2 = new Producteur(msg);
			new Thread(producteur2, "Producteur2").start();
			Producteur producteur3 = new Producteur(msg);
			new Thread(producteur3, "Producteur3").start();
			System.out.println("All the threads are started");

		}

	}
