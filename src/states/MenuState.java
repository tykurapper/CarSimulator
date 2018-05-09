package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import core.Handler;

public class MenuState extends State{
	private boolean hadMouseListener = false;
	private Handler handler;
	public MenuState(Handler handler) {
		// TODO Auto-generated constructor stub
		if(this.handler == null)
			setHandler(handler);
	}

	private void setHandler(Handler handler2) {
		// TODO Auto-generated method stub
		handler = handler2;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(handler.getMouseManager().isLeftClicked())
			{
//				State.setState(handler.getSimulator().getSimulatorState());
				System.out.println(handler.getMouseManager().getMouseX() + "  " + handler.getMouseManager().getMouseY());
			}
//		System.out.println(handler.getMouseManager().getMouseX() + "  " + handler.getMouseManager().getMouseY());
	}

	@Override
	public void render(Handler handler, Graphics g) {
		if(this.handler == null)
		setHandler(handler);
		handler.getSimulator().getMap().render(g);
		// TODO Auto-generated method stub

	}

//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		//System.out.println(handler.getSimulator().getMenuState().getState());
//		if(State.getState() instanceof MenuState){
//			State.setState(handler.getSimulator().getSimulatorState());
////			System.out.println(2);
//		}
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

}
