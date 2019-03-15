import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;
import java.lang.String;
import java.util.ArrayList;
//
// Fleets are glorified lists of cruisers.  Allows user to interact with
// their ships in an orderly manner.  These are what will show up on the
// gameboard. Battle is waged between fleets.
//
//
//
//
//
//
//
public class Fleet extends JComponent implements MouseListener, MouseMotionListener {
	private int xPos;												//position of fleet
	private int yPos;
	private int speed;												//fleet's speed rating
	private int size;												//size (1-3)
	private String state;											//Orbit, Transit, or Staging
	private Affiliation affil;										//fleet's affiliation
	private boolean hasAdmiral;										//if the fleet has an admiral
	private Planet currentLoc;										//current or last location of fleet
	private Planet destination;										//destination planet (fur use during transit step)
	private boolean hasFlag;										//if the fleet has a flagship (all fleets MUST have a flagship)
	//private Commander admiral;									//not yet implemented
	private Cruiser flagship;										//flagship of the fleet
	private ArrayList<Cruiser> cruisers;							//list of all cruisers in fleet
	public Fleet(String state, Planet planet, Affiliation affil) {
		this.state = state;											//set initial state
		this.currentLoc = planet;									//set initial location
		this.hasAdmiral = false;									//initially no admiral
		this.affil = affil;											//set affiliation
		this.speed = 5;												//initially set to max speed
		if (this.state.equals("Orbit")) {							//orbit coords are to bottom left of planet
			this.xPos = this.currentLoc.getXCoord() - 52;
			this.yPos = this.currentLoc.getYCoord() + 52;
		} else if (this.state.equals("Staging")) {					//staging coords are center of planet, but shouldn't matter
			this.xPos = this.currentLoc.getXCoord();				//since fleet won't appear on screen during staging
			this.yPos = this.currentLoc.getYCoord();
		}
		this.size = 0;												//initially set to size zero
		this.hasFlag = false;										//initially no flagships since no ships
		this.affil.addFleet(this);									//make sure we add the fleet to the affiliation list
		this.cruisers = new ArrayList<Cruiser>();					//initialize the cruiser list
	}

	public boolean hasFlagship() {									//true if flagship exists, should almost always be true
		return this.hasFlag;
	}

	public void setFlagship(Cruiser c) {							//set flagship
		this.flagship = c;
	}

	public void addCruiser(Cruiser c) {								//add cruiser to fleet
		this.cruisers.add(c);
		this.updateSize();
	}
	public void updateSize() {								
		int temp = this.cruisers.size();
		if (temp > 0 && temp <= 3) {								//1 if 3 ships or less
			this.size = 1;
		} else if (temp <= 5) {										//2 if 4-5 ships
			this.size = 2;
		} else if (temp > 5) {										//3 if more than 5
			this.size = 3;
		}

	}

	public void mouseClicked(MouseEvent e) {						//these will get implemented eventually
		
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
	public void mouseMoved(MouseEvent e) {

	}

}