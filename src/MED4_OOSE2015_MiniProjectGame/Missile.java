package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

//The missile class extends the Entity class
public class Missile extends Entity
{
	//sets variables for the missile entity
	private Entity owner;
	private int destX, destY;
	private int startX, startY;
	private float speed;
	private float dx, dy;
	private Image missileImg;
	Point location = new Point(0,0);
	
	//The constructor initialises the missile with essential variables.
	public Missile(SimpleSlickGame _game, int x, int y, int destX, int destY, Entity owner) 
	{
		//the super constructor is called to give the x and y coordinates and call the game to use its variable and 
		super(_game, x,y);
		setHitboxRadius(20); 
		this.startX = x;
		this.startY = y;
		this.owner = owner;
		this.destX = destX;
		this.destY = destY;
		setLocation(startX, startY);
		recalculateVector(destX, destY);
		
		//Initializes and adds a particle system to the entity
		try 
		{
			Image particleImg = new Image ("Graphics/Particles/particle.png");
			particles = new ParticleSystem(particleImg,150);
			
			File xmlFile = new File ("Graphics/Particles/fire effect.xml");
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
		
		//Adds sound files to the entity
		try
		{
			Sound sound = new Sound("Sounds/lazer.wav");
			float pitch = ((float)game.r.nextInt(200) + 800)/1000;
			sound.play(pitch,1f);
		} catch (SlickException e){
			System.out.println("Could not find sound file");
		}
		
		//Adds sprites to the entity
		try
		{
			missileImg = new Image("Graphics/Fireball.png");
			this.setSprite(missileImg);
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not find sprite");
		}
	}
	
	//Recalculates the vector to the destination (where the user clicks) with a certain speed.
	public void recalculateVector(int destX, int destY)
    {
       float rad = (float)(Math.atan2(destX - startX, startY - destY));
       speed = 5;
       
       this.dx =  (float) Math.sin(rad) * speed;
       this.dy = -(float) Math.cos(rad) * speed;
    }
	
	//calls the recalculateVector(int destX, int destY) 
	public void recalculateVector()
    {
       recalculateVector(destX, destY);
    }
	
	//Overrides the collides() function from entity parent class to return true only if the entity collided is not a Hero or missile.
	@Override
	public boolean collides (Entity other)
	{
		if (other == owner || other instanceof Missile)
			return false;
		else
			return super.collides(other);
	}
	
	//Overrides the collision() of the Entity parent class to set isAlive to false when colliding.
	@Override
	public void Collision(Entity e)
	{
		this.isAlive = false;
	}
	
	//Updates particles
	public void particleUpdate()
	{
        particles.update(1);
	}
	
	//Renders particles
	@Override
	public void renderParticles()
	{
		particles.render();
	}
	
	
	//Move function controls the movement of the missile
	@Override
	public void move()
	{
		//Updates the X and Y position by using dx and dy
        float x = location.getX() + dx;
        float y = location.getY() + dy;
        
        //Updates the particle emitter's position to the missiles current position
        emitter.setPosition(this.getPositionX(), this.getPositionY(),false);
        
        //Updates the location of the missile
        setLocation(x, y);
	}
	
	//Returns the location vector of the missile
	public Vector2f getLocation() {
		return location.getLocation();
	}
	//sets the location vector of the missile using the positionX and positionY variables.
	public void setLocation(float positionX, float positionY) {
		this.setPositionX((int)positionX);
		this.setPositionY((int)positionY);
		location.setLocation(positionX, positionY);
	}
}

