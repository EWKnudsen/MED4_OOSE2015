package MED4_OOSE2015_MiniProjectGame;


//abstract, meaning you can't create a instant of the class.
//(we only want to create instances of Warrior or Wizard) 
public abstract class Hero extends Character implements KeyPressedListener, KeyReleasedListener
{
	protected Missile[] missiles;
	protected int health;
	char lastChar;
	
	public Hero(SimpleSlickGame game, int x, int y) 
	{
		super(game, x, y);
		this.health = 100;
		
		game.addKeyPressedListener(this);
		game.addKeyReleasedListener(this);
	}

	@Override
	public void close()
	{
		game.removeKeyPressedListener(this);
		game.removeKeyReleasedListener(this);
	}
	
	public void keyPressed(int key, char c)
	{
		lastChar = c;
	}
	
	public void keyReleased(int key, char c)
	{
		lastChar = ' ';
	}
	
	@Override
	public void move() 
	{
		char pressed = lastChar;
		if (pressed == 'a')
		{	
			if(this.getPositionX() <= 0)
			{
				
			}else{
				this.setPositionX(this.getPositionX() - 3);	
			}
		}
		else if (pressed == 'd')
		{
			if(this.getPositionX() > game.mapWidth)
			{
				
			}else{
				this.setPositionX(this.getPositionX() + 3);	
			}
		}
		else if (pressed == 'w')
		{
			if(this.getPositionY() <= 0)
			{

			}else{
				this.setPositionY(this.getPositionY() - 3);
			}
		}
		else if (pressed == 's')
		{
			if(this.getPositionY() > game.mapHeight)
			{
				
			}else{
				this.setPositionY(this.getPositionY() + 3);
			}
		}
		
		
	}
}



