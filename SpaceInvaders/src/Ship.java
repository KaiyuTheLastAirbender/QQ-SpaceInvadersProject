import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Ship{
	
	private Image ship;
	private AffineTransform tx;
	private int x,y;
	
	public Ship() {
		ship = getImage("/imgs/Ship.png");
	}
	
	
	public void changePicture(String newFileName) {
		ship = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(ship, tx, null);
		
		
		
		
	}
	//update the picture variable location
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(1, 1);
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
