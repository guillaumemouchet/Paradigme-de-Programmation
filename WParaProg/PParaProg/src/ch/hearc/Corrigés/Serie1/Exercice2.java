
package ch.hearc.Corrigés.Serie1;

public class Exercice2
	{

	public static void main(String[] args)
		{
		Runnable r = new Runnable1();
		Thread t = new Thread(r);
		Runnable r2 = new Runnable2();
		Thread t2 = new Thread(r2);
		t.start();
		t2.start();
		}
	}

class Runnable2 implements Runnable
	{

	@Override
	public void run()
		{
		for(char i = 'a'; i <= 'z'; i++)
			{
			System.out.print(i + ",");
			}
		}
	}

class Runnable1 implements Runnable
	{

	@Override
	public void run()
		{
		for(int i = 1; i <= 26; i++)
			{
			System.out.print(i + ",");
			}
		}
	}
