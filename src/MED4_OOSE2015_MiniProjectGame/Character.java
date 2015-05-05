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
	
	public void setHealth(int newHealth)
	{
		this.health = newHealth;
	}
}
