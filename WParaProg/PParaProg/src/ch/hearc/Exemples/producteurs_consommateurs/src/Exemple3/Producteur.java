
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple3;

class Producteur implements Runnable
	{

	private BoiteMessage b;
	private int monNumero;
	private boolean actif = true;

	Producteur(int i, BoiteMessage b)
		{
		monNumero = i;
		this.b = b;
		}

	private void trace(String m)
		{
		System.out.println("producteur" + monNumero + ":" + m);
		Thread.yield();

		}

	@Override
	public void run()
		{
		try
			{
			this.trace("initialisé");
			while(actif)
				{

				if (b.estVide())
					{
					b.ajouteValeur(monNumero);
					actif = false;
					}
				}
			}
		catch (Exception e)
			{
			this.trace("probleme:" + e);

			}
		finally
			{
			this.trace("termine");
			}
		}

	}
