package sparkless101.crosshairmod.crosshair.style.styles;

import net.minecraft.client.Minecraft;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.style.CrosshairStyle;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class CircleStyle extends CrosshairStyle
{
	public CircleStyle(Minecraft mc, Crosshair crosshair)
	{
		super(mc, crosshair);
	}

	/**
	 * Draws the circle crosshair.
	 */
	@Override
	public void drawCrosshairStyle(int drawX, int drawY, RGBA renderColour)
	{
		if (!this.shouldRenderCrosshair()) return;
		
		boolean outlineEnabled = (boolean)this.crosshair.getProperties().get("outline_enabled").getValue();
		float thickness = (int)this.crosshair.getProperties().get("crosshair_thickness").getValue() / 2.0F;
		
		RGBA colour = this.getRenderColour();
		
		if (outlineEnabled) this.drawOutline(drawX, drawY);
		
		RenderManager.drawTorus(drawX, drawY, this.calculateRenderGap(), this.calculateRenderGap() + (int)thickness, colour, true);
	}
	
	/**
	 * Draws the outline around the crosshair.
	 * 
	 * @param drawX X position of the crosshair.
	 * @param drawY Y position of the crosshair.
	 */
	private void drawOutline(int drawX, int drawY)
	{
		RGBA outlineColour = (RGBA)this.crosshair.getProperties().get("outline_colour").getValue();

		float thickness = (int)this.crosshair.getProperties().get("crosshair_thickness").getValue() / 2.0F;
		
		RenderManager.drawCircle(drawX, drawY, this.calculateRenderGap() + 0.5F + thickness, 2.0F, outlineColour, true);
		RenderManager.drawCircle(drawX, drawY, this.calculateRenderGap() - 0.5F, 2.0F, outlineColour, true);
	}
}
