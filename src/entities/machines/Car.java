package entities.machines;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import core.Handler;
import direction.Direction;
import gfx.Assets;
import myFuzzy.ObstacleHandle;
import myFuzzy.Speed;
import myFuzzy.Steering;
import path.Line;
import path.Point;

public class Car extends Machine{
	
	private Direction direction;
	public static final double SLOWER = 0.5;
	public static final double SLOW = 0.75;
	public static final double MEDIUM = 1;
	public static final int LOS = 113;
	public static final int LENGTH = 15;
	public static final int WIDTH = 10;
	private double speed = 1;
	private Handler handler;
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	private void setSpeed(double speed){
//		if(speed < 0.04)
//			this.speed = 0;
//		else 
			this.speed = speed;
	}
	private Steering steer;
	private Speed fuzzyspeed1;
	private ObstacleHandle fuzzyspeed2;
	private static final int size = 30;
	public Car(Point start, Direction d) {
		super((float)start.getX(), (float)start.getY());
		setDirection(new Direction((float) (d.getX() / Math.sqrt(d.getX()*d.getX() + d.getY()*d.getY())), (float) (d.getY() / Math.sqrt(d.getX()*d.getX() + d.getY()*d.getY()))));
//		setDirection(new Direction((float)((queue2.peek().Center.getX()-x)/Math.sqrt((queue2.peek().Center.getX()-x)*(queue2.peek().Center.getX()-x)+(queue2.peek().Center.getY()-y)*(queue2.peek().Center.getY()-y))), (float)((queue2.peek().Center.getY()-y)/Math.sqrt((queue2.peek().Center.getX()-x)*(queue2.peek().Center.getX()-x)+(queue2.peek().Center.getY()-y)*(queue2.peek().Center.getY()-y)))));
		steer = new Steering();
		steer.setFb("steering.fcl");
		fuzzyspeed1 = new Speed();
		fuzzyspeed2 = new ObstacleHandle();
		fuzzyspeed1.setFb("trafficlight.fcl");
		fuzzyspeed2.setFb("obstacle.fcl");
		
		// TODO Auto-generated constructor stub
	}
//	private Direction direction = new Direction(1, 0);
	public Direction getDirection(){
		return direction;
	}
	private void left(){
		direction.turn(-(Math.PI / 120)); //36
//		setSpeed(SLOW);
	}
	private void right(){
		direction.turn(Math.PI / 120); //36
//		setSpeed(LITTLE_SLOW);
	}
	private void hardleft(){
		direction.turn(-(Math.PI / 12)); //24
//		setSpeed(SLOW);
	}
	private void hardright(){
		direction.turn(Math.PI / 12); //24
//		setSpeed(SLOW);
	}
	private void foward(double speed){
		x += direction.getX() * speed;
		y += direction.getY() * speed;
	}
	//private double i = 0;
	private double steervalue = 0;
	private double deviation;
	private double lastvalue = 0;
	private double myfuzzyspeed1; 
	private double myfuzzyspeed2;
	private double lightstatus;
	private double distance1;
	private double distance2;
	private double lastDeviation;
	private Obstacle obstacle;
	private TurningPoint previous;
	private TurningPoint next;
	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
	private Queue<TurningPoint> queue = new LinkedList<TurningPoint>();
	
