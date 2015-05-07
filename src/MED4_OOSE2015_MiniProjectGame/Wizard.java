package MED4_OOSE2015_MiniProjectGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Wizard extends Hero implements MousePressedListener
{
	private Image wizardFrontRight, wizardFrontLeft, wizardBackRight, wizardBackLeft;
	private Sound sound;
	//	private int mana;

	Wizard(SimpleSlickGame game, int x, int y)
	{
		super(game, x,y);

		game.addMousePressedListener(this);

		setHitboxRadius(30);
		//	mana = 100;
		try 
		{
			wizardFrontRight = new Image ("Graphics/Wizard full (front right).png");
			wizardFrontLeft = new Image ("Graphics/Wizard full (front left).png");
			wizardBackRight = new Image ("Graphics/Wizard full (back right).png");
			wizardBackLeft = new Image ("Graphics/Wizard full (back left).png");

		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not find sprite");
		}

		this.setSprite(wizardFrontLeft);
		this.setHealth(100);

		try {
			sound = new Sound("Sounds/WizardHit.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Collision(Entity e)
	{
		super.Collision(e);

		if (e instanceof Enemy)
		{
			float pitch = ((float)game.r.nextInt(300) +1100)/1000;

			try
			{
				sound.play(pitch, 1f);
			} catch (NullPointerException n) {
				System.out.println("Could not find Wizard sound file");
			}	
			//Should have already lost health from super collision - only play sound its specific sound
			//could also play a specific death animation and other specific stuff for the wizard
		}
	}

	public void mousePressed(int button, int mousePosX, int mousePosY)
	{
		//TEMPORARY SOLUTION!: (the if statement) - there is a bug where the game wont remove 
		//the instance from the MousePressedListener list. To fix it we check whether the entity is alive or not. 
		if(this.isAlive)
		{
			Missile missile = new Missile(game, (int)getPositionX(), (int)getPositionY(), mousePosX, mousePosY, this);
			game.getEntities().add(missile);
		}
	}

	@Override
	public void close()
	{
		game.removeMousePressedListener(this);
		super.close();
	}

	public void spriteSwitch () 
	{
		if(Mouse.getEventX() >= this.getPositionX() &&  game.appgc.getHeight() - Mouse.getEventY() >= this.getPositionY() ){
			this.setSprite(wizardFrontRight);
		}
		else if(Mouse.getEventX() <= this.getPositionX() && game.appgc.getHeight() - Mouse.getEventY() >= this.getPositionY() ){
			this.setSprite(wizardFrontLeft);
		}
		else if (Mouse.getEventX() >= this.getPositionX() && game.appgc.getHeight() - Mouse.getEventY() <= this.getPositionY()){
			this.setSprite(wizardBackRight);
		}
		else if (Mouse.getEventX() <= this.getPositionX() && game.appgc.getHeight() - Mouse.getEventY() <= this.getPositionY()){
			this.setSprite(wizardBackLeft);
		}
	}
}
