
package ch.hearc.SA.labo2.BoiteMessage;

import java.util.Arrays;
import java.util.Random;

public class test
	{

	//tools
	private Integer[] array;
	private int size;
	public test(int size)
		{
		this.size = size;
		Random rand = new Random();
		array = new Integer[size];
		for(int i = 0; i < size; i++)
			{
			array[i] = 0;
			}
		}

	public Integer[] getArray()
		{
		return array;
		}

	synchronized public Integer[] sortArray()
		{
		Arrays.sort(this.array);
		return array;
		}

	synchronized public Integer[] newArray()
		{
		Random rand = new Random();
		array = new Integer[size];
		for(int i = 0; i < size; i++)
			{
			array[i] = rand.nextInt(size * 10);
			}
		return array;
		}

	}
