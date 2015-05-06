package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;

import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class Entity 
{
	protected final SimpleSlickGame game;
	private int positionX, positionY;
	private int hitboxRadius;
	private org.newdawn.slick.Image sprite;
	protected ParticleSystem particles;
	protected ConfigurableEmitter emitter;
	
	protected boolean isAlive = true;
	
	Entity(SimpleSlickGame game, int xPos, int yPos)
	{
		this.game = game;
		positionX = xPos;
		positionY = yPos;
	}
	
	public void particleUpdate()
	{
	}
	
	public void close()
	{
	}
	
	public void move()
	{
	}
	
	public void spriteSwitch()
	{	
	}
	
	public void Collision(Entity e)
	{
		
	}
	
	public void renderParticles()
	{
		
	}
	
	public boolean collides (Entity other)
	{
		double distance = Math.sqrt(Math.pow(this.getPositionX() - other.getPositionX(), 2) + Math.pow(this.getPositionY() - other.getPositionY(), 2));
		int collisionDistance = this.getHitboxRadius() + other.getHitboxRadius();

		return distance <= collisionDistance;
	}
	
	//returned a bool before
	public boolean collides (ArrayList<Entity> others)
	{
		boolean col = false;
		for(Entity o:others)
		{
			if(this != o && this.collides(o) && o.collides(this))
			{				
				this.Collision(o);
				col = true;
			}
		}
		return col;
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

	public org.newdawn.slick.Image getSprite() {
		return sprite;
	}

	public void setSprite(org.newdawn.slick.Image sprite) {
		this.sprite = sprite;
	}
}
