package sparkless101.crosshairmod.gui.components;

import sparkless101.crosshairmod.crosshair.properties.property.FloatProperty;
import sparkless101.crosshairmod.crosshair.properties.property.IntegerProperty;
import sparkless101.crosshairmod.crosshair.properties.property.Property;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class Slider extends Component
{
	/**
	 * Position of the thumb relative to the left edge of the slider.
	 */
	private int thumbPosition;
	
	/**
	 * Stores whether the previous position of the mouse was over the thumb.
	 */
	private boolean isMouseOverThumb;
	
	/**
	 * Whether or not the thumb is currently being dragged.
	 */
	private boolean isDragging;
	
	/**
	 * Width and height of the thumb.
	 */
	private int thumbSize;
	
	/**
	 * Number of pixels inside the thumb where the mouse grabbed it.
	 */
	private int grabOffset;
	
	/**
	 * Background colour of the thumb.
	 */
	private RGBA thumbBackgroundColour;
	
	/**
	 * The current value.
	 */
	private float value;
	
	/**
	 * Smallest number the value can be.
	 */
	private float minValue;
	
	/**
	 * Largest number the value can be.
	 */
	private float maxValue;
	
	/**
	 * Whether the current value should be displayed as an integer or a float.
	 */
	private ValueType valueType;
	
	/**
	 * Defines how the current value should be formatted when displayed next to the slider.
	 * 
	 * @author Sparkless101
	 *
	 */
	public enum ValueType
	{
		/**
		 * Value is to be formatted as a float to 2 decimal places.
		 */
		FLOAT,
		
		/**
		 * Value is to be formatted as an integer.
		 */
		INTEGER;
	}
	
	public Slider(String label, int x, int y, int width, int minValue, int maxValue)
	{
		super(label, x, y, width, 13 + 3 + 10);
		
		this.thumbPosition = 0;
		
		this.isMouseOverThumb = false;
		
		this.isDragging = false;
		
		this.thumbSize = 10;
		
		this.grabOffset = 0;
		
		this.thumbBackgroundColour = Theme.PRIMARY;
		
		this.minValue = minValue;
		
		this.maxValue = maxValue;
		
		this.valueType = ValueType.INTEGER;
		
		this.hoverBackgroundColour = this.backgroundColour;
	}
	
	public void drawComponent()
	{
		// Draw title label
		RenderManager.drawString(this.label, this.x, this.y + 2, this.currentTextColour);
		
		// Draw track
		RenderManager.drawBorderedRectangle(this.x, this.y + 11 + 3 + (this.thumbSize / 2) - 1, this.x + this.width, this.y + 11 + 3 + (this.thumbSize / 2) + 1, 1.0F, this.currentBorderColour, this.currentBackgroundColour, true);
		
		// Draw thumb
		RenderManager.drawBorderedRectangle(this.x + this.thumbPosition, this.y + 11 + 3, this.x + this.thumbPosition + this.thumbSize, this.y + 11 + 3 + this.thumbSize, 1.0F, Theme.DARK_GREY, this.thumbBackgroundColour, true);
		
		// Draw value label
		RenderManager.drawString(this.getFormattedValue(), this.x + this.width + 5, this.y + 11 + 3 + (this.thumbSize / 2) - 4, this.currentTextColour);
	}
	
	public void onMouseDrag(int startX, int startY, int mouseX, int mouseY, long dragTime)
	{
		// Checks whether or not the thumb is being dragged
		// Sets the new position of the thumb
		// Sets the new value relative to the thumbs position 
		if (this.isDragging)
		{
			this.setThumbPosition(mouseX);
			
			this.setValue();
		}
	}
	
	/**
	 * Calculates the new position of the thumb, relative to the mouse position,<br>
	 * and makes sure it is within the valid bounds of the slider.
	 * 
	 * @param mouseX Current position of the mouse.
	 */
	private void setThumbPosition(int mouseX)
	{
		// Calculates the new position of the thumb, relative to the mouse position
		int newScrollValue = mouseX - this.x - this.grabOffset;
		
		// Calculates the maximum width of the scrollable area of the thumb
		int maxWidth = this.width - this.thumbSize;
		
		// Checks whether the new position of the thumb is within the valid bounds
		if (newScrollValue < 0) newScrollValue = 0;
		if (newScrollValue > maxWidth) newScrollValue = maxWidth;
		
		this.thumbPosition = newScrollValue;
	}
	
	/**
	 * Calculates the new value relative to the position of the thumb.
	 */
	private void setValue()
	{
		// Calculates the total length the thumb has travelled along the width of the slider
		float scrollRatio = this.thumbPosition / (float)(this.width - this.thumbSize);
		
		// Calculates the value relative to the thumb position
		this.value = this.minValue + ((this.maxValue - this.minValue) * scrollRatio);
		
		if (this.boundProperty != null)
		{
			// Updates the property value
			// Depending on whether the slider is a float or integer value
			if (this.valueType == ValueType.FLOAT)
			{
				CustomCrosshairMod.INSTANCE.getCrosshair().properties.set(this.boundProperty, new FloatProperty(this.value));
			}
			else
			{
				CustomCrosshairMod.INSTANCE.getCrosshair().properties.set(this.boundProperty, new IntegerProperty((int)this.value));
			}
		}
	}
	
	private void updateThumbPosition()
	{
		this.thumbPosition = (int)((this.width - this.thumbSize) * ((this.value - this.minValue) / (this.maxValue - this.minValue)));
	}
	
	@Override
	public void bindProperty(String property)
	{
		super.bindProperty(property);
		
		Property crosshairProperty = CustomCrosshairMod.INSTANCE.getCrosshair().properties.get(this.boundProperty);
		
		if (crosshairProperty instanceof IntegerProperty)
		{
			this.value = (int)crosshairProperty.getValue();
		}
		else if (crosshairProperty instanceof FloatProperty)
		{
			this.value = (float)crosshairProperty.getValue();
		}
		
		this.updateThumbPosition();
	}
	
	public void onMouseDown(int mouseX, int mouseY)
	{
		if (this.isOverThumb(mouseX, mouseY))
		{
			this.isDragging = true;
			
			this.grabOffset = mouseX - this.thumbPosition - this.thumbSize - 3;
		}
	}
	
	public void onMouseUp(int mouseX, int mouseY)
	{
		this.isDragging = false;
	}
	
	public void onMouseMove(int mouseX, int mouseY)
	{
		boolean isMouseOverThumb = this.isOverThumb(mouseX, mouseY);
		
		if (this.isMouseOverThumb != isMouseOverThumb)
		{
			if (isMouseOverThumb)
			{
				this.thumbBackgroundColour = Theme.SECONDARY;
			}
			else
			{
				this.thumbBackgroundColour = Theme.PRIMARY;
			}
			
			this.isMouseOverThumb = isMouseOverThumb;
		}
	}
	
	public void onMouseLeave()
	{
		super.onMouseLeave();
		
		this.isDragging = false;
	}
	
	/**
	 * Checks whether the given X and Y position is within the bounds of the thumb.
	 * 
	 * @param mouseX X position to check.
	 * @param mouseY Y position to check.
	 * 
	 * @return Whether or not the given X and Y position is within the bounds of the thumb.
	 */
	private boolean isOverThumb(int mouseX, int mouseY)
	{
		if (mouseX > this.x + this.thumbPosition &&
			mouseX < this.x + this.thumbPosition + this.thumbSize)
		{
			if (mouseY > this.y + 11 + 3 &&
				mouseY < this.y + 11 + 3 + this.thumbSize)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void setValueType(ValueType valueType)
	{
		this.valueType = valueType;
	}
	
	/**
	 * Gets the string value of the current value to be displayed.
	 * 
	 * @return Formatted value.
	 */
	private String getFormattedValue()
	{
		if (this.valueType == ValueType.FLOAT)
		{
			return String.format("%.2f", this.value); // Formats the value to 2 decimal places
		}
		
		return String.valueOf(Math.round(this.value));
	}
}
