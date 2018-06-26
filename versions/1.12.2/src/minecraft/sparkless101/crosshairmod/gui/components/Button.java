package sparkless101.crosshairmod.gui.components;

import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.gui.utils.RenderManager;

public class Button extends Component
{
	public Button(Screen parentScreen, String label, int x, int y, int width, int height)
	{
		super(parentScreen, label, x, y, width, height);
	}
	
	public void drawComponent()
	{
		RenderManager.drawBorderedRectangle(this.x, this.y, this.x + this.width, this.y + this.height, 1.0F, this.currentBorderColour, this.currentBackgroundColour, true);
		
		RenderManager.drawString(this.label, x + (this.width / 2) - (RenderManager.getTextWidth(this.label) / 2), this.y + (this.height / 2) - 3, this.currentTextColour);
	}
}
