
package ch.hearc.SA.labo2.BoiteMessage;

import java.util.Arrays;

public class Producteur implements Runnable
	{

	private Message msg;

	public Producteur(Message msg)
		{
		this.msg = msg;
		}

	@Override
	public void run()
		{
		String name = Thread.currentThread().getName();

		System.out.println(name + " started");

		while(true)
			{
			try
				{
				Thread.sleep(1000);
				synchronized (msg)
					{
					if(!msg.new_array)
						{
						System.out.println("\n" + name + " creating an array : " + Arrays.toString(msg.newArray()) + "\n");
						}else {
						System.out.println("\t" +name + " old array not yet processed !");

						}
					//msg.notify();
					msg.notifyAll(); //Annonce � tout les consommateurs qu'ils peuvent venir
					//prendre une valeur existante.
					}
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			}
		}

	}
