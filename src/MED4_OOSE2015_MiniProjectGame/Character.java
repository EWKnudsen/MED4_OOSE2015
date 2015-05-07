package MED4_OOSE2015_MiniProjectGame;

//The character class extends the Entity class
public class Character extends Entity
{
	//Variables are initialized. The character class only chandles the health variable
	private int health;
	/**
	 * The constructor sets the X and Y position of the entity and refers to the slick game in to reach its variables and functions
	 * @param game
	 * @param x
	 * @param y
	 */
	public Character(SimpleSlickGame game, int x, int y) 
	{
		//The super constructor (the Entity constructor) is called to give the x and y position and refer to game.
		super(game,x,y);
		//health of a character is set to a default 100
		this.health = 100;
	}
	
	/**
	 * Function that returns the characters health
	 * @return
	 */
	public int getHealth()
	{
		return this.health;
	}
	
	/**
	 * renders character particles
	 */
	@Override
	public void renderParticles()
	{
		particles.render();
	}
	
	/**
	 * Function that sets the characters health
	 * @param newHealth
	 */
	public void setHealth(int newHealth)
	{
		this.health = newHealth;
	}
	/**
	 * close() closes the entity by calling the super function of close().
	 */
	@Override
	public void close()
	{
		super.close();
	}
}
