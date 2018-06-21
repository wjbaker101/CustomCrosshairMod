package sparkless101.crosshairmod.gui.components;

import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class ButtonCircle extends Button
{
	public ButtonCircle(String label, int x, int y, int width, int height)
	{
		super(label, x, y, width, height);
	}
	
	public void drawComponent()
	{
		//RenderManager.draw(this.x, this.y, this.x + this.width, this.y + this.height, 1.0F, Theme.DARK_GREY, Theme.PRIMARY, true);
		RenderManager.drawString(this.label, x + (this.width / 2) - (RenderManager.getTextWidth(this.label) / 2), this.y + (this.height / 2) - 4, Theme.WHITE);
	}
}
