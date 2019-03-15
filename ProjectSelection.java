import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;
import java.lang.Math;
//
// This is a project selection window that is created
// when the user clicks on an empty yard in the Planetdata
// screen.  It lets the user select an eligible project
// to be placed in the yard.  Note that this is an object that
// is placed in a panel.  The object is what is repositioned when
// the user scrolls, but it effectively works the same way as the
// ScrollPanel object.
//
//
public class ProjectSelection extends JComponent implements MouseListener, MouseMotionListener {
	private String[] shipList;											//list of projects the yard can build
	private PlanetData pD;												//planetdata object that created this
	private Shipyard yard;												//yard we are selecting for
	private JFrame frame;												//frame this is displayed on
	private int yScroll;												//this is for scrolling, see ScrollPanel for explanation
	private int yBase;													//	|
	private int yScreen;												//	|
	private int dist;													//	|
	private int yLast;													//	|
	private int height;													//	V
	private int[] positions;											//list of the center of each selection pane; currently uncertain if needed
	private boolean scrollOn;											//is user scrolling
	
	public ProjectSelection(String[] shipList, Shipyard yard, PlanetData pD, JFrame frame) {
		this.frame = frame;
		this.shipList = shipList;
		this.yard = yard;
		this.pD = pD;
		this.yScroll = 0;												//set initial positions
		this.yBase = 0;
		this.scrollOn = false;
		this.height = this.shipList.length*200;							//each pane is 200 pixels tall
		this.positions = new int[this.shipList.length];					//init positions list
		for (int i = 0; i < this.shipList.length; i++) {
			positions[i] = (100 + (i*200));
		}
		super.addMouseListener(this);									//add listeners
		super.addMouseMotionListener(this);
		this.setSize(400, this.height);									//could also be accomplished with setBounds()
	}

	public void paint(Graphics g) {										//not yet complete, draws the entire selection screen
		Toolkit t = Toolkit.getDefaultToolkit();
		Image tablet = t.getImage("Images/Shipyards/ProjectBase.png");	//pane template
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i< this.shipList.length; i++) {					//draw the borders and lines
			g.drawImage(tablet,0,i*200,this);
			g.fillRect(0,i*200,400,5);
			g.fillRect(0,(i*200)+195,400,5);
			g.fillRect(0,(i*200)+5,5,195);
			g.fillRect(395,(i*200)+5,5,195);
			if (getProjectType(shipList[i]).equals("Cruiser")) {		//class-specific lines n shit
				g.fillRect(195,i*200,5,200);
			}
		}
		setBounds(0, this.yScroll, 400, this.height);					//set initial position of the selection screen
		
	}

	public void mouseClicked(MouseEvent e) {							//not yet implemented; this is the actual selection part
		int x = e.getX();
		int y = e.getY();
		
	}
	public void mouseEntered(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	public void mouseExited(MouseEvent e) {
		setCursor(Cursor.getDefaultCursor());
	}
	public void mousePressed(MouseEvent e) {							//most of the listeners are for scrolling, see ScrollPanel for detailed explanation
		int but = e.getButton();
		this.yScreen = e.getY();
		this.yLast = e.getY();
	}
	public void mouseReleased(MouseEvent e) {
		this.yBase = this.yScroll;
	}
	public void mouseDragged(MouseEvent e) {
		int y = e.getY();
		if (Math.abs(y - this.yLast) >= 3) {
			this.dist = 1*(y - yScreen);
			int temp = this.yBase + this.dist;
			if (temp < 0 && temp > (-(height-600))) {
				this.yScroll = temp;
			setBounds(0,this.yScroll,400,this.height);
			}
		}
		this.yLast = y;

	}
	public void mouseMoved(MouseEvent e) {

	}
	public String getProjectType(String project) {						//this is kinda a gay way to do this, returns project type
		String projectFile = "Data/Cruisers/" + project + ".txt";		//this works bc of how the data is sorted
		File f = new File(projectFile);									//if the data file for the ship is in the cruisers directory, it's a cruiser
		if (f.exists()) {
			return "Cruiser";
		} else {
			projectFile = "Data/Squadrons/" + project + ".txt";			//otherwise its probably a squadron
			f = new File(projectFile);
			if (f.exists()) {
				return "Squadron";
			} else {
				return "Not Found";										//or maybe its a problem child
			}
		}
	}

	public void close() {												//every window has a force close function to prevent dupe windows
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));	
	}
}