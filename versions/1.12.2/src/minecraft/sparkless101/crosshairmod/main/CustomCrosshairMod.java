package sparkless101.crosshairmod.main;

import net.minecraft.client.Minecraft;
import sparkless101.crosshairmod.crosshair.Crosshair;

public class CustomCrosshairMod
{
	/**
	 * Name of the mod.
	 */
	public static final String MOD_NAME = "Custom Crosshair Mod";
	
	/**
	 * Version of the mod.
	 */
	public static final String MOD_VERSION = "0.9.0";
	
	/**
	 * Version of Minecraft.
	 */
	public static final String MC_VERSION = "1.12.2";
	
	public static final String CURSEFORGE = "https://www.curseforge.com/projects/242995/";
	
	public static final String MC_FORUMS = "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2637819/";
	
	/**
	 * Local instance of Minecraft.
	 */
	private final Minecraft mc;
	
	/**
	 * The crosshair to display in-game.
	 */
	private final Crosshair crosshair;
	
	/**
	 * Instance of the this class.
	 */
	public static CustomCrosshairMod INSTANCE = new CustomCrosshairMod();
	
	public CustomCrosshairMod()
	{
		this.mc = Minecraft.getMinecraft();
		
		this.crosshair = new Crosshair(this.mc);
	}
	
	public Crosshair getCrosshair()
	{
		return this.crosshair;
	}
}
