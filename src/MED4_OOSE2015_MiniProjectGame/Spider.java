package MED4_OOSE2015_MiniProjectGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
		
		speed = 1;
	}
	
	@Override
	public void Collision(Entity e)
	{
		if (e instanceof Hero || e instanceof Missile)
		{
			isAlive = false;
		}
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
