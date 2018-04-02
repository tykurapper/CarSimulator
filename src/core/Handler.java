package core;

public class Handler {
	
	private Simulator simulator;
	public Handler(Simulator simulator){
		this.simulator = simulator;
	}
	public Simulator getSimulator() {
		return simulator;
	}
	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
	}
	
}
