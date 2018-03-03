package entities.machines;

import java.awt.Color;
import java.awt.Graphics;

import path.Point;

public class Zone {
	private Point pos;
	public static final int ZoneRadius = TurningPoint.DEFAULT_RADIUS;
	boolean isTrafficLight;
	public Zone(Point pos, boolean isTrafficLight) {
		this.pos = pos;
		this.isTrafficLight = isTrafficLight;
	}
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public boolean isTrafficLight() {
		return isTrafficLight;
	}
	public void setTrafficLight(boolean isTrafficLight) {
		this.isTrafficLight = isTrafficLight;
	}
	private void drawCircle(Graphics g, int x, int y, int radius){
		int diameter = radius * 2;

		  //shift x and y by the radius of the circle in order to correctly center it
		g.setColor(Color.BLACK);
		g.drawOval(x - radius, y - radius, diameter, diameter); 
	}
	private void drawTrafficLight(Graphics g, int x, int y, int radius){
		int diameter = radius * 2;

		  //shift x and y by the radius of the circle in order to correctly center it
		g.setColor(Color.GREEN);
		g.fillOval(x - radius, y - radius, diameter, diameter); 
	}
	public void render(Graphics g){
		if(isTrafficLight == false){
			drawCircle(g,(int) pos.getX(),(int) pos.getY(), TurningPoint.DEFAULT_RADIUS);
		}
		else{
			drawTrafficLight(g, (int) pos.getX(), (int) pos.getY(), TurningPoint.DEFAULT_RADIUS);
		}
	}
}
