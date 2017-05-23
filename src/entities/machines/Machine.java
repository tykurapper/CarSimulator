package entities.machines;

import entities.Entity;

public  abstract class Machine extends Entity{

	protected int health;
	public Machine(float x, float y){
		super(x, y);
		health = 10;
	}
	
}
