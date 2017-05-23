package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage car, bewbs;
	
	public static void init(){
		car = ImageLoader.loadImage("/textures/car.png");
		bewbs = ImageLoader.loadImage("/textures/90621bfa7305be29bf6bf9052369fc5b.gif");
	}
	
}
