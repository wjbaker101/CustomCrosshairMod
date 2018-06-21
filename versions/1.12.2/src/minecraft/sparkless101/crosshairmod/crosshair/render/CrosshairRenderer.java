package sparkless101.crosshairmod.crosshair.render;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.crosshair.properties.property.IntegerProperty;
import sparkless101.crosshairmod.crosshair.style.CrosshairStyle;
import sparkless101.crosshairmod.crosshair.style.styles.DefaultStyle;
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
		int styleProperty = ((IntegerProperty)this.crosshair.properties.get("crosshair_style")).getValue();
		
		// Checks whether the style is valid
		// Sets the style to 0 by default if invalid
		int style = this.crosshair.styles.containsKey(styleProperty) ? styleProperty : 0;
		
		this.crosshair.styles.get(style).drawCrosshairStyle(x, y, 0, Theme.WHITE);
	}
	
	private int calculateRenderGap()
	{
		int originalGap = (int)this.crosshair.properties.get("crosshair_gap").getValue();
		
		boolean spectator = mc.player.isSpectator();
		boolean holdingItem = mc.player.getHeldItemMainhand() != null;
		boolean dynamicAttackIndicatorEnabled = (boolean)this.crosshair.properties.get("dynamic_attackindicator_enabled").getValue();
		boolean dynamicBowEnabled = (boolean)this.crosshair.properties.get("dynamic_bow_enabled").getValue();
		
		if (!spectator && holdingItem && (dynamicAttackIndicatorEnabled || dynamicBowEnabled))
		{
			ItemStack heldItem = mc.player.getHeldItemMainhand();
			
			if (dynamicBowEnabled && heldItem.getItem() == Items.BOW)
			{
				int useCount = mc.player.getItemInUseCount();
				
				float bowExtension = (heldItem.getItem().getMaxItemUseDuration(heldItem) - useCount) / 20.0F;
				
				if (bowExtension == 0 || bowExtension > 1.0F) bowExtension = 1.0F;
				
				float gapOffset = (1.0F - bowExtension) * (originalGap * 5) * 2;
				
				return originalGap + (int)gapOffset;
			}
		}
		
		return originalGap;
	}
}
