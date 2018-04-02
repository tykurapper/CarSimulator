package states;

import java.awt.Graphics;

import core.Handler;
import gfx.Assets;

public class BewbsState extends State{
	
	public BewbsState(){
	}
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Handler handler, Graphics g) {
		
		g.drawImage(Assets.bewbs, 0, 0, null);
		
	}
	
}
