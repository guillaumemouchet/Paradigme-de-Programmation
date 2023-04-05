
package ch.hearc.airport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.hearc.airport.Tools.Tools;

public class AirportFrame extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AirportFrame(int _nbPisteArr, int _nbPisteDep, int _nbPlace, @SuppressWarnings("unused") int _nbAvion)
		{
		nbPisteArr = _nbPisteArr;
		nbPisteDep = _nbPisteDep;
		nbPlace = _nbPlace;

		listArr = new ArrayList<JLabel>();
		listTerm = new ArrayList<JLabel>();
		listDep = new ArrayList<JLabel>();

		JPanel panel = new JPanel(new BorderLayout());

		JPanel airportPanel = new JPanel();
		airportPanel.setLayout(new GridLayout(1, 3));

		ImageIcon imgRoad = new ImageIcon("img/piste.png");

		JPanel landPanel = new JPanel();
		landPanel.setLayout(new GridLayout(2 + (nbPisteArr - 1), 1));
		ImageIcon imgLand = new ImageIcon("img/landing.png");
		nbLandingLabel = new JLabel("avions en approche :", SwingConstants.CENTER);

		for(int i = 1; i <= _nbPisteArr; i++)
			{
			JLabel imgLandingLabel = new JLabel("", Tools.scaleImage(imgLand, 50, 50), SwingConstants.CENTER);
			imgLandingLabel.setVisible(false);
			listArr.add(imgLandingLabel);
			landPanel.add(imgLandingLabel);
			landPanel.add(new JLabel("", Tools.scaleImage(imgRoad, 50, 50), SwingConstants.CENTER));
			}
		landPanel.add(new JLabel());
		landPanel.add(nbLandingLabel);
		airportPanel.add(landPanel);

		JPanel airportImgPanel = new JPanel();
		airportImgPanel.setLayout(new GridLayout(3, 1));
		ImageIcon imgAir = new ImageIcon("img/airport.png");
		airportImgPanel.add(new JLabel("", Tools.scaleImage(imgAir, 150, 150), SwingConstants.CENTER));
		nbTermLabel = new JLabel("nb avion au terminal :", SwingConstants.CENTER);
		airportImgPanel.add(nbTermLabel);
		airportPanel.add(airportImgPanel);

		JPanel takeOffPanel = new JPanel();
		takeOffPanel.setLayout(new GridLayout(2 + (nbPisteDep - 1), 1));
		ImageIcon imgTakeOff = new ImageIcon("img/takeoff.png");
		nbTakeOffLabel = new JLabel("nb avion au départ :", SwingConstants.CENTER);

		for(int i = 1; i <= _nbPisteDep; i++)
			{
			JLabel imgTakeOffLabel = new JLabel("", Tools.scaleImage(imgTakeOff, 50, 50), SwingConstants.CENTER);
			imgTakeOffLabel.setVisible(false);
			listDep.add(imgTakeOffLabel);
			takeOffPanel.add(new JLabel("", Tools.scaleImage(imgRoad, 50, 50), SwingConstants.CENTER));
			takeOffPanel.add(imgTakeOffLabel);
			}
		takeOffPanel.add(nbTakeOffLabel);
		airportPanel.add(takeOffPanel);

		panel.add(airportPanel, BorderLayout.CENTER);

		JPanel parkPanel = new JPanel();
		for(int i = 1; i <= _nbPlace; i++)
			{
			ImageIcon imgPark = new ImageIcon("img/waiting.png");
			JLabel imgParkLabel = new JLabel("", Tools.scaleImage(imgPark, 50, 50), SwingConstants.CENTER);
			imgParkLabel.setVisible(false);
			listTerm.add(imgParkLabel);
			imgParkLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			parkPanel.add(imgParkLabel);
			}
		panel.add(parkPanel, BorderLayout.SOUTH);

		JPanel onAirPanel = new JPanel();
		onAirPanel.setLayout(new GridLayout(2, 2));
		ImageIcon imgOnAir = new ImageIcon("img/onair.png");
		nbOnAirLabel = new JLabel("nb avion en air (arrive) :", SwingConstants.CENTER);
		onAirPanel.add(new JLabel("", Tools.scaleImage(imgOnAir, 50, 50), SwingConstants.CENTER));
		onAirPanel.add(new JLabel("", Tools.scaleImage(imgOnAir, 50, 50), SwingConstants.CENTER));
		onAirPanel.add(nbOnAirLabel);
		nbOnAirLeaveLabel = new JLabel("nb avion en air (depart) :", SwingConstants.CENTER);
		onAirPanel.add(nbOnAirLeaveLabel);
		panel.add(onAirPanel, BorderLayout.NORTH);

		this.getContentPane().add(panel);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refreshDisplay(List<Avion> avionOnAirArray, List<Avion> avionLandingArray, List<Avion> avionTermArray, List<Avion> avionTakeOffArray, List<Avion> avionOnAirLeaveArray)
		{
		for(int i = 1; i <= nbPisteArr; i++)
			{
			JLabel label = listArr.get(i - 1);
			if (i <= avionLandingArray.size())
				{
				Avion av = avionLandingArray.get(i - 1);
				if (av != null)
					{
					label.setText(av.getCode());
					label.setVisible(true);
					}
				}
			else
				{
				label.setVisible(false);
				}
			}
		for(int i = 1; i <= nbPlace; i++)
			{
			JLabel label = listTerm.get(i - 1);
			if (i <= avionTermArray.size())
				{
				Avion av = avionTermArray.get(i - 1);
				label.setText(av.getCode());
				label.setVisible(true);
				}
			else
				{
				label.setVisible(false);
				}
			}
		for(int i = 1; i <= nbPisteDep; i++)
			{
			JLabel label = listDep.get(i - 1);
			if (i <= avionTakeOffArray.size())
				{
				Avion av = avionTakeOffArray.get(i - 1);
				label.setText(av.getCode());
				label.setVisible(true);
				}
			else
				{
				label.setVisible(false);
				}
			}

		nbOnAirLabel.setText("nb avion en air (arrive) :" + avionOnAirArray.size());
		nbLandingLabel.setText("En approche :" + avionLandingArray.size());
		nbTermLabel.setText("nb avion au terminal :" + avionTermArray.size());
		nbTakeOffLabel.setText("nb avion au départ :" + avionTakeOffArray.size());
		nbOnAirLeaveLabel.setText("nb avion en air (depart) :" + avionOnAirLeaveArray.size());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private static final long serialVersionUID = 1L;

	private ArrayList<JLabel> listTerm;
	private ArrayList<JLabel> listArr;
	private ArrayList<JLabel> listDep;

	private JLabel nbOnAirLabel;
	private JLabel nbLandingLabel;
	private JLabel nbTermLabel;
	private JLabel nbTakeOffLabel;
	private JLabel nbOnAirLeaveLabel;

	private int nbPisteArr;
	private int nbPisteDep;
	private int nbPlace;
	}
