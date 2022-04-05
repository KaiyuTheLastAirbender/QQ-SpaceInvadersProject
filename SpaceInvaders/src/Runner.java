import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


	public class Runner extends JPanel implements ActionListener{
	
		static final int SCREEN_WIDTH = 600; 
		static final int SCREEN_HEIGHT = 600;
		static final int UNIT_SIZE = 50; //grid size
		static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
		static final int DELAY = 200; //movement speed
		final int x[] = new int[GAME_UNITS];
		final int y[] = new int[GAME_UNITS];
		boolean running = false;
		Timer timer;
		Random random;

	Runner() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		

	
	
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void keyReleased(KeyEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void keyTyped(KeyEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
		
		
	

	}

}
