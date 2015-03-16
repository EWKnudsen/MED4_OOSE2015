package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;

public class Enemy 
{
	private int x, y;                     //x and y position
	private int dx, dy;                   //x and y speed
	private int scale;                    //Hero(circle) size
	private boolean visible;              //Visibility of Hero. Dead or alive
	private ArrayList<Missile> missiles;  //current missiles 'alive'
//	private Image image;				  //for later use when implementing an image instead of a circle
	
	public Enemy() 
	{
		this.scale = 10;
		this.missiles = new ArrayList<Missile>();
		this.visible = true;
		this.x = 200;
		this.y = 300;
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
	
	
	public int getX()  {   return x;   }

	public int getY()  {   return y;   }
	
	public int getDX() {   return dx;   }

	public int getDY() {   return dy;   }
	
	public ArrayList<Missile> getMissiles() {   return this.missiles;   }

	public boolean isVisible() 				{   return this.visible;    }
	
	public void setVisible(boolean visible) {   this.visible = visible; }
}
