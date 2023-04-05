
package ch.hearc.serie2.Exercice1;

public class UseMain
	{

	public static void main(String[] args)
		{
		Buffer bfr = new Buffer(-1);
		bfr.setBuffer(0);
		bfr.setBuffer(1);
		bfr.setBuffer(2);

		Consommateur consommateur1 = new Consommateur(bfr);
		new Thread(consommateur1, "Consommateur1").start();

		Consommateur consommateur2 = new Consommateur(bfr);
		new Thread(consommateur2, "Consommateur2").start();

		Producteur producteur1 = new Producteur(bfr);
		new Thread(producteur1, "producteur1").start();

		Producteur producteur2 = new Producteur(bfr);
		new Thread(producteur2, "producteur2").start();

		System.out.println("All the threads are started");
		}

	}
