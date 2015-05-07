package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

//Enemy class extends the Character class
public class Enemy extends Character
{

	//initialises variables for the class
	private Image enemyFrontLeft, enemyFrontRight, enemyBack;
	private int speed;
	
	/**
	 * The constructor sets the X and Y position of the entity and refers to the slick game in to reach its variables and functions
	 * @param game
	 * @param x
	 * @param y
	 */
	public Enemy(SimpleSlickGame game, int x, int y) 
	{
		//The super constructor (the Entity constructor) is called to give the x and y position and refer to game.
		super(game, x, y);
		
		//the enemy health is set to a default 30
		this.setHealth(30);
		
		//Initializes and adds a particle system to the entity
		try 
		{
			enemyFrontLeft = new Image("Graphics/Melee enemy front left.png");
			enemyFrontRight = new Image("Graphics/Melee enemy front right.png");
			enemyBack = new Image("Graphics/Melee enemy back.png");
			this.setSprite(enemyFrontLeft);
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not find sprite");
		}
		
		//Adds sprites to the entity
		try 
		{
			Image particleImg = new Image ("Particles/particle.png");
			particles = new ParticleSystem(particleImg,1500);
			

		//	File xmlFile = new File ("Particles/blood effect.xml");
		//	emitter = ParticleIO.loadEmitter(xmlFile);
			emitter = ParticleIO.loadEmitter("Particles/blood effect.xml");
			emitter.setPosition(this.getPositionX(), this.getPositionY(),false);
			
			particles.addEmitter(emitter);
			particles.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
			
		} catch (SlickException e1) {
			System.out.println("cannot find xml file / particle image");
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("Cannot assign xml file to emitter. File might be missing.");
			e.printStackTrace();
		}
		
		//Adds sound files to the entity
		try 
		{
			Sound sound = new Sound("Sounds/zombie.wav");
			float pitch = ((float)game.r.nextInt(200) + 800)/1000;
			sound.play(pitch, 1f);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * updates the particle system for the entity
	 */
	public void particleUpdate()
	{
		particles.update(1);
	}
	
	/**
	 * Overrides the collision() of the Entity parent class to set isAlive to false when colliding with a missile entity.
	 * @param Entity e
	 */
	@Override
	public void Collision(Entity e)
	{
		if (e instanceof Hero || e instanceof Missile)
		{
			isAlive = false;
		}
	}
	
	/**
	 * Controls the movement of the Enemy class
	 */
	public void move()
	{
		speed = 1;
		
		for(Entity e:game.getEntities() )
		{
			//checks if entity is a Hero
			if (e instanceof Hero)
			{
				//if and else if statements checks where the Hero is acrodding to the Enemy
				if (this.getPositionX() < e.getPositionX()) 
				{
					//moves enemy
					this.setPositionX(this.getPositionX() + speed);
					//sets new sprite
					this.setSprite(enemyFrontRight);
				}
				else if (this.getPositionX() > e.getPositionX()) 
				{
					//moves enemy
					this.setPositionX(this.getPositionX() - speed);
					//sets new sprite
					this.setSprite(enemyFrontLeft);
				}
				if (this.getPositionY() < e.getPositionY()) 
				{
					//moves enemy
					this.setPositionY(this.getPositionY() + speed);
				}
				else if (this.getPositionY() > e.getPositionY()) 
				{
					this.setPositionY(this.getPositionY() - speed);
					//sets new sprite
					this.setSprite(enemyBack);
				}
				break;
			}
		}
		//Updates the particle emitter's position to the enemy position
		emitter.setPosition(this.getPositionX(), this.getPositionY(),false);
	}
}
