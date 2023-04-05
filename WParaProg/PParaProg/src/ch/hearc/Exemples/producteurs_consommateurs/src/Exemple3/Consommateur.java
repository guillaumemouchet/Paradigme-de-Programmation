
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple3;

class Consommateur implements Runnable
	{

	private BoiteMessage b;
	private boolean actif = true;

	Consommateur(BoiteMessage b)
		{
		this.b = b;
		}

	private void trace(String m)
		{
		System.out.println("consommateur  :" + m);
		Thread.yield();

		}

	@Override
	public void run()
		{
		try
			{
			this.trace("initialise");
			while(actif)
				{
				if (b.estPleine())
					{
					int val = b.retireValeur();
					this.trace("retire" + val);
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
