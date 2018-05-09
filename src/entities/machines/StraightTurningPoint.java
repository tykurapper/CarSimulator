package entities.machines;

import java.awt.Color;
import java.awt.Graphics;

import direction.Direction;
import path.Point;

import path.Line;

public class StraightTurningPoint extends TurningPoint{
	protected Line lineLeft;
	protected Line lineRight;
	public StraightTurningPoint(Point start, Point center, int radius, Direction d, Line lineLeft, Line lineRight) {
		super();
		Start = start;
		direction = d;
		Center = center;
		Radius = radius;
		this.lineLeft = lineLeft;
		this.lineRight = lineRight;
	}
	public double distanceFrom(float x, float y, Line line){
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
	
	public double getDeviation(float x, float y){
		return (distanceFrom(x, y, lineLeft) / distanceFrom(x, y, lineRight));
	}
	
	public void render(Graphics g){
		lineLeft.draw(g, Color.BLACK);
		lineRight.draw(g, Color.BLACK);
	}
	public void renderPath(Graphics g){
		lineLeft.draw(g, Color.RED);
		lineRight.draw(g, Color.BLUE);
	}
}
