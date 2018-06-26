package sparkless101.crosshairmod.gui.components;

import net.minecraft.client.Minecraft;
import sparkless101.crosshairmod.gui.screens.Screen;
import sparkless101.crosshairmod.gui.screens.screen.edit_colour.EditColourScreen;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class ColourPicker extends Component
{
	public ColourPicker(Screen parentScreen, String label, int x, int y)
	{
		super(parentScreen, label, x, y, 25, 25);
		
		this.backgroundColour = Theme.PRIMARY;
		this.hoverBackgroundColour = Theme.SECONDARY;
	}
	
	public void drawComponent()
	{
		RenderManager.drawBorderedRectangle(this.x, this.y, this.x + this.height, this.y + this.height, 1.0F, Theme.DARK_GREY, this.currentBackgroundColour, true);
		
		RenderManager.drawString(this.label, this.x + this.width + 3, this.y + (this.height / 2) - 3, Theme.WHITE);
		
		if (this.boundProperty != null)
		{
			RGBA colour = ((RGBA)CustomCrosshairMod.INSTANCE.getCrosshair().getProperties().get(boundProperty).getValue());
			
			RenderManager.drawFilledRectangle(this.x + 2, this.y + 2, this.x + 2 + this.width - 4, this.y + 2 + this.height - 4, colour, true);
		}
	}
	
	public void onMouseUp(int mouseX, int mouseY)
	{
		Minecraft.getMinecraft().displayGuiScreen(new EditColourScreen(this.boundProperty));
	}
}
