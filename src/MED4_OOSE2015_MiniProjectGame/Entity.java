package MED4_OOSE2015_MiniProjectGame;

import java.awt.List;
import java.util.ArrayList;

public class Entity 
{
	private int positionX, positionY;
	private int speedX, speedY;
	private int hitboxRadius;
	
	Entity()
	{
		
	}
	
	
	public boolean collides (Entity other)
	{
		double distance = Math.sqrt(Math.pow(this.getPositionX() - other.getPositionX(), 2) + Math.pow(this.getPositionY() - other.getPositionY(), 2));
		int collisionDistance = this.getHitboxRadius() + other.getHitboxRadius();

		return distance < collisionDistance;
	}
	
	public boolean collides(ArrayList<Entity> others)
	{
		for(Entity o:others)
		{
			if(this != o && this.collides(o))
			{
				return true;
			}			
		}
		return false;
	}


	
	public int getHitboxRadius() {   return hitboxRadius;   }


	public void setHitboxRadius(int hitboxRadius) {
		this.hitboxRadius = hitboxRadius;
	}


	public int getPositionX() {
		return positionX;
	}


	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}


	public int getPositionY() {
		return positionY;
	}


	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	
	
	
	
}
