package core;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import entities.machines.Car;
import gfx.Assets;
import states.BewbsState;
import states.MenuState;
import states.SimulatorState;
import states.State;

public class Simulator implements Runnable{
	
	private Display display;
	public int width, height;
	public String title;
	private Handler handler;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//State
	private State simulatorState;
	private State bewbsState;
	private State menuState;
	
	public Simulator(String title, int width, int height){
		
		this.title = title;
		this.width = width;
		this.height = height;
				
	}

	private void init(){
		display = new Display(title, width, height);
		Assets.init();
		handler = new Handler(this);
		simulatorState = new SimulatorState(handler);
		menuState = new MenuState(handler);
		bewbsState = new BewbsState(handler);
		State.setState(menuState);
	}
	
	public State getBewbsState() {
		return bewbsState;
	}

	public void setBewbsState(State bewbsState) {
		this.bewbsState = bewbsState;
	}

	public State getMenuState() {
		return menuState;
	}

	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}

	private void tick(){
		if(State.getState() != null){
			State.getState().tick();
		}
	}
	
	private void render(Handler handler){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear
		g.clearRect(0, 0, width, height);
		
		//Begin Draw
		
		if(State.getState() != null){
//			State.getState().setHandler(handler);
			State.getState().render(handler, g);
		}
		
		//End Draw
		
		bs.show();
		g.dispose();
	}
	
	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public void run() {
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		long ticks = 0;
		
		init();
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render(handler);
				ticks ++;
				delta --;
			}
//			if(timer >= 1000000000){
//				System.out.println("FPS: " + ticks);
//				ticks = 0;
//				timer = 0;
//			}
		}
		
		stop();
		
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public State getSimulatorState() {
		return simulatorState;
	}

	public void setSimulatorState(State simulatorState) {
		this.simulatorState = simulatorState;
	}
	

}
