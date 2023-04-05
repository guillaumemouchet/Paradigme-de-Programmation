
package ch.hearc.serie7.Exercice1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UseMyTask
	{

	public static void main(String[] args)
		{
		System.out.println("Callable submit");
		callableSubmit();
		System.out.println("Callable invoke");
		callableInvokeAll();
		}

	private static void callableSubmit()
		{
		ExecutorService poolThread = Executors.newFixedThreadPool(2);

		Future<Integer> number1 = poolThread.submit(new MyTask(2));
		Future<Integer> number2 = poolThread.submit(new MyTask(2));

		int result = 0;
		try
			{
			result = number1.get() + number2.get();
			System.out.println("Resultat : " + result);
			}
		catch (InterruptedException | ExecutionException e)
			{
			System.out.println("Problem encountered in get");
			e.printStackTrace();
			}

		}

	private static void callableInvokeAll()
		{
		ExecutorService poolThread = Executors.newFixedThreadPool(2);

		ArrayList<MyTask> list = new ArrayList<MyTask>();
		Random rand = new Random();

		for(int i = 0; i < 10; i++)
			{
			list.add(new MyTask(rand.nextInt(2)));
			}
		List<Future<Integer>> futurResults = null;

		try
			{
			futurResults = poolThread.invokeAll(list);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}

		int result = 0;
		for(Future<Integer> futurResult:futurResults)
			{
			try
				{
				result += futurResult.get();
				}
			catch (InterruptedException | ExecutionException e)
				{
				e.printStackTrace();
				}
			}

		System.out.println("Resultat : " + result);

		}

	}
