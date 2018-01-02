package entities.machines;

import java.awt.Graphics;
import path.Point;

import path.Line;

public abstract class TurningPoint {
	public static final int DEFAULT_RADIUS = 20; 
	protected Point Center;
	protected int Radius; 
	
	public boolean hasCame(float x, float y){
		if((x - Center.getX())*(x - Center.getX())+(y - Center.getY())*(y - Center.getY()) < DEFAULT_RADIUS*DEFAULT_RADIUS)
			return true;
		else return false;		
	}
	public abstract double getDeviation(float x, float y);

	public abstract void render(Graphics g);
}
