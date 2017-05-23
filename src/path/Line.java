package path;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line {
	private Point a, b;
	public Point getA() {
		return a;
	}
	public void setA(Point a) {
		this.a = a;
	}
	public Point getB() {
		return b;
	}
	public void setB(Point b) {
		this.b = b;
	}
	public Line(double m, double n, double p, double q){
		a = new Point(m, n);
		b = new Point(p, q);
	}
	public Line(Point a, Point b){
		this.a = a;
		this.b = b;
	}
	public void draw(Graphics g, Color color){
		//Graphics2D g2 = (Graphics2D) g;
		//g2.setStroke(new BasicStroke(10));
		g.setColor(color);
		g.drawLine((int) a.getX(), (int) a.getY(),(int) b.getX(),(int) b.getY());
	}
}
