
package ch.hearc.SA.labo2.BlockingQueue.Producteur_Consommateur;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import ch.hearc.SA.labo2.BlockingQueue.Performance.TimePerformance;

public class Producer implements Runnable
	{

	private BlockingQueue<int[]> queue;
	private long executionTime;

	public Producer(BlockingQueue<int[]> queue, long executionTime)
		{
		this.queue = queue;
		this.executionTime = executionTime;
		}

	private void trace(String m)
		{
		System.out.println(m);
		Thread.yield();
		}

	@Override
	public void run()
		{
		String name = Thread.currentThread().getName();

		//System.out.println(name + " started");

		while(System.currentTimeMillis() - TimePerformance.getInstance().getStartTime() < executionTime)
			{

			try
				{
				Thread.sleep(1000);
				if (queue.remainingCapacity()!=0)
					{
					long startTime = System.currentTimeMillis();

					Random rand = new Random();

					int[] array = rand.ints(10, 1, 100).toArray();

					queue.put(array);
					TimePerformance.getInstance().arrayCreateSentTime(System.currentTimeMillis() - startTime);
					}
				//trace("\n" + name + " creating an array : " + Arrays.toString(array) + "\n");
				}
			catch (InterruptedException e)
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

			}
		System.out.println("Out p");
		}

	}
