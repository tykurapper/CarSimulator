package core;

import display.Display;

public class Launcher {
	public static void main(String[] args) {
		
		Simulator simulator = new Simulator("CarSimulator", 680, 360);
		simulator.start();
	}
}
