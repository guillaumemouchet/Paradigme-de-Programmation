
package ch.hearc.SA.labo1.tools;

public class Person implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Person(String name, Document doc, Role role, long startingTime, long durationTime)
		{
		// Inputs
		this.name = name;
		this.doc = doc;
		this.role = role;
		// Time
		this.startingTime = startingTime;
		this.durationTime = durationTime;
		// Helpers
		waitingLogger = WaitingLogger.getInstance();
		timer = Timer.getInstance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		try
			{
			/*
			 * -----------------------------------------------------------------------------------------------
			 * DONE : 1) Faire patienter la personne tant que le temps écoule ne depasse pas son temps de depart.
			 *        2) Une fois lance, ajoutez la personne dans la file d'attente d'acces à son document
			 *
			 * Remarque : addWaiting du WaitingLogger
			 * -----------------------------------------------------------------------------------------------
			 */
			this.sleepTimePassed(startingTime); //1)
			waitingLogger.addWaiting(this); //2)

			if (role == Role.READER)
				{
				/*
				 * ------------------------------------------------------------------------------------------------------------------------------------
				 * Done : Tentative de lecture du document.
				 *
				 * Remarque :
				 * 1) Penser àfaire dormir le thread quand il a acces au document (durationTime)
				 * 2) Utiliser les locks du document
				 * 3) Penser au fait que le programme doit pouvoir s'arreter a tout moment (ainsi que tous les threads lecteurs / redacteurs)
				 * 4) Le contenu lu dans le document ne doit pas necessairement �tre traite, seul l'operation de lecture importe
				 * ------------------------------------------------------------------------------------------------------------------------------------
				 */
				doc.getReadLock().lock(); //2)
				this.sleepTimePassed(durationTime); // 1) 3)
				doc.readContent(); //4)
				waitingLogger.removeWaiting(this);
				doc.getReadLock().unlock(); // 2)
				}
			else
				{
				/*
				 * ------------------------------------------------------------------------------------------------------------------------------------
				 * Done : Tentative d'ecriture dans le document.
				 *
				 * Remarque : 1) Penser a faire dormir le thread quand il a acces au document (durationTime)
				 *            2) Utiliser les locks du document
				 *            3) Penser au fait que le programme doit pouvoir s'arreter a tout moment (ainsi que tous les threads lecteurs / redacteurs)
				 *            4) Le nouveau contenu du document importe peu, seule l'acces a l'ecriture du document importe
				 * ------------------------------------------------------------------------------------------------------------------------------------
				 */

				doc.getWriteLock().lock(); //2)
				this.sleepTimePassed(durationTime); // 1) 3)
				doc.setContent(this.name); //4)
				waitingLogger.removeWaiting(this);
				doc.getWriteLock().unlock(); // 2)
				}

			waitingLogger.finished(this);

			}
		catch (Exception e)
			{
			System.out.println("Thread " + Thread.currentThread().getId() + " has ended with an exception :" + e);
			}
		}


	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getName()
		{
		return name;
		}

	public Role getRole()
		{
		return role;
		}

	public Document getDocument()
		{
		return doc;
		}

	public long getStartingTime()
		{
		return startingTime;
		}

	public long getDurationTime()
		{
		return durationTime;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Sleep method to not have to have try catch in all the code
	 * Sleep the thread for 100 milisecond until enought time has passed
	 */
	private void sleepTimePassed(long time)
		{
		while(timePassed() < time)
			{
			try
				{
				Thread.sleep(100); //1)
				}
			catch (InterruptedException e) //3)
				{
				System.out.println("Thread " + Thread.currentThread().getId() + " is interrupted");
				}
			}
		}

	/**
	 * Compute time passed in this particular runnable
	 * @return the time passed in this runnable
	 */
	private long timePassed()
		{
		long currentTime = System.currentTimeMillis();
		long timePassed = currentTime - timer.startTime;
		long timeInPause = currentTime - startPause;

		if (paused)
			{
			return timePassed - timePaused - timeInPause;
			}
		else
			{
			return timePassed - timePaused;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Enumeration Public						*|
	\*------------------------------------------------------------------*/
	public static enum Role
		{
	READER, WRITER
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private String name;
	private Document doc;
	private Role role;

	// Time
	private long startingTime;
	private long durationTime;
	private long startPause;
	private long timePaused;
	private boolean paused;

	// Helpers
	private WaitingLogger waitingLogger;
	private Timer timer;

	}
