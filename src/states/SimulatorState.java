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

public class SimulatorState extends State{

	private Car car;
	private Queue<TurningPoint> stack = new LinkedList<TurningPoint>();
	private MyMap map = new MyMap();
	private Handler handler;
	private boolean hadMouseListener = false;
	private Point MousePosition;
	private Obstacle obstacle;
	private int[] path = null;
	//private boolean hasObstacle = false;

	public SimulatorState(Handler handler2){
//		case 1
		if(this.handler == null)
			setHandler(handler2);
//		map.addPoint(new Point(20, 20)); 	//1
//		map.addPoint(new Point(399, 20)); 	//2
//		map.addPoint(new Point(779, 20)); 	//3
//		map.addPoint(new Point(199, 199));	//4
//		map.addPoint(new Point(399, 199));	//5
//		map.addPoint(new Point(599, 199));	//6
//		map.addPoint(new Point(20, 299));	//7
//		map.addPoint(new Point(199, 299));	//8
//		map.addPoint(new Point(399, 299));	//9
//		map.addPoint(new Point(599, 299));	//10
//		map.addPoint(new Point(779, 299));	//11
//		map.addPoint(new Point(499, 399));	//12
//		map.addPoint(new Point(20, 579));	//13
//		map.addPoint(new Point(779, 579));	//14
////		map.getPoint(0).setTrafficLight(true);
////		map.getPoint(1).setTrafficLight(true);
////		map.getPoint(2).setTrafficLight(true);
////		map.getPoint(3).setTrafficLight(true);
//		map.add(new StraightTurningPoint(map.getPoint(0), map.getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(1, 1), new Line(34, 6, 213, 185), new Line(6, 34, 185, 213)));
//		map.add(new StraightTurningPoint(map.getPoint(3), map.getPoint(0), TurningPoint.DEFAULT_RADIUS, new Direction(-1, -1), new Line(6, 34, 185, 213), new Line(34, 6, 213, 185)));
//		map.add(new StraightTurningPoint(map.getPoint(1), map.getPoint(4), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(419, 20, 419, 199), new Line(379, 20, 379, 199)));
//		map.add(new StraightTurningPoint(map.getPoint(4), map.getPoint(1), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(379, 20, 379, 199), new Line(419, 20, 419, 199)));
//		map.add(new StraightTurningPoint(map.getPoint(2), map.getPoint(5), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 1), new Line(793, 34, 613, 213), new Line(765, 6, 585, 185)));
//		map.add(new StraightTurningPoint(map.getPoint(5), map.getPoint(2), TurningPoint.DEFAULT_RADIUS, new Direction(1, -1), new Line(765, 6, 585, 185), new Line(793, 34, 613, 213)));
//		map.add(new StraightTurningPoint(map.getPoint(3), map.getPoint(4), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(199, 179, 399, 179), new Line(199, 219, 399, 219)));
//		map.add(new StraightTurningPoint(map.getPoint(4), map.getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(199, 219, 399, 219), new Line(199, 179, 399, 179)));
//		map.add(new StraightTurningPoint(map.getPoint(4), map.getPoint(5), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(399, 179, 599, 179), new Line(399, 219, 599, 219)));
//		map.add(new StraightTurningPoint(map.getPoint(5), map.getPoint(4), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(399, 219, 599, 219), new Line(399, 179, 599, 179)));
//		map.add(new StraightTurningPoint(map.getPoint(3), map.getPoint(7), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(219, 199, 219, 299), new Line(179, 199, 179, 299)));
//		map.add(new StraightTurningPoint(map.getPoint(7), map.getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(179, 199, 179, 299), new Line(219, 199, 219, 299)));
//		map.add(new StraightTurningPoint(map.getPoint(4), map.getPoint(8), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(419, 199, 419, 299), new Line(379, 199, 379, 299)));
//		map.add(new StraightTurningPoint(map.getPoint(8), map.getPoint(4), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(379, 199, 379, 299), new Line(419, 199, 419, 299)));
//		map.add(new StraightTurningPoint(map.getPoint(5), map.getPoint(9), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(619, 199, 619, 299), new Line(579, 199, 579, 299)));
//		map.add(new StraightTurningPoint(map.getPoint(9), map.getPoint(5), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(579, 199, 579, 299), new Line(619, 199, 619, 299)));
//		map.add(new StraightTurningPoint(map.getPoint(6), map.getPoint(7), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(20, 279, 199, 279), new Line(20, 319, 199, 319)));
//		map.add(new StraightTurningPoint(map.getPoint(7), map.getPoint(6), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(20, 319, 199, 319), new Line(20, 279, 199, 279)));
//		map.add(new StraightTurningPoint(map.getPoint(7), map.getPoint(8), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(199, 279, 399, 279), new Line(199, 319, 399, 319)));
//		map.add(new StraightTurningPoint(map.getPoint(8), map.getPoint(7), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(399, 319, 199, 319), new Line(399, 279, 199, 279)));
//		map.add(new StraightTurningPoint(map.getPoint(8), map.getPoint(9), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(399, 279, 599, 279), new Line(399, 319, 599, 319)));
//		map.add(new StraightTurningPoint(map.getPoint(9), map.getPoint(8), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(599, 319, 399, 319), new Line(599, 279, 399, 279)));
//		map.add(new StraightTurningPoint(map.getPoint(9), map.getPoint(10), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(599, 279, 779, 279), new Line(599, 319, 779, 319)));
//		map.add(new StraightTurningPoint(map.getPoint(10), map.getPoint(9), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(779, 319, 599, 319), new Line(779, 279, 599, 279)));
//		map.add(new StraightTurningPoint(map.getPoint(6), map.getPoint(12), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(40, 299, 40, 579), new Line(0, 299, 0, 579)));
//		map.add(new StraightTurningPoint(map.getPoint(12), map.getPoint(6), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(0, 299, 0, 579), new Line(40, 299, 40, 579)));
//		map.add(new StraightTurningPoint(map.getPoint(8), map.getPoint(12), TurningPoint.DEFAULT_RADIUS, new Direction(1, -1), new Line(410, 315, 32, 595), new Line(388, 283, 8, 562)));
//		map.add(new StraightTurningPoint(map.getPoint(12),map.getPoint(8), TurningPoint.DEFAULT_RADIUS, new Direction(1, -1), new Line(388, 283, 8, 562), new Line(410, 315, 32, 595)));
		//
//		map.add(new ArcTurningPoint(map.getPoint(0), map.getPoint(1), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new QuadCurve2D.Float(0, 0, 200, 0, 200, 100), new QuadCurve2D.Float(0, 40, 160, 40, 160, 100)));
//		map.add(new StraightTurningPoint(map.getPoint(1), map.getPoint(2), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(200, 100, 200, 200), new Line(160, 100, 160, 200)));
//		map.add(new ArcTurningPoint(map.getPoint(2), map.getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new QuadCurve2D.Float(200, 200, 200, 260, 300, 260), new QuadCurve2D.Float(160, 200, 160, 300, 300, 300)));
//		if(handler != null)
//			handler.getSimulator().getDisplay().getCanvas().addMouseListener(this);
		

//		stack.add(map.getTPfromPoint(0, 3));
//		stack.add(map.getTPfromPoint(3, 4));
//		stack.add(map.getTPfromPoint(4, 8));
//		stack.add(map.getTPfromPoint(8, 9));
//		setPath(12, 0);
//		path = handler.getSimulator().getMap().dijkstra(0, 12);
//		System.out.println(handler.getSimulator().getStartnode());
//		path = handler.getSimulator().getMap().dijkstra(handler.getSimulator().getStartnode(), handler.getSimulator().getFinishnode());
//		if(path == null)
//			{
//				State.setState(handler.getSimulator().getBewbsState());
////				System.out.println(State.getState());
////				System.out.println("path = null");
//			}
//		if(path != null) {
////			System.out.print(path.length);
//			int i;
//			for(i = 0; i < path.length - 1; i++)
//				stack.add(handler.getSimulator().getMap().getTPfromPoint(path[i], path[i+1]));
//			
////			stack.add(map.get(6));
////			stack.add(map.get(1));
////			stack.add(map.get(2));
//			car = new Car(stack.peek().getStart(), stack.peek().getDirection());
//		}
		
	}
	public void tick() {
		//System.out.println(car.distanceFrom(lineA));
		if(handler.getMouseManager().isLeftClicked())
			{
				if(!stack.isEmpty())
					clickObstacle((float) handler.getMouseManager().getMouseX(), (float) handler.getMouseManager().getMouseY());
				else
					{
						path = null;
						handler.getSimulator().setMenuVisible(true);
						State.setState(handler.getSimulator().getMenuState());
					}
			}
		if(path == null){
//			setPath();
//			System.out.println("dcmmmmmmmm");
//			setPath(handler.getSimulator().getStartnode(), handler.getSimulator().getFinishnode());
//			path = handler.getSimulator().getMap().dijkstra(handler.getSimulator().getStartnode(), handler.getSimulator().getFinishnode());
//			State.setState(handler.getSimulator().getBewbsState());
		}
			
		else
			{
				car.tick(stack, obstacle);
//				System.out.println(stack.isEmpty());
			}
		
	}
	public MyMap getMap() {
		return map;
	}
	public void setPath(){
//		path = map.dijkstra(start, finish);
		path = handler.getSimulator().getMap().dijkstra(handler.getSimulator().getStartnode(), handler.getSimulator().getFinishnode());
//		System.out.println(handler.getSimulator().getStartnode() + " + " + handler.getSimulator().getFinishnode());
		if(path!= null){
			int i;
			for(i = 0; i < path.length - 1; i++)
				stack.add(handler.getSimulator().getMap().getTPfromPoint(path[i], path[i+1]));
			
//			stack.add(map.get(6));
//			stack.add(map.get(1));
//			stack.add(map.get(2));
			car = new Car(stack.peek().getStart(), stack.peek().getDirection());
//			path = null;
		}
	}
	public int[] getPath(){
		return path;
	}
	
