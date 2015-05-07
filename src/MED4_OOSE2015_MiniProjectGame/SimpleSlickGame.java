package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.util.Timer;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

public class SimpleSlickGame extends BasicGame
{
	//Instantiating Game container
	static AppGameContainer appgc;

	// Initializing entity and keys- mousePressed lists
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<KeyPressedListener> keyPressedListeners = new ArrayList<KeyPressedListener>();
	ArrayList<KeyReleasedListener> keyReleasedListeners = new ArrayList<KeyReleasedListener>();
	ArrayList<MousePressedListener> mousePressedListeners = new ArrayList<MousePressedListener>();
	private TiledMap map;
	private Hero hero;
	private float spawnTimer = 1f;
	private int levelTimer = 10;
	private int levelCounter = 1;
	
	Timer enemySpawnTimer = new Timer();
	Timer gameTime = new Timer();

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
	}

	//Update function
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
		}

		if(hero.isAlive)
		{
			//If statement to check if the game time has proceeded the spawnTime variable.
			if(gameTime.getTime() > levelTimer){
				//if the game time has proceeded the spawnTime variable, the spawnTimer is decreased, so enemies will spawn faster.
				spawnTimer -= 0.05;
				//spawnTime is increased by 10 sec.
				levelTimer += 10;
				//The level is increased
				levelCounter += 1;
				System.out.println(levelCounter);
			}

			//Spawns an enemy at the specified spawn rate, and handles the spawn position of them.
			if(enemySpawnTimer.getTime() > spawnTimer)
			{
				//Generates random X and Y positions for the enemy
				int rndX = r.nextInt(appgc.getWidth());
				int rndY = r.nextInt(appgc.getHeight());
				//Random number to determine which enemy to spawn
				int rndNum = r.nextInt(20);

				//Check which enemy should spawn by using the random number
				if(rndNum > 5){
					//To ensure the random position is not near the player, the random X and Y will be randomized until the criterias are met.
					while(rndX < hero.getPositionX()+100 && rndX > hero.getPositionX()-100 &&
							rndY < hero.getPositionY() +100 && rndY > hero.getPositionY()-100)
					{  
						rndX = r.nextInt(appgc.getWidth());
						rndY = r.nextInt(appgc.getHeight());	  
					}
					//Adds a new zombie
					entities.add(new Enemy(this, rndX, rndY));
				} else {
					//To ensure the random position is not near the player, the random X and Y will be randomized until the criterias are met.
					while(rndX < hero.getPositionX()+200 && rndX > hero.getPositionX()-200 &&
							rndY < hero.getPositionY() + 200 && rndY > hero.getPositionY()-200)
					{  
						rndX = r.nextInt(appgc.getWidth());
						rndY = r.nextInt(appgc.getHeight());	  
					}
					//Adds a new spider
					entities.add(new Spider(this, rndX, rndY));
				}		
				//			//Resets the spawn timer. 
				enemySpawnTimer.reset();
			}
		}

		// checks through all dead entities
		for (Entity e : deadEntities) {
			//pauses the timer of showing how many seconds the hero has survived if he dies. 
			if (e instanceof Hero)
			{
				if(!e.isAlive)
				{
					gameTime.pause();
				}
			}
		}

		for (Entity e : deadEntities) {
			e.close();
			entities.remove(e);
		}
		
		if(gameTime.getTime() > levelTimer)
		{
			spawnTimer -= 0.05;
			levelTimer += 10;
			levelCounter += 1;
			System.out.println(levelCounter);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		map.render(0,0);

		for(Entity e: entities )
		{
			if (e instanceof Hero)
			{
				g.setColor(Color.red);
				g.fillRect(appgc.getWidth()/5, appgc.getHeight()/20, hero.getHealth()*1.5f , 10);
			}
			//Switching sprite according to entity's direction or mousePos.
			e.spriteSwitch();

			//Drawing all sprites
			g.drawImage(e.getSprite(), e.getPositionX() - (e.getSprite().getWidth()/2), e.getPositionY() - (e.getSprite().getHeight()/2));
			
			//Rendering all Particles.
			e.renderParticles();
		}

		//maybe make GUI func   and call: GUI(); instead
		g.setColor(Color.white);
		g.drawString("Seconds survived: " + Float.toString(gameTime.getTime()) , appgc.getWidth()-240, appgc.getHeight()/23);
		g.drawString("Level: " + levelCounter, appgc.getWidth()/2, appgc.getHeight()/2);
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

