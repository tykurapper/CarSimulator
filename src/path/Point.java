package path;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import entities.machines.TurningPoint;

public class Point {
	private boolean isTrafficLight;
	private static final int MAX_NUMBER = 600;
	private int maxnumber;
	private int lightnumber;
	
	public int getLightnumber() {
		return lightnumber;
	}
	private double x, y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Point(double x, double y){
		this.x = x;
		this.y = y;
		isTrafficLight = false;
	}
	public boolean isTrafficLight() {
		return isTrafficLight;
	}
	public void setTrafficLight(boolean isTrafficLight) {
		this.isTrafficLight = isTrafficLight;
		if (isTrafficLight){
			lightnumber = 0;
			maxnumber = MAX_NUMBER + ThreadLocalRandom.current().nextInt(0, 200);
			lightnumber = 0 + ThreadLocalRandom.current().nextInt(0, maxnumber * 2 / 3);
		}
	}
	public static void drawCircle(Graphics g, int x, int y, int radius){
		int diameter = radius * 2;

		  //shift x and y by the radius of the circle in order to correctly center it
		g.setColor(Color.BLACK);
		g.drawOval(x - radius, y - radius, diameter, diameter); 
	}
	public static void drawTrafficLight(Graphics g, int x, int y, int radius){
		int diameter = radius * 2;

		  //shift x and y by the radius of the circle in order to correctly center it

		g.fillOval(x - radius, y - radius, diameter, diameter); 
	}
	public void render(Graphics g){
		if(isTrafficLight == false){
			drawCircle(g,(int) x,(int) y, TurningPoint.DEFAULT_RADIUS);
		}
		else{
			if(lightnumber < maxnumber)
				lightnumber++;
			else lightnumber = 0;
			if(lightnumber <= maxnumber / 2)
				g.setColor(Color.GREEN);
			else if (lightnumber <= maxnumber / 2 + maxnumber / 6)
				g.setColor(Color.YELLOW);
			else g.setColor(Color.RED);
//			System.out.println(maxnumber);
			drawTrafficLight(g, (int) x, (int) y, TurningPoint.DEFAULT_RADIUS);
		}
	}
	public int getMaxnumber() {
		return maxnumber;
	}
}

