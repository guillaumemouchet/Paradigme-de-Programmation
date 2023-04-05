
package ch.hearc.serie6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class UseExercice
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		int n = 1000;
		List<Integer> list = new ArrayList<Integer>();

		IntStream.range(0, n).forEach(i -> list.add(i));

		System.out.println(list);

		Sas sas = new Sas(4);
		for(int i = 0; i < 4; i++)
			{
			new Thread(new ListCalcul(list, sas)).start();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
