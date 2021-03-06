import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ship{
	
	private Image ship;
	private AffineTransform tx;
	public int x, y, vx=0;
	
	public Ship(int x, int y) {
		
		this.x=x;
		this.y=y;
		
		ship = getImage("/imgs/Ship.png");
		
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 
	}
	
	
//	public void left() {
//		vx=-1;
//		x+=vx;
//	}
//	
//	public void right() {
//		vx=1;
//		x+=vx;
//	}
//	
	public void changePicture(String newFileName) {
		ship = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(ship, tx, null);
		
		left();
		
		right();
		
		if(x<=-50) {
			x=-50;
		}
		if(x>=768) {
			x=768;
		}
		
		
	}
	//update the picture variable location
	private void update() {
		
		x+=vx;
		
		tx.setToTranslation(x, y);
		tx.scale(1, 1);
	}
	
	public void left() {
		x-=10;
		y=400;
		tx.setToTranslation(x, y);
		tx.scale(3.0, 3.0);
		
	}
	
	public void right() {
		x+=10;
		y=400;
		tx.setToTranslation(x, y);
		tx.scale(3.0, 3.0);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(3.0, 3.0);
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
}
