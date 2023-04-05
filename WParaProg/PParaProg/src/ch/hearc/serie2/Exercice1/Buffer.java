
package ch.hearc.serie2.Exercice1;

public class Buffer
	{

	private int current_index;
	private final int  buffer_capacity = 5;
	private int[] buffer;

	public Buffer(int value)
		{
		current_index = 0;
		buffer = new int[buffer_capacity];
		buffer[(current_index) % buffer_capacity] = value;
		}

	public void setBuffer(int value)
		{
		current_index++;
		buffer[(current_index) % buffer_capacity] = value;
		}

	public int getBuffer()
		{
		current_index++;
		return buffer[(current_index) % buffer_capacity];

		}


	}


