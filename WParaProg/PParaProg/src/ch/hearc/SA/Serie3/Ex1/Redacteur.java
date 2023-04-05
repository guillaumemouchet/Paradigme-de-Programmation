
package ch.hearc.SA.Serie3.Ex1;

import java.time.LocalDateTime;

public class Redacteur implements Runnable
	{

	Redacteur()
		{
		}

	@Override
	public void run()
		{
		System.out.println("Ecrire Info météorologique at " + LocalDateTime.now());
		}
	}
