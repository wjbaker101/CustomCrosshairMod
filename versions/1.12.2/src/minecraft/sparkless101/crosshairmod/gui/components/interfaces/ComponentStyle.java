package sparkless101.crosshairmod.gui.components.interfaces;

import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.Theme;

public abstract class ComponentStyle
{
	/**
	 * Stores the default background colour of the component.
	 */
	protected RGBA backgroundColour;
	
	/**
	 * Stores the background colour of the component when the mouse is over it.
	 */
	public RGBA hoverBackgroundColour;
	
	/**
	 * Stores the current background colour, to use when rendering.
	 */
	protected RGBA currentBackgroundColour;
	
	/**
	 * Stores the default border colour of the component.
	 */
	protected RGBA borderColour;
	
	/**
	 * Stores the border colour of the component when the mouse is over it.
	 */
	public RGBA hoverBorderColour;
	
	/**
	 * Stores the current border colour, to use when rendering.
	 */
	protected RGBA currentBorderColour;
	
	/**
	 * Stores the default text colour of the component.
	 */
	protected RGBA textColour;
	
	/**
	 * Stores the text colour of the component when the mouse is over it.
	 */
	public RGBA hoverTextColour;
	
	/**
	 * Stores the current text colour, to use when rendering.
	 */
	protected RGBA currentTextColour;
	
	/**
	 * Creates the default style of the component.
	 */
	public ComponentStyle()
	{
		// Background style
		this.backgroundColour = Theme.PRIMARY;
		this.hoverBackgroundColour = Theme.SECONDARY;
		this.currentBackgroundColour = this.backgroundColour;
		
		// Border style
		this.borderColour = Theme.DARK_GREY;
		this.hoverBorderColour = this.borderColour;
		this.currentBorderColour = this.borderColour;
		
		// Text style
		this.textColour = Theme.WHITE;
		this.hoverTextColour = this.textColour;
		this.currentTextColour = this.textColour;
	}
	
	/**
	 * Sets the default background colour of the component.<br>
	 * Also updates the current background colour.
	 * 
	 * @param backgroundColour New background colour of the component.
	 */
	public void setBackgroundColour(RGBA backgroundColour)
	{
		this.backgroundColour = backgroundColour;
		
		this.currentBackgroundColour = this.backgroundColour;
	}

	/**
	 * Sets the default border colour of the component.<br>
	 * Also updates the current border colour.
	 * 
	 * @param borderColour New border colour of the component.
	 */
	public void setBorderColour(RGBA borderColour)
	{
		this.borderColour = borderColour;
		
		this.currentBorderColour = this.borderColour;
	}
	
	/**
	 * Sets the default text colour of the component.<br>
	 * Also updates the current text colour.
	 * 
	 * @param textColour New text colour of the component.
	 */
	public void setTextColour(RGBA textColour)
	{
		this.textColour = textColour;
		
		this.currentTextColour = this.textColour;
	}
}
