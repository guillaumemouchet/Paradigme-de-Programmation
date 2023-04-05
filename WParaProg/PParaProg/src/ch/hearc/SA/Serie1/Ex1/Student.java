
package ch.hearc.SA.Serie1.Ex1;

import java.util.concurrent.Phaser;

public class Student implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	Student(Phaser phaser)
		{
		this.phaser = phaser;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void run()
		{
		System.out.println("Ready to start.");
		phaser.arriveAndAwaitAdvance();
		Exercice1();
		Exercice2();
		Exercice3();

		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void Exercice1()
		{
		System.out.println("Fin de l'exo 1");
		phaser.arriveAndAwaitAdvance();

		}
	private void Exercice2()
		{
		System.out.println("Fin de l'exo 2");
		phaser.arriveAndAwaitAdvance();

		}
	private void Exercice3()
		{
		System.out.println("Fin de l'exo 3");
		phaser.arriveAndAwaitAdvance();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Phaser phaser;

	}
