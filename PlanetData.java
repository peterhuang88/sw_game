import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;
//
// This is an object which displays the data for each planet.
// Eventually, there will be an UpdateData() method, or
// similar.  Users go through here to put shit in shipyards,
// or interact with staged fleets.
// Staged fleets not yet implemented.
//
//
//
//
public class PlanetData extends JComponent implements MouseListener, MouseMotionListener {
	private JFrame frame;										//frame we are putting this in
	private String name;										//planet data copy
	private Affiliation affiliation;
	private int resource;
	private int fuel;
	private int stage;
	private int shipyards;
	private int shipSize;
	private int oResource;
	private int oFuel;
	private int oStage;
	private String[] ships;
	private Shipyard[] yards;
	private boolean selectorOpen;								//is there a project selector open
	private ProjectSelection currentSelector;					//current selector
	private boolean extraYard;									//for use with jabba
	private Shipyard specialYard;

	public PlanetData(Planet planet) {
		getPlanetInfo(planet);									//get planet info
		super.addMouseListener(this);							//add listeners
		super.addMouseMotionListener(this);
	}
	public void getPlanetInfo(Planet planet) {
		this.name = planet.getName();							//basically, initialize everything
		this.selectorOpen = false;
		this.affiliation = planet.getAffil();
		this.resource = planet.getResource();
		this.fuel = planet.getFuel();
		this.stage = planet.getStaging();
		this.shipyards = planet.getShipyards();
		this.shipSize = planet.getShipSize();
		this.ships = new String[this.shipSize];
		for (int i = 0; i < this.shipSize; i++) {
			this.ships[i] = planet.getShips()[i];
		}
		this.yards = planet.getYards();
		this.oResource = planet.getOResource();
		this.oFuel = planet.getOFuel();
		this.oStage = planet.getOStaging();
		this.extraYard = planet.hasExtraYard();
		this.specialYard = planet.getExtraYard();
	}

