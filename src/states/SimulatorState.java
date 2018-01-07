package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import path.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import entities.machines.ArcTurningPoint;
import entities.machines.Car;
import entities.machines.StraightTurningPoint;
import entities.machines.TurningPoint;
import myMath.Cubic;
import path.Line;

public class SimulatorState extends State{

	private Car car;
	private Queue<TurningPoint> stack = new LinkedList<TurningPoint>();

	public SimulatorState(){
//		case 1
		car = new Car(0, 20);
		stack.add(new ArcTurningPoint(new Point(180, 100), TurningPoint.DEFAULT_RADIUS, new QuadCurve2D.Float(0, 0, 200, 0, 200, 100), new QuadCurve2D.Float(0, 40, 160, 40, 160, 100)));
		stack.add(new StraightTurningPoint(new Point(180, 200), TurningPoint.DEFAULT_RADIUS, new Line(200, 100, 200, 200), new Line(160, 100, 160, 200)));
		stack.add(new ArcTurningPoint(new Point(300, 280), TurningPoint.DEFAULT_RADIUS, new QuadCurve2D.Float(200, 200, 200, 260, 300, 260), new QuadCurve2D.Float(160, 200, 160, 300, 300, 300)));
//		case 2
//		stack.add(new StraightTurningPoint(new Point(300, 40), TurningPoint.DEFAULT_RADIUS, new Line(40, 20, 300, 20), new Line(40, 60, 300, 60)));
//		stack.add(new StraightTurningPoint(new Point(600, 40), TurningPoint.DEFAULT_RADIUS, new Line(300, 20, 600, 20), new Line(300, 60, 600, 60)));
//		stack.add(new StraightTurningPoint(new Point(600, 400), TurningPoint.DEFAULT_RADIUS, new Line(600, 20, 500, 400), new Line(540, 20, 440, 400)));
	}
	public void tick() {
		
		//System.out.println(car.distanceFrom(lineA));
		car.tick(stack);
		
	}

	@Override
	public void render(Graphics g) {
		
		//stack.peek().render(g);
		car.render(g);
	}	
}
