import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class Background extends Canvas {
	
	public Background() {
		this.setSize(1600,900);
	}

	@Override
	public void paint(Graphics g) {
		Toolkit t = Toolkit.getDefaultToolkit();
		Image bg = t.getImage("Images/Templates/Background.png");
		g.drawImage(bg, 0,0,this);
		setBounds(0,0,1600,900);
	}
}