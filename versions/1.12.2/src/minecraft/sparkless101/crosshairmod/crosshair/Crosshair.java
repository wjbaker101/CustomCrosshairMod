package sparkless101.crosshairmod.crosshair;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sparkless101.crosshairmod.crosshair.properties.CrosshairProperties;
import sparkless101.crosshairmod.crosshair.properties.property.IntegerProperty;
import sparkless101.crosshairmod.crosshair.render.CrosshairRenderer;
import sparkless101.crosshairmod.crosshair.style.CrosshairStyle;
import sparkless101.crosshairmod.crosshair.style.styles.CircleStyle;
import sparkless101.crosshairmod.crosshair.style.styles.CrossStyle;
import sparkless101.crosshairmod.crosshair.style.styles.DefaultStyle;
import sparkless101.crosshairmod.gui.utils.Theme;

/**
 * Represents a crosshair, storing the properties and style to render in-game.
 * 
 * @author Sparkless101
 *
 */
public class Crosshair
{
	/**
	 * Local instance of Minecraft.
	 */
	private Minecraft mc;
	
	/**
	 * Defines functionality for rendering the crosshair.
	 */
	private CrosshairRenderer renderer;
	
	/**
	 * Stores the properties of the crosshair.<br>
	 * e.g. Colour, size, style, etc...
	 */
	private CrosshairProperties properties;
	
	/**
	 * Stores the items which should be checked for when rendering its cooldown.
	 */
	private final Item[] itemCooldownItems =
	{
		Items.ENDER_PEARL,
		Items.CHORUS_FRUIT,
	};
	
	/**
	 * Stores the styles the crosshair can be displayed as.
	 */
	private HashMap<Integer, CrosshairStyle> styles;
	
	/**
	 * Initialise the crosshair, by setting up default crosshair properties.
	 * 
	 * @param mc Minecraft instance.
	 */
	public Crosshair(Minecraft mc)
	{
		this.mc = mc;
		
		this.properties = new CrosshairProperties();
		
		this.createStyles();
		
		this.renderer = new CrosshairRenderer(this.mc, this);
	}
	
	/**
	 * Add the crosshair styles to the HashMap.
	 */
	private void createStyles()
	{
		this.styles = new HashMap<Integer, CrosshairStyle>();

		this.styles.put(0, new DefaultStyle(this.mc, this));
		this.styles.put(1, new CrossStyle(this.mc, this));
		this.styles.put(2, new CircleStyle(this.mc, this));
	}
	
	/**
	 * Renders the final look of the crosshair.
	 * 
	 * @param drawX The X position to render the crosshair.
	 * @param drawY The Y position to render the crosshair.
	 */
	public void drawCrosshair(int drawX, int drawY)
	{
		this.renderer.render(drawX, drawY);
	}
	
	public CrosshairProperties getProperties()
	{
		return this.properties;
	}
	
	public HashMap<Integer, CrosshairStyle> getStyles()
	{
		return this.styles;
	}
}
