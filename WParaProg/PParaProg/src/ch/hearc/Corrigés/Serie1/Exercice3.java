
package ch.hearc.Corrigés.Serie1;

import java.util.Random;

public class Exercice3 implements Runnable
	{

	public static void main(String[] args)
		{
		Exercice3 mainThread = new Exercice3();
		Thread thread = new Thread(mainThread);

		thread.start();
		}

	@Override
	public void run()
		{
		SecondaryThread miniThread1 = new SecondaryThread();
		Thread thread1 = new Thread(miniThread1);
		thread1.setName("Thread 1");

		SecondaryThread miniThread2 = new SecondaryThread();
		Thread thread2 = new Thread(miniThread2);
		thread2.setName("Thread 2");

		SecondaryThread miniThread3 = new SecondaryThread();
		Thread thread3 = new Thread(miniThread3);
		thread3.setName("Thread 3");

		thread1.start();
		thread2.start();
		thread3.start();

		try
			{
			thread1.join();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		try
			{
			thread2.join();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		try
			{
			thread3.join();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	}

class SecondaryThread implements Runnable
	{

	@Override
	public void run()
		{

		Random random = new Random();
		int nb;
		int i = 0;
		while(i < 10)
			{
			nb = random.nextInt(200);
			try
				{
				Thread.sleep(nb);
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			System.out.println(Thread.currentThread().getName() + " " + nb);
			i++;
			}
		}
	}
