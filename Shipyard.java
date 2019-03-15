//
// This is a fairly important support class that connects
// planets to the ships they make.  It doesn't have any graphical
// aspect (yet, that may change (?)).  It basically just has info
// on what's being built there, if anything, and the picture that
// gets displayed on the PlanetData screen.
//
//
//
//
public class Shipyard {
	private boolean full;					//are we building something here???
	private int turns;						//turns left until project completion
	private String project;					//name of what is being built
	private Planet planet;					//planet the yard belongs to
	private String imageFile;				//file directory for picture display

	public Shipyard(Planet planet) {
		this.full = false;					//initially not full
		this.turns = -1;					//-1 is a value that means nothing is there
		this.project = "NO_PROJECT";		//initially nothing
		this.planet = planet;				//init home planet
		this.imageFile = "Images/Shipyards/" + this.project + ".png";
	}
	public boolean hasProject() {			//check if yard has project
		return this.full;
	}
	public void setProject(String project, int turns) {
		this.project = project;				//set a project to the yard
		this.turns = turns;
		this.full = true;
		this.imageFile = "Images/Shipyards/" + this.project + ".png";
	}
	public String getCurrentProject() {
		return this.project;
	}
	public int getTurns() {
		return this.turns;
	}
	public String completeProject() {		//this isn't complete yet, procedure for when project is completed
		String temp = this.project;
		this.turns = -1;
		this.project = "NO_PROJECT";
		this.full = false;
		this.imageFile = "Images/Shipyards/" + this.project + ".png";
		return temp;
	}
	public Planet getHome() {
		return this.planet;
	}
	public String getImageFile() {
		return this.imageFile;
	}
}