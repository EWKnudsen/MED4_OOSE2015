package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;
import java.util.LinkedList;
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
	// When removing from this collection remember to call entity.close()
	ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Missile> missileList = new ArrayList<Missile>();
	ArrayList<KeyPressedListener> keyPressedListeners = new ArrayList<KeyPressedListener>();
	ArrayList<KeyReleasedListener> keyReleasedListeners = new ArrayList<KeyReleasedListener>();
	private TiledMap map;
	public int heroPosX;
	public int heroPosY;
	
	private Image wizardFrontRight = null;
	private Image wizardFrontLeft = null;
	private Image wizardBackRight = null;
	private Image wizardBackLeft = null;
	
	private Image missileImg = null;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		map = new TiledMap("Graphics/Map2.tmx");
		//entities.add(new Warrior(this, (840/2),(480/2)));
		Warrior hero = new Warrior(this,(840/2),(480/2));
		entities.add(hero);
		entities.add(new Enemy(this, (640/2),(40/2)));
		//entities.add(new Missile(this, (40/2),(480/2), null));
		wizardFrontRight = new Image ("Graphics/Wizard full (front right).png");
		wizardFrontLeft = new Image ("Graphics/Wizard full (front left).png");
		wizardBackRight = new Image ("Graphics/Wizard full (back right).png");
		wizardBackLeft = new Image ("Graphics/Wizard full (back left).png");
		//entities.add(new Missile(this, (40/2),(480/2), null));
		missileImg = new Image ("Graphics/Fireball.png");
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(Entity e:entities) {
			e.move();
			e.shoot();
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
	         
	         missile.move();
	         
	         //NOTE: Will need to determine if this hit something or went off the screen. Or otherwise, the list will get filled with invalid bullets.
	      }
	
	}
	   public void mousePressed ( int button, int x, int y )
	   {
	      addNewBullet(x,y);
	      System.out.println("hero x " + heroPosX + " hero y " + heroPosY);
	      System.out.println("mouse x " + Mouse.getEventX() + " mouse y " + Mouse.getEventY());
	      
	   }
	   private void addNewBullet(int x, int y)
	   {

	      missileList.add(new Missile(this, (int)heroPosX, (int)heroPosY, x, y, null));
	      //missileList.add(new Missile(this,heroLoactionX,heroLocationY,heroLoactionX,heroLocationY,x,y,null));
	   }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		map.render(0,0);
		for(Entity e:entities){
			
		
			if(e instanceof Hero){
				if(Mouse.getEventX() >= heroPosX &&  (Window.HEIGHT - Mouse.getEventY())*-1 <= heroPosY )
					g.drawImage(wizardFrontRight, heroPosX-(wizardFrontRight.getWidth()/2), heroPosY-(wizardFrontRight.getHeight()/2));
				else if(Mouse.getEventX() <= heroPosX && Mouse.getEventY() <= heroPosY )
					g.drawImage(wizardFrontLeft, heroPosX-(wizardFrontLeft.getWidth()/2), heroPosY-(wizardFrontLeft.getHeight()/2));
				else if (Mouse.getEventX() >= heroPosX && Mouse.getEventY() >= heroPosY)
					g.drawImage(wizardBackRight, heroPosX-(wizardBackRight.getWidth()/2), heroPosY-(wizardBackRight.getHeight()/2));
				else if (Mouse.getEventX() <= heroPosX && Mouse.getEventY() >= heroPosY)
					g.drawImage(wizardBackLeft, heroPosX-(wizardBackLeft.getWidth()/2), heroPosY-(wizardBackLeft.getHeight()/2));
				} else {
				g.drawString("John", e.getPositionX(), e.getPositionY());
			}
			//g.drawImage(wizardFrontRight, e.getPositionX()-(wizardFrontRight.getWidth()/2), e.getPositionY()-(wizardFrontRight.getHeight()/2));
			//g.drawImage(e.getSprite(), e.getPositionX(), e.getPositionY());
		}	
		
		g.drawString("Hello World!", 250, 200);
		
		 g.setColor(Color.red);
	      for(int i = 0;i<missileList.size();i++)
	      {
	         Missile missiles = missileList.get(i);
	         
	         g.drawImage(missileImg ,missiles.location.getX()- (missileImg.getWidth()/2), missiles.location.getY()-(missileImg.getHeight()/2));
	      }
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

