package entities.machines;

import java.awt.Graphics;
import java.awt.Graphics2D;

import path.Point;
import java.awt.geom.QuadCurve2D;

import myMath.Cubic;
import path.Line;

public class ArcTurningPoint extends TurningPoint{
	QuadCurve2D leftCurve = new QuadCurve2D.Float();
	QuadCurve2D rightCurve = new QuadCurve2D.Float();

	public ArcTurningPoint(Point center, int radius, QuadCurve2D leftC, QuadCurve2D rightC) {
		super();
		Center = center;
		Radius = radius;
		leftCurve.setCurve(leftC);
		rightCurve.setCurve(rightC);
	}
	public double distanceFrom(float x, float y, QuadCurve2D curve){
		Point A = new Point(curve.getCtrlX() - curve.getX1(), curve.getCtrlY() - curve.getY1());
		Point B = new Point(curve.getX1() - 2*curve.getCtrlX() + curve.getX2(), curve.getY1() - 2*curve.getCtrlY() + curve.getY2());
		if(B.getX()*B.getX() + B.getY()*B.getY() == 0)
			return ((x-B.getX())*(x-B.getX())+(y-B.getY())*(y-B.getY()));
		else {
			Point pos = new Point(curve.getX1() - x, curve.getY1() - y);
			Cubic cubic = new Cubic();
			double a = B.getX()*B.getX() + B.getY()*B.getY();
			double b = 3 * (A.getX() * B.getX() + A.getY() * B.getY());
			double c = 2 * (A.getX() * A.getX() + A.getY() * A.getY()) + pos.getX()*B.getX() + pos.getY()*B.getY();
			double d = pos.getX()*A.getX() + pos.getY()*A.getY();
			cubic.solve(a, b, c, d);
			double distMin = 9999;
			double distance;
			if(cubic.x1 <= 1 && cubic.x1 >= 0){
				a = (1 - cubic.x1) * (1-cubic.x1);
				b = 2*cubic.x1*(1-cubic.x1);
				c = cubic.x1 * cubic.x1;
				pos.setX(a * curve.getX1() + b*curve.getCtrlX() + c*curve.getX2());
				pos.setY(a * curve.getY1() + b*curve.getCtrlY() + c*curve.getY2());
				distance = Math.sqrt(((x-pos.getX())*(x-pos.getX())+ (y-pos.getY())*(y-pos.getY())));
				if(distance < distMin)
					distMin = distance;
			}
			if(cubic.x2 <= 1 && cubic.x2 >= 0){
				a = (1 - cubic.x2) * (1-cubic.x2);
				b = 2*cubic.x2*(1-cubic.x2);
				c = cubic.x2 * cubic.x2;
				pos.setX(a * curve.getX1() + b*curve.getCtrlX() + c*curve.getX2());
				pos.setY(a * curve.getY1() + b*curve.getCtrlY() + c*curve.getY2());
				distance = Math.sqrt(((x-pos.getX())*(x-pos.getX())+ (y-pos.getY())*(y-pos.getY())));
				if(distance < distMin)
					distMin = distance;
			}
			if(cubic.x3 <= 1 && cubic.x3 >= 0){
				a = (1 - cubic.x3) * (1-cubic.x3);
				b = 2*cubic.x3*(1-cubic.x3);
				c = cubic.x3 * cubic.x3;
				pos.setX(a * curve.getX1() + b*curve.getCtrlX() + c*curve.getX2());
				pos.setY(a * curve.getY1() + b*curve.getCtrlY() + c*curve.getY2());
				distance = Math.sqrt(((x-pos.getX())*(x-pos.getX())+ (y-pos.getY())*(y-pos.getY())));
				if(distance < distMin)
					distMin = distance;
			}
			distance = Math.sqrt(((x-curve.getX1())*(x-curve.getX1())+ (y-curve.getY1())*(y-curve.getY1())));
			if(distance < distMin)
				distMin = distance;
			distance = Math.sqrt(((x-curve.getX2())*(x-curve.getX2())+ (y-curve.getY2())*(y-curve.getY2())));
			if(distance < distMin)
				distMin = distance;
			return distMin;
		}
	}
	@Override
	public double getDeviation(float x, float y) {
		return(distanceFrom(x, y, leftCurve) / distanceFrom(x, y, rightCurve));
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		((Graphics2D) g).draw(leftCurve);
		((Graphics2D) g).draw(rightCurve);
	}

}
