package entities.machines;

import java.awt.Graphics;

import direction.Direction;
import path.Point;

import path.Line;

public abstract class TurningPoint {
	public static final int DEFAULT_RADIUS = 20; 
	protected Point Center;
	protected int Radius; 
	protected Point Start;
	protected Direction direction;
	public boolean hasCame(float x, float y){
		if((x - Center.getX())*(x - Center.getX())+(y - Center.getY())*(y - Center.getY()) < DEFAULT_RADIUS*DEFAULT_RADIUS)
			return true;
		else return false;		
	}
	public abstract double getDeviation(float x, float y);
	public double getLightStatus(){
		if(Center.isTrafficLight())
			return((double)Center.getLightnumber()/(double)Center.getMaxnumber());
		else return 0;
	}
	public double getDistance(float x, float y){
		return Math.sqrt((x - Center.getX())*(x - Center.getX())+(y - Center.getY())*(y - Center.getY())) - DEFAULT_RADIUS;
	}
	public abstract void render(Graphics g);
	public Point getStart() {
		return Start;
	}
	public Direction getDirection() {
		return direction;
	}
	
}
