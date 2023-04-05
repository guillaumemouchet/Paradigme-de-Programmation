
package ch.hearc.Exemples.CountDownLatch;

import java.util.concurrent.CountDownLatch;

// Le premier est un signal de départ qui empêche tout travailleur d'avancer jusqu'à ce que le conducteur soit prêt à le faire ;
// Le second est un signal d'achèvement qui permet au conducteur d'attendre que tous les travailleurs aient terminé.

public class Driver
	{

	static int N = 10;

	public static void main(String[] args) throws InterruptedException
		{
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);

		for(int i = 0; i < N; ++i) // create and start threads
			{
			new Thread(new Worker(startSignal, doneSignal)).start();
			}

		doSomethingElse(); // don't let run yet
		startSignal.countDown(); // let all threads proceed
		doSomethingElse();
		doneSignal.await(); // wait for all to finish
		System.out.println("tous les travailleurs ont terminé.");
		}

	static void doSomethingElse()
		{
		}

	}

class Worker implements Runnable
	{

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal)
		{
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
		}

	@Override
	public void run()
		{
		try
			{
			startSignal.await();
			doWork();
			doneSignal.countDown();
			}
		catch (InterruptedException ex)
			{
			} // return;
		}

	void doWork()
		{
		System.out.println("je fais quelque chose ");
		}
	}
