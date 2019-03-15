import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import java.io.*;
import java.util.Scanner;
import javax.swing.border.*;
import javax.swing.JButton;
//
// This is a class for testing everything to see if it works.
// For now, it constitutes the main runnable file for the game.
// Once things get developed, a GameState class will eventually
// get made, which will effectively take all the shit in here
// and organize it a bit better.
//
//
//
//
public class Tester {
	public static void main(String[] args) throws Exception {
		Affiliation separatist = new Affiliation("Separatist");						//intialize every planet; this will be moved to init() method later
		Affiliation republic = new Affiliation("Republic");
		Affiliation none = new Affiliation("None");
		Planet scipio = new Planet("Scipio", 190, 160, none);
		Planet catoNeimodia = new Planet("Cato_Neimodia", 680, 900, separatist);
		Planet coruscant = new Planet("Coruscant", 140, 800, republic);
		Planet fresia = new Planet("Fresia", 140, 1200, republic);
		Planet alderaan = new Planet("Alderaan",480,640,none);
		Planet corellia = new Planet("Corellia",350,930,republic);
		Planet malastare = new Planet("Malastare",440,1230,none);
		Planet eriadu = new Planet("Eriadu",140,1500,none);
		Planet naboo = new Planet("Naboo",840,1500,none);
		Planet mandalore = new Planet("Mandalore",710,200,none);
		Planet roche = new Planet("Roche",730,440,none);
		Planet kuat = new Planet("Kuat",770,1130,republic);
		Planet florrum = new Planet("Florrum",1000,360,none);
		Planet ringoVinda = new Planet("Ringo_Vinda",1030,560,none);
		Planet umbara = new Planet("Umbara",1120,800,none);
		Planet zygerria = new Planet("Zygerria",1120,100,none);
		Planet felucia = new Planet("Felucia",1290,280,none);
		Planet saleucami = new Planet("Saleucami",1400,560,none);
		Planet lianna = new Planet("Lianna",1510,150,separatist);
		Planet raxus = new Planet("Raxus",1820,100,separatist);
		Planet monCalamari = new Planet("Mon_Calamari",1680,400,none);
		Planet nimban = new Planet("Nimban",1660,760,separatist);
		Planet nalHutta = new Planet("Nal_Hutta",1490,950,none);
		Planet kamino = new Planet("Kamino",1300,1100,republic);
		Planet christophsis = new Planet("Christophsis",1360,1350,none);
		Planet geonosis = new Planet("Geonosis",1680,1500,separatist);
		Planet brentaal = new Planet("Brentaal",280,420,none);
		int planetLength = 27;														//number of planets
		Planet[] planetList = new Planet[planetLength];								//this makes an array with all planets
		for (int i = 0; i < republic.getLength(); i++) {
			planetList[i] = republic.getPlanet(i);
		}
		for (int i = 0; i < separatist.getLength(); i++) {
			planetList[i+republic.getLength()] = separatist.getPlanet(i);
		}
		for (int i = 0; i < none.getLength(); i++) {
			planetList[i+republic.getLength()+separatist.getLength()] = none.getPlanet(i);
		}
		File file = new File("Data/Routes/Routes.txt");								//obtain route data for every route, and store endpoints for each
		Scanner s = new Scanner(file);
		int routeSize = s.nextInt();
		s.nextLine();
		int[][] routes = new int[routeSize][4];
		for (int i = 0; i < routeSize; i++) {
			String s1;
			String s2;
			s1 = s.next();
			s2 = s.next();
			int x1 = 0;
			int x2 = 0;
			int y1 = 0;
			int y2 = 0;
			for (int j = 0; j < planetList.length; j++) {
				if (planetList[j].getName().equals(s1)) {
					x1 = planetList[j].getXCoord();
					y1 = planetList[j].getYCoord();
					break;
				}
			}
			for (int j = 0; j < planetList.length; j++) {
				if (planetList[j].getName().equals(s2)) {
					x2 = planetList[j].getXCoord();
					y2 = planetList[j].getYCoord();
					break;
				}
			}
			routes[i][0] = x1;														//store route endpoints in form {x1,y1,x2,y2}
			routes[i][1] = y1;
			routes[i][2] = x2;
			routes[i][3] = y2;
		}

		JFrame f = new JFrame();													//create frame to put main gamestate in
		ScrollPanel p = new ScrollPanel(1920,1600,1020,"Background",routes);		//create a scrollpanel, which is a custom jpanel extension for the map
		p.setBackground(Color.BLACK);
		f.setSize(1920,1020);														//until i find a better way to do this, this is the only size that will work
		f.setLayout(null);															//shitty method, for testing for now
		p.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);							//exit game if this frame is closed
		f.add(p);																	//put map scrollpanel in frame
		p.add(coruscant);															//add all planets to panel
		p.add(scipio);
		p.add(catoNeimodia);
		p.add(fresia);
		p.add(alderaan);
		p.add(corellia);
		p.add(malastare);
		p.add(eriadu);
		p.add(mandalore);
		p.add(roche);
		p.add(kuat);
		p.add(naboo);
		p.add(umbara);
		p.add(ringoVinda);
		p.add(florrum);
		p.add(zygerria);
		p.add(felucia);
		p.add(lianna);
		p.add(raxus);
		p.add(monCalamari);
		p.add(saleucami);
		p.add(nimban);
		p.add(nalHutta);
		p.add(kamino);
		p.add(christophsis);
		p.add(geonosis);
		p.add(brentaal);
		
		f.setVisible(true);															//make frame visible
	}
}