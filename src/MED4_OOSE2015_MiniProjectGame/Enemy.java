package MED4_OOSE2015_MiniProjectGame;

public class Enemy extends Entity
{
	private int dx, dy;                   //x and y speed
	
	public Enemy(SimpleSlickGame game, int x, int y) 
	{
		super(game, x, y);
	}
	
	public void move()
	{
		for(Entity e:game.entities)
		{
			if (e instanceof Hero)
			{
				if (this.getPositionX() < e.getPositionX())
					this.setPositionX(this.getPositionX() + 1);
				else if (this.getPositionX() > e.getPositionX())
					this.setPositionX(this.getPositionX() - 1);
				
				if (this.getPositionY() < e.getPositionY())
					this.setPositionY(this.getPositionY() + 1);
				else if (this.getPositionY() > e.getPositionY())
					this.setPositionY(this.getPositionY() - 1);
				
				break;
			}
		}
	}
}
