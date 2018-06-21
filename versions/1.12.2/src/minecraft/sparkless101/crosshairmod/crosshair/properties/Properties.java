package sparkless101.crosshairmod.crosshair.properties;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import sparkless101.crosshairmod.crosshair.properties.property.BooleanProperty;
import sparkless101.crosshairmod.crosshair.properties.property.IntegerProperty;
import sparkless101.crosshairmod.crosshair.properties.property.Property;
import sparkless101.crosshairmod.crosshair.properties.property.RGBAProperty;
import sparkless101.crosshairmod.crosshair.properties.property.StringProperty;
import sparkless101.crosshairmod.gui.utils.RGBA;
import sparkless101.crosshairmod.gui.utils.Theme;

public class Properties
{
	/**
	 * Stores the properties of the crosshair.
	 */
	private final HashMap<String, Property> properties;
	
	public Properties()
	{
		this.properties = new HashMap<String, Property>();
		
		this.initialiseDefaults();
	}
	
	/**
	 * Add properties to the HashMap, defining their aliases and values.
	 */
	private void initialiseDefaults()
	{
		this.properties.put("mod_enabled", new BooleanProperty(true));
		this.properties.put("keybind_screen_editcrosshair", new StringProperty("C"));

		this.properties.put("crosshair_style", new IntegerProperty(0));
		this.properties.put("crosshair_colour", new RGBAProperty(Theme.WHITE));
		this.properties.put("crosshair_width", new IntegerProperty(5));
		this.properties.put("crosshair_height", new IntegerProperty(5));
		this.properties.put("crosshair_gap", new IntegerProperty(3));
		this.properties.put("crosshair_thickness", new IntegerProperty(2));
		this.properties.put("crosshair_rotation", new IntegerProperty(0));
		
		this.properties.put("visible_default", new BooleanProperty(true));
		this.properties.put("visible_hiddengui", new BooleanProperty(true));
		this.properties.put("visible_debug", new BooleanProperty(true));
		this.properties.put("visible_thirdperson", new BooleanProperty(false));
		this.properties.put("visible_spectator", new BooleanProperty(true));

		this.properties.put("outline_enabled", new BooleanProperty(true));
		this.properties.put("outline_colour", new RGBAProperty(Theme.BLACK));

		this.properties.put("dot_enabled", new BooleanProperty(false));
		this.properties.put("dot_colour", new RGBAProperty(Theme.WHITE));

		this.properties.put("dynamic_attackindicator_enabled", new BooleanProperty(true));
		this.properties.put("dynamic_bow_enabled", new BooleanProperty(true));

		this.properties.put("highlight_hostile_enabled", new BooleanProperty(true));
		this.properties.put("highlight_passive_enabled", new BooleanProperty(true));
		this.properties.put("highlight_players_enabled", new BooleanProperty(true));

		this.properties.put("highlight_hostile_colour", new RGBAProperty(new RGBA(255, 0, 0, 255)));
		this.properties.put("highlight_passive_colour", new RGBAProperty(new RGBA(0, 255, 0, 255)));
		this.properties.put("highlight_players_colour", new RGBAProperty(new RGBA(0, 0, 255, 255)));

		this.properties.put("itemcooldown_enabled", new BooleanProperty(true));
		this.properties.put("itemcooldown_colour", new RGBAProperty(Theme.WHITE.setOpacity(80)));

		this.properties.put("rainbow_enabled", new BooleanProperty(false));
		this.properties.put("rainbow_speed", new IntegerProperty(500));
	}
	
	/**
	 * Gets a property from the alias.
	 * 
	 * @param alias Name of the property to get.
	 * @return Property with the given alias.
	 */
	public Property get(String alias)
	{
		return this.properties.get(alias);
	}
	
	/**
	 * Sets the value of the property at the given key of the HashMap.
	 * 
	 * @param key Alias of the property.
	 * @param property New property to add to the HashMap.
	 */
	public void set(String key, Property property)
	{
		this.properties.put(key, property);
	}
	
	/**
	 * Gets a list of entries in the HashMap of property aliases and values.
	 * 
	 * @return An unordered Set of Entry containing the keys and values in the HashMap.
	 */
	public Set<Entry<String, Property>> getList()
	{
		return this.properties.entrySet();
	}
}
