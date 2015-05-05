package MED4_OOSE2015_MiniProjectGame;

public class Character extends Entity
{
	private int health;
	
	public Character(SimpleSlickGame game, int x, int y) 
	{
		super(game,x,y);
		health = 100;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int newHealth)
	{
		health = newHealth;
	}
}
