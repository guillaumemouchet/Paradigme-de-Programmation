
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple3;

public class BoiteMessage
	{

	boolean plein;
	int valeur;

	BoiteMessage()
		{

		plein = false;
		}

	synchronized public boolean estVide()
		{

		return !plein;
		}

	synchronized public boolean estPleine()
		{
		return plein;

		}

	synchronized public void ajouteValeur(int valeur) throws Exception
		{
		if (plein)
			{ throw new Exception("Boite est pleine"); }

		this.valeur = valeur;
		plein = true;

		}

	synchronized public int retireValeur() throws Exception
		{
		if (!plein)
			{ throw new Exception("Boite est vide"); }

		plein = false;
		return valeur;
		}

	}
