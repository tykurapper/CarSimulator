package myMap;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.machines.TurningPoint;
import path.Point;

public class MyMap {
	private ArrayList<TurningPoint> map;
	private ArrayList<Point> point;
	
	public MyMap(){
		map = new ArrayList<TurningPoint>();
		point = new ArrayList<Point>();
	}
	public void addPoint(Point p){
		point.add(p);
	}
	public void add(TurningPoint tp){
		map.add(tp);
	}
	public void render(Graphics g){
		int i;
		for(i = 0; i < map.size(); i++)
			map.get(i).render(g);
		for(i = 0; i < point.size(); i++)
			point.get(i).render(g);
	}
	public Point getPoint(int i){
		return point.get(i);
	}
	public TurningPoint get(int i){
		return map.get(i);
	}
}
