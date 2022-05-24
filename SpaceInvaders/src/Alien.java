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
	public int ax, ay, avx=0;
	public int w=45, h=45;
	private Rectangle hitbox;
	
	
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
		
		
//		while(true){
//			ax+=10;
//			ax-=10;
//		}
		
	}
	
	public boolean lost() {
		if(ay>350) {
			System.out.println("lost");
			return true;
		}
		return false;
	}
	
	
	public boolean collision(int x, int y) { //collides with bullet
		Rectangle bhitbox = new Rectangle(x,y,10,10);
		hitbox = new Rectangle(ax, ay,w,h);
		if(hitbox.intersects(bhitbox)) {
			ax=2000;
			return true;
		}
		return false;
	}
	
	
	public void moveLeft() {
		ay=0;
		for(int i=0; i<10;i++) {
			ax+=10;
		}
		tx.setToTranslation(ax,ay);
		tx.scale(3.0, 3.0);
	}
	
	public void moveRight() {
		ay=0;
		for(int i=0; i<10; i++) {
			ax-=10;
		}
		tx.setToTranslation(ax, ay);
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
