package MED4_OOSE2015_MiniProjectGame;

//abstract, meaning you can't create a instant of the class.
//(we only want to create instances of Warrior or Wizard) 
public abstract class Hero extends Character implements KeyPressedListener, KeyReleasedListener
{
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
	
	public void move() 
	{
		char pressed = lastChar;
		if (pressed == 'a')
		{
			this.setPositionX(this.getPositionX() - 2);
		}
		else if (pressed == 'd')
		{
			this.setPositionX(this.getPositionX() + 2);
		}
		else if (pressed == 'w')
		{
			this.setPositionY(this.getPositionY() - 2);
		}
		else if (pressed == 's')
		{
			this.setPositionY(this.getPositionY() + 2);
		}
	}
}
