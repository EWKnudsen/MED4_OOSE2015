package MED4_OOSE2015_MiniProjectGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

public class Missile extends Entity
{
	private Entity owner;
	private int destX;
	private int destY;
	private int startX;
	private int startY;
	private float speed;
	private float dx, dy;
	private Image missileImg = null;
	Point location = new Point(0,0);
	
	public Missile(SimpleSlickGame _game, int x, int y, int destX, int destY, Entity owner) 
	{

		super(_game, x,y);
		startX = x;
		startY = y;
		this.owner = owner;
		this.destX = destX;
		this.destY = destY;
		location.setLocation(startX, startY);
		recalculateVector(destX, destY);
		try {
			missileImg = new Image("Graphics/Fireball.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not find sprite");
		}
		this.setSprite(missileImg);
	}
	
	public void recalculateVector(int destX, int destY)
    {
       float rad = (float)(Math.atan2(destX - startX, startY - destY));
       
       //Can set different speeds here, if you wanted.
       speed = 2;
       
       this.dx = (float) Math.sin(rad) * speed;
       this.dy = -(float) Math.cos(rad) * speed;
    }
	
	public void recalculateVector()
    {
       recalculateVector(destX, destY);
    }
	
	@Override
	public boolean collides (Entity other)
	{
		if (other == owner)
			return false;
		else
			return super.collides(other);
	}
	
	@Override
	public void move()
	{
		//this.setPositionX(this.getPositionX() + MISSILE_SPEED);
        float x = location.getX();
        float y = location.getY();
        
        x += dx;
        y += dy;
        
        location.setLocation(x, y);
	}
	
	
	public Vector2f getLocation() {
		return location.getLocation();
	}

	public void setLocation(float positionX, float positionY) {
		location.setLocation(positionX, positionY);
	}
}

