package MED4_OOSE2015_MiniProjectGame;

public class Vector2 
{
	/**
	 * Maximum values for x,y
	 */
	public final static double MAX_X=100, MAX_Y=100;

	/**
	 * Values for the 2D vector
	 */
	private double x,y;

	/**
	 * Initialization of the vector
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public Vector2(double x, double y) 
	{
		this.x = x;
		this.y = y;
		CheckValues();
	}

	/**
	 * Copy constructor
	 * @param other other object
	 */
	public Vector2(Vector2 other)
	{
		this.x = other.x;
		this.y = other.y;
		CheckValues();
	}
	
	public void CheckValues()
	{
		if(this.x > MAX_X) {
			this.x = MAX_X;
		}
		if(this.y > MAX_Y) {
			this.y = MAX_Y;
		}
	}

	/**
	 * Check equality between object and param object
	 * @param other other object
	 */
	public boolean equals(Vector2 other)
	{
		return other.x == this.x && other.y == this.y;
	}

	/**
	 * Returns length of vector
	 * @return float length of vector
	 */
	public double length()
	{
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	/**
	 * Scales vector
	 * @param y x scale
	 * @param y y scale
	 */
	public void scale(double x, double y) 
	{
		this.x *= x;
		this.y *= y;
		CheckValues();
	}

	/**
	 * Sums vector2 and return a new vector2
	 * @param Vector2
	 * @param Vector2
	 * @return Vector2 sum of vectors
	 */
	public static Vector2 sum(Vector2 a, Vector2 b)
	{
		return new Vector2(a.x + b.x, a.y + b.y);
	}
	
	/**
	 * subtracts one vector2 from other vector2 and returns a new Vector2
	 * @param Vector2
	 * @param Vector2
	 * @return Vector2 sum of vectors
	 */
	public static Vector2 difference(Vector2 a, Vector2 b)
	{
		return new Vector2(a.x - b.x, a.y - b.y);
	}

	/**
	 * gets the x value of the vector2.
	 * @param x
	 */
	public double getX() {   return this.x;   }

	/**
	 * sets the x value of the vector2.
	 * And checks if x is bigger than MAX_X - if it is, then is sets the x value to MAX_X.
	 * @param x
	 */
	public void setX(double x) 
	{
		this.x = x;
		CheckValues();
	}

	public double getY() {   return this.y;   }

	public void setY(double y) {
		this.y = y;
		CheckValues();
	}
}


