package MED4_OOSE2015_MiniProjectGame;

public class Warrior extends Hero
{
	private int rage;
	
	Warrior(SimpleSlickGame game, int x, int y)
	{
		super(game, x,y);
		health = 120;
		//scale = 22;
		rage = 0;
		//sprite = SOMEIMAGE
	}
	/*
	public void hit() 
	{
		//something...
	}
	
	public int getRAGE()          {   return this.rage;  }
	
	public void setRAGE(int rage) {   this.rage = rage;  }
	*/
}





