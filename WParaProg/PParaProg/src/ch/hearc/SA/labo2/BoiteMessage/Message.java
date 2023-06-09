
package ch.hearc.SA.labo2.BoiteMessage;

import java.util.Arrays;
import java.util.Random;

public class Message
	{

	//tools
	private Integer[] array;
	public boolean new_array;
	private int size;
	public Message(int size)
		{
		new_array = false;
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
		new_array = false;
		return array;
		}

	synchronized public Integer[] newArray()
		{
		Random rand = new Random();
		array = new Integer[size];
		new_array = true;
		for(int i = 0; i < size; i++)
			{
			array[i] = rand.nextInt(size * 10);
			}
		return array;
		}

	}
