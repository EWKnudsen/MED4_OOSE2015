package MED4_OOSE2015_MiniProjectGame;

public class Missile extends Entity
{
	private Entity owner;
	private int MISSILE_SPEED = 2;
	// Missing direction
	
	public Missile(SimpleSlickGame game, int x, int y, Entity owner) 
	{
		super(game, x,y);
		this.owner = owner;
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
