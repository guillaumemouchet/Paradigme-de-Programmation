
package ch.hearc.serie7.Exercice2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UseMyTask
	{

	public static void main(String[] args)
		{

		int[][] a = new int[3][3];
		int[][] b = new int[3][3];
		int h = 0;

		for(int i = 0; i < a.length; i++)
			{
			for(int j = 0; j < a[i].length; j++)
				{
				a[i][j] = h++;
				}
			}

		for(int i = 0; i < b.length; i++)
			{
			for(int j = 0; j < b[i].length; j++)
				{
				b[i][j] = h--;
				}
			}
		for(int i = 0; i < a.length; i++)
			{
			for(int j = 0; j < a[i].length; j++)
				{
				a[i][j] = h++;
				}
			}

		for(int i = 0; i < b.length; i++)
			{
			for(int j = 0; j < b[i].length; j++)
				{
				b[i][j] = h--;
				}
			}

		for(int i = 0; i < a.length; i++)
			{
			for(int j = 0; j < a[i].length; j++)
				{
				System.out.print(a[i][j]);
				}
			}

		System.out.println("\n-----------");
		for(int i = 0; i < b.length; i++)
			{
			for(int j = 0; j < b[i].length; j++)
				{
				System.out.print(b[i][j]);
				}
			}

		System.out.println("Callable invoke");

		callableInvokeAll(a, b);

		}

	private static void callableInvokeAll(int[][] a, int[][] b)
		{
		ExecutorService poolThread = Executors.newFixedThreadPool(2);

		ArrayList<MyTask> list = new ArrayList<MyTask>();

		for(int i = 0; i < 3; i++)
			{
			list.add(new MyTask(a[i]));
			}
		for(int i = 0; i < 3; i++)
			{
			list.add(new MyTask(b[i]));
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
