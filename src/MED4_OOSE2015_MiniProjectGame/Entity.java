package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;

import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class Entity 
{
	//Variables for all entities and children classes of entities
	protected final SimpleSlickGame game;
	private int positionX, positionY;
	private int hitboxRadius;
	private org.newdawn.slick.Image sprite;
	protected ParticleSystem particles;
	protected ConfigurableEmitter emitter;
	
	//Boolean to check if entity is alive
	protected boolean isAlive = true;
	
	/**
	 * Constructor that sets the X and Y position of the entity and refers to the slick game in to reach its variables and functions.
	 * @param game
	 * @param xPos
	 * @param yPos
	 */
	Entity(SimpleSlickGame game, int xPos, int yPos)
	{
		this.game = game;
		positionX = xPos;
		positionY = yPos;
	}
	
	/**
	 * particleUpdate() is used in children classes to update particles.
	 */
	public void particleUpdate()
	{
	}
	
	/**
	 * close() is used to close the entities so the key and mouse listeners is no longer working with them. (Still not functional)
	 */
	public void close()
	{
	}
	
	/**
	 * move() is used by children classes to control the movement of entities
	 */
	public void move()
	{
	}
	
	/**
	 * spriteSwitch() is used by children classes to switch between their sprites when needed
	 */
	public void spriteSwitch()
	{	
	}
	
	/**
	 * Collision() is used to generate an enitity specific action if this entity collides with another specific entity
	 * @param e
	 */
	public void Collision(Entity e)
	{
	}
	
	/**
	 * renderParticles is used by children classes to render their particle effects
	 */
	public void renderParticles()
	{
	}
	
	/**
	 * collides(Entity other) is used to check whether two entities collide or not. It returns a boolean to determine this.
	 * @param other
	 * @return
	 */
	public boolean collides (Entity other)
	{
		double distance = Math.sqrt(Math.pow(this.getPositionX() - other.getPositionX(), 2) + Math.pow(this.getPositionY() - other.getPositionY(), 2));
		int collisionDistance = this.getHitboxRadius() + other.getHitboxRadius();

		return distance <= collisionDistance;
	}
	
	/**
	 * collides(ArrayList<Entity> others) goes through all entities and determines if they collide. Returns a boolean.
	 * @param others
	 * @return
	 */
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

	/**
	 * function to get the hitbox radius of the entity
	 * @return
	 */
	public int getHitboxRadius() {
		return hitboxRadius;
	}
	
	/*
	 * function to set the hitbox radius of the entity
	 */
	public void setHitboxRadius(int hitboxRadius) {
		this.hitboxRadius = hitboxRadius;
	}

	/**
	 * function to get the X position of the entity
	 * @return
	 */
	public int getPositionX() {
		return positionX;
	}
	
	/**
	 * function to set the X position of the entity
	 * @param positionX
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * function to get the Y position of the entity
	 * @return
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * function to set the Y position of the entity
	 * @param positionY
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	/**
	 * function to get the sprite of the entity
	 * @return
	 */
	public org.newdawn.slick.Image getSprite() {
		return sprite;
	}

	/**
	 * function to set the sprite of the entity
	 * @param sprite
	 */
	public void setSprite(org.newdawn.slick.Image sprite) {
		this.sprite = sprite;
	}
}
