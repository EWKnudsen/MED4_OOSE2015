package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;

public class Entity 
{
	protected final SimpleSlickGame game;
	private int positionX, positionY;
	private int speedX, speedY;
	private int hitboxRadius;
	private org.newdawn.slick.Image sprite;
	
	Entity(SimpleSlickGame game, int x, int y)
	{
		this.game = game;
		
		positionX = x;
		positionY = y;
	}
	
	public void close()
	{
		
	}
	
	public void move()
	{
		
	}
	
	public boolean collides (Entity other)
	{
		double distance = Math.sqrt(Math.pow(this.getPositionX() - other.getPositionX(), 2) + Math.pow(this.getPositionY() - other.getPositionY(), 2));
		int collisionDistance = this.getHitboxRadius() + other.getHitboxRadius();

		return distance <= collisionDistance;
	}
	
	public boolean collides(ArrayList<Entity> others)
	{
		for(Entity o:others)
		{
			if(this != o && this.collides(o) && o.collides(this))
			{
				return true;
			}			
		}
		return false;
	}

	
	public int getHitboxRadius() {
		return hitboxRadius;
	}

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

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public org.newdawn.slick.Image getSprite() {
		return sprite;
	}

	public void setSprite(org.newdawn.slick.Image sprite) {
		this.sprite = sprite;
	}
	
	
	
	
	
}
