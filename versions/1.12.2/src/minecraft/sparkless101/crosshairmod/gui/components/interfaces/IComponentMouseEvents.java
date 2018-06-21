package sparkless101.crosshairmod.gui.components.interfaces;

/**
 * Interface containing methods called when an event occurs related to the mouse.
 * 
 * @author Sparkless101
 *
 */
public interface IComponentMouseEvents
{
	/**
	 * Called when the mouse enters the bounds of the component.
	 */
	public void onMouseEnter();
	
	/**
	 * Called when the mouse leaves the bounds of the component.
	 */
	public void onMouseLeave();
	
	/**
	 * Sets whether the mouse is over the component or not.
	 * 
	 * @param isMouseOver
	 */
	public void setMouseOver(boolean isMouseOver);
	
	/**
	 * Called when the mouse is moved within the component.
	 * 
	 * @param mouseX X position of the mouse.
	 * @param mouseY Y position of the mouse.
	 */
	public void onMouseMove(int mouseX, int mouseY);
	
	/**
	 * Called when the mouse is depressed.
	 */
	public void onMouseUp(int mouseX, int mouseY);
	
	/**
	 * Called when the mouse if pressed.
	 * 
	 * @param mouseX X position of the mouse.
	 * @param mouseY Y position of the mouse.
	 */
	public void onMouseDown(int mouseX, int mouseY);
	
	/**
	 * Called when the mouse is pressed and moved.
	 * 
	 * @param mouseX X position of the mouse.
	 * @param mouseY Y position of the mouse.
	 */
	/**
	 * Called when the mouse is pressed and moved.
	 * 
	 * @param startX X position of the mouse when the dragging started.
	 * @param startY Y position of the mouse when the dragging started.
	 * @param mouseX Current X position of the mouse.
	 * @param mouseY Current Y position of the mouse.
	 * @param dragTime Number of ticks the mouse has been dragged for.
	 */
	public void onMouseDrag(int startX, int startY, int mouseX, int mouseY, long dragTime);
}
