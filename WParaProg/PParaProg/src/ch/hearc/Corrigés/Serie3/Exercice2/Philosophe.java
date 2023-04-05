
package ch.hearc.Corrigés.Serie3.Exercice2;

import java.util.concurrent.Semaphore;

public class Philosophe implements Runnable
	{

	Semaphore leftFork;
	Semaphore rightFork;
	Semaphore mutex;
	int id;

	public Philosophe(int id, Semaphore leftFork, Semaphore rightFork, Semaphore mutex)
		{
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		this.id = id;
		this.mutex = mutex;
		}

	@Override
	public void run()
		{
		while(true)
			{
			pense();
			takeForks();
			eat();
			leaveForks();
			}
		}

	private void leaveForks()
		{
		System.out.println(id + " lache fourchettes");
		leftFork.release();
		rightFork.release();
		}

	private void eat()
		{
		System.out.println(id + " mange");
		try
			{
			Thread.sleep(10000);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	private void takeForks()
		{
		boolean good = false;
		while(!good)
			{
			try
				{
				mutex.acquire();

				if (leftFork.availablePermits() > 0 && rightFork.availablePermits() > 0)
					{

					System.out.println(id + " prend les deux fourchettes");

					leftFork.acquire();
					rightFork.acquire();

					good = true;
					}

				mutex.release();
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			}
		}

	private void pense()
		{
		System.out.println(id + " pense");
		try
			{
			Thread.sleep(1000);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	}
