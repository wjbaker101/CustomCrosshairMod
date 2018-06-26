package sparkless101.crosshairmod.crosshair.style;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import sparkless101.crosshairmod.crosshair.Crosshair;
import sparkless101.crosshairmod.gui.utils.RGBA;

/**
 * Base class for a crosshair style.
 * 
 * @author Sparkless101
 *
 */
public abstract class CrosshairStyle
{
	/**
	 * Local instance of Minecraft.
	 */
	protected Minecraft mc;
	
	/**
	 * Crosshair this style belongs to.
	 */
	protected Crosshair crosshair;
	
	/**
	 * Current numbers of ticks the rainbow crosshair has been running for.
	 */
	private int rainbowTicks;
	
	/**
	 * Initialises the crosshair style.
	 * 
	 * @param mc Minecraft instance.
	 */
	public CrosshairStyle(Minecraft mc, Crosshair crosshair)
	{
		this.mc = mc;
		
		this.crosshair = crosshair;
		
		this.rainbowTicks = 0;
	}
	
	/**
	 * 
	 * Draws the crosshair.
	 * 
	 * @param drawX X position to draw the crosshair.
	 * @param drawY Y position to draw the crosshair.
	 * @param renderGap Calculated gap at the centre of the crosshair.
	 * @param renderColour Calculated base colour of the crosshair.
	 */
	public abstract void drawCrosshairStyle(int drawX, int drawY, RGBA renderColour);
	

	
	/**
	 * Calculates the gap within the crosshair for when it is being rendered.<br>
	 * Takes into account item swing time and bow pulling animation.
	 * 
	 * @return Gap to be used when rendering the crosshair.
	 */
	protected int calculateRenderGap()
	{
		int originalGap = (int)this.crosshair.getProperties().get("crosshair_gap").getValue();
		
		boolean isSpectator = mc.player.isSpectator();
		boolean isHoldingItem = mc.player.getHeldItemMainhand().getItem() != Items.AIR;
		boolean dynamicAttackIndicatorEnabled = (boolean)this.crosshair.getProperties().get("dynamic_attackindicator_enabled").getValue();
		boolean dynamicBowEnabled = (boolean)this.crosshair.getProperties().get("dynamic_bow_enabled").getValue();
		
		if (!isSpectator && isHoldingItem && (dynamicAttackIndicatorEnabled || dynamicBowEnabled))
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
			
			if (dynamicAttackIndicatorEnabled)
            {
                float attackCooldown = mc.player.getCooledAttackStrength(mc.getRenderPartialTicks());
                
                return originalGap + (int)((1.0F - attackCooldown) * (originalGap + 5) * 2);
            }
		}
		
