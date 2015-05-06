package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.util.Timer;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

public class SimpleSlickGame extends BasicGame
{
	static AppGameContainer appgc;

	// When removing from this collection remember to call entity.close()
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<KeyPressedListener> keyPressedListeners = new ArrayList<KeyPressedListener>();
	ArrayList<KeyReleasedListener> keyReleasedListeners = new ArrayList<KeyReleasedListener>();
	ArrayList<MousePressedListener> mousePressedListeners = new ArrayList<MousePressedListener>();
	private TiledMap map;
	public int mapHeight, mapWidth;
	private Hero hero;

	Timer timer = new Timer();
	Timer timer2 = new Timer();
	Random r = new Random();

	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		map = new TiledMap("Graphics/Map3.tmx");

		hero = new Wizard(this,(appgc.getWidth()/2),(appgc.getHeight()/2));
		entities.add(hero);

		//Values used inside entity subclasses to limit their position range
		mapWidth = appgc.getWidth();
		mapHeight = appgc.getHeight();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{	
		Timer.tick();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ArrayList<Entity> deadEntities = new ArrayList<Entity>();
		for (Entity e : entities)
		{	
			e.collides(entities);			

			if(e.getPositionX() < -200 || e.getPositionX() > appgc.getWidth()+200 ||
			   e.getPositionY() < -200 || e.getPositionY() > appgc.getHeight()+200 )
			{
				e.isAlive = false;
			}
			else 
			{
				e.move();
				e.particleUpdate();
			}			
	
			if (!e.isAlive)
			{
				deadEntities.add(e);
			}
			//pauses the timer of showing how many seconds the hero has survived if he dies. 
			if (e instanceof Hero )
			{
				if(!e.isAlive)
				{
					timer2.pause();
					
				}
			}
		}

		for (Entity e : deadEntities) {
			e.close();
			entities.remove(e);
		}

		int objectLayer = map.getLayerIndex("Objects");
		map.getTileId(0, 0, objectLayer);

		//Spawns an enemy every 3 seconds at a position that is +-100 the position of the Hero.
		if(timer.getTime() > 1)
		{
			int rndX = r.nextInt(appgc.getWidth()) ;
			int rndY = r.nextInt(appgc.getHeight());

			while(rndX < hero.getPositionX()+100 && rndX > hero.getPositionX()-100 &&
				  rndY < hero.getPositionY() +100 && rndY > hero.getPositionY()-100)
			{  
				rndX = r.nextInt(appgc.getWidth());
				rndY = r.nextInt(appgc.getHeight());	  
			}
			entities.add(new Enemy(this, rndX, rndY));
			timer.reset();
		}
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		map.render(0,0);

		for(Entity e: entities )
		{
			//Switching sprite according to entity's direction or mousePos.
			e.spriteSwitch();

			//Drawing all sprites
			g.drawImage(e.getSprite(), e.getPositionX() - (e.getSprite().getWidth()/2), e.getPositionY() - (e.getSprite().getHeight()/2));
			
			//Rendering all Particles.
			e.renderParticles();
		}
		
		//maybe make GUI func   and call: GUI(); instead
		g.drawString("Seconds survived: " + Float.toString(timer2.getTime()) , 380, 15);
	}

	public static void main(String[] args)
	{
		try
		{
			//Initialising the gameContainer and starting it.
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
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
	public void mousePressed ( int button, int mousePosX, int mousePosY )
	{
		for(MousePressedListener mpl:mousePressedListeners) {
			mpl.mousePressed(button, mousePosX, mousePosY);
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

	public void addMousePressedListener(MousePressedListener toAdd) {
		mousePressedListeners.add(toAdd);
	}

	public void removeMousePressedListener(MousePressedListener toAdd) {
		mousePressedListeners.add(toAdd);
	}
}

interface KeyPressedListener {
	public void keyPressed(int key, char c);
}

interface KeyReleasedListener {
	public void keyReleased(int key, char c);
}

interface MousePressedListener {
	public void mousePressed(int button, int mousePosX, int mousePosY);
}

