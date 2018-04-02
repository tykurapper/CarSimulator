package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import path.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import core.Handler;
import direction.Direction;
import entities.machines.ArcTurningPoint;
import entities.machines.Car;
import entities.machines.Obstacle;
import entities.machines.StraightTurningPoint;
import entities.machines.TurningPoint;
import myMap.MyMap;
import myMath.Cubic;
import path.Line;

public class SimulatorState extends State implements MouseListener{

	private Car car;
	private Queue<TurningPoint> stack = new LinkedList<TurningPoint>();
	private MyMap map = new MyMap();
	private Handler handler;
	private boolean hadMouseListener = false;
	private Point MousePosition;
	private Obstacle obstacle;
	//private boolean hasObstacle = false;

	public SimulatorState(){
//		case 1
		map.addPoint(new Point(0, 20));
		map.addPoint(new Point(180, 100));
		map.addPoint(new Point(180, 200));
		map.addPoint(new Point(300, 280));
		map.getPoint(0).setTrafficLight(true);
		map.getPoint(1).setTrafficLight(true);
		map.getPoint(2).setTrafficLight(true);
		map.getPoint(3).setTrafficLight(true);
		map.add(new ArcTurningPoint(map.getPoint(0), map.getPoint(1), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new QuadCurve2D.Float(0, 0, 200, 0, 200, 100), new QuadCurve2D.Float(0, 40, 160, 40, 160, 100)));
		map.add(new StraightTurningPoint(map.getPoint(1), map.getPoint(2), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(200, 100, 200, 200), new Line(160, 100, 160, 200)));
		map.add(new ArcTurningPoint(map.getPoint(2), map.getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new QuadCurve2D.Float(200, 200, 200, 260, 300, 260), new QuadCurve2D.Float(160, 200, 160, 300, 300, 300)));
//		if(handler != null)
//			handler.getSimulator().getDisplay().getCanvas().addMouseListener(this);
		
//		case 2
//		map.addPoint(new Point(0, 40));
//		map.addPoint(new Point(300, 40));
//		map.addPoint(new Point(570, 40));
//		map.addPoint(new Point(400, 400));
//		map.add(new StraightTurningPoint(map.getPoint(0), map.getPoint(1), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(40, 20, 300, 20), new Line(40, 60, 300, 60)));
//		map.add(new StraightTurningPoint(map.getPoint(1), map.getPoint(2), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(300, 20, 600, 20), new Line(300, 60, 600, 60)));
//		map.add(new StraightTurningPoint(map.getPoint(2), map.getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(600, 20, 500, 400), new Line(540, 20, 440, 400)));
		
		stack.add(map.get(0));
		stack.add(map.get(1));
		stack.add(map.get(2));
		car = new Car(stack.peek().getStart(), stack.peek().getDirection());
	}
	public void tick() {
		//System.out.println(car.distanceFrom(lineA));
		car.tick(stack, obstacle);
		
	}

	@Override
	public void render(Handler handler, Graphics g) {
		map.render(g);
		if(handler == null)
			setHandler(handler);
		//stack.peek().render(g);
		MousePosition = new Point(MouseInfo.getPointerInfo().getLocation().x - handler.getSimulator().getDisplay().getCanvas().getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().getY() - handler.getSimulator().getDisplay().getCanvas().getLocationOnScreen().y);
		MousePosition.render(g);
		
		if(!hadMouseListener){
			handler.getSimulator().getDisplay().getCanvas().addMouseListener(this);
			hadMouseListener = true;
		}
			
//		i++;
//		addMouse();
		if(hasObstacle())
			obstacle.render(g);
		car.setHandler(handler);
		car.render(g);
		
	}
//	private void addMouse(){
//		if (handler.getSimulator().getDisplay().getCanvas().getMouseListeners() == null)
//			handler.getSimulator().getDisplay().getCanvas().addMouseListener(this);
//	}
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		clickObstacle((float) MousePosition.getX(), (float) MousePosition.getY());
//		System.out.println("(" + MousePosition.getX() + " " + MousePosition.getY() + ")");
	}
	private boolean hasObstacle(){
		if(obstacle == null)
			return false;
		else return true;
	}
	private void clickObstacle(float x, float y){
		if(!hasObstacle())
			obstacle = new Obstacle(x, y);
		else obstacle = null;
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}
