package MED4_OOSE2015_MiniProjectGame;

public class Wizard extends Hero
{
	private int mana;
	
	Wizard(SimpleSlickGame game, int x, int y)
	{
		super(game, x,y);
		health = 100;
		mana = 100;
	}
}
