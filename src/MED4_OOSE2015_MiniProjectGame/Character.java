package MED4_OOSE2015_MiniProjectGame;

public class Character extends Entity
{
	private int health;
	
	public Character(SimpleSlickGame game, int x, int y) 
	{
		super(game,x,y);
		this.health = 100;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	@Override
	public void renderParticles()
	{
		particles.render();
	}
	
	public void setHealth(int newHealth)
	{
		this.health = newHealth;
	}
	
	@Override
	public void close()
	{
		super.close();
	}
}
