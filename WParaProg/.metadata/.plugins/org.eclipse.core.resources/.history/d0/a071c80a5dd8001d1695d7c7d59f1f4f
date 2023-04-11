
package ch.hearc.SA.labo1.tools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Document
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Document(String name)
		{
		this.name = name;
		this.contentLock = new ReentrantReadWriteLock();
		this.readLock = contentLock.readLock();
		this.writeLock = contentLock.writeLock();
		content = "No data";
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*
	 * The ReadLock must be used before calling this method
	 */
	public String readContent()
		{
		return content;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*
	 * The WriteLock must be used before calling this method
	 */
	public void setContent(String newContent)
		{
		content = newContent;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getName()
		{
		return name;
		}

	/*
	 * The Person will be able to get the log
	 * He is responsible to use them before accessing the document
	 */
	public Lock getReadLock()
		{
		return readLock;
		}
	public Lock getWriteLock()
		{
		return writeLock;
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*
	 * -----------------------------------------------------------------------------------
	 * DONE : Ajouter les locks au documents afin d'assurer un acces concurrent à celui-ci
	 * Remarque : java.util.concurrent contient tout ce qu'il faut
	 * -----------------------------------------------------------------------------------
	 */

	// Concurrent content
	private String content;
	// Locks
	private ReentrantReadWriteLock contentLock;
	private final Lock writeLock;
	private final Lock readLock;
	// Other variables
	private String name;

	}
