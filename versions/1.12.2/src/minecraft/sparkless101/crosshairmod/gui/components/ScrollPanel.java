package sparkless101.crosshairmod.gui.components;

import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class ScrollPanel extends Panel
{
	/**
	 * Store whether the mouse is currently over the thumb.
	 */
	private boolean isMouseOverThumb;
	
	/**
	 * Stores whether the thumb is currently being dragged.
	 */
	private boolean isDragging;
	
	/**
	 * Stores the width of the thumb.
	 */
	private int thumbWidth;
	
	/**
	 * Stores the height of the thumb.
	 */
	private int thumbHeight;
	
	/**
	 * Stores how far into the thumb it was grabbed by the mouse.
	 */
	private int grabOffset;
	
	/**
	 * Stores the current scroll value of the panel.
	 */
	private int currentScrollValue;
	
	/**
	 * Stores the background colour of the thumb.
	 */
	private RGBA thumbBackgroundColour;
	
	/**
	 * Stores the total height of the content inside the panel.
	 */
	private int contentHeight;
	
	public ScrollPanel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		this.isMouseOverThumb = false;
		
		this.isDragging = false;
		
		this.thumbWidth = 9;
		
		this.thumbHeight = 30;
		
		this.grabOffset = 0;
		
		this.currentScrollValue = 0;
		
		this.thumbBackgroundColour = Theme.PRIMARY;
		
		this.contentHeight = 0;
	}
	
	@Override
	public void drawComponent()
	{
		RenderManager.drawBorderedRectangle(this.x + this.width - this.thumbWidth, this.y, this.x + this.width, this.y + this.height, 1.0F, Theme.DARK_GREY, Theme.PRIMARY, true);

		RenderManager.drawBorderedRectangle(this.x + this.width - this.thumbWidth, this.y + this.currentScrollValue, this.x + this.width, this.y + this.currentScrollValue + this.thumbHeight, 1.0F, Theme.DARK_GREY, this.thumbBackgroundColour, true);
		
		this.components.forEach((component) -> component.drawComponent());
	}
	
	@Override
	public void onMouseDrag(int startX, int startY, int mouseX, int mouseY, long dragTime)
	{
		if (this.isDragging)
		{
			int newScrollValue = mouseY - this.y - this.grabOffset;
			
			this.setScrollValue(newScrollValue);
		}
		
		super.onMouseDrag(startX, startY, mouseX, mouseY, dragTime);
	}
	
	@Override
	public void onMouseDown(int mouseX, int mouseY)
	{
		if (this.isOverThumb(mouseX, mouseY))
		{
			this.isDragging = true;
			
			this.grabOffset = mouseY - this.currentScrollValue - this.thumbHeight - 5;
		}
		
		super.onMouseDown(mouseX, mouseY);
	}
	
	@Override
	public void onMouseUp(int mouseX, int mouseY)
	{
		this.isDragging = false;
		
		super.onMouseUp(mouseX, mouseY);
	}
	
	@Override
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
		
		super.onMouseMove(mouseX, mouseY);
	}
	
	private void setComponentPositions()
	{
		int outsideContentHeight = (this.contentHeight + this.yOffset) - this.height;
		
		float scrollRatio = -this.currentScrollValue / (float)(this.height - this.thumbHeight);
		
		int height = (int)(outsideContentHeight * scrollRatio);
		
		for (Component component : this.components)
		{
			component.setPosition(this.x + this.xOffset, this.y + this.yOffset + height);
			
			height += component.height + this.componentSpacing;
		}
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
		if (mouseX > this.x + this.width - this.thumbWidth &&
			mouseX < this.x + this.width)
		{
			if (mouseY > this.y + this.currentScrollValue &&
				mouseY < this.y + this.currentScrollValue + this.thumbHeight)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Scrolls the panel by the given increment.
	 * 
	 * @param increment The amount to scroll the panel up or down by.
	 */
	public void incrementScroll(int increment)
	{
		int newScrollValue = this.currentScrollValue + increment;
		
		this.setScrollValue(newScrollValue);
	}
	
	/**
	 * Sets the scroll value of the panel.<br>
	 * Then updates the positions of the components.
	 * 
	 * @param scrollValue New scroll value.
	 */
	private void setScrollValue(int scrollValue)
	{
		int newScrollValue = scrollValue;
		
		// Calculates the scrollable height of the thumb
		int maxHeight = this.height - this.thumbHeight;
		
		if (newScrollValue < 0) newScrollValue = 0;
		if (newScrollValue > maxHeight) newScrollValue = maxHeight;
		
		this.currentScrollValue = newScrollValue;
		
		this.setComponentPositions();
	}
	
	/**
	 * Adds a new component.<br>
	 * Add the height of the new component to the total content height.
	 */
	@Override
	public void addComponent(Component component)
	{
		this.contentHeight += (component.height + this.componentSpacing);
		
		super.addComponent(component);
	}
	
	/**
	 * Removes a component.<br>
	 * Takes away the height of the old component to the total content height.
	 */
	@Override
	public void removeComponent(Component component)
	{
		this.contentHeight -= (component.height + this.componentSpacing);
		
		super.removeComponent(component);
	}
	
	@Override
	public void packComponent()
	{
		super.packComponent();
		
		this.setComponentPositions();
	}
}
