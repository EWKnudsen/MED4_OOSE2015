package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import java.lang.Object.*;

public class SimpleSlickGame extends BasicGame
{
	// When removing from this collection remember to call entity.close()
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<KeyPressedListener> keyPressedListeners = new ArrayList<KeyPressedListener>();
	ArrayList<KeyReleasedListener> keyReleasedListeners = new ArrayList<KeyReleasedListener>();
	private TiledMap map;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		map = new TiledMap("Graphics/Map2.tmx");
		entities.add(new Warrior(this, (840/2),(480/2)));
		entities.add(new Enemy(this, (640/2),(40/2)));
		entities.add(new Missile(this, (40/2),(480/2), null));
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		for(KeyPressedListener kpl:keyPressedListeners) {
			kpl.keyPressed(key, c);
		}
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		for(KeyReleasedListener krl:keyReleasedListeners) {
			krl.keyReleased(key, c);
		}
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(Entity e:entities) {
			e.move();
			e.shoot();
		}
		
		if( gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) 
		{
			entities.add(new Missile(this, gc.getInput().getMouseX(),gc.getInput().getMouseY(), null));
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		map.render(0,0);
		for(Entity e:entities){
			g.drawString("John", e.getPositionX(), e.getPositionY());
			//g.drawImage(e.getSprite(), e.getPositionX(), e.getPositionY());
		}	
		
		g.drawString("Hello World!", 250, 200);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	public ArrayList<Entity> getEntities() { return entities; }
	
    public void addKeyPressedListener(KeyPressedListener toAdd) {
    	keyPressedListeners.add(toAdd);
    }
    
    public void removeKeyPressedListener(KeyPressedListener toAdd) {
    	keyPressedListeners.add(toAdd);
    }

    public void addKeyReleasedListener(KeyReleasedListener toAdd) {
    	keyReleasedListeners.add(toAdd);
    }
    
    public void removeKeyReleasedListener(KeyReleasedListener toAdd) {
    	keyReleasedListeners.add(toAdd);
    }
}

interface KeyPressedListener {
    public void keyPressed(int key, char c);
}

interface KeyReleasedListener {
    public void keyReleased(int key, char c);
}

