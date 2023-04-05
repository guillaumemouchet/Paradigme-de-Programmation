
package ch.hearc.Corrigés.Serie4.Exo1;

public class Exo1
	{

	public static void main(String[] args)
		{
		int n = 5;
		int max = 5;
		int nbJoueur = 16;
		Stade stade = new Stade(max);

		Player[] players = new Player[nbJoueur];

		for(int i = 0; i < 3 * n; ++i)
			{
			players[i] = new Player(i, Team.values()[i % 3], stade);
			}

		for(Player player:players)
			{
			player.start();
			}
		}
	}
