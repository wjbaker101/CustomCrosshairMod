package sparkless101.crosshairmod.crosshair.style.styles;

import net.minecraft.client.Minecraft;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.style.CrosshairStyle;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class CrossStyle extends CrosshairStyle
{
	public CrossStyle(Minecraft mc, Crosshair crosshair)
	{
		super(mc, crosshair);
	}

	/**
	 * Draws the cross crosshair.
	 */
	@Override
	public void drawCrosshairStyle(int drawX, int drawY, RGBA renderColour)
	{
		if (!this.shouldRenderCrosshair()) return;
		
		boolean outlineEnabled = (boolean)this.crosshair.getProperties().get("outline_enabled").getValue();
		
		float thickness = (int)this.crosshair.getProperties().get("crosshair_thickness").getValue() / 2.0F;
		
		int width = (int)this.crosshair.getProperties().get("crosshair_width").getValue();
		int height = (int)this.crosshair.getProperties().get("crosshair_height").getValue();
		
		int gap = this.calculateRenderGap();
		
		RGBA colour = this.getRenderColour();
		
		if (outlineEnabled) this.drawOutline(drawX, drawY, gap, width, height, thickness);
		
		this.drawBars(drawX, drawY, gap, width, height, thickness, colour);
	}
	
	private void drawBars(int drawX, int drawY, int renderGap, int width, int height, float thickness, RGBA renderColour)
	{
		// Top bar
		RenderManager.drawFilledRectangle(drawX - thickness, drawY - renderGap - height, drawX + thickness, drawY - renderGap, renderColour, true);
		
		// Bottom bar
		RenderManager.drawFilledRectangle(drawX - thickness, drawY + renderGap, drawX + thickness, drawY + renderGap + height, renderColour, true);
		
		// Right bar
		RenderManager.drawFilledRectangle(drawX - renderGap - width, drawY - thickness, drawX - renderGap, drawY + thickness, renderColour, true);
		
		// Left bar
		RenderManager.drawFilledRectangle(drawX + renderGap, drawY - thickness, drawX + renderGap + width, drawY + thickness, renderColour, true);
	}
	
	private void drawOutline(int drawX, int drawY, int renderGap, int width, int height, float thickness)
	{
		RGBA outlineColour = (RGBA)this.crosshair.getProperties().get("outline_colour").getValue();
		
		float[] top = new float[]
		{
			drawX - thickness - 0.5F, drawY - renderGap - height - 0.5F,
			drawX - thickness - 0.5F, drawY - renderGap + 0.5F,
			
			drawX - thickness - 0.5F, drawY - renderGap + 0.5F,
			drawX + thickness + 0.5F, drawY - renderGap + 0.5F,
			
			drawX + thickness + 0.5F, drawY - renderGap + 0.5F,
			drawX + thickness + 0.5F, drawY - renderGap - height - 0.5F,
			
			drawX + thickness + 0.5F, drawY - renderGap - height - 0.5F,
			drawX - thickness - 0.5F, drawY - renderGap - height - 0.5F,
		};
		
		float[] bottom = new float[]
		{
			drawX - thickness - 0.5F, drawY + renderGap + height + 0.5F,
			drawX - thickness - 0.5F, drawY + renderGap - 0.5F,
			
			drawX - thickness - 0.5F, drawY + renderGap - 0.5F,
			drawX + thickness + 0.5F, drawY + renderGap - 0.5F,
			
			drawX + thickness + 0.5F, drawY + renderGap - 0.5F,
			drawX + thickness + 0.5F, drawY + renderGap + height + 0.5F,
			
			drawX + thickness + 0.5F, drawY + renderGap + height + 0.5F,
			drawX - thickness - 0.5F, drawY + renderGap + height + 0.5F,
		};
		
		float[] right = new float[]
		{
			drawX + renderGap + width + 0.5F, drawY - thickness - 0.5F,
			drawX + renderGap + width + 0.5F, drawY + thickness + 0.5F,
			
			drawX + renderGap + width + 0.5F, drawY + thickness + 0.5F,
			drawX + renderGap - 0.5F, drawY + thickness + 0.5F,
			
			drawX + renderGap - 0.5F, drawY + thickness + 0.5F,
			drawX + renderGap - 0.5F, drawY - thickness - 0.5F,
			
			drawX + renderGap - 0.5F, drawY - thickness - 0.5F,
			drawX + renderGap + width + 0.5F, drawY - thickness - 0.5F,
		};
		
		float[] left = new float[]
		{
			drawX - renderGap - width - 0.5F, drawY - thickness - 0.5F,
			drawX - renderGap - width - 0.5F, drawY + thickness + 0.5F,
			
			drawX - renderGap - width - 0.5F, drawY + thickness + 0.5F,
			drawX - renderGap + 0.5F, drawY + thickness + 0.5F,
			
			drawX - renderGap + 0.5F, drawY + thickness + 0.5F,
			drawX - renderGap + 0.5F, drawY - thickness - 0.5F,
			
			drawX - renderGap + 0.5F, drawY - thickness - 0.5F,
			drawX - renderGap - width - 0.5F, drawY - thickness - 0.5F,
		};
		
		RenderManager.drawLines(top, 2.0F, outlineColour, true);
		RenderManager.drawLines(bottom, 2.0F, outlineColour, true);
		
		RenderManager.drawLines(right, 2.0F, outlineColour, true);
		RenderManager.drawLines(left, 2.0F, outlineColour, true);
	}
}
