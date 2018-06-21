package sparkless101.crosshairmod.hooks;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import sparkless101.crosshairmod.crosshair.properties.Properties;
import sparkless101.crosshairmod.gui.screens.screen.edit_crosshair.EditCrosshairScreen;
import sparkless101.crosshairmod.main.CustomCrosshairMod;

public class Hooks
{
	/**
	 * Allows Minecraft to draw the crosshair.<br>
	 * 
	 * Usage: EntityRenderer.updateCameraAndRender:1178
	 * 
	 * @param drawX Horizontal mid point of the screen.
	 * @param drawY Vertical mid point of the screen.
	 */
	public static void drawCrosshair(int drawX, int drawY)
	{
		CustomCrosshairMod.INSTANCE.getCrosshair().drawCrosshair(drawX, drawY);
	}
	
	/**
	 * Allows Minecraft to check whether or not the direction axes in the debug GUI should be rendered whilst the mod is active.
	 * 
	 * Usage: GuiIngame.renderAttackIndicator:431
	 * 
	 * @return Whether or not the axes should be rendered.
	 */
	public static boolean shouldRenderAxes()
	{
		boolean enabled = (boolean)CustomCrosshairMod.INSTANCE.getCrosshair().properties.get("mod_enabled").getValue();
		
		boolean visibleDebug = (boolean)CustomCrosshairMod.INSTANCE.getCrosshair().properties.get("visible_debug").getValue();
		
		return !enabled || (enabled && visibleDebug);
	}
	
	/**
	 * Allows Minecraft to check whether or not the default crosshair should be rendered.
	 * 
	 * Usage: GuiIngame.renderAttackIndicator:443
	 * 
	 * @return Whether or not the default crosshair should be rendered.
	 */
	public static boolean shouldRenderDefaultCrosshair()
	{
		boolean enabled = (boolean)CustomCrosshairMod.INSTANCE.getCrosshair().properties.get("mod_enabled").getValue();
		
		return !enabled;
	}
	
	public static void openEditCrosshairScreen(Minecraft mc)
	{
		String keybind = (String)CustomCrosshairMod.INSTANCE.getCrosshair().properties.get("keybind_screen_editcrosshair").getValue();
		
		if (mc.currentScreen != null || !Keyboard.isKeyDown(Keyboard.getKeyIndex(keybind))) return;
		
		mc.displayGuiScreen(new EditCrosshairScreen());
	}
}
