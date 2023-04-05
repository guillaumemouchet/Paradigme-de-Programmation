
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple1;

class CompteurProtege
	{

	private String monNom;
	private int compteur;

	CompteurProtege(String monNom)
		{

		this.monNom = monNom;
		}

	public void inc()
		{
		compteur++;
		System.out.println("Apres inc, " + this.toString());
		}

	public void dec()
		{
		compteur--;
		System.out.println("Apres dec, " + this.toString());
		}

	@Override
	public String toString()
		{
		return monNom + "=" + compteur;
		}

	}
