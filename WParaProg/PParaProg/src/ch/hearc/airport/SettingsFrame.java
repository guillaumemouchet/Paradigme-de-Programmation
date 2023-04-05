
package ch.hearc.airport;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import ch.hearc.airport.BlockingQueue.AirportManagerBlocking;
import ch.hearc.airport.CircularBuffer.AirportManagerCircular;
import ch.hearc.airport.Tools.AirportManager;
import ch.hearc.airport.Tools.WindowAdapteur;

public class SettingsFrame extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public SettingsFrame()
		{
		//default value are
		nbPlane = 20; // max 50
		nbPisteArr = 2;
		nbPisteDep = 1;
		nbPlaceTarmac = 4;
		bufferType = "Circular Buffer";
		geometry();
		control();
		appearance();
		addWindowListener(createWindowListener());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public int getNbPisteArr()
		{
		return this.nbPisteArr;
		}

	public int getNbPisteDep()
		{
		return this.nbPisteDep;
		}

	public int getNbPlaceTarmac()
		{
		return this.nbPlaceTarmac;
		}

	public int getNbPlane()
		{
		return this.nbPlane;
		}

	public String getBufferType()
		{
		return this.bufferType;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void geometry()
		{

		lblPisteArr = new JLabel("Choix du nombre de piste d'atterissage");
		lblPlaceTarmac = new JLabel("Choix du nombre de place sur le Tarmac");
		lblPisteDep = new JLabel("Choix du nombre de piste de départ");
		lblnbPlane = new JLabel("Choix du nombre d'avion total");
		lblBufferType = new JLabel("Choix du type de buffer");

		spinPisteArr = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
		spinPlaceTarmac = new JSpinner(new SpinnerNumberModel(4, 1, 10, 1));
		spinPisteDep = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		spinNbPlane = new JSpinner(new SpinnerNumberModel(20, 1, 50, 1));

		String[] bufferType = { circular, blocking };
		cbBufferType = new JComboBox<String>(bufferType);

		validate = new JButton("validate");
		this.flowLayout = new FlowLayout();
		setLayout(this.flowLayout);

		add(lblPisteArr);
		add(spinPisteArr);

		add(lblPlaceTarmac);
		add(spinPlaceTarmac);

		add(lblPisteDep);
		add(spinPisteDep);

		add(lblPisteDep);
		add(spinPisteDep);

		add(lblnbPlane);
		add(spinNbPlane);

		add(lblBufferType);
		add(cbBufferType);

		add(validate);

		}

	private void control()
		{
		validate.addActionListener(createActionListener());
		}

	private void appearance()
		{
		//setBorder(BorderFactory.createTitledBorder("Flow Layout"));
		this.flowLayout.setHgap(20);
		this.flowLayout.setVgap(20);
		setSize(400, 400);
		setVisible(true); // last!
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}

	private ActionListener createActionListener()
		{
		return new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				System.out.println("Click!");
				nbPlane = (int)spinNbPlane.getValue(); // max 50
				nbPisteArr = (int)spinPisteArr.getValue();
				nbPisteDep = (int)spinPisteDep.getValue();
				nbPlaceTarmac = (int)spinPlaceTarmac.getValue();
				bufferType = cbBufferType.getSelectedItem().toString();

				if (bufferType.equals(circular))
				// BUFFERS CIRCULAIRE
					{
					AirportFrame airportFrame = new AirportFrame(nbPisteArr, nbPisteDep, nbPlaceTarmac, nbPlane);
					airportFrame.setTitle(circular);
					AirportManager airportManager = new AirportManagerCircular(airportFrame, nbPisteArr, nbPisteDep, nbPlaceTarmac, nbPlane);
					airportManager.start();
					airportFrame.addWindowListener(createWindowListenerThreadKiller(airportManager));

					airportFrame.setVisible(true);
					airportFrame.pack();
					}
				else if (bufferType.equals(blocking))
				//BLOCKING QUEUE
					{
					AirportFrame airportFrame = new AirportFrame(nbPisteArr, nbPisteDep, nbPlaceTarmac, nbPlane);
					airportFrame.setTitle(blocking);
					AirportManager airportManager = new AirportManagerBlocking(airportFrame, nbPisteArr, nbPisteDep, nbPlaceTarmac, nbPlane);
					airportManager.start();
					airportFrame.addWindowListener(createWindowListenerThreadKiller(airportManager));

					airportFrame.setVisible(true);
					airportFrame.pack();
					}

				}
			};
		}

	private static WindowListener createWindowListener()
		{
		return new WindowAdapteur()
			{
			@Override
			public void windowClosing(WindowEvent e)
				{
				System.exit(0); // 0 normal, -1 anormal
				}
			};
		}

	private static WindowListener createWindowListenerThreadKiller(AirportManager airportManager)
		{
		return new WindowAdapteur()
			{

			@Override
			public void windowClosing(WindowEvent e)
				{
				try
					{
				//kill all threads
				airportManager.killAll();
					}catch (Exception ex) {
					// TODO: handle exception
					}
				}

			};
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JLabel lblPisteArr;
	private JLabel lblPlaceTarmac;
	private JLabel lblPisteDep;
	private JLabel lblnbPlane;

	private JLabel lblBufferType;

	private JSpinner spinPisteArr;
	private JSpinner spinPlaceTarmac;
	private JSpinner spinPisteDep;
	private JSpinner spinNbPlane;

	private JComboBox<String> cbBufferType;

	private JButton validate;

	private FlowLayout flowLayout;

	private int nbPisteArr;
	private int nbPisteDep;
	private int nbPlaceTarmac;
	private int nbPlane;
	private String bufferType;
	private final String circular = "Circular Buffer";
	private final String blocking = "Blocking Queue";
	}
