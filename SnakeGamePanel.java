package snake;

import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.*;
import java.util.Random;


public class SnakeGamePanel extends JPanel implements ActionListener{
		static final int screenWidth=600;
		static final int screenHeight=600;
		static final int unitSize=25;
		static final int GameUnits=(screenHeight*screenWidth)/unitSize;
		static final int Delay=75;
		final int x[]=new int[GameUnits];
		final int y[]=new int[GameUnits];
		int bodyParts=6;
		int appleEaten;
		int appleX;
		int appleY;
		char direction='R';
		boolean running =false;
		Timer timer;
		Random random;
	SnakeGamePanel(){
		random=new Random();
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new mykeyAdapter());
		startGame();
		//System.out.println(GameUnits);
	
	}
	public void startGame() {
		new_Apple();
		running=true;
		timer=new Timer(Delay,this);
		timer.start();
		
	}
	public void new_Apple() {
		appleX=random.nextInt((int)(screenWidth/unitSize))*unitSize;
		appleY=random.nextInt((int)screenHeight/unitSize)*unitSize;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		
	}
	public void draw(Graphics g) {
		if(running) {
		for(int i=0;i<screenHeight;i++) {
			
			g.drawLine(i*unitSize,0,i*unitSize, screenHeight);
			g.drawLine(0,i*unitSize,screenWidth,i*unitSize);
		}		
		g.setColor(Color.red);
		g.fillOval(appleX, appleY, unitSize, unitSize);
			
		for(int i=0;i<bodyParts;i++) {
				if(i==0) {
					g.setColor(Color.green);
					g.fillOval(x[i], y[i], unitSize, unitSize);
				//	System.out.println(x[i]);
					
				}else {
					g.setColor(Color.green);
					g.fillOval(x[i], y[i], unitSize, unitSize);
				}
			}
		}else {
			gameOver(g);
		}
		
		
	}
	public void move() {
		for(int i=bodyParts;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0]=y[0]-unitSize;
			break;
		case 'D':
			y[0]=y[0]+unitSize;
			break;
		case 'L':
			x[0]=x[0]-unitSize;
			break;
		case 'R':
			x[0]=x[0]+unitSize;
			
		}
	}
	public void checkApple(){
		if(x[0]==appleX&&y[0]==appleY) {
			bodyParts++;
			appleEaten++;
			new_Apple();
		}
	}
	public void checkCollision() {
		for(int i=bodyParts;i>0;i--) {
			if((x[0]==x[i])&&(y[0]==y[i])){
				running=false;
			}
		}
		if(x[0]<0) {
			running=false;
		}
		if(x[0]>screenWidth) {
			running=false;
		}
		if(y[0]>screenHeight) {
			running=false;
		}
		if(x[0]<0) {
			running=false;
		}
		if(!running) {
			timer.stop();
		}
		
	}
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics=getFontMetrics(g.getFont());
		g.drawString("GameOver",100,100);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(running) {
			move();
			checkApple();
			checkCollision();
			
		}
		repaint();
		
	}
	public class mykeyAdapter extends KeyAdapter{
			
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					if(direction!='R') {
						direction='L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(direction!='L') {
						direction='R';
					}
					break;
				case KeyEvent.VK_UP:
					if(direction!='D') {
						direction='U';
					}
					break;
				case KeyEvent.VK_DOWN:
					if(direction!='U') {
						direction='D';
					}
					break;
			}
		}
	}
	
}
