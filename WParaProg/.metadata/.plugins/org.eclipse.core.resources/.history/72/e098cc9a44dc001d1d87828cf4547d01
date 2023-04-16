
package ch.hearc.SA.labo1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import ch.hearc.SA.labo1.tools.Database;
import ch.hearc.SA.labo1.tools.Person;
import ch.hearc.SA.labo1.tools.WaitingLogger;

public class UseLabo1
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Start a new cancellable future task to run the console reading
	 * @param args Program parameters, not used
	 */
	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		new Thread(consoleTask).start();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * Task starting the threads and reading the console, cancelled on EXIT or when all threads are done
	 */
	private static FutureTask<String> consoleTask = new FutureTask<>(new Callable<String>()
		{

		@Override
		public String call() throws Exception
			{
			int nbDocuments = 0;
			int nbPersons = 0;

			/*
			 * ----------------------------------------------------------------------------------------
			 * Done : Demander a l'utilisateur d'entrer un nombre de documents et un nombre de personne
			 *
			 * Remarque : via console ou interface graphique
			 * ----------------------------------------------------------------------------------------
			 */
			System.out.println("---------------------------------------------------------");
			System.out.println("*           Labo 1 - Paradigme de Programmation         *");
			System.out.println("*           Guillaume Mouchet - ISC3il-a                *");
			System.out.println("---------------------------------------------------------");
			Scanner sc = new Scanner(System.in);

			while(nbPersons<1 || nbPersons>9)
				{
			System.out.println("Insert the number of readers / writers [1:9]:");
			nbPersons = sc.nextInt();
				}
			while(nbDocuments<1 || nbDocuments>9)
				{
			System.out.println("Insert the number of concurrent documents [1:9] :");
			nbDocuments = sc.nextInt();
				}


			// Database
			Database db = Database.getInstance();
			db.init(nbDocuments);

			// Waiting logger
			WaitingLogger waitingLogger = WaitingLogger.getInstance();

			// Create threads
			ArrayList<Person> persons = generatePopulation(db, nbPersons);

			// Start threads
			ArrayList<Thread> threads = new ArrayList<Thread>();
			for(Person person:persons)
				{
				Thread thread = new Thread(person);
				thread.setName(person.getName());
				thread.start();

				threads.add(thread);
				}

			// Setup waiting controller
			waitingLogger.assignConsoleFuture(consoleTask, persons);

			String next;
			while(!consoleTask.isCancelled())
				{
				/*
				 * --------------------------------------------------------------------------------------------------------------------
				 * DONE : Lire en continu les inputs de l'utilisateur (prevoir une porte de sortie sur demande d'arret du programme).
				 *        Permettre à l'utilisateur de visualiser la prochaine operation des logs
				 *
				 * Remarque : Pour l'arret du programme, ne pas oublier d'interrompre tous les threads en plus de la tache principale.
				 * 	          Pour afficher la prochaine operation du stack, voir la méthode popNextLog du WaitingLogger
				 * --------------------------------------------------------------------------------------------------------------------
				 */

				sc = new Scanner(System.in);
				System.out.println("Veuillez entrer NEXT (N/n) pour continuer ou EXIT (E/e) pour sortir de l'application:");
				next = sc.nextLine();


				if (next.equals("NEXT") || next.equals("N") || next.equals("n"))
					{
					waitingLogger.popNextLog(); //Do all the display
					}
				else if (next.equals("EXIT") || next.equals("E") || next.equals("e"))
					{
					System.out.println("The programme has ended");
					consoleTask.cancel(true); //Cancel the consoleTask
					}

				}
			sc.close();

			threads.stream().forEach(t -> t.interrupt()); //Interrupts all the threads
			return "";
			}
		});

	/**
	 * Generate a list of person and assign them a document from the database
	 * @param db Database containing all documents
	 * @param nbPersons Number of persons to generate
	 * @return a list of persons
	 */
	private static ArrayList<Person> generatePopulation(Database db, int nbPersons)
		{
		ArrayList<Person> persons = new ArrayList<Person>();

		long minStartingTime = 0;
		long maxStartingTime = 5000;
		long minDuration = 1000;
		long maxDuration = 5000;
		double probabilityReader = 0.5f;

		for(int i = 0; i < nbPersons; i++)
			{
			long startTime = (long)(minStartingTime + Math.random() * (maxStartingTime - minStartingTime));
			long duration = (long)(minDuration + Math.random() * (maxDuration - minDuration));
			Person.Role role = Math.random() < probabilityReader ? Person.Role.READER : Person.Role.WRITER;

			persons.add(new Person("Thread " + (i + 1), db.getRandomDocument(), role, roundTime(startTime), roundTime(duration)));
			}

		return persons;
		}

	/**
	 * Round milliseconds to 100
	 * @param time Time to round
	 * @return rounded time
	 */
	private static long roundTime(long time)
		{
		return time - (time % 100);
		}

	}
