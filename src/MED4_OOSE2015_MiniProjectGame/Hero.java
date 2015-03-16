package MED4_OOSE2015_MiniProjectGame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hero {

	private int x, y;                     //x and y position
	private int dx, dy;                   //x and y speed
	private int scale;                    //Hero(circle) size
	private boolean visible;              //Visibility of Hero. Dead or alive
//	private Image image;				  //for later use when implementing an image instead of a circle
	
	public Hero() 
	{
		this.scale = 20;
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

	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();

//		if (key == KeyEvent.VK_SPACE) {   fire();    }  maybe move in Wizard?

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
	
	
	public int getX()         {   return this.x;     }
	
	public void setX(int x)   {   this.x = x;        }

	public int getY()         {   return this.y;     }
	
	public void setY(int y)   {   this.y = y;        }

	public int getDX()        {   return this.dx;    }

	public void setDX(int dx) {   this.dx = dx;      }
	
	public int getDY()        {   return this.dy;    }

	public void setDY(int dy) {   this.dy = dy;      }
	
	public int getSCALE()           {   return this.scale;       }
	
	public void setSCALE(int scale) {   this.scale = scale;      }

	public boolean isVisible() 				{   return this.visible;    }

	public void setVisible(boolean visible) {   this.visible = visible; }

	
}
