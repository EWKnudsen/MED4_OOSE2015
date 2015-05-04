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
import org.newdawn.slick.tiled.TiledMap;

public class SimpleSlickGame extends BasicGame
{
	static AppGameContainer appgc;
	
	// When removing from this collection remember to call entity.close()
	ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Missile> missileList = new ArrayList<Missile>();
	ArrayList<KeyPressedListener> keyPressedListeners = new ArrayList<KeyPressedListener>();
	ArrayList<KeyReleasedListener> keyReleasedListeners = new ArrayList<KeyReleasedListener>();
	private TiledMap map;
	public int mapHeight, mapWidth;
	public int heroPosX;
	public int heroPosY;
	
	Timer timer = new Timer();
	Random r = new Random();
	int R = r.nextInt(400) + 100;
	
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		map = new TiledMap("Graphics/Map3.tmx");
		Wizard hero = new Wizard(this,(appgc.getWidth()/2),(appgc.getHeight()/2));
		entities.add(hero);
		mapWidth = appgc.getWidth();
		mapHeight = appgc.getHeight();
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
		
		Timer.tick();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		for(Entity e:entities) {
			e.move();
			e.shoot();
			e.drawing();
			//How to get the position from a Hero
			if (e instanceof Hero)
			{
				heroPosX = e.getPositionX();
				heroPosY = e.getPositionY();
			}
		}
		
	      //Update the bullet's position.
	      for(int j = 0;j<missileList.size();j++)
	      {
	         Missile missile = missileList.get(j);
	         
	         if(missile.getLocation().x < -100 || missile.getLocation().x > appgc.getWidth()+100 || missile.getLocation().y < -100 || missile.getLocation().y > appgc.getHeight()+100 )
	         {
	        	 missileList.remove(j);
	         }
	         else {
	            missile.move();
	         }
	         //NOTE: Will need to determine if this hit something or went off the screen. Or otherwise, the list will get filled with invalid bullets.
	      }
	      
	      int objectLayer = map.getLayerIndex("Objects");
	      map.getTileId(0, 0, objectLayer);
	     
	      //Spawns an enemy every 3 seconds at a position that is +-100 the position of the Hero.
	      if(timer.getTime() > 3){
	    	  int rndX = r.nextInt(appgc.getWidth()) ;
	    	  
	    	  int rndY = r.nextInt(appgc.getHeight());
	    	  
	    	  while(rndX < heroPosX+100 && rndX > heroPosX-100 && rndY < heroPosY +100 && rndY > heroPosY-100){
	    		  
	    		  rndX = r.nextInt(appgc.getWidth());
	    		  rndY = r.nextInt(appgc.getHeight());
	    		  
	    	  }
	    	  entities.add(new Enemy(this, rndX, rndY)); 
	    	  timer.reset();
	      }
	      
	 }
				
	   public void mousePressed ( int button, int mousePosX, int mousePosY )
	   {
		   missileList.add(new Missile(this, (int)heroPosX, (int)heroPosY, mousePosX, mousePosY, null));
	   }
	   

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		map.render(0,0);
		for(Entity e:entities){
			if(e instanceof Wizard){
				g.drawImage(e.getSprite(), e.getPositionX()-(e.getSprite().getWidth()/2), e.getPositionY()-(e.getSprite().getHeight()/2));
			} else if (e instanceof Enemy) {
				g.drawImage(e.getSprite(), e.getPositionX() - (e.getSprite().getWidth()/2), e.getPositionY() - (e.getSprite().getHeight()/2));
			}
			//g.drawImage(wizardFrontRight, e.getPositionX()-(wizardFrontRight.getWidth()/2), e.getPositionY()-(wizardFrontRight.getHeight()/2));
			//g.drawImage(e.getSprite(), e.getPositionX(), e.getPositionY());
		}	
		
		
		for(int i = 0; i<missileList.size(); i++)
		{
			Missile missiles = missileList.get(i);

			g.drawImage(missiles.getSprite(),missiles.location.getX()- (missiles.getSprite().getWidth()/2), missiles.location.getY()-(missiles.getSprite().getHeight()/2));
		}
	}

	public static void main(String[] args)
	{
		try
		{
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

