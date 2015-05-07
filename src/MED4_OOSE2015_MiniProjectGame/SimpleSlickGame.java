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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class SimpleSlickGame extends BasicGame
{
	
	//Instantiating Game container
	static AppGameContainer appgc;
	
	// Initializing entity and keys-pressed lists
	// // When removing from this collection remember to call entity.close()
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<KeyPressedListener> keyPressedListeners = new ArrayList<KeyPressedListener>();
	ArrayList<KeyReleasedListener> keyReleasedListeners = new ArrayList<KeyReleasedListener>();
	ArrayList<MousePressedListener> mousePressedListeners = new ArrayList<MousePressedListener>();
	//Initializing global variables, such as map height and level counter.
	private TiledMap map;
	public int mapHeight, mapWidth;
	private Hero hero;
	private float spawnTimer = 1f;
	private int spawnTime = 10;
	private int levelCounter = 1;
	

	// Creating global timers to keep track of game time and the spawn timer.
	Timer enemySpawnTimer = new Timer();
	Timer gameTime = new Timer();

	//Creates a global random variable to create random numbers troughout the game code.
	Random r = new Random();

	public SimpleSlickGame(String gamename)
	{
		//Creates a new slick-game
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		//Loading map file
		map = new TiledMap("Graphics/Map3.tmx");

		//Instantiating hero, created as a wizard (Other classes not yet implemented). Adds the hero to entities list for later use.
		hero = new Wizard(this,(appgc.getWidth()/2),(appgc.getHeight()/2));
		entities.add(hero);

		//Values used inside entity subclasses to limit their position range
		mapWidth = appgc.getWidth();
		mapHeight = appgc.getHeight();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{	
		//Timer.tick updates the time of all timers.
		Timer.tick();
		//Controls the frame rate by adding a delay to the update function.
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//Creating a new list to keep track of dead entities
		ArrayList<Entity> deadEntities = new ArrayList<Entity>();
		
		//for loop goes through all entities in the entities list.
		for (Entity e : entities)
		{	
			//The collides function is run to check if entities collide in the game.
			e.collides(entities);			
			
			//If statement detects if entities have moved too far away from the screen.
			if(e.getPositionX() < -200 || e.getPositionX() > appgc.getWidth()+200 ||
			   e.getPositionY() < -200 || e.getPositionY() > appgc.getHeight()+200 )
			{
				//If the entity is too far away, it is set to "dead".
				e.isAlive = false;
			}
			else 
			{
				//If the entities are not too far away, the move() and particleUpdate() functions are run.
				e.move();  //Controls the movement of entities.
				e.particleUpdate(); //Controls the updating of particles in entities.
			}			
			//Checks if the entities are dead.
			if (!e.isAlive)
			{
				//If the entitiy is dead, add them to the deadEntities list.
				deadEntities.add(e);
			}
			//pauses the timer of showing how many seconds the hero has survived if he dies. 
			if (e instanceof Hero)
			{
				if(!e.isAlive)
				{
					gameTime.pause();
				}
			}
		}
		
		// checks through all dead entities
		for (Entity e : deadEntities) {
			//closes all dead entities and removes them from the entities list.
			e.close();
			entities.remove(e);
		}
		
		//If statement to check if the game time has proceeded the spawnTime variable.
		if(gameTime.getTime() > spawnTime)
		{
			//if the game time has proceeded the spawnTime variable, the spawnTimer is decreased, so enemies will spawn faster.
			spawnTimer -= 0.05;
			//spawnTime is increased by 10 sec.
			spawnTime += 10;
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
	
	//The render function controls the rendering of graphics such as sprites and particles.
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		//renders the map at position (0.0).
		map.render(0,0);
		
		//Checks through all entities
		for(Entity e: entities )
		{
			//If the entity is a hero, set the color to red and draw a health bar according to the amount of health the hero has.
			if (e instanceof Hero)
			{
				g.setColor(Color.red);
				g.fillRect(appgc.getWidth()/5, appgc.getHeight()/20, ((Hero) e).getHealth()*1.5f , 10);			
			}
			//Switching sprite according to entity's direction or mousePos.
			e.spriteSwitch();

			//Drawing all sprites
			g.drawImage(e.getSprite(), e.getPositionX() - (e.getSprite().getWidth()/2), e.getPositionY() - (e.getSprite().getHeight()/2));
			
			//Rendering all Particles.
			e.renderParticles();
		}

		//Brute force GUI: draws the amount of seconds survived and the current level.
		g.setColor(Color.white);
		g.drawString("Seconds survived: " + Float.toString(gameTime.getTime()) , appgc.getWidth()-240, appgc.getHeight()/23);
		g.drawString("Level: " + levelCounter, appgc.getWidth()/2, appgc.getHeight()/2);
	}

	//Start of Main class
	public static void main(String[] args)
	{
		try
		{
			//Initialising the gameContainer and starting it.
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			//Sets the window to 640*480 pixels.
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	//keyPressed function to detect which key is pressed.
	@Override
	public void keyPressed(int key, char c)
	{
		for(KeyPressedListener kpl:keyPressedListeners) {
			kpl.keyPressed(key, c);
		}
	}

	//keyReleased function to detect which keys are released
	@Override
	public void keyReleased(int key, char c)
	{
		for(KeyReleasedListener krl:keyReleasedListeners) {
			krl.keyReleased(key, c);
		}
	}
	
	//mousePressed function to detect when and where the mouse is pressed
	@Override
	public void mousePressed ( int button, int mousePosX, int mousePosY )
	{
		for(MousePressedListener mpl:mousePressedListeners) {
			mpl.mousePressed(button, mousePosX, mousePosY);
		}
	}

	// getEntities function that returns the entities list
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

