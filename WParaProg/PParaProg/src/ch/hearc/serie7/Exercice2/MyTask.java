
package ch.hearc.serie7.Exercice2;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Integer>
	{

	private int[] a;

	public MyTask(int[] a)
		{
		this.a = a;
		}

	@Override
	public Integer call()
		{
		System.out.println("Debut tache " + Thread.currentThread().getName());
		int result = 0;
		try
			{
			for(int i = 0; i < a.length; i++)
				{

				result += a[i];

				}
			Thread.sleep(1000);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		System.out.println("Fin tache " + Thread.currentThread().getName());
		return result;
		}

	}
