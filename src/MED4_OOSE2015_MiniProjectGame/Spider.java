package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class Spider extends Enemy {

	private Image spiderWest, spiderEast, spiderNorth, spiderSouth;
	private int speed;
	
	public Spider(SimpleSlickGame game, int x, int y) {
		super(game, x, y);
		// TODO Auto-generated constructor stub
		
		this.setHealth(15);
		
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
		
		try 
		{
			Image particleImg = new Image ("Graphics/Particles/web.png");
			particles = new ParticleSystem(particleImg,1500);
			
			File xmlFile = new File ("Graphics/Particles/web effect.xml");
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
		
		speed = 1;
	}
	
	public void move()
	{	
		for(Entity e:game.getEntities() )
		{
			if (e instanceof Hero)
			{
				
				if (this.getPositionX() < e.getPositionX() - 10) 
				{
					this.setPositionX(this.getPositionX() + speed);
					this.setSprite(spiderEast);
//					particles.setPosition(this.getPositionX(), this.getPositionY());
					speed = 1;
				}
				else if (this.getPositionX() > e.getPositionX() + 10) 
				{
					this.setPositionX(this.getPositionX() - speed);
					this.setSprite(spiderWest);
					
					speed = 1;
				}
				else if (this.getPositionY() < e.getPositionY()) 
				{
					this.setPositionY(this.getPositionY() + speed);
					this.setSprite(spiderSouth);
					speed = 5;
				}
				else if (this.getPositionY() > e.getPositionY()) 
				{
					this.setPositionY(this.getPositionY() - speed);
					this.setSprite(spiderNorth);
					speed = 5;
				}
				break;	
			}
		}
	}

}
