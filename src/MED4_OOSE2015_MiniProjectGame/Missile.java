package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.lwjgl.util.Timer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class Missile extends Entity
{
	private Entity owner;
	private int destX, destY;
	private int startX, startY;
	private float speed;
	private float dx, dy;
	private Image missileImg;
	Point location = new Point(0,0);

	
	public Missile(SimpleSlickGame _game, int x, int y, int destX, int destY, Entity owner) 
	{
		super(_game, x,y);
		setHitboxRadius(20); 
		this.startX = x;
		this.startY = y;
		this.owner = owner;
		this.destX = destX;
		this.destY = destY;
		setLocation(startX, startY);
		recalculateVector(destX, destY);
		
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
			
		try
		{
			Sound sound = new Sound("Sounds/lazer.wav");
			float pitch = ((float)game.r.nextInt(200) + 800)/1000;
			sound.play(pitch,1f);
		} catch (SlickException e){
			System.out.println("Could not find sound file");
		}
		
		try
		{
			missileImg = new Image("Graphics/Fireball.png");
			this.setSprite(missileImg);
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not find sprite");
		}
	}
	
	public void recalculateVector(int destX, int destY)
    {
       float rad = (float)(Math.atan2(destX - startX, startY - destY));
       speed = 10;
       
       this.dx = (float) Math.sin(rad) * speed;
       this.dy = -(float) Math.cos(rad) * speed;
    }
	
	public void recalculateVector()
    {
       recalculateVector(destX, destY);
    }
	
	@Override
	public boolean collides (Entity other)
	{
		if (other == owner)
			return false;
		else
			return super.collides(other);
	}
	
	@Override
	public void Collision(Entity e)
	{
		this.isAlive = false;
	}
	
	public void particleUpdate()
	{
        particles.update(1);
	}
	
	@Override
	public void renderParticles()
	{
		particles.render();
	}
	
	@Override
	public void move()
	{
        float x = location.getX() + dx;
        float y = location.getY() + dy;
        
        emitter.setPosition(this.getPositionX(), this.getPositionY(),false);
        
        setLocation(x, y);
	}
	
	public Vector2f getLocation() {
		return location.getLocation();
	}

	public void setLocation(float positionX, float positionY) {
		this.setPositionX((int)positionX);
		this.setPositionY((int)positionY);
		location.setLocation(positionX, positionY);
	}
}

