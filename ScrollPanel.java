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
// This is an extension of JPanel with vertical scrolling capabilities,
// created to display the map for the main gamestate.  Planets get added
// to this object, but the routes are displayed as part of the panel.
//
//
//
//
//
//
public class ScrollPanel extends JPanel implements MouseListener, MouseMotionListener {
	private int yScroll;													//the vertical coord of the top left corner; is always <=0
	private int yBase;														//represents panel location prior to scrolling maneuver
	private int yScreen;													//when the user scrolls, this is the position of the intitial button press
	private int dist;														//distance between current location and intial press location
	private int yLast;														//last calculated location; used for smoothing a bit
	private int height;														//total height of panel, NOT the area displayed
	private int width;														//width of the panel is constant
	private int maxH;														//display height
	private int[][] routeList;												//route coordinate data
	private boolean scrollOn;												//whether user is scrolling
	private String name;													//panel name; might remove this from the code
	private String imageFile;												//background image for panel

	public ScrollPanel(int w, int h, int mh, String name, int[][] rList) {
		this.yScroll = 0;													//set initial display values
		this.yBase = 0;
		this.scrollOn = false;												//intially, user isn't scrolling
		this.height = h;
		this.width = w;
		this.maxH = mh;
		this.name = name;
		this.routeList = rList;
		this.imageFile = "Images/Field/" + name + ".png";					//find background image file, may hardcode this at some point
		super.addMouseListener(this);										//add mouse listeners for clicks and motion
		super.addMouseMotionListener(this);
		this.setBounds(0,0,this.width,this.height);							//set position relative to frame
	}

	public void paintComponent(Graphics g) {								//draw the panel, this will most likely not be called again
		Toolkit t = Toolkit.getDefaultToolkit();							//get image file and draw
		Image i = t.getImage(this.imageFile);
		g.drawImage(i,0,0,this);
		g.setColor(Color.CYAN);												//draw routes in cyan
		showRoutes(g, this.routeList);										//draws routes, see below
	}

	public void mouseClicked(MouseEvent e) {								//used for checking positions on map when i was rearranging planets
		//System.out.println("(" + e.getX() + ", " + e.getY() + ")");
	}
	public void mouseEntered(MouseEvent e) {								//change to move-style cursor when user is hovering
		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

	}
	public void mouseExited(MouseEvent e) {									//return to default cursor when not
		setCursor(Cursor.getDefaultCursor());
		
	}
	public void mousePressed(MouseEvent e) {								//set intitial click position for scroll
		this.yScreen = e.getY();
		this.yLast = e.getY();
	}
	public void mouseReleased(MouseEvent e) {								//reset the yBase temp variable
		this.yBase = this.yScroll;
	}
	public void mouseDragged(MouseEvent e) {								//scroll computations
		int y = e.getY();													//read current position whenever mouse is moved while pressing
		if (Math.abs(y - this.yLast) >= 3) {								//this helps prevent mouse spasming
			this.dist = y - yScreen;										//set current drag distance
			int temp = this.yBase + this.dist;								//store theoretical current location to temp variable
			if (temp < 0 && temp > (-(this.height-this.maxH))) {			//map will not be changed once bounds are reached
				this.yScroll = temp;										//change panel location
				setBounds(0,this.yScroll,this.width,this.height);			
			}
		}
		this.yLast = y;														//reset the smoothing variable after every iteration

	}
	public void mouseMoved(MouseEvent e) {

	}
	public void showRoutes(Graphics g, int[][] rList) {						//plot every route onto the map as thicklines
		for (int i = 0; i < rList.length; i++) {
			drawThickLine(g,rList[i][0],rList[i][1],rList[i][2],rList[i][3],5);
		}
	}
	public void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int thickness) {  //i actually stole this code from the internet bc too lazy to make myself
		int dX = x2 - x1;																	//basically, draws polygon so line has thickness
		int dY = y2 - y1;
		double lineLength = Math.sqrt(dX * dX + dY * dY);
		double scale = (double)(thickness) / (2 * lineLength);
		double ddx = -scale * (double)dY;
		double ddy = scale * (double)dX;
		ddx += (ddx > 0) ? 0.5 : -0.5;
		ddy += (ddy > 0) ? 0.5 : -0.5;
		int dx = (int)ddx;
		int dy = (int)ddy;
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];

		xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
		xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
		xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
		xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

		g.fillPolygon(xPoints, yPoints, 4);
	}

}