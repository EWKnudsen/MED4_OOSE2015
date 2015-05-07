package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;


//abstract, meaning you can't create a instant of the class.
//(we only want to create instances of Warrior or Wizard)
//Hero extends character and implements KeyPressedListener and KeyReleasedListener
public abstract class Hero extends Character implements KeyPressedListener, KeyReleasedListener
{
	//variables in the Hero class is initialized
	protected Missile[] missiles;
	char lastChar;
	
	/**
	 * The constructor sets the X and Y position of the entity and refers to the slick game in to reach its variables and functions
	 * @param game
	 * @param x
	 * @param y
	 */
	public Hero(SimpleSlickGame game, int x, int y) 
	{
		//The super constructor (the Entity constructor) is called to give the x and y position and refer to game.
		super(game, x, y);
		
		//Adds key listeners to the Hero class
		game.addKeyPressedListener(this);
		game.addKeyReleasedListener(this);
		
		//Initializes and adds a particle system to the entity
		try 
		{
			Image particleImg = new Image ("Graphics/Particles/particle.png");
			particles = new ParticleSystem(particleImg,1500);
			
			File xmlFile = new File ("Graphics/Particles/ouch effect.xml");
			emitter = ParticleIO.loadEmitter(xmlFile);
			
			emitter.setPosition(this.getPositionX(), this.getPositionY(),false);
			//particles.addEmitter(emitter);
			particles.setRemoveCompletedEmitters(true);
			particles.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
			
		} catch (SlickException e1) {
			System.out.println("cannot find xml file / particle image");
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("Cannot assign xml file to emitter. File might be missing.");
			e.printStackTrace();
		}
	}

	/**
	 *  close() is used to close the entities so the key and mouse listeners is no longer working with them. (Still not functional)
	 */
	@Override
	public void close() {
		game.removeKeyPressedListener(this);
		game.removeKeyReleasedListener(this);
		super.close();
	}
	/**
	 * sets the lastChar variable to the key pressed
	 */
	public void keyPressed(int key, char c)	{
		lastChar = c;
	}
	
	/**
	 * Sets the lastChar variable to an empty string when key released
	 */
	public void keyReleased(int key, char c) {
		lastChar = ' ';
	}
	
	/**
	 * updates the particle system
	 */
	public void particleUpdate() {
		particles.update(1);
	}
	
	/**
	 * Checks if the hero is colliding with an entity. Checks if the entity is an Enemy and sets health according.
	 * @param Entity e
	 */
	@Override
	public void Collision(Entity e)
	{
		//calls the super.Collision with entity e to call parent collision functions first
		super.Collision(e);
		
		//checks if collided entity is of the Enemy class
		if (e instanceof Enemy)
		{
			//hero loses health
			this.setHealth(getHealth() - 20);
			System.out.println("lost 20 health current health: " + this.getHealth());
			
			//check if hero's health is 0 or below
			if(this.getHealth() <= 0)
			{
				System.out.println("Hero Died");
				//sets hero to dead
				this.isAlive = false;	
			}
		}
	}
	
	/**
	 * controls the movement of the character
	 */
	@Override
	public void move() 
	{
		//sets the last character pressed
		char pressed = lastChar;
		//with if and else if statements: checks if the last character pressed is any of the following
		if (pressed == 'a')
		{	
			//Keeps the hero from moving outside the screen
			if(this.getPositionX() <= 0)
			{	
			}
			else {
				//moves the hero
				this.setPositionX(this.getPositionX() - 3);	
			}
		}
		else if (pressed == 'd')
		{
			//Keeps the hero from moving outside the screen
			if(this.getPositionX() > game.appgc.getWidth())
			{
			}
			else {
				//moves the hero
				this.setPositionX(this.getPositionX() + 3);	
			}
		}
		else if (pressed == 'w')
		{
			//Keeps the hero from moving outside the screen
			if(this.getPositionY() <= 0)
			{
			}
			else {
				//moves the hero
				this.setPositionY(this.getPositionY() - 3);
			}
		}
		else if (pressed == 's')
		{
			//Keeps the hero from moving outside the screen
			if(this.getPositionY() > game.appgc.getHeight())
			{	
			}
			else{
				//moves the hero
				this.setPositionY(this.getPositionY() + 3);
			}
		}
		//sets the particle emitter's position to the hero's position
		emitter.setPosition(this.getPositionX(), this.getPositionY());
	}
}



