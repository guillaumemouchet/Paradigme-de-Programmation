
package ch.hearc.Exemples.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Latch
	{

	public static void main(String[] args) throws InterruptedException
		{

		ExecutorService executor = Executors.newFixedThreadPool(4);

		CountDownLatch latch = new CountDownLatch(3);
		executor.submit(new DependentService(latch));
		executor.submit(new DependentService(latch));
		executor.submit(new DependentService(latch));

		latch.await();

		System.out.println("Tout est initialisé");

		}

	public static class DependentService implements Runnable
		{

		private CountDownLatch latch;

		DependentService(CountDownLatch latch)
			{
			this.latch = latch;
			}

		@Override
		public void run()
			{
			latch.countDown();
			}

		}

	}
