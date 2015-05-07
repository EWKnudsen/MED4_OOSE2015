package MED4_OOSE2015_MiniProjectGame;

//This subclass is yet to be implemented into the game.
//Using the structure of the code, it is easy to create a new hero type

//Warrior extends the Hero class
public class Warrior extends Hero
{
	//Variables for the Warrior class is initialized
	private int rage;
	
	/**
	 * The constructor sets the X and Y position of the entity and refers to the slick game in to reach its variables and functions
	 * @param game
	 * @param x
	 * @param y
	 */
	Warrior(SimpleSlickGame game, int x, int y)
	{
		super(game, x,y);
		this.setHealth(120);
		this.rage = 0;
		//this.setSprite(SOMEIMAGE);
	}
}





