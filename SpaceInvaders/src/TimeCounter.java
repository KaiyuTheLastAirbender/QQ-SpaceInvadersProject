import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;



public class TimeCounter {

	public int x,y;
	
	public double vy=0;
	private AffineTransform tx;
	
	public TimeCounter(int x, int y) {
		this.x=x;
		this.y=y;
		
		tx=AffineTransform.getTranslateInstance(x, y);
		init(x,y);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		update();
		
		g.setColor(Color.white);
		g.drawRect(x, y, 50, 50);
	}
	
	public void update() {
		vy=2.5;
		y+=vy;
		if(y>600) {
			y=500;
		}
		
		tx.setToTranslation(x, y);
		tx.scale(3, 3);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a,b);
		tx.scale(.5, .5);
	}
	
	
	
}
