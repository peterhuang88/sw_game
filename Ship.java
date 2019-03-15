import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Cruiser {
	private String shipClass;
	private String cardFile;
	private int squadVal;
	private int firePower;
	private int speed;
	private int size;
	private int health;
	private double x;
	private double y;
	private double angle;
	private boolean hyperdrive;
	private Squadron[] squadList;

	public Cruiser(String shipClass, double x, double y, double angle) {
		this.shipClass = shipClass;
		this.cardFile = shipClass + ".txt";
		File f = new File(cardFile);
		Scanner s = new Scanner(f);
		this.squadVal = s.nextInt();
		this.firePower = s.nextInt();
		this.speed = s.nextInt();
		this.size = s.nextInt();
		this.health = s.nextInt();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.squadList = new Squadron[squadVal];
		for (int i = 0; i < squadList.size(); i++) {
			squadList[i] = null;
		}
		this.hyperdrive = true;
	}

	public void setSpeed(int s) {
		this.speed = s;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setHealth(int h) {
		this.health = h;
	}

	public int getHealth() {
		return this.health;
	}

	public void disable() {
		this.hyperdrive = false;
	}

	public void enable() {
		this.hyperdrive = true;
	}

	public boolean getStatus() {
		return this.hyperdrive;
	}

	public int getSquad() {
		return this.squadVal;
	}

	public int getFire() {
		return this.firePower;
	}

	public int getSize() {
		return this.size;
	}

	public String getClass() {
		return this.shipClass;
	}

	public double[] getCoords() {
		double[] coords = new double[3];
		coords = {this.x, this.y, this.angle};
		return coords;
	}

	public void setCoords(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	public Squadron[] getSquadList() {
		return squadList;
	}

	public boolean hasSpace() {
		for (int i = 0; i < this.squadList.size(); i++) {
			if (squadList[i].equals(null)) {
				return true;
			}
		}
		return false;
	}

	public void addSquad(Squadron s) {
		for(int i = 0; i < squadList.size(); i++) {
			if (squadList[i].equals(null)) {
				squadList[i] = s.clone();
				break;
			}
		}
	}


}