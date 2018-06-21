package sparkless101.crosshairmod.gui.utils;

/**
 * Stores bounds for a rectangle.
 * 
 * @author Sparkless101
 *
 */
public class Bounds
{
	/**
	 * Stores the top edge of the boundary.
	 */
	private final int top;
	
	/**
	 * Stores the right edge of the boundary.
	 */
	private final int right;
	
	/**
	 * Stores the bottom edge of the boundary.
	 */
	private final int bottom;
	
	/**
	 * Stores the left edge of the boundary.
	 */
	private final int left;
	
	/**
	 * Creates the bounds with the given values.
	 * 
	 * @param top Value of the top edge of the boundary.
	 * @param right Value of the right edge of the boundary.
	 * @param bottom Value of the bottom edge of the boundary.
	 * @param left Value of the left edge of the boundary.
	 */
	public Bounds(int top, int right, int bottom, int left)
	{
		this.top = top;
		this.right= right;
		this.bottom = bottom;
		this.left = left;
	}

	public int getTop()
	{
		return this.top;
	}

	public int getRight()
	{
		return this.right;
	}

	public int getBottom()
	{
		return this.bottom;
	}

	public int getLeft()
	{
		return this.left;
	}
}
