import java.util.ArrayList;
//
// This is an object that stores all the global values
// for an affiliation, or a player's "side".  Notably,
// once finished, it will contain a player's current
// resource value, references to all their planets,
// references to all their fleets, and a list of every
// ship they can build.  the ship list is used if the player
// controls Jabba's special yard (not yet implemented);
//
//
public class Affiliation {
	private String name;								//affiliation name
	private int resource;								//total resource value
	private ArrayList<Fleet> fleets;					//all the player's fleets
	//private boolean hasCommander;						//whether the player has a commander (not yet implemented)
	//private String Commander;							//uncertain how this will look at the end
	private ArrayList<String> projects;					//complete valid project list (HAS DUPES)
	private ArrayList<Planet> planets;					//planet list

	public Affiliation(String name) {
		this.name = name;								//name is parameter
		this.resource = 0;								//initially, no resources, planets, or fleets
		this.projects = new ArrayList<String>();		
		this.planets = new ArrayList<Planet>();
		this.fleets = new ArrayList<Fleet>();
	}

	public void addPlanet(Planet planet) {				//add planet
		String[] ships = planet.getShips();				//add planet's project list t master project list

		for (int i = 0; i < planet.getShipSize(); i++) {
			//int projectLength = this.projects.size();
			String project = ships[i];
			this.projects.add(project);
			/*boolean hasShip = false;							//these few lines were used to ensure no dupes, but 
			for (int j = 0; j < projectLength; j++) {			//i decided having dupes is better.  This might change eventually though
				if (this.projects.get(j).equals(project)) {
					hasShip = true;
				}
			}
			if (!hasShip) {
				projects.add(project);
			}*/
		}
		this.resource += planet.getResource();					//add planet's resource value to affiliation resource value
		this.planets.add(planet);								//add ref to planet object to list
	}

	public void removePlanet(Planet planet) {					//remove planet from affiliation
		String[] ships = planet.getShips();						//remove project list from affiliation project list

		for (int i = 0; i < planet.getShipSize(); i++) {		
			String project = ships[i];
			this.projects.remove(project);
		}
		this.resource -= planet.getResource();					//remove resource val
		this.planets.remove(planet);							//remove planet
	}

	public void addFleet(Fleet fleet) {							//not yet completed
		this.fleets.add(fleet);
	}

	public String getName() {									//get affil name, likely will never be used bc of naming convention
		return this.name;
	}

	public void printData() {									//prints data to terminal for debugging
		System.out.println(this.name + ":");
		System.out.println("Resource Total: " + this.resource);
		System.out.println("\nPlanets:");
		for (int i = 0; i < this.planets.size(); i++) {
			System.out.println(this.planets.get(i).getName());
		}
		System.out.println("\nProjects:");
		for (int i = 0; i < this.projects.size(); i++) {
			System.out.println(this.projects.get(i));
		}
		System.out.println("");
	}

	public int getLength() {									//how many planets are in the affiliation
		return this.planets.size();
	}

	public Planet getPlanet(int i) {							//return reference to 'i'th planet
		return this.planets.get(i);
	}

}