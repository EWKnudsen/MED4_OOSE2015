package MED4_OOSE2015_MiniProjectGame;

public class Warrior extends Hero
{
	private int rage;
	
	Warrior(int x, int y)
	{
		super(x,y);
		health = 120;
		scale = 22;
		rage = 0;
	}
	
	public void hit() 
	{
		//something...
	}
	
	public int getRAGE()          {   return this.rage;  }
	
	public void setRAGE(int rage) {   this.rage = rage;  }
}





