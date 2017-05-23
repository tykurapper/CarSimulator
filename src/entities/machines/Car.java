package entities.machines;

import java.awt.Color;
import java.awt.Graphics;

import direction.Direction;
import gfx.Assets;
import myFuzzy.Steering;
import path.Line;

public class Car extends Machine{

	public Steering steer;
	private static final int size = 30;
	public Car(float x, float y) {
		super(x, y);
		steer = new Steering();
		steer.setFb("steering.fcl");
		// TODO Auto-generated constructor stub
	}
	private Direction direction = new Direction(0, 1);
	public Direction getDirection(){
		return direction;
	}
	public void left(){
		direction.turn(-(Math.PI / 36)); //36
	}
	public void right(){
		direction.turn(Math.PI / 36); //36
	}
	public void hardleft(){
		direction.turn(-(Math.PI / 24)); //24
	}
	public void hardright(){
		direction.turn(Math.PI / 24); //24
	}
	public void foward(){
		x += direction.getX();
		y += direction.getY();
	}
	//private double i = 0;
	private double steervalue = -1;
	private double distanceFromA;
	private double distanceFromB;
	private double distanceFromC;
	private double distanceFromD;
	private double deviation;
	private double lastvalue = 0.5;
	
	public void tick(Line LineA, Line LineB, Line LineC, Line LineD) {
		// TODO Auto-generated method stub
		//steervalue = steer.getValue(1);
		//System.out.println(steervalue);
		distanceFromA = this.distanceFrom(LineA);
		distanceFromB = this.distanceFrom(LineB);
		distanceFromC = this.distanceFrom(LineC);
		distanceFromD = this.distanceFrom(LineD);
		if(x <= 200){
			deviation = distanceFromB / distanceFromA;
		}
		else if(x > 200)
			deviation = distanceFromC / distanceFromD;
		if(steervalue!=-1)
			lastvalue = steervalue;
		steervalue = steer.getValue((float) deviation);
		System.out.println(steervalue);
		//i++;

		//if(i == 5){
		//	i = 0;
			//left();
			if(steervalue < 0.09)
				hardright();
			else if(0.09 <= steervalue && steervalue < 0.375)
				right();
			else if(0.625 < steervalue && steervalue <= 0.9)
				left();
			else if(steervalue > 0.9)
				hardleft();
			else if(steervalue >= 0.375 && steervalue <= 0.625){
				if(lastvalue < 0.375)
					hardleft();
				if(lastvalue > 0.625)
					hardright();
			}
		//}
		foward();
		
	}
	public double distanceFrom(Line line){
		double x1 = line.getA().getX();
		double y1 = line.getA().getY();
		double x2 = line.getB().getX();
		double y2 = line.getB().getY();
		
		double A = x - x1;
		double B = y - y1;
		double C = x2 - x1;
		double D = y2 - y1;
		
		double dot = A * C + B * D;
		double len_sq = C * C + D * D;
		double param = -1;
		if (len_sq != 0) //in case of 0 length line
		    param = dot / len_sq;

		double xx, yy;

		if (param < 0) {
		  xx = x1;
		  yy = y1;
		}
		else if (param > 1) {
		  xx = x2;
		  yy = y2;
		}
		else {
		  xx = x1 + param * C;
		  yy = y1 + param * D;
		}

		double dx = x - xx;
		double dy = y - yy;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public void render(Graphics g) {
		g.drawImage(Assets.car, (int) x - size/2, (int) y - size/2, size, size, null);
		
		//g.drawImage(Assets.car, (int) x, (int) y, null)	;	
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
