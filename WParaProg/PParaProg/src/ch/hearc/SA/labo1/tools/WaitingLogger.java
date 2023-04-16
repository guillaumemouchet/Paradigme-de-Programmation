
package ch.hearc.SA.labo1.tools;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import ch.hearc.SA.labo1.tools.Log.Action;
import ch.hearc.SA.labo1.tools.Log.Type;

public class WaitingLogger
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private WaitingLogger()
		{
		//Helpers
		db = Database.getInstance();

		//BlockingQueue
		finishedQueue = new LinkedBlockingQueue<Person>();
//		processingQueue = new LinkedBlockingQueue<Person>();
//		waitingQueue = new LinkedBlockingQueue<Person>();
		logsQueue = new LinkedBlockingQueue<Log>();


		//Display
		finishedListDisplay = new ArrayList<Person>();
		processingListDisplay = new ArrayList<Person>();
		waitingListDisplay = new ArrayList<Person>();
		logsListDisplay = new ArrayList<Log>();

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Assign the future task to be able to cancel it when all threads are done
	 * @param consoleFuture Future task running the main thread
	 * @param persons List of persons generated by the main thread
	 */
	public void assignConsoleFuture(FutureTask<String> consoleFuture, ArrayList<Person> persons)
		{
		this.consoleFuture = consoleFuture;
		this.persons = persons;
		}

	/**
	 * Add a thread to the waiting queue
	 * @param p Person waiting to access a document
	 */
	public void addWaiting(Person p)
		{
		/*
		 * -----------------------------------------------------------------------------------
		 * DONE : 1) Ajouter une personne (thread) dans la liste d'attente d'acces a son document
		 *
		 * Remarque : ne pas oublier la concurrence
		 * 		 	: Les listes d'attentes sont représentées par la Logs Queue et ses actions

		 * -----------------------------------------------------------------------------------
		 */

		logsQueue.add(new Log(Log.Type.WAITING, p, Log.Action.PUT)); // 1)
		}

	/**
	 * Remove a thread from the waiting queue and add it to the processing queue
	 * @param p Person accessing the document
	 */
	public void removeWaiting(Person p)
		{
		/*
		 * ---------------------------------------------------------------------------------
		 * DONE : 1) Enlever une personne (thread) de la liste d'attente d'acces a son document
		 * 		  2) Ajouter une personne (thread) dans la liste de traitement du document
		 *
		 * Remarque : ne pas oublier la concurrence
		 * 		 	: Les listes d'attentes sont représentées par la Logs Queue et ses actions
		 * ---------------------------------------------------------------------------------
		 */

		logsQueue.add(new Log(Log.Type.WAITING, p, Log.Action.REMOVE)); //1)
		logsQueue.add(new Log(Log.Type.PROCESSING, p, Log.Action.PUT)); //2)

		}

	/**
	 * Remove a thread from the processing queue
	 * @param p Person finishing to access the document
	 */
	public void finished(Person p)
		{
		/*
		 * --------------------------------------------
		 * Done : 	1) Enlever de la liste de process
		 * 			2) Indiquer la fin d'acces a un document
		 *
		 * Remarque : ne pas oublier la concurrence
		 * 		 	: Les listes d'attentes sont représentées par la Logs Queue et ses actions
		 * 			: La Queue de fin est utile pour savoir la fin d'execution

		 * --------------------------------------------
		 */

		try
			{
			finishedQueue.put(p); //2)
			}
		catch (InterruptedException e)
			{
			System.out.println("Thread " + Thread.currentThread().getId() + " is interrupted");
			}

		logsQueue.add(new Log(Log.Type.PROCESSING, p, Log.Action.REMOVE)); //1)
		logsQueue.add(new Log(Log.Type.FINISHED, p, Log.Action.PUT)); //2)

		}

	/**
	 * Called by the user on typing 'NEXT', display the next operation logged
	 */
	public void popNextLog()
		{
		Log nextLog = null;

		//Check if their is something to display
		while(logsQueue.size() == 0)
			{
			if (finishedQueue.size() != persons.size())
				{
				System.out.println("No Logging in queue, waiting");
				sleep(1000);
				}
			}

		/*
		 * -----------------------------------------------------------------------------------------------------------
		 * DONE : 1) Recuperer le prochain log du stockage pour le traitement
		 *
		 * Remarque : a vous de definir votre type de stockage selon l'UI et l'infrastructure que vous voulez utiliser
		 * -----------------------------------------------------------------------------------------------------------
		 */

		nextLog = logsQueue.poll(); //1)

		/*
		 * The display are seperated in different functions
		 * All displays where inspired by the demo video for the project
		 */
		printThread();
		printQueueStates(nextLog);
		printDiagram();
		printAction(nextLog);

		stepCounter++;

		/*
		 * -----------------------------------------------------------------------------------------------------------------------------------
		 * DONE : 1) Controller si il s'agit du dernier log, arreter le programme si c'est le cas
		 *
		 * Remarque : interrompre consoleFuture
		 *            (2 conditions doivent etre reunies : logs == 0 et nombre de threads termines correspondant aux nombre total de personnes)
		 * -----------------------------------------------------------------------------------------------------------------------------------
		 */
		if (logsQueue.size() == 0 && finishedQueue.size() == persons.size()) //1)
			{
			System.out.println("The execution has come to an end");
			consoleFuture.cancel(true);
			}
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public static WaitingLogger getInstance()
		{
		lockSingleton.lock();
		if (instance == null)
			{
			instance = new WaitingLogger();
			}
		lockSingleton.unlock();

		return instance;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Show all threads as well with their start time and duration
	 */
	private void printThread()
		{
		// Print threads
		System.out.println();
		System.out.println("---- Threads list ----------------------------------------------");
		System.out.println();
		persons.stream()//
				.forEach(person -> System.out.println(//
						person.getName() //
								+ " (" + person.getRole() //
								+ ") start : " + person.getStartingTime() //
								+ " / duration : " + person.getDurationTime() //
								+ " (" + person.getDocument().getName() + ")"));
		}

	/**
	 * Show the state of the Queue
	 * They use they own display list to have a one by one display
	 * They don't show the transition when a Person is removed (the Person is just not visible)
	 * He will re-appear once a PUT is called
	 */
	private void printQueueStates(Log nextLog)
		{
		updateListDisplay(nextLog);

		// Print Queue states
		System.out.println();
		System.out.println("---- Queue states ----------------------------------------------");
		System.out.println();

		/*
		 * Filter for each document if the person is working on it
		 */
		for(Document document:db.getDocuments())
			{
			System.out.print(document.getName() + " (WAITING)\t: ");
			waitingListDisplay.stream()//
					.filter(person -> person.getDocument().equals(document))//
					.forEach(person -> System.out.print(//
							person.getName() + " (" + person.getRole().toString().charAt(0) + ") "));
			System.out.println();

			System.out.print(document.getName() + " (PROCESSING)\t: ");
			processingListDisplay.stream().filter(//
					person -> person.getDocument().equals(document))//
					.forEach(person -> System.out.print(//
							person.getName() + " (" + person.getRole().toString().charAt(0) + ") "));
			System.out.println();

			System.out.print(document.getName() + " (FINISHED)\t: ");
			finishedListDisplay.stream().filter(//
					person -> person.getDocument().equals(document))//
					.forEach(person -> System.out.print(//
							person.getName() + " (" + person.getRole().toString().charAt(0) + ") "));
			System.out.println();

			System.out.println();
			}
		}

	/**
	 * Show a diagramme of the action made by the persons on a document
	 * They are displayed step by step (some may be similar, but cut in more action to add visibility)
	 * Use it's own LogListDisplay to keep in memory all action done on the documents
	 */
	private void printDiagram()
		{
		// Print Diagram
		System.out.println("\n---- Diagram ---------------------------------------------------\n");

		System.out.println("W: Waiting / R: Removed from waiting / P: Processing / F: Finished\n");

		System.out.print("Step\t\t\t");

		IntStream.range(0, stepCounter).forEach(i -> System.out.print(i + "\t")); // Display the different steps
		System.out.println();

		for(Person person:persons)
			{
			//Display all the current person
			System.out.print(person.getName() + " " + "(" + person.getRole() + ")\t");

			int lastPosition = 0; //used to get how many tabs or "-" must be displayed

			//Show all action made by the current person
			Log currentLog = null;
			for(int i = 0; i < logsListDisplay.size(); i++)
				{
				if (logsListDisplay.get(i).getPerson() == person)
					{
					currentLog = logsListDisplay.get(i);

					//To help visibility if a put action is made it will be linked to it's remove action
					if (logsListDisplay.get(lastPosition).getAction() == Action.PUT && logsListDisplay.get(lastPosition).getPerson() == person)
						{
						System.out.print("--------".repeat(Math.abs(i - lastPosition)));
						}
					else //else a tab is put
						{
						/*
						 * NOTE : a tab is equals to 6 "-", a seventh is put to correspond to the number displayed for the step
						 */
						System.out.print("\t".repeat(Math.abs(i - lastPosition)));
						}

					lastPosition = i; //The last position where a action was made is the current one

					//Depending on the Action and the type a char is displayed, normally the first of the Type, except for the end of waiting
					System.out.print(//
							currentLog.getType() == Type.WAITING && currentLog.getAction() == Action.REMOVE//
							? "R" : currentLog.getType().toString().charAt(0));
					}
				}
			System.out.println();
			}
		}

	/**
	 * Display in text the last action that was made
	 * Depending on the Action and the Type
	 */
	private void printAction(Log nextLog)
		{
		System.out.println();

		// Treat log action
		String action = "";
		switch(nextLog.getAction())
			{
			case PUT:
				action = "put in";
				break;
			case REMOVE:
				action = "removed from";
				break;
			default:
			}
		// Treat log Type
		switch(nextLog.getType())
			{

			case WAITING:
				System.out.println(">>>> Thread (" + nextLog.getPerson().getName() + ") " + action + " waiting for " + nextLog.getPerson().getDocument().getName());

				break;
			case PROCESSING:

				System.out.println(">>>> Thread (" + nextLog.getPerson().getName() + ") " + action + " processing the " + nextLog.getPerson().getDocument().getName());

				break;
			case FINISHED:

				System.out.println(">>>> Thread (" + nextLog.getPerson().getName() + ")" + " finished the end of all the actions " + nextLog.getPerson().getDocument().getName());

				break;
			default:
				System.out.println(">>>> Thread (" + nextLog.getPerson().getName() + ") is doing non conventionnal actions");

				break;
			}
		System.out.println();

		}

	/**
	 * Update all the Display list
	 * Allows to do step by step visualisation
	 * Not all cases as used
	 * When the action is finished no use of removed
	 */
	private void updateListDisplay(Log nextLog)
		{

		Type type = nextLog.getType();
		Person person = nextLog.getPerson();
		Action action = nextLog.getAction();
		// Update lists
		switch(action)
			{
			case PUT:
				switch(type)
					{
					case WAITING:
						waitingListDisplay.add(person);
						break;
					case PROCESSING:
						processingListDisplay.add(person);
						break;
					case FINISHED:
						finishedListDisplay.add(person);
						break;
					default:
					}
				break;
			case REMOVE:
				switch(type)
					{
					case WAITING:
						waitingListDisplay.remove(person);
						break;
					case PROCESSING:
						processingListDisplay.remove(person);
						break;
					case FINISHED:
						finishedListDisplay.remove(person);
						break;
					default:
					}
				break;
			default:
			}
		logsListDisplay.add(nextLog);

		}

	/**
	 * Sleep for a certain given time
	 * @params timeDuration : time the Threads is sleeping
	 **/
	private void sleep(long timeDuration)
		{
		try
			{
			Thread.sleep(timeDuration);
			}
		catch (InterruptedException e)
			{
			System.out.println("Thread " + Thread.currentThread().getId() + " is interrupted");
			}

		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Singleton lock
	private final static ReentrantLock lockSingleton = new ReentrantLock();
	private static WaitingLogger instance;
	/*
	 * -----------------------------------------------------------------------------------------------
	 * Done : Prevoir un stockage concurrent pour les logs et pour les listes d'attente des documents
	 *
	 * Remarque : java.util.concurrent contient tout ce qu'il faut
	 * -----------------------------------------------------------------------------------------------
	 */

	// BlockginQueue to have a concurrent storage
	private BlockingQueue<Log> logsQueue;
	private BlockingQueue<Person> finishedQueue;

	//Similar to their BlockingQueue but don't pop the actions to display them one by one
	private ArrayList<Log> logsListDisplay;
	private ArrayList<Person> finishedListDisplay;
	private ArrayList<Person> processingListDisplay;
	private ArrayList<Person> waitingListDisplay;

	// Variables
	private ArrayList<Person> persons;
	// Singletons
	private Database db;

	private FutureTask<String> consoleFuture;
	private static int stepCounter = 1;

	}
