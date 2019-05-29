package model;

public class Applicazione {

	public static void main(String[] args) {
	
		Simulator sim = new Simulator();
		
		sim.init();
		sim.run();
		sim.stat();
	}

}