	@Override
	public void render(Handler handler, Graphics g) {
//		handler.getSimulator().getMap().render(g);
		if(path != null)
			handler.getSimulator().getMap().renderPath(g, path);
		handler.getSimulator().getMap().render(g);
//		if(this.handler == null)
//			setHandler(handler);
		//stack.peek().render(g);
//		MousePosition = new Point(MouseInfo.getPointerInfo().getLocation().x - handler.getSimulator().getDisplay().getCanvas().getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().getY() - handler.getSimulator().getDisplay().getCanvas().getLocationOnScreen().y);
		MousePosition = new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
		MousePosition.render(g);
		
//		if(!hadMouseListener){
//			handler.getSimulator().getDisplay().getCanvas().addMouseListener(this);
//			hadMouseListener = true;
//		}
			
//		i++;
//		addMouse();
		if(hasObstacle())
			obstacle.render(g);
		if(path!=null){
			
			car.setHandler(handler);
			car.render(g);
		}
		
		
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
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		clickObstacle((float) MousePosition.getX(), (float) MousePosition.getY());
////		System.out.println("(" + MousePosition.getX() + " " + MousePosition.getY() + ")");
//	}
	private boolean hasObstacle(){
		if(obstacle == null)
			return false;
		else return true;
	}
	public void clickObstacle(float x, float y){
		if(!hasObstacle())
			obstacle = new Obstacle(x, y);
		else obstacle = null;
	}
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}	
}
