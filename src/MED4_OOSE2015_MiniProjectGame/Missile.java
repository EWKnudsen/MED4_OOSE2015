package MED4_OOSE2015_MiniProjectGame;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

public class Missile extends Entity
{
	private Entity owner;
	private int MISSILE_SPEED = 2;
	private int destX = 0;
	private int destY = 0;
	private int startX = 0;
	private int startY = 0;
	private float speed;
	private float dx;
	private float dy;
	Point location = new Point(0,0);
	// Missing direction
	
	public Missile(SimpleSlickGame game, int x, int y, int destX, int destY, Entity owner) 
	{
		super(game, x,y);
		x = startX;
		y = startY;
		this.owner = owner;
		this.destX = destX;
		this.destY = destY;
		location.setLocation(0, 0);
		recalculateVector(destX, destY);
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
	
}

