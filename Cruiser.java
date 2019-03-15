import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Cruiser {
	private String shipClass;
	private String displayName;
	private String shipFile;
	private ArrayList<String> properties;
	private boolean isFlag;
	private int squadVal;
	private int firePower;
	private int speed;
	private int health;
	private int cost;
	private int buildTime;
	private boolean disabled;
	private Fleet fleet;
	//private Squadron[] squadList;

	public Cruiser(String shipClass, Fleet fleet) throws Exception {
		this.shipClass = shipClass;
		this.properties = new ArrayList<String>();
		this.shipFile = "Data/Cruisers/" + shipClass + ".txt";
		File f = new File(this.shipFile);
		Scanner s = new Scanner(f);
		this.displayName = s.nextLine();
		this.cost = s.nextInt();
		this.buildTime = s.nextInt();
		this.health = s.nextInt();
		this.firePower = s.nextInt();
		this.squadVal = s.nextInt();
		this.speed = s.nextInt();
		s.nextLine();
		String property1 = s.nextLine();
		String property2 = s.nextLine();
		properties.add(property1);
		properties.add(property2);
		/*this.squadList = new Squadron[squadVal];
		for (int i = 0; i < squadList.size(); i++) {
			squadList[i] = null;
		}*/
		this.disabled = false;
		this.fleet = fleet;
		this.fleet.addCruiser(this);
		if (!this.fleet.hasFlagship()) {
			this.setAsFlagship();
		}
	}
	public void setAsFlagship() {
		this.isFlag = true;
		this.fleet.setFlagship(this);
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
		this.disabled = true;
	}

	public void enable() {
		this.disabled = false;
	}

	public boolean getStatus() {
		return this.disabled;
	}

	public int getSquad() {
		return this.squadVal;
	}

	public int getFire() {
		return this.firePower;
	}

	public String getShipClass() {
		return this.shipClass;
	}

	/*public Squadron[] getSquadList() {
		return squadList;
	}*/

	/*public boolean hasSpace() {
		for (int i = 0; i < this.squadList.size(); i++) {
			if (squadList[i].equals(null)) {
				return true;
			}
		}
		return false;
	}*/

	/*public void addSquad(Squadron s) {
		for(int i = 0; i < squadList.size(); i++) {
			if (squadList[i].equals(null)) {
				squadList[i] = s;
				break;
			}
		}
	}*/
}