	public void paint(Graphics g) {
		Toolkit t = Toolkit.getDefaultToolkit();				//background image for planet data
		Image bg = t.getImage("Images/Templates/PlanetTemplate.jpg");
		g.drawImage(bg, 0, 0, this);
		g.setColor(Color.LIGHT_GRAY);							//draw borders and shit
		g.fillRect(345,0,5,450);
		g.fillRect(0,55,345,5);
		g.fillRect(0,270,345,5);
		g.fillRect(0,330,345,5);
		g.fillRect(0,390,345,5);
		g.fillRect(350,86,450,5);
		g.fillRect(350,177,450,5);
		g.fillRect(350,268,450,5);
		g.fillRect(350,359,450,5);
		g.fillRect(111,270,6,60);
		g.fillRect(228,270,6,60);
		g.fillRect(83,330,4,60);
		g.fillRect(170,330,4,60);
		g.fillRect(257,330,5,60);

		Image planetImage = t.getImage("Images/CardData/" + this.name + ".png");
		g.drawImage(planetImage, 0, 60, this);					//picture for each planet
		Font tf = new Font("Rockwell", Font.BOLD, 40);			//create some fonts
		Font smallfont = new Font("Rockwell", Font.BOLD, 18);
		g.setFont(tf);
		if (this.affiliation.getName().equals("Separatist")) {	//set corresponding color
			g.setColor(Color.CYAN);
		} else if (this.affiliation.getName().equals("Republic")) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.LIGHT_GRAY);
		}
		g.drawString(this.name, 30, 45);						//draw planet name

		g.setColor(Color.LIGHT_GRAY);							//display planetary stats
		g.drawString("R: ", 6, 316);
		if (this.resource < this.oResource) {
			g.setColor(Color.RED);								//red if less than usual
			g.drawString("" + this.resource, 59, 316);
		} else if (this.resource > this.oResource) {			//green if more
			g.setColor(Color.GREEN);
			g.drawString("" + this.resource, 59, 316);
		} else {
			g.setColor(Color.LIGHT_GRAY);						//light gray otherwise
			g.drawString("" + this.resource, 59, 316);
		}

		g.setColor(Color.LIGHT_GRAY);
		g.drawString("F: ", 124, 316);
		if (this.fuel < this.oFuel) {
			g.setColor(Color.RED);
			g.drawString("" + this.fuel, 177, 316);
		} else if (this.fuel > this.oFuel) {
			g.setColor(Color.GREEN);
			g.drawString("" + this.fuel, 177, 316);
		} else {
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("" + this.fuel, 177, 316);
		}

		g.setColor(Color.LIGHT_GRAY);
		g.drawString("S: ", 242, 316);
		if (this.stage < this.oStage) {
			g.setColor(Color.RED);
			g.drawString("" + this.stage, 295, 316);
		} else if (this.stage > this.oStage) {
			g.setColor(Color.GREEN);
			g.drawString("" + this.stage, 295, 316);
		} else {
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("" + this.stage, 295, 316);
		}
		for (int i = 0; i < 3; i++) {							//draw the shipyards
			if (i < this.yards.length) {
				Image yardIMG = t.getImage(this.yards[i].getImageFile());
				g.drawImage(yardIMG,(87*i),335,this);
				if (this.yards[i].getCurrentProject().equals("NO_PROJECT")) {
					g.setColor(Color.GREEN);
					g.setFont(smallfont);
					g.drawString("OPEN", (87*i)+7, 358);
				} else {
					g.setColor(Color.LIGHT_GRAY);
					g.setFont(tf);
					g.drawString(""+this.yards[i].getTurns(), (87*i)+20, 375);
				}
			} else {
				g.setColor(Color.DARK_GRAY);					//fill in slots for no shipyards
				g.fillRect(87*i, 335, 83, 55);
			}	
		}
		if (this.extraYard) {									//for use with jabba, this is the fourth yard
			Image yardIMG = t.getImage(this.specialYard.getImageFile());
			g.drawImage(yardIMG,262,335,this);
			if (this.specialYard.getCurrentProject().equals("NO_PROJECT")) {
				g.setColor(Color.GREEN);
				g.setFont(smallfont);
				g.drawString("OPEN", 269, 358);
			} else {
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(tf);
				g.drawString(""+this.specialYard.getTurns(), 282, 375);
			}
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(262, 335, 83, 55);
		}
	}

	public void mouseClicked(MouseEvent e) {					//interact if applicable positions
		int but = e.getButton();
		int clicks = e.getClickCount();
		int x = e.getX();
		int y = e.getY();
		if (but == 1 && clicks == 1) {
			if ((x>0) && (x<83) && (y>335) && (y<390)) {		//click on first shipyard
				if (this.shipyards > 0) {
					projectSelect(yards[0]);
				}
			} else if ((x>87) && (x<170) && (y>335) && (y<390)) {	//second
				if (this.shipyards > 1) {
					projectSelect(yards[1]);
				}
			} else if ((x>174) && (x<257) && (y>335) && (y<390)) {	//third
				if (this.shipyards > 2) {
					projectSelect(yards[2]);
				}
			}
		}
	}
	public void mouseEntered(MouseEvent e) {

	}
	public void mouseExited(MouseEvent e) {

	}
	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
	public void mouseDragged(MouseEvent e) {
		
	}
	public void mouseMoved(MouseEvent e) {							//if cursor is over something interactable, change to hand
		int x = e.getX();
		int y = e.getY();
		if (((x>0) && (x<83) && (y>335) && (y<390) && (this.shipyards > 0)) || 
			((x>87) && (x<170) && (y>335) && (y<390) && (this.shipyards > 1)) || 
			((x>174) && (x<257) && (y>335) && (y<390) && (this.shipyards > 2))) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else {
			setCursor(Cursor.getDefaultCursor());					//else default
		}

	}
	public void projectSelect(Shipyard yard) {						//open project selector
		if (yard.hasProject()) {
			return;
		} else {
			if (this.selectorOpen) {
				this.currentSelector.close();						//close anything currently open first, no dupes
			} 
			JFrame s = new JFrame();
			s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			s.setSize(416,640);
			JPanel p = new JPanel();
			p.setBounds(0,0,400,600);
			s.setLayout(null);
			p.setLayout(null);
			p.setBackground(Color.BLACK);
			ProjectSelection ps = new ProjectSelection(this.ships, yard, this, s);
			p.add(ps);
			s.add(p);
			s.setVisible(true);
			this.currentSelector = ps;
			this.selectorOpen = true;
		}
	}
	public void addToFrame(JFrame frame) {
		this.frame = frame;											//necessary since a new one isnt created every time, changes frame reference when reopened
	}
	public void close() {
		if (this.selectorOpen) {									//close the chain, no dupes
			this.currentSelector.close();
		}
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));	//close itself, no dupes
	}
}