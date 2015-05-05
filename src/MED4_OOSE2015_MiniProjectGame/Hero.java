package MED4_OOSE2015_MiniProjectGame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;


//abstract, meaning you can't create a instant of the class.
//(we only want to create instances of Warrior or Wizard) 
public abstract class Hero extends Character implements KeyPressedListener, KeyReleasedListener
{
	protected Missile[] missiles;
	char lastChar;
	
	public Hero(SimpleSlickGame game, int x, int y) 
	{
		super(game, x, y);
		
		game.addKeyPressedListener(this);
		game.addKeyReleasedListener(this);
		
//		try {
//			Image particleImg = new Image ("Graphics/Particles/particle.png");
//			particles = new ParticleSystem(particleImg,1500);
//			
//			File xmlFile = new File ("Graphics/Particles/blood effect.xml");
//			emitter = ParticleIO.loadEmitter(xmlFile);
//			
//			emitter.setPosition(this.getPositionX(), this.getPositionY(),false);
//			particles.addEmitter(emitter);
//			
//		} catch (SlickException e1) {
//			// TODO Auto-generated catch block
//			System.out.println("cannot find xml file / particle image");
//			e1.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Cannot assign xml file to emitter. File might be missing.");
//			e.printStackTrace();
//		}
//		
//		particles.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
		
	}

	@Override
	public void close()
	{
		game.removeKeyPressedListener(this);
		game.removeKeyReleasedListener(this);
	}
	
	public void keyPressed(int key, char c)
	{
		lastChar = c;
	}
	
	public void keyReleased(int key, char c)
	{
		lastChar = ' ';
	}
	
	@Override
	public void move() 
	{
		char pressed = lastChar;
		if (pressed == 'a')
		{	
			if(this.getPositionX() <= 0)
			{
				
			}else{
				this.setPositionX(this.getPositionX() - 3);	
			}
		}
		else if (pressed == 'd')
		{
			if(this.getPositionX() > game.mapWidth)
			{
				
			}else{
				this.setPositionX(this.getPositionX() + 3);	
			}
		}
		else if (pressed == 'w')
		{
			if(this.getPositionY() <= 0)
			{

			}else{
				this.setPositionY(this.getPositionY() - 3);
			}
		}
		else if (pressed == 's')
		{
			if(this.getPositionY() > game.mapHeight)
			{
				
			}else{
				this.setPositionY(this.getPositionY() + 3);
			}
		}
		
		
	}
}



