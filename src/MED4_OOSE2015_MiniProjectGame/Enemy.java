package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;

public class Enemy extends Entity
{
	private int dx, dy;                   //x and y speed
//	private ArrayList<Missile> missiles;  //current missiles 'alive'
	
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
		
		//x += dx;
		//y += dy;
		/*
		if (x < 1-scale) {   x = 1+scale;   }
		if (y < 1-scale) {   y = 1+scale;   }
		if (x < 640-scale) {   x = 640-scale;   }
		if (y < 480-scale) {   y = 480-scale;   }
		*/
	}
}
