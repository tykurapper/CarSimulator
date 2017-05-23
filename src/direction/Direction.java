package direction;

public class Direction {
	
	private float x;
	private float y;
	
	public Direction(float x, float y){
		this.x = x;
		this.y = y;
	}
	public void turn(double d){
		double rx = (this.x * Math.cos(d)) - (this.y * Math.sin(d));
	    double ry = (this.x * Math.sin(d)) + (this.y * Math.cos(d));
	    x = (float) rx;
	    y = (float) ry;
	}

	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
}
