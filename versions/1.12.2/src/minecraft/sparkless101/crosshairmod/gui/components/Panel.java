package sparkless101.crosshairmod.gui.components;

import java.util.ArrayList;
import java.util.List;

import sparkless101.crosshairmod.gui.components.interfaces.IComponentMouseEvents;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class Panel extends Component
{
	/**
	 * The number of pixels components should be away from the left edge of the panel.
	 */
	protected int xOffset;

	/**
	 * The number of pixels components should be away from the top edge of the panel.
	 */
	protected int yOffset;
	
	private int componentsHeight;
	
	protected int componentSpacing;
	
	public Panel(int x, int y, int width, int height)
	{
		super("", x, y, width, height);
		
		this.borderColour = Theme.DARK_GREY;
		this.backgroundColour = Theme.TRANSPARENT;
		this.hoverBackgroundColour = Theme.TRANSPARENT;
		
		this.xOffset = 7;
		this.yOffset = 7;
		
		this.componentsHeight = 0;
		
		this.componentSpacing = 5;
	}
	
	public void drawComponent()
	{
		RenderManager.drawBorderedRectangle(this.x, this.y, this.x + this.width, this.y + this.height, 1.0F, this.currentBorderColour, this.currentBackgroundColour, true);
		
		this.drawComponents();
	}
	
	private void drawComponents()
	{
		int height = this.y + this.yOffset;
		
		for (Component component : this.components)
		{
			component.setPosition(this.x + this.xOffset, height);
			
			height += component.height + this.componentSpacing;
			
			component.drawComponent();
		}
	}
	
	public void addComponent(Component component)
	{
		this.components.add(component);
	}
	
	public void removeComponent(Component component)
	{
		this.components.remove(component);
	}
	
	public void packComponent()
	{
		int height = this.yOffset + this.yOffset;
		
		for (Component component : this.components)
		{
			height += component.height + this.componentSpacing;
		}
		
		if (this.components.size() > 0) height -= this.componentSpacing;
		
		this.height = height;
	}
}
