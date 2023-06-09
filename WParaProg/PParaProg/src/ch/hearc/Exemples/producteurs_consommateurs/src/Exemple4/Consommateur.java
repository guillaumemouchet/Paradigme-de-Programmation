
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple4;

public class Consommateur implements Runnable
	{

	private Message msg;

	public Consommateur(Message m)
		{
		this.msg = m;
		}

	@Override
	public void run()
		{
		String name = Thread.currentThread().getName();
		synchronized (msg)
			{
			try
				{
				System.out.println(name + " waiting to get notified at time:" + System.currentTimeMillis());
				msg.wait(); //attend que le producteur notify une entr�e
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			System.out.println(name + " consommateur thread got notified at time:" + System.currentTimeMillis());
			//process the message now
			System.out.println(name + " processed: " + msg.getMsg());
			}
		}

	}