		return originalGap;
	}
	
	/**
	 * Gets the correct colour for the crosshair, depending on the highlighting.
	 * 
	 * @return Colour of the crosshair.
	 */
	protected RGBA getRenderColour()
	{
		if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY)
        {
            if ((mc.objectMouseOver.entityHit instanceof EntityPlayer) && (boolean)this.crosshair.getProperties().get("highlight_player_enabled").getValue())
            {
                return (RGBA)this.crosshair.getProperties().get("highlight_passive_colour").getValue();
            }
            else if (mc.objectMouseOver.entityHit instanceof EntityMob && (boolean)this.crosshair.getProperties().get("highlight_hostile_enabled").getValue())
            {
                return (RGBA)this.crosshair.getProperties().get("highlight_hostile_colour").getValue();
            }
            else if ((boolean)this.crosshair.getProperties().get("highlight_passive_enabled").getValue())
            {
            	return (RGBA)this.crosshair.getProperties().get("highlight_passive_colour").getValue();
            }
        }
		
		if ((boolean)this.crosshair.getProperties().get("rainbow_enabled").getValue())
		{
			return this.getRainbowColour();
		}
		
		return (RGBA)this.crosshair.getProperties().get("crosshair_colour").getValue();
	}
	
	/**
	 * Gets the rainbow colour.
	 * 
	 * @return Colour of the crosshair.
	 */
	private RGBA getRainbowColour()
	{
		this.rainbowTicks++;
		
		if (this.rainbowTicks >= 10000) this.rainbowTicks = 0;
		
		int opacity = ((RGBA)this.crosshair.getProperties().get("crosshair_colour").getValue()).getOpacity();
		
		RGBA rainbowColour = (new RGBA(255, 255, 255, opacity))
								.setRed(this.getRainbowColourComponent(0.0F))
								.setGreen(this.getRainbowColourComponent(2.0F))
								.setBlue(this.getRainbowColourComponent(4.0F));
		
		return rainbowColour;
	}
	
	/**
	 * Gets an individual colour component from the given offset.
	 * 
	 * @param offset Offset of the colour.
	 * @return Colour component value.
	 */
	private int getRainbowColourComponent(float offset)
	{
		int speed = (int)this.crosshair.getProperties().get("rainbow_speed").getValue();
		
		return (int)(Math.sin(((speed / 100000.0F) * this.rainbowTicks) + offset) * 127 + 128);
	}
	
	/**
	 * Checks whether or not the crosshair should be rendered depending on its properties.
	 * 
	 * @return Whether the crosshair should be rendered.
	 */
	protected boolean shouldRenderCrosshair()
	{
		boolean enabled = (boolean)this.crosshair.getProperties().get("mod_enabled").getValue();
		
		boolean visibleDefault = (boolean)this.crosshair.getProperties().get("visible_default").getValue();
		
		boolean visibleDebug = this.visibleDebug();
		
		boolean visibleThirdPerson = this.visibleThirdPerson();
		
		boolean visibleHiddenGUI = this.visibleHiddenGUI();
		
		boolean visibleSpectator = this.visibleSpectator();
		
		return enabled && visibleDefault && visibleDebug && visibleThirdPerson && visibleHiddenGUI && visibleSpectator;
	}
	
	/**
	 * Checks whether the crosshair should be rendered in terms of whether the debug GUI is visible.
	 * 
	 * @return True if property is enabled and debug GUI is visible.<br>
	 * Or if the debug GUI isn't visible.
	 */
	private boolean visibleThirdPerson()
	{
		boolean propertyValue = (boolean)this.crosshair.getProperties().get("visible_thirdperson").getValue();
		
		boolean visibleThirdPerson = !(mc.gameSettings.thirdPersonView > 0) || (mc.gameSettings.thirdPersonView > 0 && propertyValue);
		
		return visibleThirdPerson;
	}
	
	/**
	 * Checks whether the crosshair should be rendered in terms of whether the player is in third person or not.
	 * 
	 * @return True if property is enabled and player is in third person.<br>
	 * Or if the player isn't in third person mode.
	 */
	private boolean visibleDebug()
	{
		boolean propertyValue = (boolean)this.crosshair.getProperties().get("visible_thirdperson").getValue();
		
		boolean visibleDebug = !(mc.gameSettings.showDebugInfo) || (mc.gameSettings.showDebugInfo && propertyValue);
		
		return visibleDebug;
	}

	/**
	 * Checks whether the crosshair should be rendered in terms of whether the GUI is currently being hidden or not.
	 * 
	 * @return True if property is enabled and GUI is hidden.<br>
	 * Or if the GUI isn't being hidden.
	 */
	private boolean visibleHiddenGUI()
	{
		boolean propertyValue = (boolean)this.crosshair.getProperties().get("visible_hiddengui").getValue();
		
		boolean visibleHiddenGUI = !mc.gameSettings.hideGUI || (mc.gameSettings.hideGUI && propertyValue);
		
		return visibleHiddenGUI;
	}
	
	/**
	 * Checks whether the crosshair should be rendered in terms of whether the player is in spectator mode or not.
	 * 
	 * @return True if property is enabled and player is in spectator mode.<br>
	 * Or if the player isn't in spectator mode.
	 */
	private boolean visibleSpectator()
	{
		boolean propertyValue = (boolean)this.crosshair.getProperties().get("visible_spectator").getValue();
		
		boolean visibleSpectator = !mc.player.isSpectator() || (mc.player.isSpectator() && propertyValue);
		
		return visibleSpectator;
	}
}
