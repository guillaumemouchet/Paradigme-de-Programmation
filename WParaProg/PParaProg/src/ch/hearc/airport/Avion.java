
package ch.hearc.airport;

import ch.hearc.airport.Tools.AirportManager;

public class Avion implements Runnable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Avion(AirportManager _airportManager, String _codePlane)
		{
		airportManager = _airportManager;
		codePlane = _codePlane;

		}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		try
			{
			waitLand();
			}
		catch (InterruptedException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	public String getCode()
		{
		return codePlane;
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	* The five next methods call the airport manager. It checks if there is space for planes to land/takeoff.
	* The sequence goes as such: In air -> land -> terminal -> takeoff -> in air.
	*/
	private void waitLand() throws InterruptedException
		{
		System.out.println("WAIT LAND : " + this.getCode());
		airportManager.avionInAirTolanding(this);
		land();
		}

	private void land() throws InterruptedException
		{
		System.out.println("LAND : " + this.getCode());
		airportManager.avionLandToTarmac(this);
		waitTakeOff();
		}

	private void waitTakeOff() throws InterruptedException
		{
		System.out.println("WAIT TAKE OFF : " + this.getCode());

		airportManager.avionOnTermToTakeOff(this);
		takeOff();
		}

	private void takeOff() throws InterruptedException
		{
		System.out.println("TAKE OFF : " + this.getCode());

		airportManager.takeOffToAir(this);
		inAir();
		}

	private void inAir() throws InterruptedException
		{
		System.out.println("IN AIR : " + this.getCode());
		airportManager.avionInAirLeave(this);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private AirportManager airportManager;
	private String codePlane;
	}
