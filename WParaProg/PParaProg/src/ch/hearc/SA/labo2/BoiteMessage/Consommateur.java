
package ch.hearc.SA.labo2.BoiteMessage;

import java.util.Arrays;

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

		while(true)
			{
		synchronized (msg)
			{
			try
				{
				System.out.println(name + " waiting to get notified at time:" + java.time.LocalDateTime.now());
				msg.wait(); //attend que le producteur notify une entr�e
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			System.out.println(name + " consommateur thread got notified at time:" + java.time.LocalDateTime.now());
			//process the message now
			if(msg.new_array) //il consomme la ressource si elle est nouvelle
				{
			System.out.println("\t" +name + " array before process : " + Arrays.toString(msg.getArray()));
			System.out.println("\t" +name + " array after process : " + Arrays.toString(msg.sortArray()));
				}else {
					System.out.println("\t" +name + " no new array to process");

				}
			}
			}
		}

	}
