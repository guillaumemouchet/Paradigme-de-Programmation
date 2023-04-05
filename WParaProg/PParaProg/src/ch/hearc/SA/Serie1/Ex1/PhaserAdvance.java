
package ch.hearc.SA.Serie1.Ex1;

import java.util.concurrent.Phaser;

public class PhaserAdvance extends Phaser
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PhaserAdvance(int i)
		{
		super(i);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	protected boolean onAdvance(int phase, int registeredParties)
		{
		switch(phase)
			{
			case 0:
				System.out.println("Debut de l'exo 1 : phaser");
				return false;

			case 1:
				System.out.println("Debut de l'exo 2 : phaser");
				return false;

			case 2:
				System.out.println("Debut de l'exo 3 : phaser");
				return false;

			case 3:
				System.out.println("fin de l'examen : phaser");
				return false;
			}
		return false;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
