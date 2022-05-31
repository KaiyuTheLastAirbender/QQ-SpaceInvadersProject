import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

//COOL GAME pepppa approves
// loved by peppa
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener {
//	Duck d = new Duck();

	TimeCounter tic = new TimeCounter(900,500);
	
	int clock=10;
	int clockPosX=800; 
	int second;
	
	int score = 0, winscore=15;
	Background r = new Background();
	JFrame frame;
	JButton button;
	Ship s = new Ship(350, 350);
	Alien a = new Alien(200, 0, 10); // the alien will be less than 200x200 in size, preferably less than or equal to
									// 100x100
	int initx = 350, inity = 350;
	Bullet x = new Bullet(initx, inity); // when the bullet class is made, this will make sense
	boolean running = false;
	int level = 1;
	Alien[] alienArray = new Alien[15];
	
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public boolean lostGame = false;
	private int mx, my;
	
	Font font1 = new Font("Arial", Font.PLAIN, 70);	
	Timer timer;	
	String ddSecond, ddMinute;	
	DecimalFormat dFormat = new DecimalFormat("00");
	JLabel counterLabel;
	


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
		running = true;

	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		
		r.paint(g);
		
		
		tic.paint(g);
		
		g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(50f));
		g.drawString(" " + clock, clockPosX, 50);
		
		if(tic.y==550) {
			clock--;
		}
		
		if(clock<=0) {
			clock=0;
		}
		
		if(clock<=0) {
			g.setColor(Color.RED);
			g.setFont(new Font("Stencil", Font.PLAIN, 50));
			g.drawString("TIME'S UP", 320, 300);
			
			for(int i=0; i<15; i++) {
				alienArray[i].ay=-1000;
			}
		}
		
		
		
		s.paint(g);
//		x.paint(g); //bullet class is the x

		
//		for(int i=0; i<alienArray.length; i++) {
//			alienArray[i].moveLeft();
//			alienArray[i].moveRight();
//			
//		}
		
		if(a.moveLeft) {
			a.ax-=a.speed;
		}
		if(a.moveRight) {
			a.ax+=a.speed;
		}
	
		
		
		
		if (true || running && !lostGame && score<winscore) {
			for (int i = 0; i < 15; i++) {
				alienArray[i].paint(g);
					
				for(Bullet b : bullets) {
					if(alienArray[i].collision(b.x, b.y)){
						alienArray[i].ay = -1000;
						alienArray[i].update();
					}
				}
				if (alienArray[i].collision(x.x,x.y)) {
					x.x = initx;
					x.y = inity;
					score++;
					System.out.println("SCORE: " + score);
				}
				if (alienArray[i].lost()) {
					lostGame = true;
				}
//				g.setColor(Color.RED);
//				g.setFont(new Font("Stencil", Font.PLAIN, 50));
//				g.drawString("SCORE: " + score, 10, 500);
		
				
			}
			//x.paint(g,mx,my);

		}
		
//		else if (lostGame) {	
//			//you lost
//			g.setColor(Color.RED);
//			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
//			g.drawString("You Lost!", 300, 300);
//		}
		
		moveAliens();

//		if (running) { // make condition for whenever game is running -- when ship has not touched an
//						// alien or alien has not crossed the bottom of the screen
//		//	a.collision(x.x, x.y); // collision is a boolean method - if a bullet collides with an alien
//			for (int i = 0; i < 100 - level; i++) { // number will be an int with a TBD value. level will decrease the
//													// time by 1 iteration each time.
//				a.update();
//			}
////			a.moveDown();
//			level++;
//		}

		//paint any bullets
		for(Bullet b: bullets) {
			b.paint(g);
			System.out.println("paint "+b.x);
		}
		
		// end screen and death will be here

	}
	
//	public void moveAliens() {
//		for(int i=0; i<alienArray.length; i++) {
//			if(alienArray[i].moveLeft==true) {
//				alienArray[i].ax-=5;
//			}
//			if(alienArray[i].moveRight==true) {
//				alienArray[i].ax+=5;
//			}
//		
//		}
//	}
	
//	public void simpleTimer() {
//		
//		timer = new Timer(1000, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				second++;
//				
//				counterLabel.setText(""+ second);
//			}
//		});
//	}
//
//	
//	public void countdownTimer1() {
//		
//		timer = new Timer(1000, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				second--;
//				ddSecond = dFormat.format(second);
//				ddMinute = dFormat.format(minute);	
//				counterLabel.setText(ddMinute + ":" + ddSecond);
//				
//				if(second==-1) {
//					second = 59;
//					minute--;
//					ddSecond = dFormat.format(second);
//					ddMinute = dFormat.format(minute);	
//					counterLabel.setText(ddMinute + ":" + ddSecond);
//				}
//				if(minute==0 && second==0) {
//					timer.stop();
//				}
//			}
//		});	
//		
//	}		
	
	public void moveAliens() {
		for(int i=0; i<alienArray.length; i++) {
			if(alienArray[i].moveLeft) {
				alienArray[i].ax-=2;
				
			}
			
			if(alienArray[i].moveRight) {
				alienArray[i].ax+=2;
			}
			
			alienArray[i].ax += alienArray[i].speed;
			
			if(alienArray[i].ax>900) {
				for(int j=0; j<alienArray.length; j++) {
					a.ax=890;
					alienArray[j].moveLeft=true;
					alienArray[j].moveRight=false;
					//alienArray[j].ay+=50;
				}
			}
			
			if(alienArray[i].ax<20) {
				for(int j=0; j<alienArray.length; j++) {
					a.ax=30;
					alienArray[j].moveRight=true;
					alienArray[j].moveLeft=false;
					//alienArray[j].ay+=50;
				}
			}
		}
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
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		

		alienArray[0] = new Alien(200, 0, 5);
		alienArray[1] = new Alien(300, 0, 5);
		alienArray[2] = new Alien(400, 0, 5);
		alienArray[3] = new Alien(500, 0, 5);
		alienArray[4] = new Alien(600, 0, 5);
		alienArray[5] = new Alien(200, 100, 5);
		alienArray[6] = new Alien(300, 100, 5);
		alienArray[7] = new Alien(400, 100, 5);
		alienArray[8] = new Alien(500, 100, 5);
		alienArray[9] = new Alien(600, 100, 5);
		alienArray[10] = new Alien(200, 200, 5);
		alienArray[11] = new Alien(300, 200, 5);
		alienArray[12] = new Alien(400, 200, 5);
		alienArray[13] = new Alien(500, 200, 5);
		alienArray[14] = new Alien(600, 200, 5);

	}

	/*
	 * 87 = w 83 = s 65 = a 68 = d 37 = left 39 = right 38 = up 40 = down 32 = space
	 */

	public void setImage() {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		bullets.add(new Bullet(s.x+75, s.y+80));
		System.out.println("here");
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

	
	int interval = 2000;
	int reload = 50;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		interval++;
		if (running == true) {
		//	a.collision(Bullet.x, Bullet.y); // collision is in the alien method
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println(arg0.getKeyCode());
//
//		if (arg0.getKeyCode() == 37) {
//			s.left();
//		} else if (arg0.getKeyCode() == 39) {
//			s.right();
//		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		s.x = arg0.getX()-80;
		if(interval>reload) {
			bullets.add(new Bullet(s.x+80, s.y+80));
			interval = 0;
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		s.x = arg0.getX()-80;
	}

}