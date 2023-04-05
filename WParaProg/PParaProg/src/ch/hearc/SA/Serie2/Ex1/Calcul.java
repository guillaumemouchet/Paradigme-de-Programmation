
package ch.hearc.SA.Serie2.Ex1;

import java.util.concurrent.RecursiveAction;

class Calcul extends RecursiveAction
	{

	final int LIMIT = 100;
	//keep static
	static int result;
	int start, end;
	int[] data;

	Calcul(int[] data, int start, int end)
		{
		this.start = start;
		this.end = end;
		this.data = data;
		//System.out.println(" Objet de plus avec debut:   " + this.start + "  et fin :  " + this.end);
		}



	@Override
	protected void compute()
		{
		//System.out.println(" compute");
		if ((end - start) < LIMIT)
			{
			for(int i = start; i < end; i++)
				{
				result += data[i] * data[i];
				}
			}
		else
			{
			int mid = (start + end) / 2;
			Calcul left = new Calcul(data, start, mid);
			Calcul right = new Calcul(data, mid, end);
			left.fork();
			right.fork();
			left.join();
			right.join();
			}
		}
	}
