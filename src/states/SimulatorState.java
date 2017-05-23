package states;

import java.awt.Color;
import java.awt.Graphics;

import entities.machines.Car;
import path.Line;

public class SimulatorState extends State{

	private Car car;
	private Line lineA = new Line(-40, 0, 320, 360);
	private Line lineB = new Line(40, 0, 400, 360);
	private Line lineC = new Line(200, 160, 680, 160);
	private Line lineD = new Line(200, 240, 680, 240);
	public Line getLineA() {
		return lineA;
	}
	public Line getLineB() {
		return lineB;
	}
	public SimulatorState(){
		car = new Car(0, 0);
	}
	public void tick() {
		
		//System.out.println(car.distanceFrom(lineA));
		car.tick(lineA, lineB, lineC, lineD);
		
	}

	@Override
	public void render(Graphics g) {
		
		lineA.draw(g, Color.BLUE);
		lineB.draw(g, Color.RED);
		lineC.draw(g, Color.RED);
		lineD.draw(g, Color.BLUE);
		car.render(g);
		
	}
	
}
