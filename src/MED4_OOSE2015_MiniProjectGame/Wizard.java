package MED4_OOSE2015_MiniProjectGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

//Wizard extends the Hero class and implements the MousePressedListener
public class Wizard extends Hero implements MousePressedListener
{
	//Variables and images for the wizard class initialized
	private Image wizardFrontRight, wizardFrontLeft, wizardBackRight, wizardBackLeft;
	private Sound sound;

	/**
	 * The constructor sets the X and Y position of the entity and refers to the slick game in to reach its variables and functions
	 * @param game
	 * @param x
	 * @param y
	 */
	Wizard(SimpleSlickGame game, int x, int y)
	{
		//The super constructor (the Entity constructor) is called to give the x and y position and refer to game.
		super(game, x,y);

		//added a mouse listener to know when the mouse has been pressed
		game.addMousePressedListener(this);

		//sets the wizards hitbox radius to 30 pixels
		setHitboxRadius(30);
		
		//Adds sprites to the entity
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
		
		//Sets the initial sprite
		this.setSprite(wizardFrontLeft);
		//sets the wizards initial health to 100
		this.setHealth(100);

		//Adds sound files to the entity
		try {
			sound = new Sound("Sounds/WizardHit.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Overrides the collision() of the Entity parent class to check if the entity collided with is an enemy and if so play a randomly pitched sound file.
	 * @param Entity e
	 */
	@Override
	public void Collision(Entity e)
	{
		//The super.Collision() is called (the Hero Collision method) to decrease the health of the hero
		super.Collision(e);

		if (e instanceof Enemy)
		{
			//sets new pitch
			float pitch = ((float)game.r.nextInt(300) +1100)/1000;

			//Plays the sound file
			try
			{
				sound.play(pitch, 1f);
			} catch (NullPointerException n) {
				System.out.println("Could not find Wizard sound file");
			}				
		}
	}

	/**
	 * If mouse is pressed, the function creates a new missile
	 * @param int button
	 * @param int mousePosX
	 * @param int mousePosY
	 */
	public void mousePressed(int button, int mousePosX, int mousePosY)
	{
		//TEMPORARY SOLUTION!: (the if statement) - there is a bug where the game wont remove 
		//the instance from the MousePressedListener list. To fix it we check whether the entity is alive or not. 
		if(this.isAlive)
		{
			//creates a new missile and adds it to the entities list
			Missile missile = new Missile(game, (int)getPositionX(), (int)getPositionY(), mousePosX, mousePosY, this);
			game.getEntities().add(missile);
		}
	}

	/**
	 * close() closes the entity by calling the super function of close() and removes the mouselistener, so the wizard class can't create missiles
	 */
	@Override
	public void close()
	{
		game.removeMousePressedListener(this);
		super.close();
	}

	/**
	 * spriteSwitch controls the switching of sprites for the wizard
	 */
	public void spriteSwitch () 
	{
		//if and if else statements checks where the mouse is positioned according to the hero
		if(Mouse.getEventX() >= this.getPositionX() &&  game.appgc.getHeight() - Mouse.getEventY() >= this.getPositionY() ){
			//set new sprite
			this.setSprite(wizardFrontRight);
		}
		else if(Mouse.getEventX() <= this.getPositionX() && game.appgc.getHeight() - Mouse.getEventY() >= this.getPositionY() ){
			//set new sprite
			this.setSprite(wizardFrontLeft);
		}
		else if (Mouse.getEventX() >= this.getPositionX() && game.appgc.getHeight() - Mouse.getEventY() <= this.getPositionY()){
			//set new sprite
			this.setSprite(wizardBackRight);
		}
		else if (Mouse.getEventX() <= this.getPositionX() && game.appgc.getHeight() - Mouse.getEventY() <= this.getPositionY()){
			//set new sprite
			this.setSprite(wizardBackLeft);
		}
	}
}
