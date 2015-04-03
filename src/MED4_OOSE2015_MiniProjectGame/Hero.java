package MED4_OOSE2015_MiniProjectGame;

//abstract, meaning you can't create a instant of the class.
//(we only want to create instances of Warrior or Wizard) 
public abstract class Hero extends Character implements KeyPressedListener, KeyReleasedListener
{
	protected int health;
	
	public Hero(SimpleSlickGame game, int x, int y) 
	{
		super(game, x, y);
		//this.visible = true;
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
	
	char lastChar;
	
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
		
		//if (x < 1-scale) {   x = 1+scale;   }
		//if (y < 1-scale) {   y = 1+scale;   }
		//if (x < 640-scale) {   x = 640-scale;   }
		//if (y < 480-scale) {   y = 480-scale;   }
	}

	/*
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();

//		if (key == KeyEvent.VK_SPACE) {   fire();    }  maybe move in Wizard?

		if (key == KeyEvent.VK_LEFT)  {   dx = -1;   }

		if (key == KeyEvent.VK_RIGHT) {   dx = 1;    }

		if (key == KeyEvent.VK_UP)    {   dy = -1;   }

		if (key == KeyEvent.VK_DOWN)  {   dy = 1;    }
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)  {   dx = 0;   }
		
		if (key == KeyEvent.VK_RIGHT) {   dx = 0;   }

		if (key == KeyEvent.VK_UP)    {   dy = 0;   }

		if (key == KeyEvent.VK_DOWN)  {   dy = 0;   }
	}
	
	
	public int getX()         {   return this.x;     }
	
	public void setX(int x)   {   this.x = x;        }

	public int getY()         {   return this.y;     }
	
	public void setY(int y)   {   this.y = y;        }

	public int getDX()        {   return this.dx;    }

	public void setDX(int dx) {   this.dx = dx;      }
	
	public int getDY()        {   return this.dy;    }

	public void setDY(int dy) {   this.dy = dy;      }
	
	public int getSCALE()           {   return this.scale;       }
	
	public void setSCALE(int health){   this.scale = scale;      }
	
	public int getHEALTH()            {   return this.health;        }
	
	public void setHEALTH(int health) {   this.health = health;     }

	public boolean isVisible() 				{   return this.visible;    }

	public void setVisible(boolean visible) {   this.visible = visible; }
	*/
}
