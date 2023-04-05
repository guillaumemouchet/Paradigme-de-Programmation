
package ch.hearc.Exemples.producteurs_consommateurs.src.Exemple1;

public class Client implements Runnable
	{

	private int monNumero;
	private CompteurProtege monCpt;

	Client(int i, CompteurProtege c)
		{
		monNumero = i;
		monCpt = c;
		}

	@Override
	public void run()
		{
		System.out.println("numero lance" + monNumero);
		for(int i = 0; i <= 4; i++)
			{

			if (monNumero % 2 == 0)
				{
				monCpt.inc();
				}
			else
				{
				monCpt.dec();
				}
			try
				{
				Thread.sleep(100);
				}
			catch (InterruptedException ie)
				{
				System.err.println("probleme avec " + monNumero);
				}
			}
		}

	@Override
	public String toString()
		{
		return "Client" + monNumero;
		}
	}
