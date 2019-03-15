import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import java.io.*;
import java.util.Scanner;
import javax.swing.border.*;
import javax.swing.JButton;

public class Tester2 {
	public static void main(String[] args) {
		Affiliation separatist = new Affiliation("Separatist");
		Affiliation republic = new Affiliation("Republic");
		Affiliation none = new Affiliation("None");
		
		//Background bg = new Background();

		JFrame p = new JFrame();
		Planet coruscant = new Planet("Coruscant", 50, 50, republic);
		String[] testlist = {"",""};
		ProjectSelection ps = new ProjectSelection(testlist, new Shipyard(coruscant), coruscant.getPlanetData(), p);
		//JPanel p = new JPanel();
		//p.setBackground(Color.BLACK);
		p.setSize(1600,800);
		//p.setBounds(0,0,1600,800);
		//p.setInsets(new Insets(0,0,0,0));
		//f.setLayout(null);
		p.setLayout(null);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.add(coruscant);
		//f.add(p);
		//f.pack();
		p.setVisible(true);
	}
}