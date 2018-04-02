package entities.machines;

import java.awt.Color;
import java.awt.Graphics;

import path.Point;

public class Obstacle {
	private float x, y;

	public Obstacle(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	public void render(Graphics g){
		g.setColor(Color.GRAY);
		int radius = TurningPoint.DEFAULT_RADIUS;
		int diameter = TurningPoint.DEFAULT_RADIUS * 2;
		g.fillOval((int) x - radius, (int) y - radius, diameter, diameter);
	}
	public double getDistance(float x, float y){
		return Math.sqrt((x - this.x)*(x - this.x)+(y - this.y)*(y - this.y)) - TurningPoint.DEFAULT_RADIUS;
	}
}
