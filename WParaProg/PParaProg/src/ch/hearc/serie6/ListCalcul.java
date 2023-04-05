
package ch.hearc.serie6;

import java.util.List;

/*
 * La m�thode stop est d�pr�ci� dans l�API, trouvez une solution � ce probl�me. Donnez un exemple.
 */

public class ListCalcul implements Runnable
	{

	private List<Integer> list;
	private Sas sas;

	ListCalcul(List<Integer> list, Sas s)
		{
		this.list = list;
		this.sas = s;
		}
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{

		// on calcul
		int sum = 0;

		sum = list.stream().mapToInt(i -> i).sum();
		System.out.println(sum);
		// On passe le Sas
		double total = sas.calculer(sum);
		//On recalcul
		System.out.println(total);
		double sumPourcent = list.stream().mapToDouble(i -> i / total * 100).sum();
		//on sort
		System.out.println(sumPourcent);
		sas.sortir(sumPourcent);

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
