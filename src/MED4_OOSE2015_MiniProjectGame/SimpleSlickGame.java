package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Color;

import com.sun.javafx.geom.Rectangle;

import java.awt.Window;
import java.lang.Object.*;

import javax.swing.text.html.HTMLDocument.Iterator;

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
	private int x, y;
	public int heroPosX;
	public int heroPosY;

	Random r = new Random();
	
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
	      
	      if(gc.getInput().isKeyPressed(Input.KEY_SPACE))
	      {
		      entities.add(new Enemy(this, r.nextInt(640), r.nextInt(480))); 
	      }
	 }
				
	   public void mousePressed ( int button, int mousePosX, int mousePosY )
	   {
	      addNewBullet(mousePosX,mousePosY);
	   }
	   public void addNewBullet(int destPosX, int destPosY)
	   {
		  //how do we reach our heroes position in another way than this? 
	      missileList.add(new Missile(this, (int)heroPosX, (int)heroPosY, destPosX, destPosY, null));
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

