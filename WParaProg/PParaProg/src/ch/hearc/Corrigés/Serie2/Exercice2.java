
package ch.hearc.Corrigés.Serie2;

public class Exercice2
	{

	public static void main(String args[])
		{
		TamponCircV2 tampon = new TamponCircV2(5);
		ProducteurV2 prod = new ProducteurV2(tampon);
		ConsommateurV2 cons = new ConsommateurV2(tampon);

		prod.start();
		cons.start();

		}

	}

class ProducteurV2 extends Thread
	{

	private TamponCircV2 tampon;
	private int val = 0;

	public ProducteurV2(TamponCircV2 tampon)
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

class ConsommateurV2 extends Thread
	{

	private TamponCircV2 tampon;

	public ConsommateurV2(TamponCircV2 tampon)
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
