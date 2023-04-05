
package ch.hearc.SA.Serie1.Ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseEx1
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		ExecutorService exe = Executors.newFixedThreadPool(4);

		PhaserAdvance phaser = new PhaserAdvance(4);
		exe.submit(new Student(phaser));
		exe.submit(new Student(phaser));
		exe.submit(new Student(phaser));
		exe.submit(new Student(phaser));



		//System.out.println("End");
		exe.shutdown();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	}