	public void tick(Queue<TurningPoint> queue2, Obstacle obstacle) {
		// TODO Auto-generated method stub
		//steervalue = steer.getValue(1);
//		System.out.println(steervalue);
		if(!queue2.isEmpty()){
			setObstacle(obstacle);
			setQueue(queue2);
//			if(previous == null){
//				deviation = queue2.peek().getDeviation(x, y);
//			}
//			else if(!incline(queue2.peek(),(int) x,(int) y) && (x - queue2.peek().Start.getX())*(x - queue2.peek().Start.getX())+(y - queue2.peek().Start.getY())*(y - queue2.peek().Start.getY()) < TurningPoint.DEFAULT_RADIUS*TurningPoint.DEFAULT_RADIUS){
//				deviation = previous.getDeviation(x, y);
//			}
//			else if(incline(queue2.peek(),(int) x,(int) y) || (x - queue2.peek().Start.getX())*(x - queue2.peek().Start.getX())+(y - queue2.peek().Start.getY())*(y - queue2.peek().Start.getY()) > TurningPoint.DEFAULT_RADIUS*TurningPoint.DEFAULT_RADIUS){
//				deviation = queue2.peek().getDeviation(x, y);
//				previous = null;
//			}
			if((x - queue2.peek().Start.getX())*(x - queue2.peek().Start.getX())+(y - queue2.peek().Start.getY())*(y - queue2.peek().Start.getY()) < TurningPoint.DEFAULT_RADIUS*TurningPoint.DEFAULT_RADIUS)
				{
					deviation = 0.5;
					lastDeviation = 0.5;
				}
			else
				{
					lastDeviation = deviation;
					deviation = queue2.peek().getDeviation(x, y);
				}
			lightstatus = queue2.peek().getLightStatus();
			distance1 = queue2.peek().getDistance(x, y);
			if(obstacleInSight(obstacle)){
				distance2 = obstacle.getDistance(x, y);
				myfuzzyspeed2 = fuzzyspeed2.getValue((float) deviation, distance2);
			}
			else myfuzzyspeed2 = 1;
			
//			if(steervalue!=-1)
			lastvalue = steervalue;
			steervalue = steer.getValue((float) deviation);
			
			myfuzzyspeed1 = fuzzyspeed1.getValue((float) deviation, lightstatus, distance1);
			double myfuzzyspeed = Math.min(myfuzzyspeed1, myfuzzyspeed2);
//			System.out.println(incline(queue2.peek(),(int) x,(int) y));
			if(myfuzzyspeed >= 0.02)
				setSpeed(myfuzzyspeed);
			else setSpeed(0);
//			System.out.println(obstacleInSight(obstacle));
//			System.out.println(distance1);
			
//			steer(steervalue);
			if(speed > 0){
//			if(steervalue <= 0.325)
//					hardleft();
//			else if(0.45 >= steervalue && steervalue >= 0.325)
////				if(lastvalue < 0.325)
////					right();
////				else
//					left();
//			else if(steervalue <= 0.55 && steervalue >= 0.45)
//			{
//					
////				if(lastvalue > 0.55)
////					hardleft();
////				else if(lastvalue < 0.45)
////					hardright();
//			}
//			else if(0.55 <= steervalue && steervalue < 0.675)
////				if(lastvalue > 0.675)
////					left();
////				else
//					right();
//			else if(steervalue >  0.675)
//					hardright();
				steer((steervalue - 0.5) /3);
				steer((lastDeviation - deviation) * 15);
			}
			System.out.println(myfuzzyspeed1);
			foward(speed);
			if(queue2.peek().hasCame(x, y)){
				System.out.println("Came");
				previous = queue2.peek();
				queue2.remove();
				if(!queue2.isEmpty())
				setDirection(new Direction((float)((queue2.peek().Center.getX()-x)/Math.sqrt((queue2.peek().Center.getX()-x)*(queue2.peek().Center.getX()-x)+(queue2.peek().Center.getY()-y)*(queue2.peek().Center.getY()-y))), (float)((queue2.peek().Center.getY()-y)/Math.sqrt((queue2.peek().Center.getX()-x)*(queue2.peek().Center.getX()-x)+(queue2.peek().Center.getY()-y)*(queue2.peek().Center.getY()-y)))));
			}
		}
		else{
			carStop();
		}	
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	private boolean obstacleInSight(Obstacle obstacle){
		if(!hasObstacle())
			return false;		
		else {
			int a = (int) obstacle.getX();
			int b = (int) obstacle.getY();
			int x1 = losx[0], y1 = losy[0];
			int x2 = losx[1], y2 = losy[1];
			int x3 = losx[2], y3 = losy[2];
			double ABC = Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
			double ABP = Math.abs (x1 * (y2 - b) + x2 * (b - y1) + a * (y1 - y2));
			double APC = Math.abs (x1 * (b - y3) + a * (y3 - y1) + x3 * (y1 - b));
			double PBC = Math.abs (a * (y2 - y3) + x2 * (y3 - b) + x3 * (b - y2));
			return ABP + APC + PBC == ABC;			
		}
	}
	private void steer(double steering){
		direction.turn(steering * (Math.PI / 24));
	}
	private void carStop() {
		
	}
	private void setQueue(Queue<TurningPoint> queue2) {
		this.queue = queue2;
		
	}
	private boolean incline(TurningPoint current, int x, int y){
		int x1 = (int) current.Center.getX();
		int y1 = (int) current.Center.getY();
		int x2 = (int) current.Start.getX();
		int y2 = (int) current.Start.getY();
		double ABC = Math.abs (x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
		return ABC == 0;
	}
	private boolean hasObstacle(){
		return((obstacle!=null));
	}
	private int losx[] = new int[3];
	private int losy[] = new int[3];
	
	public void drawLOS(Graphics g){
		Direction vision = new Direction(direction.getX(), direction.getY());
//		float LOSx = vision.getX() + LOS;
//		float LOSy = vision.getY() + LOS;
		vision.turn(Math.PI / 4);
		float LOSx1 = x + vision.getX()*LOS;
		float LOSy1 = y + vision.getY()*LOS;
		vision.turn(-Math.PI / 2);
		float LOSx2 = x + vision.getX()*LOS;
		float LOSy2 = y + vision.getY()*LOS;
//		int losx[] = new int[3];
//		int losy[] = new int[3];
		losx[0] = (int) x; 
		losx[1] = (int) LOSx1;
		losx[2] = (int) LOSx2;
		losy[0] = (int) y;
		losy[1] = (int) LOSy1;
		losy[2] = (int) LOSy2;
//		System.out.println(handler.toString());
		int n = 3;
		
		Polygon p = new Polygon(losx, losy, n);
//		Graphics2D g2d = (Graphics2D) g;
//	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 * 0.15f));
	    g.setColor(Color.CYAN);
	    
	    g.fillPolygon(p);
	}
	public void drawCar(Graphics g){
		Direction carDir = new Direction(direction.getX(), direction.getY());
		int drawCarX[] = new int[4];
		int drawCarY[] = new int[4];
		Point head = new Point(x + carDir.getX()*LENGTH, y + carDir.getY()*LENGTH);
		Point tail = new Point(x - carDir.getX()*LENGTH, y - carDir.getY()*LENGTH);
		carDir.turn(Math.PI/2);
		drawCarX[0] = (int) (head.getX() + carDir.getX()*WIDTH);
		drawCarY[0] = (int) (head.getY() + carDir.getY()*WIDTH);
		drawCarX[1] = (int) (head.getX() - carDir.getX()*WIDTH);
		drawCarY[1] = (int) (head.getY() - carDir.getY()*WIDTH);
		drawCarX[2] = (int) (tail.getX() - carDir.getX()*WIDTH);
		drawCarY[2] = (int) (tail.getY() - carDir.getY()*WIDTH);
		drawCarX[3] = (int) (tail.getX() + carDir.getX()*WIDTH);
		drawCarY[3] = (int) (tail.getY() + carDir.getY()*WIDTH);
		int n = 4;
		Polygon car = new Polygon(drawCarX, drawCarY, n);
		g.setColor(Color.MAGENTA);
		g.fillPolygon(car);
	}
	public void render(Graphics g) {
		if(!queue.isEmpty())
			{
//				queue.peek().render(g);
			}
		
		
//		Component[] components = yourJFrame.getComponents();
		
		
//		g.drawImage(Assets.car, (int) x - size/2, (int) y - size/2, size, size, null);
		drawCar(g);
//		drawLOS(g);
		//g.drawImage(Assets.car, (int) x, (int) y, null)	;	
	}
	public Queue<TurningPoint> getQueue() {
		return queue;
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
