
package ch.hearc.Corrigés.Serie3.Exercice1;

class PassagerSem implements Runnable
	{

	private SasSem s;
	private int monId;

	PassagerSem(int i, SasSem s)
		{

		monId = i;
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

		this.trace("pret Ã  entrer");
		try
			{
			s.passerLeSas(monId);
			}
		catch (InterruptedException e)
			{
			this.trace("passager" + monId + "interrompu" + e);
			}
		}
	}
