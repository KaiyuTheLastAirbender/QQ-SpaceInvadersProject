

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Bullet {
	public  int x; //position of the bullet
	public  int y;
	public int w=10, h=10;

	private AffineTransform tx;
	public Rectangle hitbox; //bullet hitbox

	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;

		hitbox = new Rectangle(x, y, w, h);
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//call update to update the actual picture location
		y-=5;
		update(x, y);
	//	g2.drawRect(x, y, w, h);
		g2.setPaint(new Color(255,255,255));
		g.fillRect(x, y, w, h);

	}
	
	public void moveBullet(int mx, int my) {
		int smaller, deltax, deltay, incx, incy, minspeed = 5;
		

		//calculate x (horizontal) distance from the original bullet location to the mouse click
		deltax =  mx - x;
		//calculate y (vertical) distance from the original bullet location to the mouse click
		deltay =  my - y;
		//check which distance is smaller
		//y can be negative while x is always positive
		if (deltax < Math.abs(deltay)) {
			smaller = deltax;
		}
		else {
			smaller = Math.abs(deltay);  // smaller has to be a positive value
		}
		//determine increment for x, y position for the bullet by dividing deltax and deltay by whichever is smaller
		incx = deltax/smaller;  
		incy = deltay/smaller;
		
		if (incx < minspeed) {  //if x increment is too small (too slow), increase speed
			
			if(incy < minspeed && incy > -minspeed) { //if increment y is too small (absolute value of incy is less than minspeed)
				incx *= minspeed;
				incy *= minspeed;
			} 

		}
		
		//the x & y positions of the bullet are moving incrementally based on the calculations above
		x += incx;
		y += incy;
		
		/*
		if (deltay > 0 && (x<mx) && (y<my)) { // mouse click target (bear) is below the original bullet location (final y > initial y)
			x += incx;
			y += incy;
		}
		else if((x<mx) && (y>my)) { // final y < initial y (if bear is above the bullet)
			x += incx;
			y += incy;
		}
		*/
		
		//when bullet reaches the mouse pointer's position, adjust the bullet's position as it might go over a bit and then reset
		if(x >= mx) {
			x=mx;
			y=my;

		}

	}
	
	/* update the picture variable location */
	private void update(int mx, int my) {
		//if mouse button is clicked, call the move bullet method to move it towards the mouse position
		
		
		tx.setToTranslation(x, y);
		tx.scale(0.02, 0.02);
	}
	
	//scale bullet
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}
	


}
