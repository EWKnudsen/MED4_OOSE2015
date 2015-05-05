package MED4_OOSE2015_MiniProjectGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wizard extends Hero
{
	private Image wizardFrontRight, wizardFrontLeft, wizardBackRight, wizardBackLeft;
//	private int mana;

	Wizard(SimpleSlickGame game, int x, int y)
	{
		
		super(game, x,y);
		setHitboxRadius(30);
	//	mana = 100;
		try {
			wizardFrontRight = new Image ("Graphics/Wizard full (front right).png");
			wizardFrontLeft = new Image ("Graphics/Wizard full (front left).png");
			wizardBackRight = new Image ("Graphics/Wizard full (back right).png");
			wizardBackLeft = new Image ("Graphics/Wizard full (back left).png");

		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not find sprite");
		}
		this.setSprite(wizardFrontLeft);
	}

	public void spriteSwitch () 
	{
		if(Mouse.getEventX() >= this.getPositionX() &&  game.mapHeight - Mouse.getEventY() >= this.getPositionY() ){
			this.setSprite(wizardFrontRight);
		}
		else if(Mouse.getEventX() <= this.getPositionX() && game.mapHeight- Mouse.getEventY() >= this.getPositionY() ){
			this.setSprite(wizardFrontLeft);
		}
		else if (Mouse.getEventX() >= this.getPositionX() && game.mapHeight- Mouse.getEventY() <= this.getPositionY()){
			this.setSprite(wizardBackRight);
		}
		else if (Mouse.getEventX() <= this.getPositionX() && game.mapHeight- Mouse.getEventY() <= this.getPositionY()){
			this.setSprite(wizardBackLeft);
		}
	}
}
