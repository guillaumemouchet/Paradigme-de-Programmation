
package ch.hearc.SA.Serie3.Ex1;

import java.time.LocalDateTime;

public class Lecteur implements Runnable
	{

	Lecteur()
		{
		}

	@Override
	public void run()
		{
		System.out.println("Lire Info météorologique at " + LocalDateTime.now());
		}
	}
