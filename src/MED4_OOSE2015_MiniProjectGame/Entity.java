package MED4_OOSE2015_MiniProjectGame;

import java.awt.List;

public class Entity 
{
	private int positionX, positionY;
	private int speedX, speedY;
	private int scale;
	
	Entity()
	{
		
	}
	
	
	public boolean collides (Entity other)
	{
	//	if(SOMETHING) {SOMETHING}
		return false;
	}
	
	/*
	public boolean collides(List<Entity> others)
	{
		for(Entity o:others)
		{
			if(this.collides(o) && this != o)
			{
				return true;
			}
			return false;
		}
	}
	*/
	
}
