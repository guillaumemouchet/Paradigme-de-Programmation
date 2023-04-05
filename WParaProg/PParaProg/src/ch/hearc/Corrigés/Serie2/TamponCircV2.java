
package ch.hearc.Corrigés.Serie2;

public class TamponCircV2
	{

	private Object tampon[];
	private Object sauvegarde;
	private int taille;
	private int en, hors, nMess;
	private boolean init;

	public TamponCircV2(int taille)
		{
		tampon = new Object[taille];
		this.taille = taille;
		en = 0;
		hors = 0;
		nMess = 0;
		sauvegarde = new Object();
		init = false;
		}

	public synchronized void depose(Object obj)
		{
		init = true;
		while(nMess == taille)
			{ // si plein
			try
				{
				wait(); // attends non-plein
				}
			catch (InterruptedException e)
				{
				}
			}
		tampon[en] = obj;
		nMess++;
		en = (en + 1) % taille;
		notify(); // envoie un signal non-vide
		}

	public synchronized Object preleve()
		{
		while(nMess == 0)
			{ // si vide
			try
				{
				if (init == true)
					{
					System.out.println("valeur sauvegardÃ©e");
					return sauvegarde;
					}
				wait(); // attends non-vide
				}
			catch (InterruptedException e)
				{
				}
			}
		Object obj = tampon[hors];
		sauvegarde = tampon[hors];
		tampon[hors] = null; // supprime la ref a l'objet
		nMess--;
		hors = (hors + 1) % taille;
		notify(); // envoie un signal non-plein
		return obj;
		}
	}
