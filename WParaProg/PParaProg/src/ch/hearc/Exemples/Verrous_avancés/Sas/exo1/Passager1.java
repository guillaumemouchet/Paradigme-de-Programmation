
package ch.hearc.Exemples.Verrous_avancés.Sas.exo1;

class Passager1 implements Runnable
	{

	private Sas1 s;
	private int monId;
	private static final Object mutex = new Object();
	private static int cpt = 1;

	Passager1(Sas1 s)
		{
		synchronized (mutex)
			{
			monId = cpt++;
			}
		this.s = s;
		}

	private void trace(String m)
		{
		System.out.println("Passager" + monId + ":" + m);
		Thread.yield();
		}

	@Override
	public void run()
		{
		try
			{
			this.trace("pret à entrer");
			s.entrerDansSas(monId);
			this.trace("j'y suis");
			s.sortirDuSas(monId);
			this.trace("sorti");
			}
		catch (InterruptedException e)
			{
			this.trace("passager" + monId + "interrompu" + e);
			}
		}
	}
