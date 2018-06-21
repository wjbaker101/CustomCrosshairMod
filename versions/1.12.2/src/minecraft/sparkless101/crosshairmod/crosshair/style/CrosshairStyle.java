package sparkless101.crosshairmod.crosshair.style;

import net.minecraft.client.Minecraft;
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
	 * Initialises the crosshair style.
	 * 
	 * @param mc Minecraft instance.
	 */
	public CrosshairStyle(Minecraft mc, Crosshair crosshair)
	{
		this.mc = mc;
		
		this.crosshair = crosshair;
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
	public abstract void drawCrosshairStyle(int drawX, int drawY, int renderGap, RGBA renderColour);
	
	/**
	 * Checks whether or not the crosshair should be rendered depending on its properties.
	 * 
	 * @return Whether the crosshair should be rendered.
	 */
	protected boolean shouldRenderCrosshair()
	{
		boolean enabled = (boolean)this.crosshair.properties.get("mod_enabled").getValue();
		
		boolean visibleDefault = (boolean)this.crosshair.properties.get("visible_default").getValue();
		
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
		boolean propertyValue = (boolean)this.crosshair.properties.get("visible_thirdperson").getValue();
		
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
		boolean propertyValue = (boolean)this.crosshair.properties.get("visible_thirdperson").getValue();
		
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
		boolean propertyValue = (boolean)this.crosshair.properties.get("visible_hiddengui").getValue();
		
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
		boolean propertyValue = (boolean)this.crosshair.properties.get("visible_spectator").getValue();
		
		boolean visibleSpectator = !mc.player.isSpectator() || (mc.player.isSpectator() && propertyValue);
		
		return visibleSpectator;
	}
}
