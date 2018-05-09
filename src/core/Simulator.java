package core;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import display.Display;
import entities.machines.Car;
import gfx.Assets;
import input.MouseManager;
import myMap.MyMap;
import states.BewbsState;
import states.MenuState;
import states.SimulatorState;
import states.State;

public class Simulator implements Runnable{
	
	private Display display;
	private JFrame menu;
	public int width, height;
	public String title;
	private Handler handler;
	
	public int getStartnode() {
		return startnode;
	}

	public int getFinishnode() {
		return finishnode;
	}

	// Input
	private MouseManager mouseManager;
	private int startnode, finishnode;
	//
	private boolean running = false;
	private Thread thread;
	// Map
	private MyMap map;
	public MyMap getMap() {
		return map;
	}

	//
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
		mouseManager = new MouseManager();
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	private void init(){
		display = new Display(title, width, height);

		
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		map = new MyMap("case 1");
		handler = new Handler(this);
		simulatorState = new SimulatorState(handler);
		menuState = new MenuState(handler);
		bewbsState = new BewbsState(handler);
		State.setState(menuState);
		startMenu();
	}
	
	public void startMenu(){
		menu = new JFrame("Menu");
		JLabel startLabel = new JLabel("Start: ", JLabel.RIGHT);
		JLabel finishLabel = new JLabel("Finish: ", JLabel.CENTER);
		final JTextField start = new JTextField(6);
		final JTextField finish = new JTextField(6);
//		map = new MyMap("case 1");
		JButton button = new JButton("Run Simulator");
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(start.getText().isEmpty() || finish.getText().isEmpty())
	        		 JOptionPane.showMessageDialog(null, "Chua dien thong tin");
	        	 else{
		        	 startnode = Integer.parseInt(start.getText()) - 1;
		        	 finishnode = Integer.parseInt(finish.getText()) - 1;
		        	 
		        	 if(startnode == finishnode || map.dijkstra(startnode, finishnode) == null)
		        		 JOptionPane.showMessageDialog(null, "Nhap lai");
		        	 else{
		        		 menu.setVisible(false);
		        		 State.setState(simulatorState);
		        		 start();
		        	 }
	
	        	 }   
	         }
	      }); 
		menu.setSize(300, 200);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		menu.setAlwaysOnTop(true);
		menu.setVisible(true);
		menu.setResizable(false);
		menu.setLocationRelativeTo(null);
		JPanel menuPanel = new JPanel();
//		menuPanel.addInputMethodListener(null);
		menuPanel.add(startLabel);
		menuPanel.add(start);
		menuPanel.add(finishLabel);
		menuPanel.add(finish);
		menuPanel.add(button);
//		menuPanel.add(JPanel.getButtonPanel(), BorderLayout.PAGE_END);
		menu.add(menuPanel);
	}
	public State getBewbsState() {
		return bewbsState;
	}
	public void setMenuVisible(boolean b){
		menu.setVisible(b);
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
