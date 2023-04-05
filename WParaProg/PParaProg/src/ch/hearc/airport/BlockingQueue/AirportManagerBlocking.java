
package ch.hearc.airport.BlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

import ch.hearc.airport.AirportFrame;
import ch.hearc.airport.Avion;
import ch.hearc.airport.Tools.AirportManager;

public class AirportManagerBlocking implements AirportManager
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public AirportManagerBlocking(AirportFrame _airportFrame, int nbPisteArr, int nbPisteDep, int nbPlace, int nbAvion)
		{
		this.airportFrame = _airportFrame;
		this.nbAvion = nbAvion;

		this.avionInAirIncoming = new ArrayList<Avion>();
		this.avionInAirGone = new ArrayList<Avion>();
		this.avionLanding = new ArrayList<Avion>();
		this.avionTarmac = new ArrayList<Avion>();
		this.avionTakeOff = new ArrayList<Avion>();
		this.threads = new ArrayList<Thread>();

		this.landing = new ArrayBlockingQueue<Avion>(nbPisteArr);
		this.takeoff = new ArrayBlockingQueue<Avion>(nbPisteDep);
		this.tarmac = new ArrayBlockingQueue<Avion>(nbPlace);

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void start()
		{
		for(int i = 1; i <= nbAvion; i++)
			{
			Avion avion = new Avion(this, i + ":" + codePlane[i - 1]);
			avionInAirIncoming.add(avion);
			Thread av = new Thread(avion);
			threads.add(av);
			av.start();
			}
		}

	@Override
	public void avionInAirTolanding(Avion av) throws InterruptedException
		{
		Thread.sleep(randomTime());
		//Tries to land, checks if buffer is free
		landing.put(av);
		//Landed
		avionInAirIncoming.remove(av);
		avionLanding.add(av);
		refresh();
		}

	@Override
	public void avionLandToTarmac(Avion av) throws InterruptedException
		{
		Thread.sleep(randomTime());
		while(av != landing.peek())
			{
			//Checks thats the calling plane is the next one to quit the buffer
			System.out.println("avionLandToTarmac " + av.getCode());

			}
		//Goes from landing spot to terminal (if free)
		tarmac.put(av);
		landing.take();
		//once free goes to terminal
		avionLanding.remove(av);
		avionTarmac.add(av);
		refresh();
		}

	@Override
	public void avionOnTermToTakeOff(Avion av) throws InterruptedException
		{
		Thread.sleep(randomTime());
		while(av != tarmac.peek())
			{
			//Checks thats the calling plane is the next one to quit the buffer
			System.out.println("avionOnTermToTakeOff " + av.getCode());

			}
		//Goes to take off if free
		takeoff.put(av);
		tarmac.take();
		//Leaves terminal
		avionTarmac.remove(av);
		avionTakeOff.add(av);

		refresh();
		}

	@Override
	public void takeOffToAir(Avion av) throws InterruptedException
		{

		Thread.sleep(randomTime());
		while(av != takeoff.peek())
			{
			//Checks thats the calling plane is the next one to quit the buffer
			System.out.println("takeOffToAir " + av.getCode());

			}
		//Taking off
		takeoff.take();
		avionInAirGone.add(av);
		avionTakeOff.remove(av);
		refresh();
		}

	@Override
	public void avionInAirLeave(Avion av) throws InterruptedException
		{
		//Last refresh for display purpose
		refresh();
		Thread.sleep(randomTime());
		refresh();
		}

	@SuppressWarnings("deprecation")
	@Override
	public void killAll()
		{
		for(Thread thread:threads)
			{
			thread.stop();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private int randomTime()
		{
		//random int optimised for threads
		return ThreadLocalRandom.current().nextInt(min, max) + min;
		//return min;
		}

	private void refresh()
		{
		//Calls airport frame for takeoff
		airportFrame.refreshDisplay(avionInAirIncoming, avionLanding, avionTarmac, avionTakeOff, avionInAirGone);
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private int min = 500;
	private int max = 2000;

	private AirportFrame airportFrame;

	private ArrayBlockingQueue<Avion> landing;
	private ArrayBlockingQueue<Avion> takeoff;
	private ArrayBlockingQueue<Avion> tarmac;

	private List<Avion> avionInAirIncoming;
	private List<Avion> avionLanding;
	private List<Avion> avionTarmac;
	private List<Avion> avionTakeOff;
	private List<Avion> avionInAirGone;
	private List<Thread> threads;

	private int nbAvion;
	private String[] codePlane = { "3B147", "B3291", "6B239", "B1086", "780B4", "32A64", "17A69", "2A431", "647B8", "349A8", "536B8", "9103A", "9B210", "139A4", "96B01", "207B9", "830B6", "8435A", "7301B", "1076B", "5281B", "8A521", "3B806", "B6842", "B6238", "7B816", "A9437", "849A3", "60B18",
			"094B6", "4709B", "36A84", "085A3", "0718B", "80B21", "0A369", "5290A", "370B4", "021A3", "84A02", "052A6", "B6350", "630B5", "8B903", "1398B", "2693A", "902A6", "51A20", "971A5", "A7891" };

	}
