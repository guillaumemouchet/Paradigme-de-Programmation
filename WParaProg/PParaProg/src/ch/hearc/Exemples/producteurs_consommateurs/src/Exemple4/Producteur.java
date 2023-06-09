
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple4;

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
		try
			{
			Thread.sleep(1000);
			synchronized (msg)
				{
				msg.setMsg(name + " Notifier work done");
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
