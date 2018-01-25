package entities.machines;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import direction.Direction;
import gfx.Assets;
import myFuzzy.Steering;
import path.Line;
import path.Point;

public class Car extends Machine{
	
	private Direction direction;
	public static double SLOW = 0.5;
	public static double LITTLE_SLOW = 0.75;
	public static double NORMAL = 1;
	private double speed = 1;
	private void setSpeed(double speed){
		this.speed = speed;
	}
	private Steering steer;
	private static final int size = 30;
	public Car(Point start, Direction d) {
		super((float)start.getX(), (float)start.getY());
		direction = d;
		steer = new Steering();
		steer.setFb("steering.fcl");
		// TODO Auto-generated constructor stub
	}
//	private Direction direction = new Direction(1, 0);
	public Direction getDirection(){
		return direction;
	}
	private void left(){
		direction.turn(-(Math.PI / 120)); //36
		setSpeed(LITTLE_SLOW);
	}
	private void right(){
		direction.turn(Math.PI / 120); //36
		setSpeed(LITTLE_SLOW);
	}
	private void hardleft(){
		direction.turn(-(Math.PI / 12)); //24
		setSpeed(SLOW);
	}
	private void hardright(){
		direction.turn(Math.PI / 12); //24
		setSpeed(SLOW);
	}
	private void foward(double speed){
		x += direction.getX() * speed;
		y += direction.getY() * speed;
	}
	//private double i = 0;
	private double steervalue = 0;
	private double deviation;
	private double lastvalue = 0;
	private Queue<TurningPoint> queue = new LinkedList<TurningPoint>();
	
	public void tick(Queue<TurningPoint> queue2) {
		// TODO Auto-generated method stub
		//steervalue = steer.getValue(1);
//		System.out.println(steervalue);
		if(!queue2.isEmpty()){
			setQueue(queue2);
			deviation = queue2.peek().getDeviation(x, y);
//			if(steervalue!=-1)
			lastvalue = steervalue;
			steervalue = steer.getValue((float) deviation);
			System.out.println(speed);
//			steer(steervalue);
			if(steervalue > 0.67)
					hardleft();
			else if(0.167 < steervalue && steervalue <= 0.67)
				if(lastvalue > 0.67)
					right();
				else
					left();
			else if(steervalue >= -0.167 && steervalue <= 0.167)
			{
					
				if(lastvalue < -0.167)
					hardleft();
				else if(lastvalue > 0.167)
					hardright();
				else setSpeed(NORMAL);
//					steer(0);
			}
			else if(-0.67 <= steervalue && steervalue < -0.167)
				if(lastvalue < -0.67)
					left();
				else
					right();
			else if(steervalue < -0.67)
					hardright();

			foward(speed);
			if(queue2.peek().hasCame(x, y)){
				System.out.println("Came");
				queue2.remove();
			}
		}
		else{
			carStop();
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
	public void render(Graphics g) {
		if(!queue.isEmpty())
			queue.peek().render(g);
		g.drawImage(Assets.car, (int) x - size/2, (int) y - size/2, size, size, null);
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
