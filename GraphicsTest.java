import java.awt.*;

import javax.swing.JFrame;

public class GraphicsTest extends Canvas {
	public void paint(Graphics field) {
		Toolkit t = Toolkit.getDefaultToolkit();
		Image hoth = t.getImage("Images/Planets/Hoth.png");
		field.setColor(Color.WHITE);
		field.drawImage(hoth, 100, 100, this);
		setBackground(Color.BLACK);

	}

	//public void actionPerformed(ActionEvent e) {

	//}

	public static void main(String[] args) {
		GraphicsTest m = new GraphicsTest();
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(m);
		f.setSize(400,400);
		f.setVisible(true);
	}
}