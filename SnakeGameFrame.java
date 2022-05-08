package snake;

import javax.swing.JFrame;

public class SnakeGameFrame extends JFrame{
    SnakeGameFrame(){
    	this.add(new SnakeGamePanel());
    	this.setTitle("Snake and apple");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	this.pack();
    	this.setVisible(true);
    	this.setLocationRelativeTo(null);
    }
}
