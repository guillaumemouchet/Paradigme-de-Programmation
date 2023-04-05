
package ch.hearc.airport.Tools;

import ch.hearc.airport.Avion;

public interface AirportManager
	{

	public void start();

	public void avionInAirTolanding(Avion av) throws InterruptedException;

	public void avionLandToTarmac(Avion av) throws InterruptedException;

	public void avionOnTermToTakeOff(Avion av) throws InterruptedException;

	public void takeOffToAir(Avion av) throws InterruptedException;

	public void avionInAirLeave(Avion av) throws InterruptedException;

	public void killAll();

	}
