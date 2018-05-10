package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
	
		private Pong pong;
		public int x, y, width = 25, height = 25;
		public int motionX, motionY;
		public Random random;
		public static int collisionCount = 0;
		
		
		public Ball(Pong pong){
			this.random = new Random();

			spawn(pong);
		}
		 
		public void update(Paddle paddle1, Paddle paddle2) {
			int speed = 2;
			
			this.x += motionX * speed;
			this.y += motionY * speed;
			
			//top screen collide
			
			if (this.y < 0) {
				this.motionY=random.nextInt(4);
				
			}
			//bottom screen collide
			if(this.y + this.height > pong.height){
				this.motionY= -random.nextInt(4);
				
			}
			
			
			
			if(checkCollision(paddle1) == 1) {
				this.motionX = 1 + (collisionCount/3);
				this.motionY = -2 + random.nextInt(4);
				//ensure ball does not stay still after collision
				if (motionY == 0) {
					motionY = 1;
					
				}
			}
			else if(checkCollision(paddle2) == 1) {
				this.motionX = -1 - (collisionCount/3);
				this.motionY = -2 + random.nextInt(4);
				//ensure the ball does not stay still after collision
				if (motionY == 0) {
					motionY = 1;
					
				}
			}
			
			if(checkCollision(paddle1) == 2) {
				paddle2.score ++;
				spawn(pong);
			}
			else if(checkCollision(paddle2) == 2) {
				paddle1.score ++;
				spawn(pong);
			}
		}
		
		public void spawn(Pong pong) {
			collisionCount = 0;
			
			this.x = pong.width/2 +5;
			this.y = pong.height/2 +5;
			this.pong = pong;
			this.motionY = -2 + random.nextInt(4);

			//	get the ball moving
			if (motionY == 0) {
				motionY = 1;
			}
			
			if (random.nextBoolean()) {
				motionX = 1;
			}
			else {
				motionX = -1;
			}
		}
		
		public int checkCollision(Paddle paddle) {
					
			if (this.x < paddle.x + paddle.width && this.x + this.width > paddle.x && this.y < paddle.y + paddle.height && this.y + this.height > paddle.y)  {
							
				collisionCount +=1;
				return 1; //collide;
				}
			else if ((paddle.paddleNumb == 1 && paddle.x > x) || (paddle.x < x - paddle.width + width && paddle.paddleNumb == 2)){
				
				return 2; //wall					
				}
			else {
			return 0; //air
			}
		}

		
		public void render (Graphics g) {
			g.setColor(Color.yellow);
			g.fillOval(x,y,width,height);
		}
}
