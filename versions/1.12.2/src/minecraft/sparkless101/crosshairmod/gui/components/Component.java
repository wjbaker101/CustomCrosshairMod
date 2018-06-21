package sparkless101.crosshairmod.gui.components;

import java.util.ArrayList;
import java.util.List;

import sparkless101.crosshairmod.crosshair.properties.property.Property;
import sparkless101.crosshairmod.gui.components.interfaces.ComponentStyle;
import sparkless101.crosshairmod.gui.components.interfaces.IComponentMouseEvents;
import sparkless101.crosshairmod.gui.utils.Bounds;
import sparkless101.crosshairmod.gui.utils.Theme;

/**
 * The base component that can be displayed on a screen.
 * 
 * @author Sparkless101
 *
 */
public abstract class Component extends ComponentStyle implements IComponentMouseEvents
{
	/**
	 * Stores a list of components that may be within this component.
	 */
	protected List<Component> components;
	
	/**
	 * Text to be displayed with the component.
	 */
	protected String label;
	
	/**
	 * X position of the component relative to the left side of the screen.
	 */
	protected int x;
	
	/**
	 * Y position of the component relative to the top side of the screen.
	 */
	protected int y;
	
	/**
	 * Width of the component.
	 */
	protected int width;
	
	/**
	 * Height of the component.
	 */
	protected int height;
	
	/**
	 * Stores whether the mouse is currently over the component.
	 */
	protected boolean isMouseOver;
	
	/**
	 * Stores a property of the crosshair to associate with the component.
	 * Null if no property is bound.
	 */
	protected String boundProperty;
	
	/**
	 * Stores the bounds of the component.<br>
	 * The clickable region of the component.
	 */
	private Bounds bounds;
	
	public Component(String label, int x, int y, int width, int height)
	{
		super();
		
		this.label = label;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.components = new ArrayList<Component>();
		
		this.bounds = new Bounds(this.y, this.x + this.width, this.y + this.height, this.x);
	}
	
	/**
	 * Draws the component onto the screen.
	 */
	public void drawComponent() {}
	
	/**
	 * Adds the given alias of a property to the component.
	 * 
	 * @param alias Alias of the property
	 */
	public void bindProperty(String alias)
	{
		this.boundProperty = alias;
	}
	
	/**
	 * Set the bounds of the component.
	 * 
	 * @param bounds New bounds.
	 */
	public void setBounds(Bounds bounds)
	{
		this.bounds = bounds;
	}
	
	/**
	 * Returns the bound object of the component.
	 * 
	 * @return Bounds
	 */
	public Bounds getBounds()
	{
		return this.bounds;
	}
	
	/**
	 * Checks whether the given x and y coordinates are within the bounds of the component.
	 * 
	 * @param x X position to check.
	 * @param y Y position to check.
	 * @return True if coordinates are within the bounds.
	 */
	public boolean isWithinBounds(int x, int y)
	{
		if ((x > this.getBounds().getLeft() - 1) && (x < this.getBounds().getRight() + 1)) // Check horizontal bounds
		{
			if ((y > this.getBounds().getTop() - 1) && (y < this.getBounds().getBottom() + 1)) // Check vertical bounds
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void onMouseEnter()
	{
		this.currentBackgroundColour = this.hoverBackgroundColour;
		this.currentBorderColour = this.hoverBorderColour;
		this.currentTextColour = this.hoverTextColour;
	}
	
	@Override
	public void onMouseLeave()
	{
		this.currentBackgroundColour = this.backgroundColour;
		this.currentBorderColour = this.borderColour;
		this.currentTextColour = this.textColour;
	}
	
	@Override
	public void setMouseOver(boolean isMouseOver)
	{
		// Checks whether the new value is different from the current value
		if (this.isMouseOver != isMouseOver)
		{
			// Calls an event depending on whether the mouse is entering or leaving
			if (isMouseOver)
				this.onMouseEnter();
			else
				this.onMouseLeave();
			
			this.isMouseOver = isMouseOver;
		}
	}
	
	@Override
	public void onMouseMove(int mouseX, int mouseY)
	{
		this.components.forEach((component) ->
		{
			component.setMouseOver(false);
			
			if (component.isWithinBounds(mouseX, mouseY))
			{
				component.setMouseOver(true);
				
				component.onMouseMove(mouseX, mouseY);
			}
		});
	}
	
	@Override
	public void onMouseUp(int mouseX, int mouseY)
	{
		this.components.forEach((component) ->
		{
			if (!component.isWithinBounds(mouseX, mouseY)) return;
			
			component.onMouseUp(mouseX, mouseY);
		});
	}
	
	@Override
	public void onMouseDown(int mouseX, int mouseY)
	{
		this.components.forEach((component) ->
		{
			if (!component.isWithinBounds(mouseX, mouseY)) return;
			
			component.onMouseDown(mouseX, mouseY);
		});
	}
	
	@Override
	public void onMouseDrag(int startX, int startY, int mouseX, int mouseY, long dragTime)
	{
		this.components.forEach((component) -> component.onMouseDrag(startX, startY, mouseX, mouseY, dragTime));
	}
	
	/**
	 * Sets the X and Y position of the component.
	 * 
	 * @param x New X position.
	 * @param y New Y position.
	 */
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		this.bounds = new Bounds(this.y, this.x + this.width, this.y + this.height, this.x);
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getHeight()
	{
		return this.height;
	}
}
