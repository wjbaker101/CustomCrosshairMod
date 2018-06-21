package sparkless101.crosshairmod.crosshair.style.styles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.style.CrosshairStyle;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.RenderManager;
import sparkless101.crosshairmod.gui.utils.Theme;

public class CrossStyle extends CrosshairStyle
{
	private int drawX;
	
	private int drawY;
	
	private int renderGap;
	
	public CrossStyle(Minecraft mc, Crosshair crosshair)
	{
		super(mc, crosshair);
	}

	/**
	 * Draws the cross crosshair.
	 */
	@Override
	public void drawCrosshairStyle(int drawX, int drawY, int renderGap, RGBA renderColour)
	{
		if (!this.shouldRenderCrosshair()) return;
		
		//this.drawOutline(drawX, drawY, renderGap);
		
		this.drawVerticalBars(drawX, drawY, renderGap, renderColour);
		
		this.drawHorizontalBars(drawX, drawY, renderGap, renderColour);
	}
	
	private void drawVerticalBars(int drawX, int drawY, int renderGap, RGBA renderColour)
	{
		float thickness = (int)this.crosshair.properties.get("crosshair_thickness").getValue() / 2.0F;
		
		int height = (int)this.crosshair.properties.get("crosshair_height").getValue();
		
		// Top bar
		RenderManager.drawFilledRectangle(drawX - thickness, drawY - renderGap - height, drawX + thickness, drawY - renderGap, renderColour, true);
		
		// Bottom bar
		RenderManager.drawFilledRectangle(drawX - thickness, drawY + renderGap, drawX + thickness, drawY + renderGap + height, renderColour, true);
	}
	
	private void drawHorizontalBars(int drawX, int drawY, int renderGap, RGBA renderColour)
	{
		float thickness = (int)this.crosshair.properties.get("crosshair_thickness").getValue() / 2.0F;
		
		int width = (int)this.crosshair.properties.get("crosshair_width").getValue();
		
		// Right bar
		RenderManager.drawFilledRectangle(drawX - renderGap - width, drawY - thickness, drawX - renderGap, drawY + thickness, renderColour, true);
		
		// Left bar
		RenderManager.drawFilledRectangle(drawX + renderGap, drawY - thickness, drawX + renderGap + width, drawY + thickness, renderColour, true);
	}
	
	private void drawOutline(int drawX, int drawY, int renderGap)
	{
		float thickness = (int)this.crosshair.properties.get("crosshair_thickness").getValue() / 2.0F;

		int width = (int)this.crosshair.properties.get("crosshair_width").getValue();
		int height = (int)this.crosshair.properties.get("crosshair_height").getValue();
		
		float[] points = new float[]
		{
			drawX - thickness, drawY - renderGap - height,
			drawX - thickness, drawY - renderGap - thickness,
			drawX - thickness, drawY - renderGap - thickness,
			drawX - renderGap - width, drawY - renderGap - thickness,
			drawX - renderGap - width, drawY - renderGap - thickness,
			drawX - renderGap - width, drawY + renderGap + thickness,
			drawX - renderGap - width, drawY + renderGap + thickness,
			drawX - thickness, drawY + renderGap + thickness,
			drawX - thickness, drawY + renderGap + thickness,
			drawX - thickness, drawY + height + renderGap + thickness - 1.0F,
			drawX - thickness, drawY + height + renderGap + thickness - 1.0F,
			drawX + thickness, drawY + height + renderGap + thickness - 1.0F,
			drawX + thickness, drawY + height + renderGap + thickness - 1.0F,
			drawX + thickness, drawY + renderGap + thickness,
			drawX + thickness, drawY + renderGap + thickness,
			drawX + width + thickness, drawY + renderGap + thickness,
		};
		
		RenderManager.drawLines(points, 2.0F, Theme.BLACK, true);
	}
}
