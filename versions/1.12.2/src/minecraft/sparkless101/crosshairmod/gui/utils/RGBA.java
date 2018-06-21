package sparkless101.crosshairmod.gui.utils;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Blue;

public class RGBA
{
	/**
     * Stores how red the colour is.
     */
    private final int RED;

    /**
     * Stores how green the colour is.
     */
    private final int GREEN;

    /**
     * Stores how blue the colour is.
     */
    private final int BLUE;

    /**
     * Stores the opacity of the colour.
     */
    private final int OPACITY;

    /**
     * Creates the colour from the given red, green, blue and opacity.
     * 
     * @param red Red component of the colour.
     * @param green Green component of the colour.
     * @param blue Blue component of the colour.
     * @param opacity Opacity component of the colour.
     */
    public RGBA(int red, int green, int blue, int opacity)
    {
    	int finalRed = this.checkValueBounds(red);
    	int finalGreen = this.checkValueBounds(green);
    	int finalBlue = this.checkValueBounds(blue);
    	int finalOpacity = this.checkValueBounds(opacity);
    	
        this.RED = finalRed;
        this.GREEN = finalGreen;
        this.BLUE = finalBlue;
        this.OPACITY = finalOpacity;
    }
    
    /**
     * Checks whether the value is within the colour bounds of 0 and 255.<br>
     * Updates the value to become the lower or upper bound if outside of the bounds.
     * 
     * @param value The value to check
     * @return Value, or the updated value.
     */
    private int checkValueBounds(int value)
    {
    	if (value < 0) return 0;
    	
    	if (value > 255) return 255;
    	
    	return value;
    }

    /**
     * Outputs the colour as a string.
     */
    public String toString()
    {
        return String.format("%d/%d/%d/%d", this.RED, this.GREEN, this.BLUE, this.OPACITY);
    }

    /**
     * Gets the red component of the colour.
     * 
     * @return Red component value between 0 and 255.
     */
    public int getRed()
    {
        return this.RED;
    }
    
    /**
     * Creates a new colour with the given red component.
     * 
     * @param red Red component value.
     * @return New colour with the new red value.
     */
    public RGBA setRed(int red)
    {
        return new RGBA(red, this.GREEN, this.BLUE, this.OPACITY);
    }

    /**
     * Gets the green component of the colour.
     * 
     * @return Green component value between 0 and 255.
     */
    public int getGreen()
    {
        return this.GREEN;
    }

    /**
     * Creates a new colour with the given green component.
     * 
     * @param green Green component value.
     * @return New colour with the new green value.
     */
    public RGBA setGreen(int green)
    {
        return new RGBA(this.RED, green, this.BLUE, this.OPACITY);
    }

    /**
     * Gets the blue component of the colour.
     * 
     * @return Blue component value between 0 and 255.
     */
    public int getBlue()
    {
        return this.BLUE;
    }

    /**
     * Creates a new colour with the given blue component.
     * 
     * @param blue Blue component value.
     * @return New colour with the new blue value.
     */
    public RGBA setBlue(int blue)
    {
        return new RGBA(this.RED, this.GREEN, blue, this.OPACITY);
    }

    /**
     * Gets the opacity component of the colour.
     * 
     * @return Opacity component value between 0 and 255.
     */
    public int getOpacity()
    {
        return this.OPACITY;
    }

    /**
     * Creates a new colour with the given opacity component.
     * 
     * @param opacity Opacity Red component value.
     * @return New colour with the new opacity value.
     */
    public RGBA setOpacity(int opacity)
    {
        return new RGBA(this.RED, this.GREEN, this.BLUE, opacity);
    }
}
