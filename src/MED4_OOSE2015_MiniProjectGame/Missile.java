package MED4_OOSE2015_MiniProjectGame;

import org.newdawn.slick.geom.Vector2f;

public class Missile extends Entity
{
	private Entity owner;
	private int MISSILE_SPEED;
	private Vector2f pos;
	// Missing direction
	
	public Missile(SimpleSlickGame game, int x, int y, Entity owner) 
	{
		super(game, x,y);
		this.owner = owner;
		pos = new Vector2f(x,y);
	}
	
	@Override
	public boolean collides (Entity other)
	{
		if (other == owner)
			return false;
		else
			return super.collides(other);
	}
	
	@Override
	public void move()
	{
		this.setPositionX(this.getPositionX() + MISSILE_SPEED);
	}
	
}

