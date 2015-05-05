package MED4_OOSE2015_MiniProjectGame;

public class Warrior extends Hero
{
	private int rage;
	
	Warrior(SimpleSlickGame game, int x, int y)
	{
		super(game, x,y);
		this.setHealth(120);
		this.rage = 0;
		//this.setSprite(SOMEIMAGE);
	}
}





