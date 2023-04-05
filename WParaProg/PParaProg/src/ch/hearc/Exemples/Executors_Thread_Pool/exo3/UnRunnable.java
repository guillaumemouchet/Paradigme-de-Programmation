
package ch.hearc.Exemples.Executors_Thread_Pool.exo3;

class UnRunnable implements Runnable
	{

	private String message;
	public int monNumero;

	UnRunnable(int id, String s)
		{
		this.message = s;
		monNumero = id;
		}

	private void trace(String m)
		{
		System.out.println("UneThread " + monNumero + ":" + m);

		}

	@Override
	public void run()
		{

		this.trace("je dis" + message);
		try
			{
			Thread.sleep(1000);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		this.trace("Aurevoir");
		}

	}
