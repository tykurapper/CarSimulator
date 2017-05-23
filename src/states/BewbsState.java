package states;

import java.awt.Graphics;

import gfx.Assets;

public class BewbsState extends State{
	
	public BewbsState(){
	}
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.bewbs, 0, 0, null);
		
	}
	
}
