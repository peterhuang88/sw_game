import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;
import java.lang.String;
//
// Planet objects have a shitton of properties, and are
// shown in the main gamestate frame on the map.  The
// coordinate system is fucking cancer, so ask me directly
// for an explanation if you need it.  Basically, the planet
// object is a square 104x104 centered around its coordinates,
// but JComponents' display coordinates are the top left corner.
//
//
//
public class Planet extends JComponent implements MouseListener, MouseMotionListener {
	private String name;				//planet name
	private Affiliation affiliation;	//planet affiliation
	private String cardFile;			//data file
	private String imgFile;				//planet image file (100x100 png)
	private int resource;				//current resource val
	private int fuel;					//current fuel val
	private int stage;					//current stage val
	private int shipyards;				//number of shipyards
	private int shipSize;				//number of projects available
	private int oResource;				//original resource val
	private int oFuel;					//original fuel val
	private int oStage;					//original staging val
	private String[] ships;				//list of projects
	private Shipyard[] yards;			//list of yards, initially all "NONE"
	private int xd;						//x coord of planet
	private int yd;						//y coord of planet
	//private int dim;					//dimension of planet
	//private int radius;				//radius of planet
	private boolean selected;			//whether cursor is over planet
	private boolean dataOpen;			//whether data screen is open
	private PlanetData planetData;		//reference to the data object
	private boolean extraYard;			//only true if jabba
	private Shipyard specialYard;		//reference to the special yard if jabba

	public void getData() throws FileNotFoundException {						
		File f = new File(cardFile);							//read data file and get planet info
		Scanner s = new Scanner(f);
		this.oResource = s.nextInt();
		this.oFuel = s.nextInt();
		this.oStage = s.nextInt();
		this.shipyards = s.nextInt();
		this.resource = this.oResource;							//current stats = initial stats
		this.fuel = this.oFuel;
		this.stage = this.oStage;
		this.shipSize = s.nextInt();
		this.ships = new String[shipSize];
		s.nextLine();
		for (int i = 0; i < shipSize; i++) {
			ships[i] = s.nextLine();
		}
	}

	public Planet(String name, int xd, int yd, Affiliation affiliation) {
		this.xd = xd;											//set positions
		this.yd = yd;
		//this.dim = 100;
		//this.radius = 50;
		this.name = name;
		this.dataOpen = false;									//data has never been opened
		this.extraYard = false;									//no jabba yet
		this.specialYard = new Shipyard(this);					//this isn't right, will fix once jabba is implemented (VERY VERY VVVV LONG TIME)
		this.affiliation = affiliation;							//reference to affiliation
		this.cardFile = "Data/Planets/" + name + ".txt";		//data file
		this.imgFile = "Images/Planets/" + name + ".png";		//image file
		this.selected = false;									//not selected
		try {													//get data
			getData();
		} catch(FileNotFoundException e) {
			System.out.println("File not found during compile.");
		}
		this.yards = new Shipyard[this.shipyards];
		for (int i = 0; i < shipyards; i++) {
			this.yards[i] = new Shipyard(this);										
		}
		this.affiliation.addPlanet(this);						//add planet to affiliation list
		this.planetData = new PlanetData(this);					//create data object
		super.addMouseListener(this);							//add listeners
		super.addMouseMotionListener(this);
		this.setSize(100 + 4, 100 + 4);							//planet size
	}
	public void paint(Graphics g) {
		Toolkit t = Toolkit.getDefaultToolkit();
		Image i = t.getImage(imgFile);
		if (this.affiliation.getName().equals("Separatist")) {			//set color according to affiliation
			g.setColor(Color.BLUE);
		} else if (this.affiliation.getName().equals("Republic")) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.LIGHT_GRAY);
		}
		if (!this.selected) {											//draw planet and show highlights if selected
			g.drawImage(i, 2,2, this);
		} else if(this.selected) {
			g.fillOval(0,0,100+4,100+4);
			g.drawImage(i, 2,2, this);
		}
		if (this.affiliation.getName().equals("Separatist")) {			//set color and write a letter to indicate affiliation
			g.setFont(new Font("Rockwell", Font.BOLD, 40));
			g.drawString("CIS",15, 70);
		} else if (this.affiliation.getName().equals("Republic")) {
			g.setFont(new Font("Rockwell", Font.BOLD, 50));
			g.drawString("R",35, 70);
		}
		setBounds(this.xd - 52, this.yd - 52,100+4,100+4);				//set interactable bounds
	}

	public void printData() {											//for debugging; prints planet data to console
		System.out.println(this.name);
		System.out.println(this.affiliation.getName());
		System.out.println("Resource Value: " + this.resource);
		System.out.println("Fuel Value: " + this.fuel);
		System.out.println("Staging Value: " + this.stage);
		System.out.println("Shipyards: " + this.shipyards);
		System.out.println("\nShips:");
		for (int i = 0; i < shipSize; i++) {
			System.out.println(ships[i]);
		}
		
	}

	public void openData() {											//open PlanetData window
		if (this.dataOpen) {
			this.planetData.close();
		} 
		JFrame p = new JFrame();
		p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.planetData.addToFrame(p);
		p.add(this.planetData);
		p.setSize(850,500);
		p.setVisible(true);
		this.dataOpen = true;
	}

	public void changeAffil(Affiliation a) {
		this.affiliation.removePlanet(this);
		a.addPlanet(this);
		this.affiliation = a;
	}
	@Override
	public void mouseClicked(MouseEvent e) {							//when left-clicked, openData
		int but = e.getButton();
		int clicks = e.getClickCount();
		if (clicks == 1 && but == 1) {
			openData();
		}
		
	}
	public void mouseEntered(MouseEvent e) {							//repaint with highlight when selected
		this.selected = true;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		repaint();

	}
	public void mouseExited(MouseEvent e) {								//repaint w/out highlight when not
		this.selected = false;
		setCursor(Cursor.getDefaultCursor());
		repaint();

	}
	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
	public void mouseDragged(MouseEvent e) {
		
	}
	public void mouseMoved(MouseEvent e) {

	}
	public String getName() {											//these all return current values
		return this.name;
	}
	public Affiliation getAffil() {										//reference to affiliation
		return this.affiliation;
	}
	public int getResource() {
		return this.resource;
	}
	public int getFuel() {
		return this.fuel;
	}
	public int getStaging() {
		return this.stage;
	}
	public int getShipyards() {											//return number of yards
		return this.shipyards;
	}
	public int getShipSize() {											//return number of different projects available
		return this.shipSize;
	}
	public String[] getShips() {										//reference to project list
		return this.ships;
	}
	public int getXCoord() {
		return this.xd;
	}
	public int getYCoord() {
		return this.yd;
	}
	/*public int getDim() {
		return this.dim;
	}*/
	public int getOResource() {											//original values
		return this.oResource;
	}
	public int getOFuel() {
		return this.oFuel;
	}
	public int getOStaging() {
		return this.oStage;
	}
	public Shipyard[] getYards() {										//reference to yard list
		return this.yards;
	}
	public void setProject(Shipyard yard, String project, int turns) {			//set project to yard
		yard.setProject(project, turns);
	}
	public PlanetData getPlanetData() {
		return this.planetData;
	}

	public boolean hasExtraYard() {
		return this.extraYard;
	}
	public Shipyard getExtraYard() {
		return this.specialYard;
	}
}