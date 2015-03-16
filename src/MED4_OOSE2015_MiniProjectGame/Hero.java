package MED4_OOSE2015_MiniProjectGame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hero {

	private int x, y;                     //x and y position
	private int dx, dy;                   //x and y speed
	private int scale;                    //Hero(circle) size
	private boolean visible;              //Visibility of Hero. Dead or alive
	private ArrayList<Missile> missiles;  //current missiles 'alive'
//	private Image image;				  //for later use when implementing an image instead of a circle
	
	public Hero() 
	{
		this.scale = 20;
		this.missiles = new ArrayList<Missile>();
		this.visible = true;
		this.x = 40;
		this.y = 60;
	}
	
	public void move() 
	{
		x += dx;
		y += dy;
		if (x < 1) {   x = 1;   }
		if (y < 1) {   y = 1;   }
	}
	
	public void fire() 
	{
		missiles.add(new Missile(x + scale, y + scale));
	}

	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {   fire();    }

		if (key == KeyEvent.VK_LEFT)  {   dx = -1;   }

		if (key == KeyEvent.VK_RIGHT) {   dx = 1;    }

		if (key == KeyEvent.VK_UP)    {   dy = -1;   }

		if (key == KeyEvent.VK_DOWN)  {   dy = 1;    }
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)  {   dx = 0;   }
		
		if (key == KeyEvent.VK_RIGHT) {   dx = 0;   }

		if (key == KeyEvent.VK_UP)    {   dy = 0;   }

		if (key == KeyEvent.VK_DOWN)  {   dy = 0;   }
	}
	
	
	public int getX()  {   return x;   }

	public int getY()  {   return y;   }
	
	public int getDX() {   return dx;   }

	public int getDY() {   return dy;   }
	
	public ArrayList<Missile> getMissiles() {   return this.missiles;   }

	public boolean isVisible() 				{   return this.visible;    }
	
	public void setVisible(boolean visible) {   this.visible = visible; }

	
}
