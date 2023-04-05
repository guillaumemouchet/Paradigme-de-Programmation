
package ch.hearc.SA.labo1.tools;

import java.util.concurrent.locks.ReentrantLock;

public class Timer
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public long timePassed()
		{
		return System.currentTimeMillis() - startTime;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public static Timer getInstance()
		{
		lockSingleton.lock();
		if (instance == null)
			{
			instance = new Timer();
			}
		lockSingleton.unlock();

		return instance;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	public final long startTime = System.currentTimeMillis();

	// Singleton lock
	private final static ReentrantLock lockSingleton = new ReentrantLock();
	private static Timer instance;

	}
