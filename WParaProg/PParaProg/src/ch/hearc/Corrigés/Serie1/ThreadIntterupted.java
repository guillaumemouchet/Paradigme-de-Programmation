
package ch.hearc.Corrigés.Serie1;

public class ThreadIntterupted
	{

	public static void main(String[] args) throws InterruptedException
		{

		Thread t1 = new Thread(new PrintEvenNumbers2());

		Thread t2 = new Thread(new PrintEvenNumbers2());

		// starting the thread
		t1.start();

		// calling interrupt() method.

		t1.interrupt();

		t2.start();

		}
	}

class PrintEvenNumbers2 implements Runnable
	{

	@Override
	public void run()
		{
		for(int i = 1; i <= 2; i++)
			{

			if (Thread.interrupted())
				{
				System.out.println("code for interrupted thread");
				}
			else
				{
				System.out.println("code for normal thread");
				}

			} //end of for loop
		}
	}
