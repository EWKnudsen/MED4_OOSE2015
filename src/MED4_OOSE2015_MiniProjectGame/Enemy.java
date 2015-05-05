package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class Enemy extends Character
{
	private Image enemyFrontLeft, enemyFrontRight, enemyBack;
	private int speed;
	
	public Enemy(SimpleSlickGame game, int x, int y) 
	{
		super(game, x, y);
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
		
		try 
		{
			Image particleImg = new Image ("Graphics/Particles/particle.png");
			particles = new ParticleSystem(particleImg,1500);
			
			File xmlFile = new File ("Graphics/Particles/blood effect.xml");
			emitter = ParticleIO.loadEmitter(xmlFile);
			
			emitter.setPosition(this.getPositionX(), this.getPositionY(),false);
			particles.addEmitter(emitter);
			
		} catch (SlickException e1) {
			System.out.println("cannot find xml file / particle image");
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("Cannot assign xml file to emitter. File might be missing.");
			e.printStackTrace();
		}
		
		particles.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
		
		this.setHealth(30);
	}
	
	public void particleUpdate()
	{
		particles.update(1);
	}
	
	public void move()
	{
		speed = 1;
		
		for(Entity e:game.entities )
		{
			if (e instanceof Hero)
			{
				if (this.getPositionX() < e.getPositionX()) 
				{
					this.setPositionX(this.getPositionX() + speed);
					this.setSprite(enemyFrontRight);
				}
				else if (this.getPositionX() > e.getPositionX()) 
				{
					this.setPositionX(this.getPositionX() - speed);
					this.setSprite(enemyFrontLeft);
				}
				if (this.getPositionY() < e.getPositionY()) 
				{
					this.setPositionY(this.getPositionY() + speed);
				}
				else if (this.getPositionY() > e.getPositionY()) 
				{
					this.setPositionY(this.getPositionY() - speed);
					this.setSprite(enemyBack);
				}
				break;
			}
		}
		emitter.setPosition(this.getPositionX(), this.getPositionY(),false);
	}
}
