package sparkless101.crosshairmod.crosshair.render;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.properties.property.IntegerProperty;
import sparkless101.crosshairmod.crosshair.style.CrosshairStyle;
import sparkless101.crosshairmod.crosshair.style.styles.DefaultStyle;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.Theme;

/**
 * Handles the rendering of the crosshair.
 * 
 * @author Sparkless101
 *
 */
public class CrosshairRenderer
{
	/**
	 * The crosshair to draw.
	 */
	private Crosshair crosshair;
	
	/**
	 * Minecraft instance.
	 */
	private Minecraft mc;
	
	public CrosshairRenderer(Minecraft mc, Crosshair crosshair)
	{
		this.mc = mc;
		
		this.crosshair = crosshair;
	}
	
	/**
	 * Render the crosshair with the current style.
	 * 
	 * @param x X position on the screen to draw the crosshair to.
	 * @param y Y position on the screen to draw the crosshair to.
	 */
	public void render(int x, int y)
	{
		int gap = (int)this.crosshair.getProperties().get("crosshair_gap").getValue();
		
		RGBA colour = (RGBA)this.crosshair.getProperties().get("crosshair_colour").getValue();
		
		this.preRotation(x, y);
        
		this.getStyle().drawCrosshairStyle(x, y, colour);
        
		this.postRotation(x, y);
	}
	
	/**
	 * Called before rendering the crosshair, rotates the crosshair.
	 * 
	 * @param x X position of the crosshair.
	 * @param y Y position of the crosshair.
	 */
	private void preRotation(int x, int y)
	{
		int rotation = (int)this.crosshair.getProperties().get("crosshair_rotation").getValue();
		
		GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glRotatef(rotation, x, y, 8000);
        GL11.glTranslatef(-x, -y, 0);
        
        if ((int)this.crosshair.getProperties().get("crosshair_style").getValue() != 0)
        {
        	GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
        }
	}
	
	/**
	 * Called after the crosshair has been rendered. Reverts any changes made from the rotation.
	 * 
	 * @param x X position of the crosshair.
	 * @param y Y position of the crosshair.
	 */
	private void postRotation(int x, int y)
	{
		GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
		
        GL11.glPopMatrix();
	}
	
	/**
	 * Gets the currently selected style, or the Default crosshair if invalid value.
	 * 
	 * @return Crosshair style to be displayed.
	 */
	private CrosshairStyle getStyle()
	{
		int styleProperty = ((IntegerProperty)this.crosshair.getProperties().get("crosshair_style")).getValue();

		// Checks whether the style is valid
		// Sets the style to 0 by default if invalid
		if (this.crosshair.getStyles().containsKey(styleProperty))
		{
			return this.crosshair.getStyles().get(styleProperty);
		}
		else
		{
			return this.crosshair.getStyles().get(0);
		}
	}
}
