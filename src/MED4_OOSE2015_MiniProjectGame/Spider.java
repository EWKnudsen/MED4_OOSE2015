package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

//The Spider class extends the Enemy class
public class Spider extends Enemy {

	//Spider class variables initialized
	private Image spiderWest, spiderEast, spiderNorth, spiderSouth;
	private int speed;
	
	/**
	 * The constructor sets the X and Y position of the entity and refers to the slick game in to reach its variables and functions
	 * @param game
	 * @param x
	 * @param y
	 */
	public Spider(SimpleSlickGame game, int x, int y) {
		//The super constructor (the Entity constructor) is called to give the x and y position and refer to game.
		super(game, x, y);
		
		//The spider health is set to 15
		this.setHealth(15);
		
		//Adds sprites to the entity
		try 
		{
			spiderEast = new Image("Graphics/Spider east.png");
			spiderWest = new Image("Graphics/Spider west.png");
			spiderNorth = new Image("Graphics/Spider north.png");
			spiderSouth = new Image("Graphics/Spider south.png");
			this.setSprite(spiderNorth);
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not find Spider sprite");
		}
		
		//Initializes and adds a particle system to the entity
		try 
		{
			Image particleImg = new Image ("src/Graphics/web.png");
			particles = new ParticleSystem(particleImg,1500);
			
			File xmlFile = new File ("src/Graphics/web effect.xml");
			emitter = ParticleIO.loadEmitter(xmlFile);
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
		
		//set speed to an initial 1
		speed = 1;
	}
	
	/**
	 * The move() function controls the spider movement
	 */
	public void move()
	{	
		for(Entity e:game.getEntities() )
		{
			//checks if entity is Hero
			if (e instanceof Hero)
			{
				
				//if and else if statements checks where the Hero is
				if (this.getPositionX() < e.getPositionX() - 10) 
				{
					//move Spider
					this.setPositionX(this.getPositionX() + speed);
					//set new sprite
					this.setSprite(spiderEast);
					//Set new speed
					speed = 1;
				}
				else if (this.getPositionX() > e.getPositionX() + 10) 
				{
					//move Spider
					this.setPositionX(this.getPositionX() - speed);
					//set new sprite
					this.setSprite(spiderWest);
					//Set new speed
					speed = 1;
				}
				else if (this.getPositionY() < e.getPositionY()) 
				{
					//move Spider
					this.setPositionY(this.getPositionY() + speed);
					//set new sprite
					this.setSprite(spiderSouth);
					//Set new speed
					speed = 5;
				}
				else if (this.getPositionY() > e.getPositionY()) 
				{
					//move Spider
					this.setPositionY(this.getPositionY() - speed);
					//set new sprite
					this.setSprite(spiderNorth);
					//Set new speed
					speed = 5;
				}
				break;	
			}
		}
	}
}
