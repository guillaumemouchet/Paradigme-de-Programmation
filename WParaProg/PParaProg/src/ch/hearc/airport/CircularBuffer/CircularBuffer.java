
package ch.hearc.airport.CircularBuffer;

import ch.hearc.airport.Avion;

public class CircularBuffer
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public CircularBuffer(int size)
		{
		buffer = new Avion[size];
		this.size = size;
		in = 0;
		out = 0;
		nbPlane = 0;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Both methods enter and exit are inspired from the circular buffer homework
	 */
	public synchronized void enter(Avion plane)
		{
		while(nbPlane == size)
			{
			try
				{
				wait();
				}
			catch (InterruptedException e)
				{
				}

			}
		buffer[in] = plane;
		nbPlane++;
		in = (in + 1) % size;
		notify();
		}

	/*
	 * Do not return a Plane
	 * Front() peeks the next plane that exiting the circular buffer
	 */
	public synchronized void exit()
		{
		while(nbPlane == 0)
			{
			try
				{
				wait();
				}
			catch (InterruptedException e)
				{
				}
			}
		buffer[out] = null;
		nbPlane--;
		out = (out + 1) % size;
		notify();
		}

	public Avion front()
		{
		return buffer[out];
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	public Avion buffer[];
	private int size;
	private int in, out, nbPlane;

	}
