package MED4_OOSE2015_MiniProjectGame;

public class Missile 
{
	private int x, y;
	private int MISSILE_SPEED = 2;
	private boolean visible;
	
	public Missile(int x, int y) 
	{
		visible = true;
		this.x = x;
		this.y = y;
	}
	
	public void move() 
	{
		x += MISSILE_SPEED;
		//Maybe instead of making it invisible we could remove it from the Array (destroy it)..?
//		if (x > Map.getMAP_WIDTH)  {   visible = false;   }
//		if (x > Map.getMAP_HEIGHT) {   visible = false;   }
	}
	
	
	public int getX()  {   return this.x;   }

	public int getY()  {   return this.y;   }
	
	public int getMISSILE_SPEED()           {   return this.MISSILE_SPEED;   }
	
	public void setMISSILE_SPEED(int s)     {   this.MISSILE_SPEED = s;      }

	public boolean isVisible()              {   return this.visible;         }

	public void setVisible(Boolean visible) {   this.visible = visible;      }
}
