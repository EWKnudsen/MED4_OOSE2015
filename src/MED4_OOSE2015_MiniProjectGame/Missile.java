package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
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
	private ParticleSystem particles;
	private ConfigurableEmitter emitter;
	
	public Missile(SimpleSlickGame _game, int x, int y, int destX, int destY, Entity owner) 
	{
		super(_game, x,y);
		startX = x;
		startY = y;
		this.owner = owner;
		this.destX = destX;
		this.destY = destY;
		location.setLocation(startX, startY);
		recalculateVector(destX, destY);
		
		try {
			Image particleImg = new Image ("Graphics/Particles/particle.png");
			particles = new ParticleSystem(particleImg,100);
			
			File xmlFile = new File ("Graphics/Particles/fire effect.png");
			emitter = ParticleIO.loadEmitter(xmlFile);
			
			emitter.setPosition(this.getPositionX(), this.getPositionY());
			particles.addEmitter(emitter);
			
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			System.out.println("cannot find xml file / particle image");
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot assign xml file to emitter. File might be missing.");
			e.printStackTrace();
		}
		particles.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
		
		
		try {
			missileImg = new Image("Graphics/Fireball.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not find sprite");
		}
		this.setSprite(missileImg);
	}
	
	public void recalculateVector(int destX, int destY)
    {
       float rad = (float)(Math.atan2(destX - startX, startY - destY));
       speed = 2;
       
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
	
	public void update()
	{
        
        emitter.setPosition(this.getPositionX(), this.getPositionY());
        particles.update(1);
        particles.render();
	}
	
	@Override
	public void move()
	{
        float x = location.getX();
        float y = location.getY();
        
        x += dx;
        y += dy;
        
        setPositionX((int) x);
        setPositionY((int) y);
        
        location.setLocation(x, y);
        
	}
	
	public Vector2f getLocation() {
		return location.getLocation();
	}

	public void setLocation(float positionX, float positionY) {
		location.setLocation(positionX, positionY);
	}
}

