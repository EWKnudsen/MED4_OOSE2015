package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;

public class Wizard extends Hero
{
	private int mana;
	private ArrayList<Missile> missiles;  //current missiles 'alive'
	
	Wizard()
	{
		this.missiles = new ArrayList<Missile>();
		
	}
	
	public void fire() 
	{
		missiles.add(new Missile(getX() + getSCALE(), getY() + getSCALE()));
	}
	
	public ArrayList<Missile> getMissiles() {   return this.missiles;   }
	
	public int getMANA() {   return this.mana;   }
	
}
