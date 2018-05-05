package states;

import java.awt.Graphics;

import core.Handler;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	public abstract void tick();
	
	public abstract void render(Handler handler, Graphics g);
	
}
