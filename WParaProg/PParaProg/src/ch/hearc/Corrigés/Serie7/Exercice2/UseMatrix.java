
package ch.hearc.Corrigés.Serie7.Exercice2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseMatrix
	{

	public static void main(String[] args)
		{
		System.err.println("--- Work pool ---");
		useMatrix(5);

		}

	private static void useMatrix(int size)
		{
		SquareMatrix matrix1 = new SquareMatrix(size);
		System.out.println(matrix1);
		SquareMatrix matrix2 = new SquareMatrix(size);
		System.out.println(matrix2);

		ExecutorService executor = Executors.newWorkStealingPool();

		System.out.println(matrix1.add(matrix2, executor));
		}

	}
