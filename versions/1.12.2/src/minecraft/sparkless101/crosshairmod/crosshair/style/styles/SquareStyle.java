package sparkless101.crosshairmod.crosshair.style.styles;

import net.minecraft.client.Minecraft;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.style.CrosshairStyle;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class SquareStyle extends CrosshairStyle
{
	public SquareStyle(Minecraft mc, Crosshair crosshair)
	{
		super(mc, crosshair);
	}

	/**
	 * Draws the square crosshair.
	 */
	@Override
	public void drawCrosshairStyle(int drawX, int drawY, RGBA renderColour)
	{
		if (!this.shouldRenderCrosshair()) return;
		
		boolean outlineEnabled = (boolean)this.crosshair.getProperties().get("outline_enabled").getValue();
		
		float thickness = (int)this.crosshair.getProperties().get("crosshair_thickness").getValue();
		
		int width = (int)this.crosshair.getProperties().get("crosshair_width").getValue();
		int height = (int)this.crosshair.getProperties().get("crosshair_height").getValue();
		
		int gap = this.calculateRenderGap();
		
		this.drawBars(drawX, drawY, gap, width, height, thickness);
		
		if (outlineEnabled) this.drawOutline(drawX, drawY, gap, width, height, thickness);
	}
	
	/**
	 * Draws the base crosshair square style.
	 * 
	 * @param drawX Centred X position of the crosshair.
	 * @param drawY Centred X position of the crosshair.
	 * @param gap Additional gap inside of the square.
	 * @param width Inner width of the square.
	 * @param height Inner height of the square.
	 * @param thickness Thickness of the square.
	 */
	private void drawBars(int drawX, int drawY, int gap, int width, int height, float thickness)
	{
		RGBA colour = this.getRenderColour();
		
		// Top
		RenderManager.drawFilledRectangle(drawX - width - thickness - gap, drawY - height - thickness - gap, drawX + width + thickness + gap, drawY - height - gap, colour, true);
		
		// Bottom
		RenderManager.drawFilledRectangle(drawX - width - thickness - gap, drawY + height + gap, drawX + width + thickness + gap, drawY + height + thickness + gap, colour, true);
		
		// Left
		RenderManager.drawFilledRectangle(drawX - width - thickness - gap, drawY - gap - height, drawX - width - gap, drawY + gap + height, colour, true);
		
		// Right
		RenderManager.drawFilledRectangle(drawX + width + gap, drawY - gap - height, drawX + width + thickness + gap, drawY + gap + height, colour, true);
	}
	
	/**
	 * Draws the inner and outer outline of the square.
	 * 
	 * @param drawX Centred X position of the crosshair.
	 * @param drawY Centred X position of the crosshair.
	 * @param gap Additional gap inside of the square.
	 * @param width Inner width of the square.
	 * @param height Inner height of the square.
	 * @param thickness Thickness of the square.
	 */
	private void drawOutline(int drawX, int drawY, int gap, int width, int height, float thickness)
	{
		// Inner
		RenderManager.drawRectangle(drawX - width - gap + 0.5F, drawY - height - gap + 0.5F, drawX + width + gap - 0.5F, drawY + height + gap - 0.5F, 2.0F, Theme.BLACK, true);
		
		// Outer
		RenderManager.drawRectangle(drawX - width - thickness - gap - 0.5F, drawY - height - thickness - gap - 0.5F, drawX + width + thickness + gap + 0.5F, drawY + height + thickness + gap + 0.5F, 2.0F, Theme.BLACK, true);
	}
}
