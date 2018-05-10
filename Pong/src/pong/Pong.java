package pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong implements ActionListener, KeyListener{
	public static Pong pong;
	
	public int width = 700, height = 700; 
	
	public Renderer renderer;
	
	public Paddle player1;
	
	public Paddle player2;
	
	public Ball ball;
	
	public boolean bot = false;
	
	public boolean w, s, up, down;
	
	public int gameStatus = 0; //0 = stopped, 1 = paused, 2 = playing
	
	public Pong() {
		Timer timer = new Timer(20, this);
		JFrame jframe = new JFrame("Pong");
		
		renderer = new Renderer(); 
		
		jframe.setSize(width + 17, height);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(renderer);
		jframe.addKeyListener(this);
		
		
		timer.start();
	}
	
	public void start(){
		gameStatus = 2;
		player1 = new Paddle(this, 1);
		player2 = new Paddle(this, 2);
		ball = new Ball(this);
		player1.score = 0;
		player2.score = 0;
	}
	
	public void update() {
		if(w) {
			player1.move(true); //move up
		}
		if(s) {
			player1.move(false); //move down
		}
		
		if(!bot) {
			if(up) {
				player2.move(true); //move up
			}
			if(down) {
				player2.move(false); //move down
			}
		}
		else {
			if (player2.y < player2.gap) {
				player2.y = player2.gap;
			}
			
			else if (player2.y + player2.height + player2.gap > this.height + 5) {
				player2.y = this.height - (player2.height + player2.gap - 5);
//				int calc = player2.y + player2.height + player2.gap;
//				System.out.println("calc " + calc);
//				System.out.println("player2 " + player2.height);
			}
				
			else if (player2.y + (player2.height/2) < ball.y -(ball.y % player2.speed)+5) {
//					int calc = ball.y -(ball.y % player2.speed)+5;
//					System.out.println("Ball " + calc);
//					System.out.println("paddle " + player2.y);
					player2.y += player2.speed;
				}
			else if (player2.y > ball.y -(ball.y % player2.speed)+5) {
					player2.y -= player2.speed;
				}
		}
		
		
		ball.update(player1, player2);
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		//greatly improves simple graphics
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//text to screen
		if (gameStatus == 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Berlin Sans FB", 1, 100));
			g.drawString("Pong", width/2 - 135, 150);
			
			g.setFont(new Font("Berlin Sans FB", 1, 60));
			g.drawString("Press 1 for 1 player", width-620, 300);
			g.drawString("Press 2 for 2 player", width-630, 450);
			g.drawString("Press space to PAUSE", width-650, 600);
		}
		
		if (gameStatus == 1 || gameStatus == 2) {
			g.setColor(Color.white);
			g.setStroke(new BasicStroke(5));
			g.drawLine(width/2, 0, width/2, height);
			g.drawOval(width/2 - 75, height/2 -75, 150, 150);
			
			g.setColor(Color.lightGray);
			g.setFont(new Font("Berlin Sans FB", 1, 20));
			g.drawString("Score Player 1: " + player1.score, 100, 50);	
			g.drawString("Score Player 2: " + player2.score, width-300, 50);
			g.drawString("Paddle strokes " + Ball.collisionCount/2, width/2 - 69, 25);
			

			player1.render(g);
			player2.render(g);
			ball.render(g);
			
			
		}

		
		if (gameStatus == 1) {
			g.setColor(Color.BLUE);
			g.setFont(new Font("Berlin Sans FB", 1, 60));
			g.drawString("GAME PAUSED", width/2 - 210, 200);
			g.drawString("SPACE to resume", width/2 - 225, 400);
			g.drawString("Q to end", width/2 -150, 500);
		}
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//no update on a paused game
		if (gameStatus == 2) {
			update();		
		}
		
		renderer.repaint();		
	}
	
	public static void main(String[] args) {
		pong =new Pong();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		
		if(id == KeyEvent.VK_W) {
			w=true;
		}
		if(id == KeyEvent.VK_S) {
			s=true;
		}
		if(id == KeyEvent.VK_UP) {
			up=true;
		}
		if(id == KeyEvent.VK_DOWN) {
			down=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();
		
		if(id == KeyEvent.VK_W) {
			w=false;
		}
		if(id == KeyEvent.VK_S) {
			s=false;
		}
		if(id == KeyEvent.VK_UP) {
			up=false;
		}
		if(id == KeyEvent.VK_DOWN) {
			down=false;
		}
		
		if(id == KeyEvent.VK_1 && gameStatus == 0) {
			bot = true;
			start();
		}
		
		if(id == KeyEvent.VK_2 && gameStatus == 0) {
				start();
		}
		
		if(id == KeyEvent.VK_SPACE) {
			// game to pause
			if (gameStatus == 2) {
				gameStatus = 1;
			}
			//pause to resume
			else if (gameStatus == 1) {
				gameStatus = 2;
			}
		}
		
		if(id == KeyEvent.VK_Q) {
			if (gameStatus == 1){
				gameStatus = 0;
			}
				
		}
	}






	
	

}
