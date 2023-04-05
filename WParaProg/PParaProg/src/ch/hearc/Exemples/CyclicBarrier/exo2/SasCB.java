
package ch.hearc.Exemples.CyclicBarrier.exo2;

import java.util.concurrent.CyclicBarrier;

class SasCB
	{

	private CyclicBarrier leSas;

	SasCB(int taille)
		{
		leSas = new CyclicBarrier(taille, new Signaleur());

		}

	private synchronized void trace(String m)
		{
		System.out.println(" -----" + m);
		}

	public void passerLeSas(int qui) throws InterruptedException
		{

		try
			{
			this.trace(qui + "await sur le sas");
			leSas.await();
			}
		catch (Exception e)
			{
			System.err.println("un probleme est survenu" + e);
			}
		}
	}
