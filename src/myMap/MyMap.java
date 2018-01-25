package myMap;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.machines.TurningPoint;

public class MyMap {
	private ArrayList<TurningPoint> map;
	
	public MyMap(){
		map = new ArrayList<TurningPoint>();
	}
	public void add(TurningPoint tp){
		map.add(tp);
	}
	public void render(Graphics g){
		int i;
		for(i = 0; i < map.size(); i++)
			map.get(i).render(g);
	}
	public TurningPoint get(int i){
		return map.get(i);
	}
}
