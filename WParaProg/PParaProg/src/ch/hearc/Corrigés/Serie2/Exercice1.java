
package ch.hearc.Corrigés.Serie2;

public class Exercice1
	{

	public static void main(String args[])
		{
		TamponCirc tampon = new TamponCirc(5);
		Producteur prod = new Producteur(tampon);
		Consommateur cons = new Consommateur(tampon);

		prod.start();
		cons.start();

		}

	}

class Producteur extends Thread
	{

	private TamponCirc tampon;
	private int val = 0;

	public Producteur(TamponCirc tampon)
		{
		this.tampon = tampon;
		}

	@Override
	public void run()
		{
		while(true)
			{
			System.out.println("je depose " + val);
			tampon.depose(Integer.valueOf(val++));
			try
				{
				Thread.sleep((int)(Math.random() * 100)); // attend jusqu'a 100 ms
				}
			catch (InterruptedException e)
				{
				}
			}
		}
	}

class Consommateur extends Thread
	{

	private TamponCirc tampon;

	public Consommateur(TamponCirc tampon)
		{
		this.tampon = tampon;
		}

	@Override
	public void run()
		{
		while(true)
			{
			System.out.println("je preleve " + "thread" + Thread.currentThread() + " le nombre" + ((Integer)tampon.preleve()).toString());
			try
				{
				Thread.sleep((int)(Math.random() * 200)); // attends jusqu'a 200 ms
				}
			catch (InterruptedException e)
				{
				}
			}
		}
	}
