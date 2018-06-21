package sparkless101.crosshairmod.crosshair.style.styles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.style.CrosshairStyle;
import sparkless101.crosshairmod.gui.utils.RGBA;

/**
 * Handles rendering of the default Minecraft crosshair.
 * 
 * @author Sparkless101
 *
 */
public class DefaultStyle extends CrosshairStyle
{
	public DefaultStyle(Minecraft mc, Crosshair crosshair)
	{
		super(mc, crosshair);
	}

	/**
	 * Draws the default crosshair.<br>
	 * Pretty much a copy of how the crosshair is rendered in GuiIngame.
	 */
	@Override
	public void drawCrosshairStyle(int drawX, int drawY, int renderGap, RGBA renderColour)
	{
		if (!this.shouldRenderCrosshair()) return;

		GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(775, 769, 1, 0);
        
		mc.getTextureManager().bindTexture(Gui.ICONS);
        mc.ingameGUI.drawTexturedModalRect(drawX - 7, drawY - 7, 0, 0, 16, 16);

        GL11.glDisable(GL11.GL_BLEND);
	}
}
