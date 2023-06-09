
package ch.hearc.SA.labo1.tools;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Database
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private Database()
		{
		documents = new CopyOnWriteArrayList<Document>();
		names = new CopyOnWriteArrayList<String>();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Initialize the database with the number of documents to generate
	 * @param size
	 */
	public void init(int size)
		{
		for(int i = 0; i < size; i++)
			{
			String name = "Document " + (i + 1);

			documents.add(new Document(name));
			names.add(name);
			}
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	/**
	 * Return all documents names
	 * @return all names
	 */
	public CopyOnWriteArrayList<String> getNames()
		{
		return names;
		}

	/**
	 * Return all documents
	 * @return all documents
	 */
	public CopyOnWriteArrayList<Document> getDocuments()
		{
		return documents;
		}

	/**
	 * Select a random document in the database
	 * @return a random document contained in the database
	 */
	public Document getRandomDocument()
		{
		if (documents.size() > 0)
			{ return documents.get((int)(Math.floor(Math.random() * documents.size()))); }

		return null;
		}

	public static Database getInstance()
		{
		lockSingleton.lock();
		if (instance == null)
			{
			instance = new Database();
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

	// Singleton lock
	private final static ReentrantLock lockSingleton = new ReentrantLock();
	private static Database instance;

	//https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/CopyOnWriteArrayList.html
	private CopyOnWriteArrayList<String> names;
	private CopyOnWriteArrayList<Document> documents;

	}
