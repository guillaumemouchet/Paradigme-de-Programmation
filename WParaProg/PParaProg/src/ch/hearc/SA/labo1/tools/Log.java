
package ch.hearc.SA.labo1.tools;

public class Log
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Log(Type type, Person person, Action action)
		{
		this.type = type;
		this.person = person;
		this.action = action;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Type getType()
		{
		return type;
		}

	public Person getPerson()
		{
		return person;
		}

	public Action getAction()
		{
		return action;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Enumeration Public						*|
	\*------------------------------------------------------------------*/

	public enum Action
		{
	PUT, REMOVE
		}

	public enum Type
		{
	WAITING, PROCESSING, FINISHED
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Type type;
	private Person person;
	private Action action;

	}
