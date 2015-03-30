package MED4_OOSE2015_MiniProjectGame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

//abstract, meaning you can't create a instant of the class.
//(we only want to create instances of Warrior or Wizard) 
public abstract class Hero 
{
	private int x, y;                     //x and y position
	private int dx, dy;                   //x and y speed
	protected int scale;                    //Hero(circle) size
	private boolean visible;              //Visibility of Hero. Dead or alive
	protected int health;
//	private Image image;				  //for later use when implementing an image instead of a circle
	
	public Hero(int x, int y) 
	{
		this.scale = 20;
		this.visible = true;
		this.x = x;
		this.y = y;
		this.health = 100;
	}
	
	public void move() 
	{
		x += dx;
		y += dy;
		if (x < 1-scale) {   x = 1+scale;   }
		if (y < 1-scale) {   y = 1+scale;   }
		if (x < 640-scale) {   x = 640-scale;   }
		if (y < 480-scale) {   y = 480-scale;   }
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
	
	public void setSCALE(int health){   this.scale = scale;      }
	
	public int getHEALTH()            {   return this.health;        }
	
	public void setHEALTH(int health) {   this.health = health;     }

	public boolean isVisible() 				{   return this.visible;    }

	public void setVisible(boolean visible) {   this.visible = visible; }
}
