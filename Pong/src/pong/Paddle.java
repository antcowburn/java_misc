package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {

		public int paddleNumb;
		
		public int x, y, width = 50, height = 250;
		
		public int score = 0;
		
		public int speed = 10;
		
		public int gap = 35; // top and bottom gap - stages of 5 make the game run smoother
		
		public Paddle(Pong pong, int paddleNumb) {
			this.paddleNumb = paddleNumb;
			
			if (paddleNumb == 1) {
				this.x = 5;
			
			}
			
			if (paddleNumb == 2) {
				this.x = pong.width - (width + 5);
				
			}
			
			this.y = pong.height/2 - this.height/2;
			
		}

		public void render(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
			
		}

//		Movement here. Nice
		public void move(boolean up) {
			
			
			if (up) {
				if (y-gap-speed > 0) {
					y -= speed;
				}
				else {
					y = gap;
				}
			}
			else {
				if (y+height+speed+gap < Pong.pong.height) {
					y += speed;
				}
				else {
					y= Pong.pong.height-height-gap;
				}
			}
		}

}
