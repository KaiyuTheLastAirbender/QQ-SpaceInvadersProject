import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
//	Duck d = new Duck();
	
	int score =0;
	Background b = new Background();
	JFrame frame;
	JButton button;
	Ship s = new Ship(350,350);
	Alien a = new Alien(200,0); // the alien will be less than 200x200 in size, preferably less than or equal to 100x100
	Bullet x = new Bullet(350,350); //when the bullet class is made, this will make sense
	boolean running = false;
	int level = 1;
	Alien[]alienArray = new Alien[15];
	public boolean lostGame = false;
	
//	Ghostthing w = new Ghostthing();
//	//Crosshair CH = new Crosshair();
	
	
//	public void paint(Graphics g) {
//		super.paintComponent(g);
//		bg.paint(g);
//		w.paint(g);
//		d.paint(g);
//		//CH.paint(g);
//	}
//	
	
	
	public void startGame() {
		running=true;
		
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		b.paint(g);
		s.paint(g);
		x.paint(g); //bullet class is the x
		
		if(running) {
			for(int i=0; i<15; i++) {
				alienArray[i].paint(g);
			
				if(alienArray[i].collision(Bullet.x,Bullet.y)) {
					Bullet.x=s.x;
					Bullet.y=s.y;
					score++;
					System.out.println("SCORE: " + score);
				}
				if(alienArray[i].lost()) {
					lostGame=true;
				
				}	
			}
			
		}
		

		if(running) { // make condition for whenever game is running -- when ship has not touched an alien or alien has not crossed the bottom of the screen
			a.collision(Bullet.x,Bullet.y); //collision is a boolean method - if a bullet collides with an alien
			for(int i=0; i<100-level; i++) { //number will be an int with a TBD value. level will decrease the time by 1 iteration each time.
				a.moveLeft();
				a.moveRight();
			}
			a.moveDown();
			level++;
		}
	
		
		//end screen and death will be here
        
	}
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Space Invaders");
		f.setSize(new Dimension(900, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(true);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		alienArray[0]= new Alien(200,0);
		alienArray[1]= new Alien(300,0);
		alienArray[2]= new Alien(400,0);
		alienArray[3]= new Alien(500,0);
		alienArray[4]= new Alien(600,0);
		alienArray[5]= new Alien(200,100);
		alienArray[6]= new Alien(300,100);
		alienArray[7]= new Alien(400,100);
		alienArray[8]= new Alien(500,100);
		alienArray[9]= new Alien(600,100);
		alienArray[10]= new Alien(200,200);
		alienArray[11]= new Alien(300,200);
		alienArray[12]= new Alien(400,200);
		alienArray[13]= new Alien(500,200);
		alienArray[14]= new Alien(600,200);
		
		
	}
	
/*87 = w
83 = s
65 = a
68 = d
37 = left
39 = right
38 = up
40 = down
32 = space
*/
	
	public void setImage() {
		
		
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(running = true) {
			a.collision(Bullet.x,Bullet.y); //collision is in the alien method
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		
		if(arg0.getKeyCode()==37) {
			s.left();
		}
		else if(arg0.getKeyCode()==39) {
			s.right();
		}
	}
	
	
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}