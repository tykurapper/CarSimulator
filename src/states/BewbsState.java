package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import core.Handler;
import gfx.Assets;

public class BewbsState extends State{
	private boolean hadMouseListener = false;
	private Handler handler;
	public BewbsState(Handler handler){
		if(this.handler == null)
			setHandler(handler);
	}
	private void setHandler(Handler handler2) {
		// TODO Auto-generated method stub
		handler = handler2;
	}
	public void tick() {
		// TODO Auto-generated method stub
		if(handler.getMouseManager().isLeftClicked())
			State.setState(handler.getSimulator().getMenuState());
	}

	@Override
	public void render(Handler handler, Graphics g) {
		
		g.drawImage(Assets.bewbs, 0, 0, null);
		
		if(this.handler == null)
		setHandler(handler);
		// TODO Auto-generated method stub
//		if(!hadMouseListener){
//			handler.getSimulator().getDisplay().getCanvas().addMouseListener(this);
//			hadMouseListener = true;
//		}
	}
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		if(State.getState() instanceof BewbsState)
//		{
//			State.setState(handler.getSimulator().getMenuState());
//			System.out.println(1);
//		}
//		
//	}
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
