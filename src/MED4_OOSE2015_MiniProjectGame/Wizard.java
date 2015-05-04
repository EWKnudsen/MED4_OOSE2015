package MED4_OOSE2015_MiniProjectGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;

public class Wizard extends Hero
{
	private Image wizardFrontRight = null;
	private Image wizardFrontLeft = null;
	private Image wizardBackRight = null;
	private Image wizardBackLeft = null;
	private int mana;
	
	Wizard(SimpleSlickGame game, int x, int y)
	{
		super(game, x,y);
		health = 100;
		mana = 100;
	}
	
	public void move () 
	{
		if(Mouse.getEventX() >= this.getPositionX() &&  game.mapHeight - Mouse.getEventY() >= this.getPositionY() ){
			//g.drawImage(wizardFrontRight, this.getPositionX()-(wizardFrontRight.getWidth()/2), this.getPositionY()-(wizardFrontRight.getHeight()/2));
		}else if(Mouse.getEventX() <= this.getPositionX() && game.mapHeight- Mouse.getEventY() >= this.getPositionY() ){
			//g.drawImage(wizardFrontLeft, this.getPositionX()-(wizardFrontLeft.getWidth()/2), this.getPositionY()-(wizardFrontLeft.getHeight()/2));
		}else if (Mouse.getEventX() >= this.getPositionX() && game.mapHeight- Mouse.getEventY() <= this.getPositionY()){
			//g.drawImage(wizardBackRight, this.getPositionX() -(wizardBackRight.getWidth()/2), this.getPositionY()-(wizardBackRight.getHeight()/2));
		}else if (Mouse.getEventX() <= this.getPositionX() && game.mapHeight- Mouse.getEventY() <= this.getPositionY()){
			//g.drawImage(wizardBackLeft, this.getPositionX()-(wizardBackLeft.getWidth()/2), this.getPositionY()-(wizardBackLeft.getHeight()/2));
	
		}
	}
}
