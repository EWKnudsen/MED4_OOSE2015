package MED4_OOSE2015_MiniProjectGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy extends Entity
{
	private int dx, dy;                   //x and y speed
	protected Image enemyFrontLeft = null;
	protected Image enemyFrontRight = null;
	protected Image enemyBack = null;
	
	public Enemy(SimpleSlickGame game, int x, int y) 
	{
		super(game, x, y);
		try {
			
			enemyFrontLeft = new Image("Graphics/Melee enemy front left.png");
			enemyFrontRight = new Image("Graphics/Melee enemy front right.png");
			enemyBack = new Image("Graphics/Melee enemy back.png");
			this.setSprite(enemyFrontLeft);
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("Could not find sprite");
		}
	}
	
	public void move()
	{
		for(Entity e:game.entities)
		{
			if (e instanceof Hero)
			{
				if (this.getPositionX() < e.getPositionX()){
					this.setPositionX(this.getPositionX() + 1);
					this.setSprite(enemyFrontRight);
				} else if (this.getPositionX() > e.getPositionX()){
					this.setPositionX(this.getPositionX() - 1);
					this.setSprite(enemyFrontLeft);
				}
				
				if (this.getPositionY() < e.getPositionY())
					this.setPositionY(this.getPositionY() + 1);
				else if (this.getPositionY() > e.getPositionY()){
					this.setPositionY(this.getPositionY() - 1);
					this.setSprite(enemyBack);
				}
				
				break;
			}
		}
	}
}
