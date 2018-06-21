package sparkless101.crosshairmod.gui.components;

import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class ButtonMenu extends ButtonCircle
{
	public ButtonMenu(int x, int y, int width, int height)
	{
		super("", x, y, width, height);
	}
	
	public void drawComponent()
	{
		super.drawComponent();
		
		RenderManager.drawLine(100, 100, 110, 100, 2.0F, Theme.WHITE, true);
		RenderManager.drawLine(100, 103, 110, 103, 2.0F, Theme.WHITE, true);
		RenderManager.drawLine(100, 106, 110, 106, 2.0F, Theme.WHITE, true);
	}
}
