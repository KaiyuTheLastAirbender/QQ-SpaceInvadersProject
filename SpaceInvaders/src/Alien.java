import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Alien {

	private Image alien;
	private AffineTransform tx;
	public int ax, ay, avx=5, vx=10;
	public int w=45, h=45; 
	private Rectangle hitbox;
	public static boolean right = true;
	
	public Alien(int x, int y) {
		ax=x;
		ay=y;
		
		alien = getImage("imgs/Blue.png");
		
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 
		
		
		
	}
	
	public void changePicture(String newFileName) {
		alien = getImage(newFileName);
		init(ax, ay);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		g2.drawImage(alien, tx, null);
		g.drawRect(ax+80, ay+80, w, h); //hitbox needs to match what's drawn on the screen for aliens
		int count=100;
		if(ax>700 ) {
			right = false;
			vx = -10;
		}
		
		if(ax <= 10) {
			right = true;
			vx = 10;
		}
	//	update();
	/*	if(ax>0) {
			ax=1;
			update();
		}
		
		if(ax>500) {
			ax=499;
			vx*=-1;
		}*/
		
		
		
//		while(true){
//			ax+=10;
//			ax-=10;
//		}
		
	}
	
	
//	public void update{
//		
//	}
	
	public boolean lost() {
		if(ay>350) {
			System.out.println("lost");
			return true;
		}
		return false;
	}
	
	
	public boolean collision(int x, int y) { //collides with bullet
		
		Rectangle ahitbox = new Rectangle(x,y,10,10);
		hitbox = new Rectangle(ax, ay,w,h);
		if(hitbox.intersects(ahitbox)) {
			System.out.println("hit");
			ax=2000;
			return true;
		}
		return false;
	}
	
	
	public void update() {
		ax+=vx;
		tx.setToTranslation(ax,ay);
		tx.scale(3.0, 3.0);
	}
	
	
	public void moveDown() {
		for(int i=0; i<35;i++) {
			ay-=10;
		}
		tx.setToTranslation(ax, ay);
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
