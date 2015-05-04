package MED4_OOSE2015_MiniProjectGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy extends Entity
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
	}
	
	public void move()
	{
		speed = 1;
		
		for(Entity e:game.entities )
		{
			if (e instanceof Hero)
			{
				if (this.getPositionX() < e.getPositionX()) {
					this.setPositionX(this.getPositionX() + speed);
					this.setSprite(enemyFrontRight);
				}
				else if (this.getPositionX() > e.getPositionX()) {
					this.setPositionX(this.getPositionX() - speed);
					this.setSprite(enemyFrontLeft);
				}
				if (this.getPositionY() < e.getPositionY()) {
					this.setPositionY(this.getPositionY() + speed);
					this.setSprite(enemyFrontLeft);
				}
				else if (this.getPositionY() > e.getPositionY()) {
					this.setPositionY(this.getPositionY() - speed);
					this.setSprite(enemyBack);
				}
				break;
			}
		}
	}
}